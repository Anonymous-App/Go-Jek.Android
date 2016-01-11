package com.gojek.gobox.util;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.gojek.gobox.model.TokenResponse;
import com.gojek.gobox.networking.NetworkService;
import rx.Observable;

public class GoBoxAuthUtil
{
  private boolean mBound;
  private ServiceConnection mConnection = new GoBoxAuthUtil.1(this);
  private Context mContext;
  private GoBoxAuthListener mGoBoxAuthListener;
  private GoBoxPreferences mGoBoxPreferences;
  private NetworkService mNetworkService;
  private boolean mRefreshingToken;
  private TokenResponse mTokenResponse;
  
  public GoBoxAuthUtil(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  private void startSaveAuthentication()
  {
    this.mNetworkService.getSaveAccessTokenObservable(this.mTokenResponse).map(GoBoxAuthUtil..Lambda.1.lambdaFactory$()).flatMap(GoBoxAuthUtil..Lambda.2.lambdaFactory$(this)).subscribe(new GoBoxAuthUtil.2(this));
  }
  
  public void saveGoBoxAuthentication(String paramString1, String paramString2, long paramLong, boolean paramBoolean, GoBoxAuthListener paramGoBoxAuthListener)
  {
    this.mTokenResponse = new TokenResponse(paramString1, paramString2, paramLong);
    this.mRefreshingToken = paramBoolean;
    this.mGoBoxAuthListener = paramGoBoxAuthListener;
    this.mGoBoxPreferences = new GoBoxPreferences(this.mContext);
    paramString1 = new Intent(this.mContext, NetworkService.class);
    this.mContext.bindService(paramString1, this.mConnection, 1);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/util/GoBoxAuthUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */