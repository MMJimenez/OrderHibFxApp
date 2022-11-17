module com.example.orderhibfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires lombok;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires java.base;


    opens com.example.orderhibfx to javafx.fxml;
    exports com.example.orderhibfx;
}