package com.example.demo.service.impl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

// 有个很重要的点 StudentListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class StudentListener extends AnalysisEventListener<Student> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<Student> list = new ArrayList<Student>();
    private StudentServiceImpl studentServiceImpl;
    public StudentListener(){
        studentServiceImpl = new StudentServiceImpl();
    }
    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param studentServiceImpl
     */
    public StudentListener(StudentServiceImpl studentServiceImpl){
        this.studentServiceImpl = studentServiceImpl;
    }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param student
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param analysisContext
     */
    @Override
    public void invoke(Student student, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据：{}",student);
        list.add(student);
        //解析到五条数据或者五条数据额以上就存入数据库中
        if(list.size()>=BATCH_COUNT){
            saveStudent();
            // 存储完成清理 list
            list.clear();
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //也是保存数据，防止遗漏的数据没有保存到数据库中，也就是最后解析到小于五条的数据
        saveStudent();
    }
    public void saveStudent(){
        LOGGER.info("{}条数据，保存到数据库中！",list.size());
        System.out.println("是否为空："+studentServiceImpl);
        studentServiceImpl.insertStudents(list);
        LOGGER.info("保存成功！");
    }
}
