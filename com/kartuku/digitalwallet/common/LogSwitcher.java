package com.kartuku.digitalwallet.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.kartuku.digitalwallet.common.dto.controller.Card;
import com.kartuku.digitalwallet.common.dto.controller.CardLinkDto;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LogSwitcher
{
  private Logger ˊ;
  @Value("${log.verbosity}")
  private String ˋ;
  
  private static Map<String, Object> ˊ(Object paramObject)
    throws Exception
  {
    HashMap localHashMap = new HashMap();
    new CardLinkDto();
    for (;;)
    {
      try
      {
        if (((paramObject instanceof CardLinkDto)) && (((Card)((CardLinkDto)paramObject).getCardVoucherList().get(0)).getPrintedNumber() != null) && (!((Card)((CardLinkDto)paramObject).getCardVoucherList().get(0)).getPrintedNumber().isEmpty()))
        {
          paramObject = new CardLinkDto((CardLinkDto)paramObject);
          ((Card)((CardLinkDto)paramObject).getCardVoucherList().get(0)).setPrintedNumber("******");
          localHashMap.putAll(ˋ(paramObject));
          if ((localHashMap.containsKey("password")) && (localHashMap.get("password") != null) && (!localHashMap.get("password").toString().isEmpty()))
          {
            localHashMap.get("password");
            localHashMap.put("password", "******");
            if ((localHashMap.containsKey("rePassword")) && (localHashMap.get("rePassword") != null) && (!localHashMap.get("rePassword").toString().isEmpty()))
            {
              localHashMap.get("rePassword");
              localHashMap.put("rePassword", "*****");
            }
            if ((localHashMap.containsKey("newPassword")) && (localHashMap.get("newPassword") != null) && (!localHashMap.get("newPassword").toString().isEmpty()))
            {
              localHashMap.get("newPassword");
              localHashMap.put("newPassword", "*****");
            }
            return localHashMap;
          }
        }
        else
        {
          localHashMap.putAll(ˋ(paramObject));
          continue;
        }
        if (!localHashMap.containsKey("printedNumber")) {
          break label367;
        }
      }
      catch (Exception paramObject)
      {
        return localHashMap;
      }
      if ((localHashMap.get("printedNumber") != null) && (!localHashMap.get("printedNumber").toString().isEmpty()))
      {
        localHashMap.get("printedNumber");
        localHashMap.put("printedNumber", "*****");
      }
      else
      {
        label367:
        if ((localHashMap.containsKey("pan")) && (localHashMap.get("pan") != null) && (!localHashMap.get("pan").toString().isEmpty()))
        {
          localHashMap.get("pan");
          localHashMap.put("pan", "*****");
        }
      }
    }
  }
  
  private static Map<String, Object> ˋ(Object paramObject)
    throws Exception
  {
    HashMap localHashMap = new HashMap();
    PropertyDescriptor[] arrayOfPropertyDescriptor = Introspector.getBeanInfo(paramObject.getClass()).getPropertyDescriptors();
    int j = arrayOfPropertyDescriptor.length;
    int i = 0;
    while (i < j)
    {
      PropertyDescriptor localPropertyDescriptor = arrayOfPropertyDescriptor[i];
      Method localMethod = localPropertyDescriptor.getReadMethod();
      if (localMethod != null) {
        localHashMap.put(localPropertyDescriptor.getName(), localMethod.invoke(paramObject, new Object[0]));
      }
      i += 1;
    }
    return localHashMap;
  }
  
  public String getLogVerbosity()
  {
    return this.ˋ;
  }
  
  public void logSwitch(Class paramClass, String paramString1, String paramString2, String paramString3, String paramString4, Exception paramException, String paramString5, String... paramVarArgs)
  {
    String str = paramString3;
    if (paramString3 == null) {
      str = "";
    }
    if (ProcessUtil.getProcessIdArgs(paramVarArgs).isEmpty()) {}
    for (paramString3 = "";; paramString3 = ProcessUtil.getProcessIdArgs(paramVarArgs))
    {
      this.ˊ = LoggerFactory.getLogger(paramClass);
      paramClass = "";
      if (paramException != null) {
        paramClass = "Caused By : " + paramException;
      }
      if ((this.ˋ.equals("Error")) && (paramString5.equals("Error"))) {
        break label204;
      }
      if ((!this.ˋ.equals("Info")) || (!paramString5.equals("Info"))) {
        break;
      }
      this.ˊ.info("{} : {}, {}, {}, Status : {}.", new Object[] { paramString1, paramString3, paramString2, paramString4, str });
      return;
    }
    if (paramString5.equals("Info"))
    {
      this.ˊ.info("{} : {}, {}, {}, Status : {}.", new Object[] { paramString1, paramString3, paramString2, paramString4, str });
      return;
    }
    label204:
    this.ˊ.error("{} : {}, {}, {}, Status : {}. {}", new Object[] { paramString1, paramString3, paramString2, paramString4, str, paramClass });
  }
  
  public String maskPaymentRequest(Object paramObject)
  {
    throw new NullPointerException();
  }
  
  public String objectToJSON(Object paramObject)
  {
    try
    {
      paramObject = ˊ(paramObject);
      paramObject = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(paramObject);
      return (String)paramObject;
    }
    catch (Exception paramObject) {}
    return null;
  }
  
  public String objectToJSONUnmasked(Object paramObject)
  {
    try
    {
      paramObject = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(paramObject);
      return (String)paramObject;
    }
    catch (Exception paramObject) {}
    return null;
  }
  
  public void setLogVerbosity(String paramString)
  {
    this.ˋ = paramString;
  }
  
  public void writeJsonToFile(Object paramObject, String paramString)
    throws IOException
  {
    paramString = new FileWriter(null + paramString + ".txt");
    try
    {
      paramString.write(objectToJSON(paramObject));
      paramString.flush();
      paramString.close();
      return;
    }
    catch (IOException paramObject)
    {
      paramObject = paramObject;
      paramString.flush();
      paramString.close();
      return;
    }
    finally
    {
      paramObject = finally;
      paramString.flush();
      paramString.close();
      throw ((Throwable)paramObject);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/LogSwitcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */