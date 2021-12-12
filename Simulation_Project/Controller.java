package SimulationProject;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Controller {

   Queue<Process> processes;
   LinkedList<Process> completedProcesses;
   int clock;
   IOManager iom;
   CpuManager cpum;
   ProcessCreator pc;
   int IOExtraTime;

   //Constructor
   public Controller() {
       Scanner sc = new Scanner(System.in);
       processes = new LinkedList();
       completedProcesses = new LinkedList();
       this.clock = 0;
       this.IOExtraTime=0;
       this.cpum = new CpuManager();
       this.iom = new IOManager();
       System.out.println("Enter Percentage of IO Bound processes");
       int ioboundpercentage = sc.nextInt();
       this.pc = new ProcessCreator(ioboundpercentage, 100);
   }

   //This simulates running of cpu manager every sync
   public void run() {
       //Controller runs until time is 5 minutes and no process in cpu or io
       for (this.clock = 0;completedProcesses.size() != pc.count || pc.count==0; this.clock += 100) {
           //This part is for checking processes in pc, cpum and iom
           //It checks if process creator has a process.
           if (pc.isProcessAvailable(clock))
           {
               processes.add(pc.giveProcess());
               processes.peek().startTime = this.clock;
           }
           //It checks if cpu manager has any process
           if (cpum.hasCompletedProcess())
           {
               //If any process is completed we will take it back into the controller queue
               processes.add(cpum.giveProcess());
           }
           //It checks if cpu manager has any process
           if(iom.hasCompletedProcesses())
           {
               //If any process is completed we will take it back into the controller processes queue
               processes.add(iom.giveProcess());
           }

           //This part is for giving processes to cpum and iom if needed
           if (!processes.isEmpty())
           {
               //if the current task is "CPU" then send the process to cpu manager
               if (processes.peek().getCurrentTask().substring(0, 3).equals("CPU"))
                   cpum.takeProcess(this.processes.poll());
               else if(processes.peek().getCurrentTask().substring(0, 3).equals("I/O"))
                   iom.takeProcess(this.processes.poll());
               else
               {
                   this.IOExtraTime+=this.processes.peek().IOExtraTime;
                   this.processes.peek().endTime = this.clock + this.IOExtraTime;
                   this.completedProcesses.add(this.processes.poll());
               }
           }
           cpum.run();
           iom.run();
       }

   }
}
