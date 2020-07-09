package com.xkf.yogatest;

import android.graphics.Color;

import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaEdge;

@LayoutSpec
class ListItemSpec {
    @OnCreateLayout
    static Component onCreateLayout(ComponentContext componentContext) {
        return Column.create(componentContext)
                .paddingDip(YogaEdge.ALL, 16)
                .backgroundColor(Color.WHITE)
                .child(Text.create(componentContext)
                        .text("Hello World")
                        .textSizeDip(40))
                .child(Text.create(componentContext)
                        .text("Litho tutorial")
                        .textSizeSp(20))
                .build();
    }
}
