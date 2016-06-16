package model;

import Jama.Matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dawid on 12.04.16.
 */
public class Variants {

    // Lista zawierajaca wszystkie informacje o kryteriach i wlasciwosciach wariantow
    List<Criteria> variants = new LinkedList<Criteria>();

    public void addVariant(Criteria criteria){
        variants.add(criteria);
    }

    Matrix m;
    String[][] arrays;
    public String[][] buildMatrix() {
        int size0 = variants.get(0).getCriteria().size();
        int size = variants.size();
        arrays = new String[size0][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size0; j++){
                arrays[j][i] = variants.get(i).getCriteria().get(j);
            }
        }

        System.out.println(Arrays.deepToString(arrays));
        return arrays;
    }


    @Override
    public String toString() {
        String calosc = new String("");
        for(Criteria c : variants){
            calosc += c.toString() + "\n";
        }
        return calosc;
    }

    public int size(){
        return variants.size();
    }
    public List<Criteria> getVariants() {
        return variants;
    }

    public void setVariants(List<Criteria> variants) {
        this.variants = variants;
    }


}
