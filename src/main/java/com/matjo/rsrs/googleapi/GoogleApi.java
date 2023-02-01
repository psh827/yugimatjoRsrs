package com.matjo.rsrs.googleapi;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleApi {
	public static Map<String, String> getDetail(String resName) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = RequestBody.create(mediaType, "");
				HttpUrl base = HttpUrl.parse("https://maps.googleapis.com/maps/api/place/findplacefromtext/test");
				HttpUrl link = base.resolve("json?input=" + resName + "&inputtype=textquery&fields=formatted_address%2Cname%2Cprice_level%2Crating%2Copening_hours%2Cgeometry&key=zaS");
				Request request = new Request.Builder()
				  .url(link)
				  .method("GET", null)
				  .build();
				Response response = null;
				try {
					response = client.newCall(request).execute();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String jsonData = response.body().string();
				JSONObject jsonObject = new JSONObject(jsonData);
				JSONArray Jarray = jsonObject.getJSONArray("candidates");
				Map<String,String> resInfo = new HashMap<String, String>();
				for (int i = 0; i < Jarray.length(); i++) {
			        JSONObject object = Jarray.getJSONObject(i);
			        if(object.get("rating") != null	) {
			        	resInfo.put("rating", object.get("rating").toString());			        	
			        }
			        
			        if(object.get("formatted_address") != null) {
			        	resInfo.put("addr", object.get("formatted_address").toString());
			        }
			    }
				return resInfo;
	}
	
	public static Map<String, String> getLocation(String resName) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
				MediaType mediaType = MediaType.parse("text/plain");
				RequestBody body = RequestBody.create(mediaType, "");
				HttpUrl base = HttpUrl.parse("https://maps.googleapis.com/maps/api/place/findplacefromtext/test");
				HttpUrl link = base.resolve("json?input=" + resName + "&inputtype=textquery&fields=formatted_address%2Cname%2Cprice_level%2Crating%2Copening_hours%2Cgeometry&key=za");
				Request request = new Request.Builder()
				  .url(link)
				  .method("GET", null)
				  .build();
				Response response = null;
				try {
					response = client.newCall(request).execute();
				} catch (IOException e) {
					e.printStackTrace();
				}
				String jsonData = response.body().string();
				JSONObject jsonObject = new JSONObject(jsonData);
				JSONArray Jarray = jsonObject.getJSONArray("candidates");
				Map<String,String> resInfo = new HashMap<String, String>();
				for (int i = 0; i < Jarray.length(); i++) {
			        JSONObject object = Jarray.getJSONObject(i);
			        if(object.get("rating") != null	) {
			        	resInfo.put("rating", object.get("rating").toString());
			        }
			        
			        if(object.get("geometry") != null) {
			        	String location = object.get("geometry").toString();
			        	resInfo.put("location", location.split("\"location\":")[1].replace("{", "").replace("}", "").replace("\"", ""));
			        }
			        
			        if(object.get("formatted_address") != null) {
			        	resInfo.put("addr", object.get("formatted_address").toString());
			        }
			    }
				return resInfo;
	}
}
