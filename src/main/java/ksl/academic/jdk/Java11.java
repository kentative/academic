package ksl.academic.jdk;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

/**
 * 
 * 2018 - 2 Releases every year indefinitely 2028 - Java 31... - Increase
 * frequency, smaller footprint and incremental - Not experimental releases -
 * Deprecation/removals between LTS Java 11 - LTS release every 3 years -> Java
 * 17, 23, etc...requires Oracle JDK
 * 
 * AdoptOpenJDK, Azul
 *
 * JDK 11 - Java Flight Recorder - low overhead data collection framework - Java
 * Mission Control - Launching Single-file source code - java Hello.java
 * 
 * 
 * @author Kent
 *
 */
public class Java11 {

	public static void main(String[] args) throws Exception {
		System.out.println("Hello 11");

		testSend();
		testAsyncSend();
		
	}

	public static void testHttpClient() {
		// building request
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://www.bing.com/")).build();
		// creating response body handler
		HttpResponse.BodyHandler<String> bodyHandler = HttpResponse.BodyHandlers.ofString();

		// sending request and receiving response via HttpClient
		HttpClient client = HttpClient.newHttpClient();
		CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, bodyHandler);
		future.thenApply(HttpResponse::body).thenAccept(System.out::println).join();
	}
	
	public static void testSend() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder(URI.create("https://pluralsight.com")).build();
		
		HttpResponse<String> response = client.send(req, BodyHandlers.ofString());
		System.out.println(response.headers().map());

	}
	
	public static void testAsyncSend() throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();		
		HttpRequest req = HttpRequest.newBuilder(URI.create("https://google.com")).build();
		
		CompletableFuture<HttpResponse<String>> resFuture = client.sendAsync(req, BodyHandlers.ofString());
		resFuture.thenAccept(res -> System.out.println(res.version()));
		resFuture.join(); // waits until future is accepted

	}

}
