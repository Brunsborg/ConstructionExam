package com.example.demo.Repository;

import com.example.demo.Model.Supervisor;
import org.springframework.data.repository.CrudRepository;

//Creates an interface to the CRUD repository so we can utilize the prewritten methods it contains
public interface SupervisorRepository extends CrudRepository <Supervisor, Long> {
}
