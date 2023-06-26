module mainProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires passay;


    exports org.group62;
    opens org.group62 to javafx.fxml;

}