package lk.nsbm.dep.booking.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SearchFormController {
    public AnchorPane root;
    public JFXTextField txt_sch;
    public TableView sch_tbl;

    public void img_bk(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/lk/nsbm/dep/booking/view/MainForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
    }
}
