package com.example.demo;

import com.example.demo.controller.StudentController;
import com.example.demo.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	StudentController studentController;
	@Test
	void contextLoads() {
		List<Student> listStudent = studentController.findListStudent();
		System.out.println(listStudent.size());
		for(Student stu:listStudent){
			System.out.println("学生信息："+stu);
			System.out.println("学生地址："+stu.getAddressId());
		}
	}
	@Test
	void lodestudent(){
		String path = "D:\\workplace\\demo\\target\\student.xlsx";
		studentController.insertStudents(path);
	}

}
