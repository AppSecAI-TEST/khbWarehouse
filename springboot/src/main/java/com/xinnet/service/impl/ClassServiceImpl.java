package com.xinnet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xinnet.entity.Student;
import com.xinnet.entity.Class;
import com.xinnet.mapper.ClassMapper;
import com.xinnet.service.ClassService;

/**
 * 班级操作service
 * @author fan
 *
 */
@Service
@Transactional
public class ClassServiceImpl implements ClassService{
	
	@Autowired
	private ClassMapper classMapper;
	/**
	 * 添加班级
	 * @param cls
	 * @return
	 */
	@Override
	public Boolean addStudent(Class cls){
		Boolean flag = true;
		Integer sqlFlag = classMapper.addClass(cls);
		if(sqlFlag != 1){
			flag = false;
		}
		return flag;
	}
	/**
	 * 查询所有班级
	 * @return
	 */
	@Override
	public List<Class> searchAllClass() {
		return classMapper.searchAllClass();
		
	}
	/**
	 * 修改班级
	 * @param cls
	 * @return
	 */
	@Override
	public Boolean editClass(Class cls) {
		Boolean flag = true;
		Integer sqlFlag = classMapper.editClass(cls);
		if(sqlFlag != 1){
			flag = false;
		}
		return flag;
	}
	/**
	 * 删除班级
	 * @param cId
	 * @return
	 */
	@Override
	public Boolean deleteClass(Long cId) {
		Boolean flag = true;
		Integer studentCount = classMapper.searchIsExistStudentByClass(cId);
		if(studentCount != 0){
			flag = false;
		}else{
			Integer sqlFlag = classMapper.deleteClass(cId);
			if(sqlFlag != 1 || studentCount > 0){
				flag = false;
			}
		}
		return flag;
	}
	/**
	 * 查询未添加班级的学生
	 * @return
	 */
	@Override
	public List<Student> searchNoCheckedClassStudent() {
		return classMapper.searchNoCheckedClassStudent();
	}
	/**
	 * 保存修改学生的班级信息
	 * @param student
	 * @return
	 */
	@Override
	public Boolean updateStudentClass(Student student) {
		return classMapper.updateStudentClass(student);
	}
}
