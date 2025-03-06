package TravisWinstonAssignment14.repository;

import java.util.ArrayList;
import java.util.List;

public class ChannelRepository {

    private final List<String> channels = new ArrayList<>(List.of("General"));

    public List<String> getAllChannels() {
        return channels;
    }

    public String getChannel(String channelId) {
    	//(conditional statement) ? (send if True) : (Send if False)
        //return channels.contains(channelId) ? channelId : null;
        return channelId;
    }
    
    public String addChannel(String channelName) {
    	channels.add(channelName);
    	return channelName;
    }

}
