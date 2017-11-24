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
    
    public static double[][][] ShortestPathGenerator(int value, String[] vertex, Double[][] edges) {
        double[][][] res = new double[value + 1][value][value];

        for (int i = 0; i < value; i++) {
            for (int j = 0; j < value; j++) {
                res[0][i][j] = edges[i][j];
            }
        }
        

        int x = 1;

        for (int k = 0; k < value; k++) {
            for (int i = 0; i < value; i++) {
                for (int j = 0; j < value; j++) {

                    if (edges[i][j] > edges[i][k] + edges[k][j]) {
                        edges[i][j] = edges[i][k] + edges[k][j];
                    }

                    res[x][i][j] = edges[i][j];
                }
            }

            x++;
        }
        
        return res;
    }
}
