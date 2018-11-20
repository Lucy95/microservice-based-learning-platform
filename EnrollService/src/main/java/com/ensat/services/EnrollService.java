package com.ensat.services;

import com.ensat.entities.Enroll;
import com.ensat.entities.Course;
import com.ensat.entities.Student;
import com.ensat.repositories.EnrollRepository;
import com.ensat.repositories.CourseRepository;
import com.ensat.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Service
public class EnrollService{
    @Autowired
    private EnrollRepository enrollRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
	private StudentRepository studentRepository;

    
    public void addEnroll(Enroll enroll) 
    {
    	enrollRepository.save(enroll);

    	Course crs = courseRepository.findOne(enroll.getCourseId());
    	crs.setNoEnrollments(crs.getNoEnrollments()+1);
        courseRepository.save(crs);

        Student stu = studentRepository.findOne(enroll.getStudentId());
    	stu.setNoCoursesEnrolled(stu.getNoCoursesEnrolled()+1);
        studentRepository.save(stu);
    }

    public List enrolledCourses(int student_id)
    {
        return enrollRepository.find(student_id);
    }
}
