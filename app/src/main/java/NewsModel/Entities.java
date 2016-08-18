package NewsModel;

/**
 * Created by tinoba on 17.8.2016..
 */
final class Entities {
    public final String Paragraph;
    public final String PublishDate;
    public final String HeadlineMainText;


    public Entities(String Paragraph, String HeadlineText, String PublishDate){
        this.Paragraph= Paragraph;
        this.HeadlineMainText = HeadlineText;
        this.PublishDate = PublishDate;
    }
}
