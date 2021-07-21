/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Fouad Braimoh
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class MainApp extends Application{
            @Override
            public void start(Stage primaryStage) throws IOException {
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ToDoList.fxml")));

                Scene scene = new Scene(root);
                primaryStage.setTitle("ToDoListApp");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
    public static void main(String[] args) {
                launch(args);
    }

        }