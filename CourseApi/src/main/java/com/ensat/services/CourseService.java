package com.ensat.services;

import com.ensat.entities.Course;
import com.ensat.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService{
    @Autowired
    private CourseRepository courseRepository;

    public List getAllCourses() {
        
        List courses = new ArrayList<>();
        courseRepository.findAll().forEach(courses::add);
        
        return courses;
    }

    public Course getCourse(Integer id) {
        return courseRepository.findOne(id);
    
    }
    
  
    public void addCourse(Course course) 
    {
        course.setNoEnrollments(0);
        courseRepository.save(course);
    }
    
   
    public void updateCourse(Integer id, Course course) {
        Course crs = courseRepository.findOne(id);
        crs.setCourseCode(course.getCourseCode());
        crs.setCourseName(course.getCourseName());
        crs.setCourseLevel(course.getCourseLevel());
        crs.setCourseDescp(course.getCourseDescp());
        crs.setCourseDuration(course.getCourseDuration());
        crs.setCourseIncharge(course.getCourseIncharge());
        crs.setNoEnrollments(course.getNoEnrollments());
        courseRepository.save(crs);
    }
    
  
    public void deleteCourse(Integer id) {
        courseRepository.delete(id);
    }

}
