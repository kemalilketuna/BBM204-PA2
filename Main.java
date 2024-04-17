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

        BufferedReader reader2 = new BufferedReader(new FileReader(args[1]));
        parts = reader2.readLine().trim().split(" ");
        int maxNumberOfAvailableESVs = Integer.parseInt(parts[0]);
        int maxESVCapacity = Integer.parseInt(parts[1]);
        ArrayList<Integer> maintenanceTaskEnergyDemands = new ArrayList<>();
        parts = reader2.readLine().trim().split(" ");
        for (String part : parts) {
            maintenanceTaskEnergyDemands.add(Integer.parseInt(part));
        }
        OptimalESVDeploymentGP optimalESVDeploymentGP = new OptimalESVDeploymentGP(maintenanceTaskEnergyDemands);
        int minNumESVsToDeploy = optimalESVDeploymentGP.getMinNumESVsToDeploy(maxNumberOfAvailableESVs, maxESVCapacity);
        // The minimum number of ESVs to deploy: 5
        // ESV 1 tasks: [100]
        // ESV 2 tasks: [80, 20]
        // ESV 3 tasks: [70, 30]
        // ESV 4 tasks: [50, 40, 10]
        // ESV 5 tasks: [10]
        System.out.println("The minimum number of ESVs to deploy: " + minNumESVsToDeploy);
        ArrayList<ArrayList<Integer>> maintenanceTasksAssignedToESVs = optimalESVDeploymentGP.getMaintenanceTasksAssignedToESVs();
        for (int i = 0; i < maintenanceTasksAssignedToESVs.size(); i++) {
            System.out.println("ESV " + (i + 1) + " tasks: " + maintenanceTasksAssignedToESVs.get(i));
        }
        reader2.close();
        System.out.println("##MISSION ECO-MAINTENANCE COMPLETED##");
    }
}
