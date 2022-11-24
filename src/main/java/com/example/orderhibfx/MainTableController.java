package com.example.orderhibfx;

import com.example.orderhibfx.dao.ProductDAO;
import com.example.orderhibfx.dao.RequestDAO;
import com.example.orderhibfx.models.Request;
import com.example.orderhibfx.utils.DataHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class MainTableController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private TableView<Request> tableRequest;

    @FXML
    private TableColumn<Request, String> columnClientName;

    @FXML
    private TableColumn<Request, Date> columnDate;

    @FXML
    private TableColumn<Request, Boolean> columnDelivered;

    @FXML
    private TableColumn<Request, Integer> columnId;

    @FXML
    private TableColumn<Request, String> columnProductName;

    @FXML
    private Button btnShowOrder;

    @FXML
    private ChoiceBox<String> choiceBox;

    private ArrayList<Request> requestsTableData;

    private String choiceSelected;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RequestDAO requestDAO = new RequestDAO();
        requestsTableData = requestDAO.getAllByDate();

        inflateChoiceBox();
        inflateTable();
        updateTable();
    }

    @FXML
    void showOrderAction(ActionEvent event) {
        RequestDAO requestDAO = new RequestDAO();
        choiceSelected = choiceBox.getValue();

        updateTable();
    }

    private void inflateChoiceBox() {
        choiceBox.getItems().add("Todos");
        choiceBox.getItems().add("Hoy");
        choiceBox.getItems().add("Última semana");
        choiceBox.getItems().add("Última mes");
        choiceBox.getItems().add("Última año");
        choiceBox.setValue("Todos");
        choiceSelected = "Todos";
    }



    private void inflateTable() {
        tableRequest.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        columnId.setCellValueFactory(new PropertyValueFactory("id"));
        columnDate.setCellValueFactory(new PropertyValueFactory("date"));
        columnClientName.setCellValueFactory(new PropertyValueFactory("client"));
        columnDelivered.setCellValueFactory(new PropertyValueFactory("delivered"));
        columnProductName.setCellValueFactory(new PropertyValueFactory("product"));
    }

    private void updateTable() {
        RequestDAO requestDAO = new RequestDAO();
        ProductDAO productDAO = new ProductDAO();

        switch (choiceSelected) {
            case "Todos":
                requestsTableData = requestDAO.getAllByDate();
                break;
            case "Hoy":
                requestsTableData = requestDAO.getAllToday();
                break;
            case "Última semana":
                requestsTableData = requestDAO.getAllLastWeek();
                break;
            case "Última año":
                requestsTableData = requestDAO.getAllLastYear();
                break;
        }

        ArrayList<RequestProduct> requestProductsList = new ArrayList<>();

        for (Request request : requestsTableData) {
            RequestProduct requestProduct = new RequestProduct(
                    request.getId(),
                    request.getDate(),
                    request.getClient(),
                    request.getDelivered(),
                    productDAO.get(request.getProduct()).getName()
            );
            requestProductsList.add(requestProduct);
        }

        tableRequest.getItems().clear();
        tableRequest.getItems().addAll(requestsTableData);
    }

    private Request getSelectedRow() {
        return tableRequest.getSelectionModel().getSelectedItem();
    }

    @FXML
    void filterAvaliableAction(ActionEvent event) {
        RequestDAO requestDAO = new RequestDAO();
        requestsTableData = requestDAO.getAllNotDelivered();
        updateTable();
    }

    public void checkDelivered(ActionEvent event) throws IOException{
        if (getSelectedRow() == null) {
            errorMessage();
        }else {
            var request = getSelectedRow();
            request.setDelivered(true);
            var requestDAO = new RequestDAO();
            requestDAO.update(request);
            updateTable();
        }
    }

    public void deleteRequest(ActionEvent event) throws IOException{
        if (getSelectedRow() == null) {
            errorMessage();
        }else {
            Alert important = new Alert(Alert.AlertType.CONFIRMATION);
            important.setHeaderText("IMPORTANTE");
            important.setContentText("¿Está seguro de eliminar este pedido?");
            var confirmation = important.showAndWait();
            if(confirmation.get() == ButtonType.OK){
                var request = getSelectedRow();
                var requestDAO = new RequestDAO();
                requestDAO.delete(request);
                updateTable();
            }
            important.close();
        }
    }
    public void errorMessage(){
        Alert fail = new Alert(Alert.AlertType.INFORMATION);
        fail.setHeaderText("ERROR");
        fail.setContentText("No hay producto seleccionado");
        fail.showAndWait();
    }

    public void changeToCreateRequest(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("create-request-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeToModifyRequest(ActionEvent event) throws IOException {
        Request request = getSelectedRow();

        if (request != null) {
            DataHolder dataHolder = DataHolder.getInstance();
            dataHolder.setData(request);

            Parent root = FXMLLoader.load(getClass().getResource("modify-request-view.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            errorMessage();
        }
    }

    //Colapsa esta clase
    private class RequestProduct implements Serializable {
        private Integer id;
        private Date date;
        private String client;
        private Boolean delivered;
        private String product;

        public RequestProduct(Integer id, Date date, String client, Boolean delivered, String product) {
            this.id = id;
            this.date = date;
            this.client = client;
            this.delivered = delivered;
            this.product = product;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public Boolean getDelivered() {
            return delivered;
        }

        public void setDelivered(Boolean delivered) {
            this.delivered = delivered;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }
    }
}
