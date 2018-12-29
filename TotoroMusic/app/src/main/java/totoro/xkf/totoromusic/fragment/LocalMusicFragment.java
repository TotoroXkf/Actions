package totoro.xkf.totoromusic.fragment;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.adapter.LocalMusicAdapter;
import totoro.xkf.totoromusic.application.AppCache;
import totoro.xkf.totoromusic.listener.OnMoreClickListener;
import totoro.xkf.totoromusic.listener.OnPositionChangeListener;
import totoro.xkf.totoromusic.model.Music;
import totoro.xkf.totoromusic.service.MusicService;

public class LocalMusicFragment extends Fragment implements OnMoreClickListener, OnPositionChangeListener {
    private RecyclerView rvLocalMusicList;
    private TextView tvNoMusic;
    private LocalMusicAdapter adapter;
    private MusicService mMusicService;
    private static final int REQUEST_WRITE_SETTINGS = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.local_music_fragment, container, false);
        mMusicService = AppCache.getMusicService();
        mMusicService.setOnPositionChangeListener(this);
        rvLocalMusicList = view.findViewById(R.id.rv_local_music_list);
        tvNoMusic = view.findViewById(R.id.no_music);
        if (AppCache.getMusicList().isEmpty()) {
            tvNoMusic.setVisibility(View.VISIBLE);
            rvLocalMusicList.setVisibility(View.GONE);
        } else {
            adapter = new LocalMusicAdapter();
            adapter.setOnMoreClickListener(this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                    (getContext(), LinearLayoutManager.VERTICAL, false);
            rvLocalMusicList.setLayoutManager(linearLayoutManager);
            rvLocalMusicList.setAdapter(adapter);
        }
        return view;
    }

    @Override
    public void onMoreClick(final Music music) {
        String[] items = getResources().getStringArray(R.array.localMusicMore);
        if (music.getId() == mMusicService.getCurrentPlayingMusic().getId()) {
            items = new String[]{items[0], items[1], items[2]};
        }
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        share(music);
                        break;
                    case 1:
                        dialog.cancel();
                        showMusicInfo(music);
                        break;
                    case 2:
                        requestSetRingtone(music);
                        break;
                    case 3:
                        dialog.cancel();
                        mMusicService.delete(music);
                        delete();
                        break;
                }
            }
        };
        new AlertDialog.Builder(getContext()).setTitle(getString(R.string.more))
                .setItems(items, listener)
                .show();
    }

    private void delete() {
        if (AppCache.getMusicList().isEmpty()) {
            tvNoMusic.setVisibility(View.VISIBLE);
            rvLocalMusicList.setVisibility(View.GONE);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void share(Music music) {
        String path = music.getPath();
        Uri uri = Uri.parse(path);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("audio/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(intent, getString(R.string.shareTo)));
    }

    private void showMusicInfo(Music music) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(music.getTitle());
        String message = "作者   " + music.getArtist() + "\n\n"
                + "专辑   " + music.getAlbum() + "\n\n"
                + "时长   " + music.getTime() + "\n\n"
                + "文件名   " + music.getFileName() + "\n\n"
                + "大小   " + music.getSize() + "\n\n"
                + "文件路径   " + music.getPath();
        dialog.setMessage(message);
        dialog.show();
    }

    private void requestSetRingtone(final Music music) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.System.canWrite(getContext())) {
            Toast.makeText(getContext(), "没有权限，无法设置铃声，请授予权限", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + getContext().getPackageName()));
            startActivityForResult(intent, REQUEST_WRITE_SETTINGS);
        } else {
            setRingtone(music);
        }
    }

    private void setRingtone(Music music) {
        Uri uri = MediaStore.Audio.Media.getContentUriForPath(music.getPath());
        // 查询音乐文件在媒体库是否存在
        Cursor cursor = getContext().getContentResolver().query(uri, null,
                MediaStore.MediaColumns.DATA + "=?", new String[]{music.getPath()}, null);
        if (cursor == null) {
            return;
        }
        if (cursor.moveToFirst() && cursor.getCount() > 0) {
            String _id = cursor.getString(0);
            ContentValues values = new ContentValues();
            values.put(MediaStore.Audio.Media.IS_MUSIC, true);
            values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
            values.put(MediaStore.Audio.Media.IS_ALARM, false);
            values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
            values.put(MediaStore.Audio.Media.IS_PODCAST, false);
            getContext().getContentResolver().update(uri, values, MediaStore.MediaColumns.DATA + "=?",
                    new String[]{music.getPath()});
            Uri newUri = ContentUris.withAppendedId(uri, Long.valueOf(_id));
            RingtoneManager.setActualDefaultRingtoneUri(getContext(),
                    RingtoneManager.TYPE_RINGTONE, newUri);
            Toast.makeText(getContext(), "设置成功", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    @Override
    public void onPositionChange(int newPosition) {
        adapter.updatePlayLabel(newPosition);
    }

    public void refreshData() {
        rvLocalMusicList.getAdapter().notifyDataSetChanged();
    }
}
