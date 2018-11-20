package com.ensat.services;

import com.ensat.entities.Student;
import com.ensat.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService{

    @Autowired
	private StudentRepository studentRepository;

    public void addStudent(Student student) 
    {
    	studentRepository.save(student); 
    }
}
