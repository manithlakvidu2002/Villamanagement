package controller;

import bo.BoFactory;
import bo.custom.LoginBO;
import bo.custom.impl.LoginBOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    public AnchorPane LoginAnchorPane;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private Label txtxSignIn;

    LoginBO loginBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.LOGIN_BO);

    @FXML
    void btnLoginOnAction(ActionEvent event) {

        try {
            String name= txtUserName.getText();
            String psw=txtPassword.getText();
            if (loginBO.existEmployee(name,psw)){
                    System.out.println("AdminDashBoardForm");
                    setUi("AdminDashBoardForm");
            }else {
                new Alert(Alert.AlertType.ERROR,"Something Wrong..!" ).show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUi(String url){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/"+url+".fxml"));
                Parent parent = loader.load();
                Scene scene=new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                Stage stage1= (Stage)LoginAnchorPane.getScene().getWindow();
                stage1.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        System.out.println("SignIn");

       setUi("SignInForm");

    }

}
