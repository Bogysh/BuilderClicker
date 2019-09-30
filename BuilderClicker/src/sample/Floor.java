package sample;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Floor extends HBox {

    GainButton[] buttonTable;

    private static final String  background_Path ="/Resources/Wall2.png";
    BackgroundImage backgroundImage;
    Background background;
    int floorMoney = 0;
    Label moneyLabel;
    Service service;
    int amount;


    public Floor(int amount) {
        this.amount = amount;


        this.setAlignment(Pos.CENTER);
        setPrefHeight(80);
        setPrefWidth(700);
        setSpacing(20);
        setBackground();
        setButtons(amount);
        moneyLabel = new Label("Pietro zarabia 0");
        this.getChildren().add(moneyLabel);

        produce(amount);


    }

    public void setButtons(int x) {
        buttonTable = new GainButton[x];

        for (int i = 0; i < x - 1; i++) {
            buttonTable[i] = new GainButton(10);

            this.getChildren().add(buttonTable[i]);

        }
        buttonTable[x - 1] = new GainButton(20);
        this.getChildren().add(buttonTable[x - 1]);

    }


    public void produce(int amount) {

        service = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        int buttonsPressed = 0;
                        while (buttonsPressed < amount) {

                            for (int i = 0; i < buttonTable.length; i++) {
                                if (buttonTable[i].disabled()) {
                                    floorMoney++;
                                    buttonTable[i].setFalse();
                                    System.out.println(floorMoney);
                                    buttonsPressed++;
                                    Platform.runLater(() -> moneyLabel.setText("Pietro zarabia "+String.valueOf(floorMoney)));
                                }
                            }
                        }
                        System.out.println("Koniec");
                        return 0;
                    }
                };
            }

        };
        service.start();
    }

    private void setBackground(){
        backgroundImage = new BackgroundImage(new Image(background_Path,900,80,false,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        background = new Background(backgroundImage);
        this.setBackground(background);
    }


}

