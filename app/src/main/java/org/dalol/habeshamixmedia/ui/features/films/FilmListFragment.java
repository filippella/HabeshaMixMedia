package org.dalol.habeshamixmedia.ui.features.films;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.data.callback.OnListItemClickListener;
import org.dalol.habeshamixmedia.data.model.vo.YoutubeVideoVO;
import org.dalol.habeshamixmedia.ui.base.BaseChildFragment;
import org.dalol.habeshamixmedia.ui.extras.LinearItemsMarginDecorator;
import org.dalol.habeshamixmedia.ui.features.players.YoutubeVideoPlayerFragment;
import org.dalol.habeshamixmedia.ui.widgets.HMRefreshableListLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 13:19.
 */
public class FilmListFragment extends BaseChildFragment {

    private HMRefreshableListLayout mFilmListLayout;
    private FilmListAdapter mFilmListAdapter;

    public static FilmListFragment newInstance() {

        Bundle args = new Bundle();

        FilmListFragment fragment = new FilmListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentView() {
        return R.layout.common_refreshable_layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        setTitle("Films");
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);

        Context context = getContext();
        int size = getResources().getDimensionPixelSize(R.dimen.dimen_video_item_margin);

        mFilmListLayout = view.findViewById(R.id.refreshableListLayout);
        mFilmListLayout.setItemListHasFixedSize(true);
        mFilmListLayout.setItemListLayoutManager(new LinearLayoutManager(context));
        mFilmListLayout.addItemDecoration(new LinearItemsMarginDecorator(size));
//        mFilmListLayout.addOnScrollListener(loadMoreDataListener);
        mFilmListLayout.setItemListAdapter(mFilmListAdapter = new FilmListAdapter(context));
        mFilmListAdapter.setItemClickListener(new OnListItemClickListener<YoutubeVideoVO>() {
            @Override
            public void onItemClick(View view, YoutubeVideoVO model, int position, int touchType) {
                addChildFragment(YoutubeVideoPlayerFragment.newInstance(model.mVideoId), "TAG_VIDEO");
            }
        });
//        mVideoListAdapter.setItemClickListener(new OnListItemClickListener<YoutubeVideoVO>() {
//            @Override
//            public void onItemClick(View view, YoutubeVideoVO model, int position, int touchType) {
//                addChildFragment(YoutubeVideoPlayerFragment.newInstance(model.mVideoId), "TAG_VIDEO_PLAYER");
//            }
//        });

        List<YoutubeVideoVO> videoVOS = new ArrayList<>();
        videoVOS.add(new YoutubeVideoVO("TjXSLvDzBUY", "ጀማሪ ሌባ JEMARI LEBA", "", "", "https://i.ytimg.com/vi/TjXSLvDzBUY/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLAGdWyLcnyM-eTYYgkm_KWIzottaA"));
        videoVOS.add(new YoutubeVideoVO("0_etS3Xevf0", "ዘመዴ ነሽ - Zemede Nesh", "", "", "https://i.ytimg.com/vi/0_etS3Xevf0/hqdefault.jpg?sqp=-oaymwEZCPYBEIoBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLApCzLrPbhZ8re0-wdLik7kG5jqSA"));
        //only for funny clip
        videoVOS.add(new YoutubeVideoVO("T8zwzvpNgiE", "[AASTU]Vine2☼ANJONEYO", "", "", "https://i.ytimg.com/an_webp/T8zwzvpNgiE/mqdefault_6s.webp?du=3000&sqp=CKismdkF&rs=AOn4CLAJwRLvgBiYlVDt0Lm_vIYGVsFVeA"));
        videoVOS.add(new YoutubeVideoVO("6GH-wdyrkZo", "Top Ethiopian in America crazy moments", "", "", "https://i.ytimg.com/vi/6GH-wdyrkZo/hqdefault.jpg?sqp=-oaymwEZCNACELwBSFXyq4qpAwsIARUAAIhCGAFwAQ==&rs=AOn4CLAFh5qPaEFQSUzrjhzbRd1oK_B0EA"));
        mFilmListAdapter.setItems(videoVOS);
        mFilmListLayout.setItemListProgressBarVisibility(false);
    }
}
