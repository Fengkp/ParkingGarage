package garage.view;

import garage.model.TicketSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class SelectionsViewController extends UniversalController{
    @FXML
    private Label spacesAvailLbl;

    public void init() {
        spacesAvailLbl.setText(TicketSystem.getInstance().getSpaceCount());
    }

    public void openTicketBtn(ActionEvent event) throws IOException {
        if (Integer.parseInt(TicketSystem.getInstance().getSpaceCount()) > 0) {
            Parent root = FXMLLoader.load(getClass().getResource("OpenTicketView.fxml"));
            Scene openTicketScene = new Scene(root);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setTitle("Open Ticket");
            window.setResizable(false);
            window.setScene(openTicketScene);
            window.show();
        }
    }

    public void retrieveTicketBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CloseTicketView.fxml"));
        Scene closeTicketViewScene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Close Ticket");
        window.setScene(closeTicketViewScene);
        window.show();
    }

    public void findTicketBtn() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FindTicketView.fxml"));
        Parent root = loader.load();
        FindTicketViewController controller = loader.getController();
        controller.init();

        Stage window = new Stage();
        window.setTitle("Find Ticket");
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    public void invoicesBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("InvoicesView.fxml"));
        Parent root = loader.load();
        InvoicesViewController controller = loader.getController();
        controller.init();

        Scene closeTicketViewScene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setTitle("Invoices");
        window.setResizable(false);
        window.setScene(closeTicketViewScene);
        window.show();
    }
}
