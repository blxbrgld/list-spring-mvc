package gr.blxbrgld.mylist.web;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.blxbrgld.mylist.service.ActivityService;
import gr.blxbrgld.mylist.service.ArtistService;
import gr.blxbrgld.mylist.service.ItemService;

/**
 * Home Page Controller
 */
@Controller
public class HomeController {
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private ArtistService artistService;

	@Autowired
	private ActivityService activityService;

    private static final String HOME_PAGE = "home";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		/*
		 * Music Items
		 */
		model.addAttribute("totalPopular", itemService.countItemsHavingCategory("Popular Music"));
		model.addAttribute("totalClassical", itemService.countItemsHavingCategory("Classical Music"));
		model.addAttribute("totalGreek", itemService.countItemsHavingCategory("Greek Music"));
		model.addAttribute("lastMusicDate", dateFormat.format(itemService.findLastDateHavingParent("Music").getDateUpdated().getTime()));
		model.addAttribute("nextMusic", itemService.findNextPlaceHavingParent("Music"));
		/*
		 * Film Items
		 */
		model.addAttribute("totalDVD", itemService.countItemsHavingCategory("DVD Films"));
		model.addAttribute("totalDivX", itemService.countItemsHavingCategory("DivX Films"));
		model.addAttribute("lastFilmDate", dateFormat.format(itemService.findLastDateHavingParent("Films").getDateUpdated().getTime()));
		model.addAttribute("nextFilm", itemService.findNextPlaceHavingParent("Films"));	
		/*
		 * Artists
		 */
		model.addAttribute("totalArtists", artistService.countArtists());
		model.addAttribute("lastArtistDate", dateFormat.format(artistService.findLastArtist().getDateUpdated().getTime()));
		model.addAttribute("totalActivities", activityService.countActivities());
		
		return HOME_PAGE;
	}
}