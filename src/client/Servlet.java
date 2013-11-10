package client;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/servlet")
public class Servlet extends HttpServlet {

	/**
	 * The generated serial ID.
	 */
	private static final long serialVersionUID = 721321134685482010L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		HttpSession session = request.getSession();
		Integer times = (Integer) session.getAttribute("timesS");
		if (times == null)
			times = 1;

		// Wrapper.getInstance().compute(new InputBuilder(request) //
		// .setLocale(request.getParameter("locale")) //
		// .setConstraints(request.getParameter("constraints")) //
		// .setFunctions(request.getParameter("functions")) //
		// .setScenarios(request.getParameter("scenarios")));

		times += 1;
		session.setAttribute("timesS", times);
		request.setAttribute("timesR", times);
		request.setAttribute("number", request.getParameter("number"));

		RequestDispatcher dispatcher = request.getRequestDispatcher("/temp.jsp");
		dispatcher.forward(request, response);
	}
}
