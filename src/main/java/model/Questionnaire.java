package model;

import org.springframework.web.bind.annotation.RequestBody;
import sp.BaseController;
import sp.NewQuest;

import java.util.*;

/**
 * Created by dawid on 11.04.16.
 */

public class Questionnaire {

    LinkedList<Pair> listToScroll;
    LinkedList<double[]> eigenVectors;

    public Questionnaire(NewQuest nq){
        categoriesList = nq.getCategoriesList();
        variantsList = nq.getVariantsList();


    }

    public Questionnaire(){}

    private int id;
    private String type, owner, name, problem, properties, summary;

    //    *Lista wariantów, z których użytkownik chce wybrać najlepszy
    private Variants variants; // = new LinkedList<Criteria>();

    PriorityMatrix macierz;
    List<PriorityMatrix> matrixes;
    String[][] allProperties;

    List<String> categoriesList;
    List<String> variantsList;


    public void printAllMaps(){
        for(PriorityMatrix matrix : matrixes){
            System.out.println(matrix.map);
        }
    }
    public void makeQuestionnaire(){

        //categoriesString = new LinkedList<String>();
        //variantsString = new LinkedList<String>();

        variants = new Variants();

        List<String> nullVawiant = BaseController.nq.getCategoriesList();
        nullVawiant.add(0,"Nazwa");

        variants.addVariant(new Criteria(nullVawiant));
       // variants.addVariant(new Criteria("Nazwa", "Cena", "Bateria", "Ekran", "Pamiec", "Aparat"));
        for(int i=0; i<BaseController.nq.getVariantsList().size(); i++){
          //  System.out.println(BaseController.ncq.getCompletedVariant(i));
            ArrayList<String> oneVariant = new ArrayList<String>(BaseController.ncq.getCompletedVariant(i));
            oneVariant.add(0, BaseController.nq.getVariantsList().get(i));
            variants.addVariant(new Criteria(oneVariant));
        }
//        variants.addVariant(new Criteria("A", "3100 zl", "79 h", "5.5\"FHD", "64 GB", "8 Mpx"));
//        variants.addVariant(new Criteria("B", "1550 zl", "72 h", "5.1\"FHD", "16 GB", "16 Mpx"));
//        variants.addVariant(new Criteria("C", "1850 zl", "46 h", "5\"FHD", "32 GB", "20 Mpx"));
//        variants.addVariant(new Criteria("D", "1900 zl", "85 h", "5.2\"FHD", "16 GB", "20.2 Mpx"));

        allProperties = variants.buildMatrix();

        variantsNumber = variants.size();

        matrixes = new LinkedList<PriorityMatrix>();

        matrixes.add(new PriorityMatrix("PRIORYTETY", BaseController.nq.getCategoriesList().subList(1,BaseController.nq.getCategoriesList().size())));
        //macierz = new PriorityMatrix(5);
        //macierz = new PriorityMatrix("Cena", "Bateria", "Ekran", "Pamiec", "Aparat");
        //macierz.countEigenVector();


        // wypisuje kazdy z kazdym i liczy wektor wlasny
        for(String[] s : Arrays.copyOfRange(allProperties,1,allProperties.length)){
            System.out.println("Macierz dla: " + s[0]);
            macierz = new PriorityMatrix(s[0], Arrays.asList(Arrays.copyOfRange(s,1,s.length)));
            matrixes.add(macierz);
            macierz.countEigenVector();
        }

        System.out.println("POROWNANIA DO SUWAKOW WSZYSTKIE: ");
        for(PriorityMatrix pm : matrixes){
            System.out.println(pm.mapToFil);
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

    public void setValueInMaps(){
        eigenVectors = new LinkedList<double[]>();
        for(PriorityMatrix matrix : matrixes){
            for(Pair pair : listToScroll){
                matrix.setValueInMap(pair);
                matrix.convertValueInMap();
            }
            matrix.fillArray();
            System.out.println(Arrays.deepToString(matrix.mainMatrix));
            double[] temp = matrix.countEigenVector();
            eigenVectors.add(temp);
        }
    }

    public double[] countResult(){
        double[] result = new double[eigenVectors.getFirst().length];

        for(int i=1; i<eigenVectors.size(); i++){

            System.out.println("Przed mnozeniem: " + Arrays.toString(eigenVectors.get(i)));
            for(int k=0; k<eigenVectors.getFirst().length; k++){
                eigenVectors.get(i)[k] = eigenVectors.get(i)[k] * eigenVectors.getFirst()[i-1];
            }

//            for(double element : eigenVectors.get(i)){
//                element = element * eigenVectors.get(0)[i-1];
//
//            }
            System.out.println("Po mnozeniu: " + Arrays.toString(eigenVectors.get(i)));

        }

        for(double[] v : eigenVectors.subList(1,eigenVectors.size())){
            result = addVectors(result,v);
        }

        return result;
    }

    public double[] addVectors(double[] v1, double[] v2){
        double[] result = new double[v1.length];

        for(int i=0; i< v1.length; i++){
            result[i] = v1[i] + v2[i];
        }

        return result;
    }

    public LinkedList<Pair> getListToScrollFromMatrixes(){
        listToScroll= new LinkedList<Pair>();

        for(PriorityMatrix matrix : matrixes){
            listToScroll.addAll(matrix.getMapToFillAsList());
        }

        return listToScroll;
    }


    public LinkedList<Pair> getListToScroll() {
        return listToScroll;
    }

    public void setListToScroll(LinkedList<Pair> listToScroll) {
        this.listToScroll = listToScroll;
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

    public List<String> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public List<String> getVariantsList() {
        return variantsList;
    }

    public void setVariantsList(List<String> variantsList) {
        this.variantsList = variantsList;
    }

    public String[][] getAllProperties() {
        return allProperties;
    }

    public void setAllProperties(String[][] allProperties) {
        this.allProperties = allProperties;
    }

    public List<PriorityMatrix> getMatrixes() {
        return matrixes;
    }

    public void setMatrixes(List<PriorityMatrix> matrixes) {
        this.matrixes = matrixes;
    }

    public PriorityMatrix getMacierz() {
        return macierz;
    }

    public void setMacierz(PriorityMatrix macierz) {
        this.macierz = macierz;
    }

    public static void setSurveysData(@RequestBody NewQuest cs) {
        System.out.println("        JEEEEEEEEEEEEEEEEEEEEESTEM W JAAAAAAAAAAAAAAAAAAAAAAAAAVIEEEEEEEEEEEEEEEEEEEE");
        System.out.println("        Nazwa ankiety: " + cs.getSurveyName());
    }
}
