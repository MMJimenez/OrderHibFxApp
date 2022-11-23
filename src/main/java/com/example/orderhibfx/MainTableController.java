package com.example.orderhibfx;

import com.example.orderhibfx.controllers.RequestTable;
import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Request;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;
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
    TableView tableRequest;

    @FXML
    TableColumn columnClientName;

    @FXML
    TableColumn columnDate;

    @FXML
    TableColumn columnDelivered;

    @FXML
    TableColumn columnId;

    @FXML
    TableColumn columnProductName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RequestDAO requestDAO = new RequestDAO();
        ArrayList<Request> requests = requestDAO.getAll("");
        ArrayList<RequestTable> requestsTable = new ArrayList<>();
        for (Request request : requests) {
            requestsTable.add(new RequestTable(request));
        }

        ObservableList<RequestTable> requestsObservableList = FXCollections.observableArrayList(requestsTable);



        tableRequest = new TableView<RequestTable>(requestsObservableList);
        tableRequest.setEditable(false);
        tableRequest.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnId = new TableColumn("id");
        columnId.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("id"));

        columnDate = new TableColumn("date");
        columnDate.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("date"));

        columnClientName = new TableColumn("client");
        columnClientName.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("client"));

        columnDelivered = new TableColumn("delivered");
        columnDelivered.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("delivered"));

        columnProductName = new TableColumn("name");
        columnProductName.setCellValueFactory(new PropertyValueFactory<RequestTable, String>("name"));

        tableRequest.setItems(requestsObservableList);
        tableRequest.getColumns().addAll(columnId, columnDate, columnClientName, columnDelivered, columnProductName);


        System.out.println(requestsObservableList.get(1).getId()
                + "\n" + requestsObservableList.get(1).getDate().toString()
                + "\n" + requestsObservableList.get(1).getClient()
                + "\n" + requestsObservableList.get(1).getDelivered().toString()
                + "\n" + requestsObservableList.get(1).getProduct().toString());
    }


}
