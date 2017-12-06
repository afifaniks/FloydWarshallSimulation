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
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author void
 */
public class ManualInputController implements Initializable {

    public static int steps;
    private String[] vertexValue;
    private Double[][] edgesValue;
    private JFXButton[] selectors = new JFXButton[7];
    private JFXTextField[] edges;
    private boolean[] clicked = new boolean[7];
    private boolean lockedIn = false;
    private int counter = 0;
    private int row = 0;
    private static String[] colors = {"#4A148C", "#00838F", "#2E7D32", "#283593", "#4E342E", "#37474F", "#827717"};

    private JFXTextField[] vertex;

    @FXML
    private Label label;

    @FXML
    private AnchorPane manualPane;

    @FXML
    private GridPane vertexGrid;

    @FXML
    private GridPane edgesGrid;

    @FXML
    private GridPane weightGrid;

    @FXML
    private JFXButton nextButton;

    @FXML
    private JFXButton previousButton;

    @FXML
    private JFXButton weightButton;

    @FXML
    void nextButtonAction(ActionEvent event) throws IOException {

        if (!lockedIn) {
            JFXSnackbar snackbar = new JFXSnackbar(manualPane);
            snackbar.show("You haven't locked in!", 5000);

        //DO NOT EVEN DARE TO TOUCH HERE, I REPEAT DO NOT!
        
        } else if (counter < steps - 1) {

            boolean valueTaker = weightValidator();

            if (valueTaker) {
                counter++;
                Arrays.fill(clicked, false);
                nodeSelector();
                row++;
            }

        } else {

            boolean valueTaker = weightValidator();

            if (valueTaker)
                Arrays.fill(clicked, false);
            
            FloydWarshallAlgorithm tableGenerator = new FloydWarshallAlgorithm(steps, vertexValue, edgesValue);

            TableViewerController.edges = tableGenerator.getPath();
            TableViewerController.sequence = tableGenerator.getSequence();
            GraphViewerController.vertex = vertexValue;
            GraphViewerController.shortestPath = TableViewerController.edges;
            GraphViewerController.sequence = TableViewerController.sequence;
            GraphViewerController.step = steps;

            AnchorPane temp = FXMLLoader.load(getClass().getResource("CategoryChooser.fxml"));
            manualPane.getChildren().setAll(temp);
        }
    }

    @FXML
    void previousButtonAction(ActionEvent event) throws IOException {
            AnchorPane temp = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            manualPane.getChildren().setAll(temp);
        
    }

