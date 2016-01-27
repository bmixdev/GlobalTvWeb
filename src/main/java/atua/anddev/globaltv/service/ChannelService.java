package atua.anddev.globaltv.service;


import atua.anddev.globaltv.entity.Channel;

import java.util.List;

public interface ChannelService {
    void addChannel(Channel channel);
    Channel getChannelById(int id);
    List<Channel> findChannelsByName(String name);
    List<Channel> getAllChannels();
    void updateChannel(Channel channel);
    void deleteChannel(Channel channel);
    List<Channel> getGroupList(String name);
    List<Channel> getAllChannelsByPlaylist(String name);
    List<Channel> getChannelsByGroup(String name, String grp);
    String getChannelUrlById(int id);
}
