package atua.anddev.globaltv.dao;

import atua.anddev.globaltv.entity.Playlist;

import java.util.List;

public interface PlaylistDAO {
    void addPlaylist(Playlist playlist);
    Playlist getPlaylistById(int id);
    List<Playlist> getPlaylistsByName(String name);
    List<Playlist> getAllPlaylists();
    void updatePlaylist(Playlist playlist);
    void deletePlaylist(Playlist playlist);
}
