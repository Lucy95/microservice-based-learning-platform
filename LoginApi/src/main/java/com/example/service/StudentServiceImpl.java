package com.example.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.model.Student;
import com.example.repository.StudentRepository;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Student findStudentByEmail(String email) {
		return studentRepository.findByEmail(email);
	}

	@Override
	public void saveStudent(Student student) {
		student.setName(student.getName());
		student.setEmail(student.getEmail());
		student.setPassword(bCryptPasswordEncoder.encode(student.getPassword()));
		studentRepository.save(student);
	}

}
