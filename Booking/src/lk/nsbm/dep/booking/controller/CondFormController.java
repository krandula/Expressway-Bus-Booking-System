package lk.nsbm.dep.booking.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.nsbm.dep.booking.business.custom.ConductorBO;
import lk.nsbm.dep.booking.business.exception.AlreadyExistsInTicketException;
import lk.nsbm.dep.booking.dto.ConductorDTO;
import lk.nsbm.dep.booking.util.ConductorTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CondFormController implements Initializable {
    public AnchorPane root;
    public ImageView img_back;
    public Label lbl_id;
    public JFXTextField txt_name;
    public JFXTextField txt_nic;
    public JFXTextField txt_num;
    public JFXComboBox cmb_bs_id;
    public JFXButton btn_new;
    public JFXButton btn_delete;
    public JFXButton btn_update;
    public TableView<ConductorTM> con_tbl;

    private ConductorBO conductorBO = BOFactory.getInstance().getBO(BOType.CONDUCTOR);

    public void initialize(URL url, ResourceBundle rb) {
        con_tbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("conId"));
        con_tbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("conName"));
        con_tbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("conNic"));
        con_tbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("conConNum"));

        txt_name.setDisable(true);
        txt_nic.setDisable(true);
        txt_num.setDisable(true);

        try {
            List<ConductorDTO> conductor = conductorBO.findAllConductor();
            ObservableList<ConductorTM> conductorTMS = con_tbl.getItems();
            for (ConductorDTO d : conductor) {
                conductorTMS.add(new ConductorTM(d.getConId(), d.getConName(), d.getConNic(), d.getConConNum()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
        }

        con_tbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ConductorTM>() {
            @Override
            public void changed(ObservableValue<? extends ConductorTM> observable, ConductorTM oldValue, ConductorTM newValue) {
                ConductorTM selectedItem = con_tbl.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btn_update.setText("Save");
                    btn_new.setDisable(true);
                    return;
                }

                btn_update.setText("Update");
                clear();

                lbl_id.setText(selectedItem.getConId());
                txt_name.setText(selectedItem.getConName());
                txt_nic.setText(selectedItem.getConNic());
                txt_num.setText(selectedItem.getConConNum());
            }
        });
    }

    public void clear() {
        btn_update.setDisable(false);
        btn_update.setDisable(false);
        txt_num.setDisable(false);
        txt_nic.setDisable(false);
        txt_name.setDisable(false);
    }

    public void btn_new_act(ActionEvent actionEvent) {
        txt_name.clear();
        txt_nic.setText("");
        txt_num.clear();
        con_tbl.getSelectionModel().clearSelection();
        clear();

        int maxId = 0;

        try {
            String lastId = conductorBO.getLastConId();

            if (lastId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastId.replace("C", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "C00" + maxId;
            } else if (maxId < 100) {
                id = "C0" + maxId;
            } else {
                id = "C" + maxId;
            }
            lbl_id.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
        }
    }

    public void btn_delete_act(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete details about this bus?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            ConductorTM selectedItem = con_tbl.getSelectionModel().getSelectedItem();
            try {
                conductorBO.deleteConductor(selectedItem.getConId());
                con_tbl.getItems().remove(selectedItem);
            } catch (AlreadyExistsInTicketException e) {
                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE, null, e);
            }
        }
        btn_new_act(actionEvent);
    }

    public void btn_update_act(ActionEvent actionEvent) {
        if (!txt_name.getText().matches("[A-Za-z][A-Za-z. ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
            return;
        }
        if (btn_update.getText().equals("Save")) {
            ObservableList<ConductorTM> conductorTMS = con_tbl.getItems();
            ConductorDTO newConductor = new ConductorDTO(
                    lbl_id.getText(),
                    txt_name.getText(),
                    txt_nic.getText(),
                    txt_num.getText()

            );
            try {
                conductorBO.saveConductor(newConductor);
                conductorTMS.add(new ConductorTM(newConductor.getConId(), newConductor.getConName(),
                        newConductor.getConNic(), newConductor.getConConNum()));
                btn_update_act(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
            }
        } else {
            ConductorTM selectedItem = con_tbl.getSelectionModel().getSelectedItem();
            try {
                conductorBO.updateConductor(new ConductorDTO(selectedItem.getConId(),
                        txt_name.getText(),
                        txt_nic.getText(),
                        txt_num.getText()));
                selectedItem.setConName(txt_name.getText());
                selectedItem.setConNic(txt_nic.getText());
                selectedItem.setConConNum(txt_num.getText());
                con_tbl.refresh();
                btn_update_act(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
            }
        }
    }

    public void gohome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/nsbm/dep/booking/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}
