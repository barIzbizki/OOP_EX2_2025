package gym.management.Sessions;

import gym.customers.Instructor;

public class SessionNinja extends Session {
    private static final int MAX_PARTICIPANTS = 5;
    private static final int PRICE = 150;

    public SessionNinja(Instructor instructor, String dateTime, ForumType forum) {
        super(SessionType.Ninja, instructor, dateTime, forum);
        this.maxParticipants = MAX_PARTICIPANTS;
        this.price = PRICE;
    }
}
