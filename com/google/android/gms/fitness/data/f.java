package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.a;
import com.google.android.gms.common.internal.safeparcel.a.a;
import com.google.android.gms.common.internal.safeparcel.b;
import java.util.ArrayList;

public class f
  implements Parcelable.Creator<DataSet>
{
  static void a(DataSet paramDataSet, Parcel paramParcel, int paramInt)
  {
    int i = b.D(paramParcel);
    b.a(paramParcel, 1, paramDataSet.getDataSource(), paramInt, false);
    b.c(paramParcel, 1000, paramDataSet.getVersionCode());
    b.a(paramParcel, 2, paramDataSet.getDataType(), paramInt, false);
    b.d(paramParcel, 3, paramDataSet.iK(), false);
    b.c(paramParcel, 4, paramDataSet.iL(), false);
    b.a(paramParcel, 5, paramDataSet.iC());
    b.H(paramParcel, i);
  }
  
  public DataSet bm(Parcel paramParcel)
  {
    boolean bool = false;
    ArrayList localArrayList1 = null;
    int j = a.C(paramParcel);
    ArrayList localArrayList2 = new ArrayList();
    DataType localDataType = null;
    DataSource localDataSource = null;
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = a.B(paramParcel);
      switch (a.aD(k))
      {
      default: 
        a.b(paramParcel, k);
        break;
      case 1: 
        localDataSource = (DataSource)a.a(paramParcel, k, DataSource.CREATOR);
        break;
      case 1000: 
        i = a.g(paramParcel, k);
        break;
      case 2: 
        localDataType = (DataType)a.a(paramParcel, k, DataType.CREATOR);
        break;
      case 3: 
        a.a(paramParcel, k, localArrayList2, getClass().getClassLoader());
        break;
      case 4: 
        localArrayList1 = a.c(paramParcel, k, DataSource.CREATOR);
        break;
      case 5: 
        bool = a.c(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != j) {
      throw new a.a("Overread allowed size end=" + j, paramParcel);
    }
    return new DataSet(i, localDataSource, localDataType, localArrayList2, localArrayList1, bool);
  }
  
  public DataSet[] cC(int paramInt)
  {
    return new DataSet[paramInt];
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */