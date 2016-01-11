package com.google.android.gms.plus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.o;
import com.google.android.gms.internal.nz;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.plus.model.people.Person;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class PlusShare
{
  public static final String EXTRA_CALL_TO_ACTION = "com.google.android.apps.plus.CALL_TO_ACTION";
  public static final String EXTRA_CONTENT_DEEP_LINK_ID = "com.google.android.apps.plus.CONTENT_DEEP_LINK_ID";
  public static final String EXTRA_CONTENT_DEEP_LINK_METADATA = "com.google.android.apps.plus.CONTENT_DEEP_LINK_METADATA";
  public static final String EXTRA_CONTENT_URL = "com.google.android.apps.plus.CONTENT_URL";
  public static final String EXTRA_IS_INTERACTIVE_POST = "com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST";
  public static final String EXTRA_SENDER_ID = "com.google.android.apps.plus.SENDER_ID";
  public static final String KEY_CALL_TO_ACTION_DEEP_LINK_ID = "deepLinkId";
  public static final String KEY_CALL_TO_ACTION_LABEL = "label";
  public static final String KEY_CALL_TO_ACTION_URL = "url";
  public static final String KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION = "description";
  public static final String KEY_CONTENT_DEEP_LINK_METADATA_THUMBNAIL_URL = "thumbnailUrl";
  public static final String KEY_CONTENT_DEEP_LINK_METADATA_TITLE = "title";
  public static final String PARAM_CONTENT_DEEP_LINK_ID = "deep_link_id";
  
  @Deprecated
  protected PlusShare()
  {
    throw new AssertionError();
  }
  
  public static Bundle a(String paramString1, String paramString2, Uri paramUri)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("title", paramString1);
    localBundle.putString("description", paramString2);
    if (paramUri != null) {
      localBundle.putString("thumbnailUrl", paramUri.toString());
    }
    return localBundle;
  }
  
  protected static boolean cd(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      Log.e("GooglePlusPlatform", "The provided deep-link ID is empty.");
      return false;
    }
    if (paramString.contains(" "))
    {
      Log.e("GooglePlusPlatform", "Spaces are not allowed in deep-link IDs.");
      return false;
    }
    return true;
  }
  
  public static Person createPerson(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      throw new IllegalArgumentException("MinimalPerson ID must not be empty.");
    }
    if (TextUtils.isEmpty(paramString2)) {
      throw new IllegalArgumentException("Display name must not be empty.");
    }
    return new nz(paramString2, paramString1, null, 0, null);
  }
  
  public static String getDeepLinkId(Intent paramIntent)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramIntent != null)
    {
      localObject1 = localObject2;
      if (paramIntent.getData() != null) {
        localObject1 = paramIntent.getData().getQueryParameter("deep_link_id");
      }
    }
    return (String)localObject1;
  }
  
  public static class Builder
  {
    private boolean alr;
    private ArrayList<Uri> als;
    private final Context mContext;
    private final Intent mIntent;
    
    public Builder(Activity paramActivity)
    {
      this.mContext = paramActivity;
      this.mIntent = new Intent().setAction("android.intent.action.SEND");
      this.mIntent.addFlags(524288);
      if ((paramActivity != null) && (paramActivity.getComponentName() != null)) {
        this.alr = true;
      }
    }
    
    @Deprecated
    public Builder(Activity paramActivity, PlusClient paramPlusClient)
    {
      this(paramActivity);
      boolean bool;
      if (paramPlusClient != null)
      {
        bool = true;
        o.a(bool, "PlusClient must not be null.");
        o.a(paramPlusClient.isConnected(), "PlusClient must be connected to create an interactive post.");
        o.a(paramPlusClient.mZ().cg("https://www.googleapis.com/auth/plus.login"), "Must request PLUS_LOGIN scope in PlusClient to create an interactive post.");
        paramActivity = paramPlusClient.getCurrentPerson();
        if (paramActivity == null) {
          break label73;
        }
      }
      label73:
      for (paramActivity = paramActivity.getId();; paramActivity = "0")
      {
        this.mIntent.putExtra("com.google.android.apps.plus.SENDER_ID", paramActivity);
        return;
        bool = false;
        break;
      }
    }
    
    public Builder(Context paramContext)
    {
      this.mContext = paramContext;
      this.mIntent = new Intent().setAction("android.intent.action.SEND");
    }
    
    public Builder addCallToAction(String paramString1, Uri paramUri, String paramString2)
    {
      o.a(this.alr, "Must include the launching activity with PlusShare.Builder constructor before setting call-to-action");
      if ((paramUri != null) && (!TextUtils.isEmpty(paramUri.toString()))) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "Must provide a call to action URL");
        Bundle localBundle = new Bundle();
        if (!TextUtils.isEmpty(paramString1)) {
          localBundle.putString("label", paramString1);
        }
        localBundle.putString("url", paramUri.toString());
        if (!TextUtils.isEmpty(paramString2))
        {
          o.a(PlusShare.cd(paramString2), "The specified deep-link ID was malformed.");
          localBundle.putString("deepLinkId", paramString2);
        }
        this.mIntent.putExtra("com.google.android.apps.plus.CALL_TO_ACTION", localBundle);
        this.mIntent.putExtra("com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST", true);
        this.mIntent.setType("text/plain");
        return this;
      }
    }
    
    public Builder addStream(Uri paramUri)
    {
      Uri localUri = (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
      if (localUri == null) {
        return setStream(paramUri);
      }
      if (this.als == null) {
        this.als = new ArrayList();
      }
      this.als.add(localUri);
      this.als.add(paramUri);
      return this;
    }
    
    public Intent getIntent()
    {
      boolean bool2 = true;
      int i;
      boolean bool1;
      if ((this.als != null) && (this.als.size() > 1))
      {
        i = 1;
        boolean bool3 = "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
        boolean bool4 = this.mIntent.getBooleanExtra("com.google.android.apps.plus.GOOGLE_INTERACTIVE_POST", false);
        if ((i != 0) && (bool4)) {
          break label307;
        }
        bool1 = true;
        label59:
        o.a(bool1, "Call-to-action buttons are only available for URLs.");
        if ((bool4) && (!this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_URL"))) {
          break label312;
        }
        bool1 = true;
        label84:
        o.a(bool1, "The content URL is required for interactive posts.");
        bool1 = bool2;
        if (bool4)
        {
          bool1 = bool2;
          if (!this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_URL"))
          {
            if (!this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")) {
              break label317;
            }
            bool1 = bool2;
          }
        }
        label125:
        o.a(bool1, "Must set content URL or content deep-link ID to use a call-to-action button.");
        if (this.mIntent.hasExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")) {
          o.a(PlusShare.cd(this.mIntent.getStringExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID")), "The specified deep-link ID was malformed.");
        }
        if ((i == 0) && (bool3))
        {
          this.mIntent.setAction("android.intent.action.SEND");
          if ((this.als == null) || (this.als.isEmpty())) {
            break label322;
          }
          this.mIntent.putExtra("android.intent.extra.STREAM", (Parcelable)this.als.get(0));
          label217:
          this.als = null;
        }
        if ((i != 0) && (!bool3))
        {
          this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
          if ((this.als == null) || (this.als.isEmpty())) {
            break label334;
          }
          this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.als);
        }
      }
      for (;;)
      {
        if (!"com.google.android.gms.plus.action.SHARE_INTERNAL_GOOGLE".equals(this.mIntent.getAction())) {
          break label346;
        }
        this.mIntent.setPackage("com.google.android.gms");
        return this.mIntent;
        i = 0;
        break;
        label307:
        bool1 = false;
        break label59;
        label312:
        bool1 = false;
        break label84;
        label317:
        bool1 = false;
        break label125;
        label322:
        this.mIntent.removeExtra("android.intent.extra.STREAM");
        break label217;
        label334:
        this.mIntent.removeExtra("android.intent.extra.STREAM");
      }
      label346:
      if (!this.mIntent.hasExtra("android.intent.extra.STREAM"))
      {
        this.mIntent.setAction("com.google.android.gms.plus.action.SHARE_GOOGLE");
        this.mIntent.setPackage("com.google.android.gms");
        return this.mIntent;
      }
      this.mIntent.setPackage("com.google.android.apps.plus");
      return this.mIntent;
    }
    
    public Builder setContentDeepLinkId(String paramString)
    {
      return setContentDeepLinkId(paramString, null, null, null);
    }
    
    public Builder setContentDeepLinkId(String paramString1, String paramString2, String paramString3, Uri paramUri)
    {
      o.b(this.alr, "Must include the launching activity with PlusShare.Builder constructor before setting deep links");
      if (!TextUtils.isEmpty(paramString1)) {}
      for (boolean bool = true;; bool = false)
      {
        o.b(bool, "The deepLinkId parameter is required.");
        paramString2 = PlusShare.a(paramString2, paramString3, paramUri);
        this.mIntent.putExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_ID", paramString1);
        this.mIntent.putExtra("com.google.android.apps.plus.CONTENT_DEEP_LINK_METADATA", paramString2);
        this.mIntent.setType("text/plain");
        return this;
      }
    }
    
    public Builder setContentUrl(Uri paramUri)
    {
      String str = null;
      if (paramUri != null) {
        str = paramUri.toString();
      }
      if (TextUtils.isEmpty(str))
      {
        this.mIntent.removeExtra("com.google.android.apps.plus.CONTENT_URL");
        return this;
      }
      this.mIntent.putExtra("com.google.android.apps.plus.CONTENT_URL", str);
      return this;
    }
    
    public Builder setRecipients(Person paramPerson, List<Person> paramList)
    {
      Intent localIntent = this.mIntent;
      if (paramPerson != null) {}
      for (paramPerson = paramPerson.getId();; paramPerson = "0")
      {
        localIntent.putExtra("com.google.android.apps.plus.SENDER_ID", paramPerson);
        return setRecipients(paramList);
      }
    }
    
    @Deprecated
    public Builder setRecipients(List<Person> paramList)
    {
      if (paramList != null) {}
      for (int i = paramList.size(); i == 0; i = 0)
      {
        this.mIntent.removeExtra("com.google.android.apps.plus.RECIPIENT_IDS");
        this.mIntent.removeExtra("com.google.android.apps.plus.RECIPIENT_DISPLAY_NAMES");
        return this;
      }
      ArrayList localArrayList1 = new ArrayList(i);
      ArrayList localArrayList2 = new ArrayList(i);
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Person localPerson = (Person)paramList.next();
        localArrayList1.add(localPerson.getId());
        localArrayList2.add(localPerson.getDisplayName());
      }
      this.mIntent.putStringArrayListExtra("com.google.android.apps.plus.RECIPIENT_IDS", localArrayList1);
      this.mIntent.putStringArrayListExtra("com.google.android.apps.plus.RECIPIENT_DISPLAY_NAMES", localArrayList2);
      return this;
    }
    
    public Builder setStream(Uri paramUri)
    {
      this.als = null;
      this.mIntent.putExtra("android.intent.extra.STREAM", paramUri);
      return this;
    }
    
    public Builder setText(CharSequence paramCharSequence)
    {
      this.mIntent.putExtra("android.intent.extra.TEXT", paramCharSequence);
      return this;
    }
    
    public Builder setType(String paramString)
    {
      this.mIntent.setType(paramString);
      return this;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/plus/PlusShare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */