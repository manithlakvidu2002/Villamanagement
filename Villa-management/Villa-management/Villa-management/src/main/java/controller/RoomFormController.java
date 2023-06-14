package controller;

import bo.BoFactory;
import bo.custom.RoomBO;
import dto.RoomDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import view.tdm.RoomTM;
import util.ValidationUtil;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RoomFormController {

    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    @FXML
    private ComboBox cmbRoomAvailable;

    @FXML
    private ComboBox cmbRoomDescrition;

    @FXML
    private ComboBox cmbRoomType;

    @FXML
    private TableColumn colAvailable;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colPrice;

    @FXML
    private TableColumn colType;

    @FXML
    private Label lblDate;

    @FXML
    private TableView<RoomTM> tblRoom;

    @FXML
    private TextField txtRoomId;

    @FXML
    private TextField txtRoomPrice;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    RoomBO roomBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ROOM_BO);

    public void initialize() {
        btnSave.setDisable(true);
        Pattern patternId = Pattern.compile("^(R00)[0-9]{1,6}$");
        Pattern patternPrice = Pattern.compile("^[0-9]{2,}$");

        map.put(txtRoomId, patternId);
        map.put(txtRoomPrice, patternPrice);


        colId.setCellValueFactory(new PropertyValueFactory("roomId"));
        colType.setCellValueFactory(new PropertyValueFactory("type"));
        colDescription.setCellValueFactory(new PropertyValueFactory("description"));
        colAvailable.setCellValueFactory(new PropertyValueFactory("available"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));

        LoadAllCustomer();
        generateRealTime();
        loadComboBox();

        cmbRoomType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                try {
                        btnSave.setDisable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        });

    }
    private void loadComboBox() {
        ObservableList obList = FXCollections.observableArrayList();
        obList.add("AC");
        obList.add("Non-AC");
        obList.add("Non-AC/Food");
        obList.add("AC/Food");
        cmbRoomType.setItems(obList);

        ObservableList obList1 = FXCollections.observableArrayList();
        obList1.add("Single");
        obList1.add("Double");
        obList1.add("Family");
        cmbRoomDescrition.setItems(obList1);

        ObservableList obList2 = FXCollections.observableArrayList();
        obList2.add("Available");
        obList2.add("Non-Available");
        cmbRoomAvailable.setItems(obList2);
    }


    private void LoadAllCustomer() {
        tblRoom.getItems().clear();
        try {
            ArrayList <RoomDTO> alls=roomBO.LoadAllRoom();
            for (RoomDTO tm : alls) {
                tblRoom.getItems().add(
                        new RoomTM(
                                tm.getRoomId(),
                                tm.getType(),
                                tm.getDescription(),
                                tm.getAvailable(),
                                tm.getPrice()
                        ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void SearchOnKeyPress(KeyEvent event) {
        String id = txtRoomId.getText();
        try {
            /*RoomDAOImpl R1 =new RoomDAOImpl();*/
            if (!roomBO.existRoom(id)) {

            } else {
                /*RoomDTO search = R1.search(id);*/
                RoomDTO search =roomBO.SearchRoom(id);
                if (!search.getRoomId().equals(null)) {
                    cmbRoomType.setValue(search.getType());
                    cmbRoomDescrition.setValue(search.getDescription());
                    cmbRoomAvailable.setValue(search.getAvailable());
                    txtRoomPrice.setText(search.getPrice());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction() {
        txtRoomId.clear();
        txtRoomPrice.clear();
        cmbRoomType.getSelectionModel().clearSelection();
        cmbRoomAvailable.getSelectionModel().clearSelection();
        cmbRoomDescrition.getSelectionModel().clearSelection();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            /*RoomDAOImpl roomDAOImpl = new RoomDAOImpl();
            boolean delete = roomDAOImpl.delete(txtRoomId.getText());*/
            boolean b=roomBO.RoomDelete(txtRoomId.getText());
            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "employee  " + txtRoomId.getText() + " Deleted..!").show();
                btnClearOnAction();
                LoadAllCustomer();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Wrong..!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void btnSaveOnAction() {
        String id = txtRoomId.getText();
        String type = String.valueOf(cmbRoomType.getValue());
        String description = String.valueOf(cmbRoomAvailable.getValue());
        String available = String.valueOf(cmbRoomAvailable.getValue());
        String price = txtRoomPrice.getText();
        try {
            if (!id.equals("") && !type.equals(null) && !description.equals(null) && !available.equals(null) && !price.equals("")) {
                if (!roomBO.existRoom(id)) {
                   /* RoomDAOImpl roomDAOImpl = new RoomDAOImpl();
                    boolean save = roomDAOImpl.save(new RoomDTO(id, type, description, available, price));*/
                    boolean b=roomBO.RoomSave(new RoomDTO(id, type, description, available, price));
                    if (b) {
                        new Alert(Alert.AlertType.INFORMATION, id + " RoomDAOImpl Added..!").show();
                        LoadAllCustomer();
                        btnClearOnAction();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something Wrong..!").show();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Duplicate Entry..!").show();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Enter Data..!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtRoomId.getText();
        try {
            if (!roomBO.existRoom(id)) {
                new Alert(Alert.AlertType.ERROR, id + " Room Not Registered..!").show();
            } else {
                RoomDTO search =roomBO.SearchRoom(id);
                if (!search.getRoomId().equals(null)) {
                    cmbRoomType.setValue(search.getType());
                    cmbRoomDescrition.setValue(search.getDescription());
                    cmbRoomAvailable.setValue(search.getAvailable());
                    txtRoomPrice.setText(search.getPrice());
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtRoomId.getText();
        String type = String.valueOf(cmbRoomType.getValue());
        String description = String.valueOf(cmbRoomDescrition.getValue());
        String available = String.valueOf(cmbRoomAvailable.getValue());
        String price = txtRoomPrice.getText();

        try {
            /*RoomDAOImpl roomDAOImpl = new RoomDAOImpl();
            boolean update = roomDAOImpl.update(new RoomDTO(id, type, description, available, price));*/
            boolean b=roomBO.RoomUpdate(new RoomDTO(id, type, description, available, price));
            if (b) {
                new Alert(Alert.AlertType.INFORMATION, id + " RoomDAOImpl Updated..!").show();
                btnClearOnAction();
                LoadAllCustomer();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Wrong..!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateRealTime() {
        //lblDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            lblDate.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void textFieldsKeyReleasesd(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        ValidationUtil.Validation(map);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object respond = ValidationUtil.Validation(map);
            if (respond instanceof TextField) {
                TextField textField = (TextField) respond;
                textField.requestFocus();
            } else {
                /*EmployeeDAOImpl e1 =new EmployeeDAOImpl();
                boolean exit = e1.existCustomer(txtRoomId.getText());*/
                boolean b=roomBO.existRoom(txtRoomId.getText());
                if (b) {
                    btnSave.setDisable(true);
                } else {
                    btnSave.setDisable(false);
                    btnSaveOnAction();
                }
            }
        }
    }


}
