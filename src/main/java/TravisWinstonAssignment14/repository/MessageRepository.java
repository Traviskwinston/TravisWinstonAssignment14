package TravisWinstonAssignment14.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageRepository {
    private final Map<String, List<String>> messages = new HashMap<>();

    public List<String> getMessagesByChannel(String channelId) {
    	//if ChannelId exists, get messages for that channel ID Otherwise, make a new list.
        return messages.getOrDefault(channelId, new ArrayList<>());
    }
    
    //Add message to text area of the channel
    public void addMessage(String channelId, String message) {
    	//ensure the list exists for the given channel ID, if it doesn't, make one and then, add the message
        messages.computeIfAbsent(channelId, k -> new ArrayList<>()).add(message);
    }
}
