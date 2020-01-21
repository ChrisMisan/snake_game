package utils;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import simulation.Grid;
import java.io.FileReader;

public class Parser {

   public void loadMapParam(Grid map,String json){
       try {
           JSONObject parser = (JSONObject)new JSONParser().parse(new FileReader(json));
           map.width=((Long)parser.get("width")).intValue();
           map.height=((Long)parser.get("height")).intValue();
           map.fruitNum=((Long)parser.get("fruitNum")).intValue();
           map.wallNum=((Long)parser.get("wallNum")).intValue();
       }
       catch(Exception error){
           System.out.println("File read error "+error.getMessage());
       }


       if(map.width*map.cell>900)
           map.width=900/map.cell;
       if(map.height*map.cell>650)
           map.height=650/map.cell;
   }


}
