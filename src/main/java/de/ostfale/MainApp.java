package de.ostfale;

import de.ostfale.app.MainHandler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {

    private final Logger log = LoggerFactory.getLogger(MainApp.class);

    @Override
    public void start(Stage stage) {
        log.info("Start Application...");
        MainHandler mainHandler = new MainHandler();
        Scene scene = new Scene(mainHandler.getUiRoot(), 1200, 800);
        scene.getStylesheets().add("/static/css/style.css");
        stage.setScene(scene);
        stage.show();
    }
}
