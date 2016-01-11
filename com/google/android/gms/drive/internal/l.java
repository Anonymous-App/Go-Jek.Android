package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class l
  extends Metadata
{
  private final MetadataBundle Or;
  
  public l(MetadataBundle paramMetadataBundle)
  {
    this.Or = paramMetadataBundle;
  }
  
  protected <T> T a(MetadataField<T> paramMetadataField)
  {
    return (T)this.Or.a(paramMetadataField);
  }
  
  public Metadata hR()
  {
    return new l(MetadataBundle.a(this.Or));
  }
  
  public boolean isDataValid()
  {
    return this.Or != null;
  }
  
  public String toString()
  {
    return "Metadata [mImpl=" + this.Or + "]";
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */