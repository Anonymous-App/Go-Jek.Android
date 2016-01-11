package com.google.android.gms.internal;

import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class pc
{
  private static int a(String paramString, pd.a.a[] paramArrayOfa)
  {
    int m = paramArrayOfa.length;
    int i = 0;
    int j = 14;
    if (i < m)
    {
      pd.a.a locala = paramArrayOfa[i];
      int k;
      if (j == 14) {
        if ((locala.type == 9) || (locala.type == 2) || (locala.type == 6)) {
          k = locala.type;
        }
      }
      do
      {
        do
        {
          i += 1;
          j = k;
          break;
          k = j;
        } while (locala.type == 14);
        throw new IllegalArgumentException("Unexpected TypedValue type: " + locala.type + " for key " + paramString);
        k = j;
      } while (locala.type == j);
      throw new IllegalArgumentException("The ArrayList elements should all be the same type, but ArrayList with key " + paramString + " contains items of type " + j + " and " + locala.type);
    }
    return j;
  }
  
  static int a(List<Asset> paramList, Asset paramAsset)
  {
    paramList.add(paramAsset);
    return paramList.size() - 1;
  }
  
  public static a a(DataMap paramDataMap)
  {
    pd localpd = new pd();
    ArrayList localArrayList = new ArrayList();
    localpd.awd = a(paramDataMap, localArrayList);
    return new a(localpd, localArrayList);
  }
  
  private static pd.a.a a(List<Asset> paramList, Object paramObject)
  {
    pd.a.a locala1 = new pd.a.a();
    if (paramObject == null)
    {
      locala1.type = 14;
      return locala1;
    }
    locala1.awh = new pd.a.a.a();
    if ((paramObject instanceof String))
    {
      locala1.type = 2;
      locala1.awh.awj = ((String)paramObject);
    }
    Object localObject2;
    Object localObject1;
    int i;
    Object localObject3;
    for (;;)
    {
      return locala1;
      if ((paramObject instanceof Integer))
      {
        locala1.type = 6;
        locala1.awh.awn = ((Integer)paramObject).intValue();
      }
      else if ((paramObject instanceof Long))
      {
        locala1.type = 5;
        locala1.awh.awm = ((Long)paramObject).longValue();
      }
      else if ((paramObject instanceof Double))
      {
        locala1.type = 3;
        locala1.awh.awk = ((Double)paramObject).doubleValue();
      }
      else if ((paramObject instanceof Float))
      {
        locala1.type = 4;
        locala1.awh.awl = ((Float)paramObject).floatValue();
      }
      else if ((paramObject instanceof Boolean))
      {
        locala1.type = 8;
        locala1.awh.awp = ((Boolean)paramObject).booleanValue();
      }
      else if ((paramObject instanceof Byte))
      {
        locala1.type = 7;
        locala1.awh.awo = ((Byte)paramObject).byteValue();
      }
      else if ((paramObject instanceof byte[]))
      {
        locala1.type = 1;
        locala1.awh.awi = ((byte[])paramObject);
      }
      else if ((paramObject instanceof String[]))
      {
        locala1.type = 11;
        locala1.awh.aws = ((String[])paramObject);
      }
      else if ((paramObject instanceof long[]))
      {
        locala1.type = 12;
        locala1.awh.awt = ((long[])paramObject);
      }
      else if ((paramObject instanceof float[]))
      {
        locala1.type = 15;
        locala1.awh.awu = ((float[])paramObject);
      }
      else if ((paramObject instanceof Asset))
      {
        locala1.type = 13;
        locala1.awh.awv = a(paramList, (Asset)paramObject);
      }
      else
      {
        if (!(paramObject instanceof DataMap)) {
          break;
        }
        locala1.type = 9;
        paramObject = (DataMap)paramObject;
        localObject2 = ((DataMap)paramObject).keySet();
        localObject1 = new pd.a[((Set)localObject2).size()];
        localObject2 = ((Set)localObject2).iterator();
        i = 0;
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (String)((Iterator)localObject2).next();
          localObject1[i] = new pd.a();
          localObject1[i].name = ((String)localObject3);
          localObject1[i].awf = a(paramList, ((DataMap)paramObject).get((String)localObject3));
          i += 1;
        }
        locala1.awh.awq = ((pd.a[])localObject1);
      }
    }
    int j;
    label584:
    pd.a.a locala2;
    if ((paramObject instanceof ArrayList))
    {
      locala1.type = 10;
      localObject2 = (ArrayList)paramObject;
      localObject3 = new pd.a.a[((ArrayList)localObject2).size()];
      paramObject = null;
      int k = ((ArrayList)localObject2).size();
      j = 0;
      i = 14;
      if (j < k)
      {
        localObject1 = ((ArrayList)localObject2).get(j);
        locala2 = a(paramList, localObject1);
        if ((locala2.type != 14) && (locala2.type != 2) && (locala2.type != 6) && (locala2.type != 9)) {
          throw new IllegalArgumentException("The only ArrayList element types supported by DataBundleUtil are String, Integer, Bundle, and null, but this ArrayList contains a " + localObject1.getClass());
        }
        if ((i == 14) && (locala2.type != 14))
        {
          i = locala2.type;
          paramObject = localObject1;
        }
      }
    }
    for (;;)
    {
      localObject3[j] = locala2;
      j += 1;
      break label584;
      if (locala2.type != i)
      {
        throw new IllegalArgumentException("ArrayList elements must all be of the sameclass, but this one contains a " + paramObject.getClass() + " and a " + localObject1.getClass());
        locala1.awh.awr = ((pd.a.a[])localObject3);
        break;
        throw new RuntimeException("newFieldValueFromValue: unexpected value " + paramObject.getClass().getSimpleName());
      }
    }
  }
  
  public static DataMap a(a parama)
  {
    DataMap localDataMap = new DataMap();
    pd.a[] arrayOfa = parama.awb.awd;
    int j = arrayOfa.length;
    int i = 0;
    while (i < j)
    {
      pd.a locala = arrayOfa[i];
      a(parama.awc, localDataMap, locala.name, locala.awf);
      i += 1;
    }
    return localDataMap;
  }
  
  private static ArrayList a(List<Asset> paramList, pd.a.a.a parama, int paramInt)
  {
    ArrayList localArrayList = new ArrayList(parama.awr.length);
    parama = parama.awr;
    int k = parama.length;
    int i = 0;
    if (i < k)
    {
      pd.a[] arrayOfa = parama[i];
      if (arrayOfa.type == 14) {
        localArrayList.add(null);
      }
      for (;;)
      {
        i += 1;
        break;
        if (paramInt == 9)
        {
          DataMap localDataMap = new DataMap();
          arrayOfa = arrayOfa.awh.awq;
          int m = arrayOfa.length;
          int j = 0;
          while (j < m)
          {
            pd.a locala = arrayOfa[j];
            a(paramList, localDataMap, locala.name, locala.awf);
            j += 1;
          }
          localArrayList.add(localDataMap);
        }
        else if (paramInt == 2)
        {
          localArrayList.add(arrayOfa.awh.awj);
        }
        else
        {
          if (paramInt != 6) {
            break label191;
          }
          localArrayList.add(Integer.valueOf(arrayOfa.awh.awn));
        }
      }
      label191:
      throw new IllegalArgumentException("Unexpected typeOfArrayList: " + paramInt);
    }
    return localArrayList;
  }
  
  private static void a(List<Asset> paramList, DataMap paramDataMap, String paramString, pd.a.a parama)
  {
    int i = parama.type;
    if (i == 14)
    {
      paramDataMap.putString(paramString, null);
      return;
    }
    Object localObject1 = parama.awh;
    if (i == 1)
    {
      paramDataMap.putByteArray(paramString, ((pd.a.a.a)localObject1).awi);
      return;
    }
    if (i == 11)
    {
      paramDataMap.putStringArray(paramString, ((pd.a.a.a)localObject1).aws);
      return;
    }
    if (i == 12)
    {
      paramDataMap.putLongArray(paramString, ((pd.a.a.a)localObject1).awt);
      return;
    }
    if (i == 15)
    {
      paramDataMap.putFloatArray(paramString, ((pd.a.a.a)localObject1).awu);
      return;
    }
    if (i == 2)
    {
      paramDataMap.putString(paramString, ((pd.a.a.a)localObject1).awj);
      return;
    }
    if (i == 3)
    {
      paramDataMap.putDouble(paramString, ((pd.a.a.a)localObject1).awk);
      return;
    }
    if (i == 4)
    {
      paramDataMap.putFloat(paramString, ((pd.a.a.a)localObject1).awl);
      return;
    }
    if (i == 5)
    {
      paramDataMap.putLong(paramString, ((pd.a.a.a)localObject1).awm);
      return;
    }
    if (i == 6)
    {
      paramDataMap.putInt(paramString, ((pd.a.a.a)localObject1).awn);
      return;
    }
    if (i == 7)
    {
      paramDataMap.putByte(paramString, (byte)((pd.a.a.a)localObject1).awo);
      return;
    }
    if (i == 8)
    {
      paramDataMap.putBoolean(paramString, ((pd.a.a.a)localObject1).awp);
      return;
    }
    if (i == 13)
    {
      if (paramList == null) {
        throw new RuntimeException("populateBundle: unexpected type for: " + paramString);
      }
      paramDataMap.putAsset(paramString, (Asset)paramList.get((int)((pd.a.a.a)localObject1).awv));
      return;
    }
    if (i == 9)
    {
      parama = new DataMap();
      localObject1 = ((pd.a.a.a)localObject1).awq;
      int j = localObject1.length;
      i = 0;
      while (i < j)
      {
        Object localObject2 = localObject1[i];
        a(paramList, parama, ((pd.a)localObject2).name, ((pd.a)localObject2).awf);
        i += 1;
      }
      paramDataMap.putDataMap(paramString, parama);
      return;
    }
    if (i == 10)
    {
      i = a(paramString, ((pd.a.a.a)localObject1).awr);
      paramList = a(paramList, (pd.a.a.a)localObject1, i);
      if (i == 14)
      {
        paramDataMap.putStringArrayList(paramString, paramList);
        return;
      }
      if (i == 9)
      {
        paramDataMap.putDataMapArrayList(paramString, paramList);
        return;
      }
      if (i == 2)
      {
        paramDataMap.putStringArrayList(paramString, paramList);
        return;
      }
      if (i == 6)
      {
        paramDataMap.putIntegerArrayList(paramString, paramList);
        return;
      }
      throw new IllegalStateException("Unexpected typeOfArrayList: " + i);
    }
    throw new RuntimeException("populateBundle: unexpected type " + i);
  }
  
  private static pd.a[] a(DataMap paramDataMap, List<Asset> paramList)
  {
    Object localObject1 = paramDataMap.keySet();
    pd.a[] arrayOfa = new pd.a[((Set)localObject1).size()];
    localObject1 = ((Set)localObject1).iterator();
    int i = 0;
    while (((Iterator)localObject1).hasNext())
    {
      String str = (String)((Iterator)localObject1).next();
      Object localObject2 = paramDataMap.get(str);
      arrayOfa[i] = new pd.a();
      arrayOfa[i].name = str;
      arrayOfa[i].awf = a(paramList, localObject2);
      i += 1;
    }
    return arrayOfa;
  }
  
  public static class a
  {
    public final pd awb;
    public final List<Asset> awc;
    
    public a(pd parampd, List<Asset> paramList)
    {
      this.awb = parampd;
      this.awc = paramList;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/pc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */