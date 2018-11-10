package com.xiaoxin.guid.search.disease.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xiaoxin.guid.bean.DiseaseDetailBean;
import com.xiaoxin.guid.search.disease.fragment.JianJieFragment;

/**
 * @author: xiaoxin
 * date: 2018/10/27
 * describe:疾病详情
 * 修改内容:
 */

public class DiseaseDetailAdapter extends FragmentPagerAdapter {

    String[] mTitles;
    private DiseaseDetailBean.DataBean.ItemsBean mItemsBean;

    public DiseaseDetailAdapter(FragmentManager fm, DiseaseDetailBean.DataBean.ItemsBean itemsBean,String... titles) {
        super(fm);
        mTitles = titles;
        mItemsBean = itemsBean;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return JianJieFragment.newInstance(mItemsBean.getArticle().get(0).getDetail());
        } else if (position == 1) {
            return JianJieFragment.newInstance(mItemsBean.getArticle().get(1).getDetail());
        }else if (position == 2) {
            return JianJieFragment.newInstance(mItemsBean.getArticle().get(2).getDetail());
        }else if (position == 3) {
            return JianJieFragment.newInstance(mItemsBean.getArticle().get(3).getDetail());
        }else if (position == 4) {
            return JianJieFragment.newInstance(mItemsBean.getArticle().get(4).getDetail());
        }else if (position == 5) {
            return JianJieFragment.newInstance(mItemsBean.getArticle().get(5).getDetail());
        }else if (position == 6) {
            return JianJieFragment.newInstance(mItemsBean.getArticle().get(6).getDetail());
        }else if (position == 7) {
            return JianJieFragment.newInstance("暂无数据");
        }else if (position == 8) {
            return JianJieFragment.newInstance("暂无数据");
        }else if (position == 9) {
            return JianJieFragment.newInstance("暂无数据");
        } else {
            return new JianJieFragment();
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
