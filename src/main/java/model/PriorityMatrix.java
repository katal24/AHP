package model;

/**
 * Created by dawid on 11.04.16.
 */
public class PriorityMatrix {

    /**
     * Macierz pokazujaca zaleznosci w ramach jednej cechy
     */
    double[][] matrix;
    double factor;
    double[] priority;

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double getFactor() {
        return factor;
    }

    public void setFactor(double factor) {
        this.factor = factor;
    }

    public double[] getPriority() {
        return priority;
    }

    public void setPriority(double[] priority) {
        this.priority = priority;
    }
}
