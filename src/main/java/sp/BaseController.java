package sp;


import com.google.gson.Gson;
import model.Questionnaire;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

//import org.codehaus.jackson.map.ObjectMapper;

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

    @RequestMapping(value = "/setSurveysData", method = RequestMethod.POST)//, headers = "content-type=application/x-www-form-urlencoded")
   @ResponseBody
    public void setSurveysData(@RequestBody String cs) throws ClassNotFoundException, SQLException, IOException {

        // cs to JSON zawierajacay dane z formularza (step 1-3)

        System.out.println("        JEEEEEEEEEEEEEEST W BASE ------------------------  " + cs);

        Gson gson = new Gson();

        // zwiera dane z formularza step 1-3
        NewQuest nq = gson.fromJson(cs, NewQuest.class);

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
