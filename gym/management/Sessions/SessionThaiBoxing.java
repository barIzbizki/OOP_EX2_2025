package gym.management.Sessions;

import gym.customers.Instructor;

public class SessionThaiBoxing extends Session {
    private static final int MAX_PARTICIPANTS = 20;
    private static final int PRICE = 100;

    public SessionThaiBoxing(Instructor instructor, String dateTime, ForumType forum) {
        super(SessionType.ThaiBoxing, instructor, dateTime, forum);
        this.maxParticipants = MAX_PARTICIPANTS;
        this.price = PRICE;
    }
}
