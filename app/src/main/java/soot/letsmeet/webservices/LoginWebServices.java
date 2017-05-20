package soot.letsmeet.webservices;

import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;
import soot.letsmeet.webservices.requests.OAuthConstants;
import soot.letsmeet.webservices.responses.AccountResponse;

/**
 * Created by Soot on 20/05/2017.
 */

public interface LoginWebServices {

    @POST("/api/users/")
    Observable<AccountResponse> login(@Header(OAuthConstants.AUTHORIZATION) String AccestToken);

}