    @FXML
    void weightButtonAction(ActionEvent event) { //To Lock or Unlock The Vertex Fields
        if (!lockedIn) {
            boolean valueTaker = true;
            for (int i = 0; i < steps; i++) {
                vertexValue[i] = vertex[i].getText();

                if ("".equals(vertexValue[i])) { //Checking if any field is empty or not
                    JFXSnackbar snackbar = new JFXSnackbar(manualPane);
                    vertex[i].setUnFocusColor(Color.web("#ff0000"));
                    snackbar.show("Vertex Field can not be Empty!", 5000);
                    valueTaker = false;
                } else {
                    vertex[i].setUnFocusColor(Color.web("#4d4d4d"));
                }
            }

            if (valueTaker) {
                counter = 0;
                row = 0;

                label.setText("Select adjacent vertices of '" + vertexValue[counter] + "' and input weight: ");
                vertexGrid.getChildren().clear();
                lockedIn = true;
                weightButton.setText("Unlock");
                weightButton.setStyle("-fx-background-color: #FF0000;"
                            + " -fx-background-radius: 30 30 0 0;");

                Label vertexLabel[] = new Label[steps];
                Pane vPane[] = new Pane[steps];
                edges = new JFXTextField[steps];

                for (int i = 0; i < steps; i++) {
                    vertexLabel[i] = new Label();
                    vPane[i] = new Pane();
                    edges[i] = new JFXTextField();

                    vertexGrid.add(vPane[i], i, 0);
                    vertexLabel[i].setAlignment(Pos.CENTER);
                    vertexLabel[i].setText(vertexValue[i]);

                    //selector is initialized in initialize() and 
                    //will help to select adjacent nodes
                    GridPane.setFillHeight(selectors[i], true);
                    GridPane.setFillWidth(selectors[i], true);

                    selectors[i].setText(vertexValue[i]);
                    selectors[i].setTextFill(Color.WHITE);
                    selectors[i].setStyle("-fx-background-color: " + colors[i]
                            + "; -fx-border-color: #000000;"
                            + " -fx-background-radius: 10 10 10 10;"
                            + "; -fx-border-radius: 10 10 10 10;");

                    selectors[i].setPrefSize(100, 100);

                    edgesGrid.add(selectors[i], i, 0);

                    GridPane.setHalignment(vertexLabel[i], HPos.CENTER);

                    if (i == counter) {
                        selectors[i].setDisable(true);
                        vertexLabel[i].setTextFill(Color.WHITE);
                        vPane[i].setStyle("-fx-background-color: " + colors[i]
                                + "; -fx-border-color: #000000;"
                                + " -fx-background-radius: 10 10 10 10;"
                                + "; -fx-border-radius: 10 10 10 10;");
                    } else {
                        vertexLabel[i].setTextFill(Color.BLACK);
                        vPane[i].setStyle("-fx-background-color: #B2DFDB; -fx-border-color: #000000;");
                    }

                    //This portion gonna add the edges fields
                    edges[i].setFocusColor(Color.web("1A237E"));
                    edges[i].setUnFocusColor(Color.web("#4d4d4d"));
                    edges[i].setText("∞");

                    if (i == counter) {
                        edges[i].setText("0");
                    }

                    edges[i].setAlignment(Pos.CENTER);
                    edges[i].setDisable(true);
                    weightGrid.add(edges[i], i, 0);

                    vertexGrid.add(vertexLabel[i], i, 0);
                }

            }
        } else {
            lockedIn = false;
            weightButton.setText("Lock In");
                weightButton.setStyle("-fx-background-color:  #1B5E20;"
                            + " -fx-background-radius: 0 0 30 30;");
            counter = 0;
            weightGrid.getChildren().clear();
            edgesGrid.getChildren().clear();
            vertexGrid.getChildren().clear();

            for (int i = 0; i < steps; i++) {
                vertex[i].setText(vertexValue[i]);
                vertex[i].setFocusColor(Color.web("1A237E"));
                vertex[i].setUnFocusColor(Color.web("#4d4d4d"));
                vertexGrid.add(vertex[i], i, 0);
            }

        }
    }

    public void initialize(URL url, ResourceBundle rb) {
        vertexValue = new String[steps];
        edgesValue = new Double[steps + 1][steps + 1];

        steps = FXMLDocumentController.value;

        vertex = new JFXTextField[steps];

        for (int i = 0; i < 7; i++) {
            selectors[i] = new JFXButton();
        }

        for (int i = 0; i < steps; i++) {
            vertex[i] = new JFXTextField();
            vertex[i].setFocusColor(Color.web("1A237E"));
            vertex[i].setUnFocusColor(Color.web("#4d4d4d"));
            vertex[i].setText(Integer.toString(i + 1));
            vertexGrid.add(vertex[i], i, 0);
        }

        selectors[0].setOnAction((event) -> {
            valueSetter(selectors[0], 0);
        });

        selectors[1].setOnAction((event) -> {
            valueSetter(selectors[1], 1);
        });

        selectors[2].setOnAction((event) -> {
            valueSetter(selectors[2], 2);
        });

        selectors[3].setOnAction((event) -> {
            valueSetter(selectors[3], 3);
        });

        selectors[4].setOnAction((event) -> {
            valueSetter(selectors[4], 4);
        });

        selectors[5].setOnAction((event) -> {
            valueSetter(selectors[5], 5);
        });

        selectors[6].setOnAction((event) -> {
            valueSetter(selectors[6], 6);
        });
    }

