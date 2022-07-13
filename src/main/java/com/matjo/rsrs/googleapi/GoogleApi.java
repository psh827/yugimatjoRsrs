package com.matjo.rsrs.googleapi;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GoogleApi {
	public void getDetail() {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		HttpUrl base = HttpUrl.parse("https://maps.googleapis.com/maps/api/place/details/test");
		HttpUrl link = base.resolve("json?place_id=ChIJN1t_tDeuEmsRUsoyG83frY4&fields=name%2Crating%2Cformatted_phone_number&key=AIzaSyDnHdrBDEvUp0BRbUiKE1vhmWclLRRZ400");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
		  .url(link)
		  .addHeader("Accept", "application/json")
		  .method("GET", null)
		  .build();
		Response response;
		try {
			response = client.newCall(request).execute();
			System.out.println(response.body().string() );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		GoogleApi ga = new GoogleApi();
		ga.getDetail();
	}
}
