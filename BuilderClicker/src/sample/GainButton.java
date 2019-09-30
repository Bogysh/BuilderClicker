package sample;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class GainButton extends Button {

    private static int moneyPerSec = 0;
    boolean checked = false;


    public GainButton(int cost) {

        setText(Integer.toString(cost));

        // setPadding(new Insets(10,10,10,10));

        setStyle(
                "-fx-background-color: #800060;" +
                        "-fx-border: none;" +
                        "-fx-color: #ffffff;" +
                        "-fx-text-align: center;" +
                        //   "-fx-display: inline-block;"+
                        " -fx-font-size: 16px;" +
                        // " -fx-margin: 10px 10px;"+
                        "-fx-border-radius: 75%;"


        );

        setListener();

    }

    public void setListener() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                moneyPerSec++;
                setDisabled(true);
                checked = true;
                System.out.println(moneyPerSec);

            }
        });
    }

    public boolean disabled() {
        return checked;
    }

    public void setFalse() {
        checked = false;
    }

    public static Integer getMoneyPS() {
        return moneyPerSec;
    }
}
