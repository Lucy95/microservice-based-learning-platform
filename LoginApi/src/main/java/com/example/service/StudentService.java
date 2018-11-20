package com.example.service;

import com.example.model.Student;

public interface StudentService {
	public Student findStudentByEmail(String email);
	public void saveStudent(Student student);
}
