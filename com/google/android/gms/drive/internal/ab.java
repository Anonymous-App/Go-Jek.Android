package com.google.android.gms.drive.internal;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.drive.RealtimeDocumentSyncRequest;

public abstract interface ab
  extends IInterface
{
  public abstract IntentSender a(CreateFileIntentSenderRequest paramCreateFileIntentSenderRequest)
    throws RemoteException;
  
  public abstract IntentSender a(OpenFileIntentSenderRequest paramOpenFileIntentSenderRequest)
    throws RemoteException;
  
  public abstract void a(RealtimeDocumentSyncRequest paramRealtimeDocumentSyncRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(AddEventListenerRequest paramAddEventListenerRequest, ad paramad, String paramString, ac paramac)
    throws RemoteException;
  
  public abstract void a(AuthorizeAccessRequest paramAuthorizeAccessRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(CheckResourceIdsExistRequest paramCheckResourceIdsExistRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(CloseContentsAndUpdateMetadataRequest paramCloseContentsAndUpdateMetadataRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(CloseContentsRequest paramCloseContentsRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(CreateContentsRequest paramCreateContentsRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(CreateFileRequest paramCreateFileRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(CreateFolderRequest paramCreateFolderRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(DeleteResourceRequest paramDeleteResourceRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(DisconnectRequest paramDisconnectRequest)
    throws RemoteException;
  
  public abstract void a(GetDriveIdFromUniqueIdentifierRequest paramGetDriveIdFromUniqueIdentifierRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(GetMetadataRequest paramGetMetadataRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(ListParentsRequest paramListParentsRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(LoadRealtimeRequest paramLoadRealtimeRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(OpenContentsRequest paramOpenContentsRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(QueryRequest paramQueryRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(RemoveEventListenerRequest paramRemoveEventListenerRequest, ad paramad, String paramString, ac paramac)
    throws RemoteException;
  
  public abstract void a(SetDrivePreferencesRequest paramSetDrivePreferencesRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(SetResourceParentsRequest paramSetResourceParentsRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(TrashResourceRequest paramTrashResourceRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(UpdateMetadataRequest paramUpdateMetadataRequest, ac paramac)
    throws RemoteException;
  
  public abstract void a(ac paramac)
    throws RemoteException;
  
  public abstract void b(QueryRequest paramQueryRequest, ac paramac)
    throws RemoteException;
  
  public abstract void b(ac paramac)
    throws RemoteException;
  
  public abstract void c(ac paramac)
    throws RemoteException;
  
  public abstract void d(ac paramac)
    throws RemoteException;
  
  public abstract void e(ac paramac)
    throws RemoteException;
  
  public abstract void f(ac paramac)
    throws RemoteException;
  
  public abstract void g(ac paramac)
    throws RemoteException;
  
  public static abstract class a
    extends Binder
    implements ab
  {
    public static ab U(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.drive.internal.IDriveService");
      if ((localIInterface != null) && ((localIInterface instanceof ab))) {
        return (ab)localIInterface;
      }
      return new a(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      Object localObject2 = null;
      Object localObject3 = null;
      Object localObject4 = null;
      Object localObject5 = null;
      Object localObject6 = null;
      Object localObject7 = null;
      Object localObject8 = null;
      Object localObject9 = null;
      Object localObject10 = null;
      Object localObject11 = null;
      Object localObject12 = null;
      Object localObject13 = null;
      Object localObject14 = null;
      Object localObject15 = null;
      Object localObject16 = null;
      Object localObject17 = null;
      Object localObject18 = null;
      Object localObject19 = null;
      Object localObject20 = null;
      Object localObject21 = null;
      Object localObject22 = null;
      Object localObject23 = null;
      Object localObject24 = null;
      Object localObject25 = null;
      Object localObject1 = null;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.drive.internal.IDriveService");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        if (paramParcel1.readInt() != 0) {
          localObject1 = (GetMetadataRequest)GetMetadataRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((GetMetadataRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject2;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (QueryRequest)QueryRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((QueryRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject3;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (UpdateMetadataRequest)UpdateMetadataRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((UpdateMetadataRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject4;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CreateContentsRequest)CreateContentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((CreateContentsRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject5;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CreateFileRequest)CreateFileRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((CreateFileRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject6;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CreateFolderRequest)CreateFolderRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((CreateFolderRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 7: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject7;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (OpenContentsRequest)OpenContentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((OpenContentsRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 8: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject8;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CloseContentsRequest)CloseContentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((CloseContentsRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 9: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        a(ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 10: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject9;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (OpenFileIntentSenderRequest)OpenFileIntentSenderRequest.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = a((OpenFileIntentSenderRequest)localObject1);
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          return true;
          paramParcel2.writeInt(0);
        }
      case 11: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject10;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CreateFileIntentSenderRequest)CreateFileIntentSenderRequest.CREATOR.createFromParcel(paramParcel1);
        }
        paramParcel1 = a((CreateFileIntentSenderRequest)localObject1);
        paramParcel2.writeNoException();
        if (paramParcel1 != null)
        {
          paramParcel2.writeInt(1);
          paramParcel1.writeToParcel(paramParcel2, 1);
        }
        for (;;)
        {
          return true;
          paramParcel2.writeInt(0);
        }
      case 12: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject11;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (AuthorizeAccessRequest)AuthorizeAccessRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((AuthorizeAccessRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 13: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject12;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (ListParentsRequest)ListParentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((ListParentsRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 14: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject13;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (AddEventListenerRequest)AddEventListenerRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((AddEventListenerRequest)localObject1, ad.a.W(paramParcel1.readStrongBinder()), paramParcel1.readString(), ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 15: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject14;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (RemoveEventListenerRequest)RemoveEventListenerRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((RemoveEventListenerRequest)localObject1, ad.a.W(paramParcel1.readStrongBinder()), paramParcel1.readString(), ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 16: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject15;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (DisconnectRequest)DisconnectRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((DisconnectRequest)localObject1);
        paramParcel2.writeNoException();
        return true;
      case 17: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject16;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (TrashResourceRequest)TrashResourceRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((TrashResourceRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 18: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject17;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CloseContentsAndUpdateMetadataRequest)CloseContentsAndUpdateMetadataRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((CloseContentsAndUpdateMetadataRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 19: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject18;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (QueryRequest)QueryRequest.CREATOR.createFromParcel(paramParcel1);
        }
        b((QueryRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 20: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        b(ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 21: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        c(ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 22: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        d(ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 23: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        e(ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 24: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject19;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (DeleteResourceRequest)DeleteResourceRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((DeleteResourceRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 27: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject20;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (LoadRealtimeRequest)LoadRealtimeRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((LoadRealtimeRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 28: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject21;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (SetResourceParentsRequest)SetResourceParentsRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((SetResourceParentsRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 29: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject22;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (GetDriveIdFromUniqueIdentifierRequest)GetDriveIdFromUniqueIdentifierRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((GetDriveIdFromUniqueIdentifierRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 30: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject23;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (CheckResourceIdsExistRequest)CheckResourceIdsExistRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((CheckResourceIdsExistRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 31: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        f(ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 32: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        g(ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 33: 
        paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
        localObject1 = localObject24;
        if (paramParcel1.readInt() != 0) {
          localObject1 = (SetDrivePreferencesRequest)SetDrivePreferencesRequest.CREATOR.createFromParcel(paramParcel1);
        }
        a((SetDrivePreferencesRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.drive.internal.IDriveService");
      localObject1 = localObject25;
      if (paramParcel1.readInt() != 0) {
        localObject1 = (RealtimeDocumentSyncRequest)RealtimeDocumentSyncRequest.CREATOR.createFromParcel(paramParcel1);
      }
      a((RealtimeDocumentSyncRequest)localObject1, ac.a.V(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class a
      implements ab
    {
      private IBinder lb;
      
      a(IBinder paramIBinder)
      {
        this.lb = paramIBinder;
      }
      
      public IntentSender a(CreateFileIntentSenderRequest paramCreateFileIntentSenderRequest)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateFileIntentSenderRequest != null)
            {
              localParcel1.writeInt(1);
              paramCreateFileIntentSenderRequest.writeToParcel(localParcel1, 0);
              this.lb.transact(11, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramCreateFileIntentSenderRequest = (IntentSender)IntentSender.CREATOR.createFromParcel(localParcel2);
                return paramCreateFileIntentSenderRequest;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCreateFileIntentSenderRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public IntentSender a(OpenFileIntentSenderRequest paramOpenFileIntentSenderRequest)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramOpenFileIntentSenderRequest != null)
            {
              localParcel1.writeInt(1);
              paramOpenFileIntentSenderRequest.writeToParcel(localParcel1, 0);
              this.lb.transact(10, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramOpenFileIntentSenderRequest = (IntentSender)IntentSender.CREATOR.createFromParcel(localParcel2);
                return paramOpenFileIntentSenderRequest;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramOpenFileIntentSenderRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(RealtimeDocumentSyncRequest paramRealtimeDocumentSyncRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramRealtimeDocumentSyncRequest != null)
            {
              localParcel1.writeInt(1);
              paramRealtimeDocumentSyncRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramRealtimeDocumentSyncRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramRealtimeDocumentSyncRequest);
                this.lb.transact(34, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramRealtimeDocumentSyncRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(AddEventListenerRequest paramAddEventListenerRequest, ad paramad, String paramString, ac paramac)
        throws RemoteException
      {
        Object localObject = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramAddEventListenerRequest != null)
            {
              localParcel1.writeInt(1);
              paramAddEventListenerRequest.writeToParcel(localParcel1, 0);
              if (paramad != null)
              {
                paramAddEventListenerRequest = paramad.asBinder();
                localParcel1.writeStrongBinder(paramAddEventListenerRequest);
                localParcel1.writeString(paramString);
                paramAddEventListenerRequest = (AddEventListenerRequest)localObject;
                if (paramac != null) {
                  paramAddEventListenerRequest = paramac.asBinder();
                }
                localParcel1.writeStrongBinder(paramAddEventListenerRequest);
                this.lb.transact(14, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramAddEventListenerRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(AuthorizeAccessRequest paramAuthorizeAccessRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramAuthorizeAccessRequest != null)
            {
              localParcel1.writeInt(1);
              paramAuthorizeAccessRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramAuthorizeAccessRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramAuthorizeAccessRequest);
                this.lb.transact(12, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramAuthorizeAccessRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(CheckResourceIdsExistRequest paramCheckResourceIdsExistRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCheckResourceIdsExistRequest != null)
            {
              localParcel1.writeInt(1);
              paramCheckResourceIdsExistRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramCheckResourceIdsExistRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramCheckResourceIdsExistRequest);
                this.lb.transact(30, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCheckResourceIdsExistRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(CloseContentsAndUpdateMetadataRequest paramCloseContentsAndUpdateMetadataRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCloseContentsAndUpdateMetadataRequest != null)
            {
              localParcel1.writeInt(1);
              paramCloseContentsAndUpdateMetadataRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramCloseContentsAndUpdateMetadataRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramCloseContentsAndUpdateMetadataRequest);
                this.lb.transact(18, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCloseContentsAndUpdateMetadataRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(CloseContentsRequest paramCloseContentsRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCloseContentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramCloseContentsRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramCloseContentsRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramCloseContentsRequest);
                this.lb.transact(8, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCloseContentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(CreateContentsRequest paramCreateContentsRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateContentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramCreateContentsRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramCreateContentsRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramCreateContentsRequest);
                this.lb.transact(4, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCreateContentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(CreateFileRequest paramCreateFileRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateFileRequest != null)
            {
              localParcel1.writeInt(1);
              paramCreateFileRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramCreateFileRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramCreateFileRequest);
                this.lb.transact(5, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCreateFileRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(CreateFolderRequest paramCreateFolderRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramCreateFolderRequest != null)
            {
              localParcel1.writeInt(1);
              paramCreateFolderRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramCreateFolderRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramCreateFolderRequest);
                this.lb.transact(6, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramCreateFolderRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(DeleteResourceRequest paramDeleteResourceRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramDeleteResourceRequest != null)
            {
              localParcel1.writeInt(1);
              paramDeleteResourceRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramDeleteResourceRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramDeleteResourceRequest);
                this.lb.transact(24, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramDeleteResourceRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void a(DisconnectRequest paramDisconnectRequest)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 136	com/google/android/gms/drive/internal/DisconnectRequest:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/drive/internal/ab$a$a:lb	Landroid/os/IBinder;
        //   33: bipush 16
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 50 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 53	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 72	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 72	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aload_2
        //   58: iconst_0
        //   59: invokevirtual 38	android/os/Parcel:writeInt	(I)V
        //   62: goto -33 -> 29
        //   65: astore_1
        //   66: aload_3
        //   67: invokevirtual 72	android/os/Parcel:recycle	()V
        //   70: aload_2
        //   71: invokevirtual 72	android/os/Parcel:recycle	()V
        //   74: aload_1
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	a
        //   0	76	1	paramDisconnectRequest	DisconnectRequest
        //   3	68	2	localParcel1	Parcel
        //   7	60	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	65	finally
        //   18	29	65	finally
        //   29	48	65	finally
        //   57	62	65	finally
      }
      
      public void a(GetDriveIdFromUniqueIdentifierRequest paramGetDriveIdFromUniqueIdentifierRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramGetDriveIdFromUniqueIdentifierRequest != null)
            {
              localParcel1.writeInt(1);
              paramGetDriveIdFromUniqueIdentifierRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramGetDriveIdFromUniqueIdentifierRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramGetDriveIdFromUniqueIdentifierRequest);
                this.lb.transact(29, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramGetDriveIdFromUniqueIdentifierRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(GetMetadataRequest paramGetMetadataRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramGetMetadataRequest != null)
            {
              localParcel1.writeInt(1);
              paramGetMetadataRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramGetMetadataRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramGetMetadataRequest);
                this.lb.transact(1, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramGetMetadataRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(ListParentsRequest paramListParentsRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramListParentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramListParentsRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramListParentsRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramListParentsRequest);
                this.lb.transact(13, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramListParentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(LoadRealtimeRequest paramLoadRealtimeRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramLoadRealtimeRequest != null)
            {
              localParcel1.writeInt(1);
              paramLoadRealtimeRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramLoadRealtimeRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramLoadRealtimeRequest);
                this.lb.transact(27, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramLoadRealtimeRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(OpenContentsRequest paramOpenContentsRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramOpenContentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramOpenContentsRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramOpenContentsRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramOpenContentsRequest);
                this.lb.transact(7, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramOpenContentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(QueryRequest paramQueryRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramQueryRequest != null)
            {
              localParcel1.writeInt(1);
              paramQueryRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramQueryRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramQueryRequest);
                this.lb.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramQueryRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(RemoveEventListenerRequest paramRemoveEventListenerRequest, ad paramad, String paramString, ac paramac)
        throws RemoteException
      {
        Object localObject = null;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramRemoveEventListenerRequest != null)
            {
              localParcel1.writeInt(1);
              paramRemoveEventListenerRequest.writeToParcel(localParcel1, 0);
              if (paramad != null)
              {
                paramRemoveEventListenerRequest = paramad.asBinder();
                localParcel1.writeStrongBinder(paramRemoveEventListenerRequest);
                localParcel1.writeString(paramString);
                paramRemoveEventListenerRequest = (RemoveEventListenerRequest)localObject;
                if (paramac != null) {
                  paramRemoveEventListenerRequest = paramac.asBinder();
                }
                localParcel1.writeStrongBinder(paramRemoveEventListenerRequest);
                this.lb.transact(15, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramRemoveEventListenerRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(SetDrivePreferencesRequest paramSetDrivePreferencesRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramSetDrivePreferencesRequest != null)
            {
              localParcel1.writeInt(1);
              paramSetDrivePreferencesRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramSetDrivePreferencesRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramSetDrivePreferencesRequest);
                this.lb.transact(33, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramSetDrivePreferencesRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(SetResourceParentsRequest paramSetResourceParentsRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramSetResourceParentsRequest != null)
            {
              localParcel1.writeInt(1);
              paramSetResourceParentsRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramSetResourceParentsRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramSetResourceParentsRequest);
                this.lb.transact(28, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramSetResourceParentsRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(TrashResourceRequest paramTrashResourceRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramTrashResourceRequest != null)
            {
              localParcel1.writeInt(1);
              paramTrashResourceRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramTrashResourceRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramTrashResourceRequest);
                this.lb.transact(17, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramTrashResourceRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      public void a(UpdateMetadataRequest paramUpdateMetadataRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramUpdateMetadataRequest != null)
            {
              localParcel1.writeInt(1);
              paramUpdateMetadataRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramUpdateMetadataRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramUpdateMetadataRequest);
                this.lb.transact(3, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramUpdateMetadataRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void a(ac paramac)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/ab$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 9
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 50 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 53	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 72	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 72	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 72	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 72	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramac	ac
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      public IBinder asBinder()
      {
        return this.lb;
      }
      
      public void b(QueryRequest paramQueryRequest, ac paramac)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.drive.internal.IDriveService");
            if (paramQueryRequest != null)
            {
              localParcel1.writeInt(1);
              paramQueryRequest.writeToParcel(localParcel1, 0);
              if (paramac != null)
              {
                paramQueryRequest = paramac.asBinder();
                localParcel1.writeStrongBinder(paramQueryRequest);
                this.lb.transact(19, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramQueryRequest = null;
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void b(ac paramac)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/ab$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 20
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 50 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 53	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 72	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 72	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 72	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 72	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramac	ac
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void c(ac paramac)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/ab$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 21
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 50 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 53	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 72	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 72	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 72	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 72	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramac	ac
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void d(ac paramac)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/ab$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 22
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 50 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 53	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 72	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 72	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 72	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 72	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramac	ac
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void e(ac paramac)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/ab$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 23
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 50 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 53	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 72	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 72	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 72	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 72	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramac	ac
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void f(ac paramac)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/ab$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 31
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 50 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 53	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 72	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 72	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 72	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 72	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramac	ac
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
      
      /* Error */
      public void g(ac paramac)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 30
        //   11: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +43 -> 58
        //   18: aload_1
        //   19: invokeinterface 87 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 90	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 18	com/google/android/gms/drive/internal/ab$a$a:lb	Landroid/os/IBinder;
        //   34: bipush 32
        //   36: aload_2
        //   37: aload_3
        //   38: iconst_0
        //   39: invokeinterface 50 5 0
        //   44: pop
        //   45: aload_3
        //   46: invokevirtual 53	android/os/Parcel:readException	()V
        //   49: aload_3
        //   50: invokevirtual 72	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 72	android/os/Parcel:recycle	()V
        //   57: return
        //   58: aconst_null
        //   59: astore_1
        //   60: goto -35 -> 25
        //   63: astore_1
        //   64: aload_3
        //   65: invokevirtual 72	android/os/Parcel:recycle	()V
        //   68: aload_2
        //   69: invokevirtual 72	android/os/Parcel:recycle	()V
        //   72: aload_1
        //   73: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	74	0	this	a
        //   0	74	1	paramac	ac
        //   3	66	2	localParcel1	Parcel
        //   7	58	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	63	finally
        //   18	25	63	finally
        //   25	49	63	finally
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/internal/ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */