package org.example;

public class StudentNotFoundException extends InvalidStudentException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
