package eu.fiveminutes.newsapp.api.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiPerson {

    @SerializedName("organization")
    public String organization;

    @SerializedName("role")
    public String role;

    @SerializedName("firstname")
    public String firstName;

    @SerializedName("rank")
    public int rank;

    @SerializedName("lastname")
    public String lastName;

}
