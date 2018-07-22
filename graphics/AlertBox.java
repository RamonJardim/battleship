package graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

abstract class AlertBox {
    public static void display(Stage primaryStage, String title, String message, String option1, String option2) {
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        
        window.setTitle(title);
        window.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        Button optionA = new Button(option1);
        optionA.setOnAction((ActionEvent e) -> {
            window.close();
            Screen.start(primaryStage, 1); // PVP
        });
        
        Button optionB = new Button(option2);
        optionB.setOnAction((ActionEvent e) -> {
            window.close();
            Screen.start(primaryStage, 2); // PVP
        });
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, optionA, optionB);
        //layout.getChildren().addAll(label, optionB);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}