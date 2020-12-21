package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Supervisor {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    //Creates a list of students related to the supervisor and maps it to the supervisor field in the student fields as a foreign key
    @OneToMany(mappedBy = "supervisor")
    List<Student> students = new ArrayList<>();

    public Supervisor() {
    }

    public Supervisor(long id, String name) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return
                ""+ id;
    }
}
