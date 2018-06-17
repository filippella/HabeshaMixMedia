package org.dalol.habeshamixmedia.ui.features;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import org.dalol.habeshamixmedia.R;
import org.dalol.habeshamixmedia.ui.extras.GridItemsMarginDecorator;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by filippo on 13/01/2018.
 */

public class MusicDashboardActivity extends AppCompatActivity {

    private final static int SPAN_COUNT = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        RecyclerView genreList = findViewById(R.id.list_genres);
        genreList.setHasFixedSize(true);
        genreList.setLayoutManager(new GridLayoutManager(this, SPAN_COUNT));
        int size = getResources().getDimensionPixelSize(R.dimen.dimen_genre_item_margin);
        genreList.addItemDecoration(new GridItemsMarginDecorator(size));

        List<String> imageSources = new LinkedList<>();
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Newest-Playlists.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Amharic.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Oromigna.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Tigrigna.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Eritrean.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Gonder.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Gojam.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Wollo.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Ethio-Reggae.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Ethio-Traditional.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Ethiopiques.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Ethio-Jazz.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Blues.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Ethio-Folk.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Ethiopian-Somali.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Afar.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Guragigna.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Kunama.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Harari.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Tizita-Playlist.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Ethio-HipHop.jpg");



        //No Image yet
//        imageSources.add("http://www.huluzefen.com/assets/images/genres/Instrumental.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/album-no-image.png");

        imageSources.add("http://www.huluzefen.com/assets/images/genres/Spiritual.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Wedding.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Holiday.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Bands.jpg");
        imageSources.add("http://www.huluzefen.com/assets/images/genres/Classical.jpg");

        genreList.setAdapter(new MusicDashboardAdapter(imageSources));
    }

    private class MusicDashboardAdapter extends RecyclerView.Adapter<MusicDashboardAdapter.MusicDashboardViewHolder> {

        private final List<String> imageSources = new LinkedList<>();

        public MusicDashboardAdapter(List<String> imageSources) {
            this.imageSources.addAll(imageSources);
        }

        @Override
        public MusicDashboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MusicDashboardViewHolder(getLayoutInflater().inflate(R.layout.item_genre_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(MusicDashboardViewHolder holder, int position) {
            holder.bind(this.imageSources.get(position));
        }

        @Override
        public int getItemCount() {
            return this.imageSources.size();
        }

        class MusicDashboardViewHolder extends RecyclerView.ViewHolder {

            private final ImageView imageView;

            MusicDashboardViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_genre_avatar);
            }

            void bind(String source) {
                Glide.with(itemView.getContext().getApplicationContext())
                        .load(source)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                        .apply(RequestOptions.placeholderOf(R.color.colorLightBlue))
                        .into(imageView);
            }
        }
    }
}
