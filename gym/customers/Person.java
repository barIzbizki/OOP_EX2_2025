package gym.customers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a person in the gym system with details such as name, balance, birth date, gender, and notifications.
 */
public class Person {
    private String name;
    private Balance balance;
    private LocalDate birthDate;
    private Gender gender;
    private static int nextId = 1111;
    private int id;
    private List<String> notifications;

    /**
     * Constructor for creating a Person with auto-generated ID.
     *
     * @param name      the name of the person.
     * @param balance   the balance amount of the person.
     * @param gender    the gender of the person.
     * @param birthDate the birth date of the person in "yyyy-MM-dd" or "dd-MM-yyyy" format.
     */
    public Person(String name, int balance, Gender gender, String birthDate) {
        this.name = name;
        this.balance = new Balance(balance);
        this.birthDate = parseBirthDate(birthDate);
        this.id = nextId++;
        this.gender = gender;
        this.notifications = new ArrayList<>();
    }

    /**
     * Constructor for creating a Person with a specified ID.
     *
     * @param name      the name of the person.
     * @param balance   the balance object representing the person's balance.
     * @param gender    the gender of the person.
     * @param birthDate the birth date of the person in "yyyy-MM-dd" or "dd-MM-yyyy" format.
     * @param id        the ID of the person.
     */
    public Person(String name, Balance balance, Gender gender, String birthDate, int id) {
        this.name = name;
        this.balance = balance;
        this.birthDate = parseBirthDate(birthDate);
        this.gender = gender;
        this.id = id;
        this.notifications = new ArrayList<>();
    }

    /**
     * Retrieves the list of notifications for this person.
     *
     * @return a list of notifications.
     */
    public List<String> getNotifications() {
        return notifications;
    }

    /**
     * Retrieves the name of this person.
     *
     * @return the name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the gender of this person.
     *
     * @return the gender of the person.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Retrieves the birth date of this person.
     *
     * @return the birth date as a LocalDate.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Retrieves the balance amount of this person as an integer.
     *
     * @return the balance amount.
     */
    public int getBalanceInt() {
        return balance.getBalance();
    }

    /**
     * Retrieves the Balance object for this person.
     *
     * @return the Balance object.
     */
    public Balance getBalance() {
        return balance;
    }

    /**
     * Retrieves the ID of this person.
     *
     * @return the ID of the person.
     */
    public int getId() {
        return id;
    }

    /**
     * Calculates and retrieves the age of this person.
     *
     * @return the age of the person in years.
     */
    public int getAge() {
        LocalDate now = LocalDate.now();
        return Period.between(birthDate, now).getYears();
    }

    /**
     * Parses a birth date string into a LocalDate object.
     *
     * @param birthDate the birth date string in "yyyy-MM-dd" or "dd-MM-yyyy" format.
     * @return the parsed LocalDate object.
     * @throws IllegalArgumentException if the birth date format is invalid.
     */
    private LocalDate parseBirthDate(String birthDate) {
        if (birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(birthDate, formatter);
        } else if (birthDate.matches("\\d{2}-\\d{2}-\\d{4}")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return LocalDate.parse(birthDate, formatter);
        } else {
            throw new IllegalArgumentException("Invalid birth date format: " + birthDate);
        }
    }

    /**
     * Compares this person to another object for equality.
     * Two persons are considered equal if they have the same ID.
     *
     * @param obj the object to compare.
     * @return true if the object is a Person with the same ID, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ((Person) obj).getId() == id;
    }

    /**
     * Represents the balance of a person and provides operations to modify it.
     */
    public class Balance {
        private int balance;

        /**
         * Constructor to initialize the balance.
         *
         * @param balance the initial balance amount.
         */
        public Balance(int balance) {
            this.balance = balance;
        }

        /**
         * Retrieves the current balance amount.
         *
         * @return the balance amount.
         */
        public int getBalance() {
            return balance;
        }

        /**
         * Reduces the balance by a specified amount.
         *
         * @param balance the amount to deduct from the balance.
         */
        public void reduceBalance(int balance) {
            this.balance -= balance;
        }

        /**
         * Adds a specified amount to the balance.
         *
         * @param balance the amount to add to the balance.
         */
        public void addBalance(int balance) {
            this.balance += balance;
        }
    }
}
