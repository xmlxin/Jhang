package com.xiaoxin.feng.jhang.util;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import com.xiaoxin.feng.jhang.app.Constant;
import com.xiaoxin.feng.jhang.bean.ImagePiece;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiaoxin
 * date: 2018/9/17
 * describe:
 * 修改内容:
 */
public class BitmapUtil {

    private static final String TAG = "BitmapUtil";

    public static void isPathExist() {
        File rootFile = new File(Constant.filePath) ;
        if(!rootFile.exists()){
            rootFile.mkdirs() ;
        }
    }

    public static void isPathExist(String path) {
        File rootFile = new File(Constant.filePath+path) ;
        if(!rootFile.exists()){
            rootFile.mkdirs() ;
        }
    }

    /**
     * 本地文件转bitmap
     * @param path 路径
     * @return Bitmap
     */
    @SuppressLint("NewApi")
    public static Bitmap decodeFile2Bitmap(String path) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.outConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(path,options);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int newHeight,newWidth;
        if (height >= width) {
            newHeight= height;
            newWidth = height;
        }else {
            newHeight= width;
            newWidth = width;
        }
        Bitmap newBitmap = Bitmap.createBitmap(newWidth,newHeight, Bitmap.Config.ARGB_8888);
        newBitmap.eraseColor(Color.WHITE);//填充颜色
        Canvas mCanvas = new Canvas(newBitmap);
        mCanvas.drawBitmap(bitmap, (newWidth - width) / 2,
                (newHeight - height) / 2, null);
        return newBitmap;
    }

    /**
     * 这段代码采用https://blog.csdn.net/arui319/article/details/7470193
     * 切割图片 3*3
     * @param bitmap
     * @param xPiece  x轴切割数量
     * @param yPiece  y轴切割数量
     * @return
     */
    public static List<ImagePiece> split(Bitmap bitmap, int xPiece, int yPiece) {

        List<ImagePiece> pieces = new ArrayList<ImagePiece>(xPiece * yPiece);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int pieceWidth = width / 3;
        int pieceHeight = height / 3;
        for (int i = 0; i < yPiece; i++) {
            for (int j = 0; j < xPiece; j++) {
                ImagePiece piece = new ImagePiece();
                piece.index = j + i * xPiece;
                int xValue = j * pieceWidth;
                int yValue = i * pieceHeight;
                piece.bitmap = Bitmap.createBitmap(bitmap, xValue, yValue,
                        pieceWidth, pieceHeight);
                pieces.add(piece);
            }
        }
        return pieces;
    }

    /**
     * 保存图片
     * @param bitmap
     * @param index bitmap索引
     * @return
     */
    public static File savePic(Bitmap bitmap, int index){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd_hhmm");
        String time = sdf.format(new Date(System.currentTimeMillis()));
        isPathExist(time);
        File file = new File(  Constant.filePath+time+"/"+index+".png") ;
        try {
            FileOutputStream fos = new FileOutputStream(file) ;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把数据写入文件
            fos.flush() ;
            fos.close() ;
            Log.e(TAG, "savePic: " );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 保存图片
     * @param bitmap
     * @return
     */
    public static File savePic(Bitmap bitmap){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd_hhmm");
        String time = sdf.format(new Date(System.currentTimeMillis()));
        isPathExist(time);
        File file = new File(  Constant.filePath+time+"/"+time+".png") ;
        try {
            FileOutputStream fos = new FileOutputStream(file) ;
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把数据写入文件
            fos.flush() ;
            fos.close() ;
            Log.e(TAG, "savePic: " );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
