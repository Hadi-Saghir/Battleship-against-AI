/**
 * creator: Hadi Saghir
 * user-id: aj4810
 * enrollment: Systemutveckling
 */
package view;

import controller.Controller;

import javax.swing.*;

public class MainFrame extends JFrame {
    private Controller controller;
    private MainPanel pnl;

    private int width = 400;
    private int height = 300;


    public MainFrame(Controller controller){
        super("Battleships");
        this.controller = controller;
        this.setResizable(false);
        this.setSize(width, height);
        this.setLocation(500, 300);
        this.pnl = new MainPanel(width, height, controller, this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(pnl);
        this.setVisible(true);
    }

    public void setInvisible(){
        this.setVisible(false);
    }
    public void setVisible(){
        this.setVisible(true);
    }

    public MainPanel getPnl() {
        return pnl;
    }
}
