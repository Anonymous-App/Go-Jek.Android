package com.jakewharton.rxbinding.widget;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.widget.TextView;
import com.jakewharton.rxbinding.internal.Functions;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public final class RxTextView
{
  private RxTextView()
  {
    throw new AssertionError("No instances.");
  }
  
  @CheckResult
  @NonNull
  public static Observable<TextViewEditorActionEvent> editorActionEvents(@NonNull TextView paramTextView)
  {
    return editorActionEvents(paramTextView, Functions.FUNC1_ALWAYS_TRUE);
  }
  
  @CheckResult
  @NonNull
  public static Observable<TextViewEditorActionEvent> editorActionEvents(@NonNull TextView paramTextView, @NonNull Func1<? super TextViewEditorActionEvent, Boolean> paramFunc1)
  {
    return Observable.create(new TextViewEditorActionEventOnSubscribe(paramTextView, paramFunc1));
  }
  
  @CheckResult
  @NonNull
  public static Observable<Integer> editorActions(@NonNull TextView paramTextView)
  {
    return editorActions(paramTextView, Functions.FUNC1_ALWAYS_TRUE);
  }
  
  @CheckResult
  @NonNull
  public static Observable<Integer> editorActions(@NonNull TextView paramTextView, @NonNull Func1<? super Integer, Boolean> paramFunc1)
  {
    return Observable.create(new TextViewEditorActionOnSubscribe(paramTextView, paramFunc1));
  }
  
  @CheckResult
  @NonNull
  public static Action1<? super CharSequence> text(@NonNull TextView paramTextView)
  {
    return new RxTextView.1(paramTextView);
  }
  
  @CheckResult
  @NonNull
  public static Observable<TextViewTextChangeEvent> textChangeEvents(@NonNull TextView paramTextView)
  {
    return Observable.create(new TextViewTextEventOnSubscribe(paramTextView));
  }
  
  @CheckResult
  @NonNull
  public static Observable<CharSequence> textChanges(@NonNull TextView paramTextView)
  {
    return Observable.create(new TextViewTextOnSubscribe(paramTextView));
  }
  
  @CheckResult
  @NonNull
  public static Action1<? super Integer> textRes(@NonNull TextView paramTextView)
  {
    return new RxTextView.2(paramTextView);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/jakewharton/rxbinding/widget/RxTextView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */