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
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = RequestBody.create(mediaType, "");
				HttpUrl base = HttpUrl.parse("https://maps.googleapis.com/maps/api/place/findplacefromtext/test");
				HttpUrl link = base.resolve("json?input=%2B16502530000&inputtype=phonenumber&key=AIzaSyDnHdrBDEvUp0BRbUiKE1vhmWclLRRZ400");
				Request request = new Request.Builder()
				  .url(link)
				  .method("GET", null)
				  .build();
				try {
					Response response = client.newCall(request).execute();
					System.out.println(response);
					System.out.println(request);
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
