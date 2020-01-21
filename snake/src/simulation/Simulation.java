package simulation;

import javafx.scene.canvas.GraphicsContext;
import control.Controller;
import utils.Parser;
import utils.Vector2;

public class Simulation {
    Grid map=new Grid();
    public Snake snake;
    Parser parser=new Parser();
    public int state=1;//0 play//1 pause
    public long time=0;
    public double delay=300;


public void reset(){
    time=0;
    map=new Grid();
    this.initialize("param.json");
    state=1;

}
public void initialize(String json){
parser.loadMapParam(map,json);
map.initializeFruit();
map.initializeWall();
snake=new Snake(new Vector2(map.width/2,map.height/2));
}

public void simulate(long time){
    if(state==1||time-this.time<delay)
        return;
    this.time=time;

    Vector2 nextPos=snake.positions.getFirst().sum(snake.direction);
    if(nextPos.x<0||nextPos.y<0||nextPos.x>=map.width||nextPos.y>=map.height){
        state=1;
        return;
    }
    if(map.isOccupied(nextPos)){
        if(map.grid.get(nextPos) instanceof Wall){
            state=1;
            return;
        }


        map.removeObject(nextPos);
        snake.positions.add(0,nextPos);
        snake.length++;
        map.forceWall(nextPos);
        map.addRandomFruit();


    }else{


        map.removeObject(snake.positions.getLast());
        snake.positions.removeLast();
        snake.positions.add(0,nextPos);
        map.forceWall(nextPos);
    }



}

public void visualize(GraphicsContext context){
    map.visualize(context);
    snake.visualize(context,map.cell,map.height,map.width);
}

public void statUpdate(Controller controller){
    controller.Points.setText(Integer.toString(snake.length));

}

}
