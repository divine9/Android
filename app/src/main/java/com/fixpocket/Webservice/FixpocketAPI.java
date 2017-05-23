package com.fixpocket.Webservice;


import com.fixpocket.Model.BeatDetailsModel.BeatDetailsModel;
import com.fixpocket.Model.BeatListModel.BeatListModel;
import com.fixpocket.Model.CategoryModel.CategoryModel;
import com.fixpocket.Model.CollectionsModel.CollectionsModel;
import com.fixpocket.Model.CollectionsbeatsModel.CollectionsbeatsModel;
import com.fixpocket.Model.FeaturedModel.FeaturedModel;
import com.fixpocket.Model.LoginModel.Loginmodel;
import com.fixpocket.Model.RegistrationModel.RegistrationModel;
import com.fixpocket.Model.SetFavouriteModel.SetFavouriteModel;
import com.fixpocket.Model.SubCategoryModel.SubCategoryModel;
import com.google.gson.JsonElement;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FixpocketAPI {

    @FormUrlEncoded
    @POST("user/login")
    Call<Loginmodel> Login(@Header("API-AUTH") String APIAUTH,
                           @Field("username") String username,
                           @Field("password") String password);


    @FormUrlEncoded
    @POST("user/signup")
    Call<RegistrationModel> signup(@Header("API-AUTH") String APIAUTH,
                                   @Field("username") String username,
                                   @Field("password") String password,
                                   @Field("email") String email,
                                   @Field("coupon") String coupon);

    @GET("beat/categories")
    Call<CategoryModel> categories(@Header("API-AUTH") String APIAUTH);


    @FormUrlEncoded
    @POST("beat/subcategories")
    Call<SubCategoryModel> subcategories(@Header("API-AUTH") String APIAUTH,
                                         @Field("catRef") String catRef);

    @FormUrlEncoded
    @POST("beat/subcat_beats")
    Call<BeatListModel> subcat_beats(@Header("API-AUTH") String APIAUTH,
                                     @Header("AUTH-TOKEN") String AUTH_TOKEN,
                                     @Field("catRef") String catRef);

    @POST("beat/featured")
    Call<FeaturedModel> featured(@Header("API-AUTH") String APIAUTH,
                                 @Header("AUTH-TOKEN") String AUTH_TOKEN);

    @FormUrlEncoded
    @POST("beat/setfavourite")
    Call<SetFavouriteModel> setfavourite(@Header("API-AUTH") String APIAUTH,
                                         @Header("AUTH-TOKEN") String AUTH_TOKEN,
                                         @Field("beatRef") String beatRef);


    @POST("beat/collections")
    Call<CollectionsModel> collections(@Header("API-AUTH") String APIAUTH,
                                       @Header("AUTH-TOKEN") String AUTH_TOKEN);

    @FormUrlEncoded
    @POST("beat/collections_beats")
    Call<CollectionsbeatsModel> collections_beats(@Header("API-AUTH") String APIAUTH,
                                                  @Header("AUTH-TOKEN") String AUTH_TOKEN,
                                                  @Field("colRef") String catRef);

    @FormUrlEncoded
    @POST("beat/beat_info")
    Call<BeatDetailsModel> beat_info(@Header("API-AUTH") String APIAUTH,
                                     @Header("AUTH-TOKEN") String AUTH_TOKEN,
                                     @Field("beatRef") String beatRef);
}
