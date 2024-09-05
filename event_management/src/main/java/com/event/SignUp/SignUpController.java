package com.event.SignUp;

import java.util.HashMap;
import java.util.Map;

import com.event.SignIn.LogInController;
import com.event.firebaseConfig.DataService;
import com.event.notification.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUpController {

    private LogInController loginController;
    private Scene signupScene;

    // constructor
    public SignUpController(LogInController loginController) {
        this.loginController = loginController;
    }

    public Scene createSignupScene(Stage primaryStage) {

        // username
        Label userLabel = new Label("Username:");
        userLabel.setStyle("-fx-font-weight: bold");
        userLabel.setFont(new Font(25));
        userLabel.setStyle("-fx-text-fill: white;");
        TextField userTextField = new TextField();
   

        // password
        Label passLabel = new Label("Password:");
        passLabel.setStyle("-fx-font-weight: bold");
        passLabel.setFont(new Font(25));
        passLabel.setStyle("-fx-text-fill: white;");
        PasswordField passField = new PasswordField();

        // signup button
        Button signupButton = new Button("Signup");

        // already registered button
        Button alreadyRegisteredButton = new Button("Already Registered");

        // vbox contain userlabel, usertextfield
        VBox fieldBox1 = new VBox(10, userLabel, userTextField);
        fieldBox1.setMaxSize(300, 30);

        // vbox contain passlabel, passfield
        VBox fieldBox2 = new VBox(10, passLabel, passField);
        fieldBox2.setMaxSize(300, 30);

        // hbox contain already registered button and signup button
        HBox buttonBox = new HBox(50, alreadyRegisteredButton, signupButton);
        buttonBox.setMaxSize(350, 30);
        buttonBox.setAlignment(Pos.CENTER);

        // set actions for the buttons
        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleSignup(primaryStage, userTextField.getText(), passField.getText());
            }
        });

        alreadyRegisteredButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loginController.showLoginScene();
            }
        });

        // style the buttons
        signupButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14px;");
        alreadyRegisteredButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 14px;");

        // vbox contain all boxes
        VBox vbox = new VBox(20, fieldBox1, fieldBox2, buttonBox);
        vbox.setStyle("-fx-background-image: url('loginControl.jpg'); -fx-background-size: cover;");
        vbox.setAlignment(Pos.CENTER);
        signupScene = new Scene(vbox, 1920, 1080);

        // Animate the text fields
        animateField1(fieldBox1);
        animateField2(fieldBox2);
        animateField(buttonBox);

        return signupScene;
    }

    private void animateField1(VBox fieldBox) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), fieldBox);
        transition.setFromX(-1920);
        transition.setToX(0);
        transition.play();
    }

    private void animateField2(VBox fieldBox) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), fieldBox);
        transition.setFromX(1920);
        transition.setToX(0);
        transition.play();
    }

    private void animateField(HBox buttonBox) {
        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), buttonBox);
        transition.setFromX(-1920);
        transition.setToX(0);
        transition.play();
    }

    private void handleSignup(Stage primaryStage, String username, String password) {
        DataService dataService;
        try {
            dataService = new DataService();
            Map<String, Object> data = new HashMap<>();
            data.put("password", password);
            data.put("username", username);
            dataService.addData("users", username, data);
            notify.showNotification(primaryStage,"Successfully Signed Up!!");
            System.out.println("User registered successfully");
            loginController.showLoginScene();

        } catch (Exception ex) {
            notify.showNotification(primaryStage,"Enter the details!!");
            ex.printStackTrace();
        }
    }
}
