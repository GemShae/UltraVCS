 package trialFiles;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.*;
import java.awt.*;

public class TestVideo {

    public static void main(String[] args) {
        /* Create a GUI to display video */
        JFrame f = new JFrame();
        f.setLocation(100,100);
        f.setSize(1000,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        //Create an instance of Canvas which will be used to display video
        Canvas c = new Canvas();
        //Background is black
        c.setBackground(Color.black);

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());

        //Video take all the surface of JPanel
        p.add(c);
        f.add(p);

        /* Read the video file using vlcj and the native library of VLC */
        //Load the native library of VLC from the directory where VLC is installed
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:/Program Files/VideoLAN/VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

        //Initialize the media player
        MediaPlayerFactory mpf = new MediaPlayerFactory();

        //Control all the interactions with the user
        EmbeddedMediaPlayer emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f));
        emp.setVideoSurface(mpf.newVideoSurface(c));

        //Full Screen
        //emp.toggleFullScreen();

        //hide the cursor
       // emp.setEnableMouseInputHandling(false);
        //Disable the keyboard
       // emp.setEnableKeyInputHandling(false);


        //Prepare file to read
        emp.prepareMedia("videos/Initial Car.mp4");
        //Read the file
        emp.play();
    }
}
