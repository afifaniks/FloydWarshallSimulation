/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floyd.warshall;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;

/**
 * FXML Controller class
 *
 * @author void
 */
public class GraphViewerController implements Initializable {

    public static String[] vertex;
    public static double[][][] shortestPath;
    public static int step;
    private static String[] colors = {"#4A148C", "#00838F", "#2E7D32", "#283593", "#4E342E", "#37474F", "#827717"};
    public static boolean[] selection = new boolean[7];
    
    @FXML
    private AnchorPane pane;
    
    @FXML
    private Label resultLabel;

    @FXML
    private JFXButton previousButton;

    @FXML
    void previousButtonAction(ActionEvent event) throws IOException {
        AnchorPane temp = FXMLLoader.load(getClass().getResource("CategoryChooser.fxml"));
        pane.getChildren().setAll(temp);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        int[] layoutX = {415, 267, 576, 267, 576, 184, 646};
        int[] layoutY = {221, 362, 362, 80, 80, 221, 221};
        
        final int nodeRadius = 35;
      
        JFXButton[] btn = new JFXButton[7];
        Line[][] newEdge = new Line[7][7];
        boolean[][] flag = new boolean[7][7];
        
        for(int i = 0; i < 7; i++)
        {   btn[i] = new JFXButton();
            //btn[i].setVisible(false);
        }
        
        //Generating Edges
        
        for(int i = 0; i < step; i++){
            for(int j = 0; j < step; j++){
                    //Coordinate of starting point
                    double x1 = layoutX[i] + nodeRadius;
                    double y1 = layoutY[i] + nodeRadius;
                    
                    //Coordinate of ending point
                    double x2 = layoutX[j] + nodeRadius;
                    double y2 = layoutY[j] + nodeRadius;
                    
                if (!flag[i][j]) {

                    newEdge[i][j] = new Line(x1, y1, x2, y2);
                    
                    if((i == 2 && j == 3) || (j == 2 && i == 3)){
                        CubicCurve lineCurve = new CubicCurve();
                        lineCurve.setStartX(x1);
                        lineCurve.setStartY(y1);
                        lineCurve.setControlX1(layoutX[0] - 70);
                        lineCurve.setControlY1(layoutY[0] + 70);
                        lineCurve.setControlX2(layoutX[0] + 70);
                        lineCurve.setControlY2(layoutY[0] + 140);
                        lineCurve.setEndX(x2);
                        lineCurve.setEndY(y2);
                                //layoutX[0] - 200, layoutY[0] + 150, 
                                //x2, y2);
                        if (shortestPath[0][i][j] != 0 && shortestPath[0][i][j] != Double.POSITIVE_INFINITY
                                && shortestPath[0][j][i] != 0 && shortestPath[0][j][i] != Double.POSITIVE_INFINITY) {
                            lineCurve.setStroke(Color.RED);
                            lineCurve.setStrokeWidth(4);
                            lineCurve.setFill(Color.web("#ffffff", 0));
                            flag[j][i] = true;
                            pane.getChildren().add(lineCurve);

                        }else if (shortestPath[0][i][j] != 0 && shortestPath[0][i][j] != Double.POSITIVE_INFINITY) {
                            lineCurve.setStroke(Color.web(colors[i]));
                            lineCurve.setStrokeWidth(4);
                            lineCurve.setFill(Color.web("#ffffff", 0));
                            pane.getChildren().add(lineCurve);
                        }
                        
                    }
                    
                    else if((i == 1 && j == 4) || (j == 1 && i == 4)){
                        CubicCurve lineCurve = new CubicCurve();
                        lineCurve.setStartX(x1);
                        lineCurve.setStartY(y1);
                        lineCurve.setControlX1(layoutX[0]);
                        lineCurve.setControlY1(layoutY[0] - 40);
                        lineCurve.setControlX2(layoutX[0]);
                        lineCurve.setControlY2(layoutY[0] - 40);
                        lineCurve.setEndX(x2);
                        lineCurve.setEndY(y2);
                                //layoutX[0] - 200, layoutY[0] + 150, 
                                //x2, y2);
                        if (shortestPath[0][i][j] != 0 && shortestPath[0][i][j] != Double.POSITIVE_INFINITY
                                && shortestPath[0][j][i] != 0 && shortestPath[0][j][i] != Double.POSITIVE_INFINITY) {
                            lineCurve.setStroke(Color.RED);
                            lineCurve.setStrokeWidth(4);
                            lineCurve.setFill(Color.web("#ffffff", 0));
                            flag[j][i] = true;
                            pane.getChildren().add(lineCurve);

                        }else if (shortestPath[0][i][j] != 0 && shortestPath[0][i][j] != Double.POSITIVE_INFINITY) {
                            lineCurve.setStroke(Color.web(colors[i]));
                            lineCurve.setStrokeWidth(4);
                            lineCurve.setFill(Color.web("#ffffff", 0));
                            pane.getChildren().add(lineCurve);
                        }
                        
                    }
                    
                    else if((i == 5 && j == 6) || (j == 5 && i == 6)){
                        CubicCurve lineCurve = new CubicCurve();
                        lineCurve.setStartX(x1);
                        lineCurve.setStartY(y1);
                        lineCurve.setControlX1(layoutX[0]);
                        lineCurve.setControlY1(layoutY[0] - 40);
                        lineCurve.setControlX2(layoutX[0] + 70);
                        lineCurve.setControlY2(layoutY[0] - 40);
                        lineCurve.setEndX(x2);
                        lineCurve.setEndY(y2);

                        if (shortestPath[0][i][j] != 0 && shortestPath[0][i][j] != Double.POSITIVE_INFINITY
                                && shortestPath[0][j][i] != 0 && shortestPath[0][j][i] != Double.POSITIVE_INFINITY) {
                            lineCurve.setStroke(Color.RED);
                            lineCurve.setStrokeWidth(4);
                            lineCurve.setFill(Color.web("#ffffff", 0));
                            flag[j][i] = true;
                            pane.getChildren().add(lineCurve);

                        }else if (shortestPath[0][i][j] != 0 && shortestPath[0][i][j] != Double.POSITIVE_INFINITY) {
                            lineCurve.setStroke(Color.web(colors[i]));
                            lineCurve.setStrokeWidth(4);
                            lineCurve.setFill(Color.web("#ffffff", 0));
                            pane.getChildren().add(lineCurve);
                        }
                        
                    }

                    else if (shortestPath[0][i][j] != 0 && shortestPath[0][i][j] != Double.POSITIVE_INFINITY
                            && shortestPath[0][j][i] != 0 && shortestPath[0][j][i] != Double.POSITIVE_INFINITY) {
                        newEdge[i][j].setStroke(Color.RED);
                        flag[j][i] = true;
                        newEdge[i][j].setStrokeWidth(4);

                        pane.getChildren().add(newEdge[i][j]);
                    } else if (shortestPath[0][i][j] != 0 && shortestPath[0][i][j] != Double.POSITIVE_INFINITY) {
                        
                        newEdge[i][j].setStroke(Color.web(colors[i]));
                        newEdge[i][j].setStrokeWidth(4);

                        pane.getChildren().add(newEdge[i][j]);
                    }

                }
                    
                
            }
        }
        
        //Generating Vertices
        
        for (int i = 0; i < step; i++) {
            btn[i].setLayoutX(layoutX[i]);
            btn[i].setLayoutY(layoutY[i]);
            btn[i].setPrefHeight(70);
            btn[i].setPrefWidth(70);
            btn[i].setTextFill(Color.WHITE);
            btn[i].setStyle("-fx-background-color: "+colors[i]+"; -fx-background-radius: 35 35 35 35");
            pane.getChildren().add(btn[i]);
            
            
            switch (i + 1) {
                case 1:
                    btn[i].setText(vertex[i]);
                    break;
                case 2:
                    btn[i].setText(vertex[i]);
                    break;
                case 3:
                    btn[i].setText(vertex[i]);
                    break;
                case 4:
                    btn[i].setText(vertex[i]);
                    break;
                case 5:
                    btn[i].setText(vertex[i]);
                    break;
                case 6:
                    btn[i].setText(vertex[i]);
                    break;
                case 7:
                    btn[i].setText(vertex[i]);
                    break;
            }
        }
        
        btn[0].setOnAction((event) -> {
            KopaShamsuKopa(btn[0], 0);
        });

        btn[1].setOnAction((event) -> {
           KopaShamsuKopa(btn[1], 1);
        });
        
         btn[2].setOnAction((event) -> {
            KopaShamsuKopa(btn[2], 2);
        });

        btn[3].setOnAction((event) -> {
            KopaShamsuKopa(btn[3], 3);
        });
        
         btn[4].setOnAction((event) -> {
            KopaShamsuKopa(btn[4], 4);
        });

        btn[5].setOnAction((event) -> {
            KopaShamsuKopa(btn[5], 5);
        });
        
        btn[6].setOnAction((event) -> {
            KopaShamsuKopa(btn[6], 6);
        });

    }
    
    private static int selectionCtr = 0;
    private static int first;
    private static JFXButton temp;
    
    public void KopaShamsuKopa(JFXButton btn, int i){
        if(selection[i]){
            btn.setStyle("-fx-background-color:"+colors[i]+"; -fx-background-radius: 35 35 35 35");
            selection[i] = false;
            --selectionCtr;
        }else{
            btn.setStyle("-fx-background-color:"+colors[i]+"; -fx-border-color:#0091EA; -fx-border-width: 3");
            selection[i] = true;
            
            if(selectionCtr == 0){
                first = i;
                temp = btn;
                
                ++selectionCtr;
                
            }else{
                double res = shortestPath[step][first][i];
                System.out.println(res);
                resultLabel.setText(Double.toString(res));
                selection[first] = false;
                selection[i] = false;
                selectionCtr = 0;
                temp.setStyle("-fx-background-color:"+colors[first]+"; -fx-background-radius: 35 35 35 35");
                btn.setStyle("-fx-background-color:"+colors[i]+"; -fx-background-radius: 35 35 35 35");
            }
        }
    }
}
