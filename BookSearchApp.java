package com.example.bookapi;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BookSearchApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField searchField = new TextField();
        Button searchButton = new Button("Search");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        searchButton.setOnAction(e -> {
            String query = searchField.getText();
            String result = BookAPI.searchBook(query); // BookAPI.java will be your API handler
            resultArea.setText(result);
        });

        VBox root = new VBox(10, searchField, searchButton, resultArea);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Book Search App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
