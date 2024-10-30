package org.prisri.prigame.puzzle;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.ImageView;

import org.prisri.prigame.R;
import org.prisri.prigame.cutter.CutMap;
import org.prisri.prigame.cutter.ImageCutter;
import org.prisri.prigame.models.PuzzlePiece;

import java.io.File;
import java.util.ArrayList;


public class Puzzle {

    Activity mActivity;
    public Bitmap mSourceImage = null;
    ArrayList<PuzzlePiece> puzzlePieceArrayList;


    public ArrayList<PuzzlePiece> createPuzzlePieces(Activity aActivity, Bitmap aBitmap, int width, int height,
                                                     ImageView imageView, String path, int horizontalResolution, int verticalResolution) {
        this.mActivity = aActivity;
        this.puzzlePieceArrayList = new ArrayList<>();
        getDisplaySize(aBitmap, width, height, imageView);
        deleteDirectories(path);
        CutMap cutMap = new CutMap(horizontalResolution, verticalResolution);
        ImageCutter imageCutter = new ImageCutter(mSourceImage, cutMap);
        drawOrderedPuzzlePieces(imageCutter, cutMap);
        mSourceImage = null;
        return puzzlePieceArrayList;
    }

    private void getDisplaySize(Bitmap bitmap, int widthFinal, int heightFinal, final ImageView imageView) {
        Bitmap image = null;
        try {
            image = BitmapFactory.decodeResource(mActivity.getResources(), R.drawable.jig);
            mSourceImage = Bitmap.createScaledBitmap(image, widthFinal, heightFinal, false);
        } catch (OutOfMemoryError ex) {
            ex.printStackTrace();
        } finally {
            image = null;
        }
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(mSourceImage);
            }
        });

    }

    private void deleteDirectories(String aPath) {
        File dir = new File(Environment.getExternalStorageDirectory().toString() + aPath);
        if (dir.exists() && dir.isDirectory()) {
            dir.delete();
            dir.mkdir();
        } else {
            dir.mkdir();
        }
    }

    private void drawOrderedPuzzlePieces(ImageCutter imageCutter, CutMap cutMap) {
        PuzzlePiece[][] puzzlePieces = imageCutter.cutImage();
        for (int i = 0; i < cutMap.getHorizontalResolution(); i++) {
            for (int j = 0; j < cutMap.getVerticalResolution(); j++) {
                PuzzlePiece piece = puzzlePieces[i][j];
                puzzlePieceArrayList.add(piece);
            }
        }
    }

}
