package io.fabric.sdk.android;

import android.content.Context;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.Task;
import java.io.File;
import java.util.Collection;

public abstract class Kit<Result>
  implements Comparable<Kit>
{
  Context context;
  Fabric fabric;
  IdManager idManager;
  InitializationCallback<Result> initializationCallback;
  InitializationTask<Result> initializationTask = new InitializationTask(this);
  
  public int compareTo(Kit paramKit)
  {
    if (containsAnnotatedDependency(paramKit)) {}
    do
    {
      return 1;
      if (paramKit.containsAnnotatedDependency(this)) {
        return -1;
      }
    } while ((hasAnnotatedDependency()) && (!paramKit.hasAnnotatedDependency()));
    if ((!hasAnnotatedDependency()) && (paramKit.hasAnnotatedDependency())) {
      return -1;
    }
    return 0;
  }
  
  boolean containsAnnotatedDependency(Kit paramKit)
  {
    Object localObject = (DependsOn)getClass().getAnnotation(DependsOn.class);
    if (localObject != null)
    {
      localObject = ((DependsOn)localObject).value();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        if (localObject[i].equals(paramKit.getClass())) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  protected abstract Result doInBackground();
  
  public Context getContext()
  {
    return this.context;
  }
  
  protected Collection<Task> getDependencies()
  {
    return this.initializationTask.getDependencies();
  }
  
  public Fabric getFabric()
  {
    return this.fabric;
  }
  
  protected IdManager getIdManager()
  {
    return this.idManager;
  }
  
  public abstract String getIdentifier();
  
  public String getPath()
  {
    return ".Fabric" + File.separator + getIdentifier();
  }
  
  public abstract String getVersion();
  
  boolean hasAnnotatedDependency()
  {
    return (DependsOn)getClass().getAnnotation(DependsOn.class) != null;
  }
  
  final void initialize()
  {
    this.initializationTask.executeOnExecutor(this.fabric.getExecutorService(), new Void[] { (Void)null });
  }
  
  void injectParameters(Context paramContext, Fabric paramFabric, InitializationCallback<Result> paramInitializationCallback, IdManager paramIdManager)
  {
    this.fabric = paramFabric;
    this.context = new FabricContext(paramContext, getIdentifier(), getPath());
    this.initializationCallback = paramInitializationCallback;
    this.idManager = paramIdManager;
  }
  
  protected void onCancelled(Result paramResult) {}
  
  protected void onPostExecute(Result paramResult) {}
  
  protected boolean onPreExecute()
  {
    return true;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/io/fabric/sdk/android/Kit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */