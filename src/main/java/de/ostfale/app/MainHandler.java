package de.ostfale.app;

import de.ostfale.common.BaseHandler;
import javafx.scene.layout.AnchorPane;

public class MainHandler extends BaseHandler<MainController, AnchorPane> {

    private static final String FXML_PATH = "fxml/main.fxml";

    public MainHandler() {
        initHandler();
    }

    @Override
    protected String getFXMLPath() {
        return FXML_PATH;
    }
}
