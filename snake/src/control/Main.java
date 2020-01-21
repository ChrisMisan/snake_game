package control;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import simulation.Simulation;
import utils.Vector2;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Controller controller = fxmlLoader.<Controller>getController();
        primaryStage.setTitle("Snake");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 960, 720));
        primaryStage.show();

        Scene scene = primaryStage.getScene();
        Canvas canvas=(Canvas)scene.lookup("#Canvas");
        GraphicsContext context = canvas.getGraphicsContext2D();

        Simulation simulation=new Simulation();
        simulation.initialize("param.json");
        controller.simulation=simulation;


        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if(simulation.time==0){
                    simulation.state=0;
                    if (ke.getCode() == KeyCode.W) {
                            simulation.snake.direction=new Vector2(0,-1);
                        ke.consume();
                    }
                    if (ke.getCode() == KeyCode.S) {
                            simulation.snake.direction=new Vector2(0,1);
                        ke.consume();
                    }
                    if (ke.getCode() == KeyCode.A) {
                            simulation.snake.direction=new Vector2(-1,0);
                        ke.consume();
                    }
                    if (ke.getCode() == KeyCode.D) {
                            simulation.snake.direction=new Vector2(1,0);
                        ke.consume();
                    }


                }
                if (ke.getCode() == KeyCode.W) {
                    if(!(simulation.snake.direction.opposite().equals(new Vector2(0,-1))))
                    simulation.snake.direction=new Vector2(0,-1);
                    ke.consume();
                }
                if (ke.getCode() == KeyCode.S) {
                    if(!(simulation.snake.direction.opposite().equals(new Vector2(0,1))))
                        simulation.snake.direction=new Vector2(0,1);
                    ke.consume();
                }
                if (ke.getCode() == KeyCode.A) {
                    if(!(simulation.snake.direction.opposite().equals(new Vector2(-1,0))))
                        simulation.snake.direction=new Vector2(-1,0);
                    ke.consume();
                }
                if (ke.getCode() == KeyCode.D) {
                    if(!(simulation.snake.direction.opposite().equals(new Vector2(1,0))))
                        simulation.snake.direction=new Vector2(1,0);
                    ke.consume();
                }


            }
        });


        //main loop
        new AnimationTimer(){
            public void handle(long currentNanoTime)
            {
                context.clearRect(0,0,900,650);
                simulation.visualize(context);
                simulation.simulate(System.currentTimeMillis());
                simulation.statUpdate(controller);
            }

        }.start();
        primaryStage.show();




    }


    public static void main(String[] args) {
        launch(args);
    }
}
