package com.example.service;

import com.example.model.Teacher;

public interface TeacherService {
	public Teacher findTeacherByEmail(String email);
	public void saveTeacher(Teacher teacher);
}
