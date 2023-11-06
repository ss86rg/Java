package org.example;

import java.util.*;

public class dzitog {
    public static void main(String[] args) {
        Phonebook ph = new Phonebook();
        int choose = 0;
        while (choose != 6) {
            System.out.println("Доступные опции:\n" +
                    "1. Добавить контакт\n" +
                    "2. Вывести все контакты\n" +
                    "3. Поиск контакта\n" +
                    "4. Редактировать контакт\n" +
                    "5. Удалить контакт\n" +
                    "6. Выход\n");
            choose = ph.getNumber("Выберите действие: ");
            while (choose < 1 || choose > 6) {
                System.out.println("Некорректный ввод.\n");
                choose = ph.getNumber("Выберите действие: ");
            }
            switch (choose) {
                case 1: // добавление контакта
                    String name = ph.getName("Введите имя: ");
                    Integer number = ph.getNumber("Введите номер: ");
                    ph.add(name, number);
                    break;
                case 2: // печать всех контактов
                    System.out.println(ph.getPhoneBook());
                    break;
                case 3: // поиск контакта по имени
                    String searchedName = ph.getName("Введите имя контакта для поиска: ");
                    System.out.println(ph.findByName(searchedName));
                    break;
                case 4: // редактор контакта
                    String oldName = ph.getName("Введите имя контакта для редактирования: ");
                    String newName = ph.getName("Введите новое имя: ");
                    ph.editContact(oldName, newName);
                    break;
                case 5: // удаление контакта
                    String deletingName = ph.getName("Введите имя контакта для удаления: ");
                    ph.delete(deletingName);
                    System.out.printf("Контакт %s удалён.%n", deletingName);
                    break;
                case 6:
                    System.out.println("До свидания!");
            }
        }
    }
}

class Phonebook {
    private HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public String getName(String msg) {
        System.out.println(msg);
        return sc.next();
    }

    public Integer getNumber(String msg) {
        System.out.println(msg);
        return sc.nextInt();
    }

    public void add(String name, Integer number) {
        if (map.containsKey(name)) {
            map.get(name).add(number);
        } else {
            ArrayList<Integer> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(number);
            map.put(name, phoneNumbers);
        }
    }



    public ArrayList<Integer> findByName(String name) {
        ArrayList<Integer> res = new ArrayList<>();
        if (map.containsKey(name)) {
            res = map.get(name);
        }
        return res;
    }

    public void editContact(String oldName, String newName) {
        if (map.containsKey(oldName)) {
            ArrayList<Integer> numbers = map.get(oldName);
            map.remove(oldName);
            map.put(newName, numbers);
        }
    }

    public void delete(String name) {
        map.remove(name);
    }

    public HashMap<String, ArrayList<Integer>> getPhoneBook() {
        return map;
    }

}
