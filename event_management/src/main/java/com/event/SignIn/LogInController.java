package com.event.SignIn;

import java.util.concurrent.ExecutionException;
import com.event.Home.homePage;
import com.event.SignUp.SignUpController;
import com.event.firebaseConfig.DataService;
import com.event.notification.notify;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LogInController {

    private Stage primaryStage;
    private Scene loginScene;
    private DataService dataService;
    public static String key;

    // Constructor
    public LogInController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        dataService = new DataService();
        initLoginScene();
    }

    private void initLoginScene() {

        // Username
        Label userLabel = new Label("Username:");
        userLabel.setStyle("-fx-font-weight: bold");
        userLabel.setFont(new Font(25));
        userLabel.setStyle("-fx-text-fill: white;");

        TextField userTextField = new TextField();
        userTextField.setStyle("-fx-pref-width: 350;");

        // Password
        Label passLabel = new Label("Password:");
        passLabel.setStyle("-fx-font-weight: bold");
        passLabel.setFont(new Font(25));
        passLabel.setStyle("-fx-text-fill: white;");

        PasswordField passField = new PasswordField();
        passField.setStyle("-fx-pref-width: 350;");

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleLogin(userTextField.getText(), passField.getText());
                userTextField.clear();
                passField.clear();
            }
        });
        loginButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14px;");

        // Signup button
        Button signupButton = new Button("Signup");
        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSignupScene();
                userTextField.clear();
                passField.clear();
            }
        });
        signupButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 14px;");

        // VBox contain userLabel, userTextField
        VBox fieldBox1 = new VBox(10, userLabel, userTextField);
        fieldBox1.setMaxSize(300, 30);

        // VBox contain passLabel, passField
        VBox fieldBox2 = new VBox(10, passLabel, passField);
        fieldBox2.setMaxSize(300, 30);

        // HBox contain both buttons
        HBox buttonBox = new HBox(50, loginButton, signupButton);
        buttonBox.setMaxSize(350, 30);
        buttonBox.setAlignment(Pos.CENTER);

        // VBox contain all boxes
        VBox vbox = new VBox(20, fieldBox1, fieldBox2, buttonBox);
        vbox.setAlignment(Pos.CENTER);

        // Animate the text fields and button box
        
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().addAll(vbox);

        // Set the background image
        stackPane.setStyle("-fx-background-image: url('loginControl.jpg'); -fx-background-size: cover;");

        // Create the scene
        loginScene = new Scene(stackPane, 600, 600);
    }


    public Scene getLoginScene() {
        return loginScene;
    }

    public void showLoginScene() {
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }

    private void handleLogin(String username, String password) {
        try {
            if (dataService.authenticateUser(username, password)) {
                key = username;
                showHomePageScene();
                notify.showNotification(primaryStage, "Login Successfully!!");
                primaryStage.setTitle("Home Page");
                primaryStage.show();
            } else {
                notify.showNotification(primaryStage, "Invalid Username or Password!!");
                System.out.println("Invalid credentials");
                key = null;
            }
        } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void showSignupScene() {
        SignUpController signupController = new SignUpController(this);
        Scene signupScene = signupController.createSignupScene(primaryStage);
        primaryStage.setScene(signupScene);
        primaryStage.setTitle("Signup");
        primaryStage.show();
    }

    private void showHomePageScene() {
        homePage homePageContoller = new homePage();
        Scene homScene = homePageContoller.createHomePageScene(primaryStage);
        primaryStage.setScene(homScene);
        primaryStage.setTitle("Home Page");
        primaryStage.show();
    }
}
