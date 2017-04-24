package servercloudhandler;

import java.io.Serializable;

/**
 * Created by Sagar Palo on 2017-03-26.
 */
public class MatrixOperation implements Serializable{
    public double mat1[][];
    public double mat2[][];
    public double mat3[][];
    static final long serialVersionUID =0L;

    public MatrixOperation(double mat1[][],double mat2[][]){
        this.mat1 = mat1;
        this.mat2 = mat2;
        this.mat3 = new double[100][100];
    }

    public void multiply(){
        int i,j,k;
        int n = mat1.length;
        android.util.Log.d("Message",String.valueOf(n));
        for (i = 0; i < n; i++)
        {
            for (j = 0; j < n; j++)
            {
                for (k = 0; k < n; k++)
                {
                    mat3[i][j] = mat3[i][j] + mat1[i][k] * mat2[k][j];
                }
            }
        }
    }
}
