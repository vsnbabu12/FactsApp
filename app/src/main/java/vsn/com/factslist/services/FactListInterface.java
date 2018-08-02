package vsn.com.factslist.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vsn.com.factslist.models.FactsResponse;

public interface FactListInterface {

    @GET(".")
    Call<FactsResponse> getAllFacts(@Query("api_key") String apiKey);



}
