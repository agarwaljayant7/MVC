package com.jayant.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.jayant.dto.Student;
import com.jayant.factories.ConnectionFactory;

public class StudentDaoImpl implements StudentDao {

	@Override
	public String add(Student student) {
		String status = "";
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement statement = connection.createStatement();
			int rowCount = statement.executeUpdate("INSERT INTO STUDENT VALUES('" + student.getSid() + "','"
					+ student.getsName() + "','" + student.getsAddr() + "')");
			if (rowCount == 1) {
				status = "success";
			} else {
				status = "failure";
			}
		} catch (Exception e) {
			status = "failure";
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public Student search(String sid) {
		Student student = null;
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENT WHERE SID ='" + sid + "'");
			boolean flag = resultSet.next();
			if (flag == true) {
				student = new Student();
				student.setSid(resultSet.getString("SID"));
				student.setsName(resultSet.getString("SNAME"));
				student.setsAddr(resultSet.getString("SADDR"));
			} else {
				student = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public String update(Student student) {
		String status = "";
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement statement = connection.createStatement();
			int rowCount = statement.executeUpdate("UPDATE STUDENT SET SNAME = '" + student.getsName() + "', SADDR = '"
					+ student.getsAddr() + "' WHERE SID = '" + student.getSid() + "'");
			if (rowCount == 1) {
				status = "success";
			} else {
				status = "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String delete(String sid) {
		String status = "";
		try {
			Connection connection = ConnectionFactory.getConnection();
			Statement statement = connection.createStatement();
			int rowCount = statement.executeUpdate("DELETE FROM STUDENT WHERE SID='" + sid + "'");
			if (rowCount == 1) {
				status = "success";
			} else {
				status = "failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
