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
import lk.nsbm.dep.booking.business.custom.BusBO;
import lk.nsbm.dep.booking.business.custom.DriverBO;
import lk.nsbm.dep.booking.business.exception.AlreadyExistsInTicketException;
import lk.nsbm.dep.booking.dto.DriverDTO;
import lk.nsbm.dep.booking.util.DriverTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverFormController implements Initializable {
    public AnchorPane root;
    public ImageView img_bk;
    public JFXTextField txt_name;
    public JFXTextField txt_nic;
    public JFXTextField txt_no;
    public Label lbl_dr_id;
    public JFXButton btn_new;
    public JFXButton btn_upadte;
    public JFXButton btn_del;
    public TableView<DriverTM> driver_tbl;

    private DriverBO driverBO = BOFactory.getInstance().getBO(BOType.DRIVER);
    private BusBO busBO = BOFactory.getInstance().getBO(BOType.BUS);

    public void initialize(URL url, ResourceBundle rb) {
        driver_tbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("drvId"));
        driver_tbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("drvName"));
        driver_tbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("drvNic"));
        driver_tbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("drvConNum"));

        txt_nic.setDisable(true);
        txt_name.setDisable(true);
        txt_no.setDisable(true);
        btn_del.setDisable(true);
        btn_upadte.setDisable(true);

        try {
            List<DriverDTO> allDrivers = driverBO.findAllDriver();
            ObservableList<DriverTM> driver = driver_tbl.getItems();
            for (DriverDTO d : allDrivers) {
                driver.add(new DriverTM(d.getDrvId(), d.getDrvName(), d.getDrvNic(), d.getDrvConNum()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
        }
        driver_tbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DriverTM>() {
            @Override
            public void changed(ObservableValue<? extends DriverTM> observable, DriverTM oldValue, DriverTM newValue) {
                DriverTM selectedItem = driver_tbl.getSelectionModel().getSelectedItem();

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
                txt_no.setText(selectedItem.getDrvConNum());
                txt_nic.setText(selectedItem.getDrvNic());
                txt_name.setText(selectedItem.getDrvName());
            }
        });
    }

    private void img_back() throws IOException {
        URL resource = this.getClass().getResource("/lk/nsbm/dep/booking/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btn_new_Act(ActionEvent actionEvent) {
        txt_name.clear();
        txt_nic.clear();
        txt_no.clear();
        driver_tbl.getSelectionModel().clearSelection();
        txt_name.setDisable(false);
        txt_no.setDisable(false);
        txt_nic.setDisable(false);
        txt_name.requestFocus();
        btn_upadte.setDisable(false);

        int maxId = 0;

        try {
            String lastCustomerId = driverBO.getLastDriverId();

            if (lastCustomerId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastCustomerId.replace("D", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "D00" + maxId;
            } else if (maxId < 100) {
                id = "D0" + maxId;
            } else {
                id = "D" + maxId;
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
            ObservableList<DriverTM> drivers = driver_tbl.getItems();
            DriverDTO newDriver = new DriverDTO(
                    lbl_dr_id.getText(),
                    txt_name.getText(),
                    txt_no.getText(),
                    txt_nic.getText()
            );
            try {
                driverBO.saveDriver(newDriver);
                drivers.add(new DriverTM(newDriver.getDrvId(), newDriver.getDrvName(),
                        newDriver.getDrvNic(), newDriver.getDrvConNum()));
                btn_new_Act(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
            }
        } else {
            DriverTM selectedDrv = driver_tbl.getSelectionModel().getSelectedItem();
            try {
                driverBO.updateDriver(new DriverDTO(selectedDrv.getDrvId(),
                        txt_name.getText(),
                        txt_nic.getText(),
                        txt_no.getText()));
                selectedDrv.setDrvName(txt_name.getText());
                selectedDrv.setDrvNic(txt_nic.getText());
                selectedDrv.setDrvConNum(txt_no.getText());
                driver_tbl.refresh();
                btn_new_Act(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
            }
        }
    }

    public void btn_del_Act(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete details about this driver?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            DriverTM selecteddriver = driver_tbl.getSelectionModel().getSelectedItem();
            try {
                driverBO.deleteDriver(selecteddriver.getDrvId());
                driver_tbl.getItems().remove(selecteddriver);
            } catch (AlreadyExistsInTicketException e) {
                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE, null, e);
            }
        }
        btn_new_Act(actionEvent);

    }

    public void img_back(MouseEvent event) throws IOException {
        img_back();
    }
}
