import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ControlPanel extends VBox implements Constants{

    TextField textFieldProblemSize, textFieldLayerCount, textFieldHiddenLayer, textFieldOutputLayer, textFieldFileName;

    public ControlPanel(){
        draw();
    }

    private void draw(){
        // Problem size
        HBox hbproblemSize = new HBox();
        Text textProblemSize = new Text(0,30, "Problem Size: ");
        textFieldProblemSize = new TextField();
        textFieldProblemSize.setMaxWidth(40);
        textFieldProblemSize.setFont(Font.font(9));
        textFieldProblemSize.setText(""+PROBLEM_SIZE);
        hbproblemSize.getChildren().addAll(textProblemSize, textFieldProblemSize);

        // Problem size
        HBox hBoxLayerCount = new HBox();
        Text textLayerCount = new Text(0,30, "Layer Count: ");
        textFieldLayerCount = new TextField();
        textFieldLayerCount.setMaxWidth(40);
        textFieldLayerCount.setFont(Font.font(9));
        textFieldLayerCount.setText(""+LAYER_COUNT);
        hBoxLayerCount.getChildren().addAll(textLayerCount, textFieldLayerCount);

        // Problem size
        HBox hBoxHiddenLayer = new HBox();
        Text textHiddenLayer = new Text(0,30, "Hidden Layer Neuron Count: ");
        textFieldHiddenLayer = new TextField();
        textFieldHiddenLayer.setMaxWidth(40);
        textFieldHiddenLayer.setFont(Font.font(9));
        textFieldHiddenLayer.setText(""+HIDDEN_LAYER_NEURON_COUNT);
        hBoxHiddenLayer.getChildren().addAll(textHiddenLayer, textFieldHiddenLayer);

        // Problem size
        HBox hBoxOutputLayer = new HBox();
        Text textOutputLayer = new Text(0,30, "Output Layer Neuron Count: ");
        textFieldOutputLayer = new TextField();
        textFieldOutputLayer.setMaxWidth(40);
        textFieldOutputLayer.setFont(Font.font(9));
        textFieldOutputLayer.setText(""+OUTPUT_LAYER_NEURON_COUNT);
        hBoxOutputLayer.getChildren().addAll(textOutputLayer, textFieldOutputLayer);

        // Problem size
        HBox hBoxFileName = new HBox();
        Text textFileName = new Text(0,30, "File Name: ");
        textFieldFileName = new TextField();
        textFieldFileName.setMinWidth(140);
        textFieldFileName.setFont(Font.font(9));
        textFieldFileName.setText(PATH+FILENAME);
        hBoxFileName.getChildren().addAll(textFileName, textFieldFileName);

        getChildren().addAll(hbproblemSize, hBoxLayerCount, hBoxHiddenLayer,
                hBoxOutputLayer, hBoxFileName);
    }

}
