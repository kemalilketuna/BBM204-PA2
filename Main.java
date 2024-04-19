import java.io.*;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {

       /** MISSION POWER GRID OPTIMIZATION BELOW **/

        System.out.println("##MISSION POWER GRID OPTIMIZATION##");

        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour = new ArrayList<>();
        String[] parts = reader.readLine().trim().split(" ");
        int sumOfHour = 0;
        for (String part : parts) {
            int hour = Integer.parseInt(part);
            sumOfHour += hour;
            amountOfEnergyDemandsArrivingPerHour.add(hour);
        }
        PowerGridOptimization powerGridOptimization = new PowerGridOptimization(amountOfEnergyDemandsArrivingPerHour);
        OptimalPowerGridSolution optimalPowerGridSolution = powerGridOptimization.getOptimalPowerGridSolutionDP();

        System.out.println("The total number of demanded gigawatts: " + sumOfHour);
        System.out.println("Maximum number of satisfied gigawatts: " + optimalPowerGridSolution.getmaxNumberOfSatisfiedDemands());
        String hours = optimalPowerGridSolution.getHoursToDischargeBatteriesForMaxEfficiency().toString();
        hours = hours.substring(1, hours.length() - 1); // Remove brackets
        System.out.println("Hours at which the battery bank should be discharged: " + hours);
        System.out.println("The number of unsatisfied gigawatts: " + (sumOfHour - optimalPowerGridSolution.getmaxNumberOfSatisfiedDemands()));
        System.out.println("##MISSION POWER GRID OPTIMIZATION COMPLETED##");
        reader.close();


        /** MISSION ECO-MAINTENANCE BELOW **/

        System.out.println("##MISSION ECO-MAINTENANCE##");

        reader = new BufferedReader(new FileReader(args[1]));
        parts = reader.readLine().trim().split(" ");
        int maxNumberOfAvailableESVs = Integer.parseInt(parts[0]);
        int maxESVCapacity = Integer.parseInt(parts[1]);
        ArrayList<Integer> maintenanceTaskEnergyDemands = new ArrayList<>();
        parts = reader.readLine().trim().split(" ");
        for (String part : parts) {
            maintenanceTaskEnergyDemands.add(Integer.parseInt(part));
        }
        OptimalESVDeploymentGP optimalESVDeploymentGP = new OptimalESVDeploymentGP(maintenanceTaskEnergyDemands);
        int minNumESVsToDeploy = optimalESVDeploymentGP.getMinNumESVsToDeploy(maxNumberOfAvailableESVs, maxESVCapacity);
        
        if (minNumESVsToDeploy == -1) {
            System.out.println("Warning: Mission Eco-Maintenance Failed.");
        }else{
            System.out.println("The minimum number of ESVs to deploy: " + minNumESVsToDeploy);
            ArrayList<ArrayList<Integer>> maintenanceTasksAssignedToESVs = optimalESVDeploymentGP.getMaintenanceTasksAssignedToESVs();
            for (int i = 0; i < maintenanceTasksAssignedToESVs.size(); i++) {
                System.out.println("ESV " + (i + 1) + " tasks: " + maintenanceTasksAssignedToESVs.get(i));
            }
        }
        reader.close();
        System.out.println("##MISSION ECO-MAINTENANCE COMPLETED##");
    }
}
