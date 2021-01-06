package com.example.demo.Controller;

import com.example.demo.Model.Student;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Repository.SupervisorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    StudentRepository studentRepository;
    SupervisorRepository supervisorRepository;

    public RestController(StudentRepository studentRepository, SupervisorRepository supervisorRepository) {
        this.studentRepository = studentRepository;
        this.supervisorRepository = supervisorRepository;
    }

    @GetMapping("/Student")
    public Iterable<Student> findAll(){

        return studentRepository.findAll();
    }

    @GetMapping("/Student/{id}")
    public ResponseEntity<Optional<Student>> findById(@PathVariable Long id){
        Optional<Student> students = studentRepository.findById(id);
        if(students.isPresent()){
            return ResponseEntity.status(200).body(students);
        } else {
            return ResponseEntity.status(404).body(students);
        }
    }

    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value="/Student", consumes={"application/json"})
    public ResponseEntity<String> create(@RequestBody Student s){
        Student student = new Student(s.getId(), s.getName(), s.getEmail());
        student.setSupervisor(s.getSupervisor());
        studentRepository.save(student);

        return ResponseEntity.status(201).header("Location", "/student/" + s.getId()).body("{'post created'}");
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Student s){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()){
            return ResponseEntity.status(404).body("{'Not found'}");
        }

        studentRepository.save(s);
        return ResponseEntity.status(204).body("{'Updated'}");
    }


    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Student> students = studentRepository.findById(id);
        if (!students.isPresent()) {
            return ResponseEntity.status(404).body("{'Not found'");
        }
        studentRepository.deleteById(id);

        return ResponseEntity.status(200).body("{'Deleted'}");
    }
}
