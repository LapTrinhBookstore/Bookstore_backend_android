package com.example.appbookstore.model;

public class DetailBook {
    int id, pageNumber, bought, slDG;
    double price, star;
    String name, publisher, publishDate, language, productImg, introduction, author, authorIntro, cate;

    public DetailBook(int id, int pageNumber, int bought, int slDG, double price, double star, String name, String publisher, String publishDate, String language, String productImg, String introduction, String author, String authorIntro, String cate) {
        this.id = id;
        this.pageNumber = pageNumber;
        this.bought = bought;
        this.slDG = slDG;
        this.price = price;
        this.star = star;
        this.name = name;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.language = language;
        this.productImg = productImg;
        this.introduction = introduction;
        this.author = author;
        this.authorIntro = authorIntro;
        this.cate = cate;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public int getSlDG() {
        return slDG;
    }

    public void setSlDG(int slDG) {
        this.slDG = slDG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorIntro() {
        return authorIntro;
    }

    public void setAuthorIntro(String authorIntro) {
        this.authorIntro = authorIntro;
    }

    @Override
    public String toString() {
        return "DetailBook{" +
                "id=" + id +
                ", pageNumber=" + pageNumber +
                ", bought=" + bought +
                ", price=" + price +
                ", star=" + star +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", language='" + language + '\'' +
                ", productImg='" + productImg + '\'' +
                ", introduction='" + introduction + '\'' +
                ", author='" + author + '\'' +
                ", authorIntro='" + authorIntro + '\'' +
                '}';
    }
}
