package model;

import org.springframework.web.bind.annotation.RequestBody;
import sp.NewQuest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dawid on 11.04.16.
 */

public class Questionnaire {

    private int id;
    private String type, owner, name, problem, properties, summary;

    //    *Lista wariantów, z których użytkownik chce wybrać najlepszy
    private Variants variants; // = new LinkedList<Criteria>();

    PriorityMatrix macierz;
    List<PriorityMatrix> matrixes;
    String[][] allProperties;

    List<String> categoriesString;
    List<String> variantsString;


    public void makeQuestionnaire(){

        categoriesString = new LinkedList<String>();
        variantsString = new LinkedList<String>();

        variants = new Variants();

        variants.addVariant(new Criteria("Nazwa", "Cena", "Bateria", "Ekran", "Pamiec", "Aparat"));
        variants.addVariant(new Criteria("A", "3100 zl", "79 h", "5.5\"FHD", "64 GB", "8 Mpx"));
        variants.addVariant(new Criteria("B", "1550 zl", "72 h", "5.1\"FHD", "16 GB", "16 Mpx"));
        variants.addVariant(new Criteria("C", "1850 zl", "46 h", "5\"FHD", "32 GB", "20 Mpx"));
        variants.addVariant(new Criteria("D", "1900 zl", "85 h", "5.2\"FHD", "16 GB", "20.2 Mpx"));

        allProperties = variants.buildMatrix();

        variantsNumber = variants.size();

        matrixes = new LinkedList<PriorityMatrix>();

        matrixes.add(new PriorityMatrix("Ogolne", "Cena", "Bateria", "Ekran", "Pamiec", "Aparat"));
        //macierz = new PriorityMatrix(5);
        //macierz = new PriorityMatrix("Cena", "Bateria", "Ekran", "Pamiec", "Aparat");
        //macierz.countEigenVector();

        for(String[] s : Arrays.copyOfRange(allProperties,1,allProperties.length)){
            System.out.println("Macierz dla: " + s[0]);
            macierz = new PriorityMatrix(s[0], Arrays.copyOfRange(s,1,s.length));
            macierz.countEigenVector();
        }

//        mainMatrix = new double[variantsNumber][variantsNumber];
//
//        mainMatrix[0] = new double[]{1, 1./3, 5, 3, 0.5};
//        mainMatrix[1] = new double[]{3, 1, 5, 4, 2};
//        mainMatrix[2] = new double[]{0.2, 0.2, 1, 1./3, 1./6};
//        mainMatrix[3] = new double[]{1./3, 0.25, 3, 1, 2};
//        mainMatrix[4] = new double[]{2, 0.5, 6, 0.5, 1};
//
//        Matrix A = new Matrix(mainMatrix);
//
//        System.out.println("");
//        double[] tempMainPriority = A.eig().getV().transpose().getArray()[0];
//
//        mainPriority = normaliseArray(tempMainPriority);
//        System.out.println("Wektor wag: " + Arrays.toString(mainPriority));
//        double sumka = sumArray(mainPriority);
//        System.out.println(sumka);
//        print2Vectors(variants.getVariants().get(0).getCriteria(), mainPriority);
//


//        System.out.println("A: " + Arrays.toString(A.eig().getRealEigenvalues()));
//        System.out.println("A: " + Arrays.deepToString(A.eig().getD().getArray()));
//        System.out.println("A: " + Arrays.toString(A.eig().getImagEigenvalues()));
//
//
//        mainMatrix[0] = new double[]{1, 3, 0.2, 1./3, 2};
//        mainMatrix[1] = new double[]{1./3, 1, 0.2, 0.25, 0.5};
//        mainMatrix[2] = new double[]{5, 5, 1, 3, 6};
//        mainMatrix[3] = new double[]{3, 4, 1./3, 1, 1./2};
//        mainMatrix[4] = new double[]{0.5, 2, 1./6, 2, 1};
//
//
//        Matrix B = new Matrix(mainMatrix);
//        System.out.println("B: " + Arrays.deepToString(B.eig().getV().getArray()));
//        System.out.println("B: " + Arrays.toString(B.eig().getRealEigenvalues()));


    }

//    *Lista cech, które są dla użytkownika ważne przy wyborze
    //  private Criteria criteria;

    //Macierz z wartosciami ważności cech (kryteriów wyboru)
    private double[][] mainMatrix;

    //    * Wektor, w ktorym zapisane sa proprytety cech
    private double[] mainPriority;

    //    *Wspolczynnik niespojnosci
    private double mainFactor;

    private int propertiesNumber;
    private int variantsNumber;

public double sumArray(double[] array){
    double sum=0;
    for(double d : array){
        sum += d;
    }
    return sum;
}

    public double[] normaliseArray(double[] array){
        double sum = sumArray(array);
        for(int i=0; i<array.length; i++){
            array[i] = (double) Math.round(Math.abs(array[i])/sum * 1000d) / 1000d;
        }
        return array;
    }

    public void print2Vectors(List<String> properties, double[] values){
        System.out.println("Cecha  Wartosc" );
        for(int i=0; i<values.length; i++){
            System.out.println(properties.get(i+1) + "   " + values[i]);
        }
    }

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    public int getVariantsNumber() {
        return variantsNumber;
    }

    public void setVariantsNumber(int variantsNumber) {
        this.variantsNumber = variantsNumber;
    }

    public int getvariantsNumber() {
        return variantsNumber;
    }

    public void setvariantsNumber(int variantsNumber) {
        this.variantsNumber = variantsNumber;
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

    public List<String> getCategoriesString() {
        return categoriesString;
    }

    public void setCategoriesString(List<String> categoriesString) {
        this.categoriesString = categoriesString;
    }

    public List<String> getVariantsString() {
        return variantsString;
    }

    public void setVariantsString(List<String> variantsString) {
        this.variantsString = variantsString;
    }

    public static void setSurveysData(@RequestBody NewQuest cs) {
        System.out.println("        JEEEEEEEEEEEEEEEEEEEEESTEM W JAAAAAAAAAAAAAAAAAAAAAAAAAVIEEEEEEEEEEEEEEEEEEEE");
    }
}
