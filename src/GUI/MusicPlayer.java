package GUI;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * A music player which is able to play and stop sounds from a certain path to a wav sound-file
 * <p>
 * Created by Zacke on 2015-12-11.
 */
public class MusicPlayer {
    private File file;
    private Clip clip;

    public MusicPlayer() {
    }

    /**
     * Plays a specific sound file as background music
     *
     * @param filePath which is a String to which music file to play
     */
    public void play(String filePath) {

        try {
            file = new File(getClass().getResource(filePath).toURI());
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Stops the current music playing
     */
    public void stop() {
        clip.stop();
    }
}
