package GameOfLife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Main extends Application {

    private int width = 550;
    private int height = 500;
    private int resolution = 5;
    private Pane background = new Pane();
    private String mode = "Original";
    private Grid grid = new Grid(width, height, resolution);
    private int cols = grid.getCols();
    private int rows = grid.getRows();
    private Unit[][] units = grid.getUnits();
    private Rectangle[][] recs = new Rectangle[cols][rows];
    private String pattern = "Random";
    private Color TrackColor = Color.WHITE;

    public static void main(String[] args) {
        launch(args);
    }

    public void createRecs() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Unit unit = units[i][j];
                //  leave some space for stroke
                Rectangle rec =
                        new Rectangle(
                                unit.getX() + 5,
                                unit.getY() + 5,
                                resolution - 0.5,
                                resolution - 0.5);
                rec.setStroke(Color.WHITE);
                recs[i][j] = rec;
                background.getChildren().add(rec);
            }
        }
    }

    public void render(String mode) {
        if (mode == "HeatMap") {
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    Unit unit = units[i][j];
                    if (unit.getAge() == 0) {
                        recs[i][j].setFill(Color.WHITE);
                        if (unit.getHadBeenOccupied()) recs[i][j].setFill(TrackColor);
                    } else if (unit.getAge() >= 1 && unit.getAge() < 100)
                        recs[i][j].setFill(Color.rgb(0, 100, 200));
                    else if (unit.getAge() >= 100 && unit.getAge() < 250)
                        recs[i][j].setFill(Color.rgb(0, 0, 255));
                    else if (unit.getAge() >= 250 && unit.getAge() < 400)
                        recs[i][j].setFill(Color.rgb(0, 255, 0));
                    else if (unit.getAge() >= 400 && unit.getAge() < 600)
                        recs[i][j].setFill(Color.rgb(255, 255, 0));
                    else recs[i][j].setFill(Color.rgb(255, 0, 0));
                }
            }
        } else if (mode == "Original") {
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                    Unit unit = units[i][j];
                    if (unit.getState() == 1) {
                        recs[i][j].setFill(Color.BLACK);
                    } else {
                        recs[i][j].setFill(Color.WHITE);
                        if (unit.getHadBeenOccupied()) recs[i][j].setFill(TrackColor);
                    }
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //        Initialization
        background.setMaxWidth(width + 10);
        background.setMaxHeight(height + 10);

        createRecs();
        render(mode);
        // Top text
        StackPane paneforText = new StackPane();
        Text generationDemo = new Text("Generation: " + grid.getGeneration());
        generationDemo.setFont(new Font(30));
        paneforText.getChildren().add(generationDemo);
        //            Animation
        KeyFrame keyFrame =
                new KeyFrame(
                        Duration.millis(50),
                        event -> {
                            grid.setNextStates();
                            grid.evolutions();
                            generationDemo.setText("Generation: " + grid.getGeneration());
                            render(mode);
                        });

        Timeline animation = new Timeline(keyFrame);
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.setRate(0.6);
        //        animation.play();
        // UI

        VBox paneForUI = new VBox(30);
        paneForUI.setPadding(new Insets(5, 10, 5, 10));
        paneForUI.setAlignment(Pos.CENTER);

        TextField rateText = new TextField("0.6");
        Label rateLabel = new Label("Rate: ", rateText);
        rateLabel.setContentDisplay(ContentDisplay.RIGHT);
        Slider rateSl = new Slider(0, 1, 0.6);
        rateSl.setShowTickLabels(true);
        rateSl.setShowTickMarks(true);
        rateSl.valueProperty()
                .addListener(
                        observable -> {
                            animation.setRate(rateSl.getValue());
                            rateText.setText("" + rateSl.getValue());
                        });
        rateText.setOnAction(
                event -> {
                    double rate = Double.valueOf(rateText.getText());
                    animation.setRate(rate);
                    rateSl.setValue(rate);
                });

        ChoiceBox<String> Patterns = new ChoiceBox<>();
        Patterns.getItems()
                .addAll(
                        "Random",
                        "Glider",
                        "Gosper glider gun",
                        "3c14piwave",
                        "7-engine Cordership",
                        "6_bits",
                        "prepulsarshuttle42");
        Patterns.setValue("Random");
        Patterns.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            if (newValue == "Random") {
                                pattern = "Random";
                                animation.stop();
                                grid.randomInitialSet(0.85);
                                animation.play();
                            } else if (newValue == "Glider") {
                                pattern = "Glider";
                                animation.stop();
                                grid.deadInitialSet();
                                grid.addGlider(2, 2);
                                animation.play();
                            } else if (newValue == "Gosper glider gun") {
                                pattern = "Gosper glider gun";
                                animation.stop();
                                grid.deadInitialSet();
                                grid.addGosperGliderGun(30, 15);
                                animation.play();
                            } else if (newValue == "3c14piwave") {
                                pattern = "3c14piwave";
                                animation.stop();
                                grid.deadInitialSet();
                                try {
                                    grid.addPatternFromFile(20, 15, "patternFile/3c14piwave.cells");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                animation.play();
                            } else if (newValue == "7-engine Cordership") {
                                pattern = "7-engine Cordership";
                                animation.stop();
                                grid.deadInitialSet();
                                try {
                                    grid.addPatternFromFile(
                                            20, 15, "patternFile/7enginecordership.cells");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                animation.play();
                            } else if (newValue == "6_bits") {
                                pattern = "6_bits";
                                animation.stop();
                                grid.deadInitialSet();
                                try {
                                    grid.addPatternFromFile(30, 25, "patternFile/6bits.cells");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                animation.play();
                            } else if (newValue == "prepulsarshuttle42") {
                                pattern = "prepulsarshuttle42";
                                animation.stop();
                                grid.deadInitialSet();
                                try {
                                    grid.addPatternFromFile(
                                            30, 20, "patternFile/prepulsarshuttle42.cells");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                animation.play();
                            }
                        });
        Label patternLabel = new Label("Patterns", Patterns);
        patternLabel.setContentDisplay(ContentDisplay.RIGHT);
        TextField readFile = new TextField();
        Label fileLabel = new Label("File Name", readFile);
        fileLabel.setContentDisplay(ContentDisplay.RIGHT);
        readFile.setOnAction(
                event -> {
                    animation.stop();
                    grid.deadInitialSet();

                    try {
                        grid.addPatternFromFile(30, 30, "patternFile/" + readFile.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    animation.play();
                });
        ChoiceBox<String> Mode = new ChoiceBox<>();
        Mode.getItems().addAll("Original", "HeatMap");
        Mode.setValue("Original");
        Mode.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            mode = newValue;
                        });
        Label modeLabel = new Label("Mode", Mode);
        modeLabel.setContentDisplay(ContentDisplay.RIGHT);
        Button playButton = new Button("Play");
        playButton.setOnAction(event -> animation.play());
        Button pauseButton = new Button("Stop");
        pauseButton.setOnAction(event -> animation.pause());
        Button reStartButton = new Button("Restart");
        RadioButton TrackButton = new RadioButton("Track");
        RadioButton GridButton = new RadioButton("Grid");
        HBox paneForRadiobtns = new HBox(20);
        paneForRadiobtns.getChildren().addAll(TrackButton, GridButton);
        paneForRadiobtns.setAlignment(Pos.CENTER);
        TrackButton.setOnAction(
                event -> {
                    if (TrackButton.isSelected()) TrackColor = Color.rgb(192, 233, 169);
                    else TrackColor = Color.WHITE;
                });
        GridButton.setOnAction(
                event -> {
                    if (GridButton.isSelected()) {
                        for (int i = 0; i < cols; i++) {
                            for (int j = 0; j < rows; j++) {
                                recs[i][j].setStroke(Color.GRAY);
                            }
                        }
                    } else {
                        for (int i = 0; i < cols; i++) {
                            for (int j = 0; j < rows; j++) {
                                recs[i][j].setStroke(Color.WHITE);
                            }
                        }
                    }
                });
        reStartButton.setOnAction(
                event -> {
                    if (pattern == "Random") {
                        animation.stop();
                        grid.randomInitialSet(0.85);
                        animation.play();
                    } else if (pattern == "Glider") {
                        animation.stop();
                        grid.deadInitialSet();
                        grid.addGlider(2, 2);
                        animation.play();
                    } else if (pattern == "Gosper glider gun") {
                        animation.stop();
                        grid.deadInitialSet();
                        grid.addGosperGliderGun(30, 15);
                        animation.play();
                    } else if (pattern == "3c14piwave") {
                        animation.stop();
                        grid.deadInitialSet();
                        try {
                            grid.addPatternFromFile(20, 15, "patternFile/3c14piwave.cells");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        animation.play();
                    } else if (pattern == "7-engine Cordership") {
                        animation.stop();
                        grid.deadInitialSet();
                        try {
                            grid.addPatternFromFile(20, 15, "patternFile/7enginecordership.cells");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        animation.play();
                    } else if (pattern == "6_bits") {
                        animation.stop();
                        grid.deadInitialSet();
                        try {
                            grid.addPatternFromFile(20, 15, "patternFile/6bits.cells");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        animation.play();
                    }
                });
        HBox paneForBtns = new HBox(20);
        paneForBtns.getChildren().addAll(playButton, pauseButton, reStartButton);
        paneForUI
                .getChildren()
                .addAll(
                        rateLabel,
                        rateSl,
                        patternLabel,
                        fileLabel,
                        modeLabel,
                        paneForRadiobtns,
                        paneForBtns);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10));
        borderPane.setCenter(background);
        borderPane.setTop(paneforText);
        borderPane.setLeft(paneForUI);
        background.setStyle("-fx-border-color: darkblue ;-fx-border-width: 2px");

        Scene scene = new Scene(borderPane, 950, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Life");
        primaryStage.show();
    }
}
