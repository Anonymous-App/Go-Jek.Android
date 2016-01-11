package com.nostratech.gojek.driver.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog.Builder;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.kartuku.directclient.model.CardToken;
import com.kartuku.directclient.model.response.TokenListResponse;
import com.kartuku.directclient.model.response.TokenRemoveResponse;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.instrumentation.Instrumented;
import com.newrelic.agent.android.tracing.TraceMachine;
import com.nostratech.gojek.driver.R.drawable;
import com.nostratech.gojek.driver.R.id;
import com.nostratech.gojek.driver.R.layout;
import com.nostratech.gojek.driver.adapter.CardListAdapter;
import com.nostratech.gojek.driver.common.EncryptedRequestDto;
import com.nostratech.gojek.driver.common.directclient.AuthorizeNotifyRequest;
import com.nostratech.gojek.driver.toast.ToastWrapper;
import com.nostratech.gojek.driver.utils.FontManager;
import com.nostratech.gojek.driver.utils.Luhn;
import com.nostratech.gojek.driver.utils.TextViewManager;
import io.card.payment.CreditCard;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Instrumented
public class FragmentPaymentInfo
  extends Fragment
  implements TraceFieldInterface
{
  private static final byte[] ᐣ = { 75, 29, -1, 54, 30, 13, -11, 2, 7, 51, -8, -7, 9, -13, -69, 36, 29, 19, -15, -43, -26, 28, -7, -4, 18, -14, -69, 70, 3, 3, 0, -76, 86, -21, 11, -3, -5, -68, 57, 0, -57, 70, 3, -4, 7, -8, 28, -7, -4, 18, -14, -69, 70, 3, 3, 0, -76, 45, 0, -45, 70, 3, -4, 7, -8, -14, -2, 18, -3, -9, 11, 5, -2, -17, 13, 5, -18, 2, 17, -11, 6, -1, -65, 69, -13, 1, -2, 11, 0, -10, -1, 2, 8, -62, 65, 1, 5, -11, -3, -57, 72, -3, -6, 28, -7, -4, 18, -14, -69, 67, 5, 7, 0, 4, -14, -69, 80, -15, 24, -12, -8, 9, 6, -84, 77, -8, 15, -12, 7, -11, -8, 13, -15, 5, -7, 13, 6, -71, 72, -2, -14, 13, -69, 60, -5, 30, 17, -14, -54, 27, 6, -4, 47, -13, -1, 5, 11, -84, 35, 30, 17, -14, -68, 46, 39, -8, -11, 3, 13, -82, 77, 8, -2, 1, -84, 66, 3, -69, 65, -65, 86, -21, 11, -3, -5, -68, 54, 19, 10, -18, -65, 79, 3, -82, 45, 20, 18, 1, -15, 13, -82, 35, 30, 17, -14, 17, 24, -12, -8, 9, 6, -33, 18, 13, 4, -13, -6, 2, 28, -7, -4, 18, -14, -69, 70, 3, 3, 0, -76, 65, -65, 86, -21, 11, -3, -5, -68, 69, 19, -8, -7, 9, 7, -89, 68, -3, 19, -15, -69, 70, 3, -4, 7, -8, 31, 2, -3, -11, 17, -18, 17, -71, 71, 5, -9, -11, 12, 2, 6, -7, 6, 28, -7, -4, 18, -14, -69, 70, 3, 3, 0, -76, 57, 0, -57, 70, 3, -4, 7, -8, 28, -7, -4, 18, -14, -69, 70, 3, 3, 0, -76, 84, -12, -3, -69, 35, 19, 0, -54, 84, -5, -79, 67, 12, -1, 6, -11, 5, 7, -16, 30, 17, -14, -68, 46, 39, -8, -11, 3, 13, -56, -26, -2, -14, 13, -69, 60, -5, 43, -79, 89, -10, 6, -85, 87, -22, 13, 6, -84, 84, -5, -79, 82, -13, 8, 2, 7, -17, -69, 67, 15, -13, -1, 5, 11, -84, 67, -2, 17, -14, -68, 28, -7, -4, 18, -14, -69, 70, 3, 3, 0, -76, 86, -21, 11, -3, -5, -68, 45, 0, -45, 70, 3, -4, 7, -8, 22, 6, -3, -82, 67, -2, 17, -14, -68, 72, -7, 18, -83, 66, 3, 0, 9, -78, 82, -13, 8, -8, 17, -17, -1, 44, -17, 6, 6, -8, 9, 6, -36, 17, 24, -12, -8, 9, 6, 16, -2, 13, -78, 87, -22, 18, -83, 67, -2, 13, -11, 2, 7, -7, -1, -54, 47, -13, -1, 5, 11, -84, 35, 30, 17, -14, -68, 37, 51, -8, -7, 9, 7, -89, 36, 29, 19, -15, -69, 77, 8, -2, 1, -84, 66, 3, -69, 65, -65, 86, -21, 11, -3, -5, -68, 68, -3, 19, -15, -69, 70, 9, 3, -5, -12, 19, -84, 8, 37, 0, -45, 15, -15, 57, 0, -48, 6, -65, 53, -2, 17, -14, -54, 66, -15, 24, -12, -8, 9, 6, -70, 69, -16, -2, 13, -28, 19, 14, 2, -9, 8, 28, -7, -4, 18, -14, -69, 70, 3, 3, 0, -76, 86, -21, 11, -3, -5, -68, 35, 19, 0, -54, 78, 7, -8, -11, 3, 13 };
  private static int ᐩ = 38;
  public TokenListResponse cardList;
  protected FragmentActivity mActivity;
  private int ʳ = 1235;
  private CreditCard ʴ;
  private ArrayList<CardToken> ʹ = new ArrayList();
  private EditText ʻ;
  private EditText ʼ;
  private PaymentService ʽ;
  private Boolean ʾ = Boolean.valueOf(false);
  private Button ʿ;
  private FragmentPaymentInfo.1 ˆ = new FragmentPaymentInfo.1(this);
  private FragmentPaymentInfo.11 ˇ = new FragmentPaymentInfo.11(this);
  private TextView ˈ;
  private ImageButton ˉ;
  private EditText ˊ;
  private EditText ˋ;
  private ImageView ˌ;
  private LinearLayout ˍ;
  private EditText ˎ;
  private EditText ˏ;
  private LinearLayout ˑ;
  private FragmentPaymentInfo.12 ˡ = new FragmentPaymentInfo.12(this);
  private FragmentPaymentInfo.13 ˮ = new FragmentPaymentInfo.13(this);
  private Boolean ͺ;
  private ListView ՙ;
  private CardListAdapter י;
  private LinearLayout ـ;
  private EditText ٴ;
  private FragmentPaymentInfo.14 ۥ = new FragmentPaymentInfo.14(this);
  private EditText ᐝ;
  private FragmentPaymentInfo.15 ᐠ = new FragmentPaymentInfo.15(this);
  private ProgressBar ᐧ;
  private String ᐨ;
  private RelativeLayout ᴵ;
  private int ᵎ = 1;
  private ProgressBar ᵔ;
  private Boolean ᵢ = Boolean.valueOf(false);
  private Boolean ι = Boolean.valueOf(false);
  private LinearLayout ⁱ;
  private String ﹳ;
  private CardToken ﹶ;
  private View ﹺ;
  private boolean ｰ = true;
  private TextView ﾞ;
  
  private String ʻ()
  {
    String str = null;
    Bundle localBundle = getArguments();
    if (localBundle != null) {
      str = localBundle.getString(ˊ((byte)ᐣ[85], (byte)ᐣ['£'], (short)465));
    }
    return str;
  }
  
  private String ʼ()
  {
    String str = null;
    Bundle localBundle = getArguments();
    if (localBundle != null) {
      str = localBundle.getString(ˊ((byte)ᐣ[24], (byte)ᐣ['Œ'], (short)451));
    }
    return str;
  }
  
  private int ˊ(ListView paramListView)
  {
    paramListView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    return paramListView.getMeasuredHeight() * this.י.getCount() + this.י.getCount() * paramListView.getDividerHeight();
  }
  
  private static String ˊ(int paramInt, byte paramByte, short paramShort)
  {
    byte[] arrayOfByte1 = ᐣ;
    int i = 61 - paramByte;
    byte[] arrayOfByte2 = new byte[i];
    paramShort = 536 - paramShort;
    paramInt = 117 - paramInt;
    byte b;
    for (paramByte = 0;; paramByte = b)
    {
      b = paramByte + 1;
      arrayOfByte2[paramByte] = ((byte)paramInt);
      if (b == i) {
        return new String(arrayOfByte2, 0).intern();
      }
      paramShort += 1;
      paramInt = arrayOfByte1[paramShort] + paramInt;
    }
  }
  
  private void ˊ()
  {
    int i = 0;
    while (i < this.cardList.getCardTokens().size())
    {
      CardToken localCardToken = new CardToken();
      localCardToken.setCardToken(((CardToken)this.cardList.getCardTokens().get(i)).getCardToken());
      localCardToken.setCardNumberMasked(((CardToken)this.cardList.getCardTokens().get(i)).getCardNumberMasked());
      localCardToken.setCardExpiry(((CardToken)this.cardList.getCardTokens().get(i)).getCardExpiry());
      this.ʹ.add(localCardToken);
      i += 1;
    }
  }
  
  private void ˋ()
  {
    this.ﹺ.findViewById(R.id.layout_card).setVisibility(8);
    this.ˉ.setBackgroundResource(R.drawable.icon_plus);
    ˎ();
    this.ｰ = true;
  }
  
  private void ˎ()
  {
    this.ˊ.setText("");
    this.ˋ.setText("");
    this.ˎ.setText("");
    this.ˏ.setText("");
    this.ᐝ.setText("");
    this.ʻ.setText("");
    this.ʼ.setText("");
  }
  
  private int ˏ()
  {
    int i = 0;
    Bundle localBundle = getArguments();
    if (localBundle != null)
    {
      i = (byte)ᐣ['Ã'];
      i = Integer.parseInt(localBundle.getString(ˊ(i, (byte)(i | 0x23), (short)271)));
    }
    return i;
  }
  
  private String ᐝ()
  {
    String str = null;
    Bundle localBundle = getArguments();
    if (localBundle != null) {
      str = localBundle.getString(ˊ((byte)ᐣ[71], (byte)49, (short)282));
    }
    return str;
  }
  
  public boolean formValidator()
  {
    Boolean localBoolean = Boolean.valueOf(true);
    new Time(Time.getCurrentTimezone()).setToNow();
    Object localObject;
    if ((this.ᵢ.booleanValue()) && (!this.ʾ.booleanValue()))
    {
      localObject = localBoolean;
      if (ˏ() > 1000000L)
      {
        localObject = localBoolean;
        if (this.ٴ.getText().toString().length() < 3)
        {
          ToastWrapper.Show(getActivity(), ˊ((byte)ᐣ['Ǐ'], (byte)-ᐣ['Ó'], (short)ᐣ[30]), 1);
          localObject = Boolean.valueOf(false);
        }
      }
    }
    do
    {
      for (;;)
      {
        return ((Boolean)localObject).booleanValue();
        if ((this.ᵢ.booleanValue()) && (this.ʾ.booleanValue()))
        {
          ToastWrapper.Show(getActivity(), ˊ((byte)ᐣ['Ǐ'], (byte)-ᐣ['Ó'], (short)434), 1);
          localObject = Boolean.valueOf(false);
        }
        else if ((!this.ᵢ.booleanValue()) && (this.ʾ.booleanValue()))
        {
          ToastWrapper.Show(getActivity(), ˊ((byte)ᐣ['Ǐ'], (byte)-ᐣ['Ó'], (short)434), 1);
          localObject = Boolean.valueOf(false);
        }
        else
        {
          localObject = localBoolean;
          if (!this.ᵢ.booleanValue())
          {
            localObject = localBoolean;
            if (!this.ʾ.booleanValue())
            {
              localObject = this.ˊ.getText().toString() + this.ˋ.getText().toString() + this.ˎ.getText().toString() + this.ˏ.getText().toString();
              String str = this.ʻ.getText().toString() + this.ᐝ.getText().toString();
              if ((((String)localObject).length() == 0) || (!Luhn.Check((String)localObject)))
              {
                ToastWrapper.Show(getActivity(), ˊ((byte)(ᐣ[9] - 1), (byte)ᐣ[8], (short)385), 1);
                localObject = Boolean.valueOf(false);
              }
              else if (str.length() == 0)
              {
                localObject = getActivity();
                k = (byte)ᐣ['Ǐ'];
                int i = (byte)ᐣ[118];
                ToastWrapper.Show((Context)localObject, ˊ(k, i, (short)(i | 0x127)), 1);
                localObject = Boolean.valueOf(false);
              }
              else if (this.ᐝ.getText().toString().length() == 0)
              {
                localObject = getActivity();
                k = (byte)ᐣ['Ǐ'];
                int j = (byte)(k + 4);
                ToastWrapper.Show((Context)localObject, ˊ(k, j, (short)(j | 0x1C2)), 1);
                localObject = Boolean.valueOf(false);
              }
              else if (this.ʻ.getText().toString().length() == 0)
              {
                localObject = getActivity();
                k = (byte)ᐣ['Ǐ'];
                ToastWrapper.Show((Context)localObject, ˊ(k, (byte)(k + 4), (short)266), 1);
                localObject = Boolean.valueOf(false);
              }
              else if ((str.length() < 4) && (str.length() > 0))
              {
                ToastWrapper.Show(getActivity(), ˊ((byte)(ᐣ[9] - 1), (byte)ᐣ[30], (short)-ᐣ['ŗ']), 1);
                localObject = Boolean.valueOf(false);
              }
              else if (Integer.valueOf(str.substring(2, 4)).intValue() > 12)
              {
                ToastWrapper.Show(getActivity(), ˊ((byte)ᐣ['Ǐ'], (byte)ᐣ[''], (short)(ᐩ | 0x80)), 1);
                localObject = Boolean.valueOf(false);
              }
              else
              {
                if ((Integer.valueOf(str.substring(0, 2)).intValue() >= 15) && (Integer.valueOf(str.substring(0, 2)).intValue() <= 20)) {
                  break;
                }
                ToastWrapper.Show(getActivity(), ˊ((byte)ᐣ['Ǐ'], (byte)ᐣ[''], (short)516), 1);
                localObject = Boolean.valueOf(false);
              }
            }
          }
        }
      }
      localObject = localBoolean;
    } while (this.ʼ.getText().toString().length() >= 3);
    if (ˏ() > 1000000L) {}
    for (int k = 1;; k = 0)
    {
      localObject = localBoolean;
      if (k == 0) {
        break;
      }
      ToastWrapper.Show(getActivity(), ˊ((byte)ᐣ['Ǐ'], (byte)ᐣ[4], (short)(ᐩ | 0xD1)), 1);
      localObject = Boolean.valueOf(false);
      break;
    }
  }
  
  public EncryptedRequestDto getCardTransactionInformation()
  {
    EncryptedRequestDto localEncryptedRequestDto = new EncryptedRequestDto();
    AuthorizeNotifyRequest localAuthorizeNotifyRequest = new AuthorizeNotifyRequest();
    if (!formValidator()) {
      return null;
    }
    if ((this.ᵢ.booleanValue()) && (this.ﹶ != null))
    {
      localAuthorizeNotifyRequest.setMerchantUserCode(this.ﹳ);
      localAuthorizeNotifyRequest.setTxnAmount(String.valueOf(ˏ()));
      localAuthorizeNotifyRequest.setProductType(ᐝ());
      localAuthorizeNotifyRequest.setTxnReference(ʻ());
      if (ʼ().length() > 0) {}
      for (Object localObject = Boolean.valueOf(true);; localObject = Boolean.valueOf(false))
      {
        localAuthorizeNotifyRequest.setWithNotification(((Boolean)localObject).booleanValue());
        localAuthorizeNotifyRequest.setCardCVV(this.ٴ.getText().toString());
        localAuthorizeNotifyRequest.setReceiverUrl(ʼ());
        localAuthorizeNotifyRequest.setCardToken(this.ﹶ.getCardToken());
        localAuthorizeNotifyRequest.setCardExpiry(this.ﹶ.getCardExpiry());
        try
        {
          localObject = this.ʽ.getEncryptedRequestMessage(localAuthorizeNotifyRequest, this.ﹳ);
          return (EncryptedRequestDto)localObject;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          Log.d(ˊ((byte)ᐣ[''], (byte)ᐣ['£'], (short)116), localException.getMessage());
          return localEncryptedRequestDto;
        }
      }
    }
    return getTokenCardTransactionInformation();
  }
  
  public EncryptedRequestDto getTokenCardTransactionInformation()
  {
    EncryptedRequestDto localEncryptedRequestDto = new EncryptedRequestDto();
    AuthorizeNotifyRequest localAuthorizeNotifyRequest = new AuthorizeNotifyRequest();
    localAuthorizeNotifyRequest.setCardCVV(this.ʼ.getText().toString());
    localAuthorizeNotifyRequest.setMerchantUserCode(this.ﹳ);
    if (ʼ().length() > 0)
    {
      localObject = Boolean.valueOf(true);
      localAuthorizeNotifyRequest.setWithNotification(((Boolean)localObject).booleanValue());
      localAuthorizeNotifyRequest.setCardExpiry(this.ʻ.getText().toString() + this.ᐝ.getText().toString());
      localAuthorizeNotifyRequest.setCardNumber(this.ˊ.getText().toString() + this.ˋ.getText().toString() + this.ˎ.getText().toString() + this.ˏ.getText().toString());
      if (!this.ι.booleanValue()) {
        break label274;
      }
      localAuthorizeNotifyRequest.setCardTokenize(Boolean.valueOf(true));
      label185:
      localAuthorizeNotifyRequest.setReceiverUrl(ʼ());
      localAuthorizeNotifyRequest.setTxnAmount(Integer.toString(ˏ()));
      localAuthorizeNotifyRequest.setProductType(ᐝ());
      localAuthorizeNotifyRequest.setTxnReference(ʻ());
      if (ʼ().length() <= 0) {
        break label285;
      }
    }
    label274:
    label285:
    for (Object localObject = Boolean.valueOf(true);; localObject = Boolean.valueOf(false))
    {
      localAuthorizeNotifyRequest.setWithNotification(((Boolean)localObject).booleanValue());
      localAuthorizeNotifyRequest.setReceiverUrl(ʼ());
      try
      {
        localObject = this.ʽ.getEncryptedRequestMessage(localAuthorizeNotifyRequest, this.ﹳ);
        return (EncryptedRequestDto)localObject;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        Log.d(ˊ((byte)ᐣ['Ǐ'], (byte)ᐣ[''], (short)332), localException.getMessage());
      }
      localObject = Boolean.valueOf(false);
      break;
      localAuthorizeNotifyRequest.setCardTokenize(Boolean.valueOf(false));
      break label185;
    }
    return localEncryptedRequestDto;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == this.ʳ)
    {
      if ((paramIntent == null) || (!paramIntent.hasExtra(ˊ((byte)ᐣ['Ċ'], (byte)ᐣ[''], (short)(ᐣ[118] + 1))))) {
        break label383;
      }
      this.ʴ = ((CreditCard)paramIntent.getParcelableExtra(ˊ((byte)ᐣ['Ċ'], (byte)ᐣ[''], (short)(ᐣ[118] + 1))));
      this.ˊ.setText(this.ʴ.getFormattedCardNumber().substring(0, 4));
      this.ˋ.setText(this.ʴ.getFormattedCardNumber().substring(5, 9));
      this.ˎ.setText(this.ʴ.getFormattedCardNumber().substring(10, 14));
      this.ˏ.setText(this.ʴ.getFormattedCardNumber().substring(15, 19));
      this.ᐝ.setText(String.valueOf(this.ʴ.expiryMonth));
      this.ʻ.setText(String.valueOf(this.ʴ.expiryYear % 1000));
      paramIntent = new StringBuilder().append(ˊ((byte)(ᐣ[9] - 1), (byte)-ᐣ['ǿ'], (short)217)).append(this.ʴ.getFormattedCardNumber()).append(ˊ(107, (byte)ᐣ[''], (short)491));
      paramInt1 = (byte)-ᐣ['ǿ'];
    }
    label383:
    for (paramIntent = ˊ(paramInt1, (byte)paramInt1, (short)528) + this.ʴ.expiryMonth + ˊ((byte)ᐣ[27], (byte)ᐣ[''], (short)(ᐩ | 0x80)) + this.ʴ.expiryYear % 1000;; paramIntent = ˊ((byte)(ᐩ - 4), (byte)ᐣ['Œ'], (short)(ᐩ | 0x40)))
    {
      Log.d(ˊ((byte)(ᐣ[9] - 1), (byte)ᐣ[3], (short)392), paramIntent);
      return;
    }
  }
  
  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.mActivity = ((FragmentActivity)paramActivity);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    try
    {
      TraceMachine.enterMethod(this._nr_trace, "FragmentPaymentInfo#onCreateView", null);
      this.ﹺ = paramLayoutInflater.inflate(R.layout.fragment_payment_info, paramViewGroup, false);
      this.ﹺ.findViewById(R.id.ibAddNewCard).setOnClickListener(this.ˇ);
      this.ﹺ.findViewById(R.id.etCardNo1);
      this.ﹺ.findViewById(R.id.btn_scan).setOnClickListener(this.ˆ);
      this.ﹺ.findViewById(R.id.btnAddCard).setOnClickListener(this.ˡ);
      this.ﹺ.findViewById(R.id.tvExistingCard).setOnClickListener(this.ˮ);
      this.ﹺ.findViewById(R.id.btnChecklistSaveCard).setOnClickListener(this.ۥ);
      this.ﹺ.findViewById(R.id.btnEditList).setOnClickListener(this.ᐠ);
      this.ՙ = ((ListView)this.ﹺ.findViewById(R.id.lv_credit_card));
      paramLayoutInflater = (TextView)this.ﹺ.findViewById(R.id.tvExistingCard);
      paramViewGroup = (TextView)this.ﹺ.findViewById(R.id.tvAddNewCreditCard);
      this.ﾞ = ((TextView)this.ﹺ.findViewById(R.id.tvEditList));
      this.ˊ = ((EditText)this.ﹺ.findViewById(R.id.etCardNo1));
      this.ˋ = ((EditText)this.ﹺ.findViewById(R.id.etCardNo2));
      this.ˎ = ((EditText)this.ﹺ.findViewById(R.id.etCardNo3));
      this.ˏ = ((EditText)this.ﹺ.findViewById(R.id.etCardNo4));
      this.ᐝ = ((EditText)this.ﹺ.findViewById(R.id.etMonth));
      this.ʻ = ((EditText)this.ﹺ.findViewById(R.id.etYear));
      this.ʼ = ((EditText)this.ﹺ.findViewById(R.id.etCVV));
      this.ʿ = ((Button)this.ﹺ.findViewById(R.id.btnAddCard));
      this.ˈ = ((TextView)this.ﹺ.findViewById(R.id.tvOR));
      this.ˉ = ((ImageButton)this.ﹺ.findViewById(R.id.ibAddNewCard));
      this.ˌ = ((ImageView)this.ﹺ.findViewById(R.id.btnChecklistSaveCard));
      this.ᵔ = ((ProgressBar)this.ﹺ.findViewById(R.id.progressBarLoadCard));
      this.ˍ = ((LinearLayout)this.ﹺ.findViewById(R.id.layout_card_list));
      this.ˑ = ((LinearLayout)this.ﹺ.findViewById(R.id.layoutProgress));
      this.ⁱ = ((LinearLayout)this.ﹺ.findViewById(R.id.layout_mm_space));
      this.ᐧ = ((ProgressBar)this.ﹺ.findViewById(R.id.progressBarLoadCard));
      this.ᴵ = ((RelativeLayout)this.ﹺ.findViewById(R.id.layoutRelativeLayoutCardList));
      this.ـ = ((LinearLayout)this.ﹺ.findViewById(R.id.layout_card_number));
      paramBundle = FontManager.GetBebasRegularFont(getActivity());
      Typeface localTypeface = FontManager.GetLatoRegularFont(getActivity());
      ViewGroup localViewGroup1 = (ViewGroup)this.ﹺ.findViewById(R.id.layout_card_list);
      ViewGroup localViewGroup2 = (ViewGroup)this.ﹺ.findViewById(R.id.layout_card_number);
      TextViewManager.setFontTextView(localViewGroup1, localTypeface);
      TextViewManager.setFontTextView(localViewGroup2, localTypeface);
      paramLayoutInflater.setTypeface(paramBundle);
      this.ﾞ.setTypeface(paramBundle);
      this.ˈ.setTypeface(paramBundle);
      paramViewGroup.setTypeface(paramBundle);
      this.ˋ.setOnKeyListener(new FragmentPaymentInfo.16(this));
      this.ˎ.setOnKeyListener(new FragmentPaymentInfo.17(this));
      this.ˏ.setOnKeyListener(new FragmentPaymentInfo.18(this));
      this.ʻ.setOnKeyListener(new FragmentPaymentInfo.2(this));
      this.ˊ.addTextChangedListener(new FragmentPaymentInfo.3(this));
      this.ˋ.addTextChangedListener(new FragmentPaymentInfo.4(this));
      this.ˎ.addTextChangedListener(new FragmentPaymentInfo.5(this));
      this.ᐝ.addTextChangedListener(new FragmentPaymentInfo.6(this));
      this.ʽ = new PaymentService(getActivity(), 1, getId());
      paramViewGroup = this.ʽ;
      paramLayoutInflater = getArguments();
      if (paramLayoutInflater != null)
      {
        paramLayoutInflater = paramLayoutInflater.getString(ˊ((byte)ᐣ[7], (byte)ᐣ['Ȃ'], (short)472));
        paramViewGroup.setSecurityToken(paramLayoutInflater);
        paramLayoutInflater = getArguments();
        int i = (byte)ᐣ['Ã'];
        this.ᐨ = paramLayoutInflater.getString(ˊ(i, (byte)(i | 0x23), (short)271));
        this.ﹳ = getArguments().getString(ˊ((byte)ᐣ[30], (byte)ᐣ[3], (short)205));
        paramLayoutInflater = this.ﹳ;
        if ((this.cardList != null) && (this.cardList.getCardTokens() != null) && (!this.cardList.getCardTokens().isEmpty()))
        {
          paramLayoutInflater = this.cardList;
          if ((!this.ͺ.booleanValue()) && (paramLayoutInflater != null))
          {
            ˊ();
            showCardList();
            this.ᵔ.setVisibility(0);
            paramLayoutInflater = this.ﹺ;
            TraceMachine.exitMethod();
            return paramLayoutInflater;
          }
        }
      }
    }
    catch (NoSuchFieldError paramBundle)
    {
      for (;;)
      {
        TraceMachine.enterMethod(null, "FragmentPaymentInfo#onCreateView", null);
        continue;
        this.ˍ.setVisibility(8);
        this.ᵔ.setVisibility(8);
        this.ˑ.setVisibility(8);
        this.ᵔ.setVisibility(4);
        continue;
        this.ʽ.tokenList(paramLayoutInflater, ᐝ());
        this.ͺ = Boolean.valueOf(true);
        this.ᵔ.setVisibility(0);
        continue;
        paramLayoutInflater = null;
      }
    }
  }
  
  public void onItemClickList(int paramInt, EditText paramEditText, boolean paramBoolean)
  {
    this.ﹶ = ((CardToken)this.ʹ.get(paramInt));
    this.ٴ = paramEditText;
    this.ᵢ = Boolean.valueOf(paramBoolean);
    ˋ();
    AlertDialog.Builder localBuilder;
    if (!this.ᵢ.booleanValue())
    {
      this.ˍ.setMinimumHeight(ˊ(this.ՙ));
      if (this.ʾ.booleanValue())
      {
        paramEditText = new AlertDialog.Builder(getActivity());
        localBuilder = paramEditText.setMessage(ˊ(49, (byte)ᐣ[''], (short)199) + this.ﹶ.getCardNumberMasked() + ˊ((byte)-ᐣ['ŗ'], (byte)(ᐣ[''] - 1), (short)283)).setPositiveButton(ˊ((byte)ᐩ, (byte)(ᐣ[''] - 1), (short)386), new FragmentPaymentInfo.8(this));
        paramInt = (byte)(ᐣ[9] - 1);
        localBuilder.setNegativeButton(ˊ(paramInt, (byte)(paramInt + 5), (short)533), new FragmentPaymentInfo.7(this));
        paramEditText.show();
        this.ᵢ = Boolean.valueOf(true);
      }
    }
    do
    {
      return;
      if (this.ʾ.booleanValue())
      {
        paramEditText = new AlertDialog.Builder(getActivity());
        localBuilder = paramEditText.setMessage(ˊ(49, (byte)ᐣ[''], (short)199) + this.ﹶ.getCardNumberMasked() + ˊ((byte)-ᐣ['ŗ'], (byte)(ᐣ[''] - 1), (short)283)).setPositiveButton(ˊ((byte)ᐩ, (byte)(ᐣ[''] - 1), (short)386), new FragmentPaymentInfo.10(this));
        paramInt = (byte)(ᐣ[9] - 1);
        localBuilder.setNegativeButton(ˊ(paramInt, (byte)(paramInt + 5), (short)533), new FragmentPaymentInfo.9(this));
        paramEditText.show();
        this.ᵢ = Boolean.valueOf(true);
        return;
      }
      if ((paramInt > 0) && (ˏ() > 1000000L))
      {
        this.ˍ.setMinimumHeight(ˊ(this.ՙ) + ˊ(this.ՙ) / this.ʹ.size());
        return;
      }
    } while ((paramInt == 0) && (ˏ() > 1000000L));
    this.ˍ.setMinimumHeight(ˊ(this.ՙ));
  }
  
  public void onLongItemClickList(int paramInt)
  {
    this.ﹶ = ((CardToken)this.ʹ.get(paramInt));
    this.ʽ.removeCard(this.ﹳ, this.ﹶ.getCardToken(), ᐝ());
    this.י.notifyDataSetChanged();
  }
  
  public void onReceiveHttpResponse(Object paramObject)
  {
    if ((paramObject instanceof ResponseEntity)) {
      if ((((ResponseEntity)paramObject).getBody() instanceof TokenListResponse))
      {
        this.cardList = ((TokenListResponse)((ResponseEntity)paramObject).getBody());
        this.ͺ = Boolean.valueOf(false);
        if ((this.cardList.getCardTokens() == null) || (this.cardList.getCardTokens().size() <= 0)) {
          break label250;
        }
        TokenListResponse localTokenListResponse = this.cardList;
        if ((this.ͺ.booleanValue()) || (localTokenListResponse == null)) {
          break label212;
        }
        ˊ();
        showCardList();
      }
    }
    for (;;)
    {
      if (((((ResponseEntity)paramObject).getBody() instanceof TokenRemoveResponse)) && (((TokenRemoveResponse)((ResponseEntity)paramObject).getBody()).getIpgResponseCode().equals(ˊ((byte)ᐣ[83], (byte)ᐣ[''], (short)(ᐩ | 0x80)))))
      {
        ToastWrapper.Show(getActivity(), ˊ((byte)ᐣ[21], (byte)ᐣ[''], (short)141), 1);
        this.ʹ.clear();
        this.ʽ.tokenList(this.ﹳ, ᐝ());
      }
      this.ᵔ.setVisibility(4);
      return;
      label212:
      this.ˍ.setVisibility(8);
      this.ᵔ.setVisibility(8);
      this.ˑ.setVisibility(8);
      this.ᵔ.setVisibility(4);
      continue;
      label250:
      this.ˍ.setVisibility(8);
      this.ᵔ.setVisibility(8);
      this.ˑ.setVisibility(8);
      this.ᵔ.setVisibility(4);
    }
  }
  
  public void onResume()
  {
    this.ᵔ.setVisibility(0);
    super.onResume();
  }
  
  protected void onStart()
  {
    super.onStart();
    ApplicationStateMonitor.getInstance().activityStarted();
  }
  
  protected void onStop()
  {
    super.onStop();
    ApplicationStateMonitor.getInstance().activityStopped();
  }
  
  public void selectItem(int paramInt)
  {
    this.ᵎ = paramInt;
    int i = 0;
    if ((this.ՙ != null) && (i < this.cardList.getCardTokens().size()))
    {
      if (i == paramInt)
      {
        this.ՙ.deferNotifyDataSetChanged();
        this.ՙ.setItemChecked(paramInt, true);
      }
      for (;;)
      {
        i += 1;
        break;
        this.ՙ.setItemChecked(i, false);
      }
    }
  }
  
  public void showCardList()
  {
    ArrayList localArrayList = this.ʹ;
    Resources localResources = getResources();
    if (ˏ() > 1000000L) {}
    for (boolean bool = true;; bool = false)
    {
      this.י = new CardListAdapter(this, localArrayList, localResources, Boolean.valueOf(bool), this.ʾ);
      this.ՙ.setAdapter(this.י);
      this.ՙ.setVisibility(0);
      this.ՙ.setVisibility(0);
      this.ˍ.setMinimumHeight(ˊ(this.ՙ));
      this.ˍ.setVisibility(0);
      this.ˑ.setVisibility(4);
      this.ᵔ.setVisibility(4);
      return;
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/nostratech/gojek/driver/fragments/FragmentPaymentInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */