package lib.gojek.base.networks;

public abstract class AbstractBaseNetwork
{
  public static final long CACHE_SIZE = 4194304L;
  public static final String DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ssZ";
  protected static final String NETWORK_INITIALIZATION_ERROR_DESC = "Please initialize network first by calling 'initializeNetworkInterface()'";
  
  protected abstract void initializeNetworkInterface();
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/networks/AbstractBaseNetwork.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */