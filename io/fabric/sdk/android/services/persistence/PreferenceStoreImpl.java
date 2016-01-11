package io.fabric.sdk.android.services.persistence;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import io.fabric.sdk.android.Kit;

public class PreferenceStoreImpl
  implements PreferenceStore
{
  private final Context context;
  private final String preferenceName;
  private final SharedPreferences sharedPreferences;
  
  public PreferenceStoreImpl(Context paramContext, String paramString)
  {
    if (paramContext == null) {
      throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
    }
    this.context = paramContext;
    this.preferenceName = paramString;
    this.sharedPreferences = this.context.getSharedPreferences(this.preferenceName, 0);
  }
  
  @Deprecated
  public PreferenceStoreImpl(Kit paramKit)
  {
    this(paramKit.getContext(), paramKit.getClass().getName());
  }
  
  public SharedPreferences.Editor edit()
  {
    return this.sharedPreferences.edit();
  }
  
  public SharedPreferences get()
  {
    return this.sharedPreferences;
  }
  
  @TargetApi(9)
  public boolean save(SharedPreferences.Editor paramEditor)
  {
    if (Build.VERSION.SDK_INT >= 9)
    {
      paramEditor.apply();
      return true;
    }
    return paramEditor.commit();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/fabric/sdk/android/services/persistence/PreferenceStoreImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */