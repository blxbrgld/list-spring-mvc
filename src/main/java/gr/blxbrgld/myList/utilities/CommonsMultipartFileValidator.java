package gr.blxbrgld.myList.utilities;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import gr.blxbrgld.myList.utilities.CommonsMultipartFileValid;

/**
 * Implementing a Constraint Validator For The Constraint CommonsMultipartFileValid
 */
public class CommonsMultipartFileValidator implements ConstraintValidator<CommonsMultipartFileValid, CommonsMultipartFile> {

	private String fileSize;
	private String[] supportedFileTypes;
	
	/**
	 * Access To The Attribute Values That Should Be Validated
	 * @param constraintAnnotation CommonsMultipartFileValid
	 */
	@Override
	public void initialize(CommonsMultipartFileValid constraintAnnotation) {
		fileSize = constraintAnnotation.fileSize();
		supportedFileTypes = constraintAnnotation.supportedFileTypes();
	}

	/**
	 * Validation Logic
	 * @param value CommonsMultipartFile
	 * @param context ConstraintValidatorContext
	 */
	@Override
	public boolean isValid(CommonsMultipartFile value, ConstraintValidatorContext context) {
		if(value == null || value.isEmpty()) { //Not Required
			return true;
		}
		else {
			return validateFileExtension(value) && validateFileSize(value);
		}
	}
	
	/**
	 * Validate File Extension If A File Is Given
	 * @param value CommonsMultipartFile
	 * @return TRUE or FALSE
	 */
	private boolean validateFileExtension(CommonsMultipartFile value) {
		int dotPosition = value.getOriginalFilename().lastIndexOf(".");  
		boolean result = false;
		if(dotPosition != -1) {
			String extension = value.getOriginalFilename().substring(dotPosition + 1);
			final List<String> supportedExtensions = Arrays.asList(supportedFileTypes);
			for(String supportedExtension : supportedExtensions) {
				if(extension.equalsIgnoreCase(supportedExtension)) {  
					result = true;
					break;  
				}  
			}  
		}
		return result;
	}
	
	/**
	 * Validate File Size If A File Is Given
	 * @param value CommonsMultipartFile
	 * @return TRUE or FALSE
	 */
	private boolean validateFileSize(CommonsMultipartFile value) {
		boolean result = false;
		if(!value.isEmpty()) {
			if(value.getSize() != 0 && value.getSize() <= Long.valueOf(fileSize)) {
				result = true;
			}
		}
		return result;
	}
}
