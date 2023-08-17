import { Component } from '@angular/core';
import { ChartConfiguration, ChartOptions, ChartEvent, ChartData, ChartType } from 'chart.js';
import { TypePercentage } from 'src/app/interface/task.interface';
import { TaskService } from 'src/app/services/task.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

   typeData:TypePercentage[]=[];
   typeCount:number = 0;
   type:String[]=[];
   data:number[]=[];
   public doughnutChartLabels: String[]=[];
   public doughnutChartData:any;
   public doughnutChartType: ChartType = 'doughnut';
   public doughnutChartOptions:ChartOptions={};
   constructor(private taskService:TaskService){
   }
  createChart(){
     this.doughnutChartLabels = this.type;
   this.doughnutChartData = {
    labels: this.doughnutChartLabels,
    datasets: [
      { data: this.data,
        backgroundColor:["#FF7360", "#6FC8CE", "#F2FCC4", "#B9E8E0"] }
    ],
  };
  this.doughnutChartOptions = {
    responsive: true,
  };

  }

  // events
  public chartClicked({
    event,
    active,
  }: {
    event: ChartEvent;
    active: object[];
  }): void {
    console.log(event, active);
  }

  public chartHovered({
    event,
    active,
  }: {
    event: ChartEvent;
    active: object[];
  }): void {
    console.log(event, active);
  }
  

  ngOnInit(){
    this.taskService.getCountPercentType().subscribe(res=>
      {this.typeData=res;
        this.typeCount=this.typeData.length;
        this.typeData.forEach((data)=>{
          this.type.push(data.type);
          this.data.push(data.count_percent);
          this.createChart();
        })
      });
  }


}
