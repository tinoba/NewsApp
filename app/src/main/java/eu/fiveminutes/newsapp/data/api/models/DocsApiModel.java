package eu.fiveminutes.newsapp.data.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public final class DocsApiModel {

    public static final List<DocsApiModel> EMPTY_API_DOCS = Collections.unmodifiableList(Collections.emptyList());

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
