package garage.view;

import garage.model.TicketSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.DecimalFormat;

public class PaymentViewController extends UniversalComponents {
    @FXML
    private TextField paymentText;

    public void calculateBtn() {
        double payment = TicketSystem.getInstance().getClosedTicket().calculatePayment(TicketSystem.getInstance().getPRICE_PER_HOUR());
        DecimalFormat df = new DecimalFormat("#.00");
        paymentText.setText("$" + df.format(payment));
    }

    public void paidBtn(ActionEvent event) throws IOException {
        super.goBackBtn(event);
    }
}
