package eu.fiveminutes.news_app_2;

/**
 * Created by tinoba on 17.8.2016..
 */
public final class News {
    public final Docs docs;

    public News(Docs docs){
        this.docs = docs;
    }
    public final class Docs{
        public final String snippet;
        public final String pub_date;
        public Docs(String snippet, String pub_date){
            this.snippet = snippet;
            this.pub_date = pub_date;
        }
    }
}
