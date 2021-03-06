package sp;

import model.SurveysEntity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dawid on 02.05.16.
 */
public class NewQuest {

    private String surveyName = "ala";
    private ArrayList<Item> categories;
    private ArrayList<Item> variants;

    private String access = "public";
    private ArrayList<String> categoriesList;
    private ArrayList<String> variantsList;
    private String check1;


    public void setLists(){
        categoriesList = new ArrayList<String>();
        variantsList = new ArrayList<String>();

        for(Item i : categories){
            categoriesList.add(i.getName());
        }

        for(Item i : variants){
            variantsList.add(i.getName());
        }
    }

    public NewQuest(){}

    public NewQuest(SurveysEntity survey) {
        this.surveyName = survey.getName();
        this.access = survey.getType();

        categoriesList = makeListFromString(survey.getCategories());
        variantsList = makeListFromString(survey.getVariants());
    }

    public ArrayList<String> makeListFromString(String categoriesFromBase){
        String[] arrayString = categoriesFromBase.split(" ; ");
        return new ArrayList<String>(Arrays.asList(arrayString));

    }


    public String getCheck() {
        return check1;
    }

    public void setCheck(String check) {
        this.check1 = check;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public ArrayList<String> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<String> categoriesList) {
        categoriesList = categoriesList;
    }

    public ArrayList<String> getVariantsList() {
        return variantsList;
    }

    public void setVariantsList(ArrayList<String> variantsList) {
        variantsList = variantsList;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public ArrayList<Item> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Item> categories) {
        this.categories = categories;
    }

    public ArrayList<Item> getVariants() {
        return variants;
    }

    public void setVariants(ArrayList<Item> variants) {
        this.variants = variants;
    }
}

class Item {
    String id;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
