package de.ostfale.tableviewexample;

import java.time.LocalDate;

public record Person(
        String firstName,
        String lastName,
        String email,
        Gender gender,
        LocalDate birthday) {
}
