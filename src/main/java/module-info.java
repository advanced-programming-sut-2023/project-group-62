module mainProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires passay;


    exports org.group62;
    opens org.group62 to javafx.fxml;
    exports org.group62.veiw;
    opens org.group62.veiw to javafx.fxml;
    exports org.group62.veiw.changeMenu;
    opens org.group62.veiw.changeMenu to javafx.fxml;

}