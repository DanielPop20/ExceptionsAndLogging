package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        for (Student s : students) {
            if (s.getId().equals(student.getId())) {
                throw new InvalidIDException("Student with the same ID already exists");
            }
        }
        students.add(student);
    }

    public void deleteStudent(String id) {
        if (id == null || id.isEmpty()) {
            throw new InvalidIDException("ID cannot be empty");
        }
        boolean removed = students.removeIf(s -> s.getId().equals(id));
        if (!removed) {
            throw new StudentNotFoundException("Student with the given ID does not exist");
        }
    }

    public List<Student> retrieveStudentsByAge(int age) {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative");
        }
        return students.stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }

    public List<Student> listStudents(String orderBy) {
        if (orderBy == null || orderBy.isEmpty()) {
            throw new InvalidStudentException("Order by cannot be empty");
        }
        if (orderBy.equalsIgnoreCase("lastName")) {
            return students.stream()
                    .sorted(Comparator.comparing(Student::getLastName))
                    .collect(Collectors.toList());
        } else if (orderBy.equalsIgnoreCase("birthDate")) {
            return students.stream()
                    .sorted(Comparator.comparing(Student::getDateOfBirth))
                    .collect(Collectors.toList());
        } else {
            throw new InvalidStudentException("Invalid order by parameter");
        }
    }
}
