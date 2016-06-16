package code.fucking.cn.com.pyrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import code.fucking.cn.com.pyrecycleview.R;

/**
 * Created by zhengpeiyang on 2016/6/16.
 * mail zhengpeiyang@360.cn
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>
{
    private ArrayList<String> mDatas;
    private OnRecyclerItemClickListener mOnItemClickListener;
    private OnRecyclerItemLongClickListener mOnItemLongClickListener;
    private Context mContext;

    public HomeAdapter(Context context, ArrayList<String> data){
        mDatas = data;
        mContext = context;
    }

    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(OnRecyclerItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnRecyclerItemLongClickListener onItemLongClickListener){
        mOnItemLongClickListener = onItemLongClickListener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.recycle_item_view, parent,
                false));
        if (mOnItemClickListener != null){
            holder.setOnItemClickListener(mOnItemClickListener);
        }
        if (mOnItemLongClickListener != null){
            holder.setOnItemLongClickListener(mOnItemLongClickListener);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.tv.setText(mDatas.get(position));
        holder.tv.getLayoutParams().height = 300 + (int)(300 * Math.random());
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {

        private TextView tv;
        private OnRecyclerItemClickListener mOnItemClickListener;
        private OnRecyclerItemLongClickListener mOnItemLongClickListener;

        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void setOnItemClickListener(OnRecyclerItemClickListener onItemClickListener){
            mOnItemClickListener = onItemClickListener;
        }

        public void setOnItemLongClickListener(OnRecyclerItemLongClickListener onItemLongClickListener){
            mOnItemLongClickListener = onItemLongClickListener;
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null){
                mOnItemClickListener.onRecyclerItemClick(v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            mOnItemLongClickListener.onRecyclerItemLongClick(v, getAdapterPosition());
            return false;
        }
    }
    public interface OnRecyclerItemClickListener{
        public void onRecyclerItemClick(View view, int position);
    }

    public interface OnRecyclerItemLongClickListener{
        public void onRecyclerItemLongClick(View view, int position);
    }

}


