package de.ostfale.app.person.controller;

import de.ostfale.app.BootstrapUtil;
import de.ostfale.app.person.model.Gender;
import de.ostfale.app.person.model.Person;
import de.ostfale.app.person.service.PersonService;
import de.ostfale.common.BaseController;
import de.ostfale.common.DataModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PersonController extends BaseController<Person> {

    private final Logger log = LoggerFactory.getLogger(PersonController.class);

    private final DataModel<Person> dm = new DataModel<>();
    private final PersonChangeListener changeListener = new PersonChangeListener();
    private final PersonService personService = new PersonService();

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
        col_lastName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().lastName()));
        col_gender.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().gender()));
        col_birthday.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().birthday()));
        col_email.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().email()));
        calculateColSize();
        createRowContextMenu();
        dm.setComparator(personService.getComparator());
        dm.setChangeListener(changeListener);
        dm.updateModel(BootstrapUtil.createPersonList(), tv_person);
    }

    private void createRowContextMenu() {
        tv_person.setRowFactory(personTableView -> {
            final TableRow<Person> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem addMenuItem = new MenuItem("Add Person");
            final MenuItem removeMenuItem = new MenuItem("Delete Person");
            addMenuItem.setOnAction(actionEvent -> personService.addPerson(dm));
            removeMenuItem.setOnAction(actionEvent -> personService.deletePerson(dm));
            contextMenu.getItems().addAll(addMenuItem,removeMenuItem);
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });
    }

    private void calculateColSize() {
        col_firstName.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
        col_lastName.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
        col_gender.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.1));
        col_birthday.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
        col_email.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
    }
}
