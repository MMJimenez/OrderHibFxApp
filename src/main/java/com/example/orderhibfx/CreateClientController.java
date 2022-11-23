package com.example.orderhibfx;

import com.example.orderhibfx.dao.ProductDAO;
import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Product;
import com.example.orderhibfx.models.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateClientController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView tableProduct;
    @FXML
    private TextField clientField;

    public void changeToMainTable(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("main-table-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void createRequest(ActionEvent event) throws IOException{
        Request request = new Request();

        request.setDelivered((byte) 0);
        request.setClient(clientField.getText());
        int a = tableProduct.getSelectionModel().getSelectedIndex();
        request.setProduct(ProductDAO.get(a));

    }
}
