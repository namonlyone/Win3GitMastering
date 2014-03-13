package com.aavn.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author nvhoang2
 * 
 */

@Controller
public class BatchImportDataController extends AbstractController {

	@Override
	@RequestMapping("/batch")
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {

		String[] springConfig = { "spring/batch/config/database.xml",
				"spring/batch/config/context.xml",
				"spring/batch/jobs/job-report.xml" };

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("reportJob");

		try {

			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			System.out.println("FAILURE...");
			e.printStackTrace();
		}

		String linkSearch = "http://"+arg0.getServerName().toString()+":"+arg0.getServerPort()+arg0.getContextPath();

		ModelAndView model = new ModelAndView("batchJob");
		model.addObject("link", linkSearch);
		return model;

	}

}
