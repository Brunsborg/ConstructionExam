package com.example.demo.Service;

import com.example.demo.Model.Student;
import com.example.demo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    //Initiating the save method from CRUDRepository with student as an argument
    public void save(Student s) {
        studentRepository.save(s);
    }

    //a find by ID method, uses crud repository, gets back an optional, gets student orbject from optional.
    public Student findById(long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return optionalStudent.get();
        } else
            throw new RuntimeException();
    }

    //Creates and returns a list of students.
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        for (Student s : studentRepository.findAll()) {
            students.add(s);
        }
        return students;
    }

    //method for updating students.
    public void update(Student s) {
        studentRepository.save(s);
    }

    //method for deleting students.
    public void deleteById(long id) {
        studentRepository.deleteById(id);
    }
}
