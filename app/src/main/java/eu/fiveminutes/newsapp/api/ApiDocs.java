package eu.fiveminutes.newsapp.api;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tinoba on 18.8.2016..
 */
public final class ApiDocs {

    @SerializedName("web_url")
    public String webUrl;

    @SerializedName("snippet")
    public String snippet;

    @SerializedName("lead_paragraph")
    public String leadParagraph;

    @SerializedName("print_page")
    public String printPage;

    @SerializedName("blog")
    public List<Object> blog;

    @SerializedName("source")
    public String source;

    @SerializedName("multimedia")
    public List<ApiMultimedia> multimedia;

    @SerializedName("headline")
    public ApiHeadline headline;

    @SerializedName("keywords")
    public List<ApiKeywords> keywords;

    @SerializedName("pub_date")
    public String pubDate;

    @SerializedName("document_type")
    public String documentType;

    @SerializedName("news_desk")
    public String newsDesk;

    @SerializedName("section_name")
    public String sectionName;

    @SerializedName("subsection_name")
    public Object subsectionName;

   /* @SerializedName("byline")
    public ApiByline byline;*/

    @SerializedName("type_of_material")
    public String typeOfMaterial;

    @SerializedName("_id")
    public String id;

    @SerializedName("word_count")
    public Object wordCount;

    @SerializedName("slideshow_credits")
    public Object slideshowCredits;

    @SerializedName("abstract")
    public Object abstractText;
}
