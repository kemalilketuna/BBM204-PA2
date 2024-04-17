import java.io.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Main class
 */
// FREE CODE HERE
public class Main {
    public static void main(String[] args) throws IOException {

       /** MISSION POWER GRID OPTIMIZATION BELOW **/

        System.out.println("##MISSION POWER GRID OPTIMIZATION##");

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour = new ArrayList<>();
        String[] parts = reader.readLine().trim().split(" ");
        for (String part : parts) {
            amountOfEnergyDemandsArrivingPerHour.add(Integer.parseInt(part));
        }
        PowerGridOptimization powerGridOptimization = new PowerGridOptimization(amountOfEnergyDemandsArrivingPerHour);
        OptimalPowerGridSolution optimalPowerGridSolution = powerGridOptimization.getOptimalPowerGridSolutionDP();
        // The total number of demanded gigawatts: 22
        // Maximum number of satisfied gigawatts: 12
        // Hours at which the battery bank should be discharged: 3, 5
        // The number of unsatisfied gigawatts: 10
        int sumOfHour = amountOfEnergyDemandsArrivingPerHour.stream().mapToInt(Integer::intValue).sum();
        System.out.println("The total number of demanded gigawatts: " + sumOfHour);
        System.out.println("Maximum number of satisfied gigawatts: " + optimalPowerGridSolution.getmaxNumberOfSatisfiedDemands());
        String hours = optimalPowerGridSolution.getHoursToDischargeBatteriesForMaxEfficiency().toString();
        hours = hours.substring(1, hours.length() - 1);
        System.out.println("Hours at which the battery bank should be discharged: " + hours);
        System.out.println("The number of unsatisfied gigawatts: " + (sumOfHour - optimalPowerGridSolution.getmaxNumberOfSatisfiedDemands()));
        System.out.println("##MISSION POWER GRID OPTIMIZATION COMPLETED##");
        reader.close();



        /** MISSION ECO-MAINTENANCE BELOW **/

        System.out.println("##MISSION ECO-MAINTENANCE##");
        // TODO: Your code goes here
        // You are expected to read the file given as the second command-line argument to read
        // the number of available ESVs, the capacity of each available ESV, and the energy requirements 
        // of the maintenance tasks. Then, use this data to instantiate an OptimalESVDeploymentGP object.
        // You need to call getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity) method
        // of your OptimalESVDeploymentGP object to get the solution, and finally print it to STDOUT.
        System.out.println("##MISSION ECO-MAINTENANCE COMPLETED##");
    }
}
