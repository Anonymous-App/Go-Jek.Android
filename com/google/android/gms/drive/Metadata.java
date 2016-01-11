package com.google.android.gms.drive;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.kd;
import com.google.android.gms.internal.kf;
import com.google.android.gms.internal.kh;
import java.util.Date;

public abstract class Metadata
  implements Freezable<Metadata>
{
  public static final int CONTENT_AVAILABLE_LOCALLY = 1;
  public static final int CONTENT_NOT_AVAILABLE_LOCALLY = 0;
  
  protected abstract <T> T a(MetadataField<T> paramMetadataField);
  
  public String getAlternateLink()
  {
    return (String)a(kd.PN);
  }
  
  public int getContentAvailability()
  {
    Integer localInteger = (Integer)a(kh.Qz);
    if (localInteger == null) {
      return 0;
    }
    return localInteger.intValue();
  }
  
  public Date getCreatedDate()
  {
    return (Date)a(kf.Qt);
  }
  
  public String getDescription()
  {
    return (String)a(kd.PP);
  }
  
  public DriveId getDriveId()
  {
    return (DriveId)a(kd.PM);
  }
  
  public String getEmbedLink()
  {
    return (String)a(kd.PQ);
  }
  
  public String getFileExtension()
  {
    return (String)a(kd.PR);
  }
  
  public long getFileSize()
  {
    return ((Long)a(kd.PS)).longValue();
  }
  
  public Date getLastViewedByMeDate()
  {
    return (Date)a(kf.Qu);
  }
  
  public String getMimeType()
  {
    return (String)a(kd.Qd);
  }
  
  public Date getModifiedByMeDate()
  {
    return (Date)a(kf.Qw);
  }
  
  public Date getModifiedDate()
  {
    return (Date)a(kf.Qv);
  }
  
  public String getOriginalFilename()
  {
    return (String)a(kd.Qe);
  }
  
  public long getQuotaBytesUsed()
  {
    return ((Long)a(kd.Qj)).longValue();
  }
  
  public Date getSharedWithMeDate()
  {
    return (Date)a(kf.Qx);
  }
  
  public String getTitle()
  {
    return (String)a(kd.Qm);
  }
  
  public String getWebContentLink()
  {
    return (String)a(kd.Qo);
  }
  
  public String getWebViewLink()
  {
    return (String)a(kd.Qp);
  }
  
  public boolean isEditable()
  {
    Boolean localBoolean = (Boolean)a(kd.PX);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isFolder()
  {
    return "application/vnd.google-apps.folder".equals(getMimeType());
  }
  
  public boolean isInAppFolder()
  {
    Boolean localBoolean = (Boolean)a(kd.PV);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isPinnable()
  {
    Boolean localBoolean = (Boolean)a(kh.QA);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isPinned()
  {
    Boolean localBoolean = (Boolean)a(kd.PY);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isRestricted()
  {
    Boolean localBoolean = (Boolean)a(kd.PZ);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isShared()
  {
    Boolean localBoolean = (Boolean)a(kd.Qa);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isStarred()
  {
    Boolean localBoolean = (Boolean)a(kd.Qk);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isTrashed()
  {
    Boolean localBoolean = (Boolean)a(kd.Qn);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
  
  public boolean isViewed()
  {
    Boolean localBoolean = (Boolean)a(kd.Qc);
    if (localBoolean == null) {
      return false;
    }
    return localBoolean.booleanValue();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/Metadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */