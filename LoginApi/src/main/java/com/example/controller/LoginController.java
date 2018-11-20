package com.example.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.servlet.http.*;
import com.example.model.User;
import com.example.service.UserService;

import com.example.model.Teacher;
import com.example.service.TeacherService;

import com.example.model.Student;
import com.example.service.StudentService;
import com.example.model.Enroll;
import com.example.model.Course;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.ArrayList;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import java.util.Map;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private StudentService studentService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value="/signout", method = RequestMethod.GET)
	public ModelAndView logout(HttpSession session){
		session.removeAttribute("userId");
		System.out.println("User ID is: "+ session.getAttribute("userId"));
		return new ModelAndView("redirect:" + "/");
	}
	
	@RequestMapping(value = "/student-signup", method = RequestMethod.POST)
	public ModelAndView createNewStudentUser(@Valid User user, @Valid Student student, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("login");
			
		} else {
			user.setUserType("student");
			user.setActive(1);
			userService.saveUser(user);
			student.setNoCoursesEnrolled(0);
			studentService.saveStudent(student);
			final String uri = "http://localhost:8888/add-new-student";
	    	RestTemplate restTemplate = new RestTemplate();
	    	String response = restTemplate.postForObject(uri, student, String.class);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("student_dash");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/teacher-signup", method = RequestMethod.POST)
	public ModelAndView createNewTeacherUser(@Valid User user, @Valid Teacher teacher, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("login");
			
		} else {
			user.setUserType("teacher");
			user.setActive(1);
			userService.saveUser(user);
			teacher.setCoursesUndertaken(0);
			teacherService.saveTeacher(teacher);
			final String uri = "http://localhost:8081/add-new-teacher";
	    	RestTemplate restTemplate = new RestTemplate();
	    	String response = restTemplate.postForObject(uri, teacher, String.class);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("teacher_dash");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(HttpSession session){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		//session.setAttribute("userId", user.getId());
		System.out.println("User ID is: "+ session.getAttribute("userId"));

		if(user.getUserType().equals("student"))
		{
			Student student = studentService.findStudentByEmail(user.getEmail());
			session.setAttribute("userId", student.getId());
			modelAndView.setViewName("student_dash");
		}
		if(user.getUserType().equals("teacher"))
		{
			Teacher teacher = teacherService.findTeacherByEmail(user.getEmail());
			session.setAttribute("userId", teacher.getId());
			modelAndView.setViewName("teacher_dash");
		}

		return modelAndView;
	}

	@RequestMapping(value="/all-courses", method = RequestMethod.GET)
	public ModelAndView showCourses(){
		List courses = new ArrayList<>();
		final String uri = "http://localhost:8081/all-courses";
    	RestTemplate restTemplate = new RestTemplate();
    	courses = restTemplate.getForObject(uri, List.class);
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("courses", courses);
        modelAndView.setViewName("view_all_course");
        return modelAndView;
	}

	@RequestMapping(value="/enroll", method = RequestMethod.POST)
	public ModelAndView enrollCourse(HttpSession session, HttpServletRequest request){
		int userId = (int)session.getAttribute("userId");

		Enroll enroll = new Enroll();
		System.out.println(Integer.parseInt(request.getParameter("course_id")));
		enroll.setCourseId(Integer.parseInt(request.getParameter("course_id")));
		enroll.setStudentId(userId);

		String uri = "http://localhost:8888/course/enroll";
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.postForObject(uri, enroll, String.class);

    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("Success Message", "Successfully Enrolled");
        modelAndView.setViewName("course_descp");
        return modelAndView;
	}

	@RequestMapping(value="/add-new-course", method = RequestMethod.GET)
	public ModelAndView getAddCourseForm(){
		ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("add_new_course");
        return modelAndView;
	}

	@RequestMapping(value="/add-new-course", method = RequestMethod.POST)
		public ModelAndView add_new_course(HttpSession session, HttpServletRequest request)
		{
			int userId = (int)session.getAttribute("userId");
			Course course = new Course();
			course.setCourseName(request.getParameter("course_name"));
	        course.setCourseLevel(request.getParameter("course_level"));
	        course.setCourseCode(request.getParameter("course_code"));
	        course.setCourseDescp(request.getParameter("course_descp"));
	        course.setCourseDuration(request.getParameter("course_duration"));
	        course.setCourseIncharge(userId);
	        
			String uri = "http://localhost:8081/course/add";
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.postForObject(uri, course, String.class);

			String url = "http://localhost:8888/course/add";
			String responsee = restTemplate.postForObject(url, course, String.class);

			ModelAndView modelAndView = new ModelAndView();
	    	modelAndView.setViewName("add_new_course");
	        return modelAndView;
		}

	@RequestMapping(value="/enrolled-courses",method = RequestMethod.GET)
	public ModelAndView showAllCourseEnrolled(HttpSession session)
	{
		int userId = (int)session.getAttribute("userId");
		//List courses = new ArrayList<>();

		String uri = "http://localhost:8888/show-courses-enrolled";
		RestTemplate restTemplate = new RestTemplate();
		List courses = restTemplate.postForObject(uri, userId, List.class);
		System.out.println(courses);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("courses", courses);
	    modelAndView.setViewName("enrolled_courses");
	    return modelAndView;

	}

}