package com.event.LandingPage;

import java.util.ArrayList;
import java.util.List;

import com.event.SignIn.LogInController;
import com.event.SignUp.SignUpController; // Import SignUpController
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class landingPage extends Application {

    private List<Image> images = new ArrayList<>();
    private int currentImageIndex = 0;
    private ImageView imageView = new ImageView();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene landingScene = createLandingPageScene(primaryStage);
        primaryStage.setScene(landingScene);
        primaryStage.show();
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("logo.jpeg"));
    }

    public Scene createLandingPageScene(Stage primaryStage) {
        // Initialization of stage
        primaryStage.setTitle("Landing Page");
        primaryStage.setHeight(1080);
        primaryStage.setWidth(1920);

        // Load images for slideshow
        images.add(new Image("lp2.jpg"));
        images.add(new Image("lp1.jpg"));
        images.add(new Image("lp5.jpg"));
        images.add(new Image("landingpage3.jpg"));
        images.add(new Image("landingpage5.jpg"));
        images.add(new Image("lp4.jpg"));

        // Set initial image
        imageView.setImage(images.get(currentImageIndex));
        imageView.setFitWidth(1920);
        imageView.setFitHeight(1080);

        // Apply blur effect to the ImageView
        GaussianBlur blur = new GaussianBlur();
        blur.setRadius(9);  // You can adjust the radius as needed
        imageView.setEffect(blur);

        // Layout for main content
        VBox mainLayout = new VBox(30);
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setLayoutY(10);
        mainLayout.setPrefSize(1000, 1200); // Adjust height to accommodate both background images

        // Middle box (mvb)
        VBox mvb = new VBox(20);
        mvb.setPrefHeight(700);
        mvb.setPrefWidth(700);
        mvb.setPadding(new Insets(50));
        mvb.setAlignment(Pos.CENTER_LEFT); // Align content to the left
        mvb.setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 0, 0, 0.6), // Black transparent background
                CornerRadii.EMPTY,
                Insets.EMPTY
        )));

        // Title
        Label title = new Label("MakeMyEventSpecial.in");
        title.setWrapText(true);
        title.setMaxWidth(600);
        title.setStyle("-fx-font-size: 50px; -fx-text-fill: white; -fx-font-weight: bold");

        // Welcome message
        Label taglinelb = new Label("'Crafting Unforgettable Experiences'");
        taglinelb.setWrapText(true);
        taglinelb.setMaxWidth(600);
        taglinelb.setStyle("-fx-text-fill:white;-fx-font-weight: bold;-fx-font-size: 30px");
        Label welcomeMessage = new Label("I am excited to have all of you here.Discover unforgettable Destinations to celebrate life's special moments in Style!!");
        welcomeMessage.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
        welcomeMessage.setWrapText(true);
        welcomeMessage.setMaxWidth(600);

        // Learn more button
        Button learnMore = createStyledButton(" Learn More ");
        learnMore.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Learn More button clicked");
                SignUpController signUpController = new SignUpController(new LogInController(primaryStage)); // Pass LogInController to SignUpController
                Scene signupScene = signUpController.createSignupScene(primaryStage);
                primaryStage.setScene(signupScene);
                primaryStage.setTitle("Sign Up");
                primaryStage.show();
            }
        });

        mvb.getChildren().addAll(title, taglinelb, welcomeMessage, learnMore);

        // Side image
        Image image1 = new Image("sideimage2.jpg");
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(500);
        imageView1.setFitHeight(700);

        // Create a Rectangle with rounded corners
        Rectangle clip = new Rectangle(500, 700);
        clip.setArcWidth(30);  // Adjust these values for the desired roundness
        clip.setArcHeight(30);

        // Set the Rectangle as the clipping mask for the ImageView
        imageView1.setClip(clip);

        // Add drop shadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        imageView1.setEffect(dropShadow);

        // HBox for mvb and side image at the same level
        HBox contentBox = new HBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPrefSize(600, 700);
        contentBox.getChildren().addAll(imageView1, mvb);

        mainLayout.getChildren().add(contentBox);

        // Create a stack pane for overlaying content
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(imageView, mainLayout);

        // Create a scene and set it on the stage
        Scene sc = new Scene(stackPane, 1920, 1080);
        sc.setFill(Color.rgb(219, 226, 233));

        // Create a timeline for the slideshow
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            currentImageIndex = (currentImageIndex + 1) % images.size();
            imageView.setImage(images.get(currentImageIndex));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely
        timeline.play();

        return sc;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setAlignment(Pos.CENTER);
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
}
