module org.example.game_of_life {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.game_of_life to javafx.fxml;
    exports org.example.game_of_life;
}