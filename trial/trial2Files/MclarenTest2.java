package trial2Files;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MclarenTest2 extends JFrame {
    private JLabel carImageLabel;
    private JButton startButton;

    public MclarenTest2(String title) {
        super(title);

        initialiseComponents();
        addToFrame();
        registerListeners();
        setFrameProperties();
    }

    public void initialiseComponents() {
        carImageLabel = new JLabel("");
        carImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        carImageLabel.setIcon(new ImageIcon("image/car.jpg"));
        carImageLabel.setSize(1000, 700);

        //start = new ImageIcon("image/Start Button.jpeg");
        startButton = new JButton("Start");
        //startButton.setIcon(start);
        startButton.setBounds(1000, 100, 10, 10);
       // startImg = start.getImage();
       // startImg = startImg.getScaledInstance(startButton.getWidth(),startButton.getHeight(),Image.SCALE_SMOOTH);
    }

    public void addToFrame() {
        this.add(carImageLabel);
        this.add(startButton);
    }

    public void setFrameProperties() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setContentPane();
        this.pack();

        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        this.setSize(1000, 1000);
    }

    public void registerListeners() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Start Button clicked", "Start",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}