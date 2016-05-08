package model;


import Jama.Matrix;

import java.util.*;

/**
 * Created by dawid on 11.04.16.
 */
public class PriorityMatrix {

    /**
     * Macierz pokazujaca zaleznosci w ramach jednej cechy
     */
    String name;
    int size;
    Matrix matrix;
    double factor;
    String[] priority;
    String[] concrete;
    double[][] mainMatrix;
    Map<Pair,Double> map = new LinkedHashMap<Pair, Double>();
    public Map<Pair,Double> mapToFil = new LinkedHashMap<Pair, Double>();

    public LinkedList<Pair> getMapToFillAsList(){
        return new LinkedList<Pair>(mapToFil.keySet());
    }
    double temp;

    public void setValueInMap(Pair p){
        for(Map.Entry<Pair, Double> entry : map.entrySet()){
            if(entry.getKey().getS1().equals(p.getS1()) && entry.getKey().getS2().equals(p.getS2())){
                // moze lepiej usunac stare entry i dac nowe????
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
                // moze lepiej usunac stare entry i dac nowe????

                temp = (double) p.getValue()*(-1);
                if(temp >= 0){
                    temp++;
                } else{
                    temp --;
                    temp = temp*(-1);
                    temp = 1/temp;
                }
                entry.setValue(temp);
               // entry.setValue((double) p.getValue()*(-1));
            }
        }
    }


    public void fillMap(){

    }

    public void convertValueInMap() {

    }
    //    * Wektor, w ktorym zapisane sa proprytety cech
    private double[] mainPriority;

    //    *Wspolczynnik niespojnosci
    private double mainFactor;


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
                if(i==j) {
                    map.put(new Pair(name, variants.get(i),variants.get(j),i,j,0), 1.0);
                }
                else {
                    if(i<j){
                        mapToFil.put(new Pair(name, variants.get(i), variants.get(j), i, j,0), 0.0);
                    }
                    map.put(new Pair(name, variants.get(i), variants.get(j), i, j,0), 0.0);
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


        // teraz uzytkownik uzupelnia mape !!!



        // uzupenianie macierzy matrix musi sie robic na podstawie mapy
        for(Pair p : map.keySet()){
            mainMatrix[p.getX()][p.getY()] = map.get(p);
        }



        matrix = new Matrix(mainMatrix);

       // to powyżej jest do zmiany, ostatecznie ma wygladac tak: matrix = new Matrix(size, size);
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


    public double[] countEigenVector(){

        double[] tempMainPriority = matrix.eig().getV().transpose().getArray()[0];


        mainPriority = normaliseArray(tempMainPriority);
        System.out.println("Wektor wag: " + Arrays.toString(mainPriority));
        double sumka = sumArray(mainPriority);
        System.out.println(sumka);
        return mainPriority;
        //print2Vectors(variants.getVariants().get(0).getCriteria(), mainPriority);
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

