package gr.blxbrgld.myList.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception exception) {
		ModelAndView model = new ModelAndView("exception/exception");
		try { //Try To Add Some Info To The Model
			model.addObject("exceptionMessage", exception.getMessage());
			model.addObject("exceptionStackTrace", exceptionStackTraceToString(exception));
		}
		catch(Exception e) { //Do Nothing Here
		
		}
		return model;
	}
	
	/**
	 * Convert Exception's Stack Trace To String
	 * @param exception Raised Exception
	 * @return Stack Trace's String Representation
	 */
	private String exceptionStackTraceToString(Exception exception) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		exception.printStackTrace(printWriter);
		return writer.toString();
	}
}