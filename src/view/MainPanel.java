/**
 * creator: Hadi Saghir
 * user-id: aj4810
 * enrollment: Systemutveckling
 */
package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JList<Object> topScore;
    private DefaultListModel<Object> model;
    private JButton newGame, exit;
    private JLabel title;
    private Controller controller;
    private MainFrame frame;

    private int width;
    private int height;
    public MainPanel(int width, int height, Controller controller, MainFrame frame) {
        super(null);
        this.frame = frame;
        this.controller = controller;
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        setUp();
    }

    private void setUp() {
        title = new JLabel("Leader Board");
        title.setLocation((width / 2) - 45, 0);
        title.setSize(90, 20);
        this.add(title);

        topScore = new JList<Object>();
        model = new DefaultListModel<Object>();
        topScore.setLocation(0,20);
        topScore.setSize(width, height - 120);
        Font font = new Font("Arial",Font.PLAIN,12);
        topScore.setFont(font);
        this.add(topScore);

        updateLeaderBoard();

        newGame = new JButton("New Game");
        newGame.setEnabled(true);
        newGame.setSize(width / 2 , 30);
        newGame.setLocation(0, height - 75);
        newGame.addActionListener(l -> newGame());
        this.add(newGame);

        exit = new JButton("Exit");
        exit.setEnabled(true);
        exit.setSize(width / 2, 30);
        exit.setLocation(width / 2, height - 75);
        exit.addActionListener(l -> exit());
        this.add(exit);

    }

    private void exit() {
        int exit = JOptionPane.showConfirmDialog(null, "u sure?");
        if(exit == 0){
            System.exit(0);
        }

    }

    public void newGame(){
        controller.newGame();
    }

    public void updateLeaderBoard() {
        model.removeAllElements();
        Object[] o = controller.getTopscore();
        for(int i = 0; i < o.length; i++){
            model.addElement(o[i]);
        }
        topScore.setModel(model);
    }
}
