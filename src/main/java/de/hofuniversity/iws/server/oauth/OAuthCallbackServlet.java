package de.hofuniversity.iws.server.oauth;

import java.io.IOException;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hofuniversity.iws.server.data.entities.User;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.scribe.model.Token;
import org.scribe.model.Verifier;

import static de.hofuniversity.iws.server.services.LoginServiceImpl.*;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
@RemoteServiceRelativePath("oauth_callback")
public class OAuthCallbackServlet extends HttpServlet {

    public static final String OAUTH_LOGIN_ATTRIBUTE = "oauth-login";

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
        response.encodeRedirectURL(request.getContextPath() + "/PopUpCloser.html");

        Verifier verifier = null;
        if (request.getQueryString().contains("oauth_verifier=")) {
            // Parameter: oauth_verifier bei Twitter und Google 
            verifier = new Verifier(request.getParameter("oauth_verifier"));
        } else if (request.getQueryString().contains("code=")) {
            // Parameter: code bei Facebook             
            verifier = new Verifier(request.getParameter("code"));
        }

        Optional<OAuthLogin> login = getSessionAttribute(request, OAUTH_LOGIN_ATTRIBUTE);
        if (login.isPresent()) {
            OAuthLogin l = login.get();
            synchronized (l) {
                if (verifier == null) {
                } else {
                    //Somehow generate a coresponding user
                    Token accessToken = l.request.generateAccessToken(verifier);

                    User user = getOrCreateUserForAccessToken(accessToken);

                    l.successfull = true;
                    storeSessionAttribute(request, USER_ATTRIBUTE, user);
                }
                l.notify();
            }
        }
    }

    private User getOrCreateUserForAccessToken(Token accessToken) {
        //TODO DB access
        User u = new User();
        u.setFirstName("Daniel");
        u.setLastName("Heinrich");
        return u;
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
