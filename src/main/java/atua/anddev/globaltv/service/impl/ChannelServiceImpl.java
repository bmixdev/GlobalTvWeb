package atua.anddev.globaltv.service.impl;

import atua.anddev.globaltv.dao.ChannelDAO;
import atua.anddev.globaltv.entity.Channel;
import atua.anddev.globaltv.service.ChannelService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@NoArgsConstructor
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelDAO channelDAO;

    @Transactional
    public void addChannel(Channel channel) {
        channelDAO.addChannel(channel);
    }

    @Transactional
    public Channel getChannelById(int id) {
        return channelDAO.getChannelById(id);
    }

    @Transactional
    public List<Channel> findChannelsByName(String name) {
        return channelDAO.findChannelsByName(name);
    }

    @Transactional
    public List<Channel> getAllChannels() {
        return channelDAO.getAllChannels();
    }

    @Transactional
    public void updateChannel(Channel channel) {
        channelDAO.updateChannel(channel);
    }

    @Transactional
    public void deleteChannel(Channel channel) {
        channelDAO.deleteChannel(channel);
    }

    @Transactional
    public List<Channel> getGroupList(String name) {
        return channelDAO.getGroupList(name);
    }

    @Transactional
    public List<Channel> getAllChannelsByPlaylist(String name) {
        return channelDAO.getAllChannelsByPlaylist(name);
    }

    @Transactional
    public List<Channel> getChannelsByGroup(String name, String grp) {
        return channelDAO.getChannelsByGroup(name,grp);
    }

    @Transactional
    public String getChannelUrlById(int id) {
        return channelDAO.getChannelById(id).getUrl();
    }
}
