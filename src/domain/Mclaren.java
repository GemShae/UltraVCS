package domain;

import car.Mp3;
import car.Mp4;
import dfa.Alphabet;
import dfa.FSM;
import dfa.States;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Mclaren extends JFrame{
    public static JFrame frame;
    public static JLabel currentTransitionLabel;
    public static ArrayList<String> transitionList;
    public static JLabel currentStateLabel;
    public static JLabel speedometerLabel;
    public static JLabel stateMarkerLabel;
    public static JLabel carImageLabel;
    public static JTextField currentTransitionTextField;
    public static JTextField currentStateTextField;
    public static JTextArea processStringTextArea;
    public static JScrollPane processStringScrollPane;
    public static JButton startButton;
    public static JButton seatBeltImageButton;
    public static JButton reverseButton;
    public static JButton driveButton;

    public FSM dfa = new FSM();
    public States currentState = States.OFFPOSITION;
    public Mp3 voiceOver = new Mp3();
    
    public Mclaren (String title) {
        initialiseComponents();
        frame.setTitle(title);
        addToFrame();
        registerActionListeners();
        registerKeyListeners();

        JOptionPane.showMessageDialog(null,"Car created","Car Status",
                JOptionPane.INFORMATION_MESSAGE);

        try {
            voiceOver.playMp3("1");
        }catch (JavaLayerException ex) {
            ex.printStackTrace();
        }

        //new Mp4("1");
    }

    public void initialiseComponents() {
         frame = new JFrame();
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
         frame.setResizable(false);
         frame.setLayout(null);
         frame.setSize(1000, 738);
         frame.setFocusable(true);

         currentTransitionLabel = new JLabel("Current Transition");
         currentTransitionLabel.setBounds(90,6,300,20);
         currentTransitionLabel.setHorizontalAlignment(SwingConstants.LEFT);
         currentTransitionLabel.setFont(new Font("Times New Roman", Font.ITALIC, 15));
         currentTransitionLabel.setForeground(Color.BLUE);
         
         transitionList = new ArrayList<>();

         currentTransitionTextField = new JTextField(" ");
         currentTransitionTextField.setBounds(78,30,150,20);
         currentTransitionTextField.setHorizontalAlignment(SwingConstants.LEFT);
         currentTransitionTextField.setEditable(false);
         currentTransitionTextField.setHorizontalAlignment(JTextField.CENTER);

         processStringTextArea = new JTextArea("Process String:");
         processStringTextArea.setBounds(670,40,300,120);
         processStringTextArea.setEditable(false);

         processStringScrollPane = new JScrollPane();
         processStringScrollPane.setBounds(670,40,300,120);
         processStringScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         processStringScrollPane.getViewport().setBackground(Color.white);

         currentStateLabel = new JLabel("Current State");
         currentStateLabel.setSize(260,40);
         currentStateLabel.setHorizontalAlignment(SwingConstants.LEFT);
         currentStateLabel.setBounds(120, 55, 300, 20);
         currentStateLabel.setFont(new Font("Times New Roman", Font.ITALIC, 17));
         currentStateLabel.setForeground(Color.BLUE);

         currentStateTextField = new JTextField("OFF");
         currentStateTextField.setBounds(90,75,150,20);
         currentStateTextField.setHorizontalAlignment(SwingConstants.LEFT);
         currentStateTextField.setEditable(false);
         currentStateTextField.setHorizontalAlignment(JTextField.CENTER);

         speedometerLabel = new JLabel("0");
         speedometerLabel.setSize(260,40);
         speedometerLabel.setHorizontalAlignment(SwingConstants.LEFT);
         speedometerLabel.setBounds(387,200,85,85);
         speedometerLabel.setFont(new Font("Times New Roman", Font.BOLD, 33));
         speedometerLabel.setForeground(Color.RED);

         stateMarkerLabel = new JLabel("*");
         stateMarkerLabel.setBounds(291,38,100,200);
         stateMarkerLabel.setFont(new Font("Times New Roman",Font.BOLD,30));
         stateMarkerLabel.setForeground(Color.RED);

         carImageLabel = new JLabel();
         carImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
         carImageLabel.setIcon(new ImageIcon("image/new car.jpg"));
         carImageLabel.setBounds(1,0,1000,700);

         seatBeltImageButton = new JButton();
         seatBeltImageButton.setBounds(280,280,35,35);
         seatBeltImageButton.setIcon(new ImageIcon("image/SeatBelt.png"));
         seatBeltImageButton.setFocusable(false);

         startButton = new JButton();
         startButton.setBounds(690,530,35,35);
         startButton.setIcon(new ImageIcon("image/StartButton.png"));
         startButton.setFocusable(false);

         reverseButton = new JButton();
         reverseButton.setBounds(703,655,35,35);
         reverseButton.setIcon(new ImageIcon("image/reverse.png"));
         reverseButton.setFocusable(false);

         driveButton = new JButton();
         driveButton.setBounds(697,586,35,35);
         driveButton.setIcon(new ImageIcon("image/drive.png"));
         driveButton.setFocusable(false);
    }

    public void addToFrame() {
        frame.add(currentTransitionLabel);
        frame.getContentPane().add(currentTransitionTextField);
        processStringScrollPane.getViewport().add(processStringTextArea);
        frame.getContentPane().add(processStringScrollPane);
        frame.add(currentStateLabel);
        frame.getContentPane().add(currentStateTextField);
        frame.add(speedometerLabel);

        frame.getContentPane().add(stateMarkerLabel);
        frame.getContentPane().add(carImageLabel);
        frame.getContentPane().add(startButton);
        frame.add(seatBeltImageButton);
        frame.getContentPane().add(reverseButton);
        frame.getContentPane().add(driveButton);
    }

    public void registerActionListeners() {
        startButton.addActionListener(e -> {
            //Actions to be carried out when start button is pressed
            currentTransitionTextField.setText("Start Signal");
            new Mp4("10");
            addToProcessString(currentTransitionTextField.getText());
            writeProcessString();

            currentState = currentState.stateCheck(currentStateTextField.getText());
            System.out.println("Got current State");
            //Call function to carry out actions for state change
            dfa.stateChange(currentState, Alphabet.startSignal);
            System.out.println("State Changed");
        });

        seatBeltImageButton.addActionListener(e -> {
            //Actions to be carried out when seatbelt button is pressed
            currentTransitionTextField.setText("Seat-Belt Engaged");
            new Mp4("13");
            addToProcessString(currentTransitionTextField.getText());
            writeProcessString();
            seatBeltImageButton.setVisible(false);

            currentState = currentState.stateCheck(currentStateTextField.getText());
            System.out.println("Got current State");
            //Call function to carry out actions for state change
            dfa.stateChange(currentState,Alphabet.seatBeltEngagedSignal);
            System.out.println("State Changed");
        });

        reverseButton.addActionListener(e -> {
            //Actions to be carried out when reverse button is clicked
            currentTransitionTextField.setText("Reverse-Selected");
            new Mp4("15");
            addToProcessString(currentTransitionTextField.getText());
            writeProcessString();

            currentState = currentState.stateCheck(currentStateTextField.getText());
            System.out.println("Got current State");
            //Call function to carry out actions for state change
            dfa.stateChange(currentState,Alphabet.reverseSelectedSignal);
            System.out.println("State Changed");
        });
        driveButton.addActionListener(e -> {
            //Actions to be carried out when drive button is clicked
            currentTransitionTextField.setText("Drive-Selected");
            new Mp4("16");
            addToProcessString(currentTransitionTextField.getText());
            writeProcessString();

            currentState = currentState.stateCheck(currentStateTextField.getText());
            System.out.println("Got current State");
            //Call function to carr out actions for state change
            dfa.stateChange(currentState,Alphabet.driveSelectedSignal);
            System.out.println("State changed");
        });
    }

    public void registerKeyListeners() {
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                switch (keyCode) {
                    case KeyEvent.VK_DOWN -> { //Down to break (general context)
                        //Brake Held if space and speed = 0
                        if (speedometerLabel.getText().equals("0")) {//Or could get the speed from the speed variable
                            //Actions to be taken when break held is pressed
                            currentTransitionTextField.setText("Brake Held");
                            new Mp4("11");
                            addToProcessString(currentTransitionTextField.getText());
                            writeProcessString();

                            currentState = currentState.stateCheck(currentStateTextField.getText());
                            System.out.println("Got current State");
                            //Call function to carry out actions for state change
                            dfa.stateChange(currentState, Alphabet.brakeHeldSignal);
                        } else {
                            //Regular break if space
                            //Actions to be taken when brake is pressed
                            currentTransitionTextField.setText("Brake Pressed");
                            new Mp4("11");
                            addToProcessString(currentTransitionTextField.getText());
                            writeProcessString();
                            dfa.speed--;
                            speedometerLabel.setText(String.valueOf(dfa.speed));

                            currentState = currentState.stateCheck(currentStateTextField.getText());
                            System.out.println("Got current State");
                            //Call function to carry out actions for state change
                            dfa.stateChange(currentState, Alphabet.brakePressedSignal);
                        }
                        System.out.println("State Changed");
                    }
                    case KeyEvent.VK_UP -> {
                        //Up key to accelerate
                        //Actions to be taken if accelerate is pressed
                        currentTransitionTextField.setText("Accelerate");
                        new Mp4("12");
                        addToProcessString(currentTransitionTextField.getText());
                        writeProcessString();

                        currentState = currentState.stateCheck(currentStateTextField.getText());
                        System.out.println("Got current state");
                        //Call function to carry out actions for state change
                        dfa.stateChange(currentState, Alphabet.accelerateSignal);
                        System.out.println("State Change");
                        if (currentState == States.INFORWARDMOTIONPOSITION || currentState == States.INREVERSEMOTIONPOSITION) {
                            dfa.speed++;
                            speedometerLabel.setText(String.valueOf(dfa.speed));
                        }
                    }
                    case KeyEvent.VK_P -> {
                        //P for Park
                        //Actions to be taken if park signal is given
                        currentTransitionTextField.setText("Park");
                        new Mp4("14");
                        addToProcessString(currentTransitionTextField.getText());
                        writeProcessString();
                        seatBeltImageButton.setVisible(true);

                        currentState = currentState.stateCheck(currentStateTextField.getText());
                        System.out.println("Got current State");
                        //Call function to carry out actions for state change
                        dfa.stateChange(currentState, Alphabet.parkSelectedSignal);
                        System.out.println("State Change");
                    }
                    case KeyEvent.VK_C -> {
                        //C for Cruise-Control
                        //Actions to be taken if cruise-control signal is given
                        currentTransitionTextField.setText("Cruise-Control");
                        addToProcessString(currentTransitionTextField.getText());
                        writeProcessString();

                        currentState = currentState.stateCheck(currentStateTextField.getText());
                        System.out.println("Got current State");
                        //Call function to carry out actions for state change
                        dfa.stateChange(currentState, Alphabet.setCruiseControlSignal);
                        System.out.println("State Change");
                    }
                    case KeyEvent.VK_R -> {
                        //R for reach signalling arrival of final destination or end of process string
                        //Actions to be taken if reach signal is given
                        currentTransitionTextField.setText("REACH");
                        addToProcessString(currentTransitionTextField.getText());
                        writeProcessString();

                        try {
                            voiceOver.playMp3("9");
                        } catch (JavaLayerException ex) {
                            ex.printStackTrace();
                        }

                        currentState = currentState.stateCheck(currentStateTextField.getText());
                        if ((dfa.accept(currentState))) {
                            JOptionPane.showMessageDialog(null, "Process String Accepted",
                                    "Process String Status", JOptionPane.INFORMATION_MESSAGE);
                        }else if (!(dfa.accept(currentState))) {
                            JOptionPane.showMessageDialog(null, "Process String Rejected",
                                    "Process String Status", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        });
    }
    
    public void addToProcessString(String transition) {
        transitionList.add(transition);
    }
    
    public void writeProcessString() {
        processStringTextArea.setText("Process String:");
        for (String transition: transitionList) {
            processStringTextArea.append("\n" + transition);
        }
    }
}
