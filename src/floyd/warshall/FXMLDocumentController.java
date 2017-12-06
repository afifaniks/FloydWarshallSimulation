/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package floyd.warshall;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author void
 */


public class FXMLDocumentController implements Initializable {
    
    public static int value = 1;
    
    @FXML
    private JFXComboBox<Integer> vertex;
    
    @FXML
    private JFXButton nextButton;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private ToggleGroup inputType;
    
    @FXML
    private JFXRadioButton manualInput;

    @FXML
    private JFXRadioButton matrixInput;

    @FXML
    private void nextButtonAction(ActionEvent e) throws IOException{
        value = vertex.getValue();
        
        
        //Will Initialize the number of vertex in targeted classes
        
        TableViewerController.steps = value;
        ManualInputController.steps = value;
        
        AnchorPane temp = null;
        
        if(matrixInput.isSelected())
        {
            temp  = FXMLLoader.load(getClass().getResource("VertexAndEdge.fxml"));
            CategoryChooserController.manualChecker = false;
        }
        
        else
        {
            temp  = FXMLLoader.load(getClass().getResource("ManualInput.fxml"));
            CategoryChooserController.manualChecker = true;
        }
        rootPane.getChildren().setAll(temp);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> list = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7);
        vertex.setItems(list);
        inputType.selectToggle(matrixInput);
        vertex.setValue(value);
    }  
    
}
