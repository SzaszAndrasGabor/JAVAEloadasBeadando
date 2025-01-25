module com.example.menu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires java.sql;
    requires com.sun.istack.runtime;
    requires jakarta.xml.ws;
requires  gson;
    requires httpcore;
    requires httpclient;
requires org.joda.time;
    requires java.persistence;
    //requires java.persistence;

    opens com.example.menu to javafx.fxml;
    opens com.example.oanda.v20;
    opens com.example.oanda.v20.account;
    opens com.example.oanda.v20.pricing;
    opens com.example.oanda.v20.pricing_common;
    opens com.example.oanda.v20.order;
    opens com.example.oanda.v20.instrument;
    opens com.example.oanda.v20.transaction;
    opens com.example.oanda.v20.trade;
    exports com.example.menu;
    exports com.example.oanda.v20.primitives;
    exports com.example.oanda.v20.transaction;
    exports com.example.oanda.v20.pricing_common;
    exports com.example.oanda.v20.order;
    exports com.example.oanda.v20.trade;
}