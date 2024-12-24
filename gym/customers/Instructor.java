/**
 * Represents an Instructor in the gym management system.
 * An Instructor is a type of Person with additional attributes such as salary, areas of expertise,
 * and a list of sessions they are responsible for.
 */
package gym.customers;

import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Instructor extends Person {
    private int salary; // The hourly salary of the instructor.
    private List<SessionType> expertise; // The list of session types the instructor is certified to teach.
    private List<Session> sessions; // The list of sessions the instructor is handling.

    /**
     * Constructs an Instructor object based on a Person object, salary, and expertise.
     *
     * @param person   the base Person object.
     * @param salary   the hourly salary of the instructor.
     * @param expertise the list of session types the instructor is certified to teach.
     */
    public Instructor(Person person, int salary, List<SessionType> expertise) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate().toString(), person.getId());
        this.salary = salary;
        this.expertise = expertise;
        this.sessions = new ArrayList<>();
    }

    /**
     * Retrieves the list of notifications for the instructor.
     *
     * @return a list of notification messages.
     */
    public List<String> getNotifications() {
        return super.getNotifications();
    }

    /**
     * Retrieves the salary of the instructor.
     *
     * @return the hourly salary of the instructor.
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Retrieves the list of session types the instructor is certified to teach.
     *
     * @return a list of session types.
     */
    public List<SessionType> getExpertise() {
        return expertise;
    }

    /**
     * Retrieves the list of sessions assigned to the instructor.
     *
     * @return a list of sessions.
     */
    public List<Session> getSessions() {
        return sessions;
    }

    /**
     * Adds a session to the instructor's list of sessions.
     * If the session's type is not in the instructor's expertise, it will be added.
     *
     * @param session the session to be added.
     */
    public void addSession(Session session) {
        sessions.add(session);
        if (!expertise.contains(session.getType())) {
            expertise.add(session.getType());
        }
    }

    /**
     * Checks if the instructor is qualified to teach a session of a specific type.
     *
     * @param sessionType the type of session to check.
     * @return true if the instructor is qualified, false otherwise.
     */
    public boolean isQualifiedFor(SessionType sessionType) {
        return expertise.contains(sessionType);
    }

    /**
     * Returns a string representation of the instructor, including their details and expertise.
     *
     * @return a string representation of the instructor.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String expertiseList = expertise.stream().map(Enum::toString).collect(Collectors.joining(", "));

        return "ID: " + super.getId() +
                " | Name: " + super.getName() +
                " | Gender: " + super.getGender().toString() +
                " | Birthday: " + super.getBirthDate().format(formatter) +
                " | Age: " + super.getAge() +
                " | Balance: " + super.getBalanceInt() +
                " | Role: Instructor" +
                " | Salary per Hour: " + salary +
                " | Certified Classes: " + expertiseList;
    }
}
