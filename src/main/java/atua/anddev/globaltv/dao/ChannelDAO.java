package atua.anddev.globaltv.dao;

import atua.anddev.globaltv.entity.Channel;

import java.util.List;

public interface ChannelDAO {
    void addChannel(Channel channel);
    Channel getChannelById(int id);
    List<Channel> findChannelsByName(String name);
    List<Channel> getAllChannels();
    void updateChannel(Channel channel);
    void deleteChannel(Channel channel);
    void deletePlist(String name);
    List<Channel> getGroupList(String name);
    List<Channel> getAllChannelsByPlaylist(String name);
    List<Channel> getChannelsByGroup(String name, String grp);
}
