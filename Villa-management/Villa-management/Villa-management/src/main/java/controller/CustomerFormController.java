package controller;

import bo.BoFactory;
import bo.custom.CustomerBO;
import dto.CustomerDTO;
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
import view.tdm.CustomerTm;
import util.ValidationUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class CustomerFormController {

    public DatePicker txtDob;
    public Button btnSave;
    public Button btnUpdate;
    public Button btnDelete;
    @FXML
    private ComboBox cmbSafaryid;
    @FXML
    private ComboBox cmbSafaryType;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colBirthday;

    @FXML
    private TableColumn colContact;

    @FXML
    private TableColumn colGender;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colNic;

    @FXML
    private TableColumn colSalary;

    @FXML
    private Label lblDate;

    @FXML
    private RadioButton rbnFemale;

    @FXML
    private RadioButton rbnMale;

    @FXML
    private ToggleGroup sexuaol;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtCusAddress;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtCusNic;
    LinkedHashMap<TextField,Pattern> map = new LinkedHashMap();

    CustomerBO customerBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.CUSTOMER_BO);

    public void initialize() {

        btnSave.setDisable(true);
// validation
        Pattern patternId=Pattern.compile("^(C00)[0-9]{1,5}$");
        Pattern patternName=Pattern.compile("^[A-z]{3,}$");
        Pattern patternAddress=Pattern.compile("^[A-z 0-9 ,/]{5,}$");
        Pattern patternNic=Pattern.compile("^[0-9 v]{10,12}$");
        Pattern patternContact=Pattern.compile("^(071|072|070|075|076|078|)[0-9]{10}$");

        map.put(txtCusId,patternId);
        map.put(txtCusName,patternName);
        map.put(txtCusAddress,patternAddress);
        map.put(txtCusNic,patternNic);
        map.put(txtContact,patternContact);


        colId.setCellValueFactory(new PropertyValueFactory("cusId"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colBirthday.setCellValueFactory(new PropertyValueFactory("dob"));
        colNic.setCellValueFactory(new PropertyValueFactory("nic"));
        colContact.setCellValueFactory(new PropertyValueFactory("contact"));
        colGender.setCellValueFactory(new PropertyValueFactory("sex"));
        colSalary.setCellValueFactory(new PropertyValueFactory("safaryId"));

        LoadAllCustomer();
        loadComboBox();
        generateRealTime();
        cmbSafaryType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    setCustomerFields(newValue);
                    if (txtCusId.getText()!=null && txtCusName.getText()!=null && txtCusAddress.getText()!=null && txtCusNic.getText()!=null && txtContact.getText()!=null){
                        btnSave.setDisable(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void setCustomerFields(Object newValue) {
        ObservableList obList2 = FXCollections.observableArrayList();
        try {

            ArrayList<SafaryDTO> safaryDTOS =customerBO.SearchAllSafary(String.valueOf(newValue));
            for (SafaryDTO x:safaryDTOS) {
                obList2.add(x.getSafaryId());
            }
            cmbSafaryid.setItems(obList2);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void LoadAllCustomer() {
        tblCustomer.getItems().clear();
        try {

            ArrayList <CustomerDTO> alls=customerBO.LoadAllCustomer();
            for (CustomerDTO all : alls) {
                tblCustomer.getItems().add(
                        new CustomerTm(
                                all.getCusId(),
                                all.getName(),
                                all.getAddress(),
                                all.getDob(),
                                all.getNic(),
                                all.getContact(),
                                all.getSex(),
                                all.getSafaryId()
                        ));
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
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            /*CustomerDAOImpl customerDAOImpl = new CustomerDAOImpl();
            boolean delete = customerDAOImpl.delete(txtCusId.getText());*/

            boolean b=customerBO.CustomerDelete(txtCusId.getText());
            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "CustomerDAOImpl " + txtCusId.getText() + " Delete..!").show();
                LoadAllCustomer();
                btnClearOnAction();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Wrong..!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction() {
                String Id = txtCusId.getText();
                String name = txtCusName.getText();
                String address = txtCusAddress.getText();
                String dob = String.valueOf(txtDob.getValue());
                String nic = txtCusNic.getText();
                String contact = txtContact.getText();
                String gender;
                String safaryId = String.valueOf(cmbSafaryid.getValue());
                String safaryType = String.valueOf(cmbSafaryType.getValue());

                if (rbnMale.isSelected()) {
                    gender = "Male";
                } else {
                    gender = "Femal";
                }

                try {

                    if (!customerBO.existCustomer(Id)) {

                        boolean b=customerBO.CustomerSave(new CustomerDTO(Id, name, address, dob, nic, contact, gender, safaryId, safaryType));
                        if (b) {
                            new Alert(Alert.AlertType.INFORMATION, "Customer " + Id + " Saved..!").show();
                            LoadAllCustomer();
                            btnClearOnAction();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Something Wrong..!").show();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Customer Id Already Add..!").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
    }

    @FXML
    void btnSearchOnAction() {
        String id = txtCusId.getText();
        try {

            if (!customerBO.existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, id + " SafaryDAOImpl Not Register..!").show();
            } else {

                CustomerDTO search=customerBO.SearchCustomer(id);

                txtCusId.setText(search.getCusId());
                txtCusName.setText(search.getName());
                txtCusAddress.setText(search.getAddress());
                txtDob.setValue(LocalDate.parse(search.getDob()));
                txtCusNic.setText(search.getNic());
                txtContact.setText(search.getContact());
                String value = search.getSex();
                System.out.println(value);
                if (value.equals("Male")) {
                    rbnMale.setSelected(true);
                } else {
                    rbnFemale.setSelected(true);
                }
                cmbSafaryid.setValue(search.getSafaryId());
                cmbSafaryType.setValue(search.getSafaryType());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String Id = txtCusId.getText();
        String name = txtCusName.getText();
        String address = txtCusAddress.getText();
        String dob = String.valueOf(txtDob.getValue());
        String nic = txtCusNic.getText();
        String contact = txtContact.getText();
        String gender;
        String safaryId = String.valueOf(cmbSafaryid.getValue());
        String safaryType = String.valueOf(cmbSafaryType.getValue());

        if (rbnMale.isSelected()) {
            gender = "Male";
        } else {
            gender = "Femal";
        }

        try {
            boolean b=customerBO.CustomerUpdate(new CustomerDTO(Id, name, address, dob, nic, contact, gender, safaryId, safaryType));
            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "CustomerDAOImpl " + Id + " Update..!").show();
                LoadAllCustomer();
                btnClearOnAction();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Wrong..!").show();
            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void SearchOnKeyPress(KeyEvent keyEvent) {
        String id = txtCusId.getText();
        try {

            if (!customerBO.existCustomer(id)) {

            } else {

                CustomerDTO search=customerBO.SearchCustomer(id);

                txtCusId.setText(search.getCusId());
                txtCusName.setText(search.getName());
                txtCusAddress.setText(search.getAddress());
                txtDob.setValue(LocalDate.parse(search.getDob()));
                txtCusNic.setText(search.getNic());
                txtContact.setText(search.getContact());
                String value = search.getSex();
                System.out.println(value);
                if (value.equals("Male")) {
                    rbnMale.setSelected(true);
                } else {
                    rbnFemale.setSelected(true);
                }

                cmbSafaryid.setValue(search.getSafaryId());
                cmbSafaryType.setValue(search.getSafaryType());

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClearOnAction() {
        txtCusId.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusNic.clear();
        txtContact.clear();
        txtDob.setValue(LocalDate.parse("2000-01-01"));
        cmbSafaryid.getSelectionModel().clearSelection();
        cmbSafaryType.getSelectionModel().clearSelection();
        rbnMale.setSelected(false);
        rbnFemale.setSelected(false);

    }


    //----------------------Set Date & time--------------------------------------------------------------
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
                /*CustomerDAOImpl c1 =new CustomerDAOImpl();
                boolean exit=c1.existCustomer(txtCusId.getText());*/

                boolean b=customerBO.existCustomer(txtCusId.getText());
                if (b){

                }else {
                    btnSaveOnAction();
                }
            }
        }
    }
}
