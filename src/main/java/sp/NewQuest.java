package sp;

/**
 * Created by dawid on 02.05.16.
 */
public class NewQuest {

    private String surveyName;
   // private String password;
   // private String name;


    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    //@ModelAttribute("cs")
    public NewQuest getNewQuest(){
        return new NewQuest();
    }
}
