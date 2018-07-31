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

abstract class ScreenPlace {

    private static Controller controller;
    private static Stage primaryStage;
    private static int gameMode;

    static void start(Stage primaryStage, int gameMode) { // 1 = PVP, 2 = PVE
        ScreenPlace.primaryStage = primaryStage;
        ScreenPlace.gameMode = gameMode;

        controller = new Controller(gameMode == 1);
        Button[][] tableP1 = new Button[10][10];
        Button[][] tableP2 = new Button[10][10];
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
        Button orientationP1 = new Button(controller.getPlayer1().getTextOrientation());
        Button shipP1 = new Button(controller.getPlayer2().getTextShipAdding());
        Button orientationP2 = new Button(controller.getPlayer2().getTextOrientation());
        Button shipP2 = new Button(controller.getPlayer2().getTextShipAdding());
        /*-------------------Player1-----------------------*/
        addColumnGap(gridPane, 0, 10);

        createGrid(tableP1, gridPane, 0);

        addColumnGap(gridPane, 10, 50);

        add(gridPane, orientationP1, 11, 2, 100, 35);
        add(gridPane, shipP1, 11, 3, 100, 35);

        Text player1 = new Text();
        player1.setText(controller.getPlayer1().getName());
        player1.setFont(new Font(25));
        GridPane.setConstraints(player1, 11, 1);
        gridPane.getChildren().add(player1);

        addColumnGap(gridPane, 12, 35);

        drawVLine(gridPane, Color.BLACK, 13, 10, 35);

        addColumnGap(gridPane, 14, 35);

        setButtonsToPlace(tableP1, tableP2, shipP1, shipP2, orientationP1, orientationP2,
                controller.getPlayer1());

        /*-------------------Player2-----------------------*/
        createGrid(tableP2, gridPane, 15);

        addColumnGap(gridPane, 25, 50);

        add(gridPane, orientationP2, 26, 2, 100, 35);
        add(gridPane, shipP2, 26, 3, 100, 35);

        Text player2 = new Text();
        player2.setText(controller.getPlayer2().getName());
        player2.setFont(new Font(25));
        GridPane.setConstraints(player2, 26, 1);
        gridPane.getChildren().add(player2);

        addColumnGap(gridPane, 27, 10);
        setButtonsToPlace(tableP2, tableP1, shipP2, shipP1, orientationP2,
                orientationP1, controller.getPlayer2());

        blockGrid(gameMode, tableP2, shipP2, orientationP2, false);

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

    private static void setButtonsToPlace(Button[][] gridPlacing, Button[][] gridToPlaceLater, Button shipPlacing,
            Button shipToPlaceLater, Button orientationPlacing, Button orientationToPlaceLater, Player player) {
        for (int i = 0; i < gridPlacing.length; i++) {
            for (int j = 0; j < gridPlacing[0].length; j++) {
                final Button button = gridPlacing[i][j];
                final int x = j;
                final int y = i;
                gridPlacing[i][j].setOnAction((ActionEvent e) -> {
                    addShip(player, x, y, player.getOrientationAdding(),
                            player.getTextShipAdding(), gridPlacing);
                    shipPlacing.setText(player.getTextShipAdding());
                    if (shipPlacing.getText().equals("")) {
                        shipPlacing.setDisable(true);
                        orientationPlacing.setDisable(true);
                        if (controller.getGameState() == 0 || controller.getGameState() == 2) {
                            updateGameState(controller, controller.getGameState() + 1,
                                    gridPlacing, gridToPlaceLater, shipPlacing,
                                    shipToPlaceLater, orientationPlacing, orientationToPlaceLater);
                        } else {
                            updateGameState(controller, controller.getGameState() + 1,
                                    gridToPlaceLater, gridPlacing, shipToPlaceLater,
                                    shipPlacing, orientationToPlaceLater, orientationPlacing);
                        }
                    }

                });
            }
        }
        orientationPlacing.setOnAction((ActionEvent e) -> {
            player.changeOrientationAdding();
            orientationPlacing.setText(player.getTextOrientation());
        });

        shipPlacing.setOnAction((ActionEvent e) -> {
            /* verificar */
            player.increaseShipAdding();
            shipPlacing.setText(player.getTextShipAdding());
            if (shipPlacing.getText().equals("")) {
                shipPlacing.setDisable(true);
                orientationPlacing.setDisable(true);
            }
        });

    }

    private static void fillShipSpace(Button[][] grid, int x, int y, boolean orientation, int size) {
        if (orientation) {
            for (int i = y; i < (y + size); i++) {
                grid[i][x].setDisable(true);
                grid[i][x].setStyle("-fx-base: #006600");
            }
        } else {
            for (int i = x; i < (x + size); i++) {
                grid[y][i].setDisable(true);
                grid[y][i].setStyle("-fx-base: #006600");
            }
        }
    }

    private static void addShip(Player player, int x, int y,
            boolean orientation, String textShipAdding, Button[][] grid) {

        boolean isOk;
        if (textShipAdding.equals("Submarine (1)")) {
            isOk = player.addShip(new Submarine(x, y, orientation));
            if (isOk) {
                fillShipSpace(grid, x, y, orientation, 1);
            }
        }

        if (textShipAdding.equals("Destroyer (2)")) {
            isOk = player.addShip(new Destroyer(x, y, orientation));
            if (isOk) {
                fillShipSpace(grid, x, y, orientation, 2);
            }
        }

        if (textShipAdding.equals("Cruiser (3)")) {
            isOk = player.addShip(new Cruiser(x, y, orientation));
            if (isOk) {
                fillShipSpace(grid, x, y, orientation, 3);
            }
        }

        if (textShipAdding.equals("Battleship (4)")) {
            isOk = player.addShip(new Battleship(x, y, orientation));
            if (isOk) {
                fillShipSpace(grid, x, y, orientation, 4);
            }
        }

        if (textShipAdding.equals("Carrier (5)")) {
            isOk = player.addShip(new Carrier(x, y, orientation));
            if (isOk) {
                fillShipSpace(grid, x, y, orientation, 5);
            }
        }
    }

    private static void updateGameState(Controller controller, int newState,
            Button[][] gridP1, Button[][] gridP2, Button shipsP1, Button shipsP2,
            Button orientationP1, Button orientationP2) { // 0 = P1 posicionando navios, 1 = P2 posicionando navios
        // 2 = P1 jogando, 3 = P2 jogando
        controller.setGameState(newState);
        if (newState == 0) {
            blockGrid(2, gridP2, shipsP2, orientationP2, false);
            AlertBoxes.advice("Aviso", "Vez de P1 colocar seus navios.");
        }

        if (newState == 1) {
            blockGrid(1, gridP1, shipsP1, orientationP1, true);
            unBlockGrid(gridP2, shipsP2, orientationP2);
            AlertBoxes.advice("Aviso", "Vez de P2 colocar seus navios.");
        }

        if (newState == 2) {
            blockGrid(2, gridP2, shipsP2, orientationP2, true);
            AlertBoxes.advice("Aviso", "Agora comeca o jogo.");
            primaryStage.close();
            ScreenShot.start(primaryStage, ScreenPlace.gameMode, controller);

        }

        if (newState == 3) {
            blockGrid(1, gridP1, shipsP1, orientationP1, false);
        }
    }

    private static void unBlockGrid(Button[][] grid, Button ship, Button orientation) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setDisable(false);
            }
        }

        ship.setDisable(false);
        orientation.setDisable(false);
    }

    private static void blockGrid(int player, Button[][] grid, Button ships, Button orientation,
            boolean toPaint) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j].setDisable(true);
                if (toPaint) {
                    grid[i][j].setStyle("-fx-base: #006600");
                }
            }
        }

        ships.setDisable(true);
        orientation.setDisable(true);
        if (toPaint) {
            ships.setStyle("-fx-base: #006600");
            orientation.setStyle("-fx-base: #006600");
        }
    }

}
