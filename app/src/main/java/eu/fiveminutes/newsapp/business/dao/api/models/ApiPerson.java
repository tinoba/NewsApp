package eu.fiveminutes.newsapp.business.dao.api.models;

import com.google.gson.annotations.SerializedName;

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
