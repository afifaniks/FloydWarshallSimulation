/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floyd.warshall;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 * @author void
 */
public class VertexAndEdgeController implements Initializable {
    
    static int value;
    
    JFXTextField vertex[];
    JFXTextField edges[][];

    @FXML
    private JFXButton previousButton;
    
    @FXML
    private AnchorPane vertexPane;
    
    @FXML
    private JFXButton nextButton;
    
    @FXML
    private GridPane vertexGrid;

    @FXML
    private GridPane edgesGrid;

    @FXML
    void previousButtonAction(ActionEvent event) throws IOException {
        AnchorPane rootPane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        vertexPane.getChildren().setAll(rootPane);
    }
    
    @FXML
    void nextButtonAction(ActionEvent event) throws IOException {
        
        String[] vertexValue = new String[value];
        Double[][] edgesValue = new Double[value][value];
        
        boolean valueTaker = true;
        
        for(int i = 0; i < value; i++)
        {
            vertexValue[i] = vertex[i].getText();
            
            if("".equals(vertexValue[i])){
                JFXSnackbar snackbar = new JFXSnackbar(vertexPane);
                vertex[i].setUnFocusColor(Color.web("#ff0000"));
                snackbar.show("Vertex Field can not be Empty!", 5000);
            }
            else
                vertex[i].setUnFocusColor(Color.web("#4d4d4d"));
        }
        
//        for(int i = 0; i < value; i++)
//            System.out.println(vertexValue[i]);
        
        for(int i = 0; i < value; i++){
            for(int j = 0; j < value; j++){
                
                String num = edges[i][j].getText();
                
                if(num == ""){
                    edges[i][j].setUnFocusColor(Color.web("#ff0000"));
                    JFXSnackbar snackbar = new JFXSnackbar(vertexPane);
                    valueTaker = false;
                    snackbar.show("Edge field can not be Empty!", 5000);
                }
                
                else if("inf".equals(num) || "i".equals(num)){
                    edgesValue[i][j] = Double.POSITIVE_INFINITY;
                }
                
                else{
                    try{
                        edgesValue[i][j] = Double.parseDouble(num);
                        edges[i][j].setUnFocusColor(Color.web("#4d4d4d"));
                    }catch(Exception e){
                        JFXSnackbar snackbar = new JFXSnackbar(vertexPane);
                        edges[i][j].setUnFocusColor(Color.web("#ff0000"));
                        snackbar.show("One or more fields are not valid!", 5000);
                        valueTaker = false;
                    }
                }
            }
        }
        
        if(valueTaker){
            FloydWarshallAlgorithm tableGenerator = new FloydWarshallAlgorithm(value, vertexValue, edgesValue);
            
            TableViewerController.edges = tableGenerator.getPath();
            TableViewerController.sequence = tableGenerator.getSequence();
            
            AnchorPane temp = FXMLLoader.load(getClass().getResource("TableViewer.fxml"));
            vertexPane.getChildren().setAll(temp);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        value = FXMLDocumentController.value;
        
        vertex = new JFXTextField[value];
        edges = new JFXTextField[value][value];
        
        for(int i = 0; i < value; i++){
            vertex[i] = new JFXTextField();
            vertex[i].setFocusColor(Color.web("1A237E"));
            vertex[i].setUnFocusColor(Color.web("#4d4d4d"));
            vertex[i].setPromptText("Vertex "+ (i+1));
            vertexGrid.add(vertex[i], 0, i);
        }
        
        for(int i = 0; i < value; i++)
            for(int j = 0; j < value; j++){
                edges[i][j] = new JFXTextField();
                edges[i][j].setFocusColor(Color.web("#00838F"));
                edges[i][j].setUnFocusColor(Color.web("#4d4d4d"));
                edges[i][j].setPromptText("v"+(i+1)+"e"+(j+1));
                edgesGrid.add(edges[i][j], j, i);
            }           
    }    
}
