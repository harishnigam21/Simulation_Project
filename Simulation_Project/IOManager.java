package SimulationProject;

import java.util.LinkedList;
import java.util.Queue;

public class IOManager {

   Queue<Process> runningProcess;
   Queue<Process> completedProcess;
   int num_of_running_processes;

   //Constructor
   IOManager(){
       runningProcess = new LinkedList();
       completedProcess = new LinkedList();
       num_of_running_processes = 0;
   }

   //Method accepts processes in running queue
   public void takeProcess(Process p)
   {
       this.runningProcess.add(p);
       num_of_running_processes++;
   }

   //To check if there are any processes in running queue
   public boolean hasAnyProcess()
   {
       return !this.runningProcess.isEmpty();
   }

   //To check if there are any processes in completed queue
   public boolean hasCompletedProcesses()
   {
       return !this.completedProcess.isEmpty();
   }

   //Method to return processes that are completed
   public Process giveProcess()
   {
       return this.completedProcess.poll();
   }

   //Method to perform the execution of processes
   public void run(){

       if(this.hasAnyProcess())
       {
           for(int i = 0,temp = num_of_running_processes; i < temp; i++)
           {
               Process nextProcess = this.runningProcess.poll();
               if(nextProcess.IOTimeLeft!=0)
               {
                   nextProcess.IOTimeLeft -= 100;
                   nextProcess.IOExtraTime += 5*temp;
                   nextProcess.IOExecTime += 100;
                   this.runningProcess.add(nextProcess);
               }
               else
               {
                   this.completedProcess.add(nextProcess);
                   num_of_running_processes--;
               }
           }
       }
   }

}
