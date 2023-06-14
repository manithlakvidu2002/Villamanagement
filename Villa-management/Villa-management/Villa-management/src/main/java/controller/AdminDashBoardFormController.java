package controller;

import bo.BoFactory;
import bo.custom.AdminBO;
import bo.custom.impl.AdminDashBoardBOImpl;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class AdminDashBoardFormController {

    public Pane secondAncherPane;
    public AnchorPane mainAnchorPane;
    @FXML
    private Label lblAvailableDriver;

    @FXML
    private Label lblAvailableRoom;

    @FXML
    private Label lblSafaryCount;

    @FXML
    private Label lblTime;

    @FXML
    private Label lbltotalCustomer;

    @FXML
    private Label lbtDate;

    AdminBO adminBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ADMIN_BO);

    public void initialize() {
        generateRealTime();
        setCountDetails();
        AvailableDriver();
        AvailableRoom();
    }

    private void AvailableRoom() {
        try {

            int count = adminBO.getRoomCount();
            if (count<10){
                lblAvailableRoom.setText("0"+count);
            }else {
                lblAvailableRoom.setText(String.valueOf(count));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void AvailableDriver() {
        try {

            int count = adminBO.getDriverCount();
            if (count<10){
                lblAvailableDriver.setText("0"+count);
            }else {
                lblAvailableDriver.setText(String.valueOf(count));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCountDetails() {
        CustomerCount();
        SafaryCount();

    }

    private void SafaryCount() {
        try {

            int count = adminBO.getSuffariCount();
            if (count<10){
                lblSafaryCount.setText("0"+count);
            }else {
                lblSafaryCount.setText(String.valueOf(count));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void CustomerCount() {
        try {

            int count = adminBO.getCoustomerCount();
            if (count<10){
                lbltotalCustomer.setText("0"+count);
            }else {
                lbltotalCustomer.setText(String.valueOf(count));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {
        setUi("CustomerForm");
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) {
        setUi("EmployeeForm");
    }

    @FXML
    void btnExitPlanOnAction(ActionEvent event) {
        setUi("LoginForm");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LoginForm.fxml"));
            Parent parent = loader.load();
            Scene scene=new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage stage1= (Stage)mainAnchorPane.getScene().getWindow();
            stage1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBillPlanOnAction(ActionEvent event) {
        setUi("BillForm");
    }

    @FXML
    void btnRoomOnAction(ActionEvent actionEvent) {
        setUi("RoomForm");
    }

    @FXML
    void btnSafaryOnAction(ActionEvent actionEvent) {
        setUi("SafaryForm");
    }

    public void btnBookingRoomOnAction(ActionEvent actionEvent) {
        setUi("BookingRoomForm");
    }


    public void btnDriverOnActions(ActionEvent actionEvent) {
        setUi("DriverForm");
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        setUi("HomeForm");
    }


    //----------------------Set Date & time--------------------------------------------------------------
    private void generateRealTime() {
        lbtDate.setText(LocalDate.now().toString());
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //----------------------Set navigation--------------------------------------------------------------

    public void setUi(String url) {
        try {
            Pane pane = FXMLLoader.load(getClass().getResource("../view/" + url + ".fxml"));
            secondAncherPane.getChildren().setAll(pane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDriverOnAction(MouseEvent mouseEvent) {
    }
}

