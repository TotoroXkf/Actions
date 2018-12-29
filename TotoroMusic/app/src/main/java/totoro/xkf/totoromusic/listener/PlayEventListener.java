package totoro.xkf.totoromusic.listener;

import totoro.xkf.totoromusic.model.Music;

public interface PlayEventListener {
    void onMusicPlay(Music music);

    void onMusicPause();
}
