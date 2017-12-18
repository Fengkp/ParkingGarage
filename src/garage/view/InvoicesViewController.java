package garage.view;

import garage.model.InvoiceSystem;
import garage.model.TicketSystem;
import garage.model.ticket.Invoice;
import garage.model.ticket.Ticket;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class InvoicesViewController extends UniversalController {
    @FXML
    private TableView<Invoice> table;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn yearCol;
    @FXML
    private TableColumn monthCol;
    @FXML
    private TableColumn dayCol;
    @FXML
    private TableColumn timeCol;
    @FXML
    private TableColumn ticketNumCol;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn amountCol;

    public void init() {
        ObservableList<Invoice> data = InvoiceSystem.getInstance().getInvoices();
        FilteredList<Invoice> filteredData = new FilteredList<>(data, p -> true);

        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        dayCol.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        ticketNumCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("totalPay"));

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(invoice -> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseFilter = newValue.toLowerCase();
                if (invoice.getYear().toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if (invoice.getMonth().toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if (invoice.getDepartureDate().contains(lowerCaseFilter))
                    return true;
                else if (invoice.getDepartureTime().contains(lowerCaseFilter))
                    return true;
                else if (invoice.getId().contains(lowerCaseFilter))
                    return true;
                else if (invoice.getCustomerName().toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if (invoice.getTotalPay().contains(lowerCaseFilter))
                    return true;
                return false;
            });
        });

        SortedList<Invoice> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                showDetails(newValue);
            } catch(IOException e) {
                e.printStackTrace();
            }});
    }

    public void showDetails(Invoice invoice) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("InvoiceDetailsView.fxml"));
        Parent root = loader.load();
        InvoiceDetailsViewController controller = loader.getController();
        controller.init(invoice);

        Stage window = new Stage();
        window.setTitle("Invoice Details");
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    public void goBackBtn(ActionEvent event) throws IOException {
        super.goBackBtn(event);
    }
}
