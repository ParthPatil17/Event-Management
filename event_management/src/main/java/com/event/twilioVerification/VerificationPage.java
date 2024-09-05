package com.event.twilioVerification;

import com.event.notification.*;
import com.event.Home.homePage;
import com.event.Usersession.UserSession;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.rest.api.v2010.account.Message;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VerificationPage {

    // Enter your own details
    public static final String ACCOUNT_SID = "***Enter your Account SID***";
    public static final String AUTH_TOKEN = "***Enter your Auth_token***";
    public static final String VERIFY_SERVICE_SID = "***Enter your service_sid***"; 

    public Scene createVerificationScene(Stage verifyStage) {
        
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // back button
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #ff6347; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-size: 20px;");
        backButton.setOnAction(e -> {
            homePage wed1 = new homePage();
            Scene wed1Scene = wed1.createHomePageScene(verifyStage);
            verifyStage.setScene(wed1Scene);
        });

        Label username = new Label("Name:");
        username.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-size: 20px;");
        GridPane.setConstraints(username, 0, 0);

        TextField userField = new TextField();
        userField.setPromptText("Enter your name");
        userField.setStyle("-fx-padding: 5px; -fx-font-size: 20px;");
        GridPane.setConstraints(userField, 1, 0);


        Label phoneLabel = new Label("Phone Number:");
        phoneLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-size: 20px;");
        GridPane.setConstraints(phoneLabel, 0, 1);

        TextField phoneInput = new TextField();
        phoneInput.setPromptText("+91XXXXXXXXXX");
        phoneInput.setStyle("-fx-padding: 5px; -fx-font-size: 20px;");
        GridPane.setConstraints(phoneInput, 1, 1);

        Button sendCodeButton = new Button("Send Code");
        sendCodeButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 20px;");

        GridPane.setConstraints(sendCodeButton, 1, 2);

        Label codeLabel = new Label("Verification Code:");
        codeLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-font-size: 20px;");
        GridPane.setConstraints(codeLabel, 0, 3);

        TextField codeInput = new TextField();
        codeInput.setStyle("-fx-padding: 5px; -fx-font-size: 20px;");
        codeInput.setPromptText("Verification Code");
        GridPane.setConstraints(codeInput, 1, 3);

        Button verifyCodeButton = new Button("Verify Code");
        verifyCodeButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-font-size: 20px;");
        GridPane.setConstraints(verifyCodeButton, 1, 4);

        grid.getChildren().addAll(username,userField,phoneLabel, phoneInput, sendCodeButton, codeLabel, codeInput, verifyCodeButton);
        grid.setAlignment(Pos.CENTER);

        VBox container = new VBox(grid);
        container.setStyle("-fx-background-color:  #F9E795; -fx-border-color: #cccccc; -fx-border-width: 2px; -fx-padding: 10px; -fx-background-radius: 10px; -fx-border-radius: 10px;");
        container.setAlignment(Pos.CENTER);
        container.setLayoutX(700);
        container.setLayoutY(400);
        container.setPrefSize(500,400);

        // hbox for back button
        HBox topHBox = new HBox();
        topHBox.setAlignment(Pos.TOP_LEFT);
        topHBox.getChildren().add(backButton);

        BorderPane root = new BorderPane();
        root.setTop(topHBox);
    
        Group gp = new Group(root,container);

        sendCodeButton.setOnAction(e -> {
            String phoneNumber = phoneInput.getText();
            String usernameInput = userField.getText(); // get username input
            sendVerificationCode(phoneNumber, usernameInput);
        });

        verifyCodeButton.setOnAction(e -> {
            String phoneNumber = phoneInput.getText();
            String verificationCode = codeInput.getText();
            String usernameInput = userField.getText(); // get username input
            verifyCode(phoneNumber, verificationCode, usernameInput);
            homePage wed1 = new homePage();
            Scene wed1Scene = wed1.createHomePageScene(verifyStage);
            verifyStage.setScene(wed1Scene);
            notify.showNotification(verifyStage,"Booking Confirmed!!");
        });

        return new Scene(gp, 400, 300);
    }

    private void sendVerificationCode(String phoneNumber, String username) {
        Verification verification = Verification.creator(
                VERIFY_SERVICE_SID,
                phoneNumber,
                "sms"
        ).create();

        System.out.println("Sent verification code: " + verification.getSid());
    }

    private void verifyCode(String phoneNumber, String code, String username) {
        try {
            VerificationCheck verificationCheck = VerificationCheck.creator(
                    VERIFY_SERVICE_SID
            ).setTo(phoneNumber)
             .setCode(code)
             .create();

            if ("approved".equals(verificationCheck.getStatus())) {
                System.out.println("Verification successful!");
                // Retrieve booking details from UserSession
                String startDate = UserSession.getInstance().getBookingStartDate();
                String endDate = UserSession.getInstance().getBookingEndDate();
                String section = UserSession.getInstance().getBookingSection();

                // Construct the message body with username and booking details
                String messageBody = "Hello " + username + ", your booking from " +
                        startDate + " to " + endDate + " (" + section + ") is successfully confirmed!";
                
                sendSms(phoneNumber, messageBody);
            } else {
                System.out.println("Verification failed!");
            }
        } catch (Exception e) {
            System.err.println("Error verifying code: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void sendSms(String to, String body) {
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber("***Enter Twilio Phone Number***"), // Enter Your own Twilio Number
                body
        ).create();

        System.out.println("SMS sent: " + message.getSid());
    }
}
