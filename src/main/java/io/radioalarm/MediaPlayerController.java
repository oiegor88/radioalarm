package io.radioalarm;

import uk.co.caprica.vlcj.player.base.MediaPlayer;

public class MediaPlayerController {

    private final MediaPlayer mediaPlayer;

    public MediaPlayerController(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void play(String source) {
        this.mediaPlayer.media().play(source);
    }

    public void stop() {
        this.mediaPlayer.controls().stop();
    }

    public void exit() {
        this.mediaPlayer.release();
    }
}
