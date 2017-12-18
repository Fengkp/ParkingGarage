package garage;

import garage.model.InvoiceSystem;
import garage.model.TicketSystem;
import garage.view.SelectionsViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/SelectionsView.fxml"));
        Parent root = loader.load();
        SelectionsViewController controller = loader.getController();
        controller.init();

        TicketSystem.load();
        InvoiceSystem.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Parking Garage");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}