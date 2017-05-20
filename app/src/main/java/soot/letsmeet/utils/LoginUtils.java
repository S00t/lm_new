package soot.letsmeet.utils;

import soot.letsmeet.webservices.requests.OAuthConstants;

/**
 * Created by Soot on 20/05/2017.
 */

public class LoginUtils {


    public static String getAuthorizationHeaderForAccessToken(String accessToken) {
        return OAuthConstants.BEARER + " " + accessToken;
    }

    public static String getBasicAuthorizationHeader(String username,
                                                     String password) {
        return OAuthConstants.BASIC + " "
                + TokenUtil.encodeCredentials(username, password);
    }
}
