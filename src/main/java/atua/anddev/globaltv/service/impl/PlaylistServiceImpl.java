package atua.anddev.globaltv.service.impl;

import atua.anddev.globaltv.dao.ChannelDAO;
import atua.anddev.globaltv.dao.PlaylistDAO;
import atua.anddev.globaltv.entity.Channel;
import atua.anddev.globaltv.entity.Playlist;
import atua.anddev.globaltv.service.PlaylistService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@NoArgsConstructor
@Service
public class PlaylistServiceImpl implements PlaylistService {

    static Boolean playlistWithGroup;

    @Autowired
    private PlaylistDAO playlistDAO;

    @Autowired
    private ChannelDAO channelDAO;

    @Transactional
    public void addPlaylist(Playlist playlist) {
        playlistDAO.addPlaylist(playlist);
    }

    @Transactional
    public Playlist getPlaylistById(int id) {
        return playlistDAO.getPlaylistById(id);
    }

    @Transactional
    public List<Playlist> getPlaylistsByName(String name) {
        return playlistDAO.getPlaylistsByName(name);
    }

    @Transactional
    public List<Playlist> getAllPlaylists() {
        return playlistDAO.getAllPlaylists();
    }

    @Transactional
    public void updatePlaylist(Playlist playlist) {
        playlistDAO.updatePlaylist(playlist);
    }

    @Transactional
    public void deletePlaylist(Playlist playlist) {
        playlistDAO.deletePlaylist(playlist);
    }

    @Transactional
    public String getPlaylistNameById(int selected)
    {
        Playlist playlist = getPlaylistById(selected);
        String name = playlist.getName();
        return name;
    }

    @Transactional
    public void downloadPlaylist(int selected)
    {
        Playlist playlist = getPlaylistById(selected);
        String filename = getFileName(playlist.getName());
        String url = playlist.getUrl();
        int type = playlist.getType();
        String name = playlist.getName();
        System.out.println(filename+"-"+url);
        try {
            saveUrl(filename, url);
            playlist.setUpdateDate(new Date());
            updatePlaylist(playlist);
            System.out.println("success");
            processPlaylist(name, filename, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void openPlaylist(int selected) {

    }

    public static String getFileName(String input) {
        String output = "playlist_" + input + ".m3u";
        output = output.replace(" ", "_");
        output = output.replace("(", "_");
        output = output.replace(")", "_");
        output = output.replace("@", "_");
        return output;
    }

    public static void saveUrl(final String filename, final String urlString)
            throws MalformedURLException, IOException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            URL url = new URL(urlString);
            URLConnection uc;
            uc = url.openConnection();
            uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            uc.connect();
            in = new BufferedInputStream(uc.getInputStream());
            fout = new FileOutputStream(filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }

    public void processPlaylist(String plistname, String fname, int type) {
        playlistWithGroup = false;
        String lineStr, chName = "", chCategory = "", chLink = "";
        String groupName = "", groupName2 = "";
        Channel channel;
        channelDAO.deletePlist(plistname);
        try {
            InputStream myfile = new FileInputStream(fname);
            Scanner myInputFile = new Scanner(myfile, "UTF8");
            while (myInputFile.hasNext()) {
                lineStr = myInputFile.next();
                if (lineStr.startsWith("acestream:") || lineStr.startsWith("http:") || lineStr.startsWith("https:")
                        || lineStr.startsWith("rtmp:") || lineStr.startsWith("rtsp:") || lineStr.startsWith("mmsh:")
                        || lineStr.startsWith("mms:") || lineStr.startsWith("rtmpt:")) {
                    chLink = lineStr;
                    if (chName.startsWith("ALLFON.TV")) {
                        chName = chName.substring(10, chName.length());
                    }
                    if (chName.startsWith(" ")) {
                        chName = chName.substring(1, chName.length());
                    }
                    //chCategory = translate(chCategory);
                    channel = new Channel(chName, chCategory, chLink, plistname);
                    channelDAO.addChannel(channel);
                    if (!chCategory.equals(""))
                        playlistWithGroup = true;
                    chName = "";
                    chCategory = "";
                    chLink = "";
                    groupName = "";
                    groupName2 = "";
                }
                if ((type == 1) && lineStr.startsWith("#EXTINF:-1,") && (lineStr.indexOf("(") == lineStr.lastIndexOf("("))) {
                    chName = lineStr.substring(11, lineStr.indexOf("(") - 1);
                    chCategory = lineStr.substring(lineStr.lastIndexOf("(") + 1, lineStr.lastIndexOf(")"));
                    playlistWithGroup = true;
                }
                if ((type == 1) && lineStr.startsWith("#EXTINF:-1,") && (lineStr.indexOf("(") != lineStr.lastIndexOf("("))) {
                    chName = lineStr.substring(11, lineStr.lastIndexOf("(") - 1);
                    chCategory = lineStr.substring(lineStr.lastIndexOf("(") + 1, lineStr.lastIndexOf(")"));
                    playlistWithGroup = true;
                }
                if (lineStr.startsWith("#EXTINF:") && (type != 1)) {
                    chName = lineStr.substring(lineStr.indexOf(",") + 1, lineStr.length());
                }
                if (lineStr.contains("group-title=") && lineStr.contains(",") && (lineStr.substring(lineStr.indexOf("group-title="), lineStr.indexOf("group-title=") + 12).equals("group-title="))) {
                    groupName = lineStr.substring(lineStr.indexOf("group-title=") + 13, lineStr.indexOf('"', lineStr.indexOf("group-title=") + 13));
                    playlistWithGroup = true;
                }
                if (lineStr.contains("#EXTGRP:") && (lineStr.substring(lineStr.indexOf("#EXTGRP:"), lineStr.indexOf("#EXTGRP:") + 8).equals("#EXTGRP:"))) {
                    groupName2 = lineStr.substring(lineStr.indexOf("#EXTGRP:") + 8, lineStr.length());
                    playlistWithGroup = true;
                }
                if (!groupName.equals("")) {
                    chCategory = groupName;
                } else if (!groupName2.equals("")) {
                    groupName2 = groupName2.replace(" ","");
                    chCategory = groupName2;
                }
            }
            myInputFile.close();
        } catch (Exception e) {
            //logger.warning("Opening Playlist failed :"+e);
        }
    }
}
