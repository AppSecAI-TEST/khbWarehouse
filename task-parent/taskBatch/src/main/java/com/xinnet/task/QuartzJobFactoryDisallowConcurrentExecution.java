package com.xinnet.task;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.xinnet.task.domain.ScheduleJob;
import com.xinnet.task.util.TaskUtils;

/**
 * 若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作
 * @author hongbin.kang
 * @date 2017年8月9日下午8:00:54
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {
	public final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);

	}
}