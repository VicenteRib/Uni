import distancematrix.*;
import java.util.*;

public class SimulatedAnnealing {
    private DistanceMatrix distanceMatrix;
    private double initialTemperature;
    private double coolingRate;
    private int maxAttempts; // Número máximo de tentativas se a temperatura não mudar

    // Construtor atualizado para incluir maxAttempts
    public SimulatedAnnealing(DistanceMatrix dm, double initialTemperature, double coolingRate, int maxAttempts) {
        this.distanceMatrix = dm;
        this.initialTemperature = initialTemperature;
        this.coolingRate = coolingRate;
        this.maxAttempts = maxAttempts;
    }

    // Método para calcular a distância total de uma solução
    public int calculateDistance(ArrayList<String> cities) {
        int distance = 0;
        String previousCity = null;
        for (String city : cities) {
            if (previousCity == null) {
                previousCity = city;
                continue;
            }
            distance += distanceMatrix.distance(previousCity, city);
            previousCity = city;
        }
        return distance;
    }

    // Método para criar uma solução vizinha trocando duas cidades
    private ArrayList<String> createNeighbor(ArrayList<String> currentSolution) {
        ArrayList<String> newSolution = new ArrayList<>(currentSolution);
        Random random = new Random();
        int i = random.nextInt(newSolution.size());
        int j = random.nextInt(newSolution.size());

        // Trocar duas cidades
        Collections.swap(newSolution, i, j);

        return newSolution;
    }

    // Algoritmo de Simulated Annealing com tentativas quando a temperatura não mudar
    public void solve(ArrayList<String> cities) {

        Collections.shuffle(cities);

        ArrayList<String> currentSolution = new ArrayList<>(cities);
        ArrayList<String> bestSolution = new ArrayList<>(currentSolution);
        ArrayList<String> worstSolution = new ArrayList<>(currentSolution);
        ArrayList<String> firstSolution = new ArrayList<>(currentSolution);

        int currentDistance = calculateDistance(currentSolution);
        int bestDistance = currentDistance;
        int worstDistance = currentDistance;
        int firstDistance = currentDistance;

        ArrayList<String> lastSolution = new ArrayList<>(currentSolution);
        int lastDistance = currentDistance;

        double temperature = initialTemperature;
        double lastTemperature = initialTemperature;
        double worstTemperature = initialTemperature; // Track the temperature where worst solution is found
        int attempts = 0; // Contador de tentativas

        Random random = new Random();

        // Medir o tempo de execução
        long startTime = System.currentTimeMillis();
        
        // Loop principal para o Simulated Annealing
        while (attempts <= maxAttempts){
            ArrayList<String> neighborSolution = createNeighbor(currentSolution);
            int neighborDistance = calculateDistance(neighborSolution);

            if (neighborDistance < currentDistance) {
                currentSolution = neighborSolution;
                currentDistance = neighborDistance;

                if (currentDistance < bestDistance) {
                    bestSolution = new ArrayList<>(currentSolution);
                    bestDistance = currentDistance;
                    attempts = 0;
                }
            } else {
                double acceptanceProbability = Math.exp((currentDistance - neighborDistance) / temperature);
                if (acceptanceProbability > random.nextDouble()) {
                    currentSolution = neighborSolution;
                    currentDistance = neighborDistance;

                    temperature *= coolingRate; 
                }else {
                    attempts++;
                }
            }

            if (currentDistance > worstDistance) {
                worstSolution = new ArrayList<>(currentSolution);
                worstDistance = currentDistance;
                worstTemperature = temperature;
            }

            lastSolution = new ArrayList<>(currentSolution);
            lastDistance = currentDistance;

        }

        // Medir o tempo de execução
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // Exibir os resultados
        System.out.println("Primeira Solução:");
        printSolution(firstSolution, firstDistance, initialTemperature);

        System.out.println("Melhor Solução:");
        printSolution(bestSolution, bestDistance, temperature);

        System.out.println("Pior Solução:");
        printSolution(worstSolution, worstDistance, worstTemperature);
        System.out.println("Última Solução:");
        printSolution(lastSolution, lastDistance, temperature);

        System.out.println("Tempo de Execução: " + executionTime + "ms");
    }

    private void printSolution(ArrayList<String> solution, int distance, double temperature) {
        System.out.println("Percurso: " + solution);
        System.out.println("Distância: " + distance);
        System.out.println("Temperatura: " + temperature);
        System.out.println();
    }
}
