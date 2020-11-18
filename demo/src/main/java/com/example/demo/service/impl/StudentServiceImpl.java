package com.example.demo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.demo.entity.Address;
import com.example.demo.entity.Student;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Liuer
 * @since 2020-10-22
 */
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    StudentMapper studentMapper;
    @Autowired
    AddressServiceImpl addressServiceImpl;

    @Override
    public List<Student> selectStudent() {
        List<Student> students = studentMapper.selectStudent();
        if(students.size()>0){
            for(Student stu : students){
                //获取地址编号
                String addressId = stu.getAddressId();
                if(addressId.matches("[0-9]+")) {
                    Address address = addressServiceImpl.selectAddress(addressId);
                    String s = addressServiceImpl.selectAddress(address);
                    stu.setAddressId(s);
                }
            }
        }
        return students;
    }

    @Override
    public int insertStudent(Student student) {
//        int insert1 = studentMapper.insert(student);
        //mybatisplus内置的新增方法
        int insert = studentMapper.insert(student);
        return insert;
    }

    @Override
    public int deleteStudent(String stuno) {
        //自己写的删除方法
//        int i1 = studentMapper.deleteStudent(stuno);
        //mybatisplus里内置的方法
        int i = studentMapper.deleteById(stuno);
        return i;
    }

    @Override
    public Student selectPassword(String sno) {
        Student student = studentMapper.selectPassword(sno);
        return student;
    }

    @Override
    public void downLodeStudent() {
        String path = "D:\\workplace\\demo\\target\\student.xlsx";
        try {
            EasyExcel.write(path,Student.class).sheet("模板").doWrite(selectStudent());
        }catch (Exception e){
            if(e!=null){
                int i = 0;
                path = "D:\\workplace\\demo\\target\\student("+i+1+").xlsx";
            }
        }

    }

    @Override
    public void insertStudents(List<Student> students) {
        studentMapper.insertStudents(students);
    }

}
