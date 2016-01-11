package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

@Deprecated
public class Caverphone
  implements StringEncoder
{
  private final Caverphone2 encoder = new Caverphone2();
  
  public String caverphone(String paramString)
  {
    return this.encoder.encode(paramString);
  }
  
  public Object encode(Object paramObject)
    throws EncoderException
  {
    if (!(paramObject instanceof String)) {
      throw new EncoderException("Parameter supplied to Caverphone encode is not of type java.lang.String");
    }
    return caverphone((String)paramObject);
  }
  
  public String encode(String paramString)
  {
    return caverphone(paramString);
  }
  
  public boolean isCaverphoneEqual(String paramString1, String paramString2)
  {
    return caverphone(paramString1).equals(caverphone(paramString2));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/apache/commons/codec/language/Caverphone.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */