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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.paint.Paint;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author void
 */
public class TableViewerController implements Initializable {

    public static int steps;
    private static Timeline t;
    private static int ctr2 = -1;
    private static int ctr;
    public static double[][][] edges;
    public static double[][][] sequence;
    private Pane[][] tempPane;
    private Label[][] label;
    private Pane[][] seqPane;
    private Label[][] sLabel;
    private static boolean toggle = true;
    private static boolean startOver = false;

    @FXML
    private Label pathLabel;

    @FXML
    private Label sequenceLabel;

    @FXML
    private AnchorPane tablePane;

    @FXML
    private JFXButton nextButton;

    @FXML
    private JFXButton previousButton;

    @FXML
    private JFXButton autoPlay;

    @FXML
    private GridPane sequenceGrid;

    @FXML
    private GridPane pathGrid;

    @FXML
    void autoPlayButtonAction(ActionEvent e) throws InterruptedException {
        if (toggle) {
            startOver = false;
            toggle = false;
            autoPlay.setText("Stop!");
            autoPlay.setStyle("-fx-background-color: #DD2C00; -fx-background-radius: 30 30 30 30;");
            nextButton.setVisible(false);
            previousButton.setVisible(false);

            ctr = 0;
            ctr2 = -1;

            t = new Timeline();

            t.getKeyFrames().add(new KeyFrame(Duration.millis(1500), (ActionEvent ex) -> {
                ctr++;
                ctr2++;

                for (int i = 0; i < steps; i++) {
                    for (int j = 0; j < steps; j++) {
                        pathGrid.getChildren().removeAll(label[i][j]);
                        pathGrid.getChildren().removeAll(tempPane[i][j]);
                        sequenceGrid.getChildren().removeAll(sLabel[i][j]);
                        sequenceGrid.getChildren().removeAll(seqPane[i][j]);
                    }
                }

                pathLabel.setText("Step: " + ctr);
                sequenceLabel.setText("Step: " + ctr);

                sequenceGrid.setAlignment(Pos.CENTER);
                pathGrid.setAlignment(Pos.CENTER);

                for (int i = 0; i < steps; i++) {
                    for (int j = 0; j < steps; j++) {

                        label[i][j] = new Label();
                        sLabel[i][j] = new Label();

                        tempPane[i][j] = new Pane();
                        seqPane[i][j] = new Pane();

                        pathGrid.add(tempPane[i][j], j, i);
                        sequenceGrid.add(seqPane[i][j], j, i);

                        String x = Double.toString(edges[ctr][i][j]);
                        int y = (((int) sequence[ctr][i][j]));

                        label[i][j].setAlignment(Pos.CENTER);
                        sLabel[i][j].setAlignment(Pos.CENTER);

                        if (x.equals("Infinity")) {
                            x = "∞";
                        }

                        label[i][j].setText(x);
                        sLabel[i][j].setText(Integer.toString(y));

                        if (i == ctr2 || j == ctr2) {
                            tempPane[i][j].setStyle("-fx-background-color: #283593; -fx-border-color: #000000;");
                            seqPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #B2DFDB");

                            label[i][j].setTextFill(Paint.valueOf("white"));

                        } else {
                            if (edges[ctr][i][j] != edges[ctr - 1][i][j]) {
                                label[i][j].setTextFill(Paint.valueOf("white"));
                                tempPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #DD2C00");

                                sLabel[i][j].setTextFill(Paint.valueOf("white"));
                                seqPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #DD2C00");
                            } else {
                                tempPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #B2DFDB");
                                seqPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #B2DFDB");
                            }

                        }

                        GridPane.setHalignment(label[i][j], HPos.CENTER);
                        GridPane.setHalignment(sLabel[i][j], HPos.CENTER);

                        sequenceGrid.add(sLabel[i][j], j, i);
                        pathGrid.add(label[i][j], j, i);

                    }
                }

                if (ctr == steps) {
                    startOver = true;
                    autoPlay.setText("AutoPlay");
                    nextButton.setText("Start Over");
                    nextButton.setStyle("-fx-background-radius: 0 0 0 0; -fx-padding: 0; -fx-background-color:  #DD2C00;");
                    autoPlay.setStyle("-fx-background-color:  #1B5E20; -fx-background-radius: 30 0 30 0;");
                    nextButton.setVisible(true);
                    previousButton.setVisible(true);
                    toggle = true;
                }

            }));

            t.setCycleCount(steps);
            t.play();
        } else {
            toggle = true;
            t.stop();

            if (ctr != steps) {
                nextButton.setText("Next");
                nextButton.setStyle("-fx-background-radius:  0 30 30 0; -fx-background-color:  #1A237E;");
            }
            autoPlay.setText("AutoPlay");
            autoPlay.setStyle("-fx-background-color:  #1B5E20; -fx-background-radius: 30 0 30 0;");
            nextButton.setVisible(true);
            previousButton.setVisible(true);
        }

    }

    @FXML
    void nextButtonAction(ActionEvent event) throws IOException {
        
        if(ctr == steps){
            AnchorPane rootPane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            tablePane.getChildren().setAll(rootPane);
        }
        else if (ctr < steps) {

            ctr++;
            ctr2++;

            for (int i = 0; i < steps; i++) {
                for (int j = 0; j < steps; j++) {
                    pathGrid.getChildren().removeAll(label[i][j]);
                    pathGrid.getChildren().removeAll(tempPane[i][j]);
                    sequenceGrid.getChildren().removeAll(sLabel[i][j]);
                    sequenceGrid.getChildren().removeAll(seqPane[i][j]);
                }
            }

            pathLabel.setText("Step: " + ctr);
            sequenceLabel.setText("Step: " + ctr);

            sequenceGrid.setAlignment(Pos.CENTER);
            pathGrid.setAlignment(Pos.CENTER);

            for (int i = 0; i < steps; i++) {
                for (int j = 0; j < steps; j++) {

                    label[i][j] = new Label();
                    sLabel[i][j] = new Label();

                    tempPane[i][j] = new Pane();
                    seqPane[i][j] = new Pane();

                    pathGrid.add(tempPane[i][j], j, i);
                    sequenceGrid.add(seqPane[i][j], j, i);

                    String x = Double.toString(edges[ctr][i][j]);
                    int y = (((int) sequence[ctr][i][j]));

                    label[i][j].setAlignment(Pos.CENTER);
                    sLabel[i][j].setAlignment(Pos.CENTER);

                    if (x.equals("Infinity")) {
                        x = "∞";
                    }

                    label[i][j].setText(x);
                    sLabel[i][j].setText(Integer.toString(y));

                    if (i == ctr2 || j == ctr2) {
                        tempPane[i][j].setStyle("-fx-background-color: #283593; -fx-border-color: #000000;");
                        seqPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #B2DFDB");

                        label[i][j].setTextFill(Paint.valueOf("white"));

                    } else {
                        if (edges[ctr][i][j] != edges[ctr - 1][i][j]) {
                            label[i][j].setTextFill(Paint.valueOf("white"));
                            tempPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #DD2C00");

                            sLabel[i][j].setTextFill(Paint.valueOf("white"));
                            seqPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #DD2C00");
                        } else {
                            tempPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #B2DFDB");
                            seqPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #B2DFDB");
                        }

                    }

                    GridPane.setHalignment(label[i][j], HPos.CENTER);
                    GridPane.setHalignment(sLabel[i][j], HPos.CENTER);

                    sequenceGrid.add(sLabel[i][j], j, i);
                    pathGrid.add(label[i][j], j, i);

                }
            }
            
            if (ctr == steps) {
                startOver = true;
                nextButton.setText("Start Over");
                nextButton.setStyle("-fx-background-radius: 0 0 0 0; -fx-padding: 0; -fx-background-color:  #DD2C00;");
            }

        }
        
        
    }

