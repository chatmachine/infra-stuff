import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class TopTrendsServlet
 */
public class TopTrendsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String dt = null; 
    public TopTrendsServlet() {
            super();
    }
 
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 1000;
        dt = request.getParameter("datePicker"); 
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        TopTrendsDAO dao = new TopTrendsDAO();
        List<TopTrends> list = dao.viewAllMetrics((page-1)*recordsPerPage,recordsPerPage,dt);
        int noOfRecords = dao.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("metricList", list);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher view = request.getRequestDispatcher("displayCPU.jsp");
        view.forward(request, response);
    }

}
