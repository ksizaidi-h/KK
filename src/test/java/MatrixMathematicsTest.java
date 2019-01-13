import exception.NoSquareException;
import model.Matrix;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import service.MatrixMathematics;

import static org.junit.Assert.*;

public class MatrixMathematicsTest {

    @Test
    public void determinant() throws Exception {

        final int NROW = 3, NCOL = 3;
        Matrix testMatrix = new Matrix(NROW,NCOL);

        int i,j, value = 1;
        for (i=0;i<NCOL;i++){
            for(j=0;j<NROW;j++){
                testMatrix.setValueAt(j,i,value);
                value ++;
            }
        }

        assertEquals((double) 0,MatrixMathematics.determinant(testMatrix),0);
    }
    @Test(expected = NoSquareException.class)
    public  void determinant_noSquareException() throws NoSquareException{

        final int NROW = 3, NCOL = 2;
        Matrix testMatrix = new Matrix(NROW,NCOL);

        testMatrix.setValueAt(0,0,4);
        testMatrix.setValueAt(0,1,3);
        testMatrix.setValueAt(1,0,3);
        testMatrix.setValueAt(1,1,2);
        testMatrix.setValueAt(2,0,3);
        testMatrix.setValueAt(2,1,2);

        MatrixMathematics.determinant(testMatrix);
    }




    @Test
    public void cofactor() throws Exception {

        final int NROW = 3, NCOL = 3;
        Matrix testMatrix = new Matrix(NROW,NCOL);

        int i,j, value = 1;
        for (i=0;i<NROW;i++){
            for(j=0;j<NCOL;j++){
                testMatrix.setValueAt(i,j,value);
                value ++;
            }
        }

        Matrix matrix_cofactor = new Matrix(NCOL,NROW);

        matrix_cofactor.setValueAt(0,0,-3);
        matrix_cofactor.setValueAt(0,1,6);
        matrix_cofactor.setValueAt(0,2,-3);

        matrix_cofactor.setValueAt(1,0,6);
        matrix_cofactor.setValueAt(1,1,-12);
        matrix_cofactor.setValueAt(1,2,6);

        matrix_cofactor.setValueAt(2,0,-3);
        matrix_cofactor.setValueAt(2,1,6);
        matrix_cofactor.setValueAt(2,2,-3);

        assertEquals(matrix_cofactor.getValues(),MatrixMathematics.cofactor(testMatrix).getValues());

    }

    @Test(expected = NoSquareException.class)
    public  void cofactor_noSquareException() throws NoSquareException{
        final int NROW = 3, NCOL = 2;
        Matrix testMatrix = new Matrix(NROW,NCOL);

        testMatrix.setValueAt(0,0,4);
        testMatrix.setValueAt(0,1,3);
        testMatrix.setValueAt(1,0,3);
        testMatrix.setValueAt(1,1,2);
        testMatrix.setValueAt(2,0,3);
        testMatrix.setValueAt(2,1,2);

        MatrixMathematics.cofactor(testMatrix);
    }
}