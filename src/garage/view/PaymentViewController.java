package garage.view;

import garage.model.InvoiceSystem;
import garage.model.TicketSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class PaymentViewController extends UniversalController {
    @FXML
    private TextField cardNameText;
    @FXML
    private TextField cardNumText;
    @FXML
    private TextField monthExpText;
    @FXML
    private TextField yearExpText;
    @FXML
    private CheckBox cardBox;
    @FXML
    private CheckBox cashBox;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label orderTotalLbl;
    @FXML
    private Label beforeTaxLbl;
    @FXML
    private Label durationLbl;
    @FXML
    private Label spaceTypeLbl;

    private String ticketNum;

    public void calculatePayment() {
        double payment = TicketSystem.getInstance().getClosedTicket().calculatePay();
        double totalPay = TicketSystem.getInstance().getClosedTicket().calculateTaxPay();
        DecimalFormat df = new DecimalFormat("#.00");
        beforeTaxLbl.setText("$" + df.format(payment));
        orderTotalLbl.setText("$" + df.format(totalPay));
    }

    public void init(String ticketNum) {
        this.ticketNum = ticketNum;
        spaceTypeLbl.setText("Regular");
        durationLbl.setText(TicketSystem.getInstance().getTicket(ticketNum).getDuration() + " Hour(s)");
        TicketSystem.getInstance().closeTicket(ticketNum);
        TicketSystem.save();
        InvoiceSystem.save();
        calculatePayment();
        cardNameText.setDisable(true);
        cardNumText.setDisable(true);
        monthExpText.setDisable(true);
        yearExpText.setDisable(true);
    }

    public void cardBox() {
        if (!cardBox.isSelected()) {
            cashBox.setDisable(false);
            cardNameText.setDisable(true);
            cardNumText.setDisable(true);
            monthExpText.setDisable(true);
            yearExpText.setDisable(true);
        }
        if (cardBox.isSelected()) {
            cashBox.setDisable(true);
            cardBox.setDisable(false);
            cardNameText.setDisable(false);
            cardNumText.setDisable(false);
            monthExpText.setDisable(false);
            yearExpText.setDisable(false);
        }
    }

    public void cashBox() {
        if (!cashBox.isSelected())
            cardBox.setDisable(false);
        if (cashBox.isSelected()) {
            cardBox.setDisable(true);
            cardNameText.setDisable(true);
            cardNumText.setDisable(true);
            monthExpText.setDisable(true);
            yearExpText.setDisable(true);
        }
    }

    public void paidBtn(ActionEvent event) throws IOException {
        super.goBackBtn(event);
    }

    public void cancelBtn() {
        Stage window = (Stage) cancelBtn.getScene().getWindow();
        window.close();
    }
}
