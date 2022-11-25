package com.jayant.factories;

import com.jayant.dto.Student;
import com.jayant.service.StudentService;
import com.jayant.service.StudentServiceImpl;

public class StudentServiceFactory {

	private static StudentService studentService = null;
	
	static {
		studentService = new StudentServiceImpl();
	}
	
	public static StudentService getStudentService() {
		return studentService;
	}
	
}
