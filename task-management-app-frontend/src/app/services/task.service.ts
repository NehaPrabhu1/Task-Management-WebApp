import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Task, TypePercentage } from '../interface/task.interface';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private httpClient:HttpClient) { }

  getTaskList(){
    return this.httpClient.get<Task[]>('http://localhost:8080/api/v1/tasks');
  }

  getCountPercentType(){
    return this.httpClient.get<TypePercentage[]>('http://localhost:8080/api/v1/task/type/count');
  }

  getRemainingTasks(){
    return this.httpClient.get<Task[]>('http://localhost:8080/api/v1/tasks/remaining');
  }

  getCompletedTasks(){
    return this.httpClient.get<Task[]>('http://localhost:8080/api/v1/task/type/Completed');
  }
}
