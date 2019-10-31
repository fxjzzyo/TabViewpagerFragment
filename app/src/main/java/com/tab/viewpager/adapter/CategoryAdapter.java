package com.tab.viewpager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.tab.viewpager.R;
import com.tab.viewpager.bean.Category;
import com.tab.viewpager.fragment.ContentFragment;

import java.util.List;

/**
 * Created by fanlulin on 2019-07-18.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<Category> mDatas;

    public CategoryAdapter(Context context, List<Category> datas){
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.category_item_layout,parent,
                false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Category category = mDatas.get(position);

        holder.aSwitch.setText(category.getTitle());
        holder.aSwitch.setChecked(category.isShow());
        if(onItemCheckListener!=null){
            holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    onItemCheckListener.onCheckedChanged(holder.getLayoutPosition(),b);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        Log.d("tag","---itemcount--"+mDatas.size());
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        Switch aSwitch;

        public ViewHolder(View itemView) {
            super(itemView);
            aSwitch = itemView.findViewById(R.id.switch_item);
        }
    }

    public interface IOnItemCheckListener{
        void onCheckedChanged(int position,boolean checked);
    }

    private IOnItemCheckListener onItemCheckListener;

    public void setOnItemCheckListener(IOnItemCheckListener onItemCheckListener) {
        this.onItemCheckListener = onItemCheckListener;
    }
}
