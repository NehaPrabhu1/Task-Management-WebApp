package com.schedule.task.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.schedule.task.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{
	
	public List<Task> findByType(String type);
	
	@Query("from Task where type!=:Completed")
	public List<Task> findAllTasksNotCompleted(@Param("Completed") String type, Sort sort);

}