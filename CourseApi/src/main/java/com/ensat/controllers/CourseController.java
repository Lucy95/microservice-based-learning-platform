package com.ensat.controllers;

import com.ensat.entities.Course;
import com.ensat.services.CourseService;

import com.ensat.entities.Teacher;
import com.ensat.services.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import javax.servlet.http.*;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/all-courses", method = RequestMethod.GET)
    public @ResponseBody List getAllCourses() {
        return courseService.getAllCourses();
    }
    
   
    @RequestMapping(value = "/course/{id}", method = RequestMethod.GET)
    public @ResponseBody Course getCourse(@PathVariable Integer id) {
        return courseService.getCourse(id);
    }
    
    
    @RequestMapping(value = "/course/add", method = RequestMethod.POST)
    public @ResponseBody String addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
        return "success";
    }
    
    
    @RequestMapping(value = "/course/edit/{id}", method = RequestMethod.PUT)
    public void updateCourse(@RequestBody Course course,@PathVariable Integer id) {
        courseService.updateCourse(id, course);
    }
    
    
    @RequestMapping(value = "/course/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
    }

    @RequestMapping(value = "/add-new-teacher" , method = RequestMethod.POST)
    public @ResponseBody String addNewTeacher(@RequestBody Teacher teacher)
    {
        teacherService.addTeacher(teacher);
        String response = "success";
        return response;
    }
}
