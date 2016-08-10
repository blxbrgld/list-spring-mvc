package gr.blxbrgld.mylist.web;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Exception's Controller
 * @author blxbrgld
 */
@ControllerAdvice
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    private static final String EXCEPTION_PAGE = "exception/exception";

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception exception) {
		ModelAndView model = new ModelAndView(EXCEPTION_PAGE);
        model.addObject("exceptionMessage", exception.getMessage());
        model.addObject("exceptionStackTrace", exceptionStackTraceToString(exception));
		return model;
	}
	
	/**
	 * Convert Exception's Stack Trace To String
	 * @param exception Raised Exception
	 * @return Stack Trace's String Representation
	 */
	private String exceptionStackTraceToString(Exception exception) {
        LOGGER.error("Exception", exception); //Log Before Returning
        return ExceptionUtils.getStackTrace(exception);
	}
}