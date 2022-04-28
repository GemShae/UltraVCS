package trialFiles;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TestMultipleVideos extends JFrame implements KeyListener{
    public static Canvas cVideo;
    MediaPlayerFactory mediaPlayerFactory1;
    MediaPlayerFactory mediaPlayerFactory2;

    public EmbeddedMediaPlayer mediaPlayer1;
    public EmbeddedMediaPlayer mediaPlayer2;
    //public JPanel paintsPanel; //Was commented
    public JPanel videoPanel;

    public TestMultipleVideos() {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        LibXUtil.initialise();

        mediaPlayerFactory1 = new MediaPlayerFactory();
        mediaPlayerFactory2 = new MediaPlayerFactory();
        mediaPlayer1 = mediaPlayerFactory1.newEmbeddedMediaPlayer();
        mediaPlayer2 = mediaPlayerFactory2.newEmbeddedMediaPlayer();

        setLayout(new BorderLayout());

        //Was Commented
    /*    paintsPanel = new JPanel(new GridLayout(1,1));
        this.add(paintsPanel,BorderLayout.SOUTH);
        this.setBackground(Color.WHITE);
        paintsPanel.setPreferredSize(new Dimension(1000,700));
        paintsPanel.setBackground(Color.BLUE); */

        //Video Panel
        setBackground(Color.WHITE);

        //Was Commented
        videoPanel = new JPanel(new GridLayout(1,1));
        this.add(videoPanel, BorderLayout.NORTH);
        this.setBackground(Color.blue);
        videoPanel.setPreferredSize(new Dimension(1000,700));
        videoPanel.setBackground(Color.RED);
        videoPanel.setBorder(BorderFactory.createTitledBorder("Input Scale"));

        //Set main frame size
        this.setSize(1500,1000);
        setResizable(true);
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.black);

        cVideo= new Canvas();
        cVideo.setBackground(Color.black);
        addKeyListener(this);

        add(cVideo,BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new TestMultipleVideos();
    }

    public void playVideo (String video) {
        mediaPlayer1.setVideoSurface(mediaPlayerFactory1.newVideoSurface(cVideo));
        //mediaPlayer1.setEnableMouseInputHandling(false);
        mediaPlayer1.setEnableKeyInputHandling(true);
        mediaPlayer1.setPlaySubItems(true);
        mediaPlayer1.prepareMedia(video);
        mediaPlayer1.play();
    }

    public void playVideoSecond (String video) {
        mediaPlayer2.setVideoSurface(mediaPlayerFactory2.newVideoSurface(cVideo));
        //mediaPlayer2.setEnableMouseInputHandling(false);
        mediaPlayer2.setEnableKeyInputHandling(true);
        mediaPlayer2.setPlaySubItems(true);
        mediaPlayer2.prepareMedia(video);
        mediaPlayer2.play();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = KeyEvent.getKeyText(e.getKeyCode());
        //boolean flag = false;

        switch (key) {
            case "0":
                System.exit(0);
                break;

            case "1":
                videoPanel.setVisible(true);
                mediaPlayer2.stop();
                playVideo("videos/InForward Motion.mp4");
                //paintsPanel.setVisible(false);
                break;

            case "2":
               // paintsPanel.setVisible(true);
                mediaPlayer1.stop();
                playVideoSecond("videos/Initial Car.mp4");
                videoPanel.setVisible(true);
                break;

            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Released= " + KeyEvent.getKeyText(e.getKeyCode()));

    }
}
