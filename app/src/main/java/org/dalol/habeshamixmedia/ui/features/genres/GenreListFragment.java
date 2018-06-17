package org.dalol.habeshamixmedia.ui.features.genres;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.features.genres.GenreListUiData;
import org.dalol.habeshamixmedia.data.model.vo.Genre;
import org.dalol.habeshamixmedia.presenter.features.genres.GenreListPresenter;
import org.dalol.habeshamixmedia.presenter.features.genres.GenreListUiView;
import org.dalol.habeshamixmedia.ui.base.BaseChildFragment;
import org.dalol.habeshamixmedia.ui.extras.GridItemsMarginDecorator;
import org.dalol.habeshamixmedia.ui.features.MainActivity;
import org.dalol.habeshamixmedia.ui.features.artists.ArtistListFragment;

import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 22/04/2018 at 21:00.
 */
public class GenreListFragment extends BaseChildFragment<GenreListPresenter> implements GenreListUiView {

    private GenreListAdapter mGenreListAdapter;

    public static GenreListFragment newInstance() {

        Bundle args = new Bundle();

        GenreListFragment fragment = new GenreListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Popular Genres");
        }
    }

    @Override
    protected GenreListPresenter onCreatePresenter() {
        return new GenreListPresenter(this, new GenreListUiData());
    }

    @Override
    protected int getContentView() {
        return R.layout.common_simple_list;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);

        Context context = getContext();
        int size = getResources().getDimensionPixelSize(R.dimen.dimen_genre_item_margin);

        RecyclerView genreList = view.findViewById(R.id.list_items);
        genreList.setHasFixedSize(true);

        genreList.addItemDecoration(new GridItemsMarginDecorator(size));
        genreList.setAdapter(mGenreListAdapter = new GenreListAdapter(context));

        mGenreListAdapter.setItemClickListener(new OnListItemClickListener<Genre>() {
            @Override
            public void onItemClick(View view, Genre model, int position, int touchType) {
                addChildFragment(ArtistListFragment.newInstance(model.getGenreName()), "TAG_GENRE_MUSIC");
            }
        });

        GenreListPresenter presenter = getPresenter();
        if (presenter != null) {
            presenter.getGenres();
        }
    }

    @Override
    public void onLoadGenres(List<Genre> genres) {
        mGenreListAdapter.setItems(genres);
    }
}
