package factories;

import channels.Channel;
import dtos.NotificationJob;
import enums.ChannelEnum;

import java.util.List;

public class ChannelFactory {
    private List<Channel<?>> channels;
    public ChannelFactory(List<Channel<?>> channels) {
        this.channels = channels;
    }
    public <T extends NotificationJob> Channel<T> getChannel(ChannelEnum channel)
    {
        return (Channel<T>) channels.stream().filter(c->c.supports().equals(channel)).findFirst().orElseThrow();
    }
}
