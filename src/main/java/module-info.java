module com.equipeglr {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.equipeglr to javafx.fxml;
    exports com.equipeglr;
}
