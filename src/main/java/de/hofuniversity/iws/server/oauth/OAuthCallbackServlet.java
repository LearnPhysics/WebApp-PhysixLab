package de.hofuniversity.iws.server.oauth;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.*;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hofuniversity.iws.server.data.entities.UserDBO;
import de.hofuniversity.iws.server.oauth.accessors.AccessException;
import de.hofuniversity.iws.server.oauth.accessors.FriendListAccessor;
import de.hofuniversity.iws.server.oauth.accessors.UserDataAccessor;
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
        response.sendRedirect(response.encodeURL("PopUpCloser.html"));

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

                    UserDBO user;
                    try {
                        user = getOrCreateUserForAccessToken(accessToken, l.provider);
                        Iterable<UserDBO> friendsList = getUsersFriendsForAccessToken(accessToken, user, l.provider);
                        l.successfull = true;
                        storeSessionAttribute(request, USER_ATTRIBUTE, user);
                        storeSessionAttribute(request, FRIENDS_ATTRIBUTE, friendsList);
                    } catch (AccessException ex) {
                        l.successfull = false;
                        //TODO logging
                    }
                }
                l.notify();
            }
        }
    }

    private UserDBO getOrCreateUserForAccessToken(Token accessToken, Providers provider) throws AccessException {
        //TODO DB access
        UserDataAccessor userData = provider.getUserDataAccessor();
        UserDBO user = userData.getUserData(accessToken);
        return user;
    }

    private Iterable<UserDBO> getUsersFriendsForAccessToken(Token accessToken, UserDBO u, Providers provider) {
        Optional<FriendListAccessor> friends = provider.getAccessor(FriendListAccessor.class);
        if (friends.isPresent()) {
            try {
                FriendListAccessor acc = friends.get();
                Iterable<UserDBO> friendsList = acc.getFriends(accessToken, u);
                return friendsList;
            } catch (AccessException ex) {
                ex.printStackTrace();
                //TODO token löschen
            }
        }
        return new LinkedList<UserDBO>();
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
