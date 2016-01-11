package com.google.android.gms.drive.query;

import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.SearchableMetadataField;
import com.google.android.gms.drive.metadata.SearchableOrderedMetadataField;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.internal.kd;
import com.google.android.gms.internal.kf;
import java.util.Date;

public class SearchableField
{
  public static final SearchableMetadataField<Boolean> IS_PINNED = kd.PY;
  public static final SearchableOrderedMetadataField<Date> LAST_VIEWED_BY_ME;
  public static final SearchableMetadataField<String> MIME_TYPE;
  public static final SearchableOrderedMetadataField<Date> MODIFIED_DATE;
  public static final SearchableCollectionMetadataField<DriveId> PARENTS;
  public static final SearchableOrderedMetadataField<Date> QG;
  public static final SearchableMetadataField<AppVisibleCustomProperties> QH = kd.PO;
  public static final SearchableMetadataField<Boolean> STARRED;
  public static final SearchableMetadataField<String> TITLE = kd.Qm;
  public static final SearchableMetadataField<Boolean> TRASHED;
  
  static
  {
    MIME_TYPE = kd.Qd;
    TRASHED = kd.Qn;
    PARENTS = kd.Qi;
    QG = kf.Qx;
    STARRED = kd.Qk;
    MODIFIED_DATE = kf.Qv;
    LAST_VIEWED_BY_ME = kf.Qu;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/SearchableField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */