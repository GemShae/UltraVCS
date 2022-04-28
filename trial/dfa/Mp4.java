package dfa;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

import javax.swing.*;
import java.awt.*;

public class Mp4 extends JInternalFrame {
    public static Canvas cVideo;
    MediaPlayerFactory mediaPlayerFactory;
    public EmbeddedMediaPlayer mediaPlayer;
    public JPanel videoPanel;

    public Mp4() {
    /*    initialiseComponents();
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
        LibXUtil.initialise(); */

        //setVideo(video);
    }

    public void initialiseComponents() {
        this.setLayout(new BorderLayout());
        this.setSize(990,690);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cVideo = new Canvas();
        cVideo.setVisible(true);

        videoPanel = new JPanel(new GridLayout(1,1));
        videoPanel.setPreferredSize(new Dimension(950,650));
        videoPanel.setVisible(true);

        videoPanel.add(cVideo,BorderLayout.CENTER);
        this.add(videoPanel,BorderLayout.CENTER);
    }

    public void playVideoSetup (String video) {
        mediaPlayerFactory = new MediaPlayerFactory();
        mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(cVideo));
        mediaPlayer.setEnableKeyInputHandling(true);
        mediaPlayer.setPlaySubItems(true);
        mediaPlayer.prepareMedia(video);
        //videoThread.start();
        playVideoActual();
    }

    public void playVideoActual() {
        mediaPlayer.play();
    }

    public String setVideo(String option) {
        String videoFile = "";
        switch (option) {
            case "1":
                videoFile = "videos/Initial Car.mp4";
                mediaPlayer.stop();
                break;
            case "2":
                playVideoSetup("videos/Off Position.mp4");
                break;
            case "3":
                playVideoSetup("videos/IgnitionOn Position.mp4");
                break;
            case "4":
                playVideoSetup("videos/Hold Position.mp4");
                break;
            case "5":
                playVideoSetup("videos/EngineStarted Position.mp4");
                break;
            case "6":
                playVideoSetup("videos/Stationary.mp4");
                break;
            case "7":
                playVideoSetup("videos/Reverse.mp4");
                break;
            case "8":
                playVideoSetup("videos/InForward Motion");
                break;
            case "9":
                playVideoSetup("videos/Cruise Control.mp4");
                break;
            case "10":
                playVideoSetup("videos/Start.mp4");
                break;
            case "11":
                playVideoSetup("videos/brakeButton.mp4");
                break;
            case "12":
                playVideoSetup("videos/Accelerate.mp4");
                break;
            case "13":
                playVideoSetup("videos/Seatbelt.mp4");
                break;
            case "14":
                playVideoSetup("videos/Park.mp4");
                break;
            case "15":
                playVideoSetup("videos/ReverseSelected.mp4");
                break;
            case "16":
                playVideoSetup("videos/Drive.mp4");
                break;
            //No Cruise Control Transition Video
            default:
                break;
        }
        return videoFile;
    }

    /* public Thread videoThread = new Thread() {
        public void run() {
            try {
                mediaPlayer.play();
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }; */
}
