package eu.fiveminutes.newsapp.api;

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
    public String rank;

    @SerializedName("lastname")
    public String lastName;

}
