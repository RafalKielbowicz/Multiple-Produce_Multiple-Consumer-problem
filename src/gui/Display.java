package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Queue;

public class Display extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Queue<Integer> redQueue = new LinkedList<>();
    Queue<Integer> blueQueue = new LinkedList<>();
    Container container = new Container(redQueue, blueQueue);
    GridPane grid;
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {

        grid = new GridPane();
        grid.setGridLinesVisible(false);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);


        //Buttons
        Button red = new Button("RED");
        red.setMaxSize(70, 40);
        red.setMinSize(70, 40);

        Button blue = new Button("BLUE");
        blue.setMaxSize(70, 40);
        blue.setMinSize(70, 40);

        GridPane.setConstraints(blue, 0, 1);
        GridPane.setConstraints(red, 0, 0);

        // Consumers
        ToggleButton consumer1 = new ToggleButton();
        consumer1.setMaxSize(120, 170);
        consumer1.setMinSize(120, 170);

        ToggleButton consumer2 = new ToggleButton();
        consumer2.setMaxSize(120, 170);
        consumer2.setMinSize(120, 170);

        ToggleButton consumer3 = new ToggleButton();
        consumer3.setMaxSize(120, 170);
        consumer3.setMinSize(120, 170);

        GridPane.setConstraints(consumer1, 0, 3);
        GridPane.setConstraints(consumer2, 0, 4);
        GridPane.setConstraints(consumer3, 0, 5);


        grid.getChildren().addAll(consumer1, consumer2, consumer3, red, blue);

        // button handlers
        red.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addBall(redQueue, red.getText());
            }
        });

        blue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addBall(blueQueue, blue.getText());
            }
        });

        // customer handlers
        EventHandler<ActionEvent> customerHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello");
            }
        };

        consumer1.setOnAction(customerHandler);
        consumer2.setOnAction(customerHandler);
        consumer3.setOnAction(customerHandler);


        grid.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());

        Scene gridScene = new Scene(grid, 1000, 650);

        window = primaryStage;
        window.setResizable(false);
        window.setTitle("Multiple Producer and Consumer problem");
        window.setScene(gridScene);
        window.show();

    }


    void addBall(Queue<Integer> queue, String name) {
        if (container.LIMIT == queue.size()) {
            blockAddition();
        } else {
            if (name == "RED") {
                queue.add(1);
                Circle r = new Circle();
                r.setCenterX(90f);
                r.setCenterY(90f);
                r.setRadius(32.0f);
                r.setFill(Color.RED);
                GridPane.setConstraints(r, 15 + queue.size(), 4);
                grid.getChildren().addAll(r);

            } else {
                queue.add(1);
                Circle r = new Circle();
                r.setCenterX(90f);
                r.setCenterY(90f);
                r.setRadius(32.0f);
                r.setFill(Color.BLUE);
                GridPane.setConstraints(r, 25 + queue.size(), 4);
                grid.getChildren().addAll(r);

            }
        }

    }

    void blockAddition() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("UWAGA");
        alert.setHeaderText("KOLEJKA ZAPEŁNIONA!");
        alert.setResizable(true);
        alert.showAndWait();
    }


}