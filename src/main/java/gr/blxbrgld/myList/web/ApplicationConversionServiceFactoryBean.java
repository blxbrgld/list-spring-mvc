package gr.blxbrgld.mylist.web;

import gr.blxbrgld.mylist.model.Category;
import gr.blxbrgld.mylist.model.Role;
import gr.blxbrgld.mylist.model.Subtitles;
import gr.blxbrgld.mylist.service.CategoryService;
import gr.blxbrgld.mylist.service.RoleService;
import gr.blxbrgld.mylist.service.SubtitlesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Service;

/**
 * ApplicationConversionServiceFactoryBean
 * @author blxbrgld
 */
@Service
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Autowired private RoleService roleService;
	@Autowired private CategoryService categoryService;
	@Autowired private SubtitlesService subtitlesService;

	@SuppressWarnings("deprecation") //TODO Remove The deprecation
	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
	}
	
	@Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        //Register Application Converters
        installConverters(getObject());
    }
	
    public void installConverters(FormatterRegistry registry) {
		registry.addConverter(getIdToRoleConverter());
    	registry.addConverter(getStringToRoleConverter());
    	registry.addConverter(getIdToCategoryConverter());
    	registry.addConverter(getStringToCategoryConverter());
    	registry.addConverter(getIdToSubtitlesConverter());
    	registry.addConverter(getStringToSubtitlesConverter());
    }
    
    public Converter<Long, Role> getIdToRoleConverter() {
        return new Converter<Long, Role>() {
            @Override
            public Role convert(Long id) {
                return roleService.getRole(id);
            }
        };
    }
	
    public Converter<String, Role> getStringToRoleConverter() {
        return new Converter<String, Role>() {
            @Override
            public Role convert(String id) {
            	if("".equals(id)) {
                    return null;
                }
            	else {
                    return getObject().convert(Long.valueOf(id), Role.class);
                }
            }
        };
    }
    
    public Converter<Long, Category> getIdToCategoryConverter() {
    	return new Converter<Long, Category>() {
    		@Override
            public Category convert(Long id) {
    			return categoryService.getCategory(id);
    		}
    	};
    }
    
    public Converter<String, Category> getStringToCategoryConverter() {
    	return new Converter<String, Category>() {
    		@Override
            public Category convert(String id) {
    			if("".equals(id)) {
                    return null;
                }
    			else {
                    return getObject().convert(Long.valueOf(id), Category.class);
                }
    		}
    	};
    }
    
    public Converter<Long, Subtitles> getIdToSubtitlesConverter() {
    	return new Converter<Long, Subtitles>() {
    		@Override
            public Subtitles convert(Long id) {
    			return subtitlesService.getSubtitles(id);
    		}
    	};
    }
    
    public Converter<String, Subtitles> getStringToSubtitlesConverter() {
    	return new Converter<String, Subtitles>() {
    		@Override
            public Subtitles convert(String id) {
    			if("".equals(id)) {
                    return null;
                }
    			else {
                    return getObject().convert(Long.valueOf(id), Subtitles.class);
                }
    		}
    	};
    }
}