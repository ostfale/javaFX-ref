package de.ostfale.tableviewexample;

import de.ostfale.MainApp;
import de.ostfale.common.BaseController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PersonController extends BaseController<Person> {

    private final Logger log = LoggerFactory.getLogger(MainApp.class);

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.debug("Initialize Person controller");
        col_firstName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().firstName()));
        col_lastName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().lastName()));
        col_gender.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().gender()));
        col_birthday.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().birthday()));
        initTable();
    }

    private void initTable() {
        tv_person.getItems().addAll(
                new Person("Justin", "Smith", Gender.MALE, LocalDate.of(1966, 3, 23)),
                new Person("Sheila", "Smith", Gender.MALE, LocalDate.of(1976, 7, 3))
        );
    }
}
