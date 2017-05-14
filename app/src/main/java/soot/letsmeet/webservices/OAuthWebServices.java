package soot.letsmeet.webservices;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;
import soot.letsmeet.webservices.requests.OAuthConstants;
import soot.letsmeet.webservices.responses.TokenResponse;

/**
 * Created by ggla00 on 2017-05-12.
 */

public interface OAuthWebServices {

        @FormUrlEncoded
        @POST("oauth/token/")
        Observable<TokenResponse> getAccessToken(@Header(OAuthConstants.temp) String autorization, @FieldMap(encoded = true) Map<String, String> fields);
}
