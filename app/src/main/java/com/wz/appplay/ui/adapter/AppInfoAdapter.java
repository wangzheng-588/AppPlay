package com.wz.appplay.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wz.appplay.R;
import com.wz.appplay.bean.AppInfo;
import com.wz.appplay.common.Constant;


/**
 * Created by wz on 17-5-16.
 */

public class AppInfoAdapter extends BaseQuickAdapter<AppInfo,BaseViewHolder> {
    private  Builder mBuilder;

    private AppInfoAdapter(Builder builder) {
        super(R.layout.template_appinfo);
        mBuilder = builder;
    }

    public static Builder builder(){
        return new Builder();
    }

    @Override
    protected void convert(BaseViewHolder helper, AppInfo item) {
        Glide.with(mContext).load(Constant.BASE_IMG_URL+item.getIcon()).into((ImageView) helper.getView(R.id.img_app_icon));
        helper.setText(R.id.txt_app_name,item.getDisplayName());
        helper.setText(R.id.txt_brief,item.getLevel1CategoryName());
       // helper.setText(R.id.txt_category,item.getBriefShow());

        TextView textViewPosition = helper.getView(R.id.txt_position);
        textViewPosition.setVisibility(mBuilder.isShowposition? View.VISIBLE:View.GONE);
        textViewPosition.setText(String.valueOf(item.getPosition()+1));

        TextView textViewCategory = helper.getView(R.id.txt_category);
        textViewCategory.setVisibility(mBuilder.isShowposition? View.VISIBLE:View.GONE);
        textViewCategory.setText(item.getLevel1CategoryName());

        TextView textViewBrief = helper.getView(R.id.txt_brief);
        textViewBrief.setVisibility(mBuilder.isShowposition? View.VISIBLE:View.GONE);
        textViewBrief.setText(item.getBriefShow());




    }


    public static class Builder{

        private boolean isShowposition;
        private boolean isShowCategoryName;
        private boolean isShowBrief;


        public Builder showPosition(boolean b){
            this.isShowposition = b;
            return this;
        }

        public Builder showCategoryName(boolean b){
            this.isShowCategoryName = b;
            return this;
        }

        public Builder showShowBrief(boolean b){
            this.isShowBrief = b;
            return this;
        }

        public AppInfoAdapter build(){
            return new AppInfoAdapter(this);
        }
    }
}
