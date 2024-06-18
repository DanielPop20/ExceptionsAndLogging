package org.example;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        StudentRepository repository = new StudentRepository();

        try {
            Student student1 = new Student("John", "Doe", "2000-05-15", "male", "123");
            Student student2 = new Student("Dan", "Daniel", "1998-07-21", "female", "456");
            repository.addStudent(student1);
            repository.addStudent(student2);

            logger.log(Level.INFO, "Students added successfully");

            List<Student> studentsByAge = repository.retrieveStudentsByAge(24);
            logger.log(Level.INFO, "Students with age 24: " + studentsByAge);

            List<Student> studentsSortedByLastName = repository.listStudents("lastName");
            logger.log(Level.INFO, "Students sorted by last name: " + studentsSortedByLastName);

            List<Student> studentsSortedByBirthDate = repository.listStudents("birthDate");
            logger.log(Level.INFO, "Students sorted by birth date: " + studentsSortedByBirthDate);

            repository.deleteStudent("123");
            logger.log(Level.INFO, "Student with ID 123 deleted successfully");
        } catch (InvalidStudentException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
