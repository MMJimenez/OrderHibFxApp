package com.example.orderhibfx;

import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Request;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainTableController {

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
    private TableView<Request> tableRequest = new TableView<Request>();

    RequestDAO requestDAO = new RequestDAO();
    ObservableList<Request> requests = FXCollections.observableArrayList(requestDAO.getAll());
    }
