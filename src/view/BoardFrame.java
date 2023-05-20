/**
 * creator: Hadi Saghir
 * user-id: aj4810
 * enrollment: Systemutveckling
 */

package view;

import controller.Controller;

import javax.swing.*;

public class BoardFrame extends JFrame {
    private Controller controller;
    private BoardPanel pnl;

    private int width = 900;
    private int height = 600;


    public BoardFrame(Controller controller){
        super("Battleships");
        this.controller = controller;
        this.setResizable(false);
        this.setSize(width, height);
        this.setLocation(100, 50);
        this.pnl = new BoardPanel(width, height, controller, this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(pnl);
        this.setVisible(false);
    }

    public String positionShip(int x, int y){
        return controller.positionShip(pnl.getDirection(), x, y);
    }
    public boolean directionHorizontal(){
        return pnl.isHorizontal();

    }
    public boolean directionVertical(){
        return pnl.isVertical();
    }

    public void setInvisible(){
        this.setVisible(false);
    }
    public void setVisible(){
        this.setVisible(true);
    }

    public BoardPanel getPnl() {
        return pnl;
    }
}

