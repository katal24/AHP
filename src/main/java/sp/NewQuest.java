package sp;

import java.util.ArrayList;

/**
 * Created by dawid on 02.05.16.
 */
public class NewQuest {

    private String surveyName = "ala";
    private ArrayList<Item> categories;
    private ArrayList<Item> variants;

    private ArrayList<String> categoriesList;
    private ArrayList<String> variantsList;

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
