package com.example.orderhibfx;

import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainTableController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeToCreateClient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("create-request-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TableView<Request> tableRequest;

    @FXML
    private TableColumn<Request, String> columnClientName;

    @FXML
    private TableColumn<Request, Date> columnDate;

    @FXML
    private TableColumn<Request, Integer> columnDelivered;

    @FXML
    private TableColumn<Request, Integer> columnId;

    @FXML
    private TableColumn<Request, Integer> columnProductName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableRequest.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnId.setCellValueFactory(new PropertyValueFactory("id"));
        columnDate.setCellValueFactory(new PropertyValueFactory("date"));
        columnClientName.setCellValueFactory(new PropertyValueFactory("client"));
        columnDelivered.setCellValueFactory(new PropertyValueFactory("delivered"));
        columnProductName.setCellValueFactory(new PropertyValueFactory("product"));

        actualizarTabla();
    }

    private void actualizarTabla() {
        RequestDAO requestDAO = new RequestDAO();

        tableRequest.getItems().clear();
        tableRequest.getItems().addAll(requestDAO.getAll());
    }

}
