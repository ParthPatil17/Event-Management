package com.event.Birthday;

import com.event.Home.homePage;

import com.event.Birthday.birthday1;
import com.event.Birthday.birthday2;
import com.event.Birthday.birthday3;
import com.event.Birthday.birthday4;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class birthday {

    public Scene createBirthdayPageScene(Stage birthdayStage) {
        birthdayStage.setHeight(1080);
        birthdayStage.setWidth(1920);
        birthdayStage.setTitle("Birthday");

        // Create the Back button
        Button backButton = createStyledButton("Back");
        backButton.setOnAction(e -> {
            homePage homePage = new homePage();
            Scene homeScene = homePage.createHomePageScene(birthdayStage);
            birthdayStage.setScene(homeScene);
        });


        Label title = new Label("Birthday Venues");
        //title.setFont(new Font(35));
        title.setStyle("-fx-font-size: 35px; -fx-font-weight: bold;");

        HBox navBox = new HBox(750,backButton, title);
        navBox.setAlignment(Pos.CENTER_LEFT);
        navBox.setPrefHeight(100);
        navBox.setStyle("-fx-background-color: #B85042; -fx-padding: 10;");

        // Create a VBox to hold the hotels
        VBox vbox = new VBox(40);
        vbox.setLayoutY(100);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(createHotel(birthdayStage,"The Voyage", "birthday/hotel1.jpg", "Located amidst the Pune city, The Voyage welcomes its guests with a multi cuisine restaurant, an innovative co-working space and a home-like hostel . The Voyage, themed with victorious spirit and adventures of sea life, settles you into the calming and robust lifestyle of an undestined traveller concluding in a range of perspectives."),

                                  createHotel(birthdayStage,"11 East Street Cafe", "birthday/hotel2.jpg", "11 East Street Cafe is a bar. restaurant. lounge. bakery. cafe. all day eatery... It's a fun place... Born in 2007. Maturing by the day.. Serving the best desserts in Pune. Amazing cocktails.. Alot of dishes here are the creation of the restaurant."),

                                  createHotel(birthdayStage,"Irani cafe", "birthday/hotel3.jpg", "We promise you a wholesome experience with delicious and healthy meals at Irani Cafe, a trendy representation of Iran's authenticity. With your piping hot Irani Chai, try tasty and pocket friendly dishes for breakfast, brunch, lunch, snacks and dinner. With a belief in quality and quantity, our doors open for you at 6:00 AM every morning!"),

                                  createHotel(birthdayStage,"Starbucks FC Road", "birthday/hotel4.jpg", "Starbucks Corporation is an American multinational chain of coffeehouses and roastery reserves headquartered in Seattle, Washington. Founded in 1971 by Jerry Baldwin, Zev Siegl, and Gordon Bowker, Starbucks has grown to become one of the world's most recognizable brands in the coffee industry"));

        HBox mainBox = new HBox(vbox);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setLayoutY(100);
        mainBox.setLayoutX(400);

        

        ScrollPane scrollPane = new ScrollPane(mainBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefSize(1920, 1080);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        HBox footer = new HBox();
        footer.setPrefHeight(50);
        footer.setStyle("-fx-background-color: #A7BEAE;");
        footer.setAlignment(Pos.CENTER);
        Label footerText = new Label("© 2024 Runtime Error");
        footerText.setStyle(" -fx-font-size: 20px;");
        footer.getChildren().add(footerText);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(navBox);
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(footer);

        // Create a StackPane for background image and content
        StackPane stackPane = new StackPane();
        
        stackPane.getChildren().addAll( borderPane);

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

    private HBox createHotel(Stage birthdayStage,String hotelName, String imagePath, String description) {
        // Create an ImageView for the hotel image
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitHeight(600);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);

        // Create a Text element for the hotel name
        Text hotelNameText = new Text(hotelName);
        hotelNameText.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        // Create a Text element for the hotel description
        Text descriptionText = new Text(description);
        descriptionText.setWrappingWidth(600); // Set a maximum width to wrap text
        descriptionText.setStyle("-fx-font-size: 18px;");

        // Create a VBox for the hotel description
        VBox descriptionBox = new VBox(10);
        descriptionBox.setAlignment(Pos.CENTER_LEFT);
        descriptionBox.getChildren().addAll(hotelNameText, descriptionText);

        // Create an HBox to hold the image and the description
        HBox hotelBox = new HBox(50, imageView, descriptionBox);
        hotelBox.setAlignment(Pos.CENTER);
        hotelBox.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: lightgray; -fx-border-width: 1;");

        hotelBox.setOnMouseClicked(event -> {
            // Handle the click action here, for example, opening a new window or showing details
             switch (hotelName) {
                case "The Voyage":
                    birthday1 wedding1Page = new birthday1();
                    Scene birthday1Scene = wedding1Page.createbirthday1Scene(birthdayStage);
                    birthdayStage.setScene(birthday1Scene);
                    break;
                case "11 East Street Cafe":
                    birthday2 birthday2Page = new birthday2();
                    Scene birthday2Scene = birthday2Page.createbirthday2Scene(birthdayStage);
                    birthdayStage.setScene(birthday2Scene);
                    break;
                case "Irani cafe":
                    birthday3 birthday3Page = new birthday3();
                    Scene birthday3Scene = birthday3Page.createbirthday3Scene(birthdayStage);
                    birthdayStage.setScene(birthday3Scene);
                    break;
                case "Starbucks FC Road":
                    birthday4 birthday4Page = new birthday4();
                    Scene birthday4Scene = birthday4Page.createbirthday4Scene(birthdayStage);
                    birthdayStage.setScene(birthday4Scene);
                    break;
                default:
                    // Handle default case if needed
                    break;
            }
           
        });

        return hotelBox;
    }
}
