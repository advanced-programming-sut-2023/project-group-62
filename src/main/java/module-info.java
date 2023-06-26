module mainProject {
    requires javafx.controls;
    requires javafx.fxml;


    exports org.group62;
    opens org.group62 to javafx.fxml;

}