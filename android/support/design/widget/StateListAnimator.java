package android.support.design.widget;

import android.util.StateSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

final class StateListAnimator
{
  private Animation.AnimationListener mAnimationListener = new Animation.AnimationListener()
  {
    public void onAnimationEnd(Animation paramAnonymousAnimation)
    {
      if (StateListAnimator.this.mRunningAnimation == paramAnonymousAnimation) {
        StateListAnimator.access$002(StateListAnimator.this, null);
      }
    }
    
    public void onAnimationRepeat(Animation paramAnonymousAnimation) {}
    
    public void onAnimationStart(Animation paramAnonymousAnimation) {}
  };
  private Tuple mLastMatch = null;
  private Animation mRunningAnimation = null;
  private final ArrayList<Tuple> mTuples = new ArrayList();
  private WeakReference<View> mViewRef;
  
  private void cancel()
  {
    if (this.mRunningAnimation != null)
    {
      View localView = getTarget();
      if ((localView != null) && (localView.getAnimation() == this.mRunningAnimation)) {
        localView.clearAnimation();
      }
      this.mRunningAnimation = null;
    }
  }
  
  private void clearTarget()
  {
    View localView = getTarget();
    int j = this.mTuples.size();
    int i = 0;
    while (i < j)
    {
      Animation localAnimation = ((Tuple)this.mTuples.get(i)).mAnimation;
      if (localView.getAnimation() == localAnimation) {
        localView.clearAnimation();
      }
      i += 1;
    }
    this.mViewRef = null;
    this.mLastMatch = null;
    this.mRunningAnimation = null;
  }
  
  private void start(Tuple paramTuple)
  {
    this.mRunningAnimation = paramTuple.mAnimation;
    paramTuple = getTarget();
    if (paramTuple != null) {
      paramTuple.startAnimation(this.mRunningAnimation);
    }
  }
  
  public void addState(int[] paramArrayOfInt, Animation paramAnimation)
  {
    paramArrayOfInt = new Tuple(paramArrayOfInt, paramAnimation, null);
    paramAnimation.setAnimationListener(this.mAnimationListener);
    this.mTuples.add(paramArrayOfInt);
  }
  
  Animation getRunningAnimation()
  {
    return this.mRunningAnimation;
  }
  
  View getTarget()
  {
    if (this.mViewRef == null) {
      return null;
    }
    return (View)this.mViewRef.get();
  }
  
  ArrayList<Tuple> getTuples()
  {
    return this.mTuples;
  }
  
  public void jumpToCurrentState()
  {
    if (this.mRunningAnimation != null)
    {
      View localView = getTarget();
      if ((localView != null) && (localView.getAnimation() == this.mRunningAnimation)) {
        localView.clearAnimation();
      }
    }
  }
  
  void setState(int[] paramArrayOfInt)
  {
    Object localObject2 = null;
    int j = this.mTuples.size();
    int i = 0;
    Object localObject1 = localObject2;
    if (i < j)
    {
      localObject1 = (Tuple)this.mTuples.get(i);
      if (!StateSet.stateSetMatches(((Tuple)localObject1).mSpecs, paramArrayOfInt)) {}
    }
    else
    {
      if (localObject1 != this.mLastMatch) {
        break label64;
      }
    }
    label64:
    do
    {
      return;
      i += 1;
      break;
      if (this.mLastMatch != null) {
        cancel();
      }
      this.mLastMatch = ((Tuple)localObject1);
      paramArrayOfInt = (View)this.mViewRef.get();
    } while ((localObject1 == null) || (paramArrayOfInt == null) || (paramArrayOfInt.getVisibility() != 0));
    start((Tuple)localObject1);
  }
  
  void setTarget(View paramView)
  {
    View localView = getTarget();
    if (localView == paramView) {}
    do
    {
      return;
      if (localView != null) {
        clearTarget();
      }
    } while (paramView == null);
    this.mViewRef = new WeakReference(paramView);
  }
  
  static class Tuple
  {
    final Animation mAnimation;
    final int[] mSpecs;
    
    private Tuple(int[] paramArrayOfInt, Animation paramAnimation)
    {
      this.mSpecs = paramArrayOfInt;
      this.mAnimation = paramAnimation;
    }
    
    Animation getAnimation()
    {
      return this.mAnimation;
    }
    
    int[] getSpecs()
    {
      return this.mSpecs;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/android/support/design/widget/StateListAnimator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */