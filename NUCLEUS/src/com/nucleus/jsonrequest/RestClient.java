package com.nucleus.jsonrequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.lang.time.StopWatch;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint("NewApi")
public class RestClient {

    private ArrayList <NameValuePair> params;
    private ArrayList <NameValuePair> headers;
    private StringEntity xml;
    private String param;
    StopWatch watch = new StopWatch();
    private String url;

    private int responseCode;
    private String message;

    private String response;

    public enum RequestMethod {
    	GET, POST, POSTMULTIPART, POSTXML,PUT,DELETE
    	}
    
    public String getResponse() {
        return response;
    }

    public String getErrorMessage() {
        return message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public RestClient(String url)
    {
        this.url = url;
        params = new ArrayList<NameValuePair>();
        headers = new ArrayList<NameValuePair>();
    }

    public void AddParam(String name, String value)
    {
        params.add(new BasicNameValuePair(name, value));
    }
    public void AddParams(String value)
    {
        param= value;
    }
    
    public void AddHeader(String name, String value)
    {
        headers.add(new BasicNameValuePair(name, value));
    }
    
    public void AddContent(StringEntity value)
    {
    	xml=value;
    	Log.e("", xml.toString());
    }

    @SuppressLint("NewApi")
	public void Execute(RequestMethod method) throws Exception
    {
        switch(method) {
            case GET:
            {
                //add parameters
                String combinedParams = "";
                if(!params.isEmpty()){
                    combinedParams += "?";
                    for(NameValuePair p : params)
                    {
                        String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(),"UTF-8");
                        if(combinedParams.length() > 1)
                        {
                            combinedParams  +=  "&" + paramString;
                        }
                        else
                        {
                            combinedParams += paramString;
                        }
                    }
                }

                HttpGet request = new HttpGet(url + combinedParams);
                for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
                }
                

                executeRequest(request, url);
                break;
            }
            case POST:
            {
                HttpPost request = new HttpPost(url);

                //add headers
                for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
                }

                if(!param.isEmpty()){
                    request.setEntity(new StringEntity(param, "UTF-8"));
                }
                
                executeRequest(request, url);
                break;
            }
            case PUT:
            {
                HttpPut request = new HttpPut(url);

                //add headers
                for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
                }

                if(!param.isEmpty()){
                    request.setEntity(new StringEntity(param, "UTF-8"));
                }
                
                executeRequest(request, url);
                break;
            }
            case DELETE:
            {
                HttpDelete request = new HttpDelete(url);

                //add headers
                for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
                }

                
                String combinedParams = "";
                if(!param.isEmpty()){
                    
                        combinedParams += "?";
                }
                	
                
                executeRequest(request, url);
                break;
            }
        }
    }
    
    public void ExecuteStringpart(RequestMethod method,StringEntity se) throws Exception
    {
        switch(method) {
            case POSTXML:
            	
            {
                HttpPost request = new HttpPost(url);
                request.setHeader("Accept-Charset", "UTF-8");
                request.setHeader("Content-Type", "application/xml");
                //add headers
                for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
                }
                
                if(se!=null){
                	request.setEntity(se);
                }
                
                executeRequest(request, url);
                break;
            }
        }
    }
    
    public void ExecuteMultipart(RequestMethod method,MultipartEntity me) throws Exception
    {
        switch(method) {
            case POSTMULTIPART:
            	
            {
                HttpPost request = new HttpPost(url);

                //add headers
                for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
                }

                if(!params.isEmpty()){
                    request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                }
                
                if(!params.isEmpty()){
                    request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                }
                
                if(me!=null){
                	request.setEntity(me);
                }
                
                executeRequest(request, url);
                break;
            }
        }
    }

    private void executeRequest(HttpUriRequest request, String url) throws URIException
    {
    	HttpParams httpParameters = new BasicHttpParams();
    	HttpConnectionParams.setConnectionTimeout(httpParameters, 60000);
    	HttpConnectionParams.setSoTimeout(httpParameters, 60000);
        HttpClient client = new DefaultHttpClient(httpParameters);
        
        
        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, System.getProperty("http.agent"));
        client.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        HttpResponse httpResponse;
        HttpMethod method = new HeadMethod(url);
        try {
        	watch.start();
            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();

            
            
            HttpEntity entity = httpResponse.getEntity();
            
            if (entity != null) {

                InputStream instream = entity.getContent();
                response = convertStreamToString(instream,httpResponse);

                // Closing the input stream will trigger connection release
                instream.close();
            }

        } catch (ClientProtocolException e)  {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        } catch (IOException e) {
            client.getConnectionManager().shutdown();
            e.printStackTrace();
        }
        finally {
    		watch.stop();
    	}
        Log.e("Time :",method.getURI()+" "+watch.toString());
    }
    
    
    private static String convertStreamToString(InputStream is,HttpResponse httpResponse) throws IOException {

    	Header contentEncoding = httpResponse.getFirstHeader("Content-Encoding");
    	if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
    	    is = new GZIPInputStream(is);
    	}
    	InputStreamReader isreader = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isreader);
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}