package org.dalol.habeshamixmedia.ui.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.dalol.habeshamixmedia.ui.base.BaseFragmentController;
import org.dalol.habeshamixmedia.ui.features.videos.VideoListFragment;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 22/04/2018 at 21:12.
 */
public class VideoFragmentController extends BaseFragmentController {

    public static VideoFragmentController newInstance() {

        Bundle args = new Bundle();

        VideoFragmentController fragment = new VideoFragmentController();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Fragment createFragment() {
        return VideoListFragment.newInstance("Music Videos", "UCgdecrMD1EfiqFL_jlnPxvg");
    }
}
