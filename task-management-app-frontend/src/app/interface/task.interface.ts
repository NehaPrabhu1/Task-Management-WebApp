interface Task {
    id?: string;
    title: string;
    type: string;
    dueDate: Date;
    description: string;
  }
  interface TaskTypeOption {
    type: string;
  }
  
  interface TypePercentage {
    type: string;
    count_percent: number;
  }
  
  export { Task, TaskTypeOption, TypePercentage };