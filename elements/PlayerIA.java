package elements;

import javafx.scene.control.Button;
import ships.*;


public class PlayerIA extends Player{
     
    private Button[][] buttonsToPlace;
    private Button[][] buttonsToShot;
    private boolean isHunting;
    private boolean huntingVertical;
    private int shootsOnEnemy;
    private Coordinates[] hits;
    private boolean alreadyShot[][];
            
    public PlayerIA(Button[][] buttonsToPlace, Button[][] buttonsToShot){
        super(false, 2, "BOT");
        this.buttonsToPlace = buttonsToPlace;
        this.buttonsToShot  = buttonsToShot;
        alreadyShot = new boolean [10][10];
    }
    
    @Override
    public void generatePlaceGrid(){
        placeShips("Submarine");
        placeShips("Submarine");
        placeShips("Destroyer");
        placeShips("Destroyer");
        placeShips("Cruiser");
        placeShips("Battleship");
        placeShips("Carrier");
    }

    private void placeShips(String ship){
        int size = 0;
        int x, y, axis;
        Ship shipToAdd;
        
        if(ship.equals("Submarine")) {
            size = 1;            
        }
        
        if(ship.equals("Destroyer")) {
            size = 2;
        }
        
        if(ship.equals("Cruiser")) {
            size = 3;
        }
       
        if(ship.equals("Battleship")) {
            size = 4;
        }
        
        if(ship.equals("Carrier")) {
            size = 5;
        }
        
        do{
            orientationAdding = Math.random() < 0.5;
            x = (int) (Math.random()*10);
            y = (int) (Math.random()*10);
            
            if(orientationAdding) {
                axis = y;
            } else {
                axis = x;
            }
        } while((size + axis) >= 10);
        
        if(ship.equals("Submarine")) {
            Ship submarine = new Submarine (x, y, orientationAdding);
            if(!super.addShip(submarine)) {
                placeShips("Submarine");
            }            
        }
        
        if(ship.equals("Destroyer")) {
            Ship destroyer = new Destroyer (x, y, orientationAdding);
            if(!super.addShip(destroyer)) {
                placeShips("Destroyer");
            }    
        }
        
        if(ship.equals("Cruiser")) {
            Ship cruiser = new Cruiser (x, y, orientationAdding);
            if(!super.addShip(cruiser)) {
                placeShips("Cruiser");
            }
        }
       
        if(ship.equals("Battleship")) {
            Ship battleship = new Battleship (x, y, orientationAdding);
            if(!super.addShip(battleship)) {
                placeShips("Battleship");
            }
        }
        
        if(ship.equals("Carrier")) {
            Ship carrier = new Carrier (x, y, orientationAdding);
            if(!super.addShip(carrier)) {
                placeShips("Carrier");
            }    
        }
    }
    
    @Override
    public void fire(){
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        
        if(!alreadyShot[y][x]) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            buttonsToShot[y][x].fire();
            alreadyShot[y][x] = true;
        } else {
            fire();
        }
    }
    
}
