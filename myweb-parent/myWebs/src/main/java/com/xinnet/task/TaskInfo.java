package com.xinnet.task;

import java.io.Serializable;
import java.util.Date;

public class TaskInfo implements Serializable{

	private static final long serialVersionUID = 4148232170311577571L;

	private Long id;
	private Date startTime;		//开始执行任务时间
	private Date stopTime;		//结束执行任务时间
	private String taskName;		//执行任务名称
	private int	executeResult;		//执行结果   1为成功  0为失败
	private String message;			//失败原因或异常信息或正常信息
	private String className;		//所属class
	private String methodName;		//所属method
	private int	type;				//任务类型  未知
	private String childNumber;		//子任务数量
	private String childNumberComplete;		//子任务完成数量
	private long storeId;
	
	public String getChildNumber() {
		return childNumber;
	}
	public void setChildNumber(String childNumber) {
		this.childNumber = childNumber;
	}
	public String getChildNumberComplete() {
		return childNumberComplete;
	}
	public void setChildNumberComplete(String childNumberComplete) {
		this.childNumberComplete = childNumberComplete;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getStopTime() {
		return stopTime;
	}
	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public int getExecuteResult() {
		return executeResult;
	}
	public void setExecuteResult(int executeResult) {
		this.executeResult = executeResult;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
}
