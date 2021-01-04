package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Student {

    //Names the "id" field as the primary key.
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    private String email;

    //Creates the relationship between students and supervisors.
    @ManyToOne
private Supervisor supervisor;

    public Student() {
    }

    public Student(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Student(long id, String name, String email, Supervisor supervisor) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.supervisor = supervisor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", email=" + email +
                ", supervisor='" + supervisor + '\'' +
                '}';
    }
}
