import { Component } from '@angular/core';
import { Task } from 'src/app/interface/task.interface';
import { TaskService } from 'src/app/services/task.service';
import { MatDialog } from '@angular/material/dialog';


@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent {
  tasks:Task[] = [];
  remainingTasks:Task[] = [];
  completedTasks:Task[] = [];

  constructor(private taskService:TaskService){}

  ngOnInit(): void {
    this.getTasks();
  }
  getTasks() {
    this.taskService.getTaskList().subscribe(res=>this.tasks=res,err=>console.log(err));
    this.taskService.getRemainingTasks().subscribe(res=>this.remainingTasks=res);
    this.taskService.getCompletedTasks().subscribe(res=>this.completedTasks=res);
  }

  



}
