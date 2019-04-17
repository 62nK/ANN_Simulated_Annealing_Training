public interface Constants {

    // Constants
    int PROBLEM_SIZE = 36;
    int WHITE_PIXEL = 10;
    int BLACK_PIXEL = -10;

    /** Neural Network **/
    // Neuron Configuration
    int LAYER_COUNT = 3;
    int INPUT_LAYER_NEURON_COUNT = PROBLEM_SIZE;
    int HIDDEN_LAYER_NEURON_COUNT = 2;
    int OUTPUT_LAYER_NEURON_COUNT = 2;

    // Simulated Annealing Algorithm
    double INITIAL_TEMPERATURE = 10; // 1.44269 K
    double TEMPERATURE_SCHEDULE_MULTIPLIER = 0.90;   // Controls the cool-down
    double FINAL_TEMPERATURE = INITIAL_TEMPERATURE*0.0001;    // Hill Climbing Switch

    // Debug: std output messages
    boolean DEBUG = true;

    // Training
    int EPOCHS = 1000000;
    double TARGET_NETWORK_ERROR = 1000; // 0.4 for 4, 10 for 9

    // IO
    String PATH = "src/cfg/";
    String FILENAME = "3l-9in-2h-2out.ann";

    // JavaFX
    double SQUARE_SIZE = 50;
    double HEIGHT = 300;
    double WIDTH = 800;
}
