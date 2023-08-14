package com.schedule.task.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.schedule.task.dto.CountType;
import com.schedule.task.entity.Task;
import com.schedule.task.repository.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> getAllTasks(){
		return (List<Task>) taskRepository.findAll();
	}
	
	public Task saveTask(Task task) {
		Task newTask = taskRepository.save(task);
		return newTask;
	}
	
	public boolean existById(Long id) {
		return taskRepository.existsById(id);
	}
	
	public Task getTask(Long id) {
		return taskRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Requested Entity Not Found"));
	}
	
	public Task updateTask(Long id, Task taskPara) {
		Task task = getTask(id);
		task.setTitle(taskPara.getTitle());
		task.setType(taskPara.getType());
		task.setDescription(taskPara.getDescription());
		task.setDueDate(taskPara.getDueDate());
		Task updateTask = taskRepository.save(task);
		return updateTask;
	}
	
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
	
	public List<Task> getTasksByType(String type){
		return taskRepository.findByType(type);
	}
	
	public List<Task> getAllNotCompletedTasks() {
		return taskRepository.findAllTasksNotCompleted("Completed", Sort.by(Direction.ASC, "dueDate"));
	}
	
	public List<CountType> getPercentageByType(){
		return taskRepository.getGroupByType();
	}

}
