package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games.BaseGamesApiMethodImpl;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.game.Acls;
import com.google.android.gms.games.internal.game.Acls.LoadAclResult;

public final class AclsImpl
  implements Acls
{
  private static Acls.LoadAclResult L(Status paramStatus)
  {
    new Acls.LoadAclResult()
    {
      public Status getStatus()
      {
        return this.CW;
      }
      
      public void release() {}
    };
  }
  
  private static abstract class LoadNotifyAclImpl
    extends Games.BaseGamesApiMethodImpl<Acls.LoadAclResult>
  {
    public Acls.LoadAclResult N(Status paramStatus)
    {
      return AclsImpl.M(paramStatus);
    }
  }
  
  private static abstract class UpdateNotifyAclImpl
    extends Games.BaseGamesApiMethodImpl<Status>
  {
    public Status d(Status paramStatus)
    {
      return paramStatus;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/api/AclsImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */