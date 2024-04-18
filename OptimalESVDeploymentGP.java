import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission Eco-Maintenance
 */
public class OptimalESVDeploymentGP
{
    private ArrayList<Integer> maintenanceTaskEnergyDemands;

    /*
     * Should include tasks assigned to ESVs.
     * For the sample input:
     * 8 100
     * 20 50 40 70 10 30 80 100 10
     * 
     * The list should look like this:
     * [[100], [80, 20], [70, 30], [50, 40, 10], [10]]
     * 
     * It is expected to be filled after getMinNumESVsToDeploy() is called.
     */
    private ArrayList<ArrayList<Integer>> maintenanceTasksAssignedToESVs = new ArrayList<>();

    ArrayList<ArrayList<Integer>> getMaintenanceTasksAssignedToESVs() {
        return maintenanceTasksAssignedToESVs;
    }

    public OptimalESVDeploymentGP(ArrayList<Integer> maintenanceTaskEnergyDemands) {
        this.maintenanceTaskEnergyDemands = maintenanceTaskEnergyDemands;
    }

    public ArrayList<Integer> getMaintenanceTaskEnergyDemands() {
        return maintenanceTaskEnergyDemands;
    }

    /**
     *
     * @param maxNumberOfAvailableESVs the maximum number of available ESVs to be deployed
     * @param maxESVCapacity the maximum capacity of ESVs
     * @return the minimum number of ESVs required using first fit approach over reversely sorted items.
     * Must return -1 if all tasks can't be satisfied by the available ESVs
     */
    public int getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity) {
        // Sort tasks in decreasing order
        Collections.sort(maintenanceTaskEnergyDemands, Collections.reverseOrder());

        if(maintenanceTaskEnergyDemands.get(0) > maxESVCapacity) {
            return -1; // Largest task can't be handled by any ESV
        }

        // List to store each ESV and the sum of tasks assigned to it
        ArrayList<Integer> esvCapacities = new ArrayList<>();

        // Assign each task to an ESV
        for (int demand : maintenanceTaskEnergyDemands) {
            boolean placed = false;

            // Try to place the task in already existing ESVs
            for (int i = 0; i < esvCapacities.size(); i++) {
                if (esvCapacities.get(i) + demand <= maxESVCapacity) {
                    esvCapacities.set(i, esvCapacities.get(i) + demand);
                    maintenanceTasksAssignedToESVs.get(i).add(demand);
                    placed = true;
                    break;
                }
            }

            // If task can't be placed in existing ESVs, create a new one
            if (!placed) {
                if (esvCapacities.size() == maxNumberOfAvailableESVs) {
                    return -1; // Not enough ESVs to handle all tasks
                }
                esvCapacities.add(demand);
                ArrayList<Integer> newEsv = new ArrayList<>();
                newEsv.add(demand);
                maintenanceTasksAssignedToESVs.add(newEsv);
            }
        }

        return esvCapacities.size(); // Return the number of ESVs used
    }
}
