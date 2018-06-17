package org.dalol.habeshamixmedia.ui.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:43.
 */
public abstract class BaseListAdapter<VH extends RecyclerView.ViewHolder, VO> extends RecyclerView.Adapter<VH> {

    private final Context mContext;
    private final List<VO> mAllItems = new LinkedList<>();
    private final LayoutInflater mLayoutInflater;
    protected OnListItemClickListener<VO> mItemClickListener;

    protected BaseListAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public Context getContext() {
        return mContext;
    }

    protected LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }

    public void setItemClickListener(@NonNull OnListItemClickListener<VO> itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setItems(List<VO> items) {
        mAllItems.clear();
        mAllItems.addAll(items);
        notifyDataSetChanged();
    }

    public void appendItems(List<VO> items) {
        int size = mAllItems.size();
        mAllItems.addAll(items);
        notifyItemRangeInserted(size, mAllItems.size());
    }

    public void appendItems(int position, List<VO> items) {
        int size = mAllItems.size();
        mAllItems.addAll(position, items);
        notifyItemRangeInserted(size, mAllItems.size());
    }

    public void appendItem(int position, VO item) {
        mAllItems.add(position, item);
        notifyItemInserted(position);
    }

    public void appendItem(VO item) {
        mAllItems.add(item);
        notifyItemInserted(mAllItems.size());
    }

    public void clearItems() {
        mAllItems.clear();
        notifyDataSetChanged();
    }

    public VO getItem(int index) {
        return mAllItems.get(index);
    }

    public List<VO> getAllItems() {
        return mAllItems;
    }

    @Override
    public int getItemCount() {
        return mAllItems.size();
    }
}
