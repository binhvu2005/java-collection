package ra.presentation;
import ra.entity.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static ra.bussiness.bussiness.*;

public class BookApplication {
    public static List<Book> bookList = new ArrayList<>();
    public static final int MAX_BOOKS = 100;

    public static void main(String[] args) {

        int choice;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("----------------------------Book Menu----------------------------");
            System.out.println("1. Hiển thị danh sách các cuốn sách");
            System.out.println("2. Thêm mới sách");
            System.out.println("3. Chỉnh sửa thông tin sách");
            System.out.println("4. Xóa sách");
            System.out.println("5. Tìm kiếm sách");
            System.out.println("6. Sắp xếp");
            System.out.println("0. Thoát chương trình");
            System.out.println("----------------------------------------------------------------");
            System.out.print("lựa chọn của bạn là : ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayBooks();
                    break;
                case 2:
                    addNewBook(sc);
                    break;
                case 3:
                    editBook(sc);
                    break;
                case 4:
                    deleteBook(sc);
                    break;
                case 5:
                    searchBook(sc);
                    break;
                case 6:
                    sortBook(sc);
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        } while (choice != 0);
    }
}