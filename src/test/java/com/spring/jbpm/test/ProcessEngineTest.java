package com.spring.jbpm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jbpm.test.JBPMHelper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.runtime.manager.context.ProcessInstanceIdContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * 
 * @author suresh Mahalingam (msuresh_md@yahoo.com)
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/bean-config/jbpm-application-config.xml"})
public class ProcessEngineTest {
	
	private static Logger logger = Logger.getLogger(ProcessEngineTest.class);
	
	@Autowired
	private RuntimeManager runtimeManager;
	@Autowired
	TaskService taskService;
	
	//If you want to test with postgres DB, comment it below JBPMHelper
	@BeforeClass
    public static void setupOnce() {
        JBPMHelper.startH2Server();         
        JBPMHelper.setupDataSource();
    }
	
	@Test
	public void testProcessInstance(){
		RuntimeEngine engine = runtimeManager.getRuntimeEngine(ProcessInstanceIdContext.get());
		KieSession ksession = engine.getKieSession();
		taskService = engine.getTaskService();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "suresh");
		ProcessInstance processInstance = ksession.startProcess("com.sample.bpmn",map);
		logger.info("==============================================================");
		logger.info("Process started: "+processInstance.getId());
		logger.info("==============================================================");
        List<TaskSummary> tasks = taskService.getTasksOwned("suresh", "en-UK");
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        long taskId = tasks.get(0).getId();
        logger.info("==============================================================");
        logger.info("Task Id: " + taskId+" Task Name <<"+tasks.get(0).getName()+">>");
        logger.info("==============================================================");
        taskService.start(taskId, "suresh");
        taskService.complete(taskId, "suresh", null);
        
        
        tasks = taskService.getTasksOwned("suresh", "en-UK");
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        taskId = tasks.get(0).getId();
        taskId = tasks.get(0).getId();
        logger.info("==============================================================");
        logger.info("Task Id: " + taskId+" Task Name <<"+tasks.get(0).getName()+">>");
        logger.info("==============================================================");
        taskService.start(taskId, "suresh");
        taskService.complete(taskId, "suresh", null);
        
        ProcessInstance pi = ksession.getProcessInstance(processInstance.getId());
        assertNull(pi);
	}

}
