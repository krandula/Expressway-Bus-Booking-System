import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lk.nsbm.dep.booking.db.DBConnection;

import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {

            Logger rootLogger = Logger.getLogger("");
            FileHandler fileHandler = new FileHandler("error.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            rootLogger.addHandler(fileHandler);

            DBConnection.getInstance().getConnection();
            URL resource = this.getClass().getResource("/lk/nsbm/dep/booking/view/LoginForm.fxml");
            Parent root = FXMLLoader.load(resource);
            Scene mainScene = new Scene(root);
            primaryStage.setScene(mainScene);
            primaryStage.setTitle("Southern-Expressway");
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setWidth(700);
            primaryStage.setHeight(600);
            primaryStage.show();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, Please contact randula-jayarathna").show();
            Logger.getLogger("lk.nsbm.dep.booking").log(Level.SEVERE, null, e);
        }

    }
}
