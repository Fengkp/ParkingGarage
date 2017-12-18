package garage.view;

import garage.model.ticket.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class InvoiceDetailsViewController {
    @FXML
    private Label ticketNumLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private Label contactLbl;
    @FXML
    private Label plateLbl;
    @FXML
    private Label spaceLbl;
    @FXML
    private Label arrivalLbl;
    @FXML
    private Label departureLbl;
    @FXML
    private Label totalLbl;
    @FXML
    private Button closeBtn;

    public void init(Invoice invoice) {
        ticketNumLbl.setText(invoice.getId());
        nameLbl.setText(invoice.getCustomerName());
        contactLbl.setText(invoice.getPhoneNum());
        plateLbl.setText(invoice.getLicensePlate());
        spaceLbl.setText(invoice.getLevel() + ", " + invoice.getSpaceNum());
        arrivalLbl.setText(invoice.getArrivalTime() + ", " + invoice.getArrivalDate());
        departureLbl.setText(invoice.getDepartureTime() + ", " + invoice.getDepartureDate());
        totalLbl.setText(invoice.getTotalPay());
    }

    public void closeBtn() {
        Stage stage = (Stage)closeBtn.getScene().getWindow();
        stage.close();
    }
}
