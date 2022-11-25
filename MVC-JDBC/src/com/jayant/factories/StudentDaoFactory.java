package com.jayant.factories;

import com.jayant.dao.StudentDao;
import com.jayant.dao.StudentDaoImpl;
import com.jayant.dto.Student;

public class StudentDaoFactory {
	
	private static StudentDao studentDao = null;
	
	static {
		studentDao = new StudentDaoImpl();
	}
	public static StudentDao getStudentDao() {
		return studentDao;
	}
}
