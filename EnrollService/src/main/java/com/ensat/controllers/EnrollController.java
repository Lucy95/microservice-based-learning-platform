package com.ensat.controllers;

import com.ensat.entities.Enroll;
import com.ensat.services.EnrollService;

import com.ensat.entities.Student;
import com.ensat.services.StudentService;

import com.ensat.entities.Course;
import com.ensat.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.MediaType;
import javax.servlet.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;

@Controller
public class EnrollController {
    @Autowired
    private EnrollService enrollService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    
    @RequestMapping(value = "/course/enroll", method= RequestMethod.POST)
    public @ResponseBody String addEnroll(@RequestBody Enroll enroll) {
        System.out.println(enroll.getCourseId());
        System.out.println("I am called");
        enrollService.addEnroll(enroll);
        return "success";
    }

    @RequestMapping(value = "/add-new-student" , method = RequestMethod.POST)
    public @ResponseBody String addNewStudent(@RequestBody Student student)
    {
    	studentService.addStudent(student);
        String response = "success";
        return response;
    }

    @RequestMapping(value = "/course/add" , method = RequestMethod.POST)
    public @ResponseBody String addNewCourse(@RequestBody Course course)
    {
        courseService.addCourse(course);
        String response = "success";
        return response;
    }

    @RequestMapping(value = "/show-courses-enrolled" , method = RequestMethod.POST)
    public @ResponseBody List showCoursesEnrolled(@RequestBody int student_id)
    {
       List courses =  enrollService.enrolledCourses(student_id);
       return courses;
    }

}
