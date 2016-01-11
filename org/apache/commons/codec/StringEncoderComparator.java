package org.apache.commons.codec;

import java.util.Comparator;

public class StringEncoderComparator
  implements Comparator
{
  private final StringEncoder stringEncoder;
  
  @Deprecated
  public StringEncoderComparator()
  {
    this.stringEncoder = null;
  }
  
  public StringEncoderComparator(StringEncoder paramStringEncoder)
  {
    this.stringEncoder = paramStringEncoder;
  }
  
  public int compare(Object paramObject1, Object paramObject2)
  {
    try
    {
      int i = ((Comparable)this.stringEncoder.encode(paramObject1)).compareTo((Comparable)this.stringEncoder.encode(paramObject2));
      return i;
    }
    catch (EncoderException paramObject1) {}
    return 0;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/apache/commons/codec/StringEncoderComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */