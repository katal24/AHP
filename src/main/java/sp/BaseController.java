package sp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dawid on 02.04.16.
 */
//

//@RequestMapping("/")
//@EnableAutoConfiguration
@Controller
public class BaseController {


    @RequestMapping(value="/")
    public String index() {
        return "index";
    }


    @RequestMapping("/hello")
    public String przykladModelu(Model model) {
        model.addAttribute("message", "DAWID JEST DZIWNY XDD");
        return "hello";
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
