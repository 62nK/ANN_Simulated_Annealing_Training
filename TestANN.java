import ArtificialNeuralNetwork.NeuralNetwork;
import ArtificialNeuralNetwork.InputTargetPair;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestANN extends Application implements Constants {

    // Variables
    int problemSize = PROBLEM_SIZE;
    int layerCount = LAYER_COUNT;
    int inputLayerNeuronCount = INPUT_LAYER_NEURON_COUNT;
    int hiddenLayerNeuronCount = HIDDEN_LAYER_NEURON_COUNT;
    int outputLayerNeuronCount = OUTPUT_LAYER_NEURON_COUNT;
    int epochs = EPOCHS;

    String filename = PATH+FILENAME;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Objects
        NeuralNetwork artificialNeuralNetwork;
        BorderPane borderPane = new BorderPane();
        ControlPanel controlPanel = new ControlPanel();
        HBox hBox = new HBox(5);
        InputImagePane inputImagePane = new InputImagePane(problemSize);
        OutputDisplay outputDisplay = new OutputDisplay();

        // Input
        ArrayList<InputTargetPair> trainingSet = new ArrayList<>();
//        {
//            trainingSet.add(new InputTargetPair(new double[]{-10, -10, -10, -10}, new double[]{0, 1}));
//            trainingSet.add(new InputTargetPair(new double[]{-10, -10, -10, 10}, new double[]{0, 1}));
//            trainingSet.add(new InputTargetPair(new double[]{-10, -10, 10, -10}, new double[]{0, 1}));
//            trainingSet.add(new InputTargetPair(new double[]{-10, -10, 10, 10}, new double[]{1, 0}));
//            trainingSet.add(new InputTargetPair(new double[]{-10, 10, -10, -10}, new double[]{0, 1}));
//            trainingSet.add(new InputTargetPair(new double[]{-10, 10, -10, 10}, new double[]{1, 0}));
//            trainingSet.add(new InputTargetPair(new double[]{-10, 10, 10, -10}, new double[]{1, 0}));
//            trainingSet.add(new InputTargetPair(new double[]{-10, 10, 10, 10}, new double[]{1, 0}));
//            trainingSet.add(new InputTargetPair(new double[]{10, -10, -10, -10}, new double[]{0, 1}));
//            trainingSet.add(new InputTargetPair(new double[]{10, -10, -10, 10}, new double[]{1, 0}));
//            trainingSet.add(new InputTargetPair(new double[]{10, -10, 10, -10}, new double[]{1, 0}));
//            trainingSet.add(new InputTargetPair(new double[]{10, -10, 10, 10}, new double[]{1, 0}));
//            trainingSet.add(new InputTargetPair(new double[]{10, 10, -10, -10}, new double[]{1, 0}));
//            trainingSet.add(new InputTargetPair(new double[]{10, 10, -10, 10}, new double[]{1, 0}));
//            trainingSet.add(new InputTargetPair(new double[]{10, 10, 10, -10}, new double[]{1, 0}));
//            trainingSet.add(new InputTargetPair(new double[]{10, 10, 10, 10}, new double[]{1, 0}));
//        }
        for(int index=0; index<PROBLEM_SIZE*PROBLEM_SIZE; index++){
            double[] input = new double[PROBLEM_SIZE];
            randomGenerator(input);
            InputTargetPair inputTargetPair = new InputTargetPair(input, targetGenerator(input));
            trainingSet.add(inputTargetPair);
        }
        ArrayList<InputTargetPair> testSet = new ArrayList<>();
        {
            testSet.add(new InputTargetPair(new double[]{10, 10, -10, -10}, new double[]{1, 0}));
            testSet.add(new InputTargetPair(new double[]{-10, 10, 10, -10}, new double[]{1, 0}));
            testSet.add(new InputTargetPair(new double[]{10, 10, 10, 10}, new double[]{1, 0}));
            testSet.add(new InputTargetPair(new double[]{-10, -10, -10, -10}, new double[]{0, 1}));
            testSet.add(new InputTargetPair(new double[]{-10, -10, 10, -10}, new double[]{0, 1}));
            testSet.add(new InputTargetPair(new double[]{10, 10, 10, 10}, new double[]{1, 0}));

        }
        ArrayList<double[]> inputs = new ArrayList<>();
        for(InputTargetPair itp: trainingSet) inputs.add(itp.getInput());

        artificialNeuralNetwork = new NeuralNetwork(layerCount, inputLayerNeuronCount, hiddenLayerNeuronCount, outputLayerNeuronCount);

        // Main Pane
        hBox.getChildren().add(inputImagePane);
        hBox.getChildren().add(outputDisplay);
        hBox.setSpacing(10);
        borderPane.setPadding(new Insets(20));
        borderPane.setCenter(hBox);
        borderPane.setLeft(controlPanel);

        Button nextState = new Button("Next Example");
        Button train = new Button("Train (SA)");
        Button test = new Button("Test");
        Button restore = new Button("Read");
        Button save = new Button("Write");
        VBox buttons = new VBox();
        buttons.setAlignment(Pos.TOP_CENTER);
        buttons.setSpacing(5);
        buttons.getChildren().addAll(restore, save, train, test, nextState);
        nextState.setMinHeight(100);
        borderPane.setRight(buttons);
        nextState.setOnMouseClicked(event -> {
            inputImagePane.randomGenerator();
            inputImagePane.draw();
            categorize(artificialNeuralNetwork, inputImagePane.getInput());
            outputDisplay.drawOutput(artificialNeuralNetwork.getOutput());
            int[] w = whitePixelsCounter(inputImagePane.getInput());
            System.out.println("whites:"+w[0]+" blacks:"+w[1]);
        });
        train.setOnMouseClicked(event -> {
            train(artificialNeuralNetwork, trainingSet, epochs);
        });
        test.setOnMouseClicked(event -> {
            test(artificialNeuralNetwork, testSet);
        });
        restore.setOnMouseClicked(event -> {
            read(artificialNeuralNetwork, filename);
        });
        save.setOnMouseClicked(event -> {
            write(artificialNeuralNetwork, filename);
        });

        // Output
        if(DEBUG) print(artificialNeuralNetwork);

        // Put the pane on the scene and the scene on the stage
        Scene scene = new Scene(borderPane, WIDTH, Constants.HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Artificial Neural Network Dark/Bright detection with Simulated Annealing - Andrea Pinardi");
        primaryStage.show();
    }

    public static void write(NeuralNetwork artificialNeuralNetwork, String filename){
        artificialNeuralNetwork.writeANNToFile(filename);
        if(DEBUG) System.out.println(artificialNeuralNetwork.toString("Neural Network written to file \""+filename+"\""));
    }
    public static void print(NeuralNetwork artificialNeuralNetwork){
        System.out.println(artificialNeuralNetwork.toString("NeuralNetwork"));
    }
    public static void train(NeuralNetwork artificialNeuralNetwork, ArrayList<InputTargetPair> trainingSet, int epochs){
        Temperature t = new Temperature();
        NeuralNetwork currentNeuralNetwork = artificialNeuralNetwork;
        currentNeuralNetwork.measureNetworkError(trainingSet);

        for(int e=0; e<epochs; e++) {
            NeuralNetwork candidateNeuralNetwork = new NeuralNetwork(artificialNeuralNetwork.toMap());
            for(int layerIndex=1; layerIndex<LAYER_COUNT; layerIndex++) {
                int neuronCount = candidateNeuralNetwork.getLayer(layerIndex).getAxonCount();
                for(int neuronIndex=0; neuronIndex<neuronCount; neuronIndex++) {
                    candidateNeuralNetwork.resetSynapses(layerIndex, neuronIndex);
                    candidateNeuralNetwork.measureNetworkError(trainingSet);
                    if (SimulatedAnnealing.accept(artificialNeuralNetwork, candidateNeuralNetwork, t))
                        currentNeuralNetwork = candidateNeuralNetwork;
                    System.out.println("NE=" + currentNeuralNetwork.getNetworkError() + "J," + t.toString());
                }

            }
            t.decreaseTemperature();
            if(currentNeuralNetwork.getNetworkError()<TARGET_NETWORK_ERROR) break;
            if(t.getTemperature()<FINAL_TEMPERATURE) t.resetTemperature();
        }
        artificialNeuralNetwork.toNeurons(currentNeuralNetwork.toMap());
        if(DEBUG) System.out.println("Training set error("+trainingSet.size()+"):"+currentNeuralNetwork.getNetworkError());
    }
    public static void categorize(NeuralNetwork artificialNeuralNetwork, ArrayList<double[]> inputs){
        for(double[] input: inputs){
            artificialNeuralNetwork.feedForward(input);
            double[] output = artificialNeuralNetwork.getOutput();
            if(DEBUG) {
                System.out.println(artificialNeuralNetwork.outputToString());
                if (output[0] > output[1])
                    System.out.println("Bright");
                else
                    System.out.println("Dark");
            }
        }
    }

    private static void read(NeuralNetwork neuralNetwork, String filename){
        neuralNetwork.toNeurons(NeuralNetwork.readANNFromFile(filename));
        if(DEBUG) System.out.println(neuralNetwork.toString("Neural Network read from file \""+filename+"\""));
    }

    private static void test(NeuralNetwork artificialNeuralNetwork, ArrayList<InputTargetPair> testSet){
        artificialNeuralNetwork.test(testSet);
        if(DEBUG) System.out.println("Test set error("+testSet.size()+"):"+artificialNeuralNetwork.getNetworkError());
    }
    private static void categorize(NeuralNetwork artificialNeuralNetwork, double[] input) {
        artificialNeuralNetwork.feedForward(input);
        double[] output = artificialNeuralNetwork.getOutput();
        if (DEBUG) {
            System.out.println(artificialNeuralNetwork.outputToString());
            if (output[0] > output[1])
                System.out.println("Bright");
            else
                System.out.println("Dark");
        }
    }
    private static void randomGenerator(double[] array){
        for(int index=0; index<array.length; index++)
            array[index] = Math.random()>=0.5?WHITE_PIXEL:BLACK_PIXEL;
    }
    private static double[] targetGenerator(double[] input){
        double[] target = new double[2];
        int whitePixelsCounter = 0;
        for (int index=0; index<input.length; index++)
            whitePixelsCounter +=input[index]==WHITE_PIXEL?+1:-1;
        target[0] = whitePixelsCounter>=0?1:0;
        target[1] = whitePixelsCounter>=0?0:1;
        return target;
    }
    private static int[] whitePixelsCounter(double[] input){
        int[] target = new int[2];
        int whitePixelsCounter = 0;
        for (int index=0; index<input.length; index++)
            whitePixelsCounter +=input[index]==WHITE_PIXEL?+1:0;
        target[0] = whitePixelsCounter;
        target[1] = input.length-whitePixelsCounter;
        return target;
    }
}