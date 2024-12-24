package gym.management.Sessions;

import gym.customers.Instructor;

public class SessionPilates extends Session {
    private static final int MAX_PARTICIPANTS = 30;
    private static final int PRICE = 60;

    public SessionPilates(Instructor instructor, String dateTime, ForumType forum) {
        super(SessionType.Pilates, instructor, dateTime, forum);
        this.maxParticipants = MAX_PARTICIPANTS;
        this.price = PRICE;
    }
}
