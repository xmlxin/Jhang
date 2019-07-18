package com.xiaoxin.guid.search.drug;

import android.content.Context;
import androidx.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.search.DrugDetailBean;
import com.xiaoxin.guid.util.LoadImageUtil;

import java.util.List;


/**
 * @author: xiaoxin
 * date: 2018/10/29
 * describe:药品列表详情
 * 修改内容:
 */

public class DrugListDetailAdapter extends BaseQuickAdapter<DrugDetailBean.DataBean.ItemsBean, BaseViewHolder> {

    private Context mContext;
    private ImageView mAddDrug;
    private ImageView mCutDrug;
    private TextView mTvCount;

    public DrugListDetailAdapter(int layoutResId, @Nullable List<DrugDetailBean.DataBean.ItemsBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugDetailBean.DataBean.ItemsBean item) {

        helper.setText(R.id.tv_brand_name,item.getBrand_name()+" "+item.getName())
                .setText(R.id.tv_manufacturer,item.getManufacturer()+"")
                .setText(R.id.tv_packing_product,item.getPacking_product()+"")
                .setText(R.id.tv_unit_price,"¥ "+item.getUnit_price()/100.0)
                .addOnClickListener(R.id.iv_add_drug)
                .addOnClickListener(R.id.iv_cut_drug);

        if (item.isPrescription()) {
            helper.setVisible(R.id.tv_cfy,true);
        }else {
            helper.setVisible(R.id.tv_cfy,false);
        }
        LoadImageUtil.loadImage(mContext,item.getThumbnail_url(),helper.getView(R.id.iv_drug_img));

//        //条目错乱
//        mAddDrug = helper.getView(R.id.iv_add_drug);
//        mCutDrug = helper.getView(R.id.iv_cut_drug);
//        mTvCount = helper.getView(R.id.tv_count);
//        if(item.check) {
//            mCutDrug.setAlpha(1f);
//            mTvCount.setVisibility(View.VISIBLE);
//        }else {
//            mCutDrug.setAlpha(0f);
//            mTvCount.setVisibility(View.GONE);
//        }
//
//        mAddDrug.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                item.check = true;
//                Toast.makeText(mContext,"sdsd",Toast.LENGTH_SHORT).show();
//                ViewAnimator.animate(mCutDrug)
//                        .translationX(mAddDrug.getLeft() - mCutDrug.getLeft(), 0)
//                        .rotation(360)
//                        .alpha(0, 255)
//                        .duration(300)
//                        .interpolator(new DecelerateInterpolator())
//                        .andAnimate(mTvCount)
//                        .translationX(mAddDrug.getLeft() - mTvCount.getLeft(), 0)
//                        .rotation(360)
//                        .alpha(0, 255)
//                        .interpolator(new DecelerateInterpolator())
//                        .duration(300)
//                        .start();
//            }
//        });
//
//        mCutDrug.setOnClickListener(v ->{
//            item.check = false;
//            subAnim();
//        });
//
    }
//
//    private void subAnim(){
//        ViewAnimator.animate(mCutDrug)
//                .translationX(0, mAddDrug.getLeft() - mCutDrug.getLeft())
//                .rotation(-360)
//                .alpha(255, 0)
//                .duration(300)
//                .interpolator(new AccelerateInterpolator())
//                .andAnimate(mTvCount)
//                .onStop(new AnimationListener.Stop() {
//                    @Override
//                    public void onStop() {
////                        mButton.expendAnim();
////                        if (circle_anim) {
////                        }
//                    }
//                })
//                .translationX(0, mAddDrug.getLeft() - mTvCount.getLeft())
//                .rotation(-360)
//                .alpha(255, 0)
//                .interpolator(new AccelerateInterpolator())
//                .duration(300)
//                .start();
//    }

}
