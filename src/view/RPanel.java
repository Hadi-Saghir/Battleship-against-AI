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

public class RPanel extends JPanel {
    private JButton[][] computer = new JButton[10][10];
    private Controller controller;
    private BoardFrame frame;

    private int shots;
    private int hits;

    private int width;
    private int height;
    public RPanel(int width, int height, Controller controller, BoardFrame frame) {
        super(new GridLayout(10,10));
        this.frame = frame;
        this.controller = controller;
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setLocation(width / 2 + 300,30);
        setupPnl();
    }

    private void setupPnl() {
        add100Buttons(computer);
        shots = 0;
        hits = 0;
    }
    private String fireShotPlayer(int x, int y){
        shots++;
        pnl().setlblShots("Shots: " + shots);

        String result = controller.fireShotPlayer(x,y);
        if (result == "miss"){
            computer[y][x].setEnabled(false);
            computer[y][x].setBackground(Color.RED);
            computer[y][x].setOpaque(true);
        }
        else if(result == "win"){
            JOptionPane.showMessageDialog(null, "Player Wins!!");
            int position = controller.checkIfTopscore(shots);
            if(position < controller.getLeaderBoardLength()){
                String name = JOptionPane.showInputDialog("You have achieved a top-score !!! \n Enter username:");
                controller.addTopscore(name,shots,position);
            }
            controller.quit();
        } else{
            computer[y][x].setEnabled(false);
            computer[y][x].setBackground(Color.GREEN);
            computer[y][x].setOpaque(true);
            hits++;
            pnl().setlblHits("Hits: " + hits);
            JOptionPane.showMessageDialog(null, result);
        }
        return result;
    }

    public void add100Buttons(JButton[][] button){

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int y = 0; y < 10; y++) {
                    for (int x = 0; x < 10; x++) {
                        if (e.getSource() == button[y][x]) {
                            String result = fireShotPlayer(x,y);
                            if(result != "win") {
                                lPnl().fireShotComputer();
                            }
                        }

                    }
                }
            }

        };
        for(int y=0;y<10;y++)
        {
            for(int x=0;x<10;x++)
            {
                button[y][x] = new JButton();
                button[y][x].setEnabled(false);
                button[y][x].addActionListener(listener);
                add(button[y][x]);
            }

        }
    }
    public void enableButtons() {
        for(int y=0;y<10;y++)
        {
            for(int x=0;x<10;x++)
            {
                computer[y][x].setEnabled(true);
            }

        }
    }
    public BoardPanel pnl(){ return frame.getPnl();}
    public LPanel lPnl(){
        return frame.getPnl().getLPnl();
    }

}