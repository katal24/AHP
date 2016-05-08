package sp;

/**
 * Created by dawid on 08.05.16.
 */
public class Result implements Comparable{
    String variant;
    double result;

    public Result(String variant, double result) {
        this.variant = variant;
        this.result = (double)Math.round(result * 100 *100d) / 100d;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result1 = (Result) o;

        return Double.compare(result1.result, result) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(result);
        return (int) (temp ^ (temp >>> 32));
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int compareTo(Object o) {
        Result result1 = (Result) o;
        if(result1.result < this.result){
            return -1;
        } else if(result1.result > this.result){
            return 1;
        } else{
            return 0;
        }
    }
}
