package org.dalol.habeshamixmedia.ui.features.videos;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.api.videos.YoutubeApi;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.features.videos.VideoListUiData;
import org.dalol.habeshamixmedia.data.model.vo.VideosVO;
import org.dalol.habeshamixmedia.data.model.vo.YoutubeVideoVO;
import org.dalol.habeshamixmedia.presenter.features.videos.VideoListPresenter;
import org.dalol.habeshamixmedia.presenter.features.videos.VideoListUiView;
import org.dalol.habeshamixmedia.ui.base.BaseChildFragment;
import org.dalol.habeshamixmedia.ui.extras.LinearItemsMarginDecorator;
import org.dalol.habeshamixmedia.ui.extras.LoadMoreDataScrollListener;
import org.dalol.habeshamixmedia.ui.features.players.YoutubeVideoPlayerFragment;
import org.dalol.habeshamixmedia.ui.widgets.HMRefreshableListLayout;
import org.dalol.habeshamixmedia.utilities.TextUtils;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 22/04/2018 at 21:00.
 */
public class VideoListFragment extends BaseChildFragment<VideoListPresenter> implements VideoListUiView {

    private static final String ARGS_TITLE = "org.dalol.habeshamixmedia.ui.features.videos.args.TITLE";
    private static final String ARGS_CHANNEL_ID = "org.dalol.habeshamixmedia.ui.features.videos.args.CHANNEL_ID";

    private HMRefreshableListLayout mVideoListLayout;
    private VideoListAdapter mVideoListAdapter;
    private String mTitle;
    private String mNextPageToken;
    private String mChannelId;

    public static VideoListFragment newInstance(String title, String channelId) {

        Bundle args = new Bundle();
        args.putString(ARGS_TITLE, title);
        args.putString(ARGS_CHANNEL_ID, channelId);
        VideoListFragment fragment = new VideoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(mTitle);
    }

    @Override
    protected VideoListPresenter onCreatePresenter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return new VideoListPresenter(this, new VideoListUiData(retrofit.create(YoutubeApi.class)));
    }

    @Override
    protected int getContentView() {
        return R.layout.common_refreshable_layout;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);

        Context context = getContext();
        int size = getResources().getDimensionPixelSize(R.dimen.dimen_video_item_margin);

        mVideoListLayout = view.findViewById(R.id.refreshableListLayout);
        mVideoListLayout.setItemListHasFixedSize(true);
        mVideoListLayout.setItemListLayoutManager(new LinearLayoutManager(context));
        mVideoListLayout.addItemDecoration(new LinearItemsMarginDecorator(size));
        mVideoListLayout.addOnScrollListener(loadMoreDataListener);
        mVideoListLayout.setItemListAdapter(mVideoListAdapter = new VideoListAdapter(context));
        mVideoListAdapter.setItemClickListener(new OnListItemClickListener<YoutubeVideoVO>() {
            @Override
            public void onItemClick(View view, YoutubeVideoVO model, int position, int touchType) {
                addChildFragment(YoutubeVideoPlayerFragment.newInstance(model.mVideoId), "TAG_VIDEO_PLAYER");
            }
        });

        mVideoListLayout.setItemListProgressBarVisibility(true);

        VideoListPresenter presenter = getPresenter();

        Bundle arguments = getArguments();
        mTitle = arguments.getString(ARGS_TITLE);
        mChannelId = arguments.getString(ARGS_CHANNEL_ID);
        if (mChannelId != null && presenter != null) {

            String hopeMusic = "hoplessable";
            String hopeMusicChannel = "UCgdecrMD1EfiqFL_jlnPxvg";
            String myChannelName = "UCaJhhhbN8cxAk05QqraQPxA";
            String amharicLyrics = "AhaduWubuZewdie";
            String amharicFilms = "UClE5X8V_7GJykqzYDARQjwg";

            presenter.getVideoList(mChannelId);
            //presenter.getChannelVideos(hopeMusic);
        }
    }

    private final LoadMoreDataScrollListener loadMoreDataListener = new LoadMoreDataScrollListener() {
        @Override
        protected void onLoadMore(RecyclerView recyclerView, LoadMoreDataScrollListener listener) {
            listener.setLoading(true);
            VideoListPresenter presenter = getPresenter();
            if (presenter != null && !TextUtils.isEmpty(mNextPageToken)) {
                presenter.getNextVideoList(mChannelId, mNextPageToken);
            }
        }
    };

    @Override
    public void onLoadVideoList(VideosVO videoVOS) {
        loadMoreDataListener.canLoadMore(TextUtils.isEmpty(videoVOS.getNextPageToken()));
        loadMoreDataListener.setLoading(false);
        mNextPageToken = videoVOS.getNextPageToken();
        mVideoListAdapter.appendItems(videoVOS.getVideoVOS());
        mVideoListLayout.setItemListProgressBarVisibility(false);
    }
}
