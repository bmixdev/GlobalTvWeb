package atua.anddev.globaltv.controller;

import atua.anddev.globaltv.entity.Channel;
import atua.anddev.globaltv.entity.Playlist;
import atua.anddev.globaltv.service.ChannelService;
import atua.anddev.globaltv.service.PlaylistService;
import atua.anddev.globaltv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Date;
import java.util.List;

@EnableWebMvc
@RestController
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    PlaylistService playlistService;

    @Autowired
    ChannelService channelService;

    int selectedPlst = 1;
    int selectedChn = 1;
    int grpSize = 0;
    int chSize = 0;
    String chUrl = "";
    String playlistName;
    List<Channel> listGroup = null;
    List<Channel> listChannel = null;
    String updateDate;

    @RequestMapping(value = "/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        List<Playlist> listPlaylists = playlistService.getAllPlaylists();
        updateDate = (playlistService.getUndateDate(selectedPlst) == null) ?
                "playlist not loaded, press update button" : playlistService.getUndateDate(selectedPlst).toString();
        modelAndView.addObject("playList", listPlaylists);
        modelAndView.addObject("selectedPlst", selectedPlst);
        modelAndView.addObject("selectedChn", selectedChn);
        modelAndView.addObject("grpList", listGroup);
        modelAndView.addObject("channelList", listChannel);
        modelAndView.addObject("grpSize", grpSize);
        modelAndView.addObject("chSize", chSize);
        modelAndView.addObject("updateDate", updateDate);
        return modelAndView;
    }

    @RequestMapping(value = "/selected_playlist", method = RequestMethod.GET)
    public ModelAndView playlistInfo(@RequestParam("plstnum") int selected, @RequestParam("action") String action) {
        selectedPlst = selected;
        if (action.equals("update")) {
            playlistService.downloadPlaylist(selected);
        } else if (action.equals("open")) {
            playlistService.openPlaylist(selected);
            playlistName = playlistService.getPlaylistNameById(selected);
            listGroup = channelService.getGroupList(playlistName);
            listChannel = channelService.getAllChannelsByPlaylist(playlistName);
            grpSize = channelService.getGroupList(playlistName).size();
            chSize = channelService.getAllChannelsByPlaylist(playlistName).size();
            updateDate = (playlistService.getUndateDate(selectedPlst) == null) ?
                    "playlist not loaded, press update button" : playlistService.getUndateDate(selectedPlst).toString();
        }
        return home();
    }

    @RequestMapping(value = "/selected_catlist", method = RequestMethod.GET)
    public ModelAndView getCatSelection(@RequestParam("ChangeCat") String changeCat) {
        if (changeCat.equals("All")) {
            listChannel = channelService.getAllChannelsByPlaylist(playlistName);
            chSize = channelService.getAllChannelsByPlaylist(playlistName).size();
        } else {
            listChannel = channelService.getChannelsByGroup(playlistName, changeCat);
            chSize = channelService.getChannelsByGroup(playlistName, changeCat).size();
        }
        return home();
    }

    @RequestMapping(value = "/selected_chlist", method = RequestMethod.GET)
    public ModelAndView getChannelSelection(@RequestParam("chname") int chId) {
        selectedChn = chId;
        chUrl = channelService.getChannelUrlById(chId);
        System.out.println(selectedChn);
        if (chUrl.startsWith("acestream://"))
            return viewPlayerAce();
        else
            return viewPlayerVlc();
    }

    @RequestMapping(value = "/view_player1")
    public ModelAndView viewPlayerAce() {
        ModelAndView modelAndView = new ModelAndView("player_ace");
        modelAndView.addObject("chUrl", chUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/view_player2")
    public ModelAndView viewPlayerVlc() {
        ModelAndView modelAndView = new ModelAndView("player_vlc");
        modelAndView.addObject("chUrl", chUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/getPlaylists", method = RequestMethod.GET)
    public List<Playlist> list2Playlists() {
        return playlistService.getAllPlaylists();
    }

}
