package io.shiftleft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Search login
 */
@Controller
public class SearchController {

  @RequestMapping(value = "/search/user", method = RequestMethod.GET)
	@RequestMapping(value = "/search/user", method = RequestMethod.GET)
	public String doGetSearch(@RequestParam String foo, HttpServletResponse response, HttpServletRequest request) {
		try {
			foo = WebUtils.htmlEscape(foo); // Escaping input to prevent XSS attacks
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(foo);
			Object message = exp.getValue();
			return message.toString();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "An error occurred while processing your request.";
		}
	}


