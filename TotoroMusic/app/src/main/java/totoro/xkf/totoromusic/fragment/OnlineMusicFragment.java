package totoro.xkf.totoromusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import totoro.xkf.totoromusic.R;
import totoro.xkf.totoromusic.adapter.OnlineMusicAdapter;

public class OnlineMusicFragment extends Fragment {
    private RecyclerView rvOnlineMusicList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.online_music_fragment, container, false);
        rvOnlineMusicList = view.findViewById(R.id.rv_online_music_list);
        OnlineMusicAdapter adapter = new OnlineMusicAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvOnlineMusicList.setLayoutManager(manager);
        rvOnlineMusicList.setAdapter(adapter);
        return view;
    }
}
