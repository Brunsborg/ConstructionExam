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


    // HTTP Get List
    @GetMapping("/Student")
    public Iterable<Student> findAll(){

        // find all recipes
        return studentRepository.findAll();
    }

    // HTTP Get by ID
    @GetMapping("/Student/{id}")
    public ResponseEntity<Optional<Student>> findById(@PathVariable Long id){
        Optional<Student> recipe = studentRepository.findById(id);
        if(recipe.isPresent()){
            return ResponseEntity.status(200).body(recipe); // OK
        } else {
            return ResponseEntity.status(404).body(recipe); // Not found
        }
    }

    // HTTP Post, ie. create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value="/recipe", consumes={"application/json"})
    public ResponseEntity<String> create(@RequestBody Student s){
        Student student = new Student(s.getId(), s.getName(), s.getEmail(), s.getSupervisor());
        //gem recipe, så der er et id tilknyttet til den nye opskrift til mapning i modsat regning
        student.setSupervisor(s.getSupervisor());
        //skal den gemmes igen?
        studentRepository.save(student);

        return ResponseEntity.status(201).header("Location", "/recipe/" + s.getId()).body("{'Msg': 'post created'}");
    }

    // HTTP PUT, ie. update
    @PutMapping("/recipe/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Student s){
        //get recipeById
        Optional<Student> optionalRecipe = studentRepository.findById(id);
        if (!optionalRecipe.isPresent()){
            //Recipe id findes ikke
            return ResponseEntity.status(404).body("{'msg':'Not found'");
        }

        //opdater category, ingredient og notes sker automatisk - nu er relationen oprettet
        //save recipe
        studentRepository.save(s);
        return ResponseEntity.status(204).body("{'msg':'Updated'}");
    }

    // HTTPDelete
    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Student> students = studentRepository.findById(id);
        //check at opskriften findes
        if (!students.isPresent()) {
            return ResponseEntity.status(404).body("{'msg':'Not found'"); // Not found
        }
        studentRepository.deleteById(id);

        return ResponseEntity.status(200).body("{'msg':'Deleted'}");
    }
}
