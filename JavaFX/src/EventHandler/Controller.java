package EventHandler;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField nameField;
    @FXML
    private Button helloButton;
    @FXML
    private Button byeButton;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Label label;

    @FXML
    public void initialize(){
        helloButton.setDisable(true);
        byeButton.setDisable(true);
    }

    @FXML
    public void onButtonClicked(ActionEvent e){
        if (e.getSource().equals(helloButton))
        System.out.println("Hello, " + nameField.getText());
        else if (e.getSource().equals(byeButton))
            System.out.println("Bye, " + nameField.getText());

        Runnable task = new Runnable(){
            @Override
            public void run() {
                try {
                    String s = Platform.isFxApplicationThread() ? "UI thread" : "Background thread";
                    System.out.println("Sleeping on " + s);
                    Thread.sleep(3000);
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run() {
                            String s = Platform.isFxApplicationThread() ? "UI thread" : "Background thread";
                            System.out.println("Updating label on " + s);
                            label.setText("We did something!");
                        }
                    });

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        };

        new Thread(task).start();

        if (checkBox.isSelected()) {
            nameField.clear();
            helloButton.setDisable(true);
            byeButton.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased(){
        String text = nameField.getText();
        boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
        helloButton.setDisable(disableButtons);
        byeButton.setDisable(disableButtons);
    }

    @FXML
    public void handleChange(){
        System.out.println("The checkbox is " + (checkBox.isSelected() ? "checked" : "not checked"));
    }
}