    @FXML
    void previousButtonAction(ActionEvent event) throws IOException {
        if (startOver) {
            startOver = false;
            nextButton.setText("Next");
            nextButton.setStyle("-fx-background-radius:  0 30 30 0; -fx-background-color:  #1A237E;");
        }
        if (ctr == 0) {
            AnchorPane temp = FXMLLoader.load(getClass().getResource("CategoryChooser.fxml"));
            tablePane.getChildren().setAll(temp);
        } else {
            ctr2--;
            ctr--;

            for (int i = 0; i < steps; i++) {
                for (int j = 0; j < steps; j++) {
                    pathGrid.getChildren().removeAll(label[i][j]);
                    pathGrid.getChildren().removeAll(tempPane[i][j]);
                    sequenceGrid.getChildren().removeAll(sLabel[i][j]);
                    sequenceGrid.getChildren().removeAll(seqPane[i][j]);
                }
            }

            if (ctr == 0) {
                pathLabel.setText("Initial Path");
                sequenceLabel.setText("Initial Sequence");
            } else {
                pathLabel.setText("Step: " + ctr);
                sequenceLabel.setText("Step: " + ctr);
            }

            sequenceGrid.setAlignment(Pos.CENTER);
            pathGrid.setAlignment(Pos.CENTER);

            for (int i = 0; i < steps; i++) {
                for (int j = 0; j < steps; j++) {

                    label[i][j] = new Label();
                    sLabel[i][j] = new Label();

                    tempPane[i][j] = new Pane();
                    seqPane[i][j] = new Pane();

                    pathGrid.add(tempPane[i][j], j, i);
                    sequenceGrid.add(seqPane[i][j], j, i);

                    String x = Double.toString(edges[ctr][i][j]);
                    int y = (((int) sequence[ctr][i][j]));

                    label[i][j].setAlignment(Pos.CENTER);
                    sLabel[i][j].setAlignment(Pos.CENTER);

                    if (x.equals("Infinity")) {
                        x = "∞";
                    }

                    label[i][j].setText(x);
                    sLabel[i][j].setText(Integer.toString(y));

                    if (i == ctr2 || j == ctr2) {
                        tempPane[i][j].setStyle("-fx-background-color: #283593; -fx-border-color: #000000;");
                        seqPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #B2DFDB");

                        label[i][j].setTextFill(Paint.valueOf("white"));

                    } else {
                        if (ctr >= 1 && edges[ctr][i][j] != edges[ctr - 1][i][j]) {
                            label[i][j].setTextFill(Paint.valueOf("white"));
                            tempPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #DD2C00");

                            sLabel[i][j].setTextFill(Paint.valueOf("white"));
                            seqPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #DD2C00");
                        } else {
                            tempPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #B2DFDB");
                            seqPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #B2DFDB");
                        }

                    }

                    GridPane.setHalignment(label[i][j], HPos.CENTER);
                    GridPane.setHalignment(sLabel[i][j], HPos.CENTER);

                    sequenceGrid.add(sLabel[i][j], j, i);
                    pathGrid.add(label[i][j], j, i);

                }
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ctr = -1;
        ctr2 = -1;

        tempPane = new Pane[steps][steps];
        seqPane = new Pane[steps][steps];

        label = new Label[steps][steps];
        sLabel = new Label[steps][steps];

        pathLabel.setText("Initial Path");
        sequenceLabel.setText("Initial Sequence");

        sequenceGrid.setAlignment(Pos.CENTER);
        pathGrid.setAlignment(Pos.CENTER);

        for (int i = 0; i < steps; i++) {
            for (int j = 0; j < steps; j++) {

                label[i][j] = new Label();
                sLabel[i][j] = new Label();

                seqPane[i][j] = new Pane();
                tempPane[i][j] = new Pane();

                String x = Double.toString(edges[0][i][j]); //Path
                String y = Integer.toString((int) sequence[0][i][j]); //Sequence

                label[i][j].setAlignment(Pos.CENTER);
                sLabel[i][j].setAlignment(Pos.CENTER);

                //Setting up Distance Table
                if (x.equals("Infinity")) {
                    x = "∞";
                }

                label[i][j].setText(x);
                sLabel[i][j].setText(y);

                tempPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #B2DFDB");
                seqPane[i][j].setStyle("-fx-border-color: #000000; -fx-background-color: #B2DFDB");

                pathGrid.add(tempPane[i][j], j, i);
                sequenceGrid.add(seqPane[i][j], j, i);

                GridPane.setHalignment(label[i][j], HPos.CENTER);
                GridPane.setHalignment(sLabel[i][j], HPos.CENTER);

                pathGrid.add(label[i][j], j, i);
                sequenceGrid.add(sLabel[i][j], j, i);

            }
        }
        ctr++;
    }

}
