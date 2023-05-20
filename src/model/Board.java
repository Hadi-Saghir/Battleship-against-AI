/**
 * creator: Hadi Saghir
 * user-id: aj4810
 * enrollment: Systemutveckling
 */
package model;

import model.ship.*;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private Ship[][] playerShips;
    private Ship[][] computerShips;
    private Ship ship;
    private int numOfPlayerShips;
    private int numOfComputerShips;
    private final int maxShips = 5;

    Random rand = new Random();
    private int[][] ai;
    private String computerResult;
    private boolean attackingShip;
    private boolean knowDirection;
    private boolean shipHorizontal;
    private boolean wrongWay;
    private int ax;
    private int ay;
    private int xCord;
    private int yCord;

    private int numVertical;
    private int numHorizontal;
    private final int maxVertical = 4;
    private final int maxHorizontal = 4;

    private Topscore[] topscores;

    public Board() {
        this.playerShips = new Ship[10][10];
        this.computerShips = new Ship[10][10];

        this.ai = new int[10][10];
        this.computerResult = "sink";

        this.numOfPlayerShips = 0;
        this.numOfComputerShips = 0;
        this.numVertical = 0;
        this.numHorizontal = 0;
        populateComputerShips();

    }

    /**
     * random population the computers field with ships
     * x and y cords and direction are randomly generated and tested by called checkPositon.
     */
    private void populateComputerShips() {
        Random rand = new Random();
        for (int i = numOfComputerShips; i < maxShips; i++) {
            if (numOfComputerShips == 0) {
                this.ship = new Patrol();
            }
            if (numOfComputerShips == 1) {
                this.ship = new Submarine();
            }
            if (numOfComputerShips == 2) {
                this.ship = new Destroyer();
            }
            if (numOfComputerShips == 3) {
                this.ship = new Battleship();
            }
            if (numOfComputerShips == 4) {
                this.ship = new Carrier();
            }
            int size = ship.getSize();
            int directionNum = rand.nextInt(2);
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            String direction;
            if (directionNum == 0) {
                direction = "horizontal";
            } else {
                direction = "vertical";
            }
            while (!checkPositioning(computerShips, direction, size, x, y)) {
                System.out.println(x + y);
                x = rand.nextInt(10);
                y = rand.nextInt(10);
            }
            setPositioning(computerShips, direction, size, x, y);
            numOfComputerShips++;
        }
        ship = null;
        numVertical = 0;
        numHorizontal = 0;
        for (int y = 0; y < computerShips.length; y++) {
            for (int x = 0; x < computerShips[0].length; x++) {
                System.out.print(computerShips[y][x] + " ");
            }
            System.out.println("");
        }

    }
    /**
     * position the ship by adding objects into a string
     * @param x and @param y are inputted from user in player
     * @param direction determines the axis to move along while position the ship in the array.
     */
    public String positionShip(String direction, int x, int y) {
        String result = "Ship failed to position";
        if (numOfPlayerShips == 0) {
            this.ship = new Patrol();
        }
        if (numOfPlayerShips == 1) {
            this.ship = new Submarine();
        }
        if (numOfPlayerShips == 2) {
            this.ship = new Destroyer();
        }
        if (numOfPlayerShips == 3) {
            this.ship = new Battleship();
        }
        if (numOfPlayerShips == 4) {
            this.ship = new Carrier();
        }
        int size = ship.getSize();
        if (checkPositioning(playerShips, direction, size, x, y) && (numOfPlayerShips < maxShips)) {
            setPositioning(playerShips, direction, size, x, y);
            result = "Ship in position";
            numOfPlayerShips++;
        }

        return result;
    }

    // below methods are called upon to check that the position
    /**
     * check position takes in all the field, directiona and cords to check the position is empty
     * @return boolean whether it is empty or nor
     */
    public boolean checkPositioning(Ship[][] ships, String direction, int size, int x, int y) {
        boolean allowed = false;
        if (direction == "horizontal") {

            for (int i = x; i < x + size; i++) {
                if (x + size > ships.length | ships[y][i] != null | maxHorizontal <= numHorizontal) {
                    allowed = false;
                    break;
                }
                allowed = true;

            }
        } else if (direction == "vertical") {
            for (int i = y; i < y + size; i++) {
                if (y + size > ships[0].length | ships[i][x] != null | maxVertical <= numVertical) {
                    allowed = false;
                    break;
                }
                allowed = true;
            }
        }
        return allowed;
    }
    /**
     * after position being check, it changes said position from null to object of the ship
     */
    public void setPositioning(Ship[][] ships, String direction, int size, int x, int y) {
        if (direction == "horizontal") {
            numHorizontal++;
            for (int i = x; i < x + size; i++) {
                ships[y][i] = ship;
            }
        } else if (direction == "vertical") {
            numVertical++;
            for (int i = y; i < y + size; i++) {
                ships[i][x] = ship;
            }

        }
    }
    //vessel getter used above
    public int getShipSize() {
        return ship.getSize();
    }

    /**
     * below methods are vessels, acting to distinguish who the shooter is
     * @param x and @param y are inputted from user in player
     * x and y are generated for the computer, AI used to process the generation.
     * @return result of fireShot()
     */
    public String fireShotPlayer(int x, int y) {
        return fireShot(computerShips, x, y);
    }
    public String fireShotComputer() {
        boolean shotYet = false;
        if (computerResult == "sink") {
            attackingShip = false;
            shipHorizontal = false;
            knowDirection = false;
            wrongWay = false;
            do {
                ax = rand.nextInt(10);
                ay = rand.nextInt(10);
            } while (ai[ay][ax] != 0);
            computerResult = fireShot(playerShips, ax, ay);
            xCord = ax;
            yCord = ay;
        } else if(attackingShip){
            if(!knowDirection){
                for(int i = ax - 1; i <= ax + 1; i+=2) {
                    if (i > ai.length - 1 || i < 0) {
                        continue;
                    }
                    if (ai[ay][i] == 0) {
                        computerResult = fireShot(playerShips, i, ay);
                        xCord = i;
                        yCord = ay;
                        shotYet = true;
                        if (computerResult == "hit" || computerResult == "sink") {
                            ai[ay][i] = 1;
                            knowDirection = true;
                            shipHorizontal = true;
                        } else if (computerResult == "miss") {
                            ai[ay][i] = -1;
                            wrongWay = true;
                        }
                        break;
                    }
                }
                if (!shotYet){
                    wrongWay = false;
                    for (int j = ay - 1; j <= ay + 1; j += 2) {
                        if (j > ai.length - 1 || j < 0) {
                            continue;
                        }
                        if (ai[j][ax] == 0) {
                            computerResult = fireShot(playerShips, ax, j);
                            xCord = ax;
                            yCord = j;
                            if (computerResult == "hit" || computerResult == "sink") {
                                ai[j][ax] = 1;
                                knowDirection = true;
                                shipHorizontal = false;
                            } else if (computerResult == "miss") {
                                ai[j][ax] = -1;
                                wrongWay = true;
                            }
                            break;
                        }
                    }
                }
            } else if(knowDirection){
                if(shipHorizontal){
                    if(!wrongWay) {
                        for (int i = ax - 1; i >= -1; i--) {
                            if (i == -1) {
                                wrongWay = true;
                                break;
                            }
                            if (ai[ay][i] == 0) {
                                computerResult = fireShot(playerShips, i, ay);
                                xCord = i;
                                yCord = ay;
                                shotYet = true;
                                if (computerResult == "hit" || computerResult == "sink") {
                                    ai[ay][i] = 1;
                                } else if (computerResult == "miss") {
                                    ai[ay][i] = -1;
                                    wrongWay = true;
                                }
                                break;
                            } else if (ai[ay][i] == -1) {
                                wrongWay = true;
                                break;
                            }
                        }
                    }
                    if(wrongWay && !shotYet) {
                        for (int j = ax + 1; j < playerShips.length; j++) {
                            if (j > playerShips.length - 1) {
                                wrongWay = false;
                                knowDirection = false;
                            }
                            if (ai[ay][j] == 0) {
                                computerResult = fireShot(playerShips, j, ay);
                                xCord = j;
                                yCord = ay;
                                shotYet = true;
                                if (computerResult == "hit" || computerResult == "sink") {
                                    ai[ay][j] = 1;
                                } else if (computerResult == "miss") {
                                    ai[ay][j] = -1;
                                    knowDirection = false;
                                    wrongWay = false;
                                }
                                break;
                            } else if (ai[ay][j] == -1) {
                                shipHorizontal = true;
                                wrongWay = false;
                            }
                        }
                    }
                }
                if(!shipHorizontal){
                    if(!wrongWay && !shotYet) {
                        for (int i = ay - 1; i >= 0; i--) {
                            if (i <= 0) {
                                wrongWay = true;
                            }
                            if (ai[i][ax] == 0) {
                                computerResult = fireShot(playerShips, ax, i);
                                xCord = ax;
                                yCord = i;
                                shotYet = true;
                                if (computerResult == "hit" || computerResult == "sink") {
                                    ai[i][ax] = 1;
                                } else if (computerResult == "miss") {
                                    ai[i][ax] = -1;
                                    wrongWay = true;
                                }
                                break;
                            } else if (ai[i][ax] == -1) {
                                wrongWay = true;
                                break;
                            }
                        }
                    }
                    if (wrongWay && !shotYet) {
                        for (int j = ay + 1; j < playerShips.length; j++) {
                            if (j > playerShips.length - 1) {
                                knowDirection = false;
                            }
                            if (ai[j][ax] == 0) {
                                computerResult = fireShot(playerShips, ax, j);
                                xCord = ax;
                                yCord = j;
                                if (computerResult == "hit" || computerResult == "sink") {
                                    ai[j][ax] = 1;
                                } else if (computerResult == "miss") {
                                    ai[j][ax] = -1;
                                    wrongWay = false;
                                    knowDirection = false;
                                    shotYet = false;
                                }
                                break;
                            }
                        }
                    }
                }
            }

        } else if(computerResult == "miss" || !shotYet){
            ai[ay][ax] = -1;
            while (ai[ay][ax] != 0) {
                ax = rand.nextInt(10);
                ay = rand.nextInt(10);
            }
            computerResult = fireShot(playerShips, ax, ay);
            xCord = ax;
            yCord = ay;
        }
        if (computerResult == "hit"){attackingShip = true; ai[ay][ax] = 1;}
        if (computerResult == "sink"){attackingShip = false; ai[ay][ax] = 1;}
        System.out.println("*****************");
        for(int i =  0; i < ai.length; i++) {
            System.out.print(Arrays.toString(ai[i]));
            System.out.println();
        }
        System.out.println("****************");
        return computerResult;
    }
    /**
     * above methods call upon fireshot, checking if a ship is positioned at inputted x and y cords
     * @param ships is a two-dim array of one of the playing fields
     *     the field the fire is shot at hinges on the caller, being one of the above methods
     * @return result of the fired shot "hit","miss"....
     */
    public String fireShot(Ship[][] ships, int x, int y){
        String result = "miss";
        if(ships[y][x] != null){
            result = ships[y][x].toString() + " has sunk";
            if(ships[y][x] == playerShips[y][x]) {result = "sink";}
            for(int i = y - ships[y][x].getSize() + 1; i <= y + ships[y][x].getSize() - 1; i++){
                if((i < 0)  || (i > 9) || (i == y)){
                    continue;
                }
                if (ships[i][x] != null){
                    if (ships[y][x].getSize() == ships[i][x].getSize()) {
                        result = "hit on "+ ships[y][x].toString() + " (size: " + ships[y][x].getSize() + ")";
                        if(ships[y][x]== playerShips[y][x]) {result = "hit";}
                        break;
                    }
                }
            }
            for(int j = x - ships[y][x].getSize(); j <= x + ships[y][x].getSize(); j++){
                if( (j < 0) || (9 < j) || (j == x)){
                    continue;
                }
                if (ships[y][j] != null) {
                    if(ships[y][x].getSize() == ships[y][j].getSize()) {
                        result = "hit on " + ships[y][x].toString() + " (size: " + ships[y][x].getSize() + ")";
                        if(ships[y][x] == playerShips[y][x]) {result = "hit";}
                    }
                }
            }
            ships[y][x] = null;
        }
        if(!fleetStands(ships)){
            result = "win";
        }
        return result;
    }
    /**
     * above methods call upon this method to check the array of the field
     * @param ships is a two-dim array of a certain the playing field
     * @return result of the stands, in turn telling us if it is a "sink" or "win"
     */
    public boolean fleetStands(Ship[][] ships){
        boolean stands = false;
        for(int y=0;y<10;y++)
        {
            for(int x=0;x<10;x++)
            {
                if(ships[y][x] != null){
                    stands = true;
                    break;
                }
            }

        }
        return stands;
    }

    //getters used by view to know which button to change
    public int getAX() {
        return xCord;
    }
    public int getAY() {
        return yCord;
    }
    public int getMaxShips() {
        return maxShips;
    }
}
