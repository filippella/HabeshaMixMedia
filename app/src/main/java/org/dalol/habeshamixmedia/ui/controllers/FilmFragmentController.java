package org.dalol.habeshamixmedia.ui.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.dalol.habeshamixmedia.ui.base.BaseFragmentController;
import org.dalol.habeshamixmedia.ui.features.films.FilmListFragment;
import org.dalol.habeshamixmedia.ui.features.videos.VideoListFragment;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 22/04/2018 at 21:12.
 */
public class FilmFragmentController extends BaseFragmentController {

    public static FilmFragmentController newInstance() {

        Bundle args = new Bundle();

        FilmFragmentController fragment = new FilmFragmentController();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Fragment createFragment() {
        return VideoListFragment.newInstance("Films", "UClE5X8V_7GJykqzYDARQjwg");
    }
}
