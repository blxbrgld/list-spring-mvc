package gr.blxbrgld.mylist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.blxbrgld.mylist.service.ItemService;

/**
 * Administrator's Controller
 * @author blxbrgld
 */
@Controller
@RequestMapping("/admin/administrator")
public class AdministratorController {

	@Autowired private ItemService itemService;

    private static final String LUCENE_PAGE = "administrator/lucene";
	
	@RequestMapping(value = "lucene")
	public String lucene(@RequestParam(value="mode", required = false) String mode, Model model) {
		boolean synchronously = (mode != null && "synchronously".equals(mode)) ? true : false;
        itemService.initializeLucene(synchronously);
        model.addAttribute("mode", synchronously);
        return LUCENE_PAGE;
	}
}
