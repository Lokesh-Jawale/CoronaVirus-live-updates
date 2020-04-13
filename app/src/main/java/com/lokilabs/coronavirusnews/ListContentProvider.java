package com.lokilabs.coronavirusnews;

import android.net.Uri;

public class ListContentProvider {

    private String country;
    private String cases;
    private String deaths;
    private String newCases;
    private String totalDeaths;
    private String recovered;
    private String danger;
    private String totalCases;

    private String newsImage;
    private String newsTitle;
    private String newsDes;
    private Uri uri;

    public ListContentProvider(){

    }

    ListContentProvider(String newsTitle, String newsDes, String newsImage, Uri uri){
        this.newsTitle = newsTitle;
        this.newsDes = newsDes;
        this.newsImage = newsImage;
        this.uri = uri;
    }

    public ListContentProvider(String country, String cases, String deaths, String newCases){
        this.country = country;
        this.cases = cases;
        this.deaths = deaths;
        this.newCases = newCases;
    }

    public String getCases() {
        return cases;
    }
    public void setCases(String cases) {
        this.cases = cases;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String textView) {
        this.country = textView;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getNewCases() {
        return newCases;
    }

    public void setNewCases(String newCases) {
        this.newCases = newCases;
    }

    public String getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(String totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDanger() {
        return danger;
    }

    public void setDanger(String danger) {
        this.danger = danger;
    }

    public String getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(String totalCases) {
        this.totalCases = totalCases;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDes() {
        return newsDes;
    }

    public void setNewsDes(String newsDes) {
        this.newsDes = newsDes;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}

