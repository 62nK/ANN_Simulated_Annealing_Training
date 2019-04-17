import ArtificialNeuralNetwork.NeuralNetwork;

public class SimulatedAnnealing {

    public static double measureEnergy(NeuralNetwork neuralNetwork){
        return neuralNetwork.getNetworkError();
    }
    public static boolean accept(NeuralNetwork currentNeuralNetwork, NeuralNetwork nextNeuralNetwork, Temperature temperature){
        if(measureEnergy(nextNeuralNetwork)<=measureEnergy(currentNeuralNetwork))
            return true;
        else {
            double probability = getAcceptanceProbability(currentNeuralNetwork, nextNeuralNetwork, temperature);
            double random = Math.random();
            if(Constants.DEBUG) System.out.println("Acceptance Probability:"+probability+", Random seed:"+random+", Decision:"+(random<=probability?"accepted":"rejected"));
            return random<=probability;
        }
    }

    public static double getAcceptanceProbability(NeuralNetwork currentNeuralNetwork, NeuralNetwork nextNeuralNetwork, Temperature temperature){
        return Math.exp(-(measureEnergy(nextNeuralNetwork) - measureEnergy(currentNeuralNetwork)) / temperature.getTemperature());
    }

}
