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

public class Mp4Test extends JFrame implements KeyListener{
    public static Canvas cVideo;
    MediaPlayerFactory mediaPlayerFactory1;
    MediaPlayerFactory mediaPlayerFactory2;

    public EmbeddedMediaPlayer mediaPlayer1;
    public EmbeddedMediaPlayer mediaPlayer2;
    public JPanel videoPanel;

    public static void main(String[] args) {
        new Mp4Test();
    }

    public Mp4Test() {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        LibXUtil.initialise();

        mediaPlayerFactory1 = new MediaPlayerFactory();
        mediaPlayerFactory2 = new MediaPlayerFactory();
        mediaPlayer1 = mediaPlayerFactory1.newEmbeddedMediaPlayer();
        mediaPlayer2 = mediaPlayerFactory2.newEmbeddedMediaPlayer();

        //Set main frame size
        this.setLayout(new BorderLayout());
        this.setSize(1500,800);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);

        cVideo= new Canvas();

        videoPanel = new JPanel(new GridLayout(1,1));
        videoPanel.setPreferredSize(new Dimension(1000,700));

        videoPanel.add(cVideo,BorderLayout.CENTER);
        this.add(videoPanel,BorderLayout.CENTER);
    }

    public void playVideo (String video) {

        mediaPlayer1.setVideoSurface(mediaPlayerFactory1.newVideoSurface(cVideo));
        mediaPlayer1.setEnableKeyInputHandling(true);
        mediaPlayer1.setPlaySubItems(true);
        mediaPlayer1.prepareMedia(video);
        mediaPlayer1.play();
    }

    public void playVideoSecond (String video) {
        mediaPlayer2.setVideoSurface(mediaPlayerFactory2.newVideoSurface(cVideo));
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

        switch (key) {
            case "0":
                System.exit(0);
                break;

            case "1":
                videoPanel.setVisible(true);
                mediaPlayer2.stop();
                playVideo("videos/InForward Motion.mp4");
                break;

            case "2":
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
