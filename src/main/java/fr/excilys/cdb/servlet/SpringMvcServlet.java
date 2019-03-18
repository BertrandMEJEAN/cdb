package fr.excilys.cdb.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/SpringMvcTest")
public class SpringMvcServlet {

		@GetMapping
		public String testSpring() {
			return "SpringMvcTest";
		}
}
