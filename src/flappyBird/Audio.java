package flappyBird;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
	File music;
	Clip clip;
	private int rand;
	String[] songList = {"songs/bigredheart.wav",
			 			 "songs/heartbeatfromyou.wav",
			 			 "songs/king.wav",
			 			 "songs/pekomiko.wav",
			 			 "songs/poponrocks.wav"};
	int[] songLength = {213, 239, 136, 258, 160};
	
	public Audio() {
	}
	
	public void musicStop() {
		this.clip.stop();
	}
	
	public void musicLoop() {
		try {
			while (true) {
				rand = (int)(Math.random()*songList.length);
				this.clip = AudioSystem.getClip();
				this.music = new File(songList[rand]);
				this.clip.open(AudioSystem.getAudioInputStream(this.music));
				this.clip.loop(0);
				Thread.sleep(songLength[rand]*1000);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	

}
