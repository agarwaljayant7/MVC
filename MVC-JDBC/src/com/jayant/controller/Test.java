package com.jayant.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.jayant.dto.Student;
import com.jayant.factories.ConnectionFactory;
import com.jayant.factories.StudentServiceFactory;
import com.jayant.service.StudentService;

public class Test {
	static {
		try {
			Class.forName("com.jayant.factories.ConnectionFactory");
//			Class.forName("com.jayant.factories.StudentServiceFactory");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {

		BufferedReader bufferedReader = null;
		String sid = "";
		String sname = "";
		String saddr = "";
		String status = "";
		StudentService studentService = StudentServiceFactory.getStudentService();
		Student student = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Student Management System");
			System.out.println("========================================");
			while(true) {
				System.out.println();
				System.out.println("1.ADD Student");
				System.out.println("2. SEARCH Student");
				System.out.println("3. UPDATE Student");
				System.out.println("4. DELETE Student");
				System.out.println("5. EXIT");
				System.out.print("Your Option [1,2,3,4,5]  : ");
				int userOption = Integer.parseInt(bufferedReader.readLine());
				
				switch(userOption) {
					case 1:// ADD Student
						
						System.out.println("+++++++++++ADD Student Module+++++++++++");
						System.out.print("Student ID       : ");
						sid = bufferedReader.readLine();
						student = studentService.searchStudent(sid);
						if(student == null) {
							System.out.println("Student Name   : ");
							sname = bufferedReader.readLine();
							System.out.println("Student Address: ");
							saddr = bufferedReader.readLine();
							
							student = new Student();
							student.setSid(sid);
							student.setsName(sname);
							student.setsAddr(saddr);
							
							status = studentService.addStudent(student);
							if(status.equalsIgnoreCase("success")) {
								System.out.println("Status : Student Insertion Success");
							}
							if(status.equalsIgnoreCase("failure")) {
								System.out.println("Status : Student Insertions failure");
							}
						}
						else {
							System.out.println("Status : Student Existed Already");
						}
						break;
					case 2:// SEARCH Student
						System.out.println("++++++++Search Student Module+++++++");
						System.out.println("Student id      : ");
						sid = bufferedReader.readLine();
						Student std = studentService.searchStudent(sid);
						if(std == null) {
							System.out.println("Status  :   Student not existed ");
						}else {
							System.out.println("Status  : Student Existed");
							System.out.println("Student Details ");
							System.out.println("-------------------------------");
							System.out.print("Student Id      : " + std.getSid());
							System.out.println("Student Name    : " + std.getsName());
							System.out.println("Student Address : " + std.getsAddr());
						}
						break;
					case 3:  // UPDATE Student
						System.out.println("+++++++Update Student Module++++++++");
						System.out.print("Student ID           : ");
						sid = bufferedReader.readLine();
						student = studentService.searchStudent(sid);
						if(student==null) {
							System.out.println("Status    : Student Does not Exist");
						}else {
							System.out.print("Student Name [Old: " + student.getsName() +"] New : ");
							String new_Sname = bufferedReader.readLine();
							if(new_Sname == null || new_Sname.trim().equals("")) {
								new_Sname =  student.getsName();
							}
							System.out.println("Student Address  [Old : " + student.getsAddr()+"] New : ");
							String new_Saddr = bufferedReader.readLine();
							if(new_Saddr == null || new_Saddr.trim().equals("")) {
								new_Saddr = student.getsAddr();
							}
							Student new_Student = new Student();
							new_Student.setSid(student.getSid());
							new_Student.setsName(new_Sname);
							new_Student.setsAddr(new_Saddr);
							
							status = studentService.updateStudent(new_Student);
							if(status.equalsIgnoreCase("success")) {
								System.out.println("Status   : Student Updatation Success");
							}
							else {
								System.out.println("Status   : Student Updatation Failure");
							}
						}
						break;
					case 4:// DELETE Student
						System.out.println("+++++ Delete Student Module+++++");
						System.out.println("Student ID     : ");
						sid = bufferedReader.readLine();
						student = studentService.searchStudent(sid);
						if(student == null) {
							System.out.println("Status  : Student Does Not Exist");
						}else {
							status = studentService.deleteStudent(sid);
							if(status.equalsIgnoreCase("success")) {
								System.out.println("Status  : Student Deletion Success");
							}
							else {
								System.out.println("Status  : Student Deletion Failure");
							}
						}
						break;
					case 5:
						System.out.println("********Thank you for using this application, Visit again ********");
						ConnectionFactory.close();
						System.exit(0);
						break;
					default:
						System.out.println("Invalid User Option, please enter the number from 1 to 5 only");
						break;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
