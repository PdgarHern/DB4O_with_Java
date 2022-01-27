package com.company;

public class Anime {

    private String title;
    private String author;
    private String studio;
    private String demographic;
    private int episodes;

    public Anime(String title, String author, String studio, String demographic, int episodes) {
        this.title = title;
        this.author = author;
        this.studio = studio;
        this.demographic = demographic;
        this.episodes = episodes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getDemographic() {
        return demographic;
    }

    public void setDemographic(String demographic) {
        this.demographic = demographic;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

}
