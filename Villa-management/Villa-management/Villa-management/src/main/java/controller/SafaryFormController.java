package controller;

import bo.BoFactory;
import bo.custom.SafaryBO;
import dao.custom.Impl.CustomerDAOImpl;
import dto.CustomEntityDTO;
import dto.DriverDTO;
import dto.SafaryDTO;
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
import view.tdm.SafaryTm;
import view.tdm.driverTm;
import util.ValidationUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class SafaryFormController {
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    @FXML
    private ComboBox cmbDriverId;

    @FXML
    private ComboBox cmbSafaryType;

    @FXML
    private TableColumn colDate;

    @FXML
    private TableColumn colDriverContact;

    @FXML
    private TableColumn colDriverId;

    @FXML
    private TableColumn colDriverName;

    @FXML
    private TableColumn colSafaryId;

    @FXML
    private TableColumn colTime;

    @FXML
    private TableColumn colType;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDriverContct;

    @FXML
    private Label lblDriverName;

    @FXML
    private TableView<SafaryTm> tblSafary;

    @FXML
    private DatePicker txtDate;

    @FXML
    private TextField txtSafaryId;

    @FXML
    private TextField txtTime;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();
    SafaryBO safaryBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.SAFARY_BO);

    public void initialize() {
        btnSave.setDisable(true);
        Pattern patternSId=Pattern.compile("^(SF00)[0-9]{1,5}$");
        Pattern patternTime=Pattern.compile("^[0-9]{2}(:)[0-9]{2}(:)[0-9]{2}$");

        map.put(txtSafaryId,patternSId);
        map.put(txtTime,patternTime);

        colSafaryId.setCellValueFactory(new PropertyValueFactory("safaryId"));
        colType.setCellValueFactory(new PropertyValueFactory("type"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        colDriverId.setCellValueFactory(new PropertyValueFactory("driverId"));
        colDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        colDriverContact.setCellValueFactory(new PropertyValueFactory("driverContact"));

        LoadAllCustomer();
        generateRealTime();
        loadComboBox();

        cmbDriverId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    setDriverFields(newValue);
                    if (txtSafaryId.getText()!=null && txtTime.getText()!=null && txtDate.getValue()!=null) {
                        btnSave.setDisable(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void setDriverFields(Object newValue) {
        try {
            DriverDTO search = safaryBO.searchDriver(String.valueOf(newValue));
            if (!search.getDriverId().equals(null)){
                lblDriverName.setText(search.getDriverName());
                lblDriverContct.setText(search.getContact());
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadComboBox() {
        ObservableList obList = FXCollections.observableArrayList();
        obList.add("Yala");
        obList.add("Beach");
        obList.add("Camping");
        obList.add("diving");
        cmbSafaryType.setItems(obList);

        ObservableList obList2 = FXCollections.observableArrayList();
        try {
            ArrayList<DriverDTO> all = safaryBO.getAllDriver();
            ArrayList<driverTm> tm = new ArrayList<>();
            for (DriverDTO d:all) {
                tm.add(new driverTm(d.getDriverId()));
            }
            for (driverTm x:tm) {
                obList2.add(x.getDriverId());
            }
            cmbDriverId.setItems(obList2);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void LoadAllCustomer() {
        tblSafary.getItems().clear();
        try {
            ArrayList<CustomEntityDTO> all = safaryBO.getAll();
            ArrayList<SafaryTm> tm = new ArrayList<>();
            for (CustomEntityDTO t: all) {
                tm.add(new SafaryTm(
                        t.getSafaryId(),
                        t.getTime(),
                        t.getDriverId(),
                        t.getDriverName(),
                        t.getDriverContact(),
                        t.getSafaryType(),
                        t.getDate()
                ));
            }
            for (SafaryTm t:tm) {
                tblSafary.getItems().add(
                  new SafaryTm(
                          t.getSafaryId(),
                          t.getType(),
                          t.getDate(),
                          t.getTime(),
                          t.getDriverId(),
                          t.getDriverName(),
                          t.getDriverContact()
                  )
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void SearchOnKeyPress(KeyEvent event) {
        String id = txtSafaryId.getText();
        try {
            SafaryDTO search1 = safaryBO.search(id);

            if (search1!=null) {
                    cmbSafaryType.setValue(search1.getType());
                    txtDate.setValue(LocalDate.parse(search1.getDate()));
                    txtTime.setText(search1.getTime());
                    cmbDriverId.setValue(search1.getDriverId());


                    DriverDTO search = safaryBO.searchDriver(search1.getDriverId());

                    if (search!=null){
                        lblDriverName.setText(search.getDriverName());
                        lblDriverContct.setText(search.getContact());
                    }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction() {
        txtTime.clear();
        txtSafaryId.clear();
        cmbDriverId.getSelectionModel().clearSelection();
        cmbSafaryType.getSelectionModel().clearSelection();
        lblDriverName.setText("");
        lblDriverContct.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean delete = safaryBO.delete(txtSafaryId.getText());
            if (delete) {
                new Alert(Alert.AlertType.INFORMATION, "SafaryDAOImpl  " + txtSafaryId.getText() + " Deleted..!").show();
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
        String id = txtSafaryId.getText();
        String type = String.valueOf(cmbSafaryType.getValue());
        String date = String.valueOf(txtDate.getValue());
        String time = txtTime.getText();
        String driverId = String.valueOf(cmbDriverId.getValue());

        if (id!=null && date!=null && time!=null){
            try {
                boolean save = safaryBO.save(new SafaryDTO(id, type, date, time, driverId));
                if (save) {
                    new Alert(Alert.AlertType.INFORMATION, id + " Safary Added..!").show();
                    LoadAllCustomer();
                    btnClearOnAction();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Wrong..!").show();
                }


            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Enter Data..!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String id = txtSafaryId.getText();
        try {
            SafaryDTO search1 = safaryBO.search(id);

            if (search1!=null) {
                cmbSafaryType.setValue(search1.getType());
                txtDate.setValue(LocalDate.parse(search1.getDate()));
                txtTime.setText(search1.getTime());
                cmbDriverId.setValue(search1.getDriverId());


                DriverDTO search = safaryBO.searchDriver(search1.getDriverId());

                if (search!=null){
                    lblDriverName.setText(search.getDriverName());
                    lblDriverContct.setText(search.getContact());
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "SafaryDAOImpl id not Register..!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtSafaryId.getText();
        String type = String.valueOf(cmbSafaryType.getValue());
        String date = String.valueOf(txtDate.getValue());
        String time = txtTime.getText();
        String driverId = String.valueOf(cmbDriverId.getValue());

        try {

            boolean update = safaryBO.update(new SafaryDTO(id, type, date, time, driverId));
            if (update) {
                new Alert(Alert.AlertType.INFORMATION, id + " SafaryDAOImpl Updated..!").show();
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
        if (keyEvent.getCode()== KeyCode.ENTER){
            Object respond = ValidationUtil.Validation(map);
            if (respond instanceof TextField){
                TextField textField= (TextField) respond;
                textField.requestFocus();
            }else {
                CustomerDAOImpl c1 =new CustomerDAOImpl();
                boolean exit= c1.existCustomer(txtSafaryId.getText());
                if (exit){

                }else {
                    btnSaveOnAction();
                }
            }
        }
    }

}
