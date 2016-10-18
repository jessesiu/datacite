package datacite;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.net.HttpURLConnection;
import java.net.URL;



public class mint_doi {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("/Users/xiaosizhe/Downloads/jun-sep_reviews.xml"));
			
			String file="";
			String doi="";
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				file=file+ sCurrentLine +"\n";
				if(sCurrentLine.startsWith("<identifier identifierType=\"DOI\">"))
				{
					sCurrentLine=sCurrentLine.replace("<identifier identifierType=\"DOI\">", "");
					sCurrentLine=sCurrentLine.replace("</identifier>", "");
					doi=sCurrentLine;
					
				}
				if(sCurrentLine.startsWith("</resource>"))
				{
					
					try {
						doi=doi.replace("/", "_");
						doi=doi.replace(" ","");
						FileWriter file1 = new FileWriter("/Users/xiaosizhe/Documents/publon/"+doi+".xml");
						file1.write(file);
						file1.flush();
						file1.close();

					} catch (IOException e) {
						e.printStackTrace();
					}
					
					file="";
					doi="";
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void postxml()
	{
		
	}
	public void mint()
	{
		
	}

}
