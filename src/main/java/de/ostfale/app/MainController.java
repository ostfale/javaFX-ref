package de.ostfale.app;

import de.ostfale.common.BaseController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends BaseController<Void> implements Initializable {

    @FXML
    private AnchorPane apMain;

    @FXML
    private MenuBar mBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void closeApplication(ActionEvent event) {
        Platform.exit();
    }
}
