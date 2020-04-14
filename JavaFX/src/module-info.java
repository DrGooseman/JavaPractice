module JavaFX {
    requires javafx.fxml;
    requires javafx.controls;

    opens HelloWorld;
    opens GridPane;
    opens EventHandler;
}