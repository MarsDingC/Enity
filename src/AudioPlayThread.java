import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Created by Tim on 2017/12/18
 */
public class AudioPlayThread extends Thread {
    private AudioPlayer player = new AudioPlayer();
    @Override
    public void run() {
        super.run();
        try {
            player.load();
            player.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    public void musicPause() {
        player.pause();
    }

    public void musicResume() {
        player.resume();
        interrupt();
    }
}
