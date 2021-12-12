package SimulationProject;

import java.util.Random;

public class ProcessCreator {
   int percentageOfIOBoundProcess;
   int processRate;
   Random r = new Random();

   //Constructor
   ProcessCreator(int percentageOfIOBoundProcess, int processRate) {
       this.percentageOfIOBoundProcess = percentageOfIOBoundProcess;
       this.processRate = processRate;
   }

   int nextProcessTime=0;
   int processID = 0;
   int count = 0;     //Count of the processes arrived
   int IOPercent;

   //Method to define how many processes are IOBound
   public void processType(){
       if(count<(processRate-percentageOfIOBoundProcess))
           IOPercent = 20;

       else IOPercent = 80;
   }

   //Method to send process to the controller
   public Process giveProcess() {
       processType();
       Process p = new Process(processID, IOPercent, (r.nextInt(5) + 3)*1000);
       processID++;
       nextProcessTime+=(r.nextInt(4)+1)*1000;
       count++;
       return p;
   }


   //To check if there is any new Process to be sent to the controller
   public Boolean isProcessAvailable(int clock) {
       if(clock == nextProcessTime && nextProcessTime<300000){
           return true;
       }
       else return false;
   }
}
