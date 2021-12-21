package de.ostfale.app;

import de.ostfale.MainApp;
import de.ostfale.common.BaseController;
import de.ostfale.tableviewexample.PersonHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends BaseController<Void> {

    private final Logger log = LoggerFactory.getLogger(MainApp.class);

    private final PersonHandler personHandler = new PersonHandler();

    @FXML
    private BorderPane bp_root;

    @FXML
    private AnchorPane ap_main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.debug("Initialize main controller");
    }

    @FXML
    void viewTableViewExample(ActionEvent event) {
        log.debug("View Table View styling example");
        var ap = personHandler.getUiRoot();
        AnchorPane.setTopAnchor(ap, 0.0);
        AnchorPane.setBottomAnchor(ap, 15.0);
        AnchorPane.setRightAnchor(ap, 15.0);
        AnchorPane.setLeftAnchor(ap, 15.0);
        ap_main.getChildren().add(ap);
    }

    @FXML
    void viewProjects(ActionEvent event) {
        log.debug("View Project View");
    }

    @FXML
    void viewTimeRecordings(ActionEvent event) {
        log.debug("View Time Recordings");
    }


    @FXML
    void closeApplication(ActionEvent event) {
        Platform.exit();
    }
}
