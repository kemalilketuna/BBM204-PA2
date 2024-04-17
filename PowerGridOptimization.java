import java.util.ArrayList;

/**
 * This class accomplishes Mission POWER GRID OPTIMIZATION
 */
public class PowerGridOptimization {
    private ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour;

    public PowerGridOptimization(ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour){
        this.amountOfEnergyDemandsArrivingPerHour = amountOfEnergyDemandsArrivingPerHour;
    }

    public ArrayList<Integer> getAmountOfEnergyDemandsArrivingPerHour() {
        return amountOfEnergyDemandsArrivingPerHour;
    }

    /**
     *     Function to implement the given dynamic programming algorithm
     *     SOL(0) <- 0
     *     HOURS(0) <- [ ]
     *     For{j <- 1...N}
     *         SOL(j) <- max_{0<=i<j} [ (SOL(i) + min[ E(j), P(j − i) ] ]
     *         HOURS(j) <- [HOURS(i), j]
     *     EndFor
     *
     * @return OptimalPowerGridSolution
     */
    public OptimalPowerGridSolution getOptimalPowerGridSolutionDP() {
        int N = amountOfEnergyDemandsArrivingPerHour.size();
        int[] SOL = new int[N + 1];
        ArrayList<ArrayList<Integer>> HOURS = new ArrayList<>();

        // Initialize SOL and HOURS
        SOL[0] = 0;
        HOURS.add(new ArrayList<>());  // Empty list for HOURS(0)

        // Dynamic programming to calculate SOL and HOURS
        for (int j = 1; j <= N; j++) {
            int maxSol = Integer.MIN_VALUE;
            ArrayList<Integer> bestHours = new ArrayList<>();

            for (int i = 0; i < j; i++) {
                int potentialSol = SOL[i] + Math.min(amountOfEnergyDemandsArrivingPerHour.get(j - 1), (j - i) * (j - i));  // Assuming P(j - i) is a method that gives power available from j-i

                if (potentialSol > maxSol) {
                    maxSol = potentialSol;
                    bestHours = new ArrayList<>(HOURS.get(i));
                    bestHours.add(j);
                }
            }

            SOL[j] = maxSol;
            HOURS.add(bestHours);
        }

        return new OptimalPowerGridSolution(SOL[N], HOURS.get(N));
    }

}
