package lk.nsbm.dep.booking.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.nsbm.dep.booking.business.BOFactory;
import lk.nsbm.dep.booking.business.BOType;
import lk.nsbm.dep.booking.business.custom.BusBO;
import lk.nsbm.dep.booking.business.custom.ConductorBO;
import lk.nsbm.dep.booking.business.custom.DriverBO;
import lk.nsbm.dep.booking.business.exception.AlreadyExistsInTicketException;
import lk.nsbm.dep.booking.dto.BusDTO;
import lk.nsbm.dep.booking.util.BusTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BusFromController implements Initializable {
    public AnchorPane root;
    public JFXTextField tct_bs_no;
    public Label txt_bs_id;
    public JFXTextField txt_bs_st;
    public JFXComboBox<String> cmb_rt_no;
    public JFXComboBox cmb_drv_id;
    public JFXComboBox cmb_cnd_id;
    public JFXButton btn_new;
    public JFXButton btn_update;
    public JFXButton btn_dlt;
    public TableView<BusTM> bus_tbl;
    ObservableList<String> list = FXCollections.observableArrayList("Kahathuduwa", "Gelanigama",
            "Dodangoda", "Welipanna", "Kurundugahahetekma", "Baddegama", "Pinnaduwa", "Imaduwa", "Kokmaduwa", "Godagama");
    private DriverBO driverBO = BOFactory.getInstance().getBO(BOType.DRIVER);
    private ConductorBO conductorBO = BOFactory.getInstance().getBO(BOType.CONDUCTOR);
    private BusBO busBO = BOFactory.getInstance().getBO(BOType.BUS);

    public void initialize(URL url, ResourceBundle rb) {
        bus_tbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("busId"));
        bus_tbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("busNo"));
        bus_tbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("seat"));
        bus_tbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("route"));
        bus_tbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("drvId"));
        bus_tbl.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("conId"));

        txt_bs_id.setDisable(true);
        txt_bs_st.setDisable(true);
        cmb_cnd_id.setDisable(true);
        cmb_drv_id.setDisable(true);
        cmb_rt_no.setDisable(true);

        cmb_rt_no.setItems(list);

        try {
            List<String> ids = driverBO.getAllDriverIDs();
            cmb_drv_id.setItems(FXCollections.observableArrayList(ids));

            List<String> conids = conductorBO.getAllConductorIDs();
            cmb_cnd_id.setItems(FXCollections.observableArrayList(conids));

            List<BusDTO> allbus = busBO.findAllbus();
            ObservableList<BusTM> busTMS = bus_tbl.getItems();
            for (BusDTO b : allbus) {
                busTMS.add(new BusTM(b.getBusId(), b.getBusNo(), b.getSeat(),
                        b.getRoute(), b.getDrvID(), b.getConId()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
        }

        bus_tbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BusTM>() {
            @Override
            public void changed(ObservableValue<? extends BusTM> observable, BusTM oldValue, BusTM newValue) {
                BusTM selectedItem = bus_tbl.getSelectionModel().getSelectedItem();

                if (selectedItem == null) {
                    btn_update.setText("Save");
                    btn_new.setDisable(true);
                    return;
                }

                btn_update.setText("Update");
                clear();

                txt_bs_id.setText(selectedItem.getBusId());
                tct_bs_no.setText(selectedItem.getBusNo());
                txt_bs_st.setText(String.valueOf(selectedItem.getSeat()));
                cmb_rt_no.setId(selectedItem.getRoute());
                cmb_cnd_id.setId(selectedItem.getConId());
                cmb_drv_id.setId(selectedItem.getDrvId());
            }
        });
    }

    public void clear() {
        btn_update.setDisable(false);
        btn_dlt.setDisable(false);
        txt_bs_id.setDisable(false);
        txt_bs_st.setDisable(false);
        tct_bs_no.setDisable(false);
        cmb_cnd_id.setDisable(false);
        cmb_drv_id.setDisable(false);
        cmb_rt_no.setDisable(false);

    }

    public void btn_new_Action(ActionEvent actionEvent) {
        txt_bs_st.clear();
        txt_bs_id.setText("");
        tct_bs_no.clear();
        bus_tbl.getSelectionModel().clearSelection();
        clear();

        int maxId = 0;

        try {
            String lastId = busBO.getLastBusId();

            if (lastId == null) {
                maxId = 0;
            } else {
                maxId = Integer.parseInt(lastId.replace("B", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "B00" + maxId;
            } else if (maxId < 100) {
                id = "B0" + maxId;
            } else {
                id = "B" + maxId;
            }
            txt_bs_id.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
        }
    }

    public void btn_update_act(ActionEvent actionEvent) {
        if (btn_update.getText().equals("Save")) {
            ObservableList<BusTM> busTMS = bus_tbl.getItems();
            BusDTO newBUs = new BusDTO(
                    txt_bs_id.getText(),
                    tct_bs_no.getText(),
                    txt_bs_st.getText(),
                    cmb_rt_no.getId(),
                    cmb_drv_id.getId(),
                    cmb_cnd_id.getId());
            try {
                busBO.saveBus(newBUs);
                busTMS.add(new BusTM(newBUs.getBusId(), newBUs.getBusNo(), newBUs.getSeat(),
                        newBUs.getRoute(), newBUs.getDrvID(), newBUs.getConId()));
                btn_update_act(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong with the table").show();
                Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
            }
        } else {
            BusTM selectedItem = bus_tbl.getSelectionModel().getSelectedItem();
            try {
                busBO.updateBus(new BusDTO(selectedItem.getBusId(), tct_bs_no.getText(), txt_bs_st.getText(),
                        cmb_rt_no.getId(), cmb_drv_id.getId(), cmb_cnd_id.getId()));
                selectedItem.setBusNo(tct_bs_no.getText());
                selectedItem.setSeat(Integer.parseInt(txt_bs_st.getText()));
                selectedItem.setRoute(cmb_rt_no.getId());
                selectedItem.setConId(cmb_cnd_id.getId());
                bus_tbl.refresh();
                btn_update_act(actionEvent);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
            }
        }
    }

    public void btn_dlt_Act(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure whether you want to delete details about this bus?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            BusTM selectedItem = bus_tbl.getSelectionModel().getSelectedItem();
            try {
                busBO.deleteBus(selectedItem.getBusId());
                bus_tbl.getItems().remove(selectedItem);
            } catch (AlreadyExistsInTicketException e) {
                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                Logger.getLogger("lk.ijse.dep.pos.controller").log(Level.SEVERE, null, e);
            }
        }
    }

    public void img_bk(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/nsbm/dep/booking/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();

    }
}
