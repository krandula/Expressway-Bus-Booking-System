package lk.nsbm.dep.booking.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.nsbm.dep.booking.business.BOFactory;
import lk.nsbm.dep.booking.business.BOType;
import lk.nsbm.dep.booking.business.custom.PassengerBO;
import lk.nsbm.dep.booking.business.exception.AlreadyExistsInTicketException;
import lk.nsbm.dep.booking.dto.PassengerDTO;
import lk.nsbm.dep.booking.util.PassengerTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PassFormController implements Initializable {
    public AnchorPane root;
    public ImageView img_bk;
    public JFXTextField txt_name;
    public JFXTextField txt_nic;
    public JFXTextField txt_no;
    public Label lbl_dr_id;
    public JFXButton btn_newww;
    public JFXButton btn_upadte;
    public JFXButton btn_del;
    public TableView<PassengerTM> pass_tbl;

    private PassengerBO passengerBO = BOFactory.getInstance().getBO(BOType.PASSENGER);

    public void initialize(URL url, ResourceBundle rb) {
        pass_tbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("passId"));
        pass_tbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("passName"));
        pass_tbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("passNic"));
        pass_tbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("passConNum"));

        txt_nic.setDisable(true);
        txt_name.setDisable(true);
        txt_no.setDisable(true);
        btn_del.setDisable(true);
        btn_upadte.setDisable(true);

        try {
            List<PassengerDTO> allPass = passengerBO.findAllPassenger();
            ObservableList<PassengerTM> passengerTMS = pass_tbl.getItems();
            for (PassengerDTO p : allPass) {
                passengerTMS.add(new PassengerTM(p.getPassId(), p.getPassName(), p.getPassNic(), p.getPassConNum()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
        }

        pass_tbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PassengerTM>() {
            @Override
            public void changed(ObservableValue<? extends PassengerTM> observable, PassengerTM oldValue, PassengerTM newValue) {
                PassengerTM selectedItem = pass_tbl.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btn_upadte.setText("Save");
                    btn_del.setDisable(true);
                    return;
                }

                btn_upadte.setText("Update");
                btn_upadte.setDisable(false);
                btn_del.setDisable(false);
                txt_name.setDisable(false);
                txt_nic.setDisable(false);
                txt_no.setDisable(false);
                txt_no.setText(selectedItem.getPassConNum());
                txt_nic.setText(selectedItem.getPassNic());
                txt_name.setText(selectedItem.getPassName());
            }
        });
    }

    public void btn_new_Act(ActionEvent actionEvent) {
        txt_name.clear();
        txt_nic.clear();
        txt_no.clear();
        pass_tbl.getSelectionModel().clearSelection();
        txt_name.setDisable(false);
        txt_no.setDisable(false);
        txt_nic.setDisable(false);
        txt_name.requestFocus();
        btn_upadte.setDisable(false);

        int maxId = 0;

        try {
            String lastCustomerId = passengerBO.getLastPassengerId();

            if (lastCustomerId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastCustomerId.replace("P", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "P00" + maxId;
            } else if (maxId < 100) {
                id = "P0" + maxId;
            } else {
                id = "P" + maxId;
            }
            lbl_dr_id.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
        }
    }

    public void btn_upadte_Act(ActionEvent actionEvent) {
        if (!txt_name.getText().matches("[A-Za-z][A-Za-z. ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
            return;
        }
        if (btn_upadte.getText().equals("Save")) {
            ObservableList<PassengerTM> passengerTMS = pass_tbl.getItems();
            PassengerDTO newPass = new PassengerDTO(
                    lbl_dr_id.getText(),
                    txt_name.getText(),
                    txt_no.getText(),
                    txt_nic.getText()
            );
            try {
                passengerBO.savePassenger(newPass);
                passengerTMS.add(new PassengerTM(newPass.getPassId(), newPass.getPassName(), newPass.getPassNic(),
                        newPass.getPassConNum()));
                btn_new_Act(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
            }
        } else {
            PassengerTM selectedItem = pass_tbl.getSelectionModel().getSelectedItem();
            try {
                passengerBO.updatePassenger(new PassengerDTO(selectedItem.getPassId(),
                        txt_name.getText(),
                        txt_nic.getText(),
                        txt_no.getText()));
                selectedItem.setPassName(txt_name.getText());
                selectedItem.setPassNic(txt_nic.getText());
                selectedItem.setPassConNum(txt_no.getText());
                pass_tbl.refresh();
                btn_new_Act(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
            }
        }
    }

    public void btn_del_Act(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete details about this Passenger?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            PassengerTM selectedItem = pass_tbl.getSelectionModel().getSelectedItem();
            try {
                passengerBO.deletePassenger(selectedItem.getPassId());
                pass_tbl.getItems().remove(selectedItem);
            } catch (AlreadyExistsInTicketException e) {
                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE, null, e);
            }
        }
        btn_new_Act(actionEvent);
    }

    public void img_vk(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/nsbm/dep/booking/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}
