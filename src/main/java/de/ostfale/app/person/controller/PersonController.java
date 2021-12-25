package de.ostfale.app.person.controller;

import de.ostfale.app.BootstrapUtil;
import de.ostfale.app.person.model.Gender;
import de.ostfale.app.person.model.Person;
import de.ostfale.common.BaseController;
import de.ostfale.common.DataModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PersonController extends BaseController<Person> {

    private final Logger log = LoggerFactory.getLogger(PersonController.class);


    @FXML
    private TableView<Person> tv_person;

    @FXML
    private TableColumn<Person, String> col_firstName;

    @FXML
    private TableColumn<Person, String> col_lastName;

    @FXML
    private TableColumn<Person, Gender> col_gender;

    @FXML
    private TableColumn<Person, LocalDate> col_birthday;

    @FXML
    private TableColumn<Person, String> col_email;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.debug("Initialize Person controller");

        col_firstName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().firstName()));
        col_firstName.setCellFactory(TextFieldTableCell.forTableColumn());
        col_firstName.setOnEditCommit(e -> {
            log.debug("Start editing first name...");
            var value = e.getNewValue() != null ? e.getNewValue() : e.getOldValue();
            var cvf = col_firstName.getCellValueFactory();
            var dd = e.getTableView().getItems().get(e.getTablePosition().getRow());
            var modifiedPerson = new Person(value, dd.lastName(), dd.email(), dd.gender(), dd.birthday());
            dataModel.getObjectList().remove(dd);
            dataModel.getObjectList().add(modifiedPerson);
        });

        col_lastName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().lastName()));
        col_gender.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().gender()));
        col_birthday.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().birthday()));
        col_email.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().email()));
        calculateColSize();

        initDataModel();
        new PersonContextMenuHandler(tv_person, dataModel).initContextMenu();
    }

    private void initDataModel() {
        dataModel = new DataModel<>();
        changeListener = new PersonChangeListener();
        comparator = (person1, person2) -> {
            var res = person1.lastName().compareTo(person2.lastName());
            if (res == 0) return person1.firstName().compareTo(person2.firstName());
            return res;
        };
        dataModel.setChangeListener(changeListener);
        dataModel.setComparator(comparator);
        dataModel.updateModel(BootstrapUtil.createPersonList(), tv_person);
    }

    private void calculateColSize() {
        col_firstName.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
        col_lastName.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
        col_gender.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.1));
        col_birthday.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
        col_email.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
    }

    private void setTableEditable(TableView<Person> tableView) {
        tableView.setEditable(true);
        tableView.getSelectionModel().cellSelectionEnabledProperty().set(true);
        tableView.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().isLetterKey()) {
                editFocusedCell(tableView);
            } else if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.TAB) {
                selectNextPosition(tableView);
                keyEvent.consume();
            } else if (keyEvent.getCode() == KeyCode.LEFT) {
                tableView.getSelectionModel().selectPrevious();
                keyEvent.consume();
            }
        });
    }

    private void selectNextPosition(TableView<Person> tableView) {
        log.debug("Select next position via RIGHT or TAB");
    }

    @SuppressWarnings("unchecked")
    private void editFocusedCell(TableView<Person> tableView) {
        var focusedCell = tableView.focusModelProperty().get().focusedCellProperty().get();
        tableView.edit(focusedCell.getRow(), focusedCell.getTableColumn());
    }
}
