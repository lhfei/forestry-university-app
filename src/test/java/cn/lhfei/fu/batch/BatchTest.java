/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.lhfei.fu.batch;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @version 1.0.0
 *
 * @author Hefei Li
 *
 * @since Jan 4, 2015
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
public class BatchTest {

	private static final Logger log = LoggerFactory.getLogger(BatchTest.class);

	@Test
	public void importUser() {
		Map<String, JobParameter> jobParameters = new HashMap<String, JobParameter>();
		jobParameters.put("inputFile", new JobParameter("data/log-data.json"));

		// JobExecution execution = jobLauncher.run(logDataFormatJob, new
		// JobParameters(jobParameters));

		try {
			JobExecution execution = jobLauncher.run(importUserJob,
					new JobParameters());

			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());

		} catch (JobExecutionAlreadyRunningException e) {
			e.printStackTrace();
		} catch (JobRestartException e) {
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}

	
	public void importStudent() {
		try {
			JobExecution execution = jobLauncher.run(importStudentJob,
					new JobParameters());

			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());

		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {

			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}

	public void importTeacher() {
		try {
			JobExecution execution = jobLauncher.run(importTeacherJob,
					new JobParameters());

			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());

		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {

			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}
	
	public void importHomeworkJob() {
		try {
			JobExecution execution = jobLauncher.run(importHomeworkJob,
					new JobParameters());
			
			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}
	
	
	public void importCourseJob() {
		try {
			JobExecution execution = jobLauncher.run(importCourseJob,
					new JobParameters());
			
			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
			
		} catch (JobExecutionAlreadyRunningException | JobRestartException
				| JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			
			e.printStackTrace();
			log.error(e.getMessage(), e);
		}
	}

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job importUserJob;

	@Autowired
	private Job importStudentJob;

	@Autowired
	private Job importTeacherJob;

	@Autowired
	private Job importHomeworkJob;
	
	@Autowired
	private Job importCourseJob;
}
