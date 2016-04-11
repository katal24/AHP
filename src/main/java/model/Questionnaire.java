package model;

import java.util.List;

/**
 * Created by dawid on 11.04.16.
 */
public class Questionnaire {

    private int id;
    private String type, owner, name, problem, properties, summary;

    /**
    *Lista wariantów, z których użytkownik chce wybrać najlepszy
     */
    private List<String> items;

    /**
    *Lista cech, które są dla użytkownika ważne przy wyborze
     */
    private List<String> criteria;

    /**
     * Macierz z wartosciami ważności cech (kryteriów wyboru)
     */
    private double[][] mainMatrix;

    /**
    * Wektor, w ktorym zapisane sa proprytety cech
     */
    private double[] mainPriority;

    /**
    *Wspolczynnik niespojnosci
     */
    private double mainFactor;

    private int propertiesNumber;




    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<String> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<String> criteria) {
        this.criteria = criteria;
    }

    public double[][] getMainMatrix() {
        return mainMatrix;
    }

    public void setMainMatrix(double[][] mainMatrix) {
        this.mainMatrix = mainMatrix;
    }

    public double[] getMainPriority() {
        return mainPriority;
    }

    public void setMainPriority(double[] mainPriority) {
        this.mainPriority = mainPriority;
    }

    public double getMainFactor() {
        return mainFactor;
    }

    public void setMainFactor(double mainFactor) {
        this.mainFactor = mainFactor;
    }

    public int getPropertiesNumber() {
        return propertiesNumber;
    }

    public void setPropertiesNumber(int propertiesNumber) {
        this.propertiesNumber = propertiesNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
