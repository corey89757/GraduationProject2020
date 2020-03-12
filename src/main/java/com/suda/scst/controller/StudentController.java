package com.suda.scst.controller;

import com.suda.scst.domain.Student;
import com.suda.scst.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Chen Xiao
 */

//@RestController是@ResponseBody和@Controller的组合注解
@RestController
@RequestMapping("/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/graph")
    public Map<String, Object> graph(@RequestParam(value = "limit", required = false) Integer limit) {
        return studentService.graph(limit == null ? 100 : limit);
    }

    @PostMapping(value = "/student/add")
    //接收hello请求
    public void addStudent(@RequestBody Student student) {
        studentService.upsertStudent(student);
    }


    @GetMapping("/student/query")
    public Student graph(@RequestParam(value = "name") String name) {
        return studentService.findByName(name);
    }

//    public Map<String,Student> getStudent()
//	{
//		Student student1 = new Student("wang",1998,21);
//		Student student2 = new Student("gong", 1999,21);
//		Student student3 = new Student("zhang", 2000,20);
//		Map<String,Student> map = new HashMap<>();
//		map.put("student1",student1);
//		map.put("student2",student2);
//		map.put("student3",student3);
//        return map;
//    }
}
