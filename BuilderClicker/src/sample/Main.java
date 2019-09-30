package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;




public class Main extends Application {

    private Scene gameScene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        GameViewManager gameManager1 = new GameViewManager(0);
        GameViewManager gameManager2 = new GameViewManager(1);
        GameViewManager gameManager3 = new GameViewManager(2);

        TabPane tabpane = new TabPane();

        Tab tab1 = new Tab("Warszawa");
        tab1.setContent(gameManager1.returnGamePane());

        Tab tab2 = new Tab("Gdańsk");
        tab2.setContent(gameManager2.returnGamePane());

        Tab tab3 = new Tab("Tokio");
        tab3.setContent(gameManager3.returnGamePane());

        tabpane.getTabs().addAll(tab1,tab2,tab3);


    gameScene = new Scene(tabpane,1024,728);
    primaryStage.setScene(gameScene);
    primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

