package de.ostfale.app.person.controller;

import de.ostfale.app.person.model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonChangeListener implements ChangeListener<Person> {

    private final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Override
    public void changed(ObservableValue<? extends Person> observableValue, Person oldVal, Person newVal) {
        if (newVal == null) {
            log.debug("No person selected");
        } else {
            log.debug("Selected person: {} {}", newVal.firstName(), newVal.lastName());

        }
    }
}
