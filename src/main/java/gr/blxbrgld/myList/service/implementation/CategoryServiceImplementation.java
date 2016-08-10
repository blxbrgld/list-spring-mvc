package gr.blxbrgld.mylist.service.implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import gr.blxbrgld.mylist.dao.CategoryDao;
import gr.blxbrgld.mylist.dao.ItemDao;
import gr.blxbrgld.mylist.model.Category;
import gr.blxbrgld.mylist.service.CategoryService;

/**
 * Category's Service Implementation
 * @author blxbrgld
 */
@Service
@Transactional
@PreAuthorize("denyAll")
public class CategoryServiceImplementation implements CategoryService {

	@Autowired private CategoryDao categoryDao;
	@Autowired private ItemDao itemDao;

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void persistCategory(Category category, Errors errors) {
		validateTitle(category, errors);
		//validateParent() Is Not Needed On persistCategory()
		boolean valid = !errors.hasErrors();
		if(valid) {
            categoryDao.persist(category);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public void mergeCategory(Category category, Errors errors) {
		validateTitle(category, errors);
		validateParent(category, errors);
		boolean valid = !errors.hasErrors();
		if(valid) {
            categoryDao.merge(category);
        }
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public List<Category> getCategories(String property, String order) {
		return categoryDao.getAll(property, order);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public Category getCategory(Long id) {
		return categoryDao.get(id);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public Category getCategory(String title) {
		return categoryDao.findByTitle(title);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	@PreAuthorize("hasRole('Administrator')")
	public boolean deleteCategory(Long id) {
		Category category = categoryDao.get(id);
		if(categoryDao.findByParent(category).isEmpty() && !itemDao.havingCategoryExists(category)) { //No Categories With This Parent or Items With This Category Exist
			categoryDao.deleteById(id);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Validate Uniqueness Of Category's Title
	 * @param category Category Object
	 * @param errors BindingResult Errors Of Category Form
	 */
	private void validateTitle(Category category, Errors errors) {
		Category existing = categoryDao.findByTitle(category.getTitle());
		if(existing != null && !existing.getId().equals(category.getId()) ) { //Duplicate Title
			errors.rejectValue("title", "error.duplicate");
		}
	}
	
	/**
	 * Ensure That Given Category Title and Parent Category Are Not The Same
	 * @param category Category Object
	 * @param errors BindingResult Errors Of Category Form
	 */
	private void validateParent(Category category, Errors errors) {
		if(category.getParent() != null && category.getTitle().equals(category.getParent().getTitle()))
			errors.rejectValue("parent", "error.inheritance");		
	}
}