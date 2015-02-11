import java.io.*;
import java.util.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class PostTester extends Thread{


	public static void main(String[]args) throws Exception{
		String str = sendPost();
		int start = str.indexOf(" Query limit is");
		str = str.substring(start, start + 1000);
		System.out.println(str);
	}
	public static String sendPost() throws Exception {
 
		String url = "https://www.ip2location.com/free/traceroute";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "sn=C02G8416DRJM&ipAddress=166.173.57.222&serverId=8";
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		return response.toString();
 
	}
	
} 	
 	
