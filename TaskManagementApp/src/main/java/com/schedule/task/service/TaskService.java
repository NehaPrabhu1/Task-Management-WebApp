package com.schedule.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.schedule.task.entity.Task;
import com.schedule.task.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> getAllTasks(){
		return (List<Task>) taskRepository.findAll();
	}
	
	public Task getTask(Long id) {
		return taskRepository.findById(id).get();
	}
	
	public List<Task> getTasksByType(String type){
		return taskRepository.findByType(type);
	}
	
	public Task saveTask(Task task) {
		Task newTask = taskRepository.save(task);
		return newTask;
	}
	
	public Task updateTask(Long id, Task task) {
		Task updateTask = null;
		if(taskRepository.existsById(id)) {
			updateTask = taskRepository.save(task);
		}
		return updateTask;
	}
	
	public List<Task> getAllNotCompletedTasks() {
		return taskRepository.findAllTasksNotCompleted("Completed", Sort.by(Direction.ASC, "dueDate"));
	}
	

}
