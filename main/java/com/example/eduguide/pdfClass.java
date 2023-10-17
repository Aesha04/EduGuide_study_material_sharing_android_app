package com.example.eduguide;

public class pdfClass {
    public String name,category,url;

    public pdfClass() {
    }

    public pdfClass(String name, String category, String url) {
        this.name = name;
        this.category = category;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
