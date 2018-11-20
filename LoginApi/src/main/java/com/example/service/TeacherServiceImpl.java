package com.example.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.model.Teacher;
import com.example.repository.TeacherRepository;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepository;
	
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Teacher findTeacherByEmail(String email) {
		return teacherRepository.findByEmail(email);
	}

	@Override
	public void saveTeacher(Teacher teacher) {
		teacher.setName(teacher.getName());
		teacher.setEmail(teacher.getEmail());
		teacher.setDescp(teacher.getDescp());
		teacher.setSpecialisation(teacher.getSpecialisation());
		teacher.setPassword(bCryptPasswordEncoder.encode(teacher.getPassword()));
		teacherRepository.save(teacher);
	}

}
