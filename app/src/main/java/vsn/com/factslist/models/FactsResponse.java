package vsn.com.factslist.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.inputmethodservice.Keyboard;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.SerializedName;

public class FactsResponse implements Serializable, Parcelable
{

    @SerializedName("title")
    private String title;
    @SerializedName("rows")
    private List<Row> rows = null;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    public final static Parcelable.Creator<FactsResponse> CREATOR = new Creator<FactsResponse>() {


        public FactsResponse createFromParcel(Parcel in) {
            return new FactsResponse(in);
        }

        public FactsResponse[] newArray(int size) {
            return (new FactsResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 3878420183346456473L;

    protected FactsResponse(Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.rows, (Row.class.getClassLoader()));
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public FactsResponse() {
    }

    @SerializedName("title")
    public String getTitle() {
        return title;
    }

    @SerializedName("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("rows")
    public List<Row> getRows() {
        return rows;
    }

    @SerializedName("rows")
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }


    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeList(rows);
        dest.writeValue(additionalProperties);
    }

    public int describeContents() {
        return 0;
    }

}

