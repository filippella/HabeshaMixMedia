package org.dalol.habeshamixmedia.ui.features.tracks;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.features.tracks.TrackListUiData;
import org.dalol.habeshamixmedia.data.model.vo.TrackVO;
import org.dalol.habeshamixmedia.data.model.vo.TracksVO;
import org.dalol.habeshamixmedia.presenter.features.tracks.TrackListPresenter;
import org.dalol.habeshamixmedia.presenter.features.tracks.TrackListUiView;
import org.dalol.habeshamixmedia.ui.base.BaseChildFragment;
import org.dalol.habeshamixmedia.ui.extras.LinearItemsMarginDecorator;
import org.dalol.habeshamixmedia.ui.features.MainActivity;
import org.dalol.habeshamixmedia.ui.features.players.YoutubeVideoPlayerFragment;
import org.dalol.habeshamixmedia.ui.widgets.HMRefreshableListLayout;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 22/04/2018 at 21:59.
 */
public class TrackListFragment extends BaseChildFragment<TrackListPresenter> implements TrackListUiView {

    private final static String ARGS_TRACK_LIST = "org.dalol.habeshamultimedia.ui.features.tracks";

    private HMRefreshableListLayout mTrackListLayout;
    private TrackListAdapter mTrackListAdapter;

    private int mArtistId;
    private String mArtistName;

    public static TrackListFragment newInstance(TracksVO trackVOS) {

        Bundle args = new Bundle();
        args.putParcelable(ARGS_TRACK_LIST, trackVOS);
        TrackListFragment fragment = new TrackListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected TrackListPresenter onCreatePresenter() {
        return new TrackListPresenter(this, new TrackListUiData());
    }

    @Override
    protected int getContentView() {
        return R.layout.common_refreshable_layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(mArtistName);
        }
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);

        setHasOptionsMenu(true);

        Context context = getContext();

        int size = getResources().getDimensionPixelSize(R.dimen.dimen_album_item_margin);

        mTrackListLayout = view.findViewById(R.id.refreshableListLayout);
        mTrackListLayout.setItemListHasFixedSize(true);
        mTrackListLayout.setItemListLayoutManager(new LinearLayoutManager(context));
        mTrackListLayout.addItemDecoration(new LinearItemsMarginDecorator(size));

        //mTrackListLayout.addOnScrollListener(loadMoreDataListener);
        mTrackListLayout.setItemListAdapter(mTrackListAdapter = new TrackListAdapter(context));
        mTrackListAdapter.setItemClickListener(new OnListItemClickListener<TrackVO>() {
            @Override
            public void onItemClick(View view, TrackVO model, int position, int touchType) {
                addChildFragment(YoutubeVideoPlayerFragment.newInstance(model.getTrackYoutubeId()), "TAG_VIDEO_PLAYER");
            }
        });


        TracksVO trackVOS = null;
        Bundle arguments = getArguments();
        if (arguments != null) {
            trackVOS = arguments.getParcelable(ARGS_TRACK_LIST);
        }

        if (trackVOS != null) {
            mTrackListAdapter.setItems(trackVOS.getTrackList());
        }

//        mAlbumListAdapter.setItemClickListener(new OnListItemClickListener<Album>() {
//            @Override
//            public void onItemClick(View view, Album model, int position, int touchType) {
//                onShowToast(model.getName());
//            }
//        });

        //Bundle arguments = arguments1;
//        if (arguments != null) {
//            mArtistName = arguments.getString(EXTRA_ARTIST_NAME);
//            mArtistId = arguments.getInt(EXTRA_ARTIST_ID, -1);
//        }

        TrackListPresenter presenter = getPresenter();
        if (presenter != null) {
            //presenter.getTracks(mArtistId);
        }

        mTrackListLayout.setItemListProgressBarVisibility(false);
    }

//    @Override
//    public void onLoadAlbums(List<Album> albums) {
//        if(albums.isEmpty()) onShowToast("Empty album!!");
//        mAlbumListAdapter.setItems(albums);
//        mAlbumListLayout.setItemListProgressBarVisibility(false);
//    }
}
