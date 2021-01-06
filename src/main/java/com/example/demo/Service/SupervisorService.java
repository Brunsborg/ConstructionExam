package com.example.demo.Service;

import com.example.demo.Model.Supervisor;
import com.example.demo.Repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupervisorService {

    @Autowired
    SupervisorRepository supervisorRepository;

    //Method for finding supervisor by their id.
    public Supervisor findById(long id) {
        Optional<Supervisor> optionalSupervisor = supervisorRepository.findById(id);
        if (optionalSupervisor.isPresent()) {
            return optionalSupervisor.get();
        } else
            throw new RuntimeException();
    }

    //Creates a list of all supervisors.
    public List<Supervisor> findAll() {
        List<Supervisor> supervisors = new ArrayList<>();
        for (Supervisor sv : supervisorRepository.findAll()) {
            supervisors.add(sv);
        }
        return supervisors;
    }

}
