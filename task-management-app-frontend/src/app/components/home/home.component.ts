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

   constructor(private taskService:TaskService){
   }

  public doughnutChartLabels: String[] = this.type;
  public doughnutChartData: ChartData<'doughnut'> = {
    labels: this.doughnutChartLabels,
    datasets: [
      { data: this.data,
        backgroundColor:["#FF7360", "#6FC8CE", "#F2FCC4", "#B9E8E0"] }
    ],
  };
  
  public doughnutChartType: ChartType = 'doughnut';

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
  public doughnutChartOptions:ChartOptions = {
    responsive: true,
  };

  ngOnInit(){
    this.taskService.getCountPercentType().subscribe(res=>
      {this.typeData=res;
        this.typeCount=this.typeData.length;
        this.typeData.forEach((data)=>{
          this.type.push(data.type);
          this.data.push(data.count_percent);
        })
      });
  }


}
