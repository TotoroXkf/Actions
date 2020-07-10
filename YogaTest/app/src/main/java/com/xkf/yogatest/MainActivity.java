package com.xkf.yogatest;

import android.os.Bundle;

import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.soloader.SoLoader;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SoLoader.init(this, false);

        ComponentContext componentContext = new ComponentContext(this);
        final Component component = RecyclerCollectionComponent.create(componentContext)
                .disablePTR(true)
                .section(ListSection.create(new SectionContext(componentContext)).build())
                .build();
        LithoView lithoView = LithoView.create(componentContext, component);
        setContentView(lithoView);
    }
}