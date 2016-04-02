package sp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dawid on 02.04.16.
 */
//


//@EnableAutoConfiguration
@Controller
//@RequestMapping("/kontroler")
public class BaseController {

//    @RequestMapping("/hello")
//    public ModelAndView helloWorld() {
//
//        String message = "Hello World, Spring 3.0!";
//        System.out.println(message);
//        return new ModelAndView("hello", "message", message);
//    }

//    @Autowired
//    private Inquiry inquiry;        // chcemy miec obiekt Inquiry i miec go dostepnego pod nazwa inquiry
//
//    @RequestMapping("/metoda")
//    public static String metoda(){
//        return "glowny";
//    }
//
    // metoda ustawia atrybut, ktory bedzie dostepny z poziomu widoku:  ${message}
    @RequestMapping("/hello")
    public String przykladModelu(Model model){
        model.addAttribute("message", "To jest super inormacja przekazana z widoku");
        return "hello";
    }

    @RequestMapping("/witaj")
    public String powitaj(Model model){
        model.addAttribute("powitanie", "Ale dzis sloneczny dzien!");
        return "witaj";
    }

//    @RequestMapping("/main")
//    public static void main(String[] args){
//        //SpringApplication.run(BaseController.class, args);
//     //   metoda();
//    }
}
