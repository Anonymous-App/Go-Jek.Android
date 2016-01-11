package nucleus.presenter.delivery;

import android.support.annotation.Nullable;
import rx.Notification;
import rx.Notification.Kind;
import rx.functions.Action2;

public final class Delivery<View, T>
{
  private final Notification<T> notification;
  private final View view;
  
  public Delivery(View paramView, Notification<T> paramNotification)
  {
    this.view = paramView;
    this.notification = paramNotification;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (Delivery)paramObject;
      if (this.view != null)
      {
        if (this.view.equals(((Delivery)paramObject).view)) {}
      }
      else {
        while (((Delivery)paramObject).view != null) {
          return false;
        }
      }
      if (this.notification == null) {
        break;
      }
    } while (this.notification.equals(((Delivery)paramObject).notification));
    while (((Delivery)paramObject).notification != null) {
      return false;
    }
    return true;
  }
  
  public int hashCode()
  {
    int j = 0;
    if (this.view != null) {}
    for (int i = this.view.hashCode();; i = 0)
    {
      if (this.notification != null) {
        j = this.notification.hashCode();
      }
      return i * 31 + j;
    }
  }
  
  public void split(Action2<View, T> paramAction2, @Nullable Action2<View, Throwable> paramAction21)
  {
    if (this.notification.getKind() == Notification.Kind.OnNext) {
      paramAction2.call(this.view, this.notification.getValue());
    }
    while ((paramAction21 == null) || (this.notification.getKind() != Notification.Kind.OnError)) {
      return;
    }
    paramAction21.call(this.view, this.notification.getThrowable());
  }
  
  public String toString()
  {
    return "Delivery{view=" + this.view + ", notification=" + this.notification + '}';
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/nucleus/presenter/delivery/Delivery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */