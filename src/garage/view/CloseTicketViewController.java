package garage.view;

import garage.model.ticket.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import garage.model.TicketSystem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CloseTicketViewController extends UniversalComponents {
    @FXML
    private TextField ticketNumText;
    @FXML
    private Label ticketNumLbl;
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
            spaceNumLbl.setText(currentTicket.getSpaceNum());
            licensePlateLbl.setText(currentTicket.getLicensePlate());
            nameLbl.setText(currentTicket.getCustomer().getName());
            arrivalLbl.setText(currentTicket.getArrival());
        } else {
            ticketNumLbl.setText("Not Found.");
            spaceNumLbl.setText(null);
            licensePlateLbl.setText(null);
            nameLbl.setText(null);
            arrivalLbl.setText(null);
        }
    }

    public void closeTicketBtn(ActionEvent event) throws IOException {
        if (TicketSystem.getInstance().findTicket(ticketNum)) {
            TicketSystem.getInstance().closeTicket(ticketNum);
            TicketSystem.save();

            Parent paymentViewParent = FXMLLoader.load(getClass().getResource("PaymentView.fxml"));
            Scene paymentViewScene = new Scene(paymentViewParent);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(paymentViewScene);
            window.show();
        } else
            super.errorBox("Invalid Ticket Number", "Please enter a valid ticket number.");
    }

    public void goBackBtn(ActionEvent event) throws IOException {
        super.goBackBtn(event);
    }

}
