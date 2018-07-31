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

abstract class ScreenShot {

    private static Controller controller;
    private static boolean p1IsVertical;
    private static boolean p2IsVertical;
    private static Stage primaryStage;
    private static int gameMode;

    static void start(Stage primaryStage, int gameMode, Controller controller) { // 1 = PVP, 0 = PVE
        ScreenShot.controller = controller;
        ScreenShot.gameMode = gameMode;
        controller.setGameState(1);
        ScreenShot.primaryStage = primaryStage;
        Button[][] tableP1 = controller.getShotGrid(1);
        Button[][] tableP2 = controller.getShotGrid(2);
        primaryStage.setTitle("Battleship");
        primaryStage.setResizable(false);
        GridPane gridPane = new GridPane();

        generatePanel(tableP1, tableP2, gridPane, gameMode);

        Scene scene = new Scene(gridPane, 1011, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void generatePanel(Button[][] tableP1, Button[][] tableP2,
            GridPane gridPane, int gameMode) {
        /* PVP = 1 ou PVE = 2 */
 /*-------------------Player1-----------------------*/
        addColumnGap(gridPane, 0, 10);

        createGrid(tableP1, gridPane, 0);

        addColumnGap(gridPane, 10, 50);

        Text player1 = new Text();
        player1.setText(controller.getPlayer1().getName());
        player1.setFont(new Font(25));
        GridPane.setConstraints(player1, 11, 1);
        gridPane.getChildren().add(player1);

        addColumnGap(gridPane, 12, 35);

        drawVLine(gridPane, Color.BLACK, 13, 10, 35);

        addColumnGap(gridPane, 14, 35);

        setButtonsToShoot(tableP1, tableP2, controller.getPlayer1(), controller.getPlayer2(), 1);

        /*-------------------Player2-----------------------*/
        createGrid(tableP2, gridPane, 15);

        addColumnGap(gridPane, 25, 50);

        Text player2 = new Text();
        player2.setText(controller.getPlayer2().getName());
        player2.setFont(new Font(25));
        GridPane.setConstraints(player2, 26, 1);
        gridPane.getChildren().add(player2);

        addColumnGap(gridPane, 27, 10);
        setButtonsToShoot(tableP2, tableP1, controller.getPlayer2(), controller.getPlayer1(), 2);

    }

    private static void add(GridPane grid, Button add, int column, int line,
            int sizeX, int sizeY) {
        add.setPrefSize(sizeX, sizeY);
        GridPane.setConstraints(add, column, line);
        grid.getChildren().add(add);
    }

    private static void addColumnGap(GridPane grid, int column, int size) {
        Rectangle gap = new Rectangle(size, 1);
        gap.setFill(Color.TRANSPARENT);
        grid.addColumn(column, gap);
    }

    private static void drawVLine(GridPane grid, Color color, int column,
            int nLines, int lineHeight) {
        for (int i = 0; i < nLines; i++) {
            Rectangle line = new Rectangle(1, lineHeight);
            line.setFill(color);
            grid.addColumn(column, line);
        }
    }

    private static void createGrid(Button[][] table, GridPane grid, int start) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = new Button(j + "," + i);
                add(grid, table[i][j], (i + 1) + start, j, 35, 35);
            }
        }

    }

    private static void setButtonsToShoot(Button[][] gridToShoot, Button[][] gridShooting, Player playerShooting,
            Player playerToShoot, int numberPlayerShooting) {
        for (int i = 0; i < gridToShoot.length; i++) {
            for (int j = 0; j < gridToShoot[0].length; j++) {
                final Button button = gridToShoot[i][j];
                final int x = j;
                final int y = i;
                gridToShoot[i][j].setOnAction((ActionEvent e) -> {
                    gridToShoot[y][x].setDisable(true);
                    gridToShoot[y][x].setStyle("-fx-base: #0000ff");
                    if (playerToShoot.shot(x, y)) {
                        gridToShoot[y][x].setStyle("-fx-base: #ff0000");
                        if (playerToShoot.isDestroyed(x, y)) {
                            Coordinates[] coords = playerToShoot.getShipParts(x, y);
                            for (int k = 0; k < coords.length; k++) {
                                gridToShoot[coords[k].getY()][coords[k].getX()].setStyle("-fx-base: #000000");
                            }
                        }
                        if (!playerToShoot.isAlive()) {
                            primaryStage.close();
                            AlertBoxes.advice("Fim de jogo!\n", playerShooting.getName() + " Venceu!");
                        }
                        if(playerShooting instanceof PlayerIA) {
                            controller.getPlayer2().fire();
                        }
                    } else {
                        if (numberPlayerShooting == 1) {
                            updateGameState(controller, gridToShoot, gridShooting,
                                    playerShooting, playerToShoot);
                        } else {
                            updateGameState(controller, gridShooting, gridToShoot,
                                    playerToShoot, playerShooting);
                        }
                    }
                });
            }
        }

    }

    private static void updateGameState(Controller controller, Button[][] gridP1,
            Button[][] gridP2, Player player1, Player player2) {
        controller.increaseGameState();
        if (controller.getGameState() % 2 != 0) {
            blockGrid(gridP2);
            unBlockGrid(gridP1, player2.getPlayerShotsReceived());
        }

        if (controller.getGameState() % 2 == 0 && gameMode == 1) {
            blockGrid(gridP1);
            unBlockGrid(gridP2, player1.getPlayerShotsReceived());
        } else if(controller.getGameState() % 2 == 0 && gameMode == 2){
            blockGrid(gridP1);
            unBlockGrid(gridP2, player1.getPlayerShotsReceived());
            controller.getPlayer2().fire();
        }
    }

    private static void unBlockGrid(Button[][] grid, boolean[][] playerShots) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setDisable(playerShots[i][j]);
            }
        }
    }

    private static void blockGrid(Button[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setDisable(true);
            }
        }
    }

}