package trial2Files;

//import dfa.DFA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MclarenTest extends JFrame implements KeyListener{
    public static Canvas cVideo;
    public JPanel videoFrame;

    //public DFA dfa = new DFA();

    public static void main(String[] args) {
        new MclarenTest();
    }

    public MclarenTest() {
        //Set main frame size
        this.setLayout(new BorderLayout());
        this.setSize(1000,700);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);

        cVideo= new Canvas();

        videoFrame = new JPanel();
        videoFrame.add(cVideo);
        this.add(videoFrame);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = KeyEvent.getKeyText(e.getKeyCode());

      //  dfa.stateChanged(dfa.currentStateLabel,key);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released= " + KeyEvent.getKeyText(e.getKeyCode()));

    }
}
