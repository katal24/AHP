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
    private int id;
    private String type, owner, name, problem, properties, summary;
    //    *Lista wariantów, z których użytkownik chce wybrać najlepszy
    private Variants variants; // = new LinkedList<Criteria>();
    PriorityMatrix macierz;
    List<PriorityMatrix> matrixes;
    String[][] allProperties;
    List<String> categoriesList;
    List<String> variantsList;
    double errorFactor;
    //Macierz z wartosciami ważności cech (kryteriów wyboru)
    private double[][] mainMatrix;

    //    * Wektor, w ktorym zapisane sa proprytety cech
    private double[] mainPriority;

    //    *Wspolczynnik niespojnosci
    private double mainFactor;

    private int propertiesNumber;
    private int variantsNumber;

    public Questionnaire(NewQuest nq){
        categoriesList = nq.getCategoriesList();
        variantsList = nq.getVariantsList();
    }

    public Questionnaire(){}


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

    }

    public double setValueInMaps(){
        eigenVectors = new LinkedList<double[]>();
        for(PriorityMatrix matrix : matrixes){
            for(Pair pair : listToScroll){
                matrix.setValueInMap(pair);
//                matrix.convertValueInMap(); // ta funkcja jest PUSTA !!!
            }
            matrix.fillArray();
            System.out.println(Arrays.deepToString(matrix.mainMatrix));
            double[] temp = matrix.countEigenVector();
            eigenVectors.add(temp);
        }

        //LICZY ŚREDNI WSPÓŁCZYNNIK NIESPÓJNOŚCI
        double error = 0;
        errorFactor = 0;
//        = matrixes.get(0).countErrorFactor();
        for(PriorityMatrix matrix : matrixes){
            double temp = matrix.countErrorFactor();
            //int n = BaseController.nq.getCategoriesList().size()-1;
            int n=matrix.size;
            System.out.println("MAX LAMBDA: " + temp);
            System.out.println("WYMIAR n: " + n);
//            errorFactor = (temp - n)/(n-1);
            double temp2 = (temp - n)/(n-1);

            if(temp2>errorFactor){
                errorFactor=temp2;
            }
            System.out.println("SREDNI WSPOLCZYNNIK NIESPOLNOSCI WYNOSI: " + temp2);
        }
        System.out.print("LICZE NIESPOJNOSC!");




        System.out.println("SREDNI MAKSYMALNY WSPOLCZYNNIK NIESPOLNOSCI WYNOSI: " + errorFactor);
        if(errorFactor > 0.1){
            System.out.println("NIESPÓJNOŚĆ NIESPÓJNOŚĆ NIESPÓJNOŚĆ !!! WYPEŁNIJŻE PORZĄDNIE TE ANKIETE!!!!!!!!!!!!!!!!!!");
        } else{
            System.out.println("ANKIETA SPOJNA");

        }

        return errorFactor;
    }

    public double[] countResult(){
        setValueInMaps();
        double[] result = new double[eigenVectors.get(1).length];

        for(int i=1; i<eigenVectors.size(); i++){

            System.out.println("Przed mnozeniem: " + Arrays.toString(eigenVectors.get(i)));
            for(int k=0; k<eigenVectors.get(i).length; k++){
                eigenVectors.get(i)[k] = eigenVectors.get(i)[k] * eigenVectors.getFirst()[i-1];
            }
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

        System.out.println("................................-WYPEŁNIONE TABLICE:");
        for(PriorityMatrix matrix : matrixes){
            listToScroll.addAll(matrix.getMapToFillAsList());

           // System.out.println(Arrays.deepToString(matrix.mainMatrix));
        }
//
//        for(PriorityMatrix matrix : matrixes){
//        }

        return listToScroll;
    }


    public LinkedList<Pair> getListToScroll() {
        return listToScroll;
    }

    public void setListToScroll(LinkedList<Pair> listToScroll) {
        this.listToScroll = listToScroll;
    }




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
