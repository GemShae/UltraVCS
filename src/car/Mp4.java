package car;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.*;
import java.awt.*;

public class Mp4 {

    public Mp4(String video) {
        /* Create a GUI to display video */
        JFrame frame = new JFrame();
        //frame.setLocation(100,100);
        frame.setSize(1000,738);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create an instance of Canvas which will be used to display video
        Canvas canvas = new Canvas();
        //Background is black
        canvas.setBackground(Color.black);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //Video take all the surface of JPanel
        panel.add(canvas);
        frame.add(panel);

        /* Read the video file using "vlcj" and the native library of VLC */
        //Load the native library of VLC from the directory where VLC is installed
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

        //Initialize the media player
        MediaPlayerFactory mpf = new MediaPlayerFactory();

        //Control all the interactions with the user
        EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer();
        emp.setVideoSurface(mpf.newVideoSurface(canvas));

        emp.stop();
        frame.setVisible(true);
        //Prepare file to read
        emp.prepareMedia(setVideo(video));
        //Read the file
        emp.play();
    }

    public String setVideo(String option) {
        String videoFile = "";
        switch (option) {
            case "1":
                videoFile = "videos/Initial Car.mp4";
                break;
            case "2":
                videoFile = "videos/Off Position.mp4";
                break;
            case "3":
                videoFile = "videos/IgnitionOn.mp4";
                break;
            case "4":
                videoFile = "videos/Hold Position.mp4";
                break;
            case "5":
                videoFile = "videos/EngineStartedPosition1.mp4";
                break;
            case "6":
                videoFile = "videos/Stationary.mp4";
                break;
            case "7":
                videoFile = "videos/Reverse.mp4";
                break;
            case "8":
                videoFile = "videos/InForward Motion.mp4";
                break;
            case "9":
                videoFile = "videos/Cruise Control.mp4";
                break;
            case "10":
                videoFile = "videos/Start.mp4";
                break;
            case "11":
                videoFile = "videos/Brake.mp4";
                break;
            case "12":
                videoFile = "videos/Accelerate.mp4";
                break;
            case "13":
                videoFile = "videos/Seatbelt.mp4";
                break;
            case "14":
                videoFile = "videos/Park.mp4";
                break;
            case "15":
                videoFile = "videos/ReverseSelected.mp4";
                break;
            case "16":
                videoFile = "videos/Drive.mp4";
                break;
            case "17":
                videoFile = "videos/EngineStarted Position2.mp4";
            //No Cruise Control Transition Video
            default:
                break;
        }
        return videoFile;
    }
}
