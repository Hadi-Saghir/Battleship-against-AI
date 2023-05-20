/**
 * creator: Hadi Saghir
 * user-id: aj4810
 * enrollment: Systemutveckling
 */
package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LPanel extends JPanel {
    private JButton[][] player = new JButton[10][10];
    private Controller controller;
    private BoardFrame frame;
    private int numOfShips = 0;
    private int width;
    private int height;

    public LPanel(int width, int height, Controller controller, BoardFrame frame) {
        super(new GridLayout(10,10));
        this.frame = frame;
        this.controller = controller;
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setLocation(0,30);
        setupPnl();
    }

    private void setupPnl() {
        add100Buttons();
    }

    public void add100Buttons(){
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int y = 0; y < 10; y++) {
                    for (int x = 0; x < 10; x++) {
                        if (e.getSource() == player[y][x]) {
                            String result = frame.positionShip(x, y);
                            if(result == "Ship in position"){
                                int size = controller.getShipSize();
                                numOfShips++;
                                if (frame.directionHorizontal()) {
                                    for (int xx = x; xx < x + size; xx++) {
                                        player[y][xx].setBackground(Color.BLACK);
                                        player[y][xx].setOpaque(true);
                                    }
                                } else if (frame.directionVertical()) {
                                    for (int yy = y; yy < y + size; yy++) {
                                        player[yy][x].setBackground(Color.BLACK);
                                        player[yy][x].setOpaque(true);
                                    }
                                }
                                if(numOfShips == controller.getMaxShips()){
                                    enterPhaseBattle();
                                }
                            } else{
                                JOptionPane.showMessageDialog(null, "Error");
                            }
                            break;
                        }

                    }
                }
            }

        };
        for(int y=0;y<10;y++)
        {
            for(int x=0;x<10;x++)
            {
                player[y][x] = new JButton();
                player[y][x].setEnabled(true);
                player[y][x].addActionListener(listener);
                add(player[y][x]);
            }

        }
    }

    public void enterPhaseBattle() {
        disable102Buttons();
        pnl().setLblPhase("The Battle Rages");
        pnl().showTrackers();
        rPnl().enableButtons();
    }

    public void disable102Buttons(){
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                player[i][j].setEnabled(false);

            }

        }
        pnl().disable2Buttons();
    }

    public BoardPanel pnl(){ return frame.getPnl();}
    public RPanel rPnl(){
        return frame.getPnl().getRPnl();
    }

    public void fireShotComputer() {
        Font font = new Font("Arial", Font.BOLD, 20);
        String result = controller.fireShotComputer();
        int x = controller.getAX();
        int y = controller.getAY();
        if(result == "hit" || result == "sink") {
            player[y][x].setBackground(Color.RED);
        } else if (result == "miss") {
            player[y][x].setFont(font);
            player[y][x].setText("X");
        } else if (result == "win"){
            JOptionPane.showMessageDialog(null, "Computer Wins");
            controller.quit();
        }
    }
}