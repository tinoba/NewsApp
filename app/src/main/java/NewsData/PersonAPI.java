package NewsData;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class PersonAPI {
    public final String organization;
    public final String role;
    public final String firstname;
    public final String rank;
    public final String lastname;

    public PersonAPI(String organization, String role, String firstname, String rank, String lastname) {
        this.organization = organization;
        this.role = role;
        this.firstname = firstname;
        this.rank = rank;
        this.lastname = lastname;
    }
}
