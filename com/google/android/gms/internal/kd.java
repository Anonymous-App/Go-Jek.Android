package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.a;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.g;
import com.google.android.gms.drive.metadata.internal.i;
import com.google.android.gms.drive.metadata.internal.j;
import com.google.android.gms.drive.metadata.internal.k;
import com.google.android.gms.drive.metadata.internal.l;
import com.google.android.gms.drive.metadata.internal.m;
import java.util.Collection;
import java.util.Collections;

public class kd
{
  public static final MetadataField<DriveId> PM = kg.Qy;
  public static final MetadataField<String> PN = new l("alternateLink", 4300000);
  public static final a PO = new a(5000000);
  public static final MetadataField<String> PP = new l("description", 4300000);
  public static final MetadataField<String> PQ = new l("embedLink", 4300000);
  public static final MetadataField<String> PR = new l("fileExtension", 4300000);
  public static final MetadataField<Long> PS = new g("fileSize", 4300000);
  public static final MetadataField<Boolean> PT = new com.google.android.gms.drive.metadata.internal.b("hasThumbnail", 4300000);
  public static final MetadataField<String> PU = new l("indexableText", 4300000);
  public static final MetadataField<Boolean> PV = new com.google.android.gms.drive.metadata.internal.b("isAppData", 4300000);
  public static final MetadataField<Boolean> PW = new com.google.android.gms.drive.metadata.internal.b("isCopyable", 4300000);
  public static final MetadataField<Boolean> PX = new com.google.android.gms.drive.metadata.internal.b("isEditable", 4100000);
  public static final b PY = new b("isPinned", 4100000);
  public static final MetadataField<Boolean> PZ = new com.google.android.gms.drive.metadata.internal.b("isRestricted", 4300000);
  public static final MetadataField<Boolean> Qa = new com.google.android.gms.drive.metadata.internal.b("isShared", 4300000);
  public static final MetadataField<Boolean> Qb = new com.google.android.gms.drive.metadata.internal.b("isTrashable", 4400000);
  public static final MetadataField<Boolean> Qc = new com.google.android.gms.drive.metadata.internal.b("isViewed", 4300000);
  public static final c Qd = new c("mimeType", 4100000);
  public static final MetadataField<String> Qe = new l("originalFilename", 4300000);
  public static final com.google.android.gms.drive.metadata.b<String> Qf = new k("ownerNames", 4300000);
  public static final m Qg = new m("lastModifyingUser", 6000000);
  public static final m Qh = new m("sharingUser", 6000000);
  public static final d Qi = new d("parents", 4100000);
  public static final e Qj = new e("quotaBytesUsed", 4300000);
  public static final f Qk = new f("starred", 4100000);
  public static final MetadataField<a> Ql = new j("thumbnail", Collections.emptySet(), Collections.emptySet(), 4400000)
  {
    protected a k(DataHolder paramAnonymousDataHolder, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      throw new IllegalStateException("Thumbnail field is write only");
    }
  };
  public static final g Qm = new g("title", 4100000);
  public static final h Qn = new h("trashed", 4100000);
  public static final MetadataField<String> Qo = new l("webContentLink", 4300000);
  public static final MetadataField<String> Qp = new l("webViewLink", 4300000);
  public static final MetadataField<String> Qq = new l("uniqueIdentifier", 5000000);
  public static final com.google.android.gms.drive.metadata.internal.b Qr = new com.google.android.gms.drive.metadata.internal.b("writersCanShare", 6000000);
  public static final MetadataField<String> Qs = new l("role", 6000000);
  
  public static class a
    extends ke
    implements SearchableMetadataField<AppVisibleCustomProperties>
  {
    public a(int paramInt)
    {
      super();
    }
  }
  
  public static class b
    extends com.google.android.gms.drive.metadata.internal.b
    implements SearchableMetadataField<Boolean>
  {
    public b(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class c
    extends l
    implements SearchableMetadataField<String>
  {
    public c(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class d
    extends i<DriveId>
    implements SearchableCollectionMetadataField<DriveId>
  {
    public d(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class e
    extends g
    implements SortableMetadataField<Long>
  {
    public e(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class f
    extends com.google.android.gms.drive.metadata.internal.b
    implements SearchableMetadataField<Boolean>
  {
    public f(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class g
    extends l
    implements SearchableMetadataField<String>, SortableMetadataField<String>
  {
    public g(String paramString, int paramInt)
    {
      super(paramInt);
    }
  }
  
  public static class h
    extends com.google.android.gms.drive.metadata.internal.b
    implements SearchableMetadataField<Boolean>
  {
    public h(String paramString, int paramInt)
    {
      super(paramInt);
    }
    
    protected Boolean e(DataHolder paramDataHolder, int paramInt1, int paramInt2)
    {
      if (paramDataHolder.b(getName(), paramInt1, paramInt2) != 0) {}
      for (boolean bool = true;; bool = false) {
        return Boolean.valueOf(bool);
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/internal/kd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */