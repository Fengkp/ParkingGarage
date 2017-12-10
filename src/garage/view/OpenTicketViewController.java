package garage.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import garage.model.TicketSystem;

import java.io.IOException;

public class OpenTicketViewController extends UniversalComponents {
    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText;
    @FXML
    private TextField licensePlateText;

    private String firstName;
    private String lastName;
    private String licensePlate;

    public void submitBtn(ActionEvent event) throws IOException {
        if (firstNameText.getText().isEmpty()
                || lastNameText.getText() .isEmpty() || licensePlateText.getText().isEmpty())
            super.errorBox("Missing Information", "Please fill in all the information.");
        else {
            firstName = firstNameText.getText();
            lastName = lastNameText.getText();
            licensePlate = licensePlateText.getText();
            TicketSystem.getInstance().openTicket(firstName, lastName, licensePlate);
            super.goBackBtn(event);
        }
    }

    public void goBackBtn(ActionEvent event) throws IOException {
        super.goBackBtn(event);
    }


}
