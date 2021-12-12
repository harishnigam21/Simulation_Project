package SimulationProject;

public class Process {

   int processID;
   int IOPercentage;  //Percentage of total time to be spent in IO
   int totalTime;      //total time required to run alone
   int waitTime;      //Overall time process spent waiting in the queue
   int cpuTimeUsed;  //Time spent in CPU
   int cpuTimeLeft;  //Time left to execute in CPU
   int IOExecTime;  //Time spent in IO excluding extra time
   int IOExtraTime;  //Extra time taken due to slow IO
   int IOTimeLeft;  //Time left to execute in IO
   int startTime;    //Time when Process was given to the controller
   int endTime;      //Time when process completed its IO and CPU execution


   Process(int processID,int IOPercentage,int totalTime){
       this.processID=processID;
       this.IOPercentage=IOPercentage;
       this.totalTime=totalTime;
       this.waitTime = 0;
       this.cpuTimeUsed = 0;
       this.cpuTimeLeft = totalTime*(1-IOPercentage/100);
       this.IOExecTime = 0;
       this.IOTimeLeft = totalTime * IOPercentage/100;
       this.IOExtraTime = 0;
   }


   //Method which returns the mode of process(i.e. CPU or I/O)
   public String getCurrentTask(){
       String task = "";
       if(this.cpuTimeLeft != 0)
           return "CPU" + this.cpuTimeLeft;
       if(this.IOTimeLeft != 0)
           return "I/O" + this.IOTimeLeft;
       return "END";
   }

   @Override
   public String toString() //We made this to make printing pproccesses in the end easier
   {
       String full = "";
       full += "PID = "+this.processID +", Start time = "+this.startTime+", End time = "+this.endTime +", Total Execution time = "+Integer.toString((this.endTime-this.startTime))+", Expected Execution time = "+ (this.totalTime)+", Wait time = "+ this.waitTime;
//        full = "cpu used = "+this.cpuTimeUsed;
       return full;
   }


}
