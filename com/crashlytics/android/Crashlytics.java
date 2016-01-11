package com.crashlytics.android;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.beta.Beta;
import com.crashlytics.android.core.CrashlyticsCore;
import com.crashlytics.android.core.CrashlyticsCore.Builder;
import com.crashlytics.android.core.CrashlyticsListener;
import com.crashlytics.android.core.PinningInfoProvider;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import io.fabric.sdk.android.KitGroup;
import io.fabric.sdk.android.Logger;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Crashlytics
  extends Kit<Void>
  implements KitGroup
{
  public static final String TAG = "Crashlytics";
  public final Answers answers;
  public final Beta beta;
  public final CrashlyticsCore core;
  public final Collection<? extends Kit> kits;
  
  public Crashlytics()
  {
    this(new Answers(), new Beta(), new CrashlyticsCore());
  }
  
  Crashlytics(Answers paramAnswers, Beta paramBeta, CrashlyticsCore paramCrashlyticsCore)
  {
    this.answers = paramAnswers;
    this.beta = paramBeta;
    this.core = paramCrashlyticsCore;
    this.kits = Collections.unmodifiableCollection(Arrays.asList(new Kit[] { paramAnswers, paramBeta, paramCrashlyticsCore }));
  }
  
  private static void checkInitialized()
  {
    if (getInstance() == null) {
      throw new IllegalStateException("Crashlytics must be initialized by calling Fabric.with(Context) prior to calling Crashlytics.getInstance()");
    }
  }
  
  public static Crashlytics getInstance()
  {
    return (Crashlytics)Fabric.getKit(Crashlytics.class);
  }
  
  public static PinningInfoProvider getPinningInfoProvider()
  {
    checkInitialized();
    return getInstance().core.getPinningInfoProvider();
  }
  
  public static void log(int paramInt, String paramString1, String paramString2)
  {
    checkInitialized();
    getInstance().core.log(paramInt, paramString1, paramString2);
  }
  
  public static void log(String paramString)
  {
    checkInitialized();
    getInstance().core.log(paramString);
  }
  
  public static void logException(Throwable paramThrowable)
  {
    checkInitialized();
    getInstance().core.logException(paramThrowable);
  }
  
  public static void setBool(String paramString, boolean paramBoolean)
  {
    checkInitialized();
    getInstance().core.setBool(paramString, paramBoolean);
  }
  
  public static void setDouble(String paramString, double paramDouble)
  {
    checkInitialized();
    getInstance().core.setDouble(paramString, paramDouble);
  }
  
  public static void setFloat(String paramString, float paramFloat)
  {
    checkInitialized();
    getInstance().core.setFloat(paramString, paramFloat);
  }
  
  public static void setInt(String paramString, int paramInt)
  {
    checkInitialized();
    getInstance().core.setInt(paramString, paramInt);
  }
  
  public static void setLong(String paramString, long paramLong)
  {
    checkInitialized();
    getInstance().core.setLong(paramString, paramLong);
  }
  
  @Deprecated
  public static void setPinningInfoProvider(PinningInfoProvider paramPinningInfoProvider)
  {
    Fabric.getLogger().w("Crashlytics", "Use of Crashlytics.setPinningInfoProvider is deprecated");
  }
  
  public static void setString(String paramString1, String paramString2)
  {
    checkInitialized();
    getInstance().core.setString(paramString1, paramString2);
  }
  
  public static void setUserEmail(String paramString)
  {
    checkInitialized();
    getInstance().core.setUserEmail(paramString);
  }
  
  public static void setUserIdentifier(String paramString)
  {
    checkInitialized();
    getInstance().core.setUserIdentifier(paramString);
  }
  
  public static void setUserName(String paramString)
  {
    checkInitialized();
    getInstance().core.setUserName(paramString);
  }
  
  public void crash()
  {
    this.core.crash();
  }
  
  protected Void doInBackground()
  {
    return null;
  }
  
  @Deprecated
  public boolean getDebugMode()
  {
    Fabric.getLogger().w("Crashlytics", "Use of Crashlytics.getDebugMode is deprecated.");
    getFabric();
    return Fabric.isDebuggable();
  }
  
  public String getIdentifier()
  {
    return "com.crashlytics.sdk.android:crashlytics";
  }
  
  public Collection<? extends Kit> getKits()
  {
    return this.kits;
  }
  
  public String getVersion()
  {
    return "2.5.2.79";
  }
  
  @Deprecated
  public void setDebugMode(boolean paramBoolean)
  {
    Fabric.getLogger().w("Crashlytics", "Use of Crashlytics.setDebugMode is deprecated.");
  }
  
  @Deprecated
  public void setListener(CrashlyticsListener paramCrashlyticsListener)
  {
    try
    {
      this.core.setListener(paramCrashlyticsListener);
      return;
    }
    finally
    {
      paramCrashlyticsListener = finally;
      throw paramCrashlyticsListener;
    }
  }
  
  public boolean verifyPinning(URL paramURL)
  {
    return this.core.verifyPinning(paramURL);
  }
  
  public static class Builder
  {
    private Answers answers;
    private Beta beta;
    private CrashlyticsCore core;
    private CrashlyticsCore.Builder coreBuilder;
    
    private CrashlyticsCore.Builder getCoreBuilder()
    {
      try
      {
        if (this.coreBuilder == null) {
          this.coreBuilder = new CrashlyticsCore.Builder();
        }
        CrashlyticsCore.Builder localBuilder = this.coreBuilder;
        return localBuilder;
      }
      finally {}
    }
    
    public Builder answers(Answers paramAnswers)
    {
      if (paramAnswers == null) {
        throw new NullPointerException("Answers Kit must not be null.");
      }
      if (this.answers != null) {
        throw new IllegalStateException("Answers Kit already set.");
      }
      this.answers = paramAnswers;
      return this;
    }
    
    public Builder beta(Beta paramBeta)
    {
      if (paramBeta == null) {
        throw new NullPointerException("Beta Kit must not be null.");
      }
      if (this.beta != null) {
        throw new IllegalStateException("Beta Kit already set.");
      }
      this.beta = paramBeta;
      return this;
    }
    
    public Crashlytics build()
    {
      if (this.coreBuilder != null)
      {
        if (this.core != null) {
          throw new IllegalStateException("Must not use Deprecated methods delay(), disabled(), listener(), pinningInfoProvider() with core()");
        }
        this.core = this.coreBuilder.build();
      }
      if (this.answers == null) {
        this.answers = new Answers();
      }
      if (this.beta == null) {
        this.beta = new Beta();
      }
      if (this.core == null) {
        this.core = new CrashlyticsCore();
      }
      return new Crashlytics(this.answers, this.beta, this.core);
    }
    
    public Builder core(CrashlyticsCore paramCrashlyticsCore)
    {
      if (paramCrashlyticsCore == null) {
        throw new NullPointerException("CrashlyticsCore Kit must not be null.");
      }
      if (this.core != null) {
        throw new IllegalStateException("CrashlyticsCore Kit already set.");
      }
      this.core = paramCrashlyticsCore;
      return this;
    }
    
    @Deprecated
    public Builder delay(float paramFloat)
    {
      getCoreBuilder().delay(paramFloat);
      return this;
    }
    
    @Deprecated
    public Builder disabled(boolean paramBoolean)
    {
      getCoreBuilder().disabled(paramBoolean);
      return this;
    }
    
    @Deprecated
    public Builder listener(CrashlyticsListener paramCrashlyticsListener)
    {
      getCoreBuilder().listener(paramCrashlyticsListener);
      return this;
    }
    
    @Deprecated
    public Builder pinningInfo(PinningInfoProvider paramPinningInfoProvider)
    {
      getCoreBuilder().pinningInfo(paramPinningInfoProvider);
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/crashlytics/android/Crashlytics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */