package com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.mvvmArchitecture.citystatelistviewmvvm;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.R;
import com.example.sharadsingh.mvvmmodewithandroidnetworkwithjactionparser.databinding.CitylistItemBinding;
import java.util.List;

/**
 * Created by milan on 17.8.16.
 */
public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.BindingHolder> {
    private List<CityListModel> mArticles;
    private Context mContext;

    public CityListAdapter(List<CityListModel> mArticles, Context mContext ) {
        this.mArticles = mArticles;
        this.mContext = mContext;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CitylistItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.citylist_item, parent, false);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        CitylistItemBinding binding = holder.binding;
        binding.setData(new CityListViewModel(mArticles.get(position), mContext));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private CitylistItemBinding binding;
        public BindingHolder(CitylistItemBinding binding) {
            super(binding.contactCard);
            this.binding = binding;
        }
    }
}