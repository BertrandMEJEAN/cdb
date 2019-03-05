package fr.excilys.cdb.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.cdb.dto.ComputerDto;
import fr.excilys.cdb.mapper.ComputerMapper;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.ComputerService;
import fr.excilys.cdb.exception.ValidatorException;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] selectedComp = request.getParameter("selection").split(",");
		ComputerDto dto = new ComputerDto();
		Computer deleteComp = new Computer();
		
		for(String computer : selectedComp) {
			dto.setId(computer);
			
			try {
				deleteComp = ComputerMapper.getInstance().dtoToObject(dto);
				ComputerService.getInstance().delete(deleteComp);
			}catch(ValidatorException e) {
				e.getMessage();
			}			
		}
		
		this.getServletContext().getRequestDispatcher("/").forward(request, response);
	}

}
