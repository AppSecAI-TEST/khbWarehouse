
package com.xinnet.task;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.springframework.stereotype.Component;

//@Component
/*public class TaskTriggerListener implements TriggerListener, SchedulerListener {

	

//	private TaskInfoManager taskInfoManager;
	private static final ThreadLocal<TaskInfo> thread = new ThreadLocal<TaskInfo>();

	public TaskTriggerListener() {
	}
	
	public static void setChildNumber(long number) {
		TaskInfo taskInfo = thread.get();
		taskInfo.setChildNumber(number + "");
		thread.set(taskInfo);
	}

	public static void setChildNumberComplete(long number) {
		TaskInfo taskInfo = thread.get();
		taskInfo.setChildNumberComplete(number + "");
		thread.set(taskInfo);
	}

	@Override
	public String getName() {
		return "*";
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		TaskInfo taskInfo = new TaskInfo();
		taskInfo.setTaskName(trigger.getName());
//		taskInfo.setStartTime(new Date());
		taskInfo.setClassName(context.getJobInstance().toString());
		thread.set(taskInfo);
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
	}

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
	        int triggerInstructionCode) {
		TaskInfo taskInfo = thread.get();
		if(taskInfo!=null) {
			taskInfo.setStopTime(new Date());
			if (taskInfo.getMessage() == null) {
				taskInfo.setExecuteResult(1);
			}
//			taskInfoManager.save(taskInfo);
		}
	}

	@Override
	public void jobScheduled(Trigger trigger) {
	}

	@Override
	public void jobUnscheduled(String triggerName, String triggerGroup) {
	}

	@Override
	public void triggerFinalized(Trigger trigger) {
	}

	@Override
	public void triggersPaused(String triggerName, String triggerGroup) {
	}

	@Override
	public void triggersResumed(String triggerName, String triggerGroup) {
	}

	@Override
	public void jobAdded(JobDetail jobDetail) {
	}

	@Override
	public void jobDeleted(String jobName, String groupName) {
	}

	@Override
	public void jobsPaused(String jobName, String jobGroup) {
	}

	@Override
	public void jobsResumed(String jobName, String jobGroup) {
	}

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		TaskInfo taskInfo = thread.get();
		if(taskInfo!=null) {
			taskInfo.setExecuteResult(0);
			Writer w = null;
			PrintWriter out = null;
			try {
				w = new StringWriter();
				out = new PrintWriter(w);
				cause.printStackTrace(out);
				String smsg = w.toString();
				taskInfo.setMessage(smsg);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
				try {
					if (w != null) {
						w.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			taskInfoManager.save(taskInfo);
		}
	}

	@Override
	public void schedulerInStandbyMode() {
	}

	@Override
	public void schedulerStarted() {
	}

	@Override
	public void schedulerShutdown() {
	}

	@Override
	public void schedulerShuttingdown() {
	}

//	public void setTaskInfoManager(TaskInfoManager taskInfoManager) {
//		this.taskInfoManager = taskInfoManager;
//	}
}*/
