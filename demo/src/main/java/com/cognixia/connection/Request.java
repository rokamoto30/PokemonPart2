package com.cognixia.connection;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.cognixia.exception.ApiException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Request {
		
	public static String send(String endpoint, String method) throws ApiException {
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(endpoint))
				.method(method, HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() >= 200 && response.statusCode() < 300 ) {
				return response.body();
			}

			throw new ApiException(response.body());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public static <T> T parse(String response, Class<T> typeParameterClass) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		T parsed = mapper.readValue(response, typeParameterClass); //https://stackoverflow.com/questions/3437897/how-do-i-get-a-class-instance-of-generic-type-t
		return parsed;
	}
	
}
