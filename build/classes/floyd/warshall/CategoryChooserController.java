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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author void
 */
public class CategoryChooserController implements Initializable {

    @FXML
    private JFXButton matrixButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton graphButton;
    
    @FXML
    private JFXButton previousButton;
    public static boolean manualChecker = false;
    
    @FXML
    void previousButtonAction(ActionEvent event) throws IOException {
        
        AnchorPane temp = null;
        if(manualChecker)
            temp = FXMLLoader.load(getClass().getResource("ManualInput.fxml"));
        else
            temp = FXMLLoader.load(getClass().getResource("VertexAndEdge.fxml"));
        pane.getChildren().setAll(temp);
    }

    @FXML
    void graphButtonHandler(ActionEvent event) throws IOException {
        AnchorPane temp = FXMLLoader.load(getClass().getResource("GraphViewer.fxml"));
        pane.getChildren().setAll(temp);
    }

    @FXML
    void matrixButtonHandler(ActionEvent event) throws IOException {
        AnchorPane temp = FXMLLoader.load(getClass().getResource("TableViewer.fxml"));
        pane.getChildren().setAll(temp);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
