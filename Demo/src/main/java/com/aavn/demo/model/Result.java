package com.aavn.demo.model;

/**
 * @author lqdung
 */
public class Result {

    String type;
    // Person Properties
    String name;
    String born;
    // Movie Properties
    String title;
    String tagline;
    String released;

    public Result(String type, String name, String born) {
        this.type = type;
        this.name = name;
        this.born = born;
    }

    public Result(String type, String title, String tagline, String released) {
        this.type = type;
        this.title = title;
        this.tagline = tagline;
        this.released = released;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }
}