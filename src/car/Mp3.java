package car;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Mp3 {
	@SuppressWarnings("unused")
	
	public Mp3() { }

	BufferedInputStream BufferedInputStream;
	FileInputStream FileInputStream;
	Player player;
	
	public void playMp3(String name) throws JavaLayerException{
		try {
			setSound(name);
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		player = new Player(BufferedInputStream);
		player.play();
		//songThread.start();
	}
	
	public void setSound(String option) throws FileNotFoundException{
		switch (option){
			case "1": FileInputStream = new FileInputStream("audios/OFF.mp3");
						 BufferedInputStream = new BufferedInputStream(FileInputStream);
				break;
			case "2": FileInputStream = new FileInputStream("audios/Ignition On.mp3");
			 			 BufferedInputStream = new BufferedInputStream(FileInputStream);
			 	break;
			case "3": FileInputStream = new FileInputStream("audios/Hold Position.mp3");
				BufferedInputStream = new BufferedInputStream(FileInputStream);
				break;
			case "4": FileInputStream = new FileInputStream("audios/EngineStarted.mp3");
				BufferedInputStream = new BufferedInputStream(FileInputStream);
			 break;
			case "5": FileInputStream = new FileInputStream("audios/Stationary.mp3");
				BufferedInputStream = new BufferedInputStream(FileInputStream);
				break;
			case "6": FileInputStream = new FileInputStream("audios/Reverse.mp3");
				BufferedInputStream = new BufferedInputStream(FileInputStream);
				break;
			case "7": FileInputStream = new FileInputStream("audios/Forward.mp3");
				BufferedInputStream = new BufferedInputStream(FileInputStream);
				break;
			case "8": FileInputStream = new FileInputStream("audios/CruiseControl.mp3");
				BufferedInputStream = new BufferedInputStream(FileInputStream);
				break;
			case "9": FileInputStream = new FileInputStream("audios/Reach.mp3");
				BufferedInputStream = new BufferedInputStream(FileInputStream);
				break;
			default:
				break;
		}
	}
}
