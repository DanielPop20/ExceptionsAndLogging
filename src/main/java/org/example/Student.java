package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Student {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String id;

    public Student(String firstName, String lastName, String dateOfBirth, String gender, String id) throws InvalidStudentException {
        if (firstName == null || firstName.isEmpty()) {
            throw new InvalidStudentException("First name cannot be empty");
        }
        if (lastName == null || lastName.isEmpty()) {
            throw new InvalidStudentException("Last name cannot be empty");
        }
        try {
            this.dateOfBirth = LocalDate.parse(dateOfBirth);
        } catch (DateTimeParseException e) {
            throw new InvalidStudentException("Invalid date of birth format");
        }
        if (this.dateOfBirth.isBefore(LocalDate.of(1900, 1, 1)) ||
                this.dateOfBirth.isAfter(LocalDate.now().minusYears(18))) {
            throw new InvalidAgeException("Date of birth must be between 1900 and current year - 18");
        }
        if (!(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") ||
                gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f"))) {
            throw new InvalidGenderException("Gender must be male, female, M, or F");
        }
        if (id == null || id.isEmpty()) {
            throw new InvalidIDException("ID cannot be empty");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getGender() {
        return gender;
    }
    public String getId() {
        return id;
    }
    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}