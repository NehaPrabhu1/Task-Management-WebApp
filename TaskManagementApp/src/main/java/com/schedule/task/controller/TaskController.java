package com.schedule.task.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schedule.task.dto.CountType;
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
	
	@PostMapping("/task")
	public ResponseEntity<Task> addTask(@RequestBody Task task){
		Task savedTask = taskService.saveTask(task);
		return new ResponseEntity<Task>(savedTask, HttpStatus.CREATED); 
	}
	
	@GetMapping("/task/{id}")
	public ResponseEntity<?> getTaskUsingId(@PathVariable("id") Long id){
		if(taskService.existById(id)) {
			Task task = taskService.getTask(id);
			return new ResponseEntity<Task>(task, HttpStatus.OK);
		}
		else {
			HashMap<String, String> message = new HashMap<>();
			message.put("message", "Requested task not found");
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/task/{id}")
	public ResponseEntity<?> updateTask(@PathVariable("id") Long id, @RequestBody Task task){
		if(taskService.existById(id)) {
			Task updatedTask = taskService.updateTask(id, task);
			return new ResponseEntity<Task>(updatedTask, HttpStatus.OK);
		}
		else {
			HashMap<String, String> message = new HashMap<>();
			message.put("message", "Requested task not found");
			return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/task/{id}")
	public ResponseEntity<HashMap<String,String>> deleteTask(@PathVariable("id") Long id){
		HashMap<String, String> message = new HashMap<>();
		if(taskService.existById(id)) {
			taskService.deleteTask(id);
			message.put("message", "Requested task deleted successfully !!!");
			return new ResponseEntity<HashMap<String,String>>(message, HttpStatus.OK);
		}
		else {
			message.put("message", "Requested task not found !!!");
			return new ResponseEntity<HashMap<String,String>>(message, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/task/type/{type}")
	public ResponseEntity<List<Task>> getAllTasksByType(@PathVariable("type") String type){
		List<Task> tasks = taskService.getTasksByType(type);
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/tasks/remaining")
	public ResponseEntity<List<Task>> displayTasks(){ //display tasks on dashboard
		List<Task> tasks = taskService.getAllNotCompletedTasks();
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	
	@GetMapping("/task/type/count")
	public List<CountType> displayCountPercentType(){
		return taskService.getPercentageByType();
	}

}
