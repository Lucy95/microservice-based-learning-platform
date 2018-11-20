package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Teacher;

@Repository("teacherRepository")
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	 Teacher findByEmail(String email);
}