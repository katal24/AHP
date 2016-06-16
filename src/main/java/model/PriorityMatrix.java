package model;


import Jama.Matrix;

import java.util.*;

/**
 * Created by dawid on 11.04.16.
 */
public class PriorityMatrix {

    String name;
    int size;
    Matrix matrix;
    double factor;
    String[] priority;
    //macierz z wartosciami
    double[][] mainMatrix;

    // Wektor, w ktorym zapisane sa proprytety cech
    private double[] mainPriority;
    Map<Pair,Double> map = new LinkedHashMap<Pair, Double>();
    //mapa porownan, ktore maja byc pokazane w suwakach
    public Map<Pair,Double> mapToFil = new LinkedHashMap<Pair, Double>();

    public LinkedList<Pair> getMapToFillAsList(){
        return new LinkedList<Pair>(mapToFil.keySet());
    }
    double temp;

    public void setValueInMap(Pair p){
        for(Map.Entry<Pair, Double> entry : map.entrySet()){
            if(entry.getKey().getS1().equals(p.getS1()) && entry.getKey().getS2().equals(p.getS2())){
                temp = (double) p.getValue();
                if(temp >= 0){
                    temp++;
                } else{
                    temp--;
                    temp = temp*(-1);
                    temp = 1/temp;
                }
                entry.setValue(temp);
            }
            else if(entry.getKey().getS1().equals(p.getS2()) && entry.getKey().getS2().equals(p.getS1())){
                temp = (double) p.getValue()*(-1);
                if(temp >= 0){
                    temp++;
                } else{
                    temp --;
                    temp = temp*(-1);
                    temp = 1/temp;
                }
                entry.setValue(temp);
            }
        }
    }


    PriorityMatrix(String name, List<String> variants){

        this.name = name;

        this.size = variants.size();
        priority = new String[size];
        for(int i=0; i<size; i++){
            priority[i] = variants.get(i);
        }
        mainMatrix = new double[size][size];

        for(int i = 0; i<this.size; i++){
            for(int j = 0; j<this.size; j++){
                if(i==j || (variants.get(i).equals(variants.get(j)))) {
                    map.put(new Pair(name, variants.get(i),variants.get(j),i,j,0), 1.0);
                }
                else{
                    if (i < j) {
                        if (!variants.get(i).equals(variants.get(j))) {
                            Pair p = new Pair(name, variants.get(i), variants.get(j), i, j, 0);
                            if(!isInMapToFIll(p)) {
                                mapToFil.put(p, 0.0);
                            }
                        }
                    }
                    map.put(new Pair(name, variants.get(i), variants.get(j), i, j, 0), 0.0);
                }
            }
        }


        for (Pair p : map.keySet()) {
            System.out.println(p + " : " + map.get(p));
        }

        System.out.println("DO SUWAKÓW");
        for (Pair p : mapToFil.keySet()) {
            System.out.println(p + " : " + mapToFil.get(p));
        }

        for(Pair p : map.keySet()){
            mainMatrix[p.getX()][p.getY()] = map.get(p);
        }

        matrix = new Matrix(mainMatrix);
    }

    public boolean isInMapToFIll(Pair p){
        for(Pair klucz : mapToFil.keySet()){
            if(klucz.getS1().equals(p.getS1()) && klucz.getS2().equals(p.getS2())){
                return true;
            }
        }
        return false;
    }

    public void fillArray(){
        for(Pair p : map.keySet()){
            mainMatrix[p.getX()][p.getY()] = map.get(p);
        }

        matrix = new Matrix(mainMatrix);
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public String[] getPriority() {
        return priority;
    }

    public void setPriority(String[] priority) {
        this.priority = priority;
    }

    public void fillMatrix() {
        double[][] mainMatrix = new double[5][5];
        mainMatrix[0] = new double[]{1, 1./3, 5, 3, 0.5};
        mainMatrix[1] = new double[]{3, 1, 5, 4, 2};
        mainMatrix[2] = new double[]{0.2, 0.2, 1, 1./3, 1./6};
        mainMatrix[3] = new double[]{1./3, 0.25, 3, 1, 2};
        mainMatrix[4] = new double[]{2, 0.5, 6, 0.5, 1};
    }

    public double countErrorFactor(){
        double[] errorFactors = matrix.eig().getRealEigenvalues();
        System.out.println(Arrays.toString(errorFactors));
        double errorFactor = -10.0;

        for(double d : errorFactors){
            if(d>errorFactor){
                errorFactor = d;
            }
        }

        return errorFactor;
    }

    public double[] countEigenVector(){

        mainPriority = new double[matrix.getArray().length];
        double[][] tempMainPriority2 = matrix.eig().getV().getArray();
        double[] tempMainPriority = new double[mainPriority.length];
        int counter = 0;

        for(double [] row : matrix.getArray()){
            double sum = 1;
            for(double element : row){
                sum *= element;
            }
            double avg = Math.pow(sum, 1.0/row.length);
            tempMainPriority[counter] = avg;
            counter++;
        }

        mainPriority = normaliseArray(tempMainPriority);

        return mainPriority;
    }

    public double sumArray(double[] array){
        double sum=0;
        for(double d : array){
            sum += d;
        }
        return sum;
    }

    public double[] normaliseArray(double[] array){
        for(int i=0; i<array.length; i++){
            if(array[i] < 0 ) array[i] = -array[i];
        }

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
}

