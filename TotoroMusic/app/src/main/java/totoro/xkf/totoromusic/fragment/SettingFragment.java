package totoro.xkf.totoromusic.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import totoro.xkf.totoromusic.R;

public class SettingFragment extends PreferenceFragment  {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_setting);
    }
}


