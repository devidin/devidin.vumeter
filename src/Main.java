//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import soundmodel.SoundCardHelper;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;

public class Main {
    public static void main(String[] args) {
        SoundCardHelper.listMixers();
        SoundCardHelper.audioLevelMonitor(7, 0);
    }
}