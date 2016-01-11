package com.google.android.gms.drive;

import android.os.Bundle;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.internal.l;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.b;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.internal.e;
import com.google.android.gms.internal.kd;
import java.util.Collection;
import java.util.Iterator;

public final class MetadataBuffer
  extends DataBuffer<Metadata>
{
  private final String Nq;
  private a Nr;
  
  public MetadataBuffer(DataHolder paramDataHolder, String paramString)
  {
    super(paramDataHolder);
    this.Nq = paramString;
    paramDataHolder.gy().setClassLoader(MetadataBuffer.class.getClassLoader());
  }
  
  public Metadata get(int paramInt)
  {
    a locala2 = this.Nr;
    a locala1;
    if (locala2 != null)
    {
      locala1 = locala2;
      if (a.a(locala2) == paramInt) {}
    }
    else
    {
      locala1 = new a(this.II, paramInt);
      this.Nr = locala1;
    }
    return locala1;
  }
  
  public String getNextPageToken()
  {
    return this.Nq;
  }
  
  private static class a
    extends Metadata
  {
    private final DataHolder II;
    private final int JY;
    private final int Ns;
    
    public a(DataHolder paramDataHolder, int paramInt)
    {
      this.II = paramDataHolder;
      this.Ns = paramInt;
      this.JY = paramDataHolder.ar(paramInt);
    }
    
    protected <T> T a(MetadataField<T> paramMetadataField)
    {
      return (T)paramMetadataField.a(this.II, this.Ns, this.JY);
    }
    
    public Metadata hR()
    {
      MetadataBundle localMetadataBundle = MetadataBundle.io();
      Iterator localIterator = e.in().iterator();
      while (localIterator.hasNext())
      {
        MetadataField localMetadataField = (MetadataField)localIterator.next();
        if ((!(localMetadataField instanceof b)) && (localMetadataField != kd.Ql)) {
          localMetadataField.a(this.II, localMetadataBundle, this.Ns, this.JY);
        }
      }
      return new l(localMetadataBundle);
    }
    
    public boolean isDataValid()
    {
      return !this.II.isClosed();
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/MetadataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */