package test.design.prototype;

import android.os.Parcel;
import android.os.Parcelable;

public class SourceParceble implements Parcelable {
    private int id;
    private String type;
    private String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.type);
        dest.writeString(this.name);
    }

    public SourceParceble() {
    }

    protected SourceParceble(Parcel in) {
        this.id = in.readInt();
        this.type = in.readString();
        this.name = in.readString();
    }

    public static final Creator<SourceParceble> CREATOR = new Creator<SourceParceble>() {
        @Override
        public SourceParceble createFromParcel(Parcel source) {
            return new SourceParceble(source);
        }

        @Override
        public SourceParceble[] newArray(int size) {
            return new SourceParceble[size];
        }
    };
}
