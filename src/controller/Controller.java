/**
 * creator: Hadi Saghir
 * user-id: aj4810
 * enrollment: Systemutveckling
 */
package controller;

import model.Board;
import model.LeaderBoard;
import model.Topscore;
import view.*;

public class Controller {
    private MainFrame view;
    private BoardFrame gView;

    private Board board;
    private LeaderBoard lBoard;

    //contructor
    public Controller(){
        lBoard = new LeaderBoard();
        view = new MainFrame(this);
    }

    /**
     * creates a new game, called upon by press of a button
     * it achieves it by initializing a new board and board frame
     * show to board interface and hides main frame
     */
    public void newGame(){
        board = new Board();
        gView = new BoardFrame(this);
        view.setInvisible();
        gView.setVisible();
    }
    /**
     * returns to main frame and updates leader-board
     */
    public void quit() {
        gView.setInvisible();
        view.setVisible();
        view.getPnl().updateLeaderBoard();
    }

    //below are caller methods with eponymous names and descriptions above the callee method
    public String positionShip(String direction, int x, int y) {
        return board.positionShip(direction, x, y);
    }
    public int getShipSize(){
        return board.getShipSize();
    }
    public String fireShotPlayer(int x, int y) {
        return board.fireShotPlayer(x, y);
    }
    public String fireShotComputer() {
        return board.fireShotComputer();
    }
    public int getAX() {
        return board.getAX();
    }
    public int getAY() {
        return board.getAY();
    }
    public Object[] getTopscore(){
        return lBoard.getTopscores();
    }
    public int checkIfTopscore(int shots) {
        return lBoard.checkIfTopscore(shots);
    }
    public void addTopscore(String name, int shots, int position) {
        lBoard.addTopscore(name,shots,position);
    }
    public int getMaxShips() {
        return board.getMaxShips();
    }
    public int getLeaderBoardLength(){
        return lBoard.getLeaderBoardLength();
    }
}
