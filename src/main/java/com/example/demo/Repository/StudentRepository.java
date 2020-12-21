package com.example.demo.Repository;

import com.example.demo.Model.Student;
import org.springframework.data.repository.CrudRepository;

//Creates an interface to the CRUD repository so we can utilize the prewritten methods it contains
public interface StudentRepository extends CrudRepository <Student, Long>{
}
