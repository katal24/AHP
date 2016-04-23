package model;

/**
 * Created by dawid on 23.04.16.
 * Klara reprezentuje jedną parę rzeczy, które nalezy porównać wewnątrz danej cechy, np. benzyna i ropa.
 */
public class Pair {
    private String s1,s2;
    private int x,y;
    boolean first;

    public Pair(String s1, String s2, int x, int y) {
        this.s1 = s1;
        this.s2 = s2;
        this.x = x;
        this.y = y;
        if(x<y) first = true;
    }

    @Override
    public String toString() {
        return "(" + s1 +"," + s2 + ":" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if(s1!=null && s2!=null && s1.equals(s2)) return true;
        else return false;

    }

    @Override
    public int hashCode() {
        int result = s1 != null ? s1.hashCode() : 0;
        result = 31 * result + (s2 != null ? s2.hashCode() : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isFirst() {
        return first;
    }
}
