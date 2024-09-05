package com.event.Party;


import com.event.Home.homePage;

import com.event.Party.party1;
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

public class party {

    public Scene createPartyPageScene(Stage partyStage) {
        partyStage.setHeight(1080);
        partyStage.setWidth(1920);
        partyStage.setTitle("Party");

        // Create the Back button
        Button backButton = createStyledButton("Back");
        backButton.setOnAction(e -> {
            homePage homePage = new homePage();
            Scene homeScene = homePage.createHomePageScene(partyStage);
            partyStage.setScene(homeScene);
        });


        Label title = new Label("Party Venues");
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
        vbox.getChildren().addAll(createHotel(partyStage,"Alto Vino", "party/party1.jpg", "Savor an extensive spread of Italian delicacies by chef Huber, paired with an outstanding collection of premium spirits and a connoisseur’s selection of international wines at the award winning Italian restaurant of Pune; Alto Vino."),

                                  createHotel(partyStage,"Pune 14", "party/party2.jpg", "Pune 14 is an all day dining, buffet venue, located at the lobby level of Hyatt Regency Pune. It is a vibrant and colorful market-style restaurant, serving Asian, Western and Indian cuisines from interactive show kitchens. Known for its lively street style Sunday Brunches, the venue also features exciting food festivals from time to time."),

                                  createHotel(partyStage,"Zeta", "party/party3.jpg", "Zeta is Hyatt Regency Pune’s latest offering that is decidedly different. While deliberately moving away from fine dining, Zeta maintains a unique persona of fun, casual and style with its own brand of spontaneity. With a décor reminiscent of cozy European bistro that favors cozy nooks, hardwood floor, exposed walls and coarse murals, Zeta creates an unpretentious ambiance augmented by a creative menu."),

                                  createHotel(partyStage,"Senses", "party/party4.jpg", "Sen5es, the multi-cuisine all day diner welcomes you with exceptional restaurant dining here in Koregaon Park. Savour perfectly prepared international cuisine in an inviting, stylish atmosphere. In addition, we feature a 24-hour coffee shop and a vibrant live kitchen setting."));

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

    private HBox createHotel(Stage partyStage,String hotelName, String imagePath, String description) {
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
            switch (hotelName) {
                case "Alto Vino":
                    party1 party1Page = new party1();
                    Scene party1Scene = party1Page.createparty1Scene(partyStage);
                    partyStage.setScene(party1Scene);
                    break;
                case "Pune 14":
                    party2 party2Page = new party2();
                    Scene party2Scene = party2Page.createparty2Scene(partyStage);
                    partyStage.setScene(party2Scene);
                    break;
                case "Zeta":
                    party3 party3Page = new party3();
                    Scene party3Scene = party3Page.createparty3Scene(partyStage);
                    partyStage.setScene(party3Scene);
                    break;
                case "Senses":
                    party4 party4Page = new party4();
                    Scene party4Scene = party4Page.createparty4Scene(partyStage);
                    partyStage.setScene(party4Scene);
                    break;
                default:
                    // Handle default case if needed
                    break;
            }
           
        });

        return hotelBox;
    }
}
