package gr.blxbrgld.myList.web;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.blxbrgld.myList.service.ItemService;

/**
 * Administrator's Controller
 */
@Controller
@RequestMapping("/admin/administrator")
public class AdministratorController {

	@Inject private ItemService itemService;
	
	@RequestMapping(value = "lucene")
	public String lucene(@RequestParam(value="mode", required = false) String mode, Model model) {
		boolean synchronously = (mode != null && mode.equals("synchronously")) ? true : false;
		itemService.initializeLucene(synchronously);
		model.addAttribute("mode", synchronously);
		return "administrator/lucene";
	}
}
