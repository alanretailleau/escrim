module com.escrim {
    opens com.escrim.model to javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.h2database;
    requires javafx.base;
    requires javafx.graphics;

    opens com.escrim to javafx.fxml;
    exports com.escrim;
}
