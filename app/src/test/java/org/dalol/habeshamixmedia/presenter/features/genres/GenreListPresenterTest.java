package org.dalol.habeshamixmedia.presenter.features.genres;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.dalol.habeshamixmedia.common.rx.AbstractCallable;
import org.dalol.habeshamixmedia.data.api.artists.ArtistListApi;
import org.dalol.habeshamixmedia.data.features.artists.ArtistListUiData;
import org.dalol.habeshamixmedia.data.features.genres.GenreListUiData;
import org.dalol.habeshamixmedia.data.model.vo.Artist;
import org.dalol.habeshamixmedia.data.model.vo.Genre;
import org.dalol.habeshamixmedia.data.model.vo.GenreArtists;
import org.dalol.habeshamixmedia.utilities.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 10/06/2018 at 22:31.
 */
public class GenreListPresenterTest {

    final OkHttpClient client = new OkHttpClient();
    private ArtistListUiData data;

    @Before
    public void setUp() throws Exception {
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://www.huluzefen.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        data = new ArtistListUiData(retrofit1.create(ArtistListApi.class));
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void processGenres() throws Exception {
        String json = FileUtils.readAsString("/Users/filippo/Desktop/habesha_mix.json");
        List<GenreArtists> genreArtists = new Gson().fromJson(json, new TypeToken<List<GenreArtists>>(){}.getType());
        Map<String, List<GenreArtists>> genresMap = new WeakHashMap<>();
        for (GenreArtists genreArtist : genreArtists) {
            String genreName = genreArtist.getGenreName();
            if (!genresMap.containsKey(genreName)) {
                genresMap.put(genreName, new ArrayList<GenreArtists>());
            }
            genresMap.get(genreName).add(genreArtist);
        }
        String jsonMap = new Gson().toJson(genresMap);
        System.out.println(genresMap.size());
    }


    @Test
    public void getAllGenres() throws Exception {
        new GenreListUiData().getGenres().toObservable()
                .flatMap(new Function<List<Genre>, Observable<GenreArtists>>() {
                    @Override
                    public Observable<GenreArtists> apply(List<Genre> genres) throws Exception {
                        return Observable.fromIterable(genres)
                                .flatMap(new Function<Genre, ObservableSource<GenreArtists>>() {
                                    @Override
                                    public ObservableSource<GenreArtists> apply(final Genre genre) throws Exception {
                                        List<Integer> count = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
                                        return Observable.fromIterable(count)
                                                .flatMap(new Function<Integer, ObservableSource<GenreArtists>>() {
                                                    @Override
                                                    public ObservableSource<GenreArtists> apply(Integer integer) throws Exception {
                                                        System.out.println("getArtists -> " + integer);
                                                        return data.getArtists(genre.getGenreName(), integer).toObservable();
                                                    }
                                                });
                                    }
                                });
                    }
                })
                .toList()
                .subscribeWith(new DisposableSingleObserver<List<GenreArtists>>() {
                    @Override
                    public void onSuccess(List<GenreArtists> genreArtists) {
                        String json = new Gson().toJson(genreArtists);
                        System.out.println(json);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Test
    public void getGenres() {


        //List<Integer> count = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);


        new GenreListUiData().getGenres().toObservable()
                .flatMap(new Function<List<Genre>, Observable<GenreArtists>>() {
                    @Override
                    public Observable<GenreArtists> apply(List<Genre> genres) throws Exception {
                        return Observable.fromIterable(genres)
                                .flatMap(new Function<Genre, ObservableSource<GenreArtists>>() {
                                    @Override
                                    public ObservableSource<GenreArtists> apply(final Genre genre) throws Exception {
                                        List<Integer> count = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
                                        return Observable.fromIterable(count)
                                                .flatMap(new Function<Integer, ObservableSource<GenreArtists>>() {
                                                    @Override
                                                    public ObservableSource<GenreArtists> apply(Integer integer) throws Exception {
                                                        return data.getArtists(genre.getGenreName(), integer).toObservable();
                                                    }
                                                });
                                    }
                                });
                    }
                })
                .flatMap(new Function<GenreArtists, ObservableSource<GenreArtists>>() {
                    @Override
                    public ObservableSource<GenreArtists> apply(GenreArtists genreArtists) throws Exception {
                        return Observable.fromCallable(new AbstractCallable<GenreArtists, GenreArtists>(genreArtists) {
                            @Override
                            protected GenreArtists call(GenreArtists subject) throws Exception {
                                List<Artist> artists = subject.getArtists();
                                String genreName = subject.getGenreName();

                                String genrePath = "/Users/filippo/Documents/HABESHA-MULTIMEDIA/" + genreName;

                                //FileUtils.createDirectory(genrePath);

                                for (Artist artist : artists) {
                                    String artistName = artist.getArtistName();

                                    if ("Kassa Tessema".equals(artistName)) {
                                        int x = 0;
                                    }

                                    String path = genrePath + "/" + artistName;

                                    //FileUtils.createDirectory(path);

                                    try {
//                                        Request request = new Request.Builder()
//                                                .url(artist.getImageUrl())
//                                                .build();

                                        //Response response = client.newCall(request).execute();

                                        //InputStream inputStream = response.body().byteStream();

                                        //String fileName = new File(artist.getImageUrl()).getName();

                                        //FileUtils.createFile(inputStream, new File(path + "/"+ fileName));

                                        //response.close();
                                    } catch (Exception e) {
                                        System.out.println("Artis Name -> " + artistName + ", imageURL -> " + artist.getImageUrl());
                                        e.printStackTrace();
                                    }


                                    System.out.println(artistName);
                                }
                                System.out.println(artists.size());
                                return subject;
                            }
                        });
                    }
                })
                .subscribe(new Consumer<GenreArtists>() {
                    @Override
                    public void accept(GenreArtists genreArtists) throws Exception {
                        //List<Artist> artists = genreArtists.getArtists();
                        String genreName = genreArtists.getGenreName();
                        System.out.println("Done with -> " + genreName);
//                        FileUtils.createDirectory("/Users/filippo/Documents/HABESHA-MULTIMEDIA/", genreName);
//                        for (Artist artist : artists) {
//                            FileUtils.createDirectory("/Users/filippo/Documents/HABESHA-MULTIMEDIA/" + genreName + "/", artist.getArtistName());
//                            System.out.println(artist.getArtistName());
//                        }
//                        System.out.println(artists.size());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getMessage());
                    }
                });

//        Observable.fromIterable(count)
//                .flatMap(new Function<Integer, ObservableSource<GenreArtists>>() {
//                    @Override
//                    public ObservableSource<GenreArtists> apply(Integer integer) throws Exception {
//                        return data.getArtists("Amharic", integer).toObservable();
//                    }
//                })
//                .subscribe(new Consumer<GenreArtists>() {
//                    @Override
//                    public void accept(GenreArtists genreArtists) throws Exception {
//                        List<Artist> artists = genreArtists.getArtists();
//                        String genreName = genreArtists.getGenreName();
//                        FileUtils.createDirectory("/Users/filippo/Documents/HABESHA-MULTIMEDIA/", genreName);
//                        for (Artist artist : artists) {
//                            FileUtils.createDirectory("/Users/filippo/Documents/HABESHA-MULTIMEDIA/"+ genreName +"/", artist.getArtistName());
//                            System.out.println(artist.getArtistName());
//                        }
//                        System.out.println(artists.size());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        System.out.println(throwable.getMessage());
//                    }
//                });

//        data.getArtists("Amharic", 1)
//                .subscribeWith(new DisposableSingleObserver<GenreArtists>() {
//                    @Override
//                    public void onSuccess(GenreArtists genreArtists) {
//                        List<Artist> artists = genreArtists.getArtists();
//                        for (Artist artist : artists) {
//                            System.out.println(artist.getArtistName());
//                        }
//                        System.out.println(artists.size());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        System.out.println(e.getMessage());
//                    }
//                });
    }
}