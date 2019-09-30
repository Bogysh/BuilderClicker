package sample;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;


public class GameViewManager {
    int cityname;

    String[] backgroundTab = new String[3];

    BackgroundImage backgroundImage;
    Background gameBackground;

    private BorderPane gamePane;

    private List<Floor> floorArray;
    private Label moneyLabel;
    private Label perSecLabel;
    private VBox container = new VBox();
    private VBox labelContainer;

    private  int money = 0;
    private int moneyPerSec = 0;

    private int cost = 40;
    private Button btn;




    public GameViewManager(int cityname) {
        addBackgrounds();
        this.cityname=cityname;
        gamePane = new BorderPane();
        setGameBackground();
        floorArray = new ArrayList<>();

        gamePane.setBottom(container);
        container.setLayoutX(300);


        moneyLabel = new Label("Zgromadzone pieniądze 0");
        perSecLabel = new Label("Zarabiasz 0");

        labelContainer = new VBox();
        labelContainer.getChildren().addAll(moneyLabel, perSecLabel);
        gamePane.setTop(labelContainer);


        addBuyButton();
        addFloor(10);
        gamePane.setPadding(new Insets(20, 20, 20, 20));

        perSecLabel.setLayoutX(100);

        getMoneyPerSec();


    }



    public Pane returnGamePane() {
        return gamePane;
    }


    public void addFloor(int amount) {

        floorArray.add(new Floor(amount));

        container.getChildren().add(floorArray.get(floorArray.size() - 1));

    }

    public void addBuyButton() {
        btn = new Button("Kup nowe pietro za 40");
        container.setAlignment(Pos.CENTER);
        container.getChildren().add(btn);
        btn.setOnAction(e -> {
                    if (money >= cost) {
                        money -= cost;
                        addFloor(10);
                        cost = (int) pow(floorArray.size(), 2) * 40;
                        btn.setText("Kup nowe pietro za " + cost);

                    }
                }
        );


    }


    public void getMoneyPerSec() {
        Service service = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        while (true) {
                            moneyPerSec = GainButton.getMoneyPS();
                            money += moneyPerSec;
                            Platform.runLater(() -> moneyLabel.setText("Zgromadzone pieniądze " + Integer.toString(money)));
                            Platform.runLater(() -> perSecLabel.setText("Zarabiasz " + Integer.toString(moneyPerSec)));
                            Thread.sleep(1000);
                        }

                    }
                };
            }
        };
        service.start();
    }
    private void setGameBackground(){
        backgroundImage = new BackgroundImage(new Image(backgroundTab[cityname],1024,768,false,true),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        gameBackground = new Background(backgroundImage);
        gamePane.setBackground(gameBackground);
    }

    public void addBackgrounds(){
        backgroundTab[0] = "/Resources/city.jpg";
        backgroundTab[1] = "/Resources/sea.jpg";
        backgroundTab[2] = "/Resources/japan.png";


    }
}

