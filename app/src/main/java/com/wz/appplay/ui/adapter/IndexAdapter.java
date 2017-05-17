package com.wz.appplay.ui.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wz.appplay.R;
import com.wz.appplay.bean.AppInfo;
import com.wz.appplay.bean.Banner;
import com.wz.appplay.bean.IndexBean;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wz on 17-5-16.
 */

public class IndexAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    private Context mContext;
    private LayoutInflater mInflater;
    private IndexBean mIndexBean;


    public IndexAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(IndexBean indexBean) {
        if (indexBean != null) {
            mIndexBean = indexBean;
            notifyDataSetChanged();
        }

    }

    public static final int TYPE_BANNER = 1;
    public static final int TYPE_ICON = 2;
    public static final int TYPE_APPS = 3;
    public static final int TYPE_GAMES = 4;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_BANNER) {
            return new BannerViewHolder(mInflater.inflate(R.layout.template_banner, parent, false));
        } else if (viewType == TYPE_ICON) {
            return new IconViewHolder(mInflater.inflate(R.layout.template_nav_icon, parent, false));
        } else if (viewType == TYPE_APPS) {
            return new AppViewHolder(mInflater.inflate(R.layout.template_recyleview_with_title, null, false), TYPE_APPS);
        } else if (viewType == TYPE_GAMES) {
            return new AppViewHolder(mInflater.inflate(R.layout.template_recyleview_with_title, null, false), TYPE_GAMES);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (position == 0) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;

            List<Banner> banners = mIndexBean.getBanners();
            List<String> urls = new ArrayList<>(banners.size());
            for (int i = 0; i < banners.size(); i++) {
                urls.add(banners.get(i).getThumbnail());
            }
            bannerViewHolder.mBanner.setImageLoader(new GlideImageLoader());
            bannerViewHolder.mBanner.setImages(urls);

            //mBannerViewHolder.mBanner.start();
            if (bannerViewHolder.mBanner != null) {
                if (mListener != null) {
                    mListener.changeListener(bannerViewHolder.mBanner);
                }
            }


        } else if (position == 1) {
            IconViewHolder iconViewHolder = (IconViewHolder) holder;
            iconViewHolder.mLayoutHotApp.setOnClickListener(this);
            iconViewHolder.mLayoutHotGame.setOnClickListener(this);
            iconViewHolder.mLayoutHotSubject.setOnClickListener(this);
        } else {
            AppViewHolder appViewHolder = (AppViewHolder) holder;
            AppInfoAdapter adapter = AppInfoAdapter.builder().showPosition(false).showShowBrief(true)
                    .showCategoryName(false).build();
            if (appViewHolder.type==TYPE_APPS){
                appViewHolder.mText.setText(R.string.hot_app);
                List<AppInfo> recommendApps = mIndexBean.getRecommendApps();
                adapter.addData(recommendApps);
            } else  if (appViewHolder.type==TYPE_GAMES){
                appViewHolder.mText.setText(R.string.hot_game);
                List<AppInfo> recommendGamess = mIndexBean.getRecommendGames();
                adapter.addData(recommendGamess);
            }
            ((AppViewHolder) holder).mRecyclerView.setAdapter(adapter);
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_BANNER;
        } else if (position == 1) {
            return TYPE_ICON;
        } else if (position == 2) {
            return TYPE_APPS;
        } else if (position == 3) {
            return TYPE_GAMES;
        }
        return 0;
    }

    @Override
    public void onClick(View v) {

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.banner)
        com.youth.banner.Banner mBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class IconViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.layout_hot_app)
        LinearLayout mLayoutHotApp;
        @BindView(R.id.layout_hot_game)
        LinearLayout mLayoutHotGame;
        @BindView(R.id.layout_hot_subject)
        LinearLayout mLayoutHotSubject;

        public IconViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class AppViewHolder extends RecyclerView.ViewHolder {

        int type;

        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.recycler_view)
        RecyclerView mRecyclerView;

        public AppViewHolder(View itemView, int type) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.type = type;
            initRecyclerView();
        }

        private void initRecyclerView() {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
        }
    }



    class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

    OnBannerChangeListener mListener;

    public void setOnBannerChangeListener(OnBannerChangeListener listener) {
        mListener = listener;
    }

    public interface OnBannerChangeListener {
        void changeListener(com.youth.banner.Banner banner);
    }

}
