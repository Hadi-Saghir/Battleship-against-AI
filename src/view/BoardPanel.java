/**
 * creator: Hadi Saghir
 * user-id: aj4810
 * enrollment: Systemutveckling
 */
package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private JLabel lblPlayer, lblComputer, lblPhase, lblHits, lblShots;
    private JRadioButton horizontal, vertical;
    private JButton quit;
    private LPanel lPnl;
    private RPanel rPnl;

    private Controller controller;
    private BoardFrame frame;

    private int width;
    private int height;

    public BoardPanel(int width, int height, Controller controller, BoardFrame frame) {
        super(null);
        this.frame = frame;
        this.width = width;
        this.height = height;
        this.controller = controller;
        this.setSize(width, height);
        lPnl = new LPanel(width / 3 + 100, height - 150, this.controller, this.frame);
        add(lPnl);
        rPnl = new RPanel(width / 3 + 100, height - 150, this.controller, this.frame);
        add(rPnl);
        setupPnl();
    }

    private void setupPnl() {
        Font font = new Font("Arial", Font.BOLD, 20);

        lblPlayer = new JLabel("Player");
        lblPlayer.setLocation((width / 3 - 150), 0);
        lblPlayer.setSize(160, 30);
        lblPlayer.setFont(font);
        this.add(lblPlayer);

        lblPhase = new JLabel("Position Your Ships");
        lblPhase.setLocation((width / 2 - 100), 0);
        lblPhase.setSize(200, 30);
        lblPhase.setFont(font);
        this.add(lblPhase);

        lblComputer = new JLabel("Computer");
        lblComputer.setLocation((width * 2 / 3 + 50), 0);
        lblComputer.setSize(160, 30);
        lblComputer.setFont(font);
        this.add(lblComputer);

        lblShots = new JLabel("Shots: 0");
        lblShots.setLocation(30,height - 100);
        lblShots.setSize(width / 4, 30);
        lblShots.setFont(new Font("Arial", Font.BOLD, 12));
        lblShots.setVisible(false);
        this.add(lblShots);

        lblHits = new JLabel("hits: 0");
        lblHits.setLocation((width / 4), height - 100);
        lblHits.setSize(width / 4, 30);
        lblHits.setFont(new Font("Arial", Font.BOLD, 12));
        lblHits.setVisible(false);
        this.add(lblHits);

        font = new Font("Arial", Font.BOLD, 12);

        horizontal = new JRadioButton("horizontal");
        horizontal.setLocation(20, height - 100);
        horizontal.setSize(100, 30);
        horizontal.setFont(font);
        horizontal.setSelected(true);
        this.add(horizontal);

        vertical = new JRadioButton("vertical");
        vertical.setLocation(150, height - 100);
        vertical.setSize(100, 30);
        vertical.setFont(font);
        vertical.setSelected(false);
        this.add(vertical);

        ButtonGroup direction = new ButtonGroup();
        direction.add(horizontal);
        direction.add(vertical);

        quit = new JButton("Quit");
        quit.setEnabled(true);
        quit.setSize(100, 30);
        quit.setLocation(width - 150, height - 100);
        quit.addActionListener(l -> quit());
        this.add(quit);
    }

    public String getDirection() {
        String direction;
        if (horizontal.isSelected()) {
            direction = "horizontal";
        } else {
            direction = "vertical";
        }
        return direction;
    }
    public boolean isHorizontal() {
        return horizontal.isSelected();
    }
    public boolean isVertical() {
        return vertical.isSelected();
    }

    public LPanel getLPnl() {
        return lPnl;
    }
    public RPanel getRPnl() {
        return rPnl;
    }

    public void setLblPhase(String txt) {
        this.lblPhase.setText(txt);
    }
    public void setlblShots(String txt) {
        lblShots.setText(txt);
    }
    public void setlblHits(String txt) {
        lblHits.setText(txt);
    }

    public void disable2Buttons() {
        vertical.setEnabled(false);
        horizontal.setEnabled(false);
        vertical.setVisible(false);
        horizontal.setVisible(false);
    }
    public void showTrackers() {
        lblShots.setVisible(true);
        lblHits.setVisible(true);
    }

    public void quit() {
        controller.quit();
    }
}
