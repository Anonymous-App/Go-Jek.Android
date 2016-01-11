package com.google.android.gms.drive;

import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties.a;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.internal.kd;
import com.google.android.gms.internal.kf;
import java.util.Date;

public final class MetadataChangeSet
{
  public static final MetadataChangeSet Nt = new MetadataChangeSet(MetadataBundle.io());
  private final MetadataBundle Nu;
  
  public MetadataChangeSet(MetadataBundle paramMetadataBundle)
  {
    this.Nu = MetadataBundle.a(paramMetadataBundle);
  }
  
  public String getDescription()
  {
    return (String)this.Nu.a(kd.PP);
  }
  
  public String getIndexableText()
  {
    return (String)this.Nu.a(kd.PU);
  }
  
  public Date getLastViewedByMeDate()
  {
    return (Date)this.Nu.a(kf.Qu);
  }
  
  public String getMimeType()
  {
    return (String)this.Nu.a(kd.Qd);
  }
  
  public String getTitle()
  {
    return (String)this.Nu.a(kd.Qm);
  }
  
  public MetadataBundle hS()
  {
    return this.Nu;
  }
  
  public Boolean isPinned()
  {
    return (Boolean)this.Nu.a(kd.PY);
  }
  
  public Boolean isStarred()
  {
    return (Boolean)this.Nu.a(kd.Qk);
  }
  
  public Boolean isViewed()
  {
    return (Boolean)this.Nu.a(kd.Qc);
  }
  
  public static class Builder
  {
    private final MetadataBundle Nu = MetadataBundle.io();
    private AppVisibleCustomProperties.a Nv;
    
    public MetadataChangeSet build()
    {
      if (this.Nv != null) {
        this.Nu.b(kd.PO, this.Nv.im());
      }
      return new MetadataChangeSet(this.Nu);
    }
    
    public Builder setDescription(String paramString)
    {
      this.Nu.b(kd.PP, paramString);
      return this;
    }
    
    public Builder setIndexableText(String paramString)
    {
      this.Nu.b(kd.PU, paramString);
      return this;
    }
    
    public Builder setLastViewedByMeDate(Date paramDate)
    {
      this.Nu.b(kf.Qu, paramDate);
      return this;
    }
    
    public Builder setMimeType(String paramString)
    {
      this.Nu.b(kd.Qd, paramString);
      return this;
    }
    
    public Builder setPinned(boolean paramBoolean)
    {
      this.Nu.b(kd.PY, Boolean.valueOf(paramBoolean));
      return this;
    }
    
    public Builder setStarred(boolean paramBoolean)
    {
      this.Nu.b(kd.Qk, Boolean.valueOf(paramBoolean));
      return this;
    }
    
    public Builder setTitle(String paramString)
    {
      this.Nu.b(kd.Qm, paramString);
      return this;
    }
    
    public Builder setViewed(boolean paramBoolean)
    {
      this.Nu.b(kd.Qc, Boolean.valueOf(paramBoolean));
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/MetadataChangeSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */