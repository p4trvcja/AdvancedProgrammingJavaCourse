import java.util.Arrays;
import java.util.Random;

public class Matrix {
    private double[] data;
    private int rows;
    private int cols;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows * cols];
    }

    Matrix(double[][] d) {
        this.rows = d.length;
        this.cols = Arrays.stream(d).mapToInt(row -> row.length).max().orElse(0);

        this.data = new double[rows * cols];

        for(int row = 0; row < this.rows; row++) {
            for(int col = 0; col < this.cols; col++) {
                if (col >= d[row].length)
                    this.data[row * cols + col] = 0;
                else
                    this.data[row * cols + col] = d[row][col];
            }
        }
    }
    double[][] asArray() {
        double[][] res = new double[rows][cols];
        for(int row = 0; row < rows; row++)
            for(int col = 0; col < cols; col++)
                res[row][col] = this.data[row*cols + col];

        return res;
    }

    public double get(int r, int c) {
        if(r < 0 || r >= rows || c < 0 || c >= cols)
            throw new IndexOutOfBoundsException("Invalid size of a matrix");
        return this.data[r*cols + c];
    }
    public void set(int r, int c, double value) {
        if(r < 0 || r >= rows || c < 0 || c >= cols)
            throw new IndexOutOfBoundsException("Invalid size of a matrix");
        this.data[r*cols + c] = value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i=0; i < rows; i++) {
            sb.append("[");
            for(int j=0; j < cols; j++) {
                sb.append(data[i * cols + j]);
                if(j+1 < cols) sb.append(", ");
            }
            sb.append("]");
            if(i+1 < rows) sb.append(", "); //sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    void reshape(int newRows,int newCols){
        if(rows*cols != newRows*newCols)
            throw new IndexOutOfBoundsException(String.format("%d x %d matrix can't be reshaped to %d x %d",rows,cols,newRows,newCols));

        this.rows = newRows;
        this.cols = newCols;
    }

    int[] shape() {
        return new int[]{this.rows, this.cols};
    }

    Matrix add(Matrix m) {
        if(!Arrays.toString(m.shape()).equals(Arrays.toString(this.shape())))
            throw new RuntimeException("Wrong size of an array");

        Matrix result = new Matrix(rows, cols);

        for(int i=0; i < rows*cols; i++)
            result.data[i] = this.data[i] + m.data[i];

        return result;
    }

    Matrix sub(Matrix m) {
        if(!Arrays.toString(m.shape()).equals(Arrays.toString(this.shape())))
            throw new RuntimeException("Wrong size of an array");

        Matrix result = new Matrix(rows, cols);

        for(int i=0; i < rows*cols; i++)
            result.data[i] = this.data[i] - m.data[i];

        return result;
    }

    Matrix mul(Matrix m) {
        if(!Arrays.toString(m.shape()).equals(Arrays.toString(this.shape())))
            throw new RuntimeException("Wrong size of an array");

        Matrix result = new Matrix(rows, cols);

        for(int i=0; i < rows*cols; i++)
            result.data[i] = this.data[i] * m.data[i];

        return result;
    }

    Matrix div(Matrix m) {
        if(!Arrays.toString(m.shape()).equals(Arrays.toString(this.shape())))
            throw new RuntimeException("Wrong size of an array");

        Matrix result = new Matrix(rows, cols);

        for(int i=0; i < rows*cols; i++)
            result.data[i] = this.data[i] / m.data[i];

        return result;
    }

    Matrix add(double w){
        Matrix result = new Matrix(rows, cols);

        for(int i=0; i < rows*cols; i++)
            result.data[i] = this.data[i] + w;

        return result;
    }
    Matrix sub(double w){
        Matrix result = new Matrix(rows, cols);
        for(int i=0; i < rows*cols; i++)
            result.data[i] = this.data[i] - w;

        return result;
    }
    Matrix mul(double w){
        Matrix result = new Matrix(rows, cols);

        for(int i=0; i < rows*cols; i++)
            result.data[i] = this.data[i] * w;

        return result;
    }
    Matrix div(double w){
        Matrix result = new Matrix(rows, cols);

        for(int i=0; i < rows*cols; i++)
            result.data[i] = this.data[i] / w;

        return result;
    }

    Matrix dot(Matrix m) {
        if(this.cols != m.rows)
            throw new IndexOutOfBoundsException("Cannot multiply this matrices");

        Matrix matrix = new Matrix(this.rows, m.cols);

        for(int row = 0; row < matrix.rows; row++) {
            for(int col = 0; col < matrix.cols; col++) {
                for(int i = 0; i < m.rows; i++)
                    matrix.data[row * matrix.cols + col] += data[row * this.cols + i] * m.data[i * m.cols + col];
            }
        }
        return matrix;
    }

    double frobenius() {
        return Math.sqrt(Arrays.stream(data).map(x -> x*x).sum());
    }

    public static Matrix random(int rows, int cols){
        Matrix m = new Matrix(rows,cols);
        Random r = new Random();

        for(int i = 0; i < rows*cols; i++)
            m.data[i] = r.nextDouble();

        return m;
    }

    public static Matrix eye(int n){
        Matrix m = new Matrix(n,n);
        for(int i=0; i < n; i++)
            m.data[i*m.cols + i] = 1;
        return m;
    }

    public double determinant() {
        if (rows != cols) {
            throw new IllegalStateException("Matrix must be square");
        }
        if (rows == 1) {
            return data[0];
        }
        if (rows == 2) {
            return data[0] * data[3] - data[1] * data[2];
        }
        double determinant = 0.0;
        for (int j = 0; j < cols; j++) {
            determinant += data[j] * cofactor(0, j);
        }
        return determinant;
    }
    private Matrix minor(int row, int col) {
        Matrix minor = new Matrix(rows - 1, cols - 1);
        int m = 0, n = 0;
        for (int i = 0; i < rows; i++) {
            if (i == row) {
                continue;
            }
            for (int j = 0; j < cols; j++) {
                if (j == col) {
                    continue;
                }
                minor.data[m * (cols - 1) + n] = data[i * cols + j];
                n++;
            }
            n = 0;
            m++;
        }
        return minor;
    }
    private double cofactor(int row, int col) {
        return Math.pow(-1, row + col) * minor(row, col).determinant();
    }

    public Matrix inverse() {
        if (rows != cols)
            throw new IllegalStateException("Matrix must be square to inverse");
        double det = determinant();
        if (det == 0.0) {
            throw new ArithmeticException("Matrix is singular, cannot compute its inverse");
        }
        Matrix inverse = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                inverse.data[j * rows + i] = cofactor(i, j) / det;
            }
        }
        return inverse;
    }
}
