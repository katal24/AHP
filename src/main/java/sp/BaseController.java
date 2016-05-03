package sp;

import model.Questionnaire;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by dawid on 02.04.16.
 */
///@RequestMapping("/")
//@EnableWebMvc
//@ComponentScan(basePackageClasses = BaseController.class)
//@Configuration
@EnableAutoConfiguration
@Controller
public class BaseController {

    @ModelAttribute("NQ")
    public NewQuest populateNewQuest(String imie) {
        NewQuest nq = new NewQuest();
        nq.setSurveyName(imie);
        return nq;
    }


   // @ResponseStatus(HttpStatus.OK)
   // @RequestMapping("/setSurveysData")
    @RequestMapping(value = "/setSurveysData", method = RequestMethod.POST)//, headers = "content-type=application/x-www-form-urlencoded")
//    @ResponseStatus(HttpStatus.OK)
   @ResponseBody
//    public void setSurveysData(@ModelAttribute("NQ") NewQuest cs) throws ClassNotFoundException, SQLException {
    public void setSurveysData(@RequestBody String cs) throws ClassNotFoundException, SQLException, IOException {

      //  NewQuest nq = new ObjectMapper().readValue(cs, NewQuest.class);

            //public void setSurveysData() throws ClassNotFoundException, SQLException {

        System.out.println("        JEEEEEEEEEEEEEEST W BASE ------------------------  " + cs);
      //  System.out.println(Arrays.toString(cs.getClass().getDeclaredFields()));


//        Questionnaire.setSurveysData(cs);
      //  return;// "setSurveysData";
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
        Questionnaire quest = new Questionnaire();
      //  quest.makeQuestionnaire();
        System.out.println("               jestem w index");

        System.out.println("            iiiiiindeeeeeeeeeeeeeeeeeeeexxxxxxxxxxxxxxxxxxxx");
        return "index";
    }

    @RequestMapping(value="/user")
    public String login() {
        System.out.println("           LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOGIN");
        return "login";
    }




    @RequestMapping("/witaj")
    public String przyklad(Model model) {
        model.addAttribute("powitanie", "Mile powitanie? NIEEEEEEE");
        return "witaj";
    }



//    @Autowired
//    private Inquiry inquiry;        // chcemy miec obiekt Inquiry i miec go dostepnego pod nazwa inquiry
//
//    @RequestMapping("/metoda")
//    public static String metoda(){
//        return "glowny";
//    }
//
//    // metoda ustawia atrybut, ktory bedzie dostepny z poziomu widoku:  ${message}
//    @RequestMapping("/przyklad/model")
//    public String przykladModelu(Model model){
//        model.addAttribute("message", "To jest super inormacja przekazana z widoku");
//        return "glowny";
//    }

//    @RequestMapping("/main")
//    public static void main(String[] args){
//        //SpringApplication.run(BaseController.class, args);
//     //   metoda();
//    }
}
