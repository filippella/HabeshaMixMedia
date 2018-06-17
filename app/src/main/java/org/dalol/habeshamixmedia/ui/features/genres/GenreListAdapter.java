package org.dalol.habeshamixmedia.ui.features.genres;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.model.vo.Genre;
import org.dalol.habeshamixmedia.ui.base.BaseListAdapter;
import org.dalol.habeshamixmedia.ui.base.BaseViewHolder;
import org.dalol.habeshamixmedia.ui.extras.AppImageLoader;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 18:21.
 */
public class GenreListAdapter extends BaseListAdapter<GenreListAdapter.GenreViewHolder, Genre> {

    protected GenreListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GenreViewHolder(getLayoutInflater().inflate(R.layout.item_genre_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    class GenreViewHolder extends BaseViewHolder<Genre> implements View.OnClickListener {

        private final ImageView imageView;
        private final TextView txtTitle;

        GenreViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_genre_avatar);
            txtTitle = itemView.findViewById(R.id.txtTitleGridItemXml);
            itemView.setOnClickListener(this);
        }

        @Override
        protected void bindData(Genre data) {
            super.bindData(data);
            txtTitle.setText(data.getGenreName());
            AppImageLoader.loadImage(getContext(), data.getGenreAvatar(), imageView);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                int position = getAdapterPosition();
                mItemClickListener.onItemClick(v, getItem(position), position, OnListItemClickListener.ViewTouchType.CLICK);
            }
        }
    }
}
