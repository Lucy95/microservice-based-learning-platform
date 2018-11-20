package com.ensat.repositories;

import com.ensat.entities.Enroll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface EnrollRepository extends CrudRepository<Enroll, Integer> {

	@Query("SELECT course_id FROM Enroll e WHERE (e.student_id) = (:student_id)")
    public List find(@Param("student_id") int student_id);
}
