package lk.nsbm.dep.booking.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nsbm.dep.booking.db.DBConnection;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public Label txt_con;
    public Label txt_wel;
    public ImageView sch;
    public ImageView pas;
    public ImageView bus;
    public ImageView con;
    public ImageView drv;
    public ImageView tkt;
    public AnchorPane root;

    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    @FXML
    private void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            txt_wel.setText("Welcome");
            txt_con.setText("Please select one of above main operations to proceed");
        }
    }

    @FXML
    private void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "drv":
                    txt_wel.setText("Manage Drivers");
                    txt_con.setText("Click to add, edit, delete, search Drivers");
                    break;
                case "bus":
                    txt_wel.setText("Bus Details");
                    txt_con.setText("Click to add, edit, delete, search Buses");
                    break;
                case "con":
                    txt_wel.setText("Conductor Details");
                    txt_con.setText("Click to add, edit, delete, search Conductor");
                    break;
                case "pass":
                    txt_wel.setText("Passenger Details");
                    txt_con.setText("Click to add, edit, delete, search Passenger");
                    break;
                case "sch":
                    txt_wel.setText("Search Ticket Details");
                    txt_con.setText("Click if you want to search and delete tickets");
                    break;
                case "tkt":
                    txt_wel.setText("Purchase Ticket");
                    txt_con.setText("Click if you want to Purchase a Ticket");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.BROWN);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }


    @FXML
    private void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            FXMLLoader fxmlLoader = null;
            switch (icon.getId()) {
                case "con":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/nsbm/dep/booking/view/CondForm.fxml"));
                    break;
                case "drv":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/nsbm/dep/booking/view/DriverForm.fxml"));
                    break;
                case "bus":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/nsbm/dep/booking/view/BusForm.fxml"));
                    break;
                case "pas":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/nsbm/dep/booking/view/PassForm.fxml"));
                    break;
                case "sch":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/nsbm/dep/booking/view/SearchForm.fxml"));
                    break;
                case "tkt":
                    fxmlLoader = new FXMLLoader(this.getClass().getResource("/lk/nsbm/dep/booking/view/TicketForm.fxml"));
                    root = fxmlLoader.load();
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();

                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void btn_bcup(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the DB Backup File");
        fileChooser.getExtensionFilters().
                add(new FileChooser.ExtensionFilter("SQL File", "*.sql"));
        File file = fileChooser.showSaveDialog(this.root.getScene().getWindow());
        if (file != null) {
            try {
                Process process = Runtime.getRuntime().
                        exec("mysqldump -h" + DBConnection.host + " -u" + DBConnection.username +
                                " -p" + DBConnection.password + " " + DBConnection.db + " --result-file " +
                                file.getAbsolutePath() + ((file.getAbsolutePath().endsWith(".sql")) ? "" : ".sql"));
                int exitCode = process.waitFor();
                if (exitCode != 0) {
                    InputStream errorStream = process.getErrorStream();
                    InputStreamReader isr = new InputStreamReader(errorStream);
                    BufferedReader br = new BufferedReader(isr);
                    String out = "";
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        out += line + "\n";
                    }
                    System.out.println(out);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Database backup created successfully").show();
                }
                System.out.println(exitCode);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void btn_rest(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Restore the backup File");
        fileChooser.getExtensionFilters().
                add(new FileChooser.ExtensionFilter("SQL File", "*.sql"));
        File file = fileChooser.showOpenDialog(this.root.getScene().getWindow());
        if (file != null) {

        }
    }

    public void logOut(MouseEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(this.getClass().getResource("/lk/nsbm/dep/booking/view/LoginForm.fxml"));
        Stage stage = (Stage) (this.root.getScene().getWindow());
        Scene scene = new Scene(load.load());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
