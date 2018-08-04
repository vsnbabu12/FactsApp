package vsn.com.factslist.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vsn.com.factslist.services.FactListInterface;
import vsn.com.factslist.services.FactListService;

public class MyViewModel extends ViewModel {

    private MutableLiveData<FactsResponse> users;
    private final static String API_KEY = "";
    FactListInterface listService;
    Call<FactsResponse> call;

    public LiveData<FactsResponse> getUsers() {
        if (users == null) {
            users = new MutableLiveData<FactsResponse>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FactListService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        listService = FactListService.getClient().create(FactListInterface.class);
        call = listService.getAllFacts(API_KEY);


        call.enqueue(new Callback<FactsResponse>() {
            @Override
            public void onResponse(Call<FactsResponse> call, Response<FactsResponse> response) {

                //finally we are setting the list to our MutableLiveData
                users.setValue(response.body());
            }

            @Override
            public void onFailure(Call<FactsResponse> call, Throwable t) {

            }
        });
    }

    public void getLatestList(){

        listService.getAllFacts(API_KEY);
        call = listService.getAllFacts(API_KEY);
        call.enqueue(new Callback<FactsResponse>() {
            @Override
            public void onResponse(Call<FactsResponse> call, Response<FactsResponse> response) {

                //finally we are setting the list to our MutableLiveData
                users.postValue(response.body());
            }

            @Override
            public void onFailure(Call<FactsResponse> call, Throwable t) {

            }
        });

    }
}
