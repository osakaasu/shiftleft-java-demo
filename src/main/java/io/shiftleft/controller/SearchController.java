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
			// Validate input to prevent code injection
			if (!isValidInput(foo)) {
				throw new IllegalArgumentException("Invalid input");
			}

			// Use of SpEL expressions requires explicit trust model configuration
			StandardEvaluationContext context = new StandardEvaluationContext();
			context.setVariable("request", request);
			context.setVariable("response", response);

			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(foo);
			return exp.getValue(context).toString();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}


