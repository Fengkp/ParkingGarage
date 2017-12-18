package garage.view;

import garage.model.TicketSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketConfirmationViewController extends UniversalController {
    @FXML
    private Label licensePlateLbl;
    @FXML
    private Label spaceLbl;
    @FXML
    private Label arrivalLbl;
    @FXML
    private TextField ticketNumText;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button closeBtn;

    private String firstName;
    private String lastName;
    private String licensePlate;
    private String phoneNum;

    public void init(String firstName, String lastName, String licensePlate, String phoneNum) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.licensePlate = licensePlate;
        this.phoneNum = phoneNum;
        licensePlateLbl.setText(licensePlate);
        arrivalLbl.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a")));
        spaceLbl.setText(TicketSystem.getInstance().nextOpenSpace().getID());
    }

    public void confirmBtn() {
        TicketSystem.getInstance().openTicket(firstName, lastName, licensePlate, phoneNum);
        TicketSystem.save();
        ticketNumText.setText(TicketSystem.getInstance().getTicket(licensePlate).getId().toLowerCase());
        confirmBtn.setDisable(true);
    }

    public void closeBtn() {
        Stage window = (Stage) closeBtn.getScene().getWindow();
        window.close();
    }
}
