package simulation;
import java.util.Map;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utils.Vector2;

import java.util.HashMap;

public class Grid {
   public int cell=10;
   public int height;
   public int width;
   public int fruitNum;
   public int wallNum;
   private Random rand=new Random();

   HashMap<Vector2,GridObject> grid=new HashMap<Vector2,GridObject>();
   void initializeWall(){
       for(int i=0;i<wallNum;i++){
           Vector2 pos=new Vector2(rand.nextInt(width),rand.nextInt(height));
           if(pos.x==width/2&&pos.y==height/2||isOccupied(pos)) {
               i--;
               continue;
           }
           grid.put(pos,new Wall(pos));
       }
   }

   void initializeFruit(){
       for(int i=0;i<fruitNum;i++){
           Vector2 pos=new Vector2(rand.nextInt(width),rand.nextInt(height));
           if(pos.x==width/2&&pos.y==height/2||isOccupied(pos)) {
               i--;
               continue;
           }
           grid.put(pos,new Fruit(pos));
       }
   }

    public boolean isOccupied(Vector2 pos){
       if(grid.get(pos)!=null)
           return true;
       return false;

    }

    public void removeObject(Vector2 pos) {
        grid.remove(pos);
    }

    public void visualize(GraphicsContext context){

        context.setFill(Color.BISQUE);
        context.fillRect((900-width*cell)/2,(650-height*cell)/2,width*cell,height*cell);

        for(Map.Entry element:grid.entrySet()){
            Vector2 pos=(Vector2)element.getKey();
            GridObject obj=(GridObject)element.getValue();
            if(obj instanceof Fruit)
                context.setFill(Color.RED);
            else
                context.setFill(Color.GRAY);
            context.fillRect((900-width*cell)/2+obj.pos.x*cell,(650-height*cell)/2+obj.pos.y*cell,cell,cell);


            context.setStroke(Color.BLACK);
            context.setLineWidth(0.2);
            context.strokeRect((900-width*cell)/2,(650-height*cell)/2,width*cell,height*cell);
        }


    }

    public void addRandomFruit(){
        for(int i=0;i<1;i++){
            Vector2 pos=new Vector2(rand.nextInt(width),rand.nextInt(height));
            if(isOccupied(pos)) {
                i--;
                continue;
            }
            grid.put(pos,new Fruit(pos));
        }

    }

    public void forceWall(Vector2 pos){
       grid.put(pos,new Wall(pos));
    }
}
