package com.google.android.gms.games.internal.multiplayer;

import android.os.Parcel;
import com.google.android.gms.common.internal.a;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationEntity;
import com.google.android.gms.games.multiplayer.Participant;
import java.util.ArrayList;

public final class ZInvitationCluster
  implements SafeParcelable, Invitation
{
  public static final InvitationClusterCreator CREATOR = new InvitationClusterCreator();
  private final int BR;
  private final ArrayList<InvitationEntity> aaL;
  
  ZInvitationCluster(int paramInt, ArrayList<InvitationEntity> paramArrayList)
  {
    this.BR = paramInt;
    this.aaL = paramArrayList;
    li();
  }
  
  private void li()
  {
    if (!this.aaL.isEmpty()) {}
    for (boolean bool = true;; bool = false)
    {
      a.I(bool);
      Invitation localInvitation1 = (Invitation)this.aaL.get(0);
      int j = this.aaL.size();
      int i = 1;
      while (i < j)
      {
        Invitation localInvitation2 = (Invitation)this.aaL.get(i);
        a.a(localInvitation1.getInviter().equals(localInvitation2.getInviter()), "All the invitations must be from the same inviter");
        i += 1;
      }
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ZInvitationCluster)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (ZInvitationCluster)paramObject;
    if (((ZInvitationCluster)paramObject).aaL.size() != this.aaL.size()) {
      return false;
    }
    int j = this.aaL.size();
    int i = 0;
    while (i < j)
    {
      if (!((Invitation)this.aaL.get(i)).equals((Invitation)((ZInvitationCluster)paramObject).aaL.get(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public Invitation freeze()
  {
    return this;
  }
  
  public int getAvailableAutoMatchSlots()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public long getCreationTimestamp()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public Game getGame()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public String getInvitationId()
  {
    return ((InvitationEntity)this.aaL.get(0)).getInvitationId();
  }
  
  public int getInvitationType()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public Participant getInviter()
  {
    return ((InvitationEntity)this.aaL.get(0)).getInviter();
  }
  
  public ArrayList<Participant> getParticipants()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public int getVariant()
  {
    throw new UnsupportedOperationException("Method not supported on a cluster");
  }
  
  public int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return n.hashCode(this.aaL.toArray());
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public ArrayList<Invitation> lj()
  {
    return new ArrayList(this.aaL);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    InvitationClusterCreator.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/games/internal/multiplayer/ZInvitationCluster.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */