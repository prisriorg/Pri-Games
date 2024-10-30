package org.prisri.prigame.models;

import android.graphics.Bitmap;

public class Pieces {

    private int pX = 0;
    private int pY = 0;
    private Bitmap originalResource;
    private int position = 0;

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpY() {
        return pY;
    }

    public void setpY(int pY) {
        this.pY = pY;
    }

    public  Bitmap getOriginalResource() {
        return originalResource;
    }

    public void setOriginalResource( Bitmap originalResource) {
        this.originalResource = originalResource;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
