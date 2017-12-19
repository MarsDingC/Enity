import javax.sound.sampled.*;
import java.io.File;

/**
 * Created by MarsDingC on 2017/12/18.
 */
public class AudioManager {

    private static int num = 0;
    private static Clip clip;

    static void playSoundEffect() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("footsteps/footstep_concrete_" + (num % 4 + 1) + ".wav"));
            num++;
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(5.0f); // Reduce volume by 10 decibels.
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void beginBGM() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("bgm.mp3"));
            AudioFormat audioFormat = audioInputStream.getFormat();
            if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
                audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, audioFormat.getSampleRate(), 16, audioFormat.getChannels(), audioFormat.getChannels() * 2, audioFormat.getSampleRate(), audioFormat.isBigEndian());
                audioInputStream = AudioSystem.getAudioInputStream(audioFormat, audioInputStream);
            }
            clip = AudioSystem.getClip();
            num++;
            clip.open(audioInputStream);
            setValue(-5.0f);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void setValue(float value) {
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(value);
    }

    static void playBGM() throws LineUnavailableException {
        clip.start();
    }

    static void stopBGM() throws LineUnavailableException {
        clip.stop();
    }

    static void restartBGM(){
        clip.stop();
        clip.setFramePosition(180000);
        clip.start();
    }
}
