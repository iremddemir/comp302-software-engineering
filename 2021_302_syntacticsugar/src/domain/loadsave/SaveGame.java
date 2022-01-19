package domain.loadsave;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

import domain.GameBoard;
//import domain.physicalobjects.Obstacle;
//external java-simple module for json
import domain.Player;
import domain.abilities.AbilityType;
import domain.abilities.UsefulAbilityType;
import domain.physicalobjects.PhysicalObject;
import domain.physicalobjects.obstacles.Obstacle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class SaveGame {
    private GameBoard gameBoard;
    private Calendar calendar;
    private Player player;


    public SaveGame(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        this.player = gameBoard.getPlayer();
        this.calendar = Calendar.getInstance();

    }
    public void saveGame() {
        JSONObject savedGame = new JSONObject();
        JSONArray obstaclesList = new JSONArray();
        JSONArray abilitiesList = new JSONArray();
        JSONArray obj;
        int time = gameBoard.getTime();

        List<PhysicalObject> obstacles = gameBoard.getPhysicalObjects();
        List<AbilityType> abilities = gameBoard.getAvailableAbilities();

        //adding player info.
        savedGame.put("username", player.getUsername());
        savedGame.put("lives", player.getLives());
        savedGame.put("score", player.getScore());
        savedGame.put("time", time);


        int i = 0;
        int health;
        for(PhysicalObject o : obstacles) {
            if (o.getClass().getSuperclass().getSimpleName().equals("Obstacle") ){
                if(o.toString().equals("FirmObstacle")) {
                    i = 0;
                }
                else if(o.toString().equals("SimpleObstacle")) {
                    i = 1;
                }
                else if(o.toString().equals("ExplosiveObstacle")) {
                    i = 2;
                }
                else if(o.toString().equals("GiftObstacle")) {
                    i = 3;
                }
                obj = new JSONArray();
                health = ((Obstacle) o).getHealth();
                obj.add(i);
                obj.add(health);
                obj.add(o.getLocation().getX());
                obj.add(o.getLocation().getY());
                obstaclesList.add(obj);
            }

        }

        savedGame.put("obstacles", obstaclesList);
        int chance = 0;
        int magical = 0;
        int paddle = 0;
        int ball = 0;

        for (AbilityType a : abilities){
            if(a.toString().equals("ChanceGivingAbility")) {
                chance++;
            }
            else if(a.toString().equals("MagicalHexAbility")) {
                magical++;
            }
            else if(a.toString().equals("PaddleExpansionAbility")) {
                paddle++;
            }

            else if(a.toString().equals("UnstoppableBallAbility")) {
                ball++;
            }

        }

        abilitiesList.add(chance);
        abilitiesList.add(magical);
        abilitiesList.add(paddle);
        abilitiesList.add(ball);

        savedGame.put("abilities", abilitiesList);

        String fileName = generateFileName();
        try(FileWriter file = new FileWriter(fileName)){
            file.write(savedGame.toJSONString());
            file.flush();
        }
        catch(IOException e) {e.printStackTrace();}
    }

    private String generateFileName() {
        String fileName;
        fileName = String.format("./savedGames/%s_%s%d_%dh_%dm.json", player.getUsername(), getMonth(), getDate(), getHour(), getMin());

        return fileName;
    }

    private int getMin() {
        return calendar.get(Calendar.MINUTE);
    }

    private int getHour() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    private int getDate() {
        return calendar.get(Calendar.DATE);
    }

    public String getMonth() {
        return new SimpleDateFormat("MMM").format(calendar.getTime());
    }

}





/*
Format of json file

{
  "playerInfo": {
    "username": "username",
    "score": "score",
    "lives": "lives",
    "obstacles": [
        // if i = 0 -> SimpleObstacle
        // if i = 1 -> FirmObstacle
        // if i = 2 -> GiftObstacle
        // if i = 3 -> ExplosiveObstacle
        // if i = 4 -> HollowObstacle
        [i, health, x, y],
        [i, health, x, y],
        [i, health, x, y],
        [i, health, x, y]
    ]
  },
  "abilities": [1, 1, 0, 0] //example

}
 */



