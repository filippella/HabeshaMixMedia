package org.dalol.habeshamixmedia.data.model.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Filippo
 * @version 1.0.0
 * @since Sun, 17/06/2018 at 11:00.
 */
public class TrackVO implements Parcelable {

    private final String mAlbumName;
    private final String mAlbumImage;
    private final String mTrackName;
    private final long mTrackDuration;
    private final String mTrackYoutubeId;

    public TrackVO(String albumName, String albumImage, String trackName, long trackDuration, String trackYoutubeId) {
        mAlbumName = albumName;
        mAlbumImage = albumImage;
        mTrackName = trackName;
        mTrackDuration = trackDuration;
        mTrackYoutubeId = trackYoutubeId;
    }

    public String getAlbumImage() {
        return mAlbumImage;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public long getTrackDuration() {
        return mTrackDuration;
    }

    public String getTrackName() {
        return mTrackName;
    }

    public String getTrackYoutubeId() {
        return mTrackYoutubeId;
    }

    TrackVO(Parcel source) {
        mAlbumName = source.readString();
        mAlbumImage = source.readString();
        mTrackName = source.readString();
        mTrackDuration = source.readLong();
        mTrackYoutubeId = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mAlbumName);
        dest.writeString(mAlbumImage);
        dest.writeString(mTrackName);
        dest.writeLong(mTrackDuration);
        dest.writeString(mTrackYoutubeId);
    }

    public static final Creator<TrackVO> CREATOR = new Creator<TrackVO>() {

        @Override
        public TrackVO createFromParcel(Parcel source) {
            return new TrackVO(source);
        }

        @Override
        public TrackVO[] newArray(int size) {
            return new TrackVO[size];
        }
    };
}
