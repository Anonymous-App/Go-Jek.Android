package com.google.android.gms.internal;

import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.metadata.internal.d;
import java.util.Date;

public class kf
{
  public static final a Qt = new a("created", 4100000);
  public static final b Qu = new b("lastOpenedTime", 4300000);
  public static final d Qv = new d("modified", 4100000);
  public static final c Qw = new c("modifiedByMe", 4100000);
  public static final e Qx = new e("sharedWithMe", 4100000);
  
  public static class a
    extends d
    implements SortableMetadataField<Date>
  {
    public a(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class b
    extends d
    implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date>
  {
    public b(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class c
    extends d
    implements SortableMetadataField<Date>
  {
    public c(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class d
    extends d
    implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date>
  {
    public d(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class e
    extends d
    implements SearchableOrderedMetadataField<Date>, SortableMetadataField<Date>
  {
    public e(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/kf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */