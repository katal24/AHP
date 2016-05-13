package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dawid on 12.04.16.
 */
public class Criteria {

    // Lista zawierajaca kryteria wyboru
    private List<String> criteria; // = new LinkedList<String>();

    public void addSth(String sth){
        criteria.add(sth);
    }

    public Criteria(List<String> list){
        criteria = new ArrayList<String>();
        for(String s : list){
            criteria.add(s);
        }
    }

    public Criteria(String... args){
        criteria = new ArrayList<String>();
        for(String s : args){
            criteria.add(s);
        }
    }

    @Override
    public String toString() {
        String wiersz = new String("");
        for(String s : criteria){
            wiersz += s.toString() + "\t\t";
        }
        return wiersz;
    }

    public List<String> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<String> criteria) {
        this.criteria = criteria;
    }
}
