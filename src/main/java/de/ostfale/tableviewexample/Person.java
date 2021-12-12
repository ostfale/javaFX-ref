package de.ostfale.tableviewexample;

import java.time.LocalDate;

public record Person(
        String firstName,
        String lastName,
        Gender gender,
        LocalDate birthday) {
}
