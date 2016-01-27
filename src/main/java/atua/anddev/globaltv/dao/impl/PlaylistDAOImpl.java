package atua.anddev.globaltv.dao.impl;

import atua.anddev.globaltv.dao.PlaylistDAO;
import atua.anddev.globaltv.entity.Playlist;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoArgsConstructor
@Repository
public class PlaylistDAOImpl implements PlaylistDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addPlaylist(Playlist playlist) {
        sessionFactory.getCurrentSession().save(playlist);
    }

    public Playlist getPlaylistById(int id) {
        return sessionFactory.getCurrentSession().load(Playlist.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Playlist> getPlaylistsByName(String name) {
        String query = "from Playlist where name = :name";
        return sessionFactory.getCurrentSession().createQuery(query).setParameter("name", name).list();
    }

    @SuppressWarnings("unchecked")
    public List<Playlist> getAllPlaylists() {
        return sessionFactory.getCurrentSession().createQuery("from Playlist").list();
    }

    public void updatePlaylist(Playlist playlist) {
        sessionFactory.getCurrentSession().update(playlist);
    }

    public void deletePlaylist(Playlist playlist) {
        sessionFactory.getCurrentSession().delete(playlist);
    }
}
