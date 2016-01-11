package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.query.internal.Operator;
import com.google.android.gms.drive.query.internal.f;
import java.util.Iterator;
import java.util.List;

public class c
  implements f<String>
{
  public <T> String a(b<T> paramb, T paramT)
  {
    return String.format("contains(%s,%s)", new Object[] { paramb.getName(), paramT });
  }
  
  public <T> String a(Operator paramOperator, MetadataField<T> paramMetadataField, T paramT)
  {
    return String.format("cmp(%s,%s,%s)", new Object[] { paramOperator.getTag(), paramMetadataField.getName(), paramT });
  }
  
  public String a(Operator paramOperator, List<String> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramOperator.getTag() + "(");
    paramList = paramList.iterator();
    for (paramOperator = ""; paramList.hasNext(); paramOperator = ",")
    {
      String str = (String)paramList.next();
      localStringBuilder.append(paramOperator);
      localStringBuilder.append(str);
    }
    return ")";
  }
  
  public String bn(String paramString)
  {
    return String.format("not(%s)", new Object[] { paramString });
  }
  
  public String c(MetadataField<?> paramMetadataField)
  {
    return String.format("fieldOnly(%s)", new Object[] { paramMetadataField.getName() });
  }
  
  public <T> String c(MetadataField<T> paramMetadataField, T paramT)
  {
    return String.format("has(%s,%s)", new Object[] { paramMetadataField.getName(), paramT });
  }
  
  public String ir()
  {
    return "all()";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */