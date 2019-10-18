package lk.nsbm.dep.booking.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nsbm.dep.booking.business.BOFactory;
import lk.nsbm.dep.booking.business.BOType;
import lk.nsbm.dep.booking.business.custom.LoginBO;
import lk.nsbm.dep.booking.dto.LogtableDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public JFXTextField txt_uname_signup;
    public JFXPasswordField txt_pas_sup;
    public JFXPasswordField txt_con_pass_sup;
    public JFXTextField txt_uname_login;
    public JFXPasswordField txt_pas_login;
    public AnchorPane root;
    public String userID;

    LoginBO loginBO = BOFactory.getInstance().getBO(BOType.LOGIN);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.1);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        try {
            genarateID();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void genarateID() throws Exception {
        String lastCustomerId = loginBO.getLastuserId();
        int maxId = 0;
        if (lastCustomerId == null) {
            maxId = 0;
        } else {
            maxId = Integer.parseInt(lastCustomerId.replace("U", ""));
        }

        maxId = maxId + 1;
        String id = "";
        if (maxId < 10) {
            id = "U00" + maxId;
        } else if (maxId < 100) {
            id = "U0" + maxId;
        } else {
            id = "U" + maxId;
        }
        userID = id;
    }

    public void btnRegister(ActionEvent actionEvent) throws Exception {
        String id = userID;
        String name = txt_uname_signup.getText();
        String password = txt_pas_sup.getText();

        boolean b = loginBO.saveLogin(new LogtableDTO(id, name, password));

        if (b) {
            new Alert(Alert.AlertType.CONFIRMATION, "Registration Successfully! ").show();
        } else {

            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }

        clearAll();
    }

    private void clearAll() {
        txt_pas_sup.clear();
        txt_uname_signup.clear();
    }

    public void btnLogin(ActionEvent actionEvent) throws Exception {

        LogtableDTO logtableDTO = loginBO.checkLoginCredential(txt_uname_login.getText(), txt_pas_login.getText());

        if (logtableDTO != null) {
            FXMLLoader load = new FXMLLoader(this.getClass().getResource("/lk/nsbm/dep/booking/view/MainForm.fxml"));
            Stage stage = (Stage) (this.root.getScene().getWindow());
            Scene scene = new Scene(load.load());
            stage.setScene(scene);
            stage.setTitle("Southern-Expressway");
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Email or Password is not Correct. Please Re-Check.").show();
        }
    }

}
