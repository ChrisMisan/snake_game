package simulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utils.Vector2;

import java.util.LinkedList;

public class Snake {
    public int length;
    public LinkedList<Vector2> positions=new LinkedList<Vector2>();
    public Vector2 direction=new Vector2(0,1);
    public Snake(Vector2 pos){
        length=1;
        positions.add(new Vector2(pos.x,pos.y));

    }

    public void visualize(GraphicsContext context,int cell,int height,int width){
        context.setFill(Color.GREEN);
        for(int i=0;i<positions.size();i++){
            Vector2 pos=positions.get(i);
            context.fillRect((900-width*cell)/2+pos.x*cell,(650-height*cell)/2+pos.y*cell,cell,cell);
        }
    }

}
