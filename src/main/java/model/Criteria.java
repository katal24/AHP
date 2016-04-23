package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dawid on 12.04.16.
 */
public class Criteria {

    private List<String> criteria; // = new LinkedList<String>();

    public void recznie(){
        criteria.add("cena");
        criteria.add("bateria");
        criteria.add("ekran");
        criteria.add("pamiec");
        criteria.add("aparat");
    }

    public void addSth(String sth){
        criteria.add(sth);
    }

    public Criteria(List<String> list){
        criteria = new LinkedList<String>();

        for(String s : list){
            criteria.add(s);
        }
    }

    public Criteria(String... args){
        criteria = new LinkedList<String>();
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
}
