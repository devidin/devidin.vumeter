package soundmodel;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.*;
public class SoundCardHelper {
    public static void listMixers() {
        Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
        System.out.println("Available Audio Mixers:");
        for (int m=0 ; m < mixerInfos.length ; m++) {
            System.out.println("Mixer "+ m + ": " +  mixerInfos[m]);
            Mixer mixer=AudioSystem.getMixer(mixerInfos[m]);
            Line.Info[] lineInfos = mixer.getSourceLineInfo();
            for(int l=0; l<lineInfos.length; l++) {
                System.out.println("      line "+ l + ": " +  lineInfos[l]);

                try {
                    Line line = mixer.getLine(lineInfos[l]);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
    public static Mixer.Info[] getMixersList() {
        return AudioSystem.getMixerInfo();
    }


    public static Line[] getMixersLines(Mixer mixer) {
        Line[] lines = mixer.getTargetLines();

        return lines;
    }
    public static void audioLevelMonitor(int mixerId, int lineId) {

        try {
            // Get the selected audio mixer
            Mixer.Info[] mixersInfos = getMixersList();
            Mixer mixer = AudioSystem.getMixer(mixersInfos[mixerId]);
            System.out.println("======================================================================");
            System.out.println("Monitoring mixer: " + mixersInfos[mixerId]);

            // Get the selected line from the mixer
            Line.Info[] lineInfos = mixer.getSourceLineInfo();
            Line.Info lineInfo = lineInfos[lineId];
            Line line = mixer.getLine(lineInfo);
            System.out.println("Monitoring Line: " + lineInfos[lineId]);
            System.out.println("======================================================================");
            /*
            while (true) {
                // Display the output level
                System.out.println("Output Level: " + amplitude);
            }
            */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing line...");
                /*
            if (targetDataLine!=null) {
                targetDataLine.stop();
                targetDataLine.close();
            }
            */
        }

    }
    private static int calculateAmplitude ( byte[] buffer, int bytesRead){
        int maxAmplitude = 0;
        for (int i = 0; i < bytesRead; i += 2) {
            // Convert bytes to 16-bit signed PCM samples
            int sample = (buffer[i + 1] << 8) | (buffer[i] & 0xFF);

            // Calculate amplitude (absolute value of the sample)
            int amplitude = Math.abs(sample);

            // Update max amplitude if the current amplitude is greater
            maxAmplitude = Math.max(maxAmplitude, amplitude);
        }
        return maxAmplitude;
    }
}
