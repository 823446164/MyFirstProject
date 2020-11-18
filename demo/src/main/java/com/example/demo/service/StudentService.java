package com.example.demo.service;

import com.example.demo.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Liuer
 * @since 2020-10-22
 */
public interface StudentService extends IService<Student> {
    //查询学生信息
    List<Student> selectStudent();
    //新增学生信息
    int insertStudent(Student student);
    //删除学生信息
    int deleteStudent(String stuno);
    //查询登录账号
    Student selectPassword(String sno);
    //下载学生信息
    void downLodeStudent();
    //批量导入学生信息
    void insertStudents(List<Student> stinents);
}
