package gr.blxbrgld.myList.web;

import javax.inject.Inject;

import gr.blxbrgld.myList.model.Category;
import gr.blxbrgld.myList.model.Role;
import gr.blxbrgld.myList.model.Subtitles;
import gr.blxbrgld.myList.service.CategoryService;
import gr.blxbrgld.myList.service.RoleService;
import gr.blxbrgld.myList.service.SubtitlesService;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Service;

/**
 * ApplicationConversionServiceFactoryBean
 */
@Service
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Inject private RoleService roleService;
	@Inject private CategoryService categoryService;
	@Inject private SubtitlesService subtitlesService;
	
	@SuppressWarnings("deprecation")
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
            public Role convert(Long id) {
                return roleService.getRole(id);
            }
        };
    }
	
    public Converter<String, Role> getStringToRoleConverter() {
        return new Converter<String, Role>() {
            public Role convert(String id) {
            	if(id.equals("")) return null;
            		else return getObject().convert(Long.valueOf(id), Role.class);
            }
        };
    }
    
    public Converter<Long, Category> getIdToCategoryConverter() {
    	return new Converter<Long, Category>() {
    		public Category convert(Long id) {
    			return categoryService.getCategory(id);
    		}
    	};
    }
    
    public Converter<String, Category> getStringToCategoryConverter() {
    	return new Converter<String, Category>() {
    		public Category convert(String id) {
    			if(id.equals("")) return null;
    				else return getObject().convert(Long.valueOf(id), Category.class); 
    		}
    	};
    }
    
    public Converter<Long, Subtitles> getIdToSubtitlesConverter() {
    	return new Converter<Long, Subtitles>() {
    		public Subtitles convert(Long id) {
    			return subtitlesService.getSubtitles(id);
    		}
    	};
    }
    
    public Converter<String, Subtitles> getStringToSubtitlesConverter() {
    	return new Converter<String, Subtitles>() {
    		public Subtitles convert(String id) {
    			if(id.equals("")) return null;
    				else return getObject().convert(Long.valueOf(id), Subtitles.class); 
    		}
    	};
    }
}