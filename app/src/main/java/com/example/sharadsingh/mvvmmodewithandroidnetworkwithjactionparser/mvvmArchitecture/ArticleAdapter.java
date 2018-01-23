package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.R;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.databinding.ArticleItemBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milan on 17.8.16.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.BindingHolder> {

    private List<Article> mArticles;
    private Context mContext;
    GetViewInterFace getViewInterFace ;

    public ArticleAdapter(List<Article> mArticles, Context mContext ,GetViewInterFace getViewInterFaceR) {
        this.mArticles = mArticles;
        this.mContext = mContext;
        this.getViewInterFace= getViewInterFaceR;
    }
    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ArticleItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.article_item, parent, false);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ArticleItemBinding binding = holder.binding;
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        getViewInterFace.getDataView(integers);
        getViewInterFace.SetViewData(integers);
        binding.setAvm(new ArticleViewModel(mArticles.get(position), mContext,getViewInterFace));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ArticleItemBinding binding;

        public BindingHolder(ArticleItemBinding binding) {
            super(binding.contactCard);
            this.binding = binding;
        }
    }
}
