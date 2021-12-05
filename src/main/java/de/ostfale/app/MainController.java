package de.ostfale.app;

import de.ostfale.MainApp;
import de.ostfale.common.BaseController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends BaseController<Void> implements Initializable {

    private final Logger log = LoggerFactory.getLogger(MainApp.class);


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.debug("Initialize main controller");
    }

    @FXML
    void viewDashboard(ActionEvent event) {
        log.debug("View Dashboard View");
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
