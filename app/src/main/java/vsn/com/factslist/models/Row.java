package vsn.com.factslist.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.SerializedName;

public class Row implements Serializable, Parcelable
{

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("imageHref")
    private Object imageHref;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<Row> CREATOR = new Creator<Row>() {


        public Row createFromParcel(Parcel in) {
            return new Row(in);
        }

        public Row[] newArray(int size) {
            return (new Row[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7630623072078013502L;

    protected Row(Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.imageHref = ((Object) in.readValue((Object.class.getClassLoader())));
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public Row() {
    }

    @SerializedName("title")
    public String getTitle() {
        return title;
    }

    @SerializedName("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("description")
    public String getDescription() {
        return description;
    }

    @SerializedName("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("imageHref")
    public Object getImageHref() {
        return imageHref;
    }

    @SerializedName("imageHref")
    public void setImageHref(Object imageHref) {
        this.imageHref = imageHref;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeValue(description);
        dest.writeValue(imageHref);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}

