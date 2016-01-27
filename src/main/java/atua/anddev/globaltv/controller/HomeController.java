package atua.anddev.globaltv.controller;

import atua.anddev.globaltv.entity.Channel;
import atua.anddev.globaltv.entity.Playlist;
import atua.anddev.globaltv.entity.User;
import atua.anddev.globaltv.service.ChannelService;
import atua.anddev.globaltv.service.PlaylistService;
import atua.anddev.globaltv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
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
    String chUrl="";
    String playlistName;
    List<Channel> listGroup = null;
    List<Channel> listChannel = null;

    @RequestMapping(value = "/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        List<Playlist> listPlaylists = playlistService.getAllPlaylists();
        modelAndView.addObject("playList", listPlaylists);
        modelAndView.addObject("selectedPlst", selectedPlst);
        modelAndView.addObject("selectedChn", selectedChn);
        modelAndView.addObject("grpList", listGroup);
        modelAndView.addObject("channelList", listChannel);
        modelAndView.addObject("grpSize", grpSize);
        modelAndView.addObject("chSize", chSize);
        modelAndView.addObject("chUrl", chUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/selected_playlist", method = RequestMethod.GET)
    public ModelAndView playlistInfo(@RequestParam("plstnum") int selected, @RequestParam("action") String action) {
        selectedPlst = selected;
        if (action.equals("update"))
        {
            playlistService.downloadPlaylist(selected);
        } else
        if (action.equals("open"))
        {
            playlistService.openPlaylist(selected);
            playlistName = playlistService.getPlaylistNameById(selected);
            listGroup = channelService.getGroupList(playlistName);
            listChannel = channelService.getAllChannelsByPlaylist(playlistName);
            grpSize = channelService.getGroupList(playlistName).size();
            chSize = channelService.getAllChannelsByPlaylist(playlistName).size();
        }
        return home();
    }

    @RequestMapping(value = "/selected_catlist", method = RequestMethod.GET)
    public ModelAndView getCatSelection(@RequestParam("ChangeCat") String changeCat) {
        if (changeCat.equals("All"))
        {
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
        return home();
    }

    @RequestMapping(value = "/show_user")
    public ModelAndView homeu() {
        ModelAndView modelAndView = new ModelAndView("home2");
        List<User> listUsers = userService.getAllUsers();
        modelAndView.addObject("userList", listUsers);
        return modelAndView;
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public ModelAndView addNewUser(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("full_name") String fullName) {
        userService.addUser(new User(email, password, fullName));
        return homeu();
    }


}
