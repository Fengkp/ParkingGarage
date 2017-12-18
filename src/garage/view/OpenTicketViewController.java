package garage.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import garage.model.TicketSystem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenTicketViewController extends UniversalController {
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField licensePlateText;
    @FXML
    private TextField phoneNumText;

    private String firstName;
    private String lastName;
    private String licensePlate;
    private String phoneNum;

    public void submitBtn() throws IOException {
        if (firstNameText.getText().isEmpty()
                || lastNameText.getText() .isEmpty() || licensePlateText.getText().isEmpty() || phoneNumText.getText().isEmpty())
            super.errorBox("Missing Information", "Please fill in all the information.");
        else if (TicketSystem.getInstance().findTicket(licensePlateText.getText()))
            super.errorBox("Duplicate License Plate", "There is already a vehicle parked with that license plate.");
        else {
            setData();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TicketConfirmationView.fxml"));
            Parent root = loader.load();
            TicketConfirmationViewController controller = loader.getController();
            controller.init(firstName, lastName, licensePlate, phoneNum);

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Confirm Ticket");
            Scene scene = new Scene(root);
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        }
    }

    public void setData() {
        firstName = firstNameText.getText();
        lastName = lastNameText.getText();
        licensePlate = licensePlateText.getText();
        phoneNum = phoneNumText.getText();
    }

    public void goBackBtn(ActionEvent event) throws IOException {
        super.goBackBtn(event);
    }


}
