package de.ostfale.app.person.controller;

import de.ostfale.app.person.controller.PersonController;
import de.ostfale.common.BaseHandler;
import javafx.scene.layout.AnchorPane;

public class PersonHandler extends BaseHandler<PersonController, AnchorPane> {

    private static final String FXML_PATH = "fxml/person.fxml";

    public PersonHandler() {
        initHandler();
    }

    @Override
    protected String getFXMLPath() {
        return FXML_PATH;
    }
}
