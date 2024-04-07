import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(new double[][]{{1,2,3,4}, {5,6}, {7,8}, {9}});
        System.out.println(matrix.toString());
        System.out.println();
        Matrix m = new Matrix(new double[][]{{5,10}, {1,0,5,2}, {3,4}, {1,2,3}});
        System.out.println(m.toString());
        matrix.add(m);
        System.out.println();
        System.out.println(matrix.toString());

        Matrix a = new Matrix(new double[][]{{1,1,1}, {2,2,2}, {3,3,3}});
        Matrix b = new Matrix(new double[][]{{1,1,1}, {2,2,2}, {3,3,3}});
        Matrix res = a.dot(b);
        System.out.println(res.toString());
        System.out.println();

        Matrix eye = Matrix.eye(5);
        System.out.println(eye.toString());

        Matrix c = new Matrix(new double[][]{{1,2,3}, {4,56,3}, {8,6,3}});
        System.out.println(c.determinant());
    }
}