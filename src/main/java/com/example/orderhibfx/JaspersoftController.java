package com.example.orderhibfx;

import com.example.orderhibfx.dao.ProductDAO;
import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Product;
import com.example.orderhibfx.models.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class JaspersoftController implements Initializable {
        @FXML
        private Button backButton;

        @FXML
        private DatePicker dateFrom;

        @FXML
        private DatePicker dateUntil;

        @FXML
        private Button generate1Button;

        @FXML
        private Button generate2Button;

        @FXML
        private Button generate3Button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void generateProduct(ActionEvent actionEvent) {
        List<Product> products = new ArrayList<>();
        ProductDAO productDAO = new ProductDAO();
        products = productDAO.getAll();
    }

    public void generateToday(ActionEvent actionEvent) {
        List<Request> requests = new ArrayList<>();
        RequestDAO requestDAO = new RequestDAO();
        requests = requestDAO.getAllToday();
    }

    public void generateDate(ActionEvent actionEvent) {
        if(dateFrom.getValue() != null && dateUntil.getValue() != null) {

            LocalDate date1 = dateFrom.getValue();
            LocalDate date2 = dateUntil.getValue();

            // Si la fecha 1 es despues que la fecha 2 se cambian para que no haya error
            if (date1.isAfter(date2)) {
                LocalDate aux = date1;
                date1 = date2;
                date2 = aux;
            }

            List<Request> requests = new ArrayList<>();
            RequestDAO requestDAO = new RequestDAO();
            requests = requestDAO.getAllBetweenDates(date1, date2);

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("");
            alert.setContentText("Rellena los campos de fecha");

            ButtonType buttonTypeOk = new ButtonType("OK");

            alert.getButtonTypes().setAll(buttonTypeOk);

            alert.showAndWait();
        }
    }

    public void back(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main-table-view.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
