package com.schedule.task.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.schedule.task.dto.CountType;
import com.schedule.task.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long>{
	
	public List<Task> findByType(String type);
	
	@Query("from Task where type!=:Completed")
	public List<Task> findAllTasksNotCompleted(@Param("Completed") String type, Sort sort);
	
	@Query("select new com.schedule.task.dto.CountType(type, "
			+ "COUNT(*)*100.00/(select count(*) from Task)) "
			+ "from Task group by type")
	public List<CountType> getGroupByType();
	
	@Query("select new com.schedule.task.dto.CountType(type, "
			+ "COUNT(*)*100.00/(select count(*) from Task where type!=:Completed)) "
			+ "from Task group by type having type!=:Completed")
	public List<CountType> getGroupByTypeNotCompleted(@Param("Completed") String type);

}
