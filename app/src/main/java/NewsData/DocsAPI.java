package NewsData;

import java.util.ArrayList;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class DocsAPI {
    public final String web_url;
    public final String snippet;
    public final String lead_paragraph;
    public final String print_page;
    public final ArrayList<Object> blog;
    public final String source;
    public final ArrayList<MultimediaAPI> multimedia;
    public final HeadlineAPI headline;
    public final ArrayList<KeywordsAPI> keywords;
    public final String pub_date;
    public final String document_type;
    public final String news_desk;
    public final String section_name;
    public final Object subsection_name;
    public final BylineAPI byline;
    public final String type_of_material;
    public final String _id;
    public final Object word_count;
    public final Object slideshow_credits;

    public DocsAPI(String web_url, String snippet, String lead_paragraph, String print_page, ArrayList<Object> blog,
                   String source, ArrayList<MultimediaAPI> multimedia, HeadlineAPI headline, ArrayList<KeywordsAPI> keywords,
                   String pub_date, String document_type, String news_desk, String section_name, Object subsection_name, BylineAPI byline,
                   String type_of_material, String _id, Object word_count, Object slideshow_credits) {
        this.web_url = web_url;
        this.snippet = snippet;
        this.lead_paragraph = lead_paragraph;
        this.print_page = print_page;
        this.blog = blog;
        this.source = source;
        this.multimedia = multimedia;
        this.headline = headline;
        this.keywords = keywords;
        this.pub_date = pub_date;
        this.document_type = document_type;
        this.news_desk = news_desk;
        this.section_name = section_name;
        this.subsection_name = subsection_name;
        this.byline = byline;
        this.type_of_material = type_of_material;
        this._id = _id;
        this.word_count = word_count;
        this.slideshow_credits = slideshow_credits;
    }
}