    private void valueSetter(JFXButton btn, int serial) { //Will Change Button Appearance on click
        if (!clicked[serial]) {
            clicked[serial] = true;
            edges[serial].setDisable(false);
            edges[serial].setText("1");

            btn.setStyle("-fx-background-color: " + colors[serial]
                    + "; -fx-border-color: #FF0000; -fx-border-width: 3;"
                    + " -fx-background-radius: 10 10 10 10;"
                    + "; -fx-border-radius: 10 10 10 10;");
        } else {
            clicked[serial] = false;
            edges[serial].setDisable(true);
            edges[serial].setText("∞");
            btn.setStyle("-fx-background-color: " + colors[serial]
                    + "; -fx-border-color: #000000;"
                    + " -fx-background-radius: 10 10 10 10;"
                    + "; -fx-border-radius: 10 10 10 10;");
        }
    }

    //Will Show the selected node of which will be used to considered
    
    private void nodeSelector() { 
        weightGrid.getChildren().clear();
        edgesGrid.getChildren().clear();
        label.setText("Select adjacent vertices of '" + vertexValue[counter] + "' and input weight: ");
        vertexGrid.getChildren().clear();

        Label vertexLabel[] = new Label[steps];
        Pane vPane[] = new Pane[steps];
        edges = new JFXTextField[steps];

        for (int i = 0; i < steps; i++) {
            vertexLabel[i] = new Label();
            vPane[i] = new Pane();
            edges[i] = new JFXTextField();

            vertexGrid.add(vPane[i], i, 0);
            vertexLabel[i].setAlignment(Pos.CENTER);
            vertexLabel[i].setText(vertexValue[i]);

            //selector is initialized in initialize() and 
            //will help to select adjacent nodes
            GridPane.setFillHeight(selectors[i], true);
            GridPane.setFillWidth(selectors[i], true);

            selectors[i].setText(vertexValue[i]);
            selectors[i].setTextFill(Color.WHITE);
            selectors[i].setStyle("-fx-background-color: " + colors[i]
                    + "; -fx-border-color: #000000;"
                    + " -fx-background-radius: 10 10 10 10;"
                    + "; -fx-border-radius: 10 10 10 10;");

            selectors[i].setPrefSize(100, 100);

            edgesGrid.add(selectors[i], i, 0);

            GridPane.setHalignment(vertexLabel[i], HPos.CENTER);

            if (i == counter) {
                selectors[i].setDisable(true);
                vertexLabel[i].setTextFill(Color.WHITE);
                vPane[i].setStyle("-fx-background-color: " + colors[i]
                        + "; -fx-border-color: #000000;"
                        + " -fx-background-radius: 10 10 10 10;"
                        + "; -fx-border-radius: 10 10 10 10;");
            } else {
                vertexLabel[i].setTextFill(Color.BLACK);
                vPane[i].setStyle("-fx-background-color: #B2DFDB; -fx-border-color: #000000;");
                selectors[i].setDisable(false);
            }

            //This portion gonna add the edges fields
            edges[i].setFocusColor(Color.web("1A237E"));
            edges[i].setUnFocusColor(Color.web("#4d4d4d"));
            edges[i].setText("∞");

            if (i == counter) {
                edges[i].setText("0");
            }

            edges[i].setAlignment(Pos.CENTER);
            edges[i].setDisable(true);
            weightGrid.add(edges[i], i, 0);

            vertexGrid.add(vertexLabel[i], i, 0);
        }

        // counter++;
    }

    private boolean weightValidator() { //Will get the weights from the weight fields
                                        //and returns a bollean value to validate inputs
        boolean valueTaker = true;

        for (int i = 0; i < steps; i++) {

            String num = edges[i].getText();

            if (num == "") {
                edges[i].setUnFocusColor(Color.web("#ff0000"));
                JFXSnackbar snackbar = new JFXSnackbar(manualPane);
                valueTaker = false;
                snackbar.show("Edge field can not be Empty!", 5000);
            } else if ("∞".equals(num)) {
                edgesValue[row][i] = Double.POSITIVE_INFINITY;
            } else {
                try {
                    edgesValue[row][i] = Double.parseDouble(num);
                    edges[i].setUnFocusColor(Color.web("#4d4d4d"));
                } catch (Exception e) {
                    JFXSnackbar snackbar = new JFXSnackbar(manualPane);
                    edges[i].setUnFocusColor(Color.web("#ff0000"));
                    snackbar.show("One or more fields are not valid!", 5000);
                    valueTaker = false;
                }
            }
        }

        return valueTaker;
    }

}
