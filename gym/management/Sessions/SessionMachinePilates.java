package gym.management.Sessions;

import gym.customers.Instructor;

public class SessionMachinePilates extends Session {
    private static final int MAX_PARTICIPANTS = 10;
    private static final int PRICE = 80;

    public SessionMachinePilates(Instructor instructor, String dateTime, ForumType forum) {
        super(SessionType.MachinePilates, instructor, dateTime, forum);
        this.maxParticipants = MAX_PARTICIPANTS;
        this.price = PRICE;
    }
}
