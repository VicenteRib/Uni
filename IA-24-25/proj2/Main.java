import java.util.*;
import distancematrix.*;

public class Main {
    public static void main(String[] args) {
        // Initialize the distance matrix, cities, and algorithm parameters
        Scanner in = new Scanner(System.in);
        DistanceMatrix dm = new DistanceMatrix("input.txt"); // Assuming it's already populated
        ArrayList<String> inputs = new ArrayList<>();
        String cities = in.nextLine();
        for(String city : cities.split(",")) {
            inputs.add(city.strip());
        }

        double initialTemperature = 10000;
        double coolingRate = 0.995;

        // Create a SimulatedAnnealing object
        SimulatedAnnealing sa = new SimulatedAnnealing(dm, initialTemperature, coolingRate,9000);

        // Solve the problem and get the best tour
        sa.solve(inputs);

    }
}
