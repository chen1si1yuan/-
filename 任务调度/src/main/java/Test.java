

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Test {

	public static void main(String[] args) throws SchedulerException {
		// 反射生成任务
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
				.withIdentity("myjob")
				.build();
		// 创建触犯器 制定调度器调度时间，间隔等
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("mytrigger")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(2).repeatForever())
				.startAt(new Date(1536203836289L))
				.build();

		// 创建调度器，制定触发器和任务
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
	}
}
