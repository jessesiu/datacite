package datacite;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;


public class registerdoi {
	
	public static void main(String[] args) throws IOException {
    
	File file1= new File("/Users/xiaosizhe/Desktop/dataset.txt");
	BufferedReader br1= new BufferedReader(new FileReader(file1));
	String line1;
	while((line1=br1.readLine())!=null)
	{
		String[] aa= line1.split("\t");
		System.out.println(aa[0]);
		System.out.println(aa[1]);
		String location=aa[1].replace("/","_");
		
		int code= uploadmeta("/Users/xiaosizhe/Documents/publon/"+location+".xml");  
		if(code == 201)
		{
			mintdoi(aa[0],aa[1]); 
		}
		
		
	}
	
	
		
	}
	
	public static void mintdoi(String url, String doi)
	{
		URL url1 = null;
	    HttpURLConnection httpurlconnection1 = null;
	    String content="doi="+doi+"\n"+"url="+url;
		
		try
		    {
		      
			  url1 = new URL("https://mds.datacite.org/doi");

		      httpurlconnection1 = (HttpURLConnection) url1.openConnection();
		      httpurlconnection1.setDoOutput(true);
		      httpurlconnection1.setRequestMethod("POST");
		      httpurlconnection1.setRequestProperty("Content-type","text/plain");
		      httpurlconnection1.setRequestProperty("Charset",   "UTF-8");
		      String username="BL.BGI:BG79xzpuqwe23xmn!";
		      
		      byte[] encoding = Base64.encodeBase64(username.getBytes());
		      httpurlconnection1.setRequestProperty("Authorization", "Basic " + new String(encoding));
		      byte[] postDataBytes = content.toString().getBytes("UTF-8");
		      httpurlconnection1.getOutputStream().write(postDataBytes);;
		     
		      //httpurlconnection.getOutputStream().write(username.getBytes());
		     
		      int code = httpurlconnection1.getResponseCode();
		      
		      System.out.println("code   " + code);
		      System.out.println(content);
		      
		     
		      
		    }
		    catch(Exception e)
		    {
		      e.printStackTrace();
		    }
		    finally
		    {
		      if(httpurlconnection1!=null)
		        httpurlconnection1.disconnect();
		    }

	}
	
	public static int uploadmeta(String filelocation) throws IOException
	{
		int code=0;
		URL url = null;
	    HttpURLConnection httpurlconnection = null;
	    File msg= new File(filelocation);
	    
	    BufferedReader br = new BufferedReader(new FileReader(msg));
	    String line;
	    StringBuilder sb =  new StringBuilder();

	    while((line=br.readLine())!= null){
	        
	    	sb.append(line);
	    }
	    
	   
	    try
	    {
	      url = new URL("https://mds.datacite.org/metadata");

	      httpurlconnection = (HttpURLConnection) url.openConnection();
	      httpurlconnection.setDoOutput(true);
	      httpurlconnection.setRequestMethod("POST");
	      httpurlconnection.setRequestProperty("Content-type","application/xml");
	      httpurlconnection.setRequestProperty("Charset",   "UTF-8");
	      String username="BL.BGI:BG79xzpuqwe23xmn!";
	      
	      byte[] encoding = Base64.encodeBase64(username.getBytes());
	      httpurlconnection.setRequestProperty("Authorization", "Basic " + new String(encoding));
	      byte[] postDataBytes = sb.toString().getBytes("UTF-8");
	      httpurlconnection.getOutputStream().write(postDataBytes);;
	     
	      //httpurlconnection.getOutputStream().write(username.getBytes());
	     
	      code = httpurlconnection.getResponseCode();
	      
	      System.out.println("code   " + code);
	      
	     
	      
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }
	    finally
	    {
	      if(httpurlconnection!=null)
	        httpurlconnection.disconnect();
	    }

	    return code;
	}
}

