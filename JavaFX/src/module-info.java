module JavaFX {
    requires javafx.fxml;
    requires javafx.controls;
    requires jlfgr;

    opens HelloWorld;
    opens GridPane;
    opens EventHandler;
    opens Service;
    opens BorderPaneAndOthers;
    opens Controls;
}