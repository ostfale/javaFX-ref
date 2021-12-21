package de.ostfale.tableviewexample;

import de.ostfale.MainApp;
import de.ostfale.app.BootstrapUtil;
import de.ostfale.common.BaseController;
import de.ostfale.common.DataModel;
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

    private DataModel<Person> dm = new DataModel<>();

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
        dm.setObjectList(BootstrapUtil.createPersonList());
        col_firstName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().firstName()));
        col_lastName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().lastName()));
        col_gender.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().gender()));
        col_birthday.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().birthday()));
        col_email.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().email()));
        calculateColSize();
        tv_person.getItems().addAll(dm.getObjectList());
    }

    private void calculateColSize() {
        col_firstName.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
        col_lastName.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
        col_gender.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.1));
        col_birthday.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
        col_email.prefWidthProperty().bind(tv_person.widthProperty().multiply(0.2));
    }
}
