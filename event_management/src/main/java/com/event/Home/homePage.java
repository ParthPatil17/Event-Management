package com.event.Home;
import com.event.Birthday.birthday;
import com.event.LandingPage.landingPage;
import com.event.Party.party; 
import com.event.Wedding.wedding;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class homePage {

    public Scene createHomePageScene(Stage hpStage) {
        // Initialization of stage
        hpStage.setTitle("Event Management");
        hpStage.setHeight(1080);
        hpStage.setWidth(1920);

        // Create buttons on navbar
        Button logout = createStyledButton("Log out");

        ImageView logo = new ImageView(new Image("logo.jpeg"));
        logo.setFitHeight(100); // Adjust height as needed
        logo.setPreserveRatio(true);

        logout.setOnAction(e -> {
            landingPage landingPage = new landingPage();
            Scene landingScene = landingPage.createLandingPageScene(hpStage);
            hpStage.setScene(landingScene);
        });
       
     
        HBox logoImg = new HBox(logo);

        HBox buttons = new HBox(40,logout);
        buttons.setAlignment(Pos.CENTER);

        // Create an HBox for the navbar and set its style and height
        HBox navbar = new HBox(1400, logoImg,buttons);
        navbar.setPrefHeight(70);
        navbar.setPrefWidth(1920);
        //navbar.setStyle("-fx-background-color: #B85042;");
        //navbar.setAlignment(Pos.CENTER_RIGHT);
        navbar.setPadding(new Insets(10, 40, 10, 20));

        // Create VBoxes for Wedding, Birthday, and Party
        VBox weddingBox = createVBox("Wedding", "wedding2.jpg", hpStage);
        VBox birthdayBox = createVBox("Birthday", "birthday.jpg", hpStage);
        VBox partyBox = createVBox("Party", "party.jpg", hpStage);

        // Create an HBox to hold the VBoxes
        HBox sections = new HBox(60, weddingBox, birthdayBox, partyBox);
        sections.setAlignment(Pos.CENTER); // Center the contents of the HBox
        sections.setPadding(new Insets(20));

        // Label for text
        Label lb = new Label("Create Remarkable Memories With Us");
        lb.setFont(Font.font("Arial", FontWeight.BOLD, 50));

        // for the text
        VBox textBox = new VBox(20, lb, createSubtitleLabel());
        textBox.setAlignment(Pos.CENTER);

        VBox mainBox = new VBox(30);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.getChildren().addAll(textBox, sections);

        BorderPane root = new BorderPane();
        root.setTop(navbar);
        root.setCenter(mainBox);

        StackPane mainPane = new StackPane();
        mainPane.getChildren().addAll(createBackgroundImage(), root);

        // Create a TranslateTransition
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(2000), sections);
        translateTransition.setFromX(0);
        translateTransition.setToX(200);
        translateTransition.setCycleCount(2);
        translateTransition.setAutoReverse(true);
        translateTransition.play();

        // Create a scene and return it
        Scene sc = new Scene(mainPane, 1920, 1080);
        sc.setFill(Color.rgb(219, 226, 233));
        return sc;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setAlignment(Pos.CENTER);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        button.setStyle(
            "-fx-background-color: white; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 20px; " +
            "-fx-padding: 10 20 10 20; " +
            "-fx-background-radius: 5px;"
        );

        // Add hover effect for the button
        button.setOnMouseEntered(e -> button.setStyle(
            "-fx-background-color: lightgray; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 20px; " +
            "-fx-padding: 10 20 10 20; " +
            "-fx-background-radius: 5px;"
        ));

        button.setOnMouseExited(e -> button.setStyle(
            "-fx-background-color: white; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 20px; " +
            "-fx-padding: 10 20 10 20; " +
            "-fx-background-radius: 5px;"
        ));

        return button;
    }

    private VBox createVBox(String buttonText, String imagePath, Stage stage) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitHeight(600);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);

        // Set clip with rounded corners
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(50); // adjust the arc width for circular corners
        clip.setArcHeight(50); // adjust the arc height for circular corners
        imageView.setClip(clip);

        // Apply a drop shadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10.0);
        dropShadow.setOffsetX(5.0);
        dropShadow.setOffsetY(5.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        imageView.setEffect(dropShadow);

        Button button = new Button(buttonText);
        button.setStyle(
            "-fx-background-color: white; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 10 20 10 20; " +
            "-fx-background-radius: 5px;"
        );

        // Add hover effect for the button
        button.setOnMouseEntered(e -> button.setStyle(
            "-fx-background-color: lightgray; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 20px; " +
            "-fx-padding: 10 20 10 20; " +
            "-fx-background-radius: 5px;"
        ));

        button.setOnMouseExited(e -> button.setStyle(
            "-fx-background-color: white; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 20px; " +
            "-fx-padding: 10 20 10 20; " +
            "-fx-background-radius: 5px;"
        ));

        button.setOnAction(e -> {
            // Handle the button click event
            switch (buttonText) {
                case "Wedding":
                    wedding weddingPage = new wedding();
                    Scene weddingScene = weddingPage.createWeddingPageScene(stage);
                    stage.setScene(weddingScene);
                    break;
                case "Birthday":
                    birthday birthdayPage = new birthday();
                    Scene birthdayScene = birthdayPage.createBirthdayPageScene(stage);
                    stage.setScene(birthdayScene);
                    break;
                case "Party":
                    party partyPage = new party(); // Assuming there's a Party class
                    Scene partyScene = partyPage.createPartyPageScene(stage);
                    stage.setScene(partyScene);
                    break;
            }
        });

        VBox vbox = new VBox(20, imageView, button);
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

    private ImageView createBackgroundImage() {
        Image image = new Image("background2.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(1920);
        imageView.setFitHeight(1080);
        imageView.setOpacity(0.7);
        return imageView;
    }

    private Label createSubtitleLabel() {
        Label lb2 = new Label("We make your events smart & impactful by personalized event management services.");
        lb2.setFont(new Font(35));
        return lb2;
    }
}
