package garage.view;

import garage.model.TicketSystem;
import garage.model.ticket.Ticket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class FindTicketViewController {
    @FXML
    private TableView<Ticket> table;
    @FXML
    private TextField filterField;
    @FXML
    private TableColumn ticketNumCol;
    @FXML
    private TableColumn levelCol;
    @FXML
    private TableColumn spaceCol;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn plateNumCol;
    @FXML
    private TableColumn phoneNumCol;

    public void init() {
        ObservableList<Ticket> data = TicketSystem.getInstance().getOpenTickets();
        FilteredList<Ticket> filteredData = new FilteredList<>(data, p -> true);

        ticketNumCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        levelCol.setCellValueFactory(new PropertyValueFactory<>("level"));
        spaceCol.setCellValueFactory(new PropertyValueFactory<>("spaceNum"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        plateNumCol.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(ticket -> {
                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseFilter = newValue.toLowerCase();
                if (ticket.getCustomerName().toLowerCase().contains(lowerCaseFilter))
                    return true;
                else if (ticket.getId().contains(lowerCaseFilter))
                    return true;
                else if (ticket.getLicensePlate().contains(lowerCaseFilter))
                    return true;
                else if (ticket.getPhoneNum().contains(lowerCaseFilter))
                    return true;
                return false;
            });
        });

        SortedList<Ticket> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }

}
