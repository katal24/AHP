package sp;


import com.google.gson.Gson;
import model.DB;
import model.Questionnaire;
import model.SurveysEntity;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

//import org.codehaus.jackson.map.ObjectMapper;

/**
 * Created by dawid on 02.04.16.
 */

@EnableWebMvc
@EnableAutoConfiguration
@Controller
public class BaseController {

    String user = "Dawid";
    public Questionnaire quest;
    public static NewQuest nq = new NewQuest();
    public static NewCompletedQuest ncq = new NewCompletedQuest();
    public Map<String, Object> model = new HashMap<String, Object>();
    public double[] result;
    public LinkedList<Result> resultList;
    double errorFactor;
    DB dbConnection;

    @RequestMapping("/getSurveyData/")
    @ResponseBody
    Map<String, Object> getSurveyData() throws SQLException {
        System.out.println("         GET DATY   YYYYYYYYYYYYYYYYY");
        System.out.println("         NAZWA : " + nq.getSurveyName());
        model.put("nazwa", nq.getSurveyName());
        model.put("categories", nq.getCategoriesList());
        model.put("variants", nq.getVariantsList());
        model.put("ileVar", nq.getVariantsList().size());
        model.put("dostep", nq.getAccess());


        return model;
    }

    @RequestMapping("/getDataToScroll")
    @ResponseBody
    Map<String, Object> getDataToScroll(){
        System.out.println("##########################@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2 jestem w base w getDataScrol");
        model.put("listToScroll", quest.getListToScrollFromMatrixes());
        return model;
    }

    @RequestMapping(value="/setAllData", method = RequestMethod.POST)
    @ResponseBody
    public void setAllData(@RequestBody String items){

        System.out.println("--------------ITEEEEEEEEEEEEEEEEEEEEEM: " + items);
        Gson gson = new Gson();
//
//        // zwiera dane z formularza step 1-3
        CompleteData cd = gson.fromJson(items, CompleteData.class);
        System.out.println("            DANE Z obiektu cd: " + cd.getItems());

        quest.setListToScroll(cd.getItems());
        System.out.println("            DANE Z KLASY: " + quest.getListToScroll());
      //  errorFactor = quest.setValueInMaps(); //wszystkie mapy w matrixes są już uzupełnione, przepisane do macierzy i wektory wyliczone

      //  if(errorFactor > 0.1){
            //cos trzeba zrobic
       // } else {
            quest.printAllMaps();
            // zapisuje rezultat do listy
            result = quest.countResult();
            System.out.println("=============================================================");
            System.out.println("=============================================================");
            System.out.println("========================== WYNIK ============================");
            System.out.println(Arrays.toString(result));
       // }
    }

    @RequestMapping("/getResult")
    @ResponseBody
    Map<String, Object> getResult(){
       // if(errorFactor > 0.1){
       //     model.put("eroor", new Integer(1));
       // }

        resultList = new LinkedList<Result>();
        for(int i=0; i<result.length; i++){
            resultList.add(new Result(nq.getVariantsList().get(i),result[i]));
        }

        Collections.sort(resultList);
        System.out.println("Posortowane: " + resultList);
        model.put("resultList", resultList);
       // model.put("eroor", new Integer(0));
        return model;
    }

    public void completeSutvey(String cs){
        System.out.println("        JEST W BASE COMPLETE ------------------------  " + cs);
        Gson gson = new Gson();
//
//        // zwiera dane z formularza step 1-3
        ncq = gson.fromJson(cs, NewCompletedQuest.class);
        System.out.println("WYPELNIONE: " + Arrays.toString(ncq.getCategoriesInput().toArray()));
        System.out.println("DRUGI WARIANT: " + ncq.getCategoriesInput().get(1).getValue());

        quest = new Questionnaire();
        quest.makeQuestionnaire();

        System.out.println("LISTA DO SUWAKOW: ");
        System.out.println(quest.getListToScrollFromMatrixes());
    }

    @RequestMapping(value = "/setCompletedData", method = RequestMethod.POST)//, headers = "content-type=application/x-www-form-urlencoded")
    @ResponseBody
    public void setCompletedData(@RequestBody String cs) throws ClassNotFoundException, SQLException, IOException {

        // wpisanie ankiety do bazy danych

        dbConnection = new DB();
        SurveysEntity surveysEntity = new SurveysEntity(nq.getAccess(), user, nq.getSurveyName(), nq.getCategoriesList(), nq.getVariantsList(), cs);
        dbConnection.saveSurvay(surveysEntity);
//
//               // cs to JSON zawierajacay dane z formularza (step 1-3)
        completeSutvey(cs);
    }

    // pobiera nazwe ankiety i zwraca dane z bazy i tworzy questionnaire
    @RequestMapping(value = "/setCompletedDataFromBase", method=RequestMethod.POST)
    @ResponseBody
    public void setCompletedDataFromBase(@RequestBody String name){

        //pobranie z bazy
        dbConnection = new DB();
        SurveysEntity survey = dbConnection.getSurveyForName("kamil");
        String cs1 = survey.getCompleted();
        nq = new NewQuest(survey);
        //stworzenie ankiety
        completeSutvey(cs1);
    }


    @RequestMapping(value = "/setSurveysData", method = RequestMethod.POST)//, headers = "content-type=application/x-www-form-urlencoded")
    @ResponseBody
    public void setSurveysData(@RequestBody String cs) throws ClassNotFoundException, SQLException, IOException {

        // cs to JSON zawierajacay dane z formularza (step 1-3)
        System.out.println("        JEEEEEEEEEEEEEEST W BASE ------------------------  " + cs);

        Gson gson = new Gson();

        // zwiera dane z formularza step 1-3
        nq = gson.fromJson(cs, NewQuest.class);

        nq.setLists();
        System.out.println("CATEGORIES: " + Arrays.toString(nq.getCategoriesList().toArray()));
        System.out.println("VARIANTS:   " + Arrays.toString(nq.getVariantsList().toArray()));

    }


    @RequestMapping("/hello")
    public String przykladModelu(Model model) {
        model.addAttribute("message", "DAWID JEST DZIWNY XDD");

        Questionnaire quest = new Questionnaire();
        quest.makeQuestionnaire();
        System.out.println("            HEEEEEEEEEEEEEEEEEEEEEEEEEEEELLLLLOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        System.out.println(quest.getVariants());
        System.out.println(Arrays.deepToString(quest.getMainMatrix()));

        return "hello";
    }

    @RequestMapping(value="/")
    public String index() {
//        Questionnaire quest = new Questionnaire();
//        quest.makeQuestionnaire();
        System.out.println("               jestem w index");

        System.out.println("            iiiiiindeeeeeeeeeeeeeeeeeeeexxxxxxxxxxxxxxxxxxxx");

//        dbConnection = new DB();
//
//        ArrayList<SurveysEntity> publicSurveys = dbConnection.getOwnerSurvey(user);
//
//        for(SurveysEntity s : publicSurveys){
//            System.out.println(s);
//        }

        return "index";
    }

    @RequestMapping(value="/user")
    public String login() {
        System.out.println("           LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOGIN");
        return "login";
    }


}
