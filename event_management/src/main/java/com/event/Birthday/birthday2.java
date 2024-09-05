package com.event.Birthday;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.event.Usersession.UserSession;
import com.event.firebaseConfig.DataService;
import com.event.twilioVerification.VerificationPage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;

public class birthday2 {

    private static final int SLIDESHOW_INTERVAL = 2000; // milliseconds
    private int imageIndex = 0;

    private final String[] imageUrls = {
        "birthday/2.jpg",
        "birthday/1.jpg",
        "birthday/3.jpg"
    };

    public Scene createbirthday2Scene(Stage primaryStage) {

        // Header with back button and hotel name
        HBox header = createHeader(primaryStage);

        // Slideshow at the center
        ImageView imageView = new ImageView();
        imageView.setFitWidth(1100);
        imageView.setFitHeight(732);
        updateImage(imageView);

        // Create a Rectangle with rounded corners
        Rectangle clip = new Rectangle(1100, 732);
        clip.setArcWidth(30);
        clip.setArcHeight(30);
        imageView.setClip(clip);

        // Add drop shadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        imageView.setEffect(dropShadow);

        VBox slideshowBox = new VBox(imageView);
        slideshowBox.setAlignment(Pos.CENTER);
        slideshowBox.setPadding(new Insets(20));
        
        Text desc1 = new Text("• 11 East Street Cafe is a bar. restaurant. lounge. bakery. cafe. all day eatery... It's a fun place... Born in 2007. Maturing by the day.. Serving the best desserts in Pune. Amazing cocktails.. Alot of dishes here are the creation of the restaurant.");
        desc1.setFont(new Font("Comic Sans MS",35));
        desc1.setWrappingWidth(1600);
        desc1.setTextAlignment(TextAlignment.CENTER);

        HBox featureCards = new HBox(20);
        featureCards.setAlignment(Pos.CENTER);
        featureCards.setPadding(new Insets(20));

        VBox card1 = createFeatureCard("• People Approx", "40");
        VBox card2 = createFeatureCard("• Open Spaces ", "400 sq. ft");
        VBox card3 = createFeatureCard("• Covered Verandah", "2500 sq. ft");
        VBox card4 = createFeatureCard("• Price", "₹3000");

        featureCards.getChildren().addAll(card1, card2, card3, card4);

        VBox textBox = new VBox(desc1, featureCards);

        // Controls on the right side
        VBox controlsVBox = createControlsBox(primaryStage);

        // Combine rightVBox and controlsVBox
        VBox bottomVBox = new VBox(20, textBox, controlsVBox);
        bottomVBox.setPadding(new Insets(20));
        bottomVBox.setAlignment(Pos.TOP_CENTER);

        // Combine hotel name, slideshow, and bottomVBox into a single VBox
        VBox contentVBox = new VBox(20, slideshowBox, bottomVBox);
        contentVBox.setPadding(new Insets(20));
        contentVBox.setAlignment(Pos.TOP_CENTER);

        // Create a ScrollPane to wrap the contentVBox
        ScrollPane scrollPane = new ScrollPane(contentVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(20));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // Footer
        HBox footer = createFooter();

        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(scrollPane);
        root.setBottom(footer);

        Scene scene = new Scene(root, 1800, 1000);

        // Slideshow animation
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(SLIDESHOW_INTERVAL), event -> updateImage(imageView)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        return scene;
    }

    private void updateImage(ImageView imageView) {
        Image image = new Image(imageUrls[imageIndex]);
        imageView.setImage(image);
        imageIndex = (imageIndex + 1) % imageUrls.length;
    }

    private VBox createControlsBox(Stage primaryStage) {
        Label selectd1 = new Label("Select Dates");
        selectd1.setFont(new Font(30));
        selectd1.setStyle("-fx-font-weight: bold;");

        DatePicker startDate = new DatePicker();
        startDate.setPromptText("Start Date");

        DatePicker endDate = new DatePicker();
        endDate.setPromptText("End Date");

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Full Day", "Morning Section", "Evening Section");
        comboBox.setPromptText("Select Section");

       Button button = new Button("Book Now");
        button.setStyle("-fx-background-color: #ff4500; -fx-text-fill: white; -fx-font-size: 20px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #ff6347; -fx-text-fill: white; -fx-font-size: 20px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #ff4500; -fx-text-fill: white; -fx-font-size: 20px;"));

