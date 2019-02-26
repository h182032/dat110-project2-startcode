package no.hvl.dat110.messages;

public class PublishMsg extends Message {

    private String topic, message;

    public PublishMsg(String user, String topic, String message) {
        super(MessageType.PUBLISH, user);
        this.topic = topic;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "Message [type=" + super.getType() + ", user=" + super.getUser() + ", topic=" + getTopic() + "]";
    }


    // TODO:
    // Implement objectvariables, constructor, get/set-methods, and toString method

}
