package de.hofuniversity.iws.server;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;
/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
@RemoteServiceRelativePath("oauth_callback")
public class OAuthCallbackServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        OAuthLogin oauth = (OAuthLogin) request.getSession().getAttribute("obj_OAuthClass");
        
        if(request.getQueryString().contains("oauth_verifier="))
        {
            // Parameter: oauth_verifier bei Twitter und Google 
            oauth.set_OAUTH_VERIFIER(request.getParameter("oauth_verifier").toString());
        }
        if(request.getQueryString().contains("code="))
        {
            // Parameter: code bei Facebook             
            oauth.set_OAUTH_VERIFIER(request.getParameter("code").toString());
        }    
        
       response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OAuthCallbackServlet</title></head><body>");
            out.println("<script type=\"text/javascript\">");
            out.println("function popupclose () {");
            out.println(" fenster = window.close();");
            out.println(" return false; }");
            out.println("</script>");
            out.println("</head>");
            out.println("<body onload=\"popupclose();\">");
   /*         out.println("AuthorizeURL: " + oauth.get_AUTHORIZE_URL() + "");
            out.println("Verifier: " + oauth.get_OAUTH_VERIFIER().getValue());*/
            out.println("</body>");
            out.println("</html>");          
            out.close();
      }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