        button.setOnAction(e -> {
            String startDateValue = startDate.getValue().toString();
            String endDateValue = endDate.getValue().toString();
            String sectionValue = comboBox.getValue();

            Map<String, Object> bookingData = new HashMap<>();
            bookingData.put("startDate", startDateValue);
            bookingData.put("endDate", endDateValue);
            bookingData.put("section", sectionValue);

            saveBookingToFirestore(bookingData);

            // Save booking details in UserSession
            UserSession.getInstance().setBookingStartDate(startDateValue);
            UserSession.getInstance().setBookingEndDate(endDateValue);
            UserSession.getInstance().setBookingSection(sectionValue);

            VerificationPage verifyPage = new VerificationPage();
            Scene verifyScene = verifyPage.createVerificationScene(primaryStage);
            primaryStage.setScene(verifyScene);
        });

        VBox box = new VBox(20, selectd1, startDate, endDate, comboBox, button);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: #00000000 ; -fx-padding: 20;");
        return box;
    }

    private void saveBookingToFirestore(Map<String, Object> bookingData) {
        DataService dataService = new DataService();
        try {
            dataService.addData("bookings", bookingData.get("startDate") + "-" + bookingData.get("endDate"), bookingData);
            System.out.println("Booking saved successfully");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Button createBackButton(Stage primaryStage) {
        Button backBtn = new Button("Back");
        backBtn.setStyle(
            "-fx-background-color: white; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 20px; " +
            "-fx-padding: 10 20 10 20; " +
            "-fx-background-radius: 5px;" +
            "-fx-border-color: black;"
        );

        backBtn.setOnMouseEntered(e -> backBtn.setStyle(
            "-fx-background-color: lightgray; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 20px; " +
            "-fx-padding: 10 20 10 20; " +
            "-fx-background-radius: 5px;" +
            "-fx-border-color: black;"
        ));

        backBtn.setOnMouseExited(e -> backBtn.setStyle(
            "-fx-background-color: white; " +
            "-fx-text-fill: black; " +
            "-fx-font-size: 20px; " +
            "-fx-padding: 10 20 10 20; " +
            "-fx-background-radius: 5px;" +
            "-fx-border-color: black;"
        ));

        backBtn.setOnAction(e -> {
            birthday bd = new birthday();
            Scene wedScene = bd.createBirthdayPageScene(primaryStage);
            primaryStage.setScene(wedScene);
        });

        return backBtn;
    }

    private VBox createFeatureCard(String title, String title1) {
        Text cardTitle = new Text(title);
        cardTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        cardTitle.setFill(Color.web("#ffffff"));

        Text cardTitle1 = new Text(title1);
        cardTitle1.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        cardTitle1.setFill(Color.web("#ffffff"));

        VBox card = new VBox(75, cardTitle, cardTitle1);
        card.setAlignment(Pos.TOP_CENTER);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-background-color: #2980b9; -fx-background-radius: 10;");
        card.setPrefWidth(200);
        card.setPrefHeight(300); // Increased height
        card.setEffect(new DropShadow(10, Color.BLACK));

        card.setOnMouseEntered(e -> card.setStyle("-fx-background-color: #3498db; -fx-background-radius: 10;"));
        card.setOnMouseExited(e -> card.setStyle("-fx-background-color: #2980b9; -fx-background-radius: 10;"));

        return card;
    }

    private HBox createHeader(Stage primaryStage) {
        Button backBtn = createBackButton(primaryStage);

        Label hotelName = new Label("11 East Street Cafe");
        hotelName.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-font-family: 'Lucida Calligraphy';");
        
        HBox header = new HBox(600, backBtn, hotelName);
        header.setPadding(new Insets(10));
        header.setStyle("-fx-background-color: #B85042;");
        header.setPrefHeight(100);
        header.setAlignment(Pos.CENTER_LEFT);
        return header;
    }

    private HBox createFooter() {
        Label footerLabel = new Label("© 2024 MakeMyEventSpecial.in");
        footerLabel.setStyle("-fx-font-size: 20px;-fx-background-color: #A7BEAE;");
        
        HBox footer = new HBox(footerLabel);
        footer.setPadding(new Insets(10));
        footer.setStyle("-fx-background-color: #A7BEAE;");
        footer.setPrefHeight(50);
        footer.setAlignment(Pos.CENTER);
        return footer;
    }
}
