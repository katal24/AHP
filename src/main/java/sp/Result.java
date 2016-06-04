package sp;

/**
 * Created by dawid on 08.05.16.
 */
public class Result implements Comparable{
    String label;
    double value;

    public Result(String label, double value) {
        this.label = label;
        this.value = (double)Math.round(value * 100 *100d) / 100d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result value1 = (Result) o;

        return Double.compare(value1.value, value) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }

    public String getVariant() {
        return label;
    }

    public void setVariant(String label) {
        this.label = label;
    }

    public double getResult() {
        return value;
    }

    public void setResult(double value) {
        this.value = value;
    }

    public int compareTo(Object o) {
        Result value1 = (Result) o;
        if(value1.value < this.value){
            return -1;
        } else if(value1.value > this.value){
            return 1;
        } else{
            return 0;
        }
    }
}
