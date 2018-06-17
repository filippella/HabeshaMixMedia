package org.dalol.habeshamixmedia.ui.features.albums;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.api.artists.ArtistAlbumApi;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.features.albums.AlbumListUiData;
import org.dalol.habeshamixmedia.data.model.vo.Album;
import org.dalol.habeshamixmedia.presenter.features.albums.AlbumListPresenter;
import org.dalol.habeshamixmedia.presenter.features.albums.AlbumListUiView;
import org.dalol.habeshamixmedia.ui.base.BaseChildFragment;
import org.dalol.habeshamixmedia.ui.extras.LinearItemsMarginDecorator;
import org.dalol.habeshamixmedia.ui.features.tracks.TrackListFragment;
import org.dalol.habeshamixmedia.ui.widgets.HMRefreshableListLayout;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 22/04/2018 at 21:59.
 */
public class AlbumListFragment extends BaseChildFragment<AlbumListPresenter> implements AlbumListUiView {

    private static final String EXTRA_ARTIST_ID = "org.dalol.habeshamusic.ui.features.albums.extra.ARTIST_ID";
    private static final String EXTRA_ARTIST_NAME = "org.dalol.habeshamusic.ui.features.albums.extra.ARTIST_NAME";

    private HMRefreshableListLayout mAlbumListLayout;
    private AlbumListAdapter mAlbumListAdapter;

    private int mArtistId;
    private String mArtistName;

    public static AlbumListFragment newInstance(String artistName, int artistId) {

        Bundle args = new Bundle();
        args.putString(EXTRA_ARTIST_NAME, artistName);
        args.putInt(EXTRA_ARTIST_ID, artistId);
        AlbumListFragment fragment = new AlbumListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected AlbumListPresenter onCreatePresenter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.huluzefen.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return new AlbumListPresenter(this, new AlbumListUiData(retrofit.create(ArtistAlbumApi.class)));
    }

    @Override
    protected int getContentView() {
        return R.layout.common_refreshable_layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle(mArtistName);
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);

        setHasOptionsMenu(true);

        Context context = getContext();

        int size = getResources().getDimensionPixelSize(R.dimen.dimen_album_item_margin);

        mAlbumListLayout = view.findViewById(R.id.refreshableListLayout);
        mAlbumListLayout.setItemListHasFixedSize(true);
        mAlbumListLayout.setItemListLayoutManager(new LinearLayoutManager(context));
        mAlbumListLayout.addItemDecoration(new LinearItemsMarginDecorator(size));
        //mAlbumListLayout.addOnScrollListener(loadMoreDataListener);
        mAlbumListLayout.setItemListAdapter(mAlbumListAdapter = new AlbumListAdapter(context));
        mAlbumListAdapter.setItemClickListener(new OnListItemClickListener<Album>() {
            @Override
            public void onItemClick(View view, Album model, int position, int touchType) {
                addChildFragment(TrackListFragment.newInstance(model.getTracksVO()), "TAG_TRACK_LIST");
                onShowToast(model.getName());
            }
        });

        Bundle arguments = getArguments();
        if (arguments != null) {
            mArtistName = arguments.getString(EXTRA_ARTIST_NAME);
            mArtistId = arguments.getInt(EXTRA_ARTIST_ID, -1);
        }

        AlbumListPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.getAlbums(mArtistId);
        }

        mAlbumListLayout.setItemListProgressBarVisibility(true);
    }

    @Override
    public void onLoadAlbums(List<Album> albums) {
        if(albums.isEmpty()) onShowToast("Empty album!!");
        mAlbumListAdapter.setItems(albums);
        mAlbumListLayout.setItemListProgressBarVisibility(false);
    }
}
