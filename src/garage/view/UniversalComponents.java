package garage.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class UniversalComponents {

    public void goBackBtn(ActionEvent event) throws IOException {
        Parent selectionsViewParent = FXMLLoader.load(getClass().getResource("SelectionsView.fxml"));
        Scene selectionsViewScene = new Scene(selectionsViewParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(selectionsViewScene);
        window.show();
    }

    public void errorBox(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
