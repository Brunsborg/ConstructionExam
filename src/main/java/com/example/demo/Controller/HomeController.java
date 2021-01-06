package com.example.demo.Controller;

import com.example.demo.Model.Student;
import com.example.demo.Model.Supervisor;
import com.example.demo.Service.StudentService;
import com.example.demo.Service.SupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    StudentService studentService;
    @Autowired
    SupervisorService supervisorService;

    //Creates a list of students and supervisors for use for the GUI.
    @GetMapping("/")
    public String index(Model model) {
        List<Student> studentList = studentService.findAll();
        model.addAttribute("students", studentList);
        List<Supervisor> supervisorList = supervisorService.findAll();
        model.addAttribute("supervisors", supervisorList);
        return "Home/index";
    }

    //Mapping for creating a student.
    @PostMapping("/createStudent")
    public String createStudent(@ModelAttribute Student s) {
        studentService.save(s);
        return "redirect:/";
    }

    //Mapping for updating students
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute Student s) {
        studentService.update(s);
        return "redirect:/";
    }

    //Mapping for deleting students
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        studentService.deleteById(id);
        return "redirect:/";
    }
}