package com.event.Wedding;

import com.event.Home.homePage;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class wedding {

    public Scene createWeddingPageScene(Stage wedStage) {
        wedStage.setHeight(1080);
        wedStage.setWidth(1920);
        wedStage.setTitle("Wedding");

        // Create the Back button
        Button backButton = createStyledButton("Back");
        backButton.setOnAction(e -> {
            homePage homePage = new homePage();
            Scene homeScene = homePage.createHomePageScene(wedStage);
            wedStage.setScene(homeScene);
        });

        Label title = new Label("Wedding Venues");
        title.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        HBox navBox = new HBox(750, backButton, title);
        navBox.setAlignment(Pos.CENTER_LEFT);
        navBox.setPrefHeight(100);
        navBox.setStyle("-fx-background-color: #B85042; -fx-padding: 10;");

        // Create a VBox to hold the hotels
        VBox vbox = new VBox(40);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(
                createHotel(wedStage, "Marigold Banquets N Conventions", "wedding/hotel1.jpg",
                        "Marigold Banquets N Conventions is a venue located in the city of Pune and can be the perfect venue to host any and every one of your special occasions in life that needs and grand celebrations. For weddings, one thing that every couple keeps thinking about is finding the right venue to host all of their various wedding functions with ease and comfort with an assurance that it would be the most beautiful experience in their lives. If you are looking for a good venue in the city for hosting your wedding festivities, then your search can end here."),
                createHotel(wedStage, "Rajmudra Banquet", "wedding/hotel2.jpg",
                        "Glamoura Banquet, Marunji, Pimpri-Chinchwad (PCMC), Pune is a perfect place for any celebration. It offers an idyllic setting to host wedding functions, receptions, mehndi, sangeet, social gatherings, birthday parties, get-togethers, and corporate meets. This venue is ideal for large as well as small gatherings, with a capacity up to 1000 pax. Hotel Rajmudra Banquet and Lawns is equipped with modern amenities, luminous lights, comfortable seating space, and requires minimal to no decoration."),
                createHotel(wedStage, "Kapila Resorts", "wedding/hotel3.jpg",
                        "Nestled in Pune, Kapila Resorts serves as a picturesque wedding venue with meticulously maintained lush green event spaces, creating a perfect setting for auspicious occasions and celebrations. Offering a variety of hospitality services and venue options, this resort is designed to meet all your requirements, ensuring your nuptial ceremony is a truly memorable experience. Surrounded by scenic beauty, Kapila Resorts excels in efficient event planning, allowing you to host a luxurious, stunning, and unforgettable wedding celebration."),
                createHotel(wedStage, "The Royal Lake", "wedding/hotel4.jpg",
                        "The Royal Lake is a venue located in Pune. Many things take place in a grand wedding ceremony, and one thing that makes the look of the ceremony a gleaming one is the astounding location. If you are planning one such ceremony and looking for a good and amazing location, then The Royal Lake is the choice you should make as they are a one-stop solution for all your wedding-related needs and requirements and make sure you are equipped with truckloads of happy services and facilities. Many more. It is set up to provide an elegant and superior banqueting space to cater to the varied requirements of clients."));

        HBox mainBox = new HBox(vbox);
        mainBox.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(mainBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        

        // Create the footer
        HBox footer = new HBox();
        footer.setPrefHeight(50);
        footer.setStyle("-fx-background-color: #A7BEAE;");
        footer.setAlignment(Pos.CENTER);
        Label footerText = new Label("© 2024 MakeMyEventSpecial.in");
        footerText.setStyle("-fx-font-size: 20px;");
        footer.getChildren().add(footerText);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(navBox);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(footer);

        // Create a StackPane for background image and content
        StackPane stackPane = new StackPane();
        ImageView backgroundImageView = new ImageView(new Image("back1.jpg"));
        backgroundImageView.setFitHeight(1080);
        backgroundImageView.setFitWidth(1920);
        backgroundImageView.setPreserveRatio(true);
        stackPane.getChildren().addAll(backgroundImageView, borderPane);

        Scene sc = new Scene(stackPane, 1920, 1080);

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
                        "-fx-background-radius: 5px;");

        button.setOnMouseEntered(e -> button.setStyle(
                "-fx-background-color: lightgray; " +
                        "-fx-text-fill: black; " +
                        "-fx-font-size: 20px; " +
                        "-fx-padding: 10 20 10 20; " +
                        "-fx-background-radius: 5px;"));

        button.setOnMouseExited(e -> button.setStyle(
                "-fx-background-color: white; " +
                        "-fx-text-fill: black; " +
                        "-fx-font-size: 20px; " +
                        "-fx-padding: 10 20 10 20; " +
                        "-fx-background-radius: 5px;"));

        return button;
    }

    private HBox createHotel(Stage wedStage, String hotelName, String imagePath, String description) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitHeight(600);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);

        Text hotelNameText = new Text(hotelName);
        hotelNameText.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        Text descriptionText = new Text(description);
        descriptionText.setWrappingWidth(600);
        descriptionText.setStyle("-fx-font-size: 18px;");

        VBox descriptionBox = new VBox(10);
        descriptionBox.setAlignment(Pos.CENTER_LEFT);
        descriptionBox.getChildren().addAll(hotelNameText, descriptionText);

        HBox hotelBox = new HBox(50, imageView, descriptionBox);
        hotelBox.setAlignment(Pos.CENTER);
        hotelBox.setStyle(
                "-fx-background-color: white; -fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1;");

        hotelBox.setOnMouseClicked(event -> {
            switch (hotelName) {
                case "Marigold Banquets N Conventions":
                    wedding1 wedding1Page = new wedding1();
                    Scene wedding1Scene = wedding1Page.createwedding1Scene(wedStage);
                    wedStage.setScene(wedding1Scene);
                    break;
                case "Rajmudra Banquet":
                    wedding2 wedding2Page = new wedding2();
                    Scene wedding2Scene = wedding2Page.createwedding2Scene(wedStage);
                    wedStage.setScene(wedding2Scene);
                    break;
                case "Kapila Resorts":
                    wedding3 wedding3Page = new wedding3();
                    Scene wedding3Scene = wedding3Page.createwedding3Scene(wedStage);
                    wedStage.setScene(wedding3Scene);
                    break;
                case "The Royal Lake":
                    wedding4 wedding4Page = new wedding4();
                    Scene wedding4Scene = wedding4Page.createwedding4Scene(wedStage);
                    wedStage.setScene(wedding4Scene);
                    break;
                default:
                    // Handle default case if needed
                    break;
            }
        });

        return hotelBox;
    }
}

