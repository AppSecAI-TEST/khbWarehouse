package com.xinnet.task;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.xinnet.task.domain.ScheduleJob;
import com.xinnet.task.util.TaskUtils;

/**
 * 计划任务执行处 无状态
 * @author hongbin.kang
 * @date 2017年8月9日下午8:00:46
 */
public class QuartzJobFactory implements Job {
	public final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}