
package com.android.camera.ui;

import android.graphics.Rect;

import javax.microedition.khronos.opengles.GL11;

public abstract class AbstractIndicator extends GLView {
    private static final int DEFAULT_PADDING = 5;

    abstract protected ResourceTexture getIcon();

    public AbstractIndicator() {
        setPaddings(DEFAULT_PADDING, 0, DEFAULT_PADDING, 0);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        ResourceTexture icon = getIcon();
        new MeasureHelper(this)
               .setPreferedContentSize(icon.getWidth(), icon.getHeight())
               .measure(widthSpec, heightSpec);
    }

    @Override
    protected void render(GLRootView root, GL11 gl) {
        ResourceTexture icon = getIcon();

        Rect p = mPaddings;
        int width = getWidth() - p.left - p.right;
        int height = getHeight() - p.top - p.bottom;

        if (icon != null && icon.bind(root, gl)) {
            icon.draw(root,
                    p.left + (width - icon.getWidth()) / 2,
                    p.top + (height - icon.getHeight()) / 2);
        }
    }

    abstract public GLView getPopupContent();

    abstract public void overrideSettings(String key, String settings);
}