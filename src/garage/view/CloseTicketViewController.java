package garage.view;

import garage.model.ticket.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import garage.model.TicketSystem;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class CloseTicketViewController extends UniversalController {
    @FXML
    private TextField ticketNumText;
    @FXML
    private Label ticketNumLbl;
    @FXML
    private Label levelLbl;
    @FXML
    private Label spaceNumLbl;
    @FXML
    private Label licensePlateLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private Label arrivalLbl;

    private String ticketNum;

    public void submitBtn() {
        ticketNum = ticketNumText.getText();

        if (TicketSystem.getInstance().findTicket(ticketNum)) {
            Ticket currentTicket = TicketSystem.getInstance().getTicket(ticketNum);
            ticketNumLbl.setText(currentTicket.getId());
            levelLbl.setText(currentTicket.getLevel());
            spaceNumLbl.setText(currentTicket.getSpaceNum());
            licensePlateLbl.setText(currentTicket.getLicensePlate());
            nameLbl.setText(currentTicket.getCustomerName());
            arrivalLbl.setText(currentTicket.getArrivalTime());
        } else {
            ticketNumLbl.setText("Not Found.");
            levelLbl.setText(null);
            spaceNumLbl.setText(null);
            licensePlateLbl.setText(null);
            nameLbl.setText(null);
            arrivalLbl.setText(null);
        }
    }

    public void closeTicketBtn() throws IOException {
        if (TicketSystem.getInstance().findTicket(ticketNum)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("PaymentView.fxml"));
            Parent root = loader.load();
            PaymentViewController controller = loader.getController();
            controller.init(ticketNum);

            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Confirm Ticket");
            Scene scene = new Scene(root);
            window.setResizable(false);
            window.setScene(scene);
            window.show();
        } else
            super.errorBox("Invalid Ticket Number", "Please enter a valid ticket number.");
    }

    public void goBackBtn(ActionEvent event) throws IOException {
        super.goBackBtn(event);
    }

}
