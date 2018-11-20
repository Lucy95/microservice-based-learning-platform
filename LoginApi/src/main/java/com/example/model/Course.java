package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String course_code;
    private String course_name;
    private String course_duration;
    private String course_level;
    private int course_incharge;
    private String course_descp;
    private Integer no_enrollments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCourseName() {
        return course_name;
    }

    public void setCourseName(String course_name) {
        this.course_name = course_name;
    }

    public String getCourseCode() {
        return course_code;
    }

    public void setCourseCode(String course_code) {
        this.course_code = course_code;
    }

    public String getCourseLevel() {
        return course_level;
    }

    public void setCourseLevel(String course_level) {
        this.course_level = course_level;
    }

    public String getCourseDuration() {
        return course_duration;
    }

    public void setCourseDuration(String course_duration) {
        this.course_duration = course_duration;
    }

    public String getCourseDescp() {
        return course_descp;
    }

    public void setCourseDescp(String course_descp) {
        this.course_descp = course_descp;
    }

    public int getCourseIncharge() {
        return course_incharge;
    }

    public void setCourseIncharge(int course_incharge) {
        this.course_incharge = course_incharge;
    }

    public Integer getNoEnrollments() {
        return id;
    }

    public void setNoEnrollments(Integer no_enrollments) {
        this.no_enrollments = no_enrollments;
    }

    public Course setValues(Course course, int userId)
    {
        course.setCourseName(getCourseName());
        course.setCourseLevel(getCourseLevel());
        course.setCourseCode(getCourseCode());
        course.setNoEnrollments(0);
        course.setCourseDescp(getCourseDescp());
        course.setCourseDuration(getCourseDuration());
        course.setCourseIncharge(userId);
        return course;
    }


}
