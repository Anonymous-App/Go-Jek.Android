package lib.gojek.base.services;

import java.util.ArrayList;
import java.util.List;

public abstract class GoServicesBaseMessageHandler
  extends AbstractBaseMessageHandler
{
  public static final String MESSAGE = "message";
  public static final String PUSH_TYPE = "push_type";
  private List<Integer> pushTypes;
  
  public GoServicesBaseMessageHandler(String paramString)
  {
    super(paramString);
  }
  
  public GoServicesBaseMessageHandler(String paramString, int paramInt)
  {
    super(paramString);
    registerPushType(paramInt);
  }
  
  public GoServicesBaseMessageHandler(String paramString, int[] paramArrayOfInt)
  {
    super(paramString);
    registerPushTypes(paramArrayOfInt);
  }
  
  private void registerPushType(int paramInt)
  {
    if (this.pushTypes == null) {
      this.pushTypes = new ArrayList();
    }
    this.pushTypes.add(Integer.valueOf(paramInt));
  }
  
  private void registerPushTypes(int[] paramArrayOfInt)
  {
    int i = 0;
    while (i < paramArrayOfInt.length)
    {
      registerPushType(paramArrayOfInt[i]);
      i += 1;
    }
  }
  
  public boolean isConsumeMessage(int paramInt)
  {
    if (this.pushTypes == null) {
      return false;
    }
    return this.pushTypes.contains(Integer.valueOf(paramInt));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/lib/gojek/base/services/GoServicesBaseMessageHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */