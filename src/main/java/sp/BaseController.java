package sp;


import com.google.gson.Gson;
import model.DB;
import model.Questionnaire;
import model.SurveysEntity;
import model.User;
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
    User user1;
    public Questionnaire quest;
    public static NewQuest nq = new NewQuest();
    public static NewCompletedQuest ncq = new NewCompletedQuest();
    public Map<String, Object> model = new HashMap<String, Object>();
    public double[] result;
    public LinkedList<Result> resultList;
    double errorFactor;
    DB dbConnection;
    public static boolean logged = false;
    public String mainNazwa;
    public boolean verification = true;

    @RequestMapping("/getSurveyData/")
    @ResponseBody
    Map<String, Object> getSurveyData() throws SQLException {
        System.out.println("         GET DATY   YYYYYYYYYYYYYYYYY");
        System.out.println("         NAZWA : " + nq.getSurveyName());
        model.put("name", nq.getSurveyName());
        model.put("check", nq.getCheck());
        System.out.println("check::::::::::::::::::  " + nq.getCheck());
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
      //  model.put("listToScroll",0);

        model.put("listToScroll", quest.getListToScrollFromMatrixes());
        System.out.println("nazwa z BAZY:   " + mainNazwa);
        if(mainNazwa!=null)
        model.put("name",mainNazwa);
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
        errorFactor = quest.setValueInMaps(); //wszystkie mapy w matrixes są już uzupełnione, przepisane do macierzy i wektory wyliczone
//
//        if(errorFactor > 0.1){
//            cos trzeba zrobic
//        } else {
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


        resultList = new LinkedList<Result>();
        System.out.println("WYPISUJE RESULT W GETRESULT: " + result);
        for(int i=0; i<result.length; i++){
            resultList.add(new Result(nq.getVariantsList().get(i),result[i]));
        }

        Collections.sort(resultList);
        System.out.println("Posortowane: " + resultList);
        model.put("resultList", resultList);
        System.out.println("------------------------------------------------------------------errorfactor wynosi: " + errorFactor);
        if(errorFactor > 0.2 && verification){
            model.put("error", new Integer(0));
        } else {
            model.put("error", new Integer(1));
        }
        return model;
    }

    @RequestMapping("/getResult2")
    @ResponseBody
    Map<String, Object> getResult2(){
        model.put("resultList", resultList);
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
// TO ZAKOMENTOWYJE!!!!!!!!!!!!!!!!
        dbConnection = new DB();
        SurveysEntity surveysEntity = new SurveysEntity(nq.getAccess(), user1.getUsername(), nq.getSurveyName(), nq.getCategoriesList(), nq.getVariantsList(), nq.getCheck(), cs);
        dbConnection.saveSurvay(surveysEntity);

//               // cs to JSON zawierajacay dane z formularza (step 1-3)
        completeSutvey(cs);
    }

    // pobiera nazwe ankiety i zwraca dane z bazy i tworzy questionnaire
    @RequestMapping(value = "/setCompletedDataFromBase", method=RequestMethod.POST)
    @ResponseBody
    public void setCompletedDataFromBase(@RequestBody String name){

        System.out.println("publiczne survejki");
        //pobranie z bazy
        dbConnection = new DB();
        SurveysEntity survey = dbConnection.getSurveyForName(name);
        mainNazwa = name;
        String cs1 = survey.getCompleted();
        nq = new NewQuest(survey);
        //stworzenie ankiety
        completeSutvey(cs1);
    }


    // pobiera nazwy publicznych ankiet z bazy i zapisuje do modelu
    @RequestMapping(value = "/getPublicNamesFromBase")
    @ResponseBody
    Map<String, Object> getPublicNamesFromBase(){

        //pobranie z bazy
        dbConnection = new DB();
        ArrayList<String> namesList = dbConnection.getNamePublicSurvey();
        model.put("publicNamesSurveys", namesList);
        return model;
    }


    // pobiera nazwy publicznych ankiet z bazy i zapisuje do modelu
    @RequestMapping(value = "/getOwnerNamesFromBase")
    @ResponseBody
    Map<String, Object> getOwnerNamesFromBase(){
        System.out.println("Jestem w pobieraniu nazw dl azalogowanego");

        //pobranie z bazy
        dbConnection = new DB();
        ArrayList<String> namesList = dbConnection.getNameOwnerSurvey(user1.getUsername());
        model.put("ownerNamesSurveys", namesList);
        return model;
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


    @RequestMapping(value = "/addNewAccount", method = RequestMethod.POST)
    @ResponseBody
    public void addNewAccount(@RequestBody String newAcc){

        Gson gson = new Gson();
        User user2 = gson.fromJson(newAcc, User.class);

        dbConnection = new DB();
        dbConnection.saveUser(user2);
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    @ResponseBody
    public void loginUser(@RequestBody String loginUser){

        Gson gson = new Gson();
        user1 = gson.fromJson(loginUser, User.class);

        dbConnection = new DB();
        logged = dbConnection.exists(user1);
        user1.setLogged(logged);


        System.out.println("TAKI USER JEST W BAZIE: " + logged);
    }

    @RequestMapping("/getloggedUser")
    @ResponseBody
    Map<String, Object> getloggedUser(){


            model.put("zalogowany", logged);


        return model;
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
