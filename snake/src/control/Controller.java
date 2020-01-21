package control;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import simulation.Simulation;

public class Controller {
    public Label Points;
    public Slider speedSlider;
    public javafx.scene.control.Button Reset;
    Simulation simulation;
    public void ResetButtonAction(javafx.event.ActionEvent event) {
        simulation.reset();
    }


    public void SliderAction(MouseEvent mouseEvent){
        simulation.delay=300-2.5*speedSlider.getValue();
    }
}

