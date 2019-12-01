package com.example.todorestful.controller;

import java.util.Collections;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@Bean
	public ErrorViewResolver customErrorViewResolver() {
		/* Model and View - holds the model that would populate a view and view itself. 
		 * The Model is useful if we are using a JSP, but in this case we are not (Hence the empty map). Essentially because we are using Angular to navigate
		 * URLS and not Spring, Spring can't not find these URLS. So this will forward us back to index.html which successfully navigated
		 * to the intended URL.
		 * 
		 *  NOTE: If HttpStatus is NOT_FOUND or FORBIDDEN we will forward to index.html which will take care of the Statuses.
		 *  
		 */
		final ModelAndView redirectToIndexHtml = new ModelAndView("forward:/index.html", Collections.EMPTY_MAP, HttpStatus.OK);
		//-> is a lambda expression. -> seperates paramters on the left with the expression on the right.
		return (request, status, model) -> status == HttpStatus.NOT_FOUND || status == HttpStatus.FORBIDDEN ? redirectToIndexHtml : null ;
	}
}
