package soot.letsmeet.utils;

import android.util.Base64;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soot.letsmeet.models.OAuth2Config;
import soot.letsmeet.models.Token;
import soot.letsmeet.webservices.requests.OAuthConstants;

/**
 * Created by ggla00 on 2017-05-12.
 */

public class TokenUtil {

    public static Map<String, String> buildTokenRequest(OAuth2Config oauthDetails) {


        String clientId = oauthDetails.getClientId();
        String clientSecret = oauthDetails.getClientSecret();
        String scope = oauthDetails.getScope();
        Map<String, String> fieldsMap = new HashMap<>();

        fieldsMap.put(OAuthConstants.GRANT_TYPE, oauthDetails.getGrantType());
        fieldsMap.put(OAuthConstants.USERNAME, oauthDetails.getUsername());
        fieldsMap.put(OAuthConstants.PASSWORD, oauthDetails.getPassword());

        if (isValid(clientId)) {
            fieldsMap.put(OAuthConstants.CLIENT_ID, clientId);
        }
        if (isValid(clientSecret)) {
            fieldsMap.put(OAuthConstants.CLIENT_SECRET, clientSecret);
        }
        if (isValid(scope)) {
            fieldsMap.put(OAuthConstants.SCOPE, scope);
        }

        return fieldsMap;
    }

    public static Map<String, String> buildRefreshTokenRequest(OAuth2Config oauthDetails, Token token) {

        String clientId = oauthDetails.getClientId();
        String clientSecret = oauthDetails.getClientSecret();
        Map<String, String> fieldsMap = new HashMap<>();

        fieldsMap.put(OAuthConstants.GRANT_TYPE, "refresh_token");
        fieldsMap.put(OAuthConstants.REFRESH_TOKEN, token.getRefreshToken());

        if (isValid(clientId)) {
            fieldsMap.put(OAuthConstants.CLIENT_ID,
                    clientId);
        }
        if (isValid(clientSecret)) {
            fieldsMap.put(
                    OAuthConstants.CLIENT_SECRET, clientSecret);
        }

        return fieldsMap;
    }

    private static boolean isValid(String str) {
        return (str != null && str.trim().length() > 0);
    }

    public static String getBasicAuthorizationHeader(String username,
                                                     String password) {
        return OAuthConstants.BASIC + " "
                + encodeCredentials(username, password);
    }

    public static String encodeCredentials(String username, String password) {
        String cred = username + ":" + password;
        String encodedValue = null;
        byte[] encodedBytes = Base64.encode(cred.getBytes(), Base64.NO_WRAP);
        encodedValue = new String(encodedBytes);
        System.out.println("encodedBytes " + new String(encodedBytes));

        byte[] decodedBytes = Base64.decode(encodedBytes, Base64.NO_WRAP);
        System.out.println("decodedBytes " + new String(decodedBytes));

        return encodedValue;

    }

}