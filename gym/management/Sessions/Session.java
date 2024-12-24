package gym.management.Sessions;

import gym.customers.Client;
import gym.customers.Instructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a session conducted in a gym. Each session is defined by its type,
 * instructor, participants, date and time, forum type, maximum participants, and price.
 */
public class Session {

    /** The type of the session (e.g., Yoga, Pilates). */
    protected SessionType type;

    /** The instructor conducting the session. */
    protected Instructor instructor;

    /** List of clients registered as participants in the session. */
    protected List<Client> participants;

    /** The maximum number of participants allowed in the session. */
    protected int maxParticipants;

    /** The price for attending the session. */
    protected int price;

    /** The date and time when the session will take place. */
    protected LocalDateTime dateTime;

    /** The forum type of the session (e.g., group or private). */
    protected ForumType forum;

    /**
     * Constructs a new session.
     *
     * @param type        the type of the session
     * @param instructor  the instructor conducting the session
     * @param dateTime    the date and time of the session in the format "dd-MM-yyyy HH:mm"
     * @param forum       the forum type of the session
     */
    public Session(SessionType type, Instructor instructor, String dateTime, ForumType forum) {
        this.type = type;
        this.instructor = instructor;
        this.participants = new ArrayList<>();
        this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        this.forum = forum;
    }

    /**
     * Gets the list of participants registered for the session.
     *
     * @return a list of clients participating in the session
     */
    public List<Client> getParticipants() {
        return participants;
    }

    /**
     * Registers a client to the session.
     *
     * @param client the client to register
     */
    public void registerClient(Client client) {
        participants.add(client);
    }

    /**
     * Gets the instructor of the session.
     *
     * @return the instructor conducting the session
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Gets the price of the session.
     *
     * @return the price of the session
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gets the maximum number of participants allowed in the session.
     *
     * @return the maximum number of participants
     */
    public int getMaxParticipants() {
        return maxParticipants;
    }

    /**
     * Gets the date and time of the session.
     *
     * @return the date and time of the session
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Gets the forum type of the session.
     *
     * @return the forum type of the session
     */
    public ForumType getForum() {
        return forum;
    }

    /**
     * Gets the type of the session.
     *
     * @return the session type
     */
    public SessionType getType() {
        return type;
    }

    /**
     * Checks if this session is equal to another object.
     *
     * @param o the object to compare
     * @return true if the sessions are equal, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return maxParticipants == session.maxParticipants &&
                price == session.price &&
                type == session.type &&
                Objects.equals(instructor, session.instructor) &&
                Objects.equals(participants, session.participants) &&
                Objects.equals(dateTime, session.dateTime) &&
                forum == session.forum;
    }

    /**
     * Calculates the hash code for the session.
     *
     * @return the hash code of the session
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, instructor, participants, maxParticipants, price, dateTime, forum);
    }

    /**
     * Returns a string representation of the session.
     *
     * @return a string representing the session
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "Session Type: " + type.toString() +
                " | Date: " + dateTime.format(formatter) +
                " | Forum: " + forum.toString() +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + participants.size() + "/" + maxParticipants;
    }
}
