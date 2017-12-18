import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    private boolean isStopped;

	private boolean isPaused;

	private SourceDataLine audioSDL;

    private AudioInputStream audioInputStream;

    private final Object lock = new Object();
	public void load()
			throws UnsupportedAudioFileException, IOException,
			LineUnavailableException {

        String audioFilePath = "bgm.mp3";

        File file = new File(audioFilePath);

        audioInputStream = AudioSystem.getAudioInputStream(file);
        AudioFormat audioFormat = audioInputStream.getFormat();
        if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
            audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, audioFormat.getSampleRate(), 16, audioFormat.getChannels(), audioFormat.getChannels() * 2, audioFormat.getSampleRate(), audioFormat.isBigEndian());
            audioInputStream = AudioSystem.getAudioInputStream(audioFormat, audioInputStream);

        }
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);
        audioSDL = (SourceDataLine) AudioSystem.getLine(info);
        audioSDL.open(audioFormat);
	}

    void play() throws IOException, LineUnavailableException, UnsupportedAudioFileException {

		audioSDL.start();

        isStopped = false;
		synchronized (lock) {
		    while (true) {
                int n;
                byte data[] = new byte[320];
                while ((n = audioInputStream.read(data, 0, data.length)) != -1) {
                    //System.out.println(line.getMicrosecondPosition());
                    while (isPaused) {
                        if (audioSDL.isRunning()) {
                            audioSDL.stop();
                            System.out.println("暂停");
                        }
                        try {
                            lock.wait();
                            System.out.println("等待");
                        } catch (InterruptedException ignored) {
                        }
                    }
                    if (isStopped) break;
                    if (!audioSDL.isRunning()) {
                        System.out.println("开始播放");
                        audioSDL.start();
                    }

                    audioSDL.write(data, 0, n);
                }
                audioSDL.close();
                System.out.println("播放完了");
                load();
            }

        }

	}

	/**
	 * Stop playing back.
	 */
    void stop() {
		isStopped = true;
	}

	void pause() {
		isPaused = true;
	}

	void resume() {
		isPaused = false;
	}

}