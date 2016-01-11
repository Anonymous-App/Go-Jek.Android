package retrofit.client;

import com.newrelic.agent.android.instrumentation.okhttp2.OkHttp2Instrumentation;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class OkClient
  implements Client
{
  private final OkHttpClient client;
  
  public OkClient()
  {
    this(generateDefaultOkHttp());
  }
  
  public OkClient(OkHttpClient paramOkHttpClient)
  {
    if (paramOkHttpClient == null) {
      throw new NullPointerException("client == null");
    }
    this.client = paramOkHttpClient;
  }
  
  private static List<Header> createHeaders(Headers paramHeaders)
  {
    int j = paramHeaders.size();
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(new Header(paramHeaders.name(i), paramHeaders.value(i)));
      i += 1;
    }
    return localArrayList;
  }
  
  static com.squareup.okhttp.Request createRequest(Request paramRequest)
  {
    Request.Builder localBuilder = new Request.Builder().url(paramRequest.getUrl()).method(paramRequest.getMethod(), createRequestBody(paramRequest.getBody()));
    List localList = paramRequest.getHeaders();
    int i = 0;
    int j = localList.size();
    while (i < j)
    {
      Header localHeader = (Header)localList.get(i);
      String str = localHeader.getValue();
      paramRequest = str;
      if (str == null) {
        paramRequest = "";
      }
      localBuilder.addHeader(localHeader.getName(), paramRequest);
      i += 1;
    }
    if (!(localBuilder instanceof Request.Builder)) {
      return localBuilder.build();
    }
    return OkHttp2Instrumentation.build((Request.Builder)localBuilder);
  }
  
  private static RequestBody createRequestBody(TypedOutput paramTypedOutput)
  {
    if (paramTypedOutput == null) {
      return null;
    }
    return new OkClient.1(MediaType.parse(paramTypedOutput.mimeType()), paramTypedOutput);
  }
  
  private static TypedInput createResponseBody(ResponseBody paramResponseBody)
  {
    if (paramResponseBody.contentLength() == 0L) {
      return null;
    }
    return new OkClient.2(paramResponseBody);
  }
  
  private static OkHttpClient generateDefaultOkHttp()
  {
    OkHttpClient localOkHttpClient = new OkHttpClient();
    localOkHttpClient.setConnectTimeout(15000L, TimeUnit.MILLISECONDS);
    localOkHttpClient.setReadTimeout(20000L, TimeUnit.MILLISECONDS);
    return localOkHttpClient;
  }
  
  static Response parseResponse(com.squareup.okhttp.Response paramResponse)
  {
    return new Response(paramResponse.request().urlString(), paramResponse.code(), paramResponse.message(), createHeaders(paramResponse.headers()), createResponseBody(paramResponse.body()));
  }
  
  public Response execute(Request paramRequest)
    throws IOException
  {
    OkHttpClient localOkHttpClient = this.client;
    paramRequest = createRequest(paramRequest);
    if (!(localOkHttpClient instanceof OkHttpClient)) {}
    for (paramRequest = localOkHttpClient.newCall(paramRequest);; paramRequest = OkHttp2Instrumentation.newCall((OkHttpClient)localOkHttpClient, paramRequest)) {
      return parseResponse(paramRequest.execute());
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/retrofit/client/OkClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */