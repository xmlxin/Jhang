package com.xiaoxin.guid.search.drug;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaoxin.guid.R;
import com.xiaoxin.guid.bean.search.DrugDetailBean;
import com.xiaoxin.guid.databinding.ActivityDrugDetailBinding;
import com.xiaoxin.guid.util.LoadImageUtil;

/**
 * https://blog.csdn.net/losingcarryjie/article/details/79452279
 */
public class DrugDetailActivity extends AppCompatActivity {
    ActivityDrugDetailBinding mBinding;
    private DrugDetailBean.DataBean.ItemsBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow()
                .getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_drug_detail);

        mBean = ( DrugDetailBean.DataBean.ItemsBean)
                            getIntent().getSerializableExtra("bean");

        setText();

//        overridePendingTransition(R.anim.bottom_silent,R.anim.bottom_out);
    }

    private void setText() {
        if (mBean != null) {
            mBinding.tvBrandName.setText(mBean.getBrand_name()+" "+mBean.getName());
            mBinding.tvManufacturer.setText(mBean.getManufacturer());
            mBinding.tvPackingProduct.setText(mBean.getPacking_product());
            mBinding.tvJg.setText("¥" + mBean.getUnit_price()/100.0 + "");
            LoadImageUtil.loadImage(this,mBean.getThumbnail_url(),mBinding.ivDrugImg);
            if (mBean.isPrescription()) {
                mBinding.tvCfy.setText("处方药");
            }else {
                mBinding.tvCfy.setText("非处方药");
            }

            mBinding.include.tvYpmc.setText(mBean.getName());
            mBinding.include.tvWenhao.setText(mBean.getApproval_number());
            mBinding.include.tvPinpai.setText(mBean.getBrand_name());
            mBinding.include.tvGuige.setText(mBean.getPacking_product());
            mBinding.include.tvZzs.setText(mBean.getManufacturer());
            mBinding.include.tvCf.setText(mBean.getProductmainmaterial());
            mBinding.include.tvXz.setText(mBean.getCharacters());
            mBinding.include.tvSyz.setText(mBean.getIndication());
            mBinding.include.tvYfyl.setText(mBean.getUsage());
            mBinding.include.tvBlfy.setText(mBean.getAdverse_reaction());
            mBinding.include.tvJj.setText(mBean.getContraindication());
            mBinding.include.tvZysx.setText(mBean.getNotice());
            mBinding.include.tvYwxhzy.setText(mBean.getInteraction());
            mBinding.include.tvYldl.setText(mBean.getPharmacology_and_toxicology());
            mBinding.include.tvZc.setText(mBean.getStorage());
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_bottom_silent,R.anim.push_bottom_out);
    }
}
