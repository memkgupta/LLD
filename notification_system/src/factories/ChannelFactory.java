package factories;

import channels.Channel;
import channels.SimpleChannel;
import dtos.NotificationJob;
import enums.ChannelEnum;

import java.util.List;

public class ChannelFactory {
    private List<Channel> channels;
    public ChannelFactory(List<Channel> channels) {
        this.channels = channels;
    }
    public Channel getChannel(ChannelEnum channel)
    {
        return  channels.stream().filter(c->c.supports().equals(channel)).findFirst().orElseThrow();
    }
}
