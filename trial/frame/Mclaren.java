package frame;

import car.Mp3;
import dfa.Mp4;
import dfa.DFA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Mclaren extends JFrame implements KeyListener {
    DFA statesETC = new DFA();
    Mp3 voiceOver = new Mp3();
    Mp4 video = new Mp4();

    public static Canvas cVideo;
    public JPanel videoPanel;

    public static void main(String[] args) {
        System.out.println("Main in Mclaren");
        new Mclaren();
        System.out.println("Car created in main");
    }

    public Mclaren() {
        System.out.println("Default constructor for Maclaren");
        initialiseComponents();
        //carCreated();
    }

    public void initialiseComponents() {
        //Set main frame size
        this.setLayout(new BorderLayout());
        this.setSize(1200,700);
        this.setLocation(100,100);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);

        cVideo= new Canvas();

        videoPanel = new JPanel();
        videoPanel.setLayout(new BorderLayout());
        videoPanel.setPreferredSize(new Dimension(1000,650));

        videoPanel.add(cVideo); //,BorderLayout.CENTER
        this.add(videoPanel); //,BorderLayout.CENTER

        videoPanel.setBackground(Color.CYAN);
        videoPanel.setVisible(true);
    }

    public void carCreated() {
        //Function to call the initial car
        videoPanel.setVisible(true);
        video.playVideo("1");
        videoPanel.setVisible(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = KeyEvent.getKeyText(e.getKeyCode());

    /*    if ("0".equals(key)) {
            //To do when the destination is reached
            dfa.currentStateLabel = DFA.States.OFFPOSITION;
            videoPanel.setVisible(true);
            video.playVideo("2");
            try {
                voiceOver.playMp3("9");
                voiceOver.playMp3("1");
            } catch (JavaLayerException ex) {
                ex.printStackTrace();
            }
            videoPanel.setVisible(false); */
            System.exit(0);
       // }
    }

    @Override
    public void keyReleased(KeyEvent e) {
            System.out.println("Key Released= " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
