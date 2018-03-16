package com.example.android.newsfeed;

import static android.R.attr.name;

/**
 * Created by mohab on 8/17/2017.
 */
public class News {

    private String  article_name;
    private String  section_name;
    private String  author_name;
    private String Date;
    private String web_link;




    public News(String article,String section,String author, String date,String web_link){
        article_name=article;
        section_name=section ;
        author_name = author;
        Date = date;
        this.web_link=web_link;



    }

    public String getArticleName() {
        return article_name;
    }

    public void setArticleName(String name) {
        article_name = name;
    }


    public String getAuthorName() {
        return author_name;
    }

    public void setAuthorName(String name) {
        author_name = name;
    }


    public String getSectionName() {
        return section_name;
    }

    public void setSectionName(String name) {
        section_name = name;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getWebLink() {
        return this.web_link;
    }

    public void setWebLink(String web_link) {
        this.web_link = web_link;
    }

}


