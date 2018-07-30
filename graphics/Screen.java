package graphics;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import elements.*;
import ships.*;

abstract class Screen {
    
    private static Controller controller;
    private static boolean p1IsVertical;
    private static boolean p2IsVertical;
    private static int p1Ship = 1;
    private static int p2Ship = 1;
    
    static void start(Stage primaryStage, int gameMode) { // 1 = PVP, 0 = PVE
        controller = new Controller(gameMode == 1);
        Button[][] tableP1 = new Button[10][10];
        Button[][] tableP2 = new Button[10][10];
        primaryStage.setTitle("Battleship");
        primaryStage.setResizable(false);
        GridPane gridPane = new GridPane();
        
        generatePanel(tableP1, tableP2, gridPane, gameMode);
        
        Scene scene = new Scene(gridPane, 1011,350);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
    private static void generatePanel(Button[][] tableP1, Button[][] tableP2,
            GridPane gridPane, int gameMode) { /* PVP = 1 ou PVE = 2 */     
        /*-------------------Player1-----------------------*/
        addColumnGap(gridPane, 0, 10);
        
        createGrid(tableP1, gridPane, 0);
        
        addColumnGap(gridPane, 10, 50);
        
        Button orientationP1 = new Button (controller.getPlayer1().getTextOrientation());
        add(gridPane, orientationP1, 11, 2, 100, 35);
        
        Button shipP1 = new Button (controller.getPlayer2().getTextShipAdding());
        add(gridPane, shipP1, 11, 3, 100, 35);
        
        Text player1 = new Text();
        player1.setText("Player 1");
        player1.setFont(new Font (25));
        GridPane.setConstraints(player1, 11, 1);
        gridPane.getChildren().add(player1);
        
        addColumnGap(gridPane, 12, 35);
        
        drawVLine(gridPane, Color.BLACK, 13, 10, 35);
        
        addColumnGap(gridPane, 14, 35);
        
        setButtons(tableP1, shipP1, orientationP1, controller.getPlayer1());
        
        /*-------------------Player2-----------------------*/
        createGrid(tableP2, gridPane, 15);
        
        addColumnGap(gridPane, 25, 50);
        
        Button orientationP2 = new Button (controller.getPlayer2().getTextOrientation());
        add(gridPane, orientationP2, 26, 2, 100, 35);
        
        Button shipP2 = new Button (controller.getPlayer2().getTextShipAdding());
        add(gridPane, shipP2, 26, 3, 100, 35);
     
        Text player2 = new Text();
        if(gameMode == 1){
            player2.setText("Player 2");
        } else {
            player2.setText("BOT");
        }
        player2.setFont(new Font (25));
        GridPane.setConstraints(player2, 26, 1);
        gridPane.getChildren().add(player2);
        
        addColumnGap(gridPane, 27, 10);
        setButtons(tableP2, shipP2, orientationP2,controller.getPlayer2());
    }
    
    private static void add(GridPane grid, Button add, int column, int line,
            int sizeX, int sizeY) {
        add.setPrefSize(sizeX, sizeY);
        GridPane.setConstraints(add, column, line);
        grid.getChildren().add(add);      
    }
    
    private static void addColumnGap(GridPane grid, int column, int size) {
        Rectangle gap = new Rectangle(size,1);
        gap.setFill(Color.TRANSPARENT);
        grid.addColumn(column, gap);
    }
    
    private static void drawVLine(GridPane grid, Color color, int column, 
            int nLines, int lineHeight) {
        for (int i = 0; i < nLines; i++) {    
            Rectangle line = new Rectangle(1,lineHeight);
            line.setFill(color);
            grid.addColumn(column, line);
        }
    }
    
    private static void createGrid(Button[][] table, GridPane grid, int start){
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = new Button (j +","+i);
                add(grid, table[i][j], (i+1)+start, j, 35, 35);
            }
        }       
        
    }
    
    private static void setButtons(Button[][] grid, Button ship, Button orientation, Player player) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                final Button button = grid[i][j];
                final int x = j;
                final int y = i;
                grid[i][j].setOnAction((ActionEvent e) -> {
                    addShip(player, x, y, player.getOrientationAdding(),
                            player.getTextShipAdding(), grid);
                    ship.setText(player.getTextShipAdding());
                    
                    if(ship.getText().equals("")) {
                        ship.setDisable(true);
                        orientation.setDisable(true);
                    }
                    
                });
            }
        }
        orientation.setOnAction((ActionEvent e) -> {
            player.changeOrientationAdding();
            orientation.setText(player.getTextOrientation());
        });
        
        ship.setOnAction((ActionEvent e) -> { /* verificar */
            player.increaseShipAdding();
            ship.setText(player.getTextShipAdding());
            if(ship.getText().equals("")) {
                ship.setDisable(true);
                orientation.setDisable(true);
            }
        });
    }

    private static void fillShipSpace(Button[][] grid, int x, int y, boolean orientation, int size){
        if (orientation) {
            for (int i = y; i < (y+size); i++) {
                grid[i][x].setDisable(true);
                grid[i][x].setStyle("-fx-base: #006600");
            }
        } else {
            for (int i = x; i < (x+size); i++) {
                grid[y][i].setDisable(true);
                grid[y][i].setStyle("-fx-base: #006600");
            }
        }
    }
    
    private static void addShip(Player player, int x, int y, 
            boolean orientation, String textShipAdding, Button[][] grid) {
                
        boolean isOk;
        if(textShipAdding.equals("Submarine (1)")) {
            isOk = player.addShip(new Submarine(x, y, orientation));
            if(isOk) {
                fillShipSpace(grid, x, y, orientation, 1);
            }
        }
        
        if(textShipAdding.equals("Destroyer (2)")) {
            isOk = player.addShip(new Destroyer(x, y, orientation));
            if(isOk) {
                fillShipSpace(grid, x, y, orientation, 2);
            }
        }
        
        if(textShipAdding.equals("Cruiser (3)")) {
            isOk = player.addShip(new Cruiser(x, y, orientation));
            if(isOk) {
                fillShipSpace(grid, x, y, orientation, 3);
            }
        }
        
        if(textShipAdding.equals("Battleship (4)")) {
            isOk = player.addShip(new Battleship(x, y, orientation));
            if(isOk) {
                fillShipSpace(grid, x, y, orientation, 4);
            }
        }
        
        if(textShipAdding.equals("Carrier (5)")) {
            isOk = player.addShip(new Carrier(x, y, orientation));
            if(isOk) {
                fillShipSpace(grid, x, y, orientation, 5);
            }
        }
    }
    
    
}
