package org.dalol.habeshamixmedia.ui.features.tracks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.model.vo.TrackVO;
import org.dalol.habeshamixmedia.ui.base.BaseListAdapter;
import org.dalol.habeshamixmedia.ui.base.BaseViewHolder;
import org.dalol.habeshamixmedia.ui.extras.AppImageLoader;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 09/06/2018 at 16:42.
 */
public class TrackListAdapter extends BaseListAdapter<TrackListAdapter.TrackViewHolder, TrackVO> {

    TrackListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrackViewHolder(getLayoutInflater().inflate(R.layout.item_video_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    class TrackViewHolder extends BaseViewHolder<TrackVO> implements View.OnClickListener {

        private final ImageView videoThumbnail;
        private final TextView videoTitle;
        private final TextView videoPublishDate;
        private final TextView videoDescription;

        TrackViewHolder(View itemView) {
            super(itemView);
            videoThumbnail = (ImageView) itemView.findViewById(R.id.image_video_thumbnail);
            videoTitle = (TextView) itemView.findViewById(R.id.text_video_title);
            videoDescription = (TextView) itemView.findViewById(R.id.text_video_description);
            videoPublishDate = (TextView) itemView.findViewById(R.id.text_video_publish_date);
            itemView.setOnClickListener(this);
        }

        @Override
        protected void bindData(TrackVO data) {
            super.bindData(data);
            videoTitle.setText(data.getTrackName());
            videoDescription.setText("Description goes here");
            videoPublishDate.setText("Published date goes here");
            AppImageLoader.loadImage(getContext(), data.getAlbumImage(), videoThumbnail);
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
