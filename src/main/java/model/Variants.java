package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dawid on 12.04.16.
 */
public class Variants {

    List<Criteria> variants = new LinkedList<Criteria>();

    public void addVariant(Criteria criteria){
        variants.add(criteria);
       // return this;
    }

    @Override
    public String toString() {
        String calosc = new String("");
        for(Criteria c : variants){
            calosc += c.toString() + "\n";
            //System.out.println("Raz");
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
