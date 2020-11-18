package com.example.demo.mapper;

import com.example.demo.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Liuer
 * @since 2020-10-22
 */
public interface StudentMapper extends BaseMapper<Student> {
    List<Student> selectStudent();
    Student selectPassword(String sno);
    int deleteStudent(String sno);
    int addStudent(Student stu);
    void insertStudents(List<Student> students);
}
