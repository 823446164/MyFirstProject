package com.example.demo.controller;


import com.alibaba.excel.EasyExcel;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.example.demo.service.impl.AddressServiceImpl;
import com.example.demo.service.impl.StudentListener;
import com.example.demo.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Liuer
 * @since 2020-10-22
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    AddressServiceImpl addressService;

    @GetMapping("/students")
    public List<Student> findListStudent(){
        List<Student> list = studentService.selectStudent();
        return list;
    }
    @GetMapping("/logining")
    public Student loginType(@RequestParam("sno") String sno){
        Student student = studentService.selectPassword(sno);
        return student;
    }
    @DeleteMapping("/deleteingStudent")
    public Integer deleteStudent(@RequestParam("sno") String sno){
        int i = studentService.deleteStudent(sno);
        return i;
    }
    @PostMapping("/studenting")
    public Integer addStudent(@RequestBody Student student){
        int i = studentService.insertStudent(student);
        return i;
    }
    /*下载全部学生信息*/
    @GetMapping("/studentinfo")
    public void downLodeStudent(){
        studentService.downLodeStudent();
    }
    /*批量导入学生信息*/
    public void insertStudents(String path){
        System.out.println("controller层的数据："+studentService);
        EasyExcel.read(path,Student.class,new StudentListener(studentService)).sheet().doRead();
    }
}

