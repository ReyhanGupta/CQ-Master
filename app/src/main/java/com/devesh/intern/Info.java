package com.devesh.intern;

/**
 * Created by Devesh on 08-07-2016.
 */
public class Info {

    // SID can be added as per requirement to further increase functionality of the app

    String name;
    String genre;
    String story;
    String synopsis;
    String date;
    Integer time;

    public Info(String name, String genre, String story, String synopsis, String date, Integer time) {
    // Constructor
        this.name = name;
        this.genre = genre;
        this.story = story;
        this.synopsis = synopsis;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
