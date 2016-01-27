package atua.anddev.globaltv.dao.impl;

import atua.anddev.globaltv.dao.ChannelDAO;
import atua.anddev.globaltv.entity.Channel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoArgsConstructor
@Repository
public class ChannelDAOImpl implements ChannelDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addChannel(Channel channel) {
        sessionFactory.getCurrentSession().save(channel);
    }

    public Channel getChannelById(int id) {
        return sessionFactory.getCurrentSession().load(Channel.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Channel> findChannelsByName(String name) {
        String query = "from Channel where name = :name";
        return sessionFactory.getCurrentSession().createQuery(query).setParameter("name", name).list();
    }

    @SuppressWarnings("unchecked")
    public List<Channel> getAllChannels() {
        return sessionFactory.getCurrentSession().createQuery("from Channel").list();
    }

    public void updateChannel(Channel channel) {
        sessionFactory.getCurrentSession().update(channel);
    }

    public void deleteChannel(Channel channel) {
        sessionFactory.getCurrentSession().delete(channel);
    }

    public void deletePlist(String name) {
        String query = "delete from Channel where plistname = :name";
        sessionFactory.getCurrentSession().createQuery(query).setParameter("name", name).executeUpdate();
    }

    public List<Channel> getGroupList(String name) {
        String query = "select distinct category from Channel where plistname = :name";
        return sessionFactory.getCurrentSession().createQuery(query).setParameter("name", name).list();
    }

    public List<Channel> getAllChannelsByPlaylist(String name) {
        String query = "from Channel where plistname = :name";
        return sessionFactory.getCurrentSession().createQuery(query).setParameter("name", name).list();
    }

    public List<Channel> getChannelsByGroup(String name, String grp) {
        String query = "from Channel where plistname = :name and category = :grp";
        return sessionFactory.getCurrentSession().createQuery(query).setParameter("name", name).setParameter("grp", grp).list();
    }
}
