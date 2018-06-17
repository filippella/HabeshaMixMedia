package org.dalol.habeshamixmedia.ui.features.artists;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.api.artists.ArtistListApi;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.features.artists.ArtistListUiData;
import org.dalol.habeshamixmedia.data.model.vo.Artist;
import org.dalol.habeshamixmedia.presenter.features.artists.ArtistListPresenter;
import org.dalol.habeshamixmedia.presenter.features.artists.ArtistListUiView;
import org.dalol.habeshamixmedia.ui.base.BaseChildFragment;
import org.dalol.habeshamixmedia.ui.extras.GridItemsMarginDecorator;
import org.dalol.habeshamixmedia.ui.extras.LoadMoreDataScrollListener;
import org.dalol.habeshamixmedia.ui.features.MainActivity;
import org.dalol.habeshamixmedia.ui.features.albums.AlbumListFragment;
import org.dalol.habeshamixmedia.ui.widgets.HMRefreshableListLayout;
import org.dalol.habeshamixmedia.utilities.KeyboardUtils;
import org.dalol.habeshamixmedia.utilities.TextUtils;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 22/04/2018 at 21:59.
 */
public class ArtistListFragment extends BaseChildFragment<ArtistListPresenter> implements ArtistListUiView {

    private static final String EXTRA_GENRE_NAME = "org.dalol.habeshamusic.ui.features.genres.extra.GENRE_NAME";

    private HMRefreshableListLayout mArtistListLayout;
    private ArtistListAdapter mArtistListAdapter;

    private String mGenreName;
    private int mPage;

    private String mSearchQuery;
    private boolean mResetOnCollapse;

    public static ArtistListFragment newInstance(String genreName) {

        Bundle args = new Bundle();
        args.putString(EXTRA_GENRE_NAME, genreName);
        ArtistListFragment fragment = new ArtistListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected ArtistListPresenter onCreatePresenter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.huluzefen.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return new ArtistListPresenter(this, new ArtistListUiData(retrofit.create(ArtistListApi.class)));
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
            actionBar.setTitle(mGenreName);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_genre_artists, menu);
        FragmentActivity activity = getActivity();
        SearchManager searchManager = (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        if (searchView != null && searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if (!TextUtils.isEmpty(query)) mResetOnCollapse = true;
                    searchArtist(query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    mSearchQuery = query;
                    return false;
                }
            });
            searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem menuItem) {
                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                    if (mResetOnCollapse) searchArtist("");
                    mResetOnCollapse = false;
                    return true;
                }
            });
            EditText editText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            if (editText != null) {
                addSearchActionListener(editText);
            } else {
                int searchFieldId = getResources().getIdentifier("android:id/search_src_text", null, null);
                EditText searchField = searchView.findViewById(searchFieldId);
                if (searchField != null) {
                    addSearchActionListener(searchField);
                }
            }
        }
    }

    private void addSearchActionListener(EditText searchField) {
        searchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String query = v.getText().toString();
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (!TextUtils.isEmpty(query)) mResetOnCollapse = true;
                    searchArtist(query);
                }
                return false;
            }
        });
    }

    private void searchArtist(String query) {
        KeyboardUtils.hideNativeKeyboard(getActivity(), getView());
        mSearchQuery = query;
//        loadFeeds(mSkip = 0);
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);

        setHasOptionsMenu(true);

        Context context = getContext();

        int size = getResources().getDimensionPixelSize(R.dimen.dimen_genre_item_margin);

        mArtistListLayout = view.findViewById(R.id.refreshableListLayout);
        mArtistListLayout.setItemListHasFixedSize(true);
        mArtistListLayout.setItemListLayoutManager(new GridLayoutManager(context, 2));
        mArtistListLayout.addItemDecoration(new GridItemsMarginDecorator(size));
        mArtistListLayout.addOnScrollListener(loadMoreDataListener);
        mArtistListLayout.setItemListAdapter(mArtistListAdapter = new ArtistListAdapter(context));
        mArtistListAdapter.setItemClickListener(new OnListItemClickListener<Artist>() {
            @Override
            public void onItemClick(View view, Artist model, int position, int touchType) {
                addChildFragment(AlbumListFragment.newInstance(model.getArtistName(), model.getArtistId()), "TAG_ARTIST_ALBUM");
            }
        });

        mGenreName = getArguments().getString(EXTRA_GENRE_NAME);

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(mGenreName);
        }

        mPage = 1;
        loadArtists(mPage);
    }

    private void loadArtists(int pageNumber) {
        mArtistListLayout.setItemListProgressBarVisibility(true);
        ArtistListPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.getArtists(mGenreName, pageNumber);
        }
    }

    private final LoadMoreDataScrollListener loadMoreDataListener = new LoadMoreDataScrollListener() {
        @Override
        protected void onLoadMore(RecyclerView recyclerView, LoadMoreDataScrollListener listener) {
            listener.setLoading(true);
            loadArtists(++mPage);
        }
    };

    @Override
    public void onLoadArtists(List<Artist> artists, boolean canLoadMore) {
        loadMoreDataListener.setLoading(false);
        mArtistListAdapter.appendItems(artists);
        if (!canLoadMore) mArtistListLayout.removeOnScrollListener(loadMoreDataListener);
        mArtistListLayout.setItemListProgressBarVisibility(false);
    }
}
