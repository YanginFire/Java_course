package org.example.task4.objects;

public class Book {
    private String isbn;
    private String name;
    private String author;
    private int edition;
    private String publisher;
    private int publicationYear;
    private BookCategories category;

    public Book() {}

    public Book(String isbn, String name, String author, int edition, String publisher, int publicationYear, BookCategories category) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.edition = edition;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public BookCategories getCategory() {
        return category;
    }

    public void setCategory(BookCategories category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "ISBN: " + isbn +
                ", Название: " + name +
                ", Автор: " + author +
                ", Издание :" + edition +
                ", Издатель :" + publisher +
                ", Год издания: " + publicationYear +
                ", Категория: " + category;
    }
}
