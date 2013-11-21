package globalopt.ws.client;

import globalopt.ws.model.Helper;
import globalopt.ws.model.ParetoBuilder;
import globalopt.ws.model.Solution;

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

		ParetoBuilder builder = new ParetoBuilder() //
				.setLocale(request.getParameter("locale")) //
				.setConstraints(request.getParameter("constraints")) //
				.setFunctions(request.getParameter("functions")) //
				.setScenarios(request.getParameter("scenarios"));

		Integer computations = (Integer) session.getAttribute("computations");
		if (computations == null)
			computations = 0;

//		@SuppressWarnings("unchecked")
//		Map<Integer, Ranges> contexts = (Map<Integer, Ranges>) session.getAttribute("contexts");
//		if (contexts == null)
//			contexts = new Hashtable<Integer, Ranges>();
//		Ranges context = contexts.get(builder.hashCode());
//		if (context == null) {
//			try {
//				context = new Ranges(builder);
//			} catch (InterruptedException e) {
//				throw new ServletException(e);
//			}
//			contexts.put(context.getHash(), context);
//		}

		Solution solution = Helper.compute(builder);
		computations += 1;

		session.setAttribute("computations", computations);
//		session.setAttribute("contexts", contexts);

		request.setAttribute("computations", computations);
		request.setAttribute("timestamp", solution.getDuration());
		request.setAttribute("solution", solution);

		// if (computations < 1000000)
		// throw new IllegalArgumentException("" +
		// Arrays.toString(solution.objectives()));

		request.setAttribute("comparison.categories", solution.getComparison().getCategories());
		request.setAttribute("comparison.series", solution.getComparison().getSeries());
		request.setAttribute("categories", solution.getCosts().getNames());
		request.setAttribute("costs.series", solution.getCosts().getSeries());
		request.setAttribute("electrics.series", solution.getElectrics().getSeries());
		request.setAttribute("thermals.series", solution.getThermals().getSeries());

		request.setAttribute("scenarios", solution.names());
		request.setAttribute("objectives", solution.objectives());
		for (int o = 0; o < solution.dimensions(); o++)
			request.setAttribute("function" + (o + 1) + ".series", solution.getFunction(o).getSeries());

		RequestDispatcher dispatcher = request.getRequestDispatcher("/includes/content.jsp");
		dispatcher.forward(request, response);
		System.gc();
	}
}
