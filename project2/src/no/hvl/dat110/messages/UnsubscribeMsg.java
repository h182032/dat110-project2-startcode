package no.hvl.dat110.messages;

public class UnsubscribeMsg extends Message {

    private String topic;

    public UnsubscribeMsg(String user, String topic) {
        super(MessageType.UNSUBSCRIBE, user);
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "Message [type=" + super.getType() + ", user=" + super.getUser() + ", topic=" + getTopic() + "]";
    }

    // TODO:
    // Implement objectvariables, constructor, get/set-methods, and toString method

}
