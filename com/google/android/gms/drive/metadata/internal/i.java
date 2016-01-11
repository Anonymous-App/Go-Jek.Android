package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class i<T extends Parcelable>
  extends b<T>
{
  public i(String paramString, int paramInt)
  {
    super(paramString, Collections.emptySet(), Collections.singleton(paramString), paramInt);
  }
  
  protected void a(Bundle paramBundle, Collection<T> paramCollection)
  {
    paramBundle.putParcelableArrayList(getName(), new ArrayList(paramCollection));
  }
  
  protected Collection<T> l(Bundle paramBundle)
  {
    return paramBundle.getParcelableArrayList(getName());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/metadata/internal/i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */