import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home/home.component';
import { NgChartsModule } from 'ng2-charts';
import { ListComponent } from './task/list/list.component';
import { MaterialModule } from '../material/material/material.module';



@NgModule({
  declarations: [HomeComponent, ListComponent],
  imports: [
    CommonModule, NgChartsModule, MaterialModule
  ],
  exports: [HomeComponent]
})
export class ComponentModule { }
