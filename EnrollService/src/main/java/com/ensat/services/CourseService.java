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

    public void addCourse(Course course) 
    {
    	course.setNoEnrollments(0);
    	courseRepository.save(course); 
    }
}
