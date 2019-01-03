package gr.blxbrgld.mylist.utilities;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Defining CommonsMultipartFileValid Constraint Annotation
 * @author blxbrgld
 */
@Target({ANNOTATION_TYPE, METHOD, FIELD, PARAMETER, CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CommonsMultipartFileValidator.class)
@Documented
public @interface CommonsMultipartFileValid {

	String message() default "Only .jpg, .jpeg, .png Files are Allowed, with Size < 2Mb";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	String fileSize() default "2000000"; //2 MB
	String[] supportedFileTypes() default { "jpg", "jpeg", "png" }; 
}
