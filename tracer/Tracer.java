import java.io.*;
import java.util.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

public class Tracer{

	public static void main(String[]args) throws Exception{
		String [] list = getList();
		runList(list);
	}
	
	public static void runList( String [] list ) throws Exception{
		System.out.println(list[0].length());
		for(int k = 0; k < list.length; k++){
			for(int nation = 1; nation <= 12; nation++)
				try{ 
					String addy = list[k].replaceAll("\\n", "").replaceAll("\\r", "");
					String str = sendPost(addy, nation); 
					System.out.println(addy);
					if(str.equals("reach"))
						continue;
					process( str );
				}
				catch(Exception e){ System.out.println("Connecting...."); Thread.sleep(1000); }
		}
	}
	
	public static void toFile(String str, String filename, boolean append) throws Exception{
		FileWriter fw = new FileWriter(filename, append);
		fw.write( str );	
		fw.close();
    }
	
	public static void process(String str) throws Exception{
		try{
			String hop = "<p>Traceroute to";
			String endl = "<p>Multi-location";
			int start = str.indexOf(" Query limit is");
			String b = str.substring(start, str.indexOf("</b>"));
			System.out.println( b );
			if( str.contains( hop ) && str.contains( endl ) ){
					start = str.indexOf( hop );
				int stop = str.indexOf( endl );
				str = str.substring( start, stop );
				System.out.println( " Printed ok ");
				System.out.println(str.substring(0, str.indexOf("ms")) );
				toFile( str , "data.html", true );
			}
			else{
				System.out.println("Keywords not found");
				System.out.println(str.contains("www."));
				toFile( str , "error.html", false );
				Thread.sleep(30000);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return;
		}
	}
	
	
	public static String sendPost(String www, int id) throws Exception {
 
		String url = "https://www.ip2location.com/free/traceroute";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "sn=C02G8416DRJM&ipAddress=" + www + "&serverId=" + id;
 		System.out.println( urlParameters );
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		/*
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);*/
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 		String resp = response.toString();
 		if(resp.contains("reachable"))
 			return "reach";
		//print result
		return resp;
 
	}
	public static String [] getList() throws Exception{ 
		return new Scanner(new File("univ.txt")).useDelimiter("\\Z").next().split("\n");
	}
} 	
 	