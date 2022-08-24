module com.equipeglr {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.equipeglr to javafx.fxml;
    exports com.equipeglr;
}
