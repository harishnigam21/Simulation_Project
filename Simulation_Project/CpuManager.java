package SimulationProject;

import java.util.LinkedList;
import java.util.Queue;


public class CpuManager {

   public Queue<Process> runningProcess;
   public Queue<Process> completedProcess;

   //Constructor
   CpuManager() {
       runningProcess = new LinkedList();  //to store processes that needs Execution
       completedProcess = new LinkedList();    //to store processes that have completed execution and are waiting to go to the Controller
   }

   //To check if there are any processes in running queue
   public boolean hasAnyProcess() {
       return !this.runningProcess.isEmpty();
   }

   //To check if there are any processes in completed queue
   public boolean hasCompletedProcess() {
       return !this.completedProcess.isEmpty();
   }

   //Method accepts processes in running queue
   public void takeProcess(Process p) {
       this.runningProcess.add(p);
   }

   //Method to perform the execution of processes
   public void run() {

       if (this.hasAnyProcess()) {
           Process nextProcess = this.runningProcess.poll();
           if (nextProcess.cpuTimeLeft != 0) {
               nextProcess.cpuTimeLeft -= 100;
               nextProcess.cpuTimeUsed += 100;
               this.runningProcess.add(nextProcess);
           } else {
               this.completedProcess.add(nextProcess);
           }
       }
   }

   //Method to return processes to the Controller that are completed
   public Process giveProcess() {
       return this.completedProcess.poll();
   }
}
