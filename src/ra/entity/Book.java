package ra.entity;

import java.util.Scanner;

public class Book implements IApp.Iapp {
    private String bookId;
    private String bookName;
    private String author;
    private String category;
    private int publicationYear;
    private double price;
    private int quantity;

    public Book() {
    }

    public Book(String bookId, String bookName, String author, String category, int publicationYear, double price, int quantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.publicationYear = publicationYear;
        this.price = price;
        this.quantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void inputData(Scanner sc) {

    }
    @Override
    public void displayData() {
        System.out.println("Mã sách: " + bookId + ", Tên sách: " + bookName + ", Tác giả: " + author + ", Thể loại: " + category + ", Năm xuất bản: " + publicationYear + ", Giá: " + price + ", Số lượng: " + quantity);
    }
}