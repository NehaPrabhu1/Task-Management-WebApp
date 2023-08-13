package com.schedule.task.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.schedule.task.entity.Task;
import com.schedule.task.service.TaskService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping("/")
	public String welcome() {
		return "Welcome to Task Management API";
	}
	
	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getTasks(){
		List<Task> tasks = taskService.getAllTasks();
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/task/{id}")
	public ResponseEntity<Task> getTaskUsingId(@PathVariable("id") Long id){
		Task task = taskService.getTask(id);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}
	
	@GetMapping("/task/type/{type}")
	public ResponseEntity<List<Task>> getAllTasksByType(@PathVariable("type") String type){
		List<Task> tasks = taskService.getTasksByType(type);
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	@PostMapping("/task")
	public ResponseEntity<Task> addTask(@RequestBody Task task){
		Task savedTask = taskService.saveTask(task);
		return new ResponseEntity<Task>(savedTask, HttpStatus.CREATED); 
	}
	
	@PutMapping("/task/update/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable("id") Long id, @RequestBody Task task){
		Task updatedTask = taskService.updateTask(id, task);
		return new ResponseEntity<Task>(updatedTask, HttpStatus.CREATED);
	}
	
	@GetMapping("/tasks/remaining")
	public ResponseEntity<List<Task>> displayTasks(){ //display tasks on dashboard
		List<Task> tasks = taskService.getAllNotCompletedTasks();
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	

}
