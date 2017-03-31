package com.xinnet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xinnet.entity.Class;
import com.xinnet.entity.Student;
import com.xinnet.entity.StudentVo;
import com.xinnet.mapper.StudentMapper;


@Service
//@Transactional(rollbackFor=Exception.class)
@Transactional(rollbackFor = Exception.class,propagation= Propagation.REQUIRED)
public class StudentService {
	
	@Autowired
	private StudentMapper studentMapper;
	/**
	 * 添加学生操作
	 * @param student
	 */
	public Boolean addStudent(Student stu) {
		Boolean flag = true;
		Student student = new Student();
		student.setcId(1);
		student.setIsAvailable(true);
		student.setName("zhetian");
		Integer sqlFlag = studentMapper.addStudent(student);
		if(sqlFlag != 1){
			flag = false;
		}
		return flag;
	}
	/**
	 * 查询所有的学生
	 * @param type 
	 * @return
	 */
	public List<StudentVo> searchAllStudent(Integer cId) {
		Student student = new Student();
		student.setcId(1);
		student.setIsAvailable(true);
		student.setName("zhetian");
		addStudent(student);
		List<StudentVo> list = new ArrayList<StudentVo>();
		if(cId != 0){
			list = studentMapper.searchStudentByClass(cId);
			return list;
		}
		list = studentMapper.searchAllStudent();
		return list;
		
	}
	/**
	 * 获取班级信息
	 * @return
	 */
	public List<Class> getClassList() {
		return studentMapper.getClassList();
	}
	/**
	 * 修改学生姓名
	 * @param student
	 * @return
	 */
	public Boolean editStudent(Student student) {
		return studentMapper.editStudent(student);
	}
	/**
	 * 删除学生
	 * @param sId
	 * @return
	 */
	public Boolean deleteStudent(Long sId) {
		return studentMapper.deleteStudent(sId);
	}
	/**
	 * 根据学生姓名查询学生
	 * @param name
	 * @return
	 */
	public List<StudentVo> searchStudentByName(String name) {
		return studentMapper.searchStudentByName(name);
	}
}
