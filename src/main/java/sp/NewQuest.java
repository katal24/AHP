package sp;

/**
 * Created by dawid on 02.05.16.
 */
public class NewQuest {

    private String surveyName;
  //  private String[] categories;
   // private String[] variants;


    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public NewQuest(String surveyName) {
        this.surveyName = surveyName;
    }

    public NewQuest(){

    }

    @Override
    public String toString() {
        return "NewQuest{" +
                "surveyName='" + surveyName + '\'' +
                '}';
    }

    //
//    public String[] getCategories() {
//        return categories;
//    }
//
//    public void setCategories(String[] categories) {
//        this.categories = categories;
//    }
//
//    public String[] getVariants() {
//        return variants;
//    }
//
//    public void setVariants(String[] variants) {
//        this.variants = variants;
//    }

//    //@ModelAttribute("cs")
//    public NewQuest getNewQuest(){
//        return new NewQuest();
//    }
}
