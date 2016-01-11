package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.internal.kd;
import com.google.android.gms.internal.kf;
import java.util.Date;

public class SortableField
{
  public static final SortableMetadataField<Date> CREATED_DATE;
  public static final SortableMetadataField<Date> LAST_VIEWED_BY_ME;
  public static final SortableMetadataField<Date> MODIFIED_BY_ME_DATE;
  public static final SortableMetadataField<Date> MODIFIED_DATE;
  public static final SortableMetadataField<Long> QUOTA_USED = kd.Qj;
  public static final SortableMetadataField<Date> SHARED_WITH_ME_DATE;
  public static final SortableMetadataField<String> TITLE = kd.Qm;
  
  static
  {
    CREATED_DATE = kf.Qt;
    MODIFIED_DATE = kf.Qv;
    MODIFIED_BY_ME_DATE = kf.Qw;
    LAST_VIEWED_BY_ME = kf.Qu;
    SHARED_WITH_ME_DATE = kf.Qx;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/query/SortableField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */