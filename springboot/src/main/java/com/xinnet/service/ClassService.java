package com.xinnet.service;

import java.util.List;

import com.xinnet.entity.Class;
import com.xinnet.entity.Student;

/**
 * 班级操作service
 * @author fan
 *
 */
public interface ClassService {
	/**
	 * 添加班级
	 * @param cls
	 * @return
	 */
	public Boolean addStudent(Class cls);
	/**
	 * 查询所有班级
	 * @return
	 */
	public List<Class> searchAllClass();
	/**
	 * 修改班级
	 * @param cls
	 * @return
	 */
	public Boolean editClass(Class cls);
	/**
	 * 删除班级
	 * @param cId
	 * @return
	 */
	public Boolean deleteClass(Long cId);
	/**
	 * 查询未添加班级的学生
	 * @return
	 */
	public List<Student> searchNoCheckedClassStudent();
	/**
	 * 保存修改学生的班级信息
	 * @param student
	 * @return
	 */
	public Boolean updateStudentClass(Student student);
}
