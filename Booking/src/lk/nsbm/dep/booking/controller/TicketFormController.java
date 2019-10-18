package lk.nsbm.dep.booking.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.nsbm.dep.booking.business.BOFactory;
import lk.nsbm.dep.booking.business.BOType;
import lk.nsbm.dep.booking.business.custom.BusBO;
import lk.nsbm.dep.booking.business.custom.PassengerBO;
import lk.nsbm.dep.booking.business.custom.TicketBO;
import lk.nsbm.dep.booking.dto.TicketDTO;
import lk.nsbm.dep.booking.util.TicketTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketFormController implements Initializable {
    public AnchorPane root;
    public JFXTextField txt_pass_name;
    public JFXTextField txt_bus_rot;
    public JFXTextField txt_bs_con_Id;
    public Label lbl_tk_id;
    public JFXButton btn_book;
    public JFXComboBox cmb_pass_Id;
    public JFXComboBox cmb_bus_id;
    public JFXTextField txt_bus_drv_id;
    public JFXComboBox cmb_time;
    public Label txt_tot;
    public JFXTextField pass_nic;
    public TableView<TicketTM> ticket_tbl;
    public JFXDatePicker txt_date;
    public Label lbl_Date;

    private PassengerBO passengerBO = BOFactory.getInstance().getBO(BOType.PASSENGER);
    private BusBO busBO = BOFactory.getInstance().getBO(BOType.BUS);
    private TicketBO ticketBO = BOFactory.getInstance().getBO(BOType.TICKET);


    public void initialize(URL url, ResourceBundle rb)
//    private String ticketNo;
//    private String pssId;
//    private String busId;
//    private Date date;
//    private String tripNo;
//    private int seatNo;
//    private int price;
    {
        ticket_tbl.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ticketNo"));
        ticket_tbl.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("passId"));
        ticket_tbl.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("busId"));
        ticket_tbl.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("date"));
        ticket_tbl.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("tripNo"));
        ticket_tbl.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("seatNo"));
        ticket_tbl.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("price"));


        txt_bs_con_Id.setDisable(true);
        txt_bus_drv_id.setDisable(true);
        txt_bus_rot.setDisable(true);
        txt_pass_name.setDisable(true);
        pass_nic.setDisable(true);

        lbl_Date.setText(LocalDate.now() + "");

        try {
            List<String> passenger = passengerBO.getAllPassengerIDs();
            cmb_pass_Id.setItems(FXCollections.observableArrayList(passenger));

            List<String> buses = busBO.getAllBusIDs();
            cmb_bus_id.setItems(FXCollections.observableArrayList(buses));

            List<TicketDTO> allTickets = ticketBO.findAllTicket();

            ObservableList<TicketTM> ticketTMS = ticket_tbl.getItems();
            for (TicketDTO t : allTickets) {
                ticketTMS.add(new TicketTM(t.getTicketNo(), t.getPassId(), t.getBusId(), t.getDate(),
                        t.getTripNo(), t.getSeatNo(), t.getPrice()));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            Logger.getLogger("lk.nsbm.dep.booking.controller").log(Level.SEVERE, null, e);
        }

        ticket_tbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TicketTM>() {
            @Override
            public void changed(ObservableValue<? extends TicketTM> observable, TicketTM oldValue, TicketTM newValue) {
                TicketTM selectItem = ticket_tbl.getSelectionModel().getSelectedItem();

                if (selectItem == null) {
                    btn_book.setText("Book");
                }

                btn_book.setText("Update");
                txt_date.setPromptText(String.valueOf(ticket_tbl.getSelectionModel().getSelectedItem().getDate()));
                cmb_bus_id.setId(selectItem.getBusId());
                cmb_pass_Id.setId(selectItem.getPassId());
                cmb_time.setId(selectItem.getTripNo());
                txt_tot.setText(String.valueOf(selectItem.getPrice()));
                lbl_tk_id.setText(selectItem.getTicketNo());
            }
        });
    }


    public void btn_new_Act(ActionEvent actionEvent) {
    }

    public void btn_del_Act(ActionEvent actionEvent) {
    }

    public void img_bk(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/nsbm/dep/booking/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }

    public void btn_dlt(MouseEvent event) {
    }
}
