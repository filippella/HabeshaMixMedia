package org.dalol.habeshamixmedia.data.model.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 11:21.
 */
public class TracksVO implements Parcelable {

    private final List<TrackVO> mTrackVOS = new ArrayList<>();

    public TracksVO(List<TrackVO> trackVOS) {
        mTrackVOS.addAll(trackVOS);
    }

    public List<TrackVO> getTrackList() {
        return mTrackVOS;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mTrackVOS);
    }

    TracksVO(Parcel in) {
        in.readTypedList(mTrackVOS, TrackVO.CREATOR);
    }

    public static final Creator<TracksVO> CREATOR = new Creator<TracksVO>() {
        @Override
        public TracksVO createFromParcel(Parcel source) {
            return new TracksVO(source);
        }

        @Override
        public TracksVO[] newArray(int size) {
            return new TracksVO[size];
        }
    };
}
