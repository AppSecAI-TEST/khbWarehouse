package com.xinnet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xinnet.annotation.NotLogin;
import com.xinnet.entity.Class;
import com.xinnet.entity.Student;
import com.xinnet.entity.StudentVo;
import com.xinnet.service.StudentService;

@Controller
@RequestMapping(value = "/student")
public class StudentController extends BaseController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@NotLogin
	public String index() {
		return "student";
	}

	/**
	 * 添加学生操作	
	 * @param request
	 * @param response
	 * @param student 客户端传递封装好的学生对象
	 */
	@RequestMapping(value = "/addStudent"/*,method = RequestMethod.POST*/)
	@NotLogin
	public void addStudent(HttpServletRequest request,HttpServletResponse response) {
		Boolean flag = studentService.addStudent(null);
		this.printJson(flag, response);
	}
	/**
	 * 查询所有学生操作
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/searchAllStudent",method = RequestMethod.GET)
	@NotLogin
	public void getAllStudent(HttpServletRequest request,HttpServletResponse response,Integer cId){
		List<StudentVo> studentList = studentService.searchAllStudent(cId);
		this.printJson(studentList, response);
	}
	/**
	 * 获取班级信息列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getClassList",method = RequestMethod.GET)
	@NotLogin
	public void getClassList(HttpServletRequest request,HttpServletResponse response){
		List<Class> classList = studentService.getClassList();
		this.printJson(classList, response);
	}
	/**
	 * 修改学生姓名
	 */
	@RequestMapping(value = "/editStudent",method = RequestMethod.POST)
	@NotLogin
	public void editStudent(HttpServletRequest request,HttpServletResponse response,@RequestBody Student student){
		Boolean flag = studentService.editStudent(student);
		this.printJson(flag, response);
	}
	/**
	 * 删除学生
	 */
	@RequestMapping(value = "/deleteStudent",method = RequestMethod.GET)
	@NotLogin
	public void deleteStudent(HttpServletRequest request,HttpServletResponse response,Long sId){
		Boolean flag = studentService.deleteStudent(sId);
		this.printJson(flag, response);
	}
	/**
	 * 根据学生姓名模糊查询
	 */
	@RequestMapping(value = "/searchStudentByName",method = RequestMethod.GET)
	@NotLogin
	public void searchStudentByName(HttpServletRequest request,HttpServletResponse response,String name){
		List<StudentVo> studentList = studentService.searchStudentByName(name);
		this.printJson(studentList, response);
	}
}
