import {Task} from './task';


export class TaskList {
  private taskListId: number;
  private taskListName : string;
  private tasks : Task[];

  public constructor(taskListName : string, tasks : Task[] ){
    this.taskListName = taskListName;
    this.tasks = tasks;
  }

  getTaskListName() : string {
    return this.taskListName;
  }

  setTaskListName(taskListName : string) : void {
    this.taskListName = taskListName;
  }

  getTasks() : Task[] {
    return this.tasks;
  }

  setTasks(tasks : Task[]) : void {
    this.tasks = tasks;
  }


}
