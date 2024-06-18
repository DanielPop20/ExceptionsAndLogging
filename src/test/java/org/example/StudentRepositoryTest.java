package org.example;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StudentRepositoryTest {

    private StudentRepository repository;

    @Before
    public void setUp() {
        repository = new StudentRepository();
    }

    @Test
    public void testAddStudent() {
        Student student = new Student("John", "Doe", "2000-05-15", "male", "123");
        repository.addStudent(student);
        assertEquals(1, repository.listStudents("lastName").size());
    }

    @Test(expected = InvalidIDException.class)
    public void testAddStudentWithSameID() {
        Student student1 = new Student("John", "Doe", "2000-05-15", "male", "123");
        Student student2 = new Student("Jane", "Smith", "2001-08-19", "female", "123");
        repository.addStudent(student1);
        repository.addStudent(student2);
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student("John", "Doe", "2000-05-15", "male", "123");
        repository.addStudent(student);
        repository.deleteStudent("123");
        assertEquals(0, repository.listStudents("lastName").size());
    }

    @Test(expected = StudentNotFoundException.class)
    public void testDeleteNonExistentStudent() {
        repository.deleteStudent("999");
    }

    @Test
    public void testRetrieveStudentsByAge() {
        Student student = new Student("John", "Doe", "2000-05-15", "male", "123");
        repository.addStudent(student);
        List<Student> students = repository.retrieveStudentsByAge(24);
        assertEquals(1, students.size());
    }

    @Test(expected = InvalidAgeException.class)
    public void testRetrieveStudentsByNegativeAge() {
        repository.retrieveStudentsByAge(-1);
    }

    @Test
    public void testListStudentsByLastName() {
        Student student1 = new Student("John", "Doe", "2000-05-15", "male", "123");
        Student student2 = new Student("Jane", "Smith", "2001-08-19", "female", "456");
        repository.addStudent(student1);
        repository.addStudent(student2);
        List<Student> students = repository.listStudents("lastName");
        assertEquals("Doe", students.get(0).getLastName());
        assertEquals("Smith", students.get(1).getLastName());
    }

    @Test
    public void testListStudentsByBirthDate() {
        Student student1 = new Student("John", "Doe", "2000-05-15", "male", "123");
        Student student2 = new Student("Jane", "Smith", "2001-08-19", "female", "456");
        repository.addStudent(student1);
        repository.addStudent(student2);
        List<Student> students = repository.listStudents("birthDate");
        assertEquals("2000-05-15", students.get(0).getDateOfBirth().toString());
        assertEquals("2001-08-19", students.get(1).getDateOfBirth().toString());
    }
}
