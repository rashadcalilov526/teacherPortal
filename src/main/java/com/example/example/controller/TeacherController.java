package com.example.example.controller;

import com.example.example.entity.Teacher;
import com.example.example.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/list")
    public String getAllTeacher(Model model){
        Iterable<Teacher> teachers=teacherRepository.findAll();
        model.addAttribute("teachers",teachers);
        return "list";
    }
    @GetMapping("/new")
    public String newPage(Model model){
        model.addAttribute("teacher",new Teacher());
        return "add";
    }
    @PostMapping("/save")
    public String addTeacher(Model model,Teacher teacher){
        teacherRepository.save(teacher);
        Iterable<Teacher> teachers=teacherRepository.findAll();
        model.addAttribute("teachers",teachers);
        return "list";
    }
    @GetMapping("/update")
    public String updateTeacher(Model model, @RequestParam Integer id){
        Optional<Teacher> optionalTeacher=teacherRepository.findById(id);
        if (optionalTeacher.isPresent()){
            Teacher teacher=optionalTeacher.get();
            model.addAttribute("teacher",teacher);
        }
        return "add";

    }
    @GetMapping("/delete")
    public String deleteTeacher(Model model,@RequestParam Integer id){
        Optional<Teacher> optionalTeacher=teacherRepository.findById(id);
        if (optionalTeacher.isPresent()){
            Teacher teacher=optionalTeacher.get();
            model.addAttribute("teacher",teacher);
        }
        return "delete";
    }
    @PostMapping("/delete")
    public String delete(Model model,Teacher teacher){
        teacherRepository.delete(teacher);
        Iterable<Teacher> teachers=teacherRepository.findAll();
        model.addAttribute("teachers",teachers);
        return "list";
    }
}
