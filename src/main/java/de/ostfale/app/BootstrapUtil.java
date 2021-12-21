package de.ostfale.app;

import de.ostfale.tableviewexample.Gender;
import de.ostfale.tableviewexample.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class BootstrapUtil {

    public static ObservableList<Person> createPersonList() {
        Person p1 = new Person("Jim", "Jones", "jim.jones@mail.com", Gender.MALE, LocalDate.of(1966, 3, 23));
        Person p2 = new Person("Justin", "Smith", "justin@gmx.de", Gender.MALE, LocalDate.of(1982, 1, 11));
        Person p3 = new Person("Sheila", "Smith", "sm@google.com", Gender.FEMALE, LocalDate.of(1976, 7, 3));
        Person p4 = new Person("Irina", "Meier", "im@mail.de", Gender.FEMALE, LocalDate.of(1996, 4, 21));
        List<Person> personList = List.of(p1, p2, p3, p4);
        return FXCollections.observableList(personList);
    }
}
