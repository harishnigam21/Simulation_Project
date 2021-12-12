package SimulationProject;

public class Main{

   static int totalExpectedTime = 0;

   //Method to calculate total time of each process
   public static void calculateTotalTime(Controller cont)
   {
       System.out.println("Total Processes: "+cont.pc.count);
       int i = 0;
       while(!cont.completedProcesses.isEmpty() && i < cont.pc.count)
       {
           Process nextProcess = cont.completedProcesses.peek();
           nextProcess.waitTime = nextProcess.endTime - nextProcess.startTime - nextProcess.IOExecTime - nextProcess.cpuTimeUsed;
           totalExpectedTime += nextProcess.totalTime;
           cont.completedProcesses.add(cont.completedProcesses.poll());
           i++;
       }
   }
   //Method to print all processes after execution
   public static void printProcesses(Controller cont)
   {
       int i = 0;
       while(!cont.completedProcesses.isEmpty() && i < cont.pc.count)
       {
           Process nextProcess = cont.completedProcesses.peek();
           System.out.println(nextProcess.toString());
           cont.completedProcesses.add(cont.completedProcesses.poll());
           i++;
       }
   }

   public static void main(String[] args) {

       Controller cont = new Controller();
       cont.run();
       calculateTotalTime(cont);
       printProcesses(cont);
       System.out.println("Overall time expected = "+totalExpectedTime);
       System.out.println("Actual time taken = "+(cont.clock+cont.IOExtraTime));

   }

}



