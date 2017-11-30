/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floyd.warshall;

/**
 *
 * @author void
 */
public class FloydWarshallAlgorithm {
    //private static final int MAX = 8;
    
    private double[][][] res;
    private double[][][] sequence;
    
    public FloydWarshallAlgorithm(int value, String[] vertex, Double[][] edges) {
        
        res = new double[value + 1][value][value];
        sequence = new double[value + 1][value][value];
        
        for (int i = 0; i < value; i++) {
            for (int j = 0; j < value; j++) {
                res[0][i][j] = edges[i][j];
                
                if(i == j)
                    sequence[0][i][j] = 0;
                else
                    sequence[0][i][j] = j + 1;
            }
        }

        int x = 1;

        for (int k = 0; k < value; k++) {
            for (int i = 0; i < value; i++) {
                for (int j = 0; j < value; j++) {
                    
                    sequence[x][i][j] = sequence[x - 1][i][j];

                    if (edges[i][j] > edges[i][k] + edges[k][j]) {
                        edges[i][j] = edges[i][k] + edges[k][j];
                        sequence[x][i][j] = x;
                    }
          
                    res[x][i][j] = edges[i][j];
                }
            }

            x++;
        }
    }
    
    public double[][][] getPath()
    {
        return res;
    }
    
    public double[][][] getSequence()
    {
        return sequence;
    }
}
