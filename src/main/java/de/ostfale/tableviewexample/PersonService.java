package de.ostfale.tableviewexample;

import de.ostfale.MainApp;
import de.ostfale.common.DataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;

public class PersonService {

    private final Logger log = LoggerFactory.getLogger(MainApp.class);

    public void addPerson(DataModel<Person> dataModel) {
        log.debug("Add new (default) person object...");
        dataModel.getObjectList().add(Person.getDefault());
    }

    public Comparator<Person> getComparator() {
        log.trace("Get Person comparator");
        return (p1, p2) -> {
            var res = p1.lastName().compareTo(p2.lastName());
            if (res == 0) {
                return p1.firstName().compareTo(p1.firstName());
            }
            return res;
        };
    }
}
