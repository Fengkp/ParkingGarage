package garage.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectionsViewController {

    public SelectionsViewController() {}

    public void openTicketBtn(ActionEvent event) throws IOException {
        Parent openTicketViewParent = FXMLLoader.load(getClass().getResource("OpenTicketView.fxml"));
        Scene openTicketScene = new Scene(openTicketViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(openTicketScene);
        window.show();
    }

    public void retrieveTicketBtn(ActionEvent event) throws IOException {
        Parent closeTicketViewParent = FXMLLoader.load(getClass().getResource("CloseTicketView.fxml"));
        Scene closeTicketViewScene = new Scene(closeTicketViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(closeTicketViewScene);
        window.show();
    }



}
