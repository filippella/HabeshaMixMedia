package org.dalol.habeshamixmedia.ui.features.artists;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.model.vo.Artist;
import org.dalol.habeshamixmedia.ui.base.BaseListAdapter;
import org.dalol.habeshamixmedia.ui.base.BaseViewHolder;
import org.dalol.habeshamixmedia.ui.extras.AppImageLoader;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:42.
 */
public class ArtistListAdapter extends BaseListAdapter<ArtistListAdapter.ArtistViewHolder, Artist> {

    protected ArtistListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistViewHolder(getLayoutInflater().inflate(R.layout.item_genre_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    class ArtistViewHolder extends BaseViewHolder<Artist> implements View.OnClickListener {

        private final ImageView imageView;
        private final TextView txtTitle;

        ArtistViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_genre_avatar);
            txtTitle = itemView.findViewById(R.id.txtTitleGridItemXml);
            itemView.setOnClickListener(this);
        }

        @Override
        protected void bindData(Artist data) {
            super.bindData(data);
            txtTitle.setText(data.getArtistName());
            AppImageLoader.loadImage(getContext(), data.getImageUrl(), imageView);
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
