package com.xiaoxin.library.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.xiaoxin.library.R;

/**
 * @author xiaoxin
 * @date 2018-05-16
 * @describe: 加载图片工具类
 */

public class LoadImageUtil {

    private static RequestOptions getOps(Context context) {

        return new RequestOptions()
//                .placeholder(R.drawable.ic_pic_n)
//                .error(R.drawable.ic_pic_n)     // TODO: 2018/5/16  这里发版需要修改，因为暂时没有图片
                .priority(Priority.HIGH);
    }

    /**
     * 加载缩略图
     *
     * @param ctx
     * @param path
     * @param imageView
     */
    public static void loadImage(Context ctx, String path, ImageView imageView) {

        Glide.with(ctx).load(path).apply(getOps(ctx)).into(imageView);
    }

    /**
     * 加载缩略图
     *
     * @param ctx
     * @param drawable
     * @param imageView
     */
    public static void loadImage(Context ctx, Drawable drawable, ImageView imageView) {

        Glide.with(ctx).load(drawable).apply(getOps(ctx)).into(imageView);
    }



    /**
     * 加载药店图片
     * @param ctx
     * @param drawable
     * @param imageView
     */
    public static void loadDrugStore(Context ctx, String drawable, ImageView imageView) {
        //设置图片圆角角度
//        RoundedCorners roundedCorners= new RoundedCorners(20);
//          .bitmapTransform(roundedCorners)
//         RequestOptions options = new RequestOptions()
//                .placeholder(R.drawable.yaodian_img) //加载成功前显示的页面
//                .error(R.drawable.yaodian_img) //异常显示的图片
//                 .fallback(R.drawable.yaodian_img) //url为空显示的图片
//                 .transform(new GlideRoundTransform(DisplayUtil.dip2px(ctx,5)))
//                .priority(Priority.HIGH);
//        Glide.with(ctx).load(drawable).apply(options).into(imageView);
    }


    /**
     * 加载图片,不设置默认图片
     *
     * @param ctx
     * @param path
     * @param imageView
     */
    public static void loadImageService(Context ctx, String path, ImageView imageView) {

        Glide.with(ctx).load(path).into(imageView);
    }

    public static void loadGif(Context ctx,ImageView imageView) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(ctx)
                .asGif()
//                .load(R.drawable.loading)
                .apply(options)
                .into(imageView);

    }

}
