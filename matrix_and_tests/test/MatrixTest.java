import org.junit.Assert;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class MatrixTest {

    @org.junit.Test
    public void testAsArray() {
        double[][] arr = new double[][]{{1,2,0,0}, {4,6,5,0}, {7,0,0,0}, {8,9,10,11}};
        Matrix matrix = new Matrix(arr);
        Assert.assertArrayEquals(arr, matrix.asArray());
    }

    @org.junit.Test
    public void testConstructorWithRowsAndCols() {
        int min = 10, max = 100;
        int rows = (int)(Math.random() * (max - min + 1)) + min;
        int cols = (int)(Math.random() * (max - min + 1)) + min;

        Matrix matrix = new Matrix(rows, cols);
        Assert.assertArrayEquals(new int[]{rows, cols}, matrix.shape());
    }

    @org.junit.Test
    public void testConstructorWith2DArray() {
        Random r = new Random();
        int min = 10, max = 150;
        int rows = (int)(Math.random() * (max - min + 1)) + min;
        int cols = (int)(Math.random() * (max - min + 1)) + min;
        double[][] matrix = new double[rows][cols];

        for(int i=0; i < rows; i++)
            for(int j=0; j < cols; j++)
                matrix[i][j] = r.nextDouble();

        Assert.assertArrayEquals(matrix, new Matrix(matrix).asArray());
    }

    @org.junit.Test
    public void testGetter() {
        Matrix matrix = new Matrix(new double[][]{{1,2,8}, {4}, {6,8}});

        Assert.assertEquals(8, matrix.get(2, 1), 0.01);
        Assert.assertEquals(1, matrix.get(0, 0), 0.01);
        Assert.assertEquals(0, matrix.get(1, 1), 0.01);
    }

    @org.junit.Test
    public void testGetterThrowsException() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5}});
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
            matrix.get(1, 10);
        });
    }

    @org.junit.Test
    public void testSetter() {
        Matrix matrix = new Matrix(new double[][]{{1,2}, {4,5}});
        matrix.set(0, 1, 5);
        matrix.set(1, 0, 7);
        Assert.assertEquals(5, matrix.get(0, 1), 0.01);
        Assert.assertEquals(7, matrix.get(1, 0), 0.01);
    }

    @org.junit.Test
    public void testSetterThrowsException() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5}});
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
            matrix.set(1, 10, 5);
        });
    }

    @org.junit.Test
    public void testToString() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5},{0},{86,9},{15,2}});
        int comma = 0, parenthesis = 0;
        String str = matrix.toString();
        for(int i=0; i < str.length(); i++) {
            if(str.charAt(i)==',') comma++;
            else if(str.charAt(i)=='[') parenthesis++;
            else if(str.charAt(i)==']') parenthesis--;
        }
        Assert.assertEquals(14, comma);
        Assert.assertEquals(0, parenthesis);
    }

    @org.junit.Test
    public void testReshape() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5}});
        matrix.reshape(6,1);
        double[][] array = new double[][]{{1},{2},{3},{4},{5},{0}};

        Assert.assertArrayEquals(new int[]{6,1}, matrix.shape());
        for(int i=0; i < 6; i++)
            Assert.assertArrayEquals(array[i], matrix.asArray()[i], 0.01);
    }

    @org.junit.Test
    public void testReshapeThrowsException() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5}});
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
            matrix.reshape(3, 3);
        });
    }

    @org.junit.Test
    public void testShape() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5}});
        assertArrayEquals(matrix.shape(), new int[]{2,3});
    }

    @org.junit.Test
    public void testAddMatrix() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5}});
        Matrix other = new Matrix(new double[][]{{1,1,1},{1,1,1}});
        Matrix result = matrix.add(other);

        double[][] r = new double[][]{{2,3,4}, {5,6, 1}};
        Assert.assertArrayEquals(r, result.asArray());
    }

    @org.junit.Test
    public void testSubMatrix() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5}});
        Matrix other = new Matrix(new double[][]{{1,1,1},{1,1,1}});
        Matrix result = matrix.sub(other);

        double[][] r = new double[][]{{0,1,2}, {3,4,-1}};
        Assert.assertArrayEquals(r, result.asArray());
    }

    @org.junit.Test
    public void testMulMatrix() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3},{4,5}});
        Matrix other = new Matrix(new double[][]{{4,5,6}, {2,3,4}});
        Matrix result = matrix.mul(other);

        double[][] r = new double[][]{{4,10,18}, {8,15,0}};
        Assert.assertArrayEquals(r, result.asArray());
    }

    @org.junit.Test
    public void testDivMatrix() {
        Matrix matrix = new Matrix(new double[][]{{2,32,15},{9,12,14}});
        Matrix other = new Matrix(new double[][]{{2,8,3},{3,6,2}});
        Matrix result = matrix.div(other);

        double[][] r = new double[][]{{1,4,5}, {3,2,7}};
        Assert.assertArrayEquals(r, result.asArray());
    }

    @org.junit.Test
    public void testAddScalar() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3}, {4,5}});
        double scalar = 5;
        Matrix result = matrix.add(scalar);
        Assert.assertArrayEquals(new double[][]{{6,7,8}, {9, 10, 5}}, result.asArray());
    }

    @org.junit.Test
    public void testSubScalar() {
        Matrix matrix = new Matrix(new double[][]{{12, 4, 17}, {7, 9}});
        double scalar = 4;
        Matrix result = matrix.sub(scalar);
        Assert.assertArrayEquals(new double[][]{{8, 0, 13}, {3, 5, -4}}, result.asArray());
    }

    @org.junit.Test
    public void testMulByScalar() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3}, {4,12}});
        double scalar = 2;
        Matrix result = matrix.mul(scalar);
        Assert.assertArrayEquals(new double[][]{{2,4,6},{8,24, 0}}, result.asArray());
    }

    @org.junit.Test
    public void testDivByScalar() {
        Matrix matrix = new Matrix(new double[][]{{2,4,6},{8,24}});
        double scalar = 2;
        Matrix result = matrix.div(scalar);
        Assert.assertArrayEquals(new double[][]{{1,2,3}, {4,12,0}}, result.asArray());
    }

    @org.junit.Test
    public void testDot() {
        Matrix matrix = new Matrix(new double[][]{{1,5}, {2,3}, {1,7}});
        Matrix other = new Matrix(new double[][]{{1,2, 3, 7}, {5, 2, 8, 1}});
        Matrix c = new Matrix(new double[][]{{26, 12, 43, 12}, {17, 10, 30, 17}, {36, 16, 59, 14}});
        Matrix result = matrix.dot(other);
        Assert.assertArrayEquals(c.asArray(), result.asArray());
    }

    @org.junit.Test
    public void testDotThrowsException() {
        Matrix matrix = new Matrix(new double[][]{{1,2,3}, {1,2}, {4,3,8,6}});
        Matrix other = new Matrix(new double[][]{{1,5}, {2,3}, {1,7}});
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> {
            matrix.dot(other);
        });
    }

    @org.junit.Test
    public void testFrobenius() {
        Matrix matrix = new Matrix(new double[][]{{1,5,2}, {2,3,5}, {1,7,8}});
        Assert.assertEquals(matrix.frobenius(), 13.4907, 0.1);
    }


    @org.junit.Test
    public void testEye() {
        Matrix matrix = Matrix.eye(4);
        Matrix res = new Matrix(new double[][]{{1,0,0,0}, {0,1,0,0}, {0,0,1,0},{0,0,0,1}});
        Assert.assertArrayEquals(res.asArray(), matrix.asArray());
    }

}