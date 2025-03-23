package ra.bussiness;

import ra.entity.Book;
import ra.vadidate.vadidate;

import java.util.*;

import static ra.presentation.BookApplication.MAX_BOOKS;
import static ra.presentation.BookApplication.bookList;

public class bussiness {
    public static void displayBooks() {
        if (bookList.isEmpty()) {
            System.out.println("Danh sách trống");
        } else {
            System.out.println("Danh sách các cuốn sách:");
            for (Book book : bookList) {
                book.displayData();
            }
        }
    }
// hàm thêm sách và kiểm tra xem đầy chưa
    public static void addNewBook(Scanner sc) {
        if (bookList.size() == MAX_BOOKS) {
            System.out.println("Không thể thêm mới sách nữa. Danh sách đã đầy.");
            return;
        }

        System.out.print("Nhập số lượng sách cần thêm: ");
        int numBooks = vadidate.validatePositiveInteger(sc, "");
        if (bookList.size() + numBooks > MAX_BOOKS) {
            System.out.println("Số lượng sách vượt quá giới hạn. Chỉ có thể thêm tối đa " + (MAX_BOOKS - bookList.size()) + " cuốn sách.");
            numBooks = MAX_BOOKS - bookList.size();
        }

        for (int i = 0; i < numBooks; i++) {
            System.out.println("Nhập thông tin cho sách thứ " + (i + 1) + ":");
            Book newBook = new Book();
            newBook.setBookId(vadidate.generateBookId());
            newBook.setBookName(vadidate.validateString(sc, "Nhập tên sách: ", 100));
            newBook.setAuthor(vadidate.validateString(sc, "Nhập tên tác giả: ", 50));
            newBook.setCategory(vadidate.validateString(sc, "Nhập thể loại: ", 50));
            newBook.setPublicationYear(vadidate.validatePublicationYear(sc));
            newBook.setPrice(vadidate.validatePrice(sc));
            newBook.setQuantity(vadidate.validateQuantity(sc));
            bookList.add(newBook);
            System.out.println("Thêm sách thành công.");
        }
    }
   // hàm sửa thông tin sách hiển thị menu cho người dùng chọn thuộc tính cần sửa(trừ mã sách)
    public static void editBook(Scanner sc) {
        System.out.print("Nhập mã sách cần chỉnh sửa: ");
        String bookId = sc.nextLine();
        Book bookToEdit = findBookById(bookId);
        if (bookToEdit == null) {
            System.out.println("Không tìm thấy sách.");
            return;
        }
        bookToEdit.displayData();
        System.out.println("Chọn thuộc tính cần sửa:");
        System.out.println("1. Tên sách");
        System.out.println("2. Tác giả");
        System.out.println("3. Thể loại");
        System.out.println("4. Năm xuất bản");
        System.out.println("5. Giá");
        System.out.println("6. Số lượng");
        System.out.print("Lựa chọn của bạn: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                bookToEdit.setBookName(vadidate.validateString(sc, "Nhập tên sách mới: ", 100));
                break;
            case 2:
                bookToEdit.setAuthor(vadidate.validateString(sc, "Nhập tên tác giả mới: ", 50));
                break;
            case 3:
                bookToEdit.setCategory(vadidate.validateString(sc, "Nhập thể loại mới: ", 50));
                break;
            case 4:
                bookToEdit.setPublicationYear(vadidate.validatePublicationYear(sc));
                break;
            case 5:
                bookToEdit.setPrice(vadidate.validatePrice(sc));
                break;
            case 6:
                bookToEdit.setQuantity(vadidate.validateQuantity(sc));
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
        System.out.println("Chỉnh sửa thông tin sách thành công.");
    }
    // hàm xoá sách theo tên sách
    public static void deleteBook(Scanner sc) {
        System.out.print("Nhập mã sách cần xóa: ");
        String bookId = sc.nextLine();
        Book bookToDelete = findBookById(bookId);
        if (bookToDelete == null) {
            System.out.println("Không tìm thấy sách.");
            return;
        }
        bookToDelete.displayData();
        System.out.print("Bạn có chắc chắn muốn xóa sách này? (y/n): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            bookList.remove(bookToDelete);
            System.out.println("Xóa sách thành công.");
        } else {
            System.out.println("Hủy bỏ thao tác xóa.");
        }
    }
    // hàm tìm kiếm sách theo các tiêu chí
    public static void searchBook(Scanner sc) {
        System.out.println("Tìm kiếm theo tiêu chí:");
        System.out.println("1. Tìm kiếm theo tiêu đề sách");
        System.out.println("2. Tìm kiếm theo thể loại");
        System.out.println("3. Tìm kiếm theo khoảng giá");
        System.out.print("Lựa chọn của bạn: ");
        int choice = sc.nextInt();
        sc.nextLine();
        List<Book> searchResults = new ArrayList<>();
        switch (choice) {
            case 1:
                System.out.print("Nhập tiêu đề sách cần tìm: ");
                String title = sc.nextLine();
                for (Book book : bookList) {
                    if (book.getBookName().toLowerCase().contains(title.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                break;
            case 2:
                System.out.print("Nhập thể loại cần tìm: ");
                String category = sc.nextLine();
                for (Book book : bookList) {
                    if (book.getCategory().toLowerCase().contains(category.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                break;
            case 3:
                System.out.print("Nhập giá bắt đầu: ");
                double startPrice = sc.nextDouble();
                System.out.print("Nhập giá kết thúc: ");
                double endPrice = sc.nextDouble();
                for (Book book : bookList) {
                    if (book.getPrice() >= startPrice && book.getPrice() <= endPrice) {
                        searchResults.add(book);
                    }
                }
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
        if (searchResults.isEmpty()) {
            System.out.println("Không tìm thấy kết quả phù hợp.");
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Book book : searchResults) {
                book.displayData();
            }
        }
    }
    // hàm sắp xếp sách theo các tiêu chí
    public static void sortBook(Scanner sc) {
        System.out.println("Sắp xếp theo tiêu chí:");
        System.out.println("1. Sắp xếp theo tiêu đề tăng dần");
        System.out.println("2. Sắp xếp theo tiêu đề giảm dần");
        System.out.println("3. Sắp xếp theo giá tăng dần");
        System.out.println("4. Sắp xếp theo giá giảm dần");
        System.out.print("Lựa chọn của bạn: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                bookList.sort(Comparator.comparing(Book::getBookName));
                break;
            case 2:
                bookList.sort(Comparator.comparing(Book::getBookName).reversed());
                break;
            case 3:
                bookList.sort(Comparator.comparingDouble(Book::getPrice));
                break;
            case 4:
                bookList.sort(Comparator.comparingDouble(Book::getPrice).reversed());
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
        displayBooks();
    }
    //  hàm tìm kiếm thông tin sách
    private static Book findBookById(String bookId) {
        for (Book book : bookList) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }


}
