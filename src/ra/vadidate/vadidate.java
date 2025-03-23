package ra.vadidate;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class vadidate {
    private static Set<String> usedBookIds = new HashSet<>();
    private static int bookIdCounter = 1;

    // Phương thức tạo mã sách tự động
    public static String generateBookId() {
        String bookId;
        do {
            bookId = String.format("B%05d", bookIdCounter++);
        } while (usedBookIds.contains(bookId));
        usedBookIds.add(bookId);
        return bookId;
    }

    // Phương thức xác thực chuỗi không được để trống và giới hạn độ dài
    public static String validateString(Scanner scanner, String message, int maxLength) {
        String input;
        do {
            System.out.print(message);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Chuỗi không được để trống. Vui lòng nhập lại.");
            } else if (input.length() > maxLength) {
                System.out.println("Chuỗi không được vượt quá " + maxLength + " ký tự. Vui lòng nhập lại.");
            }
        } while (input.isEmpty() || input.length() > maxLength);
        return input;
    }

    // Phương thức xác thực năm xuất bản
    public static int validatePublicationYear(Scanner scanner) {
        int year;
        do {
            System.out.print("Nhập năm xuất bản: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập một số nguyên.");
                scanner.next();
                System.out.print("Nhập năm xuất bản: ");
            }
            year = scanner.nextInt();
            if (year <= 0 || year > java.time.Year.now().getValue()) {
                System.out.println("Năm xuất bản không hợp lệ. Vui lòng nhập lại.");
            }
        } while (year <= 0 || year > java.time.Year.now().getValue());
        scanner.nextLine();
        return year;
    }

    // Phương thức xác thực giá sách
    public static double validatePrice(Scanner scanner) {
        return validatePositiveDouble(scanner, "Nhập giá sách: ");
    }

    // Phương thức xác thực số lượng sách
    public static int validateQuantity(Scanner scanner) {
        return validatePositiveInteger(scanner, "Nhập số lượng sách: ");
    }

    // Các phương thức hiện có
    public static int validatePositiveInteger(Scanner scanner, String message) {
        int number;
        do {
            if (!message.isEmpty()) {
                System.out.print(message);
            }
            while (!scanner.hasNextInt()) {
                System.out.println("Vui lòng nhập một số nguyên.");
                scanner.next();
                if (!message.isEmpty()) {
                    System.out.print(message);
                }
            }
            number = scanner.nextInt();
            if (number <= 0) {
                System.out.println("Vui lòng nhập số nguyên dương.");
            }
        } while (number <= 0);
        scanner.nextLine();
        return number;
    }

    public static double validatePositiveDouble(Scanner scanner, String message) {
        double number;
        do {
            System.out.print(message);
            while (!scanner.hasNextDouble()) {
                System.out.println("Vui lòng nhập một số thực.");
                scanner.next();
                System.out.print(message);
            }
            number = scanner.nextDouble();
            if (number <= 0) {
                System.out.println("Vui lòng nhập số thực dương.");
            }
        } while (number <= 0);
        scanner.nextLine();
        return number;
    }

}
