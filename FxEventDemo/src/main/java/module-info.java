module com.example.fxeventdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fxeventdemo to javafx.fxml;
    exports com.example.fxeventdemo;
}