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
        }while((size + axis) >= 10);
        
        if(ship.equals("Submarine")) {
            if(!super.addShip(new Submarine (x, y, orientationAdding))) {
                placeShips("Submarine");
            }            
        }
        
        if(ship.equals("Destroyer")) {
            if(!super.addShip(new Destroyer (x, y, orientationAdding))) {
                placeShips("Destroyer");
            }    
        }
        
        if(ship.equals("Cruiser")) {
            if(!super.addShip(new Cruiser (x, y, orientationAdding))) {
                placeShips("Cruiser");
            }            }
       
        if(ship.equals("Battleship")) {
            if(!super.addShip(new Battleship (x, y, orientationAdding))) {
                placeShips("Battleship");
            }            }
        
        if(ship.equals("Carrier")) {
            if(!super.addShip(new Carrier (x, y, orientationAdding))) {
                placeShips("Carrier");
            }    
        }
        
    }
    
    @Override
    public void fire(){
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);
        
        if(!alreadyShot[y][x]) {
            buttonsToShot[y][x].fire();
            alreadyShot[y][x] = true;
        } else {
            fire();
        }
    }
    
}
