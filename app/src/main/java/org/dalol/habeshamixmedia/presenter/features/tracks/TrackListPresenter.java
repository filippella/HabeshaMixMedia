package org.dalol.habeshamixmedia.presenter.features.tracks;

import android.support.annotation.NonNull;

import org.dalol.habeshamixmedia.data.features.tracks.TrackListUiData;
import org.dalol.habeshamixmedia.presenter.BasePresenter;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 10:47.
 */
public class TrackListPresenter extends BasePresenter<TrackListUiView, TrackListUiData> {

    public TrackListPresenter(@NonNull TrackListUiView uiView, @NonNull TrackListUiData uiData) {
        super(uiView, uiData);
    }
}
