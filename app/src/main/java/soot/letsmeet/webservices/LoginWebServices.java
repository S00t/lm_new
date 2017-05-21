package soot.letsmeet.webservices;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;
import soot.letsmeet.webservices.requests.OAuthConstants;
import soot.letsmeet.webservices.responses.AccountResponse;

/**
 * Created by Soot on 20/05/2017.
 */

public interface LoginWebServices {

    @GET("/api/users/")
    Observable<AccountResponse> login(@Header(OAuthConstants.AUTHORIZATION) String AccestToken);
}
