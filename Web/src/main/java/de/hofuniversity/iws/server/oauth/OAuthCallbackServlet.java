package de.hofuniversity.iws.server.oauth;

import de.hofuniversity.iws.server.data.entities.NetworkAccountDBO;
import de.hofuniversity.iws.server.data.entities.UserDBO;
import java.io.IOException;
import java.util.*;

import com.google.common.base.Optional;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import de.hofuniversity.iws.server.data.handler.*;
import de.hofuniversity.iws.server.oauth.accessors.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.scribe.model.*;

import static de.hofuniversity.iws.server.services.LoginServiceImpl.*;

/**
 *
 * @author Andreas Arndt <andreas.arndt@hof-university.de>
 */
@RemoteServiceRelativePath("oauth_callback")
public class OAuthCallbackServlet extends HttpServlet {

    static boolean a = HibernateUtil.isConnectedToDB();
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
                try {
                    if (verifier == null) {
                    } else {
                        Token accessToken = l.request.generateAccessToken(verifier);

                        try {
                            UserDBO user = getOrCreateUserForAccessToken(accessToken, l.provider);
                            l.successfull = true;
                            storeSessionAttribute(request, USER_ATTRIBUTE, user);
                        } catch (AccessException ex) {
                            l.successfull = false;
                            //TODO logging
                        }
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                l.notify();
            }
        }
    }

    private UserDBO getOrCreateUserForAccessToken(final Token accessToken, final Providers provider) throws AccessException {
        UserDBO user = UserHandler.getUser(accessToken, provider);
        if (user == null) {
            UserDBO userData = provider.getUserDataAccessor().getUserData(accessToken);
            NetworkAccountDBO account = UserHandler.getNetworkAccount(userData,
                                                                      provider).get();
            UserDBO userByAID = UserHandler.getUserByAIDString(account.getAccountIdentificationString(), provider);
            if (userByAID != null) {
                user = userByAID;//TODO merge with userData?
            } else {
                user = UserHandler.store(userData);
                NetworkAccountHandler.store(account);
            }
        }
        final UserDBO u = user;
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<UserDBO> toAdd = new ArrayList<UserDBO>();
                outer:
                for (UserDBO newFriend : getUsersFriendsForAccessToken(accessToken, u, provider)) {
                    //next if already connected friend
                    for (UserDBO oldFriend : u.getFriends()) {
                        if (oldFriend.samePerson(newFriend)) {
                            continue outer;
                        }
                    }

                    //check if user is already in db
                    NetworkAccountDBO account = UserHandler.getNetworkAccount(newFriend, provider).get();
                    UserDBO userByAID = UserHandler.getUserByAIDString(account.getAccountIdentificationString(), provider);
                    if (userByAID != null) {
                        newFriend = userByAID;
                    } else {
                        UserHandler.store(newFriend);
                        NetworkAccountHandler.store(account);
                    }
                    toAdd.add(newFriend);
                }
                u.getFriends().addAll(toAdd);
                UserHandler.store(u);
            }
        }).start();
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
        return Collections.EMPTY_LIST;
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
