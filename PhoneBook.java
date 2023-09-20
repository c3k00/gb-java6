package org.example;

import java.util.*;

public class PhoneBook {

    Map<String, PhoneEntry> book = new HashMap<>();

    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();
        pb.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("Введите команду:");
            System.out.println("1 - Добавить контакт");
            System.out.println("2 - Найти по имени");
            System.out.println("3 - Найти по номеру");
            System.out.println("4 - Показать все контакты");
            System.out.println("0 - Выйти");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    addEntry(scanner);
                    break;
                case "2":
                    findByName(scanner);
                    break;
                case "3":
                    findByNumber(scanner);
                    break;
                case "4":
                    printAllEntries();
                    break;
                case "0":
                    break label;

                default:
                    System.out.println("Неверный пункт меню!");
            }
        }
        scanner.close();
    }

    public void addEntry(Scanner scanner) {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите номер: ");
        String number = scanner.nextLine();

        PhoneEntry entry = book.get(name);
        if (entry == null) {
            entry = new PhoneEntry(name);
            book.put(name, entry);
        }
        entry.addNumber(number);
        System.out.println("Контакт добавлен!");
    }

    public void findByName(Scanner scanner) {
        System.out.print("Введите имя для поиска: ");
        String name = scanner.nextLine();

        PhoneEntry entry = book.get(name);
        if (entry == null) {
            System.out.println("Контакт не найден!");
        } else {
            System.out.println(entry);
        }
    }

    public void findByNumber(Scanner scanner) {
        System.out.print("Введите номер для поиска: ");
        String number = scanner.nextLine();

        for (PhoneEntry entry : book.values()) {
            if (entry.hasNumber(number)) {
                System.out.println(entry);
                return;
            }
        }

        System.out.println("Контакт с таким номером не найден!");
    }

    public void printAllEntries() {
        // сортировка по убыванию номера
        book.values().stream()
                .sorted((a, b) -> b.getNumbers().size() - (a.getNumbers().size()))
                .forEach(System.out::println);
    }

}

class PhoneEntry {
    private final String name;
    private final TreeSet<String> numbers = new TreeSet<>(Comparator.reverseOrder());

    public PhoneEntry(String name) {
        this.name = name;
    }

    public void addNumber(String number) {
        numbers.add(number);
    }

    public boolean hasNumber(String number) {
        return numbers.contains(number);
    }

    public String getName() {
        return name;
    }

    public Set<String> getNumbers() {
        return numbers;
    }

    public String toString() {
        return name + ": " + numbers;
    }
}