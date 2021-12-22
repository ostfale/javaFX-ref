package de.ostfale.tableviewexample;

import java.time.LocalDate;

public record Person(
        String firstName,
        String lastName,
        String email,
        Gender gender,
        LocalDate birthday) {

    private static final String defFirstName = "Max";
    private static final String defLastName = "Mustermann";
    private static final String defEmail = "max.mustermann@mail.com";
    private static final LocalDate defBirthday = LocalDate.of(1900, 1, 1);

    public static Person getDefault() {
        return new Person(defFirstName, defLastName, defEmail, Gender.MALE, defBirthday);
    }
}
