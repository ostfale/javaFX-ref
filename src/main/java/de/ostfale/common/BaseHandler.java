package de.ostfale.common;

import javafx.fxml.FXMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseHandler<T, U> {

    private static final Logger log = LoggerFactory.getLogger(BaseHandler.class);

    protected T uiController;
    protected U uiRoot;
    protected abstract String getFXMLPath();

    private final FXMLLoader fxmlLoader = new FXMLLoader();

    // create resource bundle
    private final String resourcePath = "bundles.bundle";
    ResourceBundle resourceBundle = ResourceBundle.getBundle(resourcePath);

    protected void initHandler() {
      // final URL resource = ClassLoader.getSystemResource(getFXMLPath());
        final URL resource =getClass().getClassLoader().getResource(getFXMLPath());
        fxmlLoader.setLocation(resource);
        fxmlLoader.setResources(resourceBundle);

        try {
            uiRoot = fxmlLoader.load();
            uiController = fxmlLoader.getController();
        } catch (IOException e) {
            log.error("Could not load fxml resources from path: {}", getFXMLPath());
        }
    }

    public T getUiController() {
        return uiController;
    }

    public U getUiRoot() {
        return uiRoot;
    }
}
