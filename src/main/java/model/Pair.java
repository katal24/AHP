package model;

/**
 * Created by dawid on 23.04.16.
 * Klara reprezentuje jedną parę rzeczy, które nalezy porównać wewnątrz danej cechy, np. benzyna i ropa.
 */
public class Pair {
    private String name;
    private String s1,s2;
    private int x,y;
    boolean first;
    int value;

    public Pair(String name, String s1, String s2, int x, int y, int value) {
        this.name = name;
        this.s1 = s1;
        this.s2 = s2;
        this.x = x;
        this.y = y;
        if(x<y) first = true;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + name + ":: " + s1 +"," + s2 + ":" + x + "," + y + "::" + value + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (first != pair.first) return false;
        if (value != pair.value) return false;
        if (name != null ? !name.equals(pair.name) : pair.name != null) return false;
        if (s1 != null ? !s1.equals(pair.s1) : pair.s1 != null) return false;
        return !(s2 != null ? !s2.equals(pair.s2) : pair.s2 != null);

    }

    @Override
    public int hashCode() {
        int result = s1 != null ? s1.hashCode() : 0;
        result = 31 * result + (s2 != null ? s2.hashCode() : 0);
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirst(boolean first) {
        this.first = first;
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
