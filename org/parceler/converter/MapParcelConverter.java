package org.parceler.converter;

import android.os.Parcel;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.parceler.TypeRangeParcelConverter;

public abstract class MapParcelConverter<K, V, M extends Map<K, V>>
  implements TypeRangeParcelConverter<Map<K, V>, M>
{
  private static final int NULL = -1;
  
  public abstract M createMap();
  
  public M fromParcel(Parcel paramParcel)
  {
    int j = paramParcel.readInt();
    Object localObject;
    if (j == -1)
    {
      localObject = null;
      return (M)localObject;
    }
    Map localMap = createMap();
    int i = 0;
    for (;;)
    {
      localObject = localMap;
      if (i >= j) {
        break;
      }
      localMap.put(mapKeyFromParcel(paramParcel), mapValueFromParcel(paramParcel));
      i += 1;
    }
  }
  
  public abstract K mapKeyFromParcel(Parcel paramParcel);
  
  public abstract void mapKeyToParcel(K paramK, Parcel paramParcel);
  
  public abstract V mapValueFromParcel(Parcel paramParcel);
  
  public abstract void mapValueToParcel(V paramV, Parcel paramParcel);
  
  public void toParcel(Map<K, V> paramMap, Parcel paramParcel)
  {
    if (paramMap == null) {
      paramParcel.writeInt(-1);
    }
    for (;;)
    {
      return;
      paramParcel.writeInt(paramMap.size());
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        mapKeyToParcel(localEntry.getKey(), paramParcel);
        mapValueToParcel(localEntry.getValue(), paramParcel);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/parceler/converter/MapParcelConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */