package com.example.android.booklisting;

/**
 * Created by mohab on 8/15/2017.
 */

public class Book
{

        private String  BookTitle;
        private String  AuthorName ;

        public Book(String author_name,String title)
        {
            AuthorName = author_name;
            BookTitle = title;
        }

        public String getBookTitle()
        {
            return BookTitle;
        }

        public String getAuthorName()
        {
            return AuthorName;
        }



}


