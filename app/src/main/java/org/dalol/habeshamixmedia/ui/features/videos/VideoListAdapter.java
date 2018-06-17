package org.dalol.habeshamixmedia.ui.features.videos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.model.vo.YoutubeVideoVO;
import org.dalol.habeshamixmedia.ui.base.BaseListAdapter;
import org.dalol.habeshamixmedia.ui.base.BaseViewHolder;
import org.dalol.habeshamixmedia.ui.extras.AppImageLoader;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sat, 16/06/2018 at 23:58.
 */
public class VideoListAdapter extends BaseListAdapter<VideoListAdapter.VideoItemViewHolder, YoutubeVideoVO> {

    VideoListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public VideoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoItemViewHolder(getLayoutInflater().inflate(R.layout.item_video_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoItemViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    class VideoItemViewHolder extends BaseViewHolder<YoutubeVideoVO> implements View.OnClickListener {

        private final ImageView videoThumbnail;
        private final TextView videoTitle;
        private final TextView videoPublishDate;
        private final TextView videoDescription;

        VideoItemViewHolder(View itemView) {
            super(itemView);
            videoThumbnail = (ImageView) itemView.findViewById(R.id.image_video_thumbnail);
            videoTitle = (TextView) itemView.findViewById(R.id.text_video_title);
            videoDescription = (TextView) itemView.findViewById(R.id.text_video_description);
            videoPublishDate = (TextView) itemView.findViewById(R.id.text_video_publish_date);
            itemView.setOnClickListener(this);
        }

        @Override
        protected void bindData(YoutubeVideoVO data) {
            super.bindData(data);
            videoTitle.setText(data.mVideoTitle);
            videoDescription.setText(data.mVideoDescription);
            videoPublishDate.setText(data.mVideoPublishDate);
            AppImageLoader.loadImage(getContext(), data.mVideoThumbnailURL, videoThumbnail);
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
