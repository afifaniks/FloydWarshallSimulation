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
public class GraphViewerController implements Initializable {

    public static String[] vertex;
    public static double[][][] shortestPath;
    public static int step;

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton node1;

    @FXML
    private JFXButton node2;

    @FXML
    private JFXButton node6;

    @FXML
    private JFXButton node4;

    @FXML
    private JFXButton node3;

    @FXML
    private JFXButton node7;

    @FXML
    private JFXButton node5;

    @FXML
    private JFXButton previousButton;

    @FXML
    void previousButtonAction(ActionEvent event) throws IOException {
        AnchorPane temp = FXMLLoader.load(getClass().getResource("CategoryChooser.fxml"));
        pane.getChildren().setAll(temp);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < step; i++) {
            switch (i + 1) {
                case 1:
                    node1.setVisible(true);
                    node1.setText(vertex[i]);
                    break;
                case 2:
                    node2.setVisible(true);
                    node2.setText(vertex[i]);
                    break;
                case 3:
                    node3.setVisible(true);
                    node3.setText(vertex[i]);
                    break;
                case 4:
                    node4.setVisible(true);
                    node4.setText(vertex[i]);
                    break;
                case 5:
                    node5.setVisible(true);
                    node5.setText(vertex[i]);
                    break;
                case 6:
                    node6.setVisible(true);
                    node6.setText(vertex[i]);
                    break;
                case 7:
                    node7.setVisible(true);
                    node7.setText(vertex[i]);
                    break;
            }
        }

    }
}
