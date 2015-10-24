package gr.blxbrgld.myList.web;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.blxbrgld.myList.service.ActivityService;
import gr.blxbrgld.myList.service.ArtistService;
import gr.blxbrgld.myList.service.ItemService;

/**
 * Home Page Controller
 */
@Controller
public class HomeController {
	
	@Inject ItemService itemService;
	@Inject ArtistService artistService;
	@Inject ActivityService activityService;

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
		
		return "home";
	}
}