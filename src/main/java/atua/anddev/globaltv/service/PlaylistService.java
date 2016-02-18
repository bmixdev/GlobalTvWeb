package atua.anddev.globaltv.service;

import atua.anddev.globaltv.entity.Playlist;

import java.util.Date;
import java.util.List;

public interface PlaylistService {
    void addPlaylist(Playlist playlist);
    Playlist getPlaylistById(int id);
    List<Playlist> getPlaylistsByName(String name);
    List<Playlist> getAllPlaylists();
    void updatePlaylist(Playlist playlist);
    void deletePlaylist(Playlist playlist);
    void downloadPlaylist(int selected);
    void openPlaylist(int selected);
    String getPlaylistNameById(int id);
    public Date getUndateDate(int selected);
}
