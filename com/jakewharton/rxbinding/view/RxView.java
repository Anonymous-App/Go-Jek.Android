package com.jakewharton.rxbinding.view;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import com.jakewharton.rxbinding.internal.Functions;
import com.jakewharton.rxbinding.internal.Preconditions;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

public final class RxView
{
  private RxView()
  {
    throw new AssertionError("No instances.");
  }
  
  @CheckResult
  @NonNull
  public static Action1<? super Boolean> activated(View paramView)
  {
    return new RxView.1(paramView);
  }
  
  @CheckResult
  @NonNull
  public static Observable<ViewAttachEvent> attachEvents(@NonNull View paramView)
  {
    return Observable.create(new ViewAttachEventOnSubscribe(paramView));
  }
  
  @CheckResult
  @NonNull
  public static Observable<Object> attaches(@NonNull View paramView)
  {
    return Observable.create(new ViewAttachesOnSubscribe(paramView, true));
  }
  
  @CheckResult
  @NonNull
  public static Observable<ViewClickEvent> clickEvents(@NonNull View paramView)
  {
    return Observable.create(new ViewClickEventOnSubscribe(paramView));
  }
  
  @CheckResult
  @NonNull
  public static Action1<? super Boolean> clickable(@NonNull View paramView)
  {
    return new RxView.2(paramView);
  }
  
  @CheckResult
  @NonNull
  public static Observable<Object> clicks(@NonNull View paramView)
  {
    return Observable.create(new ViewClickOnSubscribe(paramView));
  }
  
  @CheckResult
  @NonNull
  public static Observable<Object> detaches(@NonNull View paramView)
  {
    return Observable.create(new ViewAttachesOnSubscribe(paramView, false));
  }
  
  @CheckResult
  @NonNull
  public static Observable<ViewDragEvent> dragEvents(@NonNull View paramView)
  {
    return Observable.create(new ViewDragEventOnSubscribe(paramView, Functions.FUNC1_ALWAYS_TRUE));
  }
  
  @CheckResult
  @NonNull
  public static Observable<ViewDragEvent> dragEvents(@NonNull View paramView, @NonNull Func1<? super ViewDragEvent, Boolean> paramFunc1)
  {
    return Observable.create(new ViewDragEventOnSubscribe(paramView, paramFunc1));
  }
  
  @CheckResult
  @NonNull
  public static Observable<DragEvent> drags(@NonNull View paramView)
  {
    return Observable.create(new ViewDragOnSubscribe(paramView, Functions.FUNC1_ALWAYS_TRUE));
  }
  
  @CheckResult
  @NonNull
  public static Observable<DragEvent> drags(@NonNull View paramView, @NonNull Func1<DragEvent, Boolean> paramFunc1)
  {
    return Observable.create(new ViewDragOnSubscribe(paramView, paramFunc1));
  }
  
  @CheckResult
  @NonNull
  public static Action1<? super Boolean> enabled(@NonNull View paramView)
  {
    return new RxView.3(paramView);
  }
  
  @CheckResult
  @NonNull
  public static Observable<ViewFocusChangeEvent> focusChangeEvents(@NonNull View paramView)
  {
    return Observable.create(new ViewFocusChangeEventOnSubscribe(paramView));
  }
  
  @CheckResult
  @NonNull
  public static Observable<Boolean> focusChanges(@NonNull View paramView)
  {
    return Observable.create(new ViewFocusChangeOnSubscribe(paramView));
  }
  
  @CheckResult
  @NonNull
  public static Observable<ViewLongClickEvent> longClickEvents(@NonNull View paramView)
  {
    return Observable.create(new ViewLongClickEventOnSubscribe(paramView, Functions.FUNC1_ALWAYS_TRUE));
  }
  
  @CheckResult
  @NonNull
  public static Observable<ViewLongClickEvent> longClickEvents(@NonNull View paramView, @NonNull Func1<? super ViewLongClickEvent, Boolean> paramFunc1)
  {
    return Observable.create(new ViewLongClickEventOnSubscribe(paramView, paramFunc1));
  }
  
  @CheckResult
  @NonNull
  public static Observable<Object> longClicks(@NonNull View paramView)
  {
    return Observable.create(new ViewLongClickOnSubscribe(paramView, Functions.FUNC0_ALWAYS_TRUE));
  }
  
  @CheckResult
  @NonNull
  public static Observable<Object> longClicks(@NonNull View paramView, @NonNull Func0<Boolean> paramFunc0)
  {
    return Observable.create(new ViewLongClickOnSubscribe(paramView, paramFunc0));
  }
  
  @CheckResult
  @NonNull
  public static Action1<? super Boolean> pressed(@NonNull View paramView)
  {
    return new RxView.4(paramView);
  }
  
  @CheckResult
  @NonNull
  public static Action1<? super Boolean> selected(@NonNull View paramView)
  {
    return new RxView.5(paramView);
  }
  
  @CheckResult
  @NonNull
  public static Observable<ViewTouchEvent> touchEvents(@NonNull View paramView)
  {
    return touchEvents(paramView, Functions.FUNC1_ALWAYS_TRUE);
  }
  
  @CheckResult
  @NonNull
  public static Observable<ViewTouchEvent> touchEvents(@NonNull View paramView, @NonNull Func1<? super ViewTouchEvent, Boolean> paramFunc1)
  {
    return Observable.create(new ViewTouchEventOnSubscribe(paramView, paramFunc1));
  }
  
  @CheckResult
  @NonNull
  public static Observable<MotionEvent> touches(@NonNull View paramView)
  {
    return touches(paramView, Functions.FUNC1_ALWAYS_TRUE);
  }
  
  @CheckResult
  @NonNull
  public static Observable<MotionEvent> touches(@NonNull View paramView, @NonNull Func1<? super MotionEvent, Boolean> paramFunc1)
  {
    return Observable.create(new ViewTouchOnSubscribe(paramView, paramFunc1));
  }
  
  @CheckResult
  @NonNull
  public static Action1<? super Boolean> visibility(@NonNull View paramView)
  {
    return visibility(paramView, 8);
  }
  
  @CheckResult
  @NonNull
  public static Action1<? super Boolean> visibility(@NonNull View paramView, int paramInt)
  {
    boolean bool2 = false;
    if (paramInt != 0) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      Preconditions.checkArgument(bool1, "Setting visibility to VISIBLE when false would have no effect.");
      if (paramInt != 4)
      {
        bool1 = bool2;
        if (paramInt != 8) {}
      }
      else
      {
        bool1 = true;
      }
      Preconditions.checkArgument(bool1, "Must set visibility to INVISIBLE or GONE when false.");
      return new RxView.6(paramView, paramInt);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/jakewharton/rxbinding/view/RxView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */