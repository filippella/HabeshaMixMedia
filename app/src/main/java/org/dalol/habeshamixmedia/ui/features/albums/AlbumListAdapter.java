package org.dalol.habeshamixmedia.ui.features.albums;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.model.vo.Album;
import org.dalol.habeshamixmedia.ui.base.BaseListAdapter;
import org.dalol.habeshamixmedia.ui.base.BaseViewHolder;
import org.dalol.habeshamixmedia.ui.extras.AppImageLoader;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:42.
 */
public class AlbumListAdapter extends BaseListAdapter<AlbumListAdapter.AlbumViewHolder, Album> {

    AlbumListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(getLayoutInflater().inflate(R.layout.item_album_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    class AlbumViewHolder extends BaseViewHolder<Album> implements View.OnClickListener {

        private final ImageView imageView;
        private final TextView txtTitle;
        private final TextView txtTrackCount;

        AlbumViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_album_avatar);
            txtTitle = itemView.findViewById(R.id.text_album_name);
            txtTrackCount = itemView.findViewById(R.id.text_album_track_count);
            itemView.setOnClickListener(this);
        }

        @Override
        protected void bindData(Album data) {
            super.bindData(data);
            txtTitle.setText(data.getName());
            txtTrackCount.setText(String.valueOf(data.getTracksVO().getTrackList().size()));
            AppImageLoader.loadImage(getContext(), data.getImage(), imageView);
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
