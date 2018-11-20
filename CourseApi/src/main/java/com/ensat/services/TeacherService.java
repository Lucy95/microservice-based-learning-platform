package com.ensat.services;

import com.ensat.entities.Teacher;
import com.ensat.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
/**
 * Course service implement.
 */
@Service
public class TeacherService{

    @Autowired
	private TeacherRepository teacherRepository;

    public void addTeacher(Teacher teacher) 
    {
    	teacherRepository.save(teacher); 
    }
}
