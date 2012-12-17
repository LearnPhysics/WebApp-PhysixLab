package de.hofuniversity.iws.server.oauth;

import java.io.IOException;
import java.util.logging.*;

import de.hofuniversity.iws.server.data.entities.*;
import de.hofuniversity.iws.server.data.handler.*;
import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hofuniversity.iws.server.oauth.accessors.*;
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
    public static final String[] VERIFICATION_PROPERTIES = new String[]{
        // Parameter: oauth_verifier bei Twitter und Google 
        "oauth_verifier",
        // Parameter: code bei Facebook             
        "code"
    };

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
        response.sendRedirect(response.encodeURL("PopUpCloser.html")) ;
                
        Verifier verifier = null;
        for (String p : VERIFICATION_PROPERTIES) {
            if (request.getQueryString().contains(p + '=')) {
                verifier = new Verifier(request.getParameter(p));
                break;
            }
        }

        Optional<OAuthLogin> login = getSessionAttribute(request, OAUTH_LOGIN_ATTRIBUTE);
        if (login.isPresent()) {
            OAuthLogin l = login.get();
            synchronized (l) {
                if (verifier == null) {
                } else {
                    //Somehow generate a coresponding user
                    Token accessToken = l.request.generateAccessToken(verifier);

                    User user = getOrCreateUserForAccessToken(l.provider, accessToken);

                    l.successfull = true;
                    storeSessionAttribute(request, USER_ATTRIBUTE, user);
                }
                l.notify();
            }
        }
    }

    private User getOrCreateUserForAccessToken(Providers prov, Token accessToken) {
//        Optional<UserDataAccessor> accessor = prov.getAccessor(UserDataAccessor.class);
//        if(accessor.isPresent())
//        {
//            try {
//                User user = accessor.get().getUserData(accessToken);
//                Optional<NetworkAccount> networkAccount = user.getNetworkAccount(prov);
//                NetworkAccount na = NetworkAccountHandler.getNetworkAccountEntity(prov.name(), networkAccount.get().getAccountIdentificationString(), false);
//                return na.getUser();
//            } catch (AccessException ex) {
//            }
//        }
        return new User();
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
