package com.example.catsmoxy.network;

import com.example.catsmoxy.list.CatDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CatsApiService {

    @Headers("x-api-key: 59cda0e3-2cce-4034-bbc8-e523eeb306c5")
    @GET("v1/images/search")
    Observable<List<CatDTO>> loadCats(@Query("limit") int limit, @Query("page") int page);
}
