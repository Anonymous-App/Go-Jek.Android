package com.gojek.gotix.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.gojek.gotix.Event;
import com.gojek.gotix.Image;
import com.gojek.gotix.Location;
import com.gojek.gotix.R.color;
import com.gojek.gotix.R.drawable;
import com.gojek.gotix.R.id;
import com.gojek.gotix.R.layout;
import com.gojek.gotix.R.plurals;
import com.gojek.gotix.R.string;
import com.gojek.gotix.Schedule;
import com.gojek.gotix.Ticket;
import com.gojek.gotix.activities.GotixEventDetailActivity;
import com.gojek.gotix.adapters.ImageEventAdapter;
import com.gojek.gotix.network.model.LocationMap;
import com.gojek.gotix.presenter.GotixEventDetailPresenter;
import com.gojek.gotix.services.model.ProduceEvent;
import com.gojek.gotix.tools.AndroidBus;
import com.gojek.gotix.tools.BusProvider;
import com.gojek.gotix.tools.GotixUtils;
import com.gojek.gotix.tools.ObservableIntervalHelper;
import com.gojek.gotix.views.SpannedTextView;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.norbsoft.typefacehelper.TypefaceHelper;
import com.squareup.otto.Subscribe;
import com.viewpagerindicator.CirclePageIndicator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lib.gojek.base.helper.BaseFullScreenHelper;
import lib.gojek.base.helper.BasePreferences;
import lib.gojek.base.helper.BaseToPickServiceGojek;
import lib.gojek.base.helper.FontFaceHelper;
import lib.gojek.base.util.BaseDialogFragment.AlertDialogListener;
import nucleus.factory.RequiresPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@RequiresPresenter(GotixEventDetailPresenter.class)
public class GotixEventDetailFragment
  extends GotixBaseFragment<GotixEventDetailPresenter>
  implements BaseDialogFragment.AlertDialogListener
{
  public static final String ADD = "add";
  private static final String EMPTY_STRING = "";
  private static final int END_SPAN = 8;
  private static final int MAX_LINES = 100;
  private static final String MINUS = "minus";
  private static final int MIN_LINE = 10;
  private static final int REFRESH_INTERVAL = 60;
  private static final int START_SPAN = 0;
  private static String TAG = GotixEventDetailFragment.class.getSimpleName();
  private static final int TAKEN_TICKET = 1;
  public static final String TRANSACTION_EVENT_ID = "transaction_event_id";
  public static final String VIEW_LESS = "View Less";
  public static final String VIEW_MORE = "View More";
  private BaseFullScreenHelper baseFullScreenHelper;
  private BaseToPickServiceGojek baseToPickServiceGojek;
  private ImageView btnNext;
  private ImageView btnTakeMeThere;
  private RelativeLayout callCenterEventDetail;
  private Context context;
  private TextView dateEvent;
  private TextView descTextEvent;
  private int eventId;
  private ViewPager eventPager;
  private boolean flagTextviewClicked = false;
  private ImageView floorMap;
  private ImageEventAdapter imageEventAdapter;
  private LinearLayout listTicketLayout;
  private TextView location;
  private RelativeLayout locationGroup;
  private TextView moreLessIndicator;
  private ObservableIntervalHelper observableIntervalHelper;
  private CirclePageIndicator pagerIndicator;
  private GotixEventDetailActivity parentActivity;
  private TextView priceTicket;
  private ProgressBar progresBarPostOrder;
  private CircleProgressBar progressBarEventDetail;
  private boolean stateExpandDescEvent = false;
  private SwipeRefreshLayout swipeRefreshLayout;
  private TextView tagEvent;
  private int ticketIterator = 0;
  private Map<Integer, ViewTicketHolder> ticketList;
  private TextView ticketQtySummary;
  private RelativeLayout ticketTypeLayout;
  private CardView ticketsListCard;
  private TextView titleEvent;
  private TextView totalTicketPrice;
  private SpannedTextView typeTicket;
  
  private void addButtonListener(ViewTicketHolder paramViewTicketHolder)
  {
    paramViewTicketHolder.getAddButton().setOnClickListener(GotixEventDetailFragment..Lambda.17.lambdaFactory$(this, paramViewTicketHolder));
  }
  
  private void addListeners()
  {
    this.btnNext.setOnClickListener(GotixEventDetailFragment..Lambda.1.lambdaFactory$(this));
    this.floorMap.setOnClickListener(this.baseFullScreenHelper);
    this.eventPager.setOnTouchListener(GotixEventDetailFragment..Lambda.2.lambdaFactory$(this));
  }
  
  private List<LocationMap> addLocationToTempModel(List<Location> paramList)
  {
    return (List)Stream.of(paramList).filter(GotixEventDetailFragment..Lambda.6.lambdaFactory$()).map(GotixEventDetailFragment..Lambda.7.lambdaFactory$()).collect(Collectors.toList());
  }
  
  private void addTicketsToDynamicView(Ticket paramTicket, int paramInt)
  {
    if (!this.ticketList.containsKey(paramTicket.getTicket_id()))
    {
      View localView = bindListTicket();
      ViewTicketHolder localViewTicketHolder = new ViewTicketHolder(localView, paramTicket);
      this.ticketList.put(paramTicket.getTicket_id(), localViewTicketHolder);
      this.listTicketLayout.addView(localView);
      doWhenTicketSoldOut(paramTicket.getStock().intValue(), localViewTicketHolder, this.ticketList);
      hideLastDividerOfTickets(localView, paramInt);
      this.ticketIterator += 1;
    }
  }
  
  private void bindDataTicket(final List<Ticket> paramList)
  {
    Observable.from(paramList).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber()
    {
      public void onCompleted()
      {
        GotixEventDetailFragment.access$102(GotixEventDetailFragment.this, 0);
        GotixEventDetailFragment.this.bindViewFromPresenter(((GotixEventDetailPresenter)GotixEventDetailFragment.this.getPresenter()).getTotalTicket());
      }
      
      public void onError(Throwable paramAnonymousThrowable) {}
      
      public void onNext(Ticket paramAnonymousTicket)
      {
        GotixEventDetailFragment.this.addTicketsToDynamicView(paramAnonymousTicket, paramList.size());
      }
    });
  }
  
  private View bindListTicket()
  {
    return getInflater().inflate(R.layout.item_detail_list_ticket, null);
  }
  
  private void bindViewById(View paramView)
  {
    this.swipeRefreshLayout = ((SwipeRefreshLayout)paramView.findViewById(R.id.swipe_to_refresh));
    this.titleEvent = ((TextView)paramView.findViewById(R.id.event_detail_title));
    this.tagEvent = ((TextView)paramView.findViewById(R.id.event_detail_tag));
    this.descTextEvent = ((TextView)paramView.findViewById(R.id.desc));
    this.moreLessIndicator = ((TextView)paramView.findViewById(R.id.more_less_indicator));
    this.eventPager = ((ViewPager)paramView.findViewById(R.id.event_detail_view_pager));
    this.locationGroup = ((RelativeLayout)paramView.findViewById(R.id.location_group));
    this.location = ((TextView)paramView.findViewById(R.id.location_text));
    this.callCenterEventDetail = ((RelativeLayout)paramView.findViewById(R.id.call_center_event_detail));
    this.typeTicket = ((SpannedTextView)paramView.findViewById(R.id.event_detail_ticket_type));
    this.typeTicket.setSpacing(10.0F);
    this.pagerIndicator = ((CirclePageIndicator)paramView.findViewById(R.id.event_detail_view_pager_indicator));
    this.priceTicket = ((TextView)paramView.findViewById(R.id.event_detail_price));
    this.floorMap = ((ImageView)paramView.findViewById(R.id.floor_map));
    this.ticketsListCard = ((CardView)paramView.findViewById(R.id.ticketListCard));
    this.btnNext = ((ImageView)paramView.findViewById(R.id.detail_event_next));
    this.btnTakeMeThere = ((ImageView)paramView.findViewById(R.id.detail_event_take_me_there));
    this.progresBarPostOrder = ((ProgressBar)paramView.findViewById(R.id.event_order_progressbar_btn));
    this.progressBarEventDetail = ((CircleProgressBar)paramView.findViewById(R.id.event_detail_progress_bar));
    this.pagerIndicator = ((CirclePageIndicator)paramView.findViewById(R.id.event_detail_view_pager_indicator));
    this.listTicketLayout = ((LinearLayout)paramView.findViewById(R.id.list_ticket_layout));
    this.totalTicketPrice = ((TextView)paramView.findViewById(R.id.ticket_price));
    this.ticketTypeLayout = ((RelativeLayout)paramView.findViewById(R.id.ticket_type_layout));
    this.ticketQtySummary = ((TextView)paramView.findViewById(R.id.ticket_qty_summary));
    this.dateEvent = ((TextView)paramView.findViewById(R.id.date_event));
    setButtonNextEnabled(false);
  }
  
  private void bindViewFromPresenter(Map<Integer, Integer> paramMap)
  {
    if (((GotixEventDetailPresenter)getPresenter()).getTotalTicket().size() != 0)
    {
      ((GotixEventDetailPresenter)getPresenter()).resetAvailableTicket();
      ((GotixEventDetailPresenter)getPresenter()).resetTotalPrice();
      Iterator localIterator = paramMap.keySet().iterator();
      if (localIterator.hasNext())
      {
        Integer localInteger = (Integer)localIterator.next();
        ViewTicketHolder localViewTicketHolder = (ViewTicketHolder)this.ticketList.get(localInteger);
        if (localViewTicketHolder.getStock() != 0) {
          localViewTicketHolder.getQtyTicket().setText(paramMap.get(localInteger) + "");
        }
        for (;;)
        {
          ((GotixEventDetailPresenter)getPresenter()).setTotalTicket(localInteger.intValue(), ((Integer)paramMap.get(localInteger)).intValue(), localViewTicketHolder.getPrice(), "add", localViewTicketHolder.getStock(), ((Integer)paramMap.get(localInteger)).intValue());
          ((GotixEventDetailPresenter)getPresenter()).displayWarningMessageTicket(((Integer)paramMap.get(localInteger)).intValue(), localViewTicketHolder.getStock(), localInteger.intValue());
          break;
          localViewTicketHolder.getQtyTicket().setText(getResources().getString(R.string.ticket_price_zero));
        }
      }
      listenAvailableTicket();
    }
  }
  
  private void callCenterListener(String paramString)
  {
    RxView.clicks(this.callCenterEventDetail).subscribe(GotixEventDetailFragment..Lambda.14.lambdaFactory$(this, paramString));
  }
  
  private void clickOnTicketType(String paramString)
  {
    this.ticketTypeLayout.setOnClickListener(GotixEventDetailFragment..Lambda.19.lambdaFactory$(this, paramString));
  }
  
  private void configCircleProgresbar()
  {
    this.progressBarEventDetail.setColorSchemeResources(new int[] { R.color.bg_base_green });
  }
  
  private void disableDecreaseButton(int paramInt)
  {
    getDecreaseButton(paramInt).setEnabled(false);
    getDecreaseButton(paramInt).setBackgroundResource(R.drawable.gotix_button_price_min);
  }
  
  private void displayWarningMessageTicketListener()
  {
    Iterator localIterator = this.ticketList.values().iterator();
    while (localIterator.hasNext())
    {
      ViewTicketHolder localViewTicketHolder = (ViewTicketHolder)localIterator.next();
      int i = Integer.valueOf(localViewTicketHolder.getQtyTicket().getText().toString()).intValue();
      ((GotixEventDetailPresenter)getPresenter()).displayWarningMessageTicket(i, localViewTicketHolder.getStock(), localViewTicketHolder.getTicketId());
    }
  }
  
  private void doCallAction(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder().append("tel:");
    String str;
    if (paramString != null)
    {
      str = paramString;
      if (paramString != "") {}
    }
    else
    {
      str = getString(R.string.call_center_number);
    }
    startActivity(new Intent("android.intent.action.CALL", Uri.parse(str)));
  }
  
  private void doWhenTicketSoldOut(int paramInt, ViewTicketHolder paramViewTicketHolder, Map<Integer, ViewTicketHolder> paramMap)
  {
    if (paramInt != 0)
    {
      addButtonListener(paramViewTicketHolder);
      minusButtonListener(paramViewTicketHolder);
      quantityTicketObservable(paramViewTicketHolder, paramMap);
      return;
    }
    soldOutButton(paramViewTicketHolder.getAddButton(), paramViewTicketHolder.getQtyTicket(), paramViewTicketHolder.getMinusButton());
  }
  
  private void enableAddButton(int paramInt)
  {
    getAddButton(paramInt).setEnabled(true);
    getAddButton(paramInt).setBackgroundResource(R.drawable.gotix_button_price_plus_active);
  }
  
  private void enableDecreaseButton(int paramInt)
  {
    getDecreaseButton(paramInt).setEnabled(true);
    getDecreaseButton(paramInt).setBackgroundResource(R.drawable.gotix_button_price_min_active);
  }
  
  private void fullTextDesc(String paramString)
  {
    this.descTextEvent.setMaxLines(100);
    this.moreLessIndicator.setText(spannableText(paramString));
  }
  
  private ImageView getAddButton(int paramInt)
  {
    return ((ViewTicketHolder)this.ticketList.get(Integer.valueOf(paramInt))).getAddButton();
  }
  
  private void getDataFromBundle()
  {
    Bundle localBundle = getArguments();
    if (localBundle != null) {
      this.eventId = localBundle.getInt("type_id");
    }
  }
  
  private ImageView getDecreaseButton(int paramInt)
  {
    return ((ViewTicketHolder)this.ticketList.get(Integer.valueOf(paramInt))).getMinusButton();
  }
  
  private String getText(String paramString)
  {
    String str = paramString;
    if (TextUtils.isEmpty(paramString)) {
      str = "";
    }
    return str;
  }
  
  private void hideBtnNext()
  {
    this.btnNext.setVisibility(8);
  }
  
  private void hideIndicatorIfOnlyOneImage()
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.9.lambdaFactory$(this));
  }
  
  private void hideLastDividerOfTickets(View paramView, int paramInt)
  {
    if (isLastItem(paramInt)) {
      paramView.findViewById(R.id.divider_ticket).setVisibility(8);
    }
  }
  
  private void hideTicketListCard()
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.25.lambdaFactory$(this));
  }
  
  private void initBaseFullScreenHelper()
  {
    this.baseFullScreenHelper = new BaseFullScreenHelper(this.context, getActivity());
  }
  
  private boolean isFreeEvent(Long paramLong)
  {
    return paramLong.longValue() == 0L;
  }
  
  private boolean isLastItem(int paramInt)
  {
    return paramInt - 1 == this.ticketIterator;
  }
  
  private void listenAvailableTicket()
  {
    if (((GotixEventDetailPresenter)getPresenter()).getAvailableTicket() >= ((GotixEventDetailPresenter)getPresenter()).getMaxTicket())
    {
      setButtonNextEnabled(false);
      return;
    }
    setButtonNextEnabled(true);
  }
  
  private void minusButtonListener(ViewTicketHolder paramViewTicketHolder)
  {
    paramViewTicketHolder.getMinusButton().setOnClickListener(GotixEventDetailFragment..Lambda.18.lambdaFactory$(this, paramViewTicketHolder));
  }
  
  public static GotixEventDetailFragment newInstance(int paramInt)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("type_id", paramInt);
    GotixEventDetailFragment localGotixEventDetailFragment = new GotixEventDetailFragment();
    localGotixEventDetailFragment.setArguments(localBundle);
    return localGotixEventDetailFragment;
  }
  
  private void onNextButtonClicked()
  {
    showProgressBarWhenPostOrder();
    if (BasePreferences.isLoggedIn())
    {
      ((GotixEventDetailPresenter)getPresenter()).postTicketPurchase(this.eventId);
      return;
    }
    this.parentActivity.openSignUpPage();
    hideProgressBarWhenPostOrder();
  }
  
  private void onRefreshDetailEvent()
  {
    setSwipeRefreshLayoutStatus(true);
    ((GotixEventDetailPresenter)getPresenter()).getEventDetail(this.eventId);
  }
  
  private void onTakeMeThereClicked(Double paramDouble1, Double paramDouble2, String paramString)
  {
    if (BasePreferences.isLoggedIn())
    {
      this.baseToPickServiceGojek.openGoRide(this.context, paramDouble1, paramDouble2, paramString);
      return;
    }
    this.parentActivity.openSignUpPage();
  }
  
  private void quantityTicketObservable(ViewTicketHolder paramViewTicketHolder, Map<Integer, ViewTicketHolder> paramMap)
  {
    paramViewTicketHolder.getAddButton().setEnabled(true);
    paramViewTicketHolder.getMinusButton().setEnabled(false);
    paramViewTicketHolder.getMinusButton().setBackgroundResource(R.drawable.gotix_button_price_min);
    RxTextView.textChanges(paramViewTicketHolder.getQtyTicket()).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixEventDetailFragment..Lambda.15.lambdaFactory$(this, paramViewTicketHolder, paramMap), GotixEventDetailFragment..Lambda.16.lambdaFactory$());
  }
  
  private void resetToDefaultState()
  {
    ((GotixEventDetailPresenter)getPresenter()).resetAvailableTicket();
    setTicketPrice(getResources().getString(R.string.ticket_price_zero));
    setTicketQtySummary(0);
    ((GotixEventDetailPresenter)getPresenter()).resetTotalPrice();
    setButtonNextEnabled(false);
  }
  
  private void setButtonNextEnabled(boolean paramBoolean)
  {
    this.btnNext.setEnabled(paramBoolean);
  }
  
  private void setFontFace()
  {
    TypefaceHelper.typeface(this.titleEvent, FontFaceHelper.getBebasNeue());
    TypefaceHelper.typeface(this.tagEvent, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.descTextEvent, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.moreLessIndicator, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.typeTicket, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.location, FontFaceHelper.getLatoFont());
    TypefaceHelper.typeface(this.dateEvent, FontFaceHelper.getLatoFont());
  }
  
  private void setSwipeRefreshEnabled(boolean paramBoolean)
  {
    this.swipeRefreshLayout.setEnabled(paramBoolean);
  }
  
  private void setSwipeRefreshLayoutStatus(boolean paramBoolean)
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.23.lambdaFactory$(this, paramBoolean));
  }
  
  private void showIndicatorIfMoreThanOneImage()
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.10.lambdaFactory$(this));
  }
  
  private void showProgressBar()
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.21.lambdaFactory$(this));
  }
  
  private void showTicketListCard()
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.24.lambdaFactory$(this));
  }
  
  private void soldOutButton(ImageView paramImageView1, TextView paramTextView, ImageView paramImageView2)
  {
    paramImageView1.setBackgroundResource(R.drawable.gotix_button_price_plus);
    paramImageView2.setBackgroundResource(R.drawable.gotix_button_price_min);
    paramTextView.setBackgroundResource(R.drawable.bg_edittext_ticket_quantity_disable);
    paramTextView.setTextColor(Color.rgb(221, 221, 221));
  }
  
  private void spanTextDesc(String paramString1, String paramString2)
  {
    this.descTextEvent.setText(paramString1);
    ((GotixEventDetailPresenter)getPresenter()).countLineDesc(this.descTextEvent, paramString2);
  }
  
  private SpannableString spannableText(String paramString)
  {
    paramString = new SpannableString(paramString);
    paramString.setSpan(new StyleSpan(0), 0, 8, 17);
    return paramString;
  }
  
  private void validateTouchEvent(MotionEvent paramMotionEvent)
  {
    setSwipeRefreshEnabled(false);
    switch (paramMotionEvent.getAction())
    {
    default: 
      return;
    }
    setSwipeRefreshEnabled(true);
  }
  
  public void addDynamicLocation(String paramString)
  {
    this.location.setText(paramString);
  }
  
  public void bindDataEvent(Event paramEvent)
  {
    hideProgressBar();
    if (paramEvent != null)
    {
      if ((((GotixEventDetailPresenter)getPresenter()).getTimeScheduleToday(paramEvent.getSchedules())) && (paramEvent.getIs_take_me_there().booleanValue()))
      {
        hideImageFloorPlan();
        hideTicketListCard();
        hideBtnNext();
        ((GotixEventDetailPresenter)getPresenter()).getLocationTakeMeThere();
        ((GotixEventDetailPresenter)getPresenter()).getEnableTakeMeThere();
      }
      for (;;)
      {
        this.titleEvent.setText(getText(paramEvent.getName()));
        this.typeTicket.setText(getText(paramEvent.getType()));
        this.tagEvent.setText(GotixUtils.populateTag(paramEvent.getTag(), this.context));
        callCenterListener(paramEvent.getCall_center_number());
        ((GotixEventDetailPresenter)getPresenter()).resetAvailableTicket();
        if (paramEvent.getSchedules() != null) {
          onDataTicketLoaded(paramEvent.getSchedules());
        }
        clickOnTicketType(paramEvent.getType_description());
        return;
        if (!paramEvent.getSchedules().isEmpty()) {
          if (((Schedule)paramEvent.getSchedules().get(0)).getTickets().isEmpty())
          {
            hideTicketListCard();
            hideBtnNext();
          }
          else
          {
            showTicketListCard();
          }
        }
      }
    }
    this.parentActivity.showDialogErrorService();
  }
  
  public void callTakeMeThere(Location paramLocation)
  {
    this.btnTakeMeThere.setOnClickListener(GotixEventDetailFragment..Lambda.13.lambdaFactory$(this, paramLocation));
  }
  
  public void clearTicketLayot()
  {
    this.listTicketLayout.removeAllViews();
  }
  
  public void configIndicator()
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.5.lambdaFactory$(this));
  }
  
  public void countLineViewLess(String paramString)
  {
    fullTextDesc(paramString);
    this.moreLessIndicator.setVisibility(8);
  }
  
  public void countLineViewMore(String paramString)
  {
    this.moreLessIndicator.setVisibility(0);
    this.descTextEvent.setSingleLine(false);
    this.descTextEvent.setEllipsize(TextUtils.TruncateAt.END);
    this.descTextEvent.setLines(10);
    this.moreLessIndicator.setText(spannableText(paramString));
  }
  
  public void createIntervalRefresh()
  {
    this.observableIntervalHelper = new ObservableIntervalHelper(60)
    {
      public void doOnTimerStart()
      {
        if ((GotixEventDetailFragment.this.getView() != null) && (GotixEventDetailFragment.this.getView().isShown())) {
          GotixEventDetailFragment.this.onRefreshDetailEvent();
        }
      }
    };
    this.observableIntervalHelper.startTimer();
  }
  
  public void disableAddButton(int paramInt)
  {
    getAddButton(paramInt).setEnabled(false);
    getAddButton(paramInt).setBackgroundResource(R.drawable.gotix_button_price_plus);
  }
  
  public void enableVisibleTakeMeThere(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.btnTakeMeThere.setVisibility(0);
      return;
    }
    this.btnTakeMeThere.setVisibility(8);
  }
  
  public void failedOrder()
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.29.lambdaFactory$(this));
  }
  
  protected int getLayout()
  {
    return R.layout.fragment_gotix_event_detail;
  }
  
  public void hideImageFloorPlan()
  {
    this.floorMap.setVisibility(8);
  }
  
  public void hideProgressBar()
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.22.lambdaFactory$(this));
    setSwipeRefreshLayoutStatus(false);
  }
  
  public void hideProgressBarWhenPostOrder()
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.27.lambdaFactory$(this));
  }
  
  protected void initSwipeRefreshLayout()
  {
    this.swipeRefreshLayout.setColorSchemeColors(new int[] { getResources().getColor(R.color.bg_base_green) });
    this.swipeRefreshLayout.setOnRefreshListener(GotixEventDetailFragment..Lambda.4.lambdaFactory$(this));
  }
  
  public void negativeButtonClicked() {}
  
  public void onAttach(Context paramContext)
  {
    super.onAttach(paramContext);
    this.parentActivity = ((GotixEventDetailActivity)getActivity());
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = getActivity();
    this.baseToPickServiceGojek = new BaseToPickServiceGojek();
    getDataFromBundle();
    createIntervalRefresh();
    initBaseFullScreenHelper();
  }
  
  public void onDataTicketLoaded(List<Schedule> paramList)
  {
    this.ticketList = new HashMap();
    clearTicketLayot();
    Observable.from(paramList).map(GotixEventDetailFragment..Lambda.11.lambdaFactory$()).forEach(GotixEventDetailFragment..Lambda.12.lambdaFactory$(this));
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    this.observableIntervalHelper.unsubscribe();
    GotixUtils.terminateSwipeRefresh(this.swipeRefreshLayout);
  }
  
  @Subscribe
  public void onEventsPostPonedFromNotif(ProduceEvent paramProduceEvent)
  {
    setSwipeRefreshLayoutStatus(true);
    ((GotixEventDetailPresenter)getPresenter()).getEventDetailFromNetwork(paramProduceEvent.getEventId());
  }
  
  public void onOutOfStock()
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.28.lambdaFactory$(this));
  }
  
  public void onPause()
  {
    super.onPause();
    BusProvider.getInstance().unregister(this);
    this.observableIntervalHelper.unsubscribe();
    GotixUtils.terminateSwipeRefresh(this.swipeRefreshLayout);
  }
  
  public void onResume()
  {
    super.onResume();
    resetToDefaultState();
    ((GotixEventDetailPresenter)getPresenter()).getEventDetail(this.eventId);
    BusProvider.getInstance().register(this);
  }
  
  public void onSuccesCreateOrderOfEvent()
  {
    hideProgressBarWhenPostOrder();
    this.parentActivity.openCheckoutPage();
  }
  
  public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    ((GotixEventDetailPresenter)getPresenter()).resetAvailableTicket();
    bindViewById(paramView);
    addListeners();
    configCircleProgresbar();
    initSwipeRefreshLayout();
    setFontFace();
    showProgressBar();
    ((GotixEventDetailPresenter)getPresenter()).resetTotalPrice();
  }
  
  public void positiveButtonClicked()
  {
    ((GotixEventDetailPresenter)getPresenter()).getTotalTicket().clear();
    resetToDefaultState();
    onRefreshDetailEvent();
  }
  
  public void setDateOfEvent(String paramString)
  {
    this.dateEvent.setText(paramString);
  }
  
  public void setEventDataFromCache(Event paramEvent)
  {
    setDateOfEvent(paramEvent.getDate());
    this.titleEvent.setText(paramEvent.getName());
    this.tagEvent.setText(GotixUtils.populateTag(paramEvent.getTag(), this.context));
    callCenterListener(paramEvent.getCall_center_number());
    addDynamicLocation(paramEvent.getLocation());
    showSingleImagePager(paramEvent.getImage());
    hideTicketListCard();
  }
  
  public void setImageFloorPlan(String paramString)
  {
    Glide.with(getActivity()).load(paramString).asBitmap().placeholder(R.drawable.gotix_placeholder).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new SimpleTarget()
    {
      public void onResourceReady(Bitmap paramAnonymousBitmap, GlideAnimation<? super Bitmap> paramAnonymousGlideAnimation)
      {
        GotixEventDetailFragment.this.floorMap.setImageBitmap(paramAnonymousBitmap);
      }
    });
    this.baseFullScreenHelper.setPathImage(paramString);
  }
  
  public void setListenerForOpenMap(List<Location> paramList)
  {
    this.locationGroup.setOnClickListener(GotixEventDetailFragment..Lambda.3.lambdaFactory$(this, paramList));
  }
  
  public void setLowestPriceTicket(Long paramLong)
  {
    if (paramLong == null)
    {
      this.priceTicket.setText(getString(R.string.ticket_sold_out));
      this.priceTicket.setTextColor(getResources().getColor(R.color.gotix_red));
      this.priceTicket.setTypeface(Typeface.DEFAULT_BOLD);
    }
    for (;;)
    {
      TypefaceHelper.typeface(this.priceTicket, FontFaceHelper.getLatoFont());
      return;
      this.priceTicket.setTextColor(getResources().getColor(R.color.bg_base_text));
      this.priceTicket.setTypeface(Typeface.defaultFromStyle(0));
      if (isFreeEvent(paramLong))
      {
        this.priceTicket.setText(getString(R.string.event_detail_free_event));
      }
      else
      {
        textLowestPrice(R.string.lowest_ticket_format, paramLong);
        ((GotixEventDetailPresenter)getPresenter()).countLinePrice(this.priceTicket, paramLong);
      }
    }
  }
  
  public void setTextViewSpan(String paramString)
  {
    if (!paramString.isEmpty())
    {
      this.descTextEvent.setVisibility(0);
      if (this.stateExpandDescEvent)
      {
        this.descTextEvent.setText(paramString);
        fullTextDesc(getString(R.string.event_detail_view_less));
      }
      for (;;)
      {
        this.moreLessIndicator.setOnClickListener(GotixEventDetailFragment..Lambda.20.lambdaFactory$(this, paramString));
        return;
        spanTextDesc(paramString, getString(R.string.event_detail_view_more));
      }
    }
    this.descTextEvent.setVisibility(8);
    this.moreLessIndicator.setVisibility(8);
  }
  
  public void setTicketPrice(String paramString)
  {
    this.totalTicketPrice.setText(GotixUtils.getRupiahFormat(paramString));
  }
  
  public void setTicketQtySummary(int paramInt)
  {
    if (paramInt > 0)
    {
      this.ticketQtySummary.setText(String.format(this.context.getResources().getQuantityString(R.plurals.ticket_num, paramInt), new Object[] { Integer.valueOf(paramInt) }));
      return;
    }
    this.ticketQtySummary.setText("");
  }
  
  public void setWarningQtyText(int paramInt1, int paramInt2)
  {
    if (paramInt1 != 0)
    {
      ((ViewTicketHolder)this.ticketList.get(Integer.valueOf(paramInt2))).getWarningQtyTicket().setText(String.format(getString(R.string.ticket_qty_warning), new Object[] { Integer.valueOf(paramInt1) }));
      return;
    }
    ((ViewTicketHolder)this.ticketList.get(Integer.valueOf(paramInt2))).getWarningQtyTicket().setText("");
  }
  
  public void showDialogNetworkProblem()
  {
    this.parentActivity.onNetworkProblem();
  }
  
  public void showDialogWhenNoInternetConnection()
  {
    this.parentActivity.onNoInternetConnection();
  }
  
  public void showImageFloorPlan()
  {
    this.floorMap.setVisibility(0);
  }
  
  public void showMultipleImagePager(List<Image> paramList)
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.8.lambdaFactory$(this, paramList));
  }
  
  public void showProgressBarWhenPostOrder()
  {
    this.parentActivity.runOnUiThread(GotixEventDetailFragment..Lambda.26.lambdaFactory$(this));
  }
  
  public void showSingleImagePager(String paramString)
  {
    this.imageEventAdapter = new ImageEventAdapter(this.context, paramString, getActivity());
    this.eventPager.setAdapter(this.imageEventAdapter);
    hideIndicatorIfOnlyOneImage();
  }
  
  public void textLowestPrice(int paramInt, Long paramLong)
  {
    this.priceTicket.setText(String.format(getString(paramInt), new Object[] { GotixUtils.getRupiahFormat(paramLong.longValue()) }));
  }
  
  class ViewTicketHolder
  {
    ImageView addButton;
    ImageView minusButton;
    long price;
    TextView qtyTicket;
    int stock;
    LinearLayout ticketCard;
    TextView ticketClassDesc;
    TextView ticketClassName;
    TextView ticketClassPrice;
    int ticketId;
    String warningExceedMaximumMessage;
    TextView warningQtyTicket;
    
    public ViewTicketHolder(View paramView, Ticket paramTicket)
    {
      bindViewById(paramView);
      setFontFace();
      this.ticketClassName.setText(paramTicket.getName());
      this.ticketClassDesc.setText(paramTicket.getDescription());
      ticketPriceText(this.ticketClassPrice, paramTicket.getStock().intValue(), paramTicket.getPrice().toString());
      this.price = paramTicket.getPrice().longValue();
      this.ticketId = paramTicket.getTicket_id().intValue();
      this.stock = paramTicket.getStock().intValue();
    }
    
    private void bindViewById(View paramView)
    {
      this.ticketCard = ((LinearLayout)paramView.findViewById(R.id.ticket_card));
      this.ticketClassName = ((TextView)paramView.findViewById(R.id.ticket_class_name));
      this.ticketClassPrice = ((TextView)paramView.findViewById(R.id.ticket_class_price));
      this.ticketClassDesc = ((TextView)paramView.findViewById(R.id.ticket_class_desc));
      this.warningQtyTicket = ((TextView)paramView.findViewById(R.id.warning_qty_ticket));
      this.addButton = ((ImageView)paramView.findViewById(R.id.add_qty_ticket));
      this.minusButton = ((ImageView)paramView.findViewById(R.id.minus_qty_ticket));
      this.qtyTicket = ((TextView)paramView.findViewById(R.id.qty_ticket));
    }
    
    private void setFontFace()
    {
      TypefaceHelper.typeface(this.ticketCard, FontFaceHelper.getLatoFont());
    }
    
    public ImageView getAddButton()
    {
      return this.addButton;
    }
    
    public ImageView getMinusButton()
    {
      return this.minusButton;
    }
    
    public long getPrice()
    {
      return this.price;
    }
    
    public TextView getQtyTicket()
    {
      return this.qtyTicket;
    }
    
    public int getStock()
    {
      return this.stock;
    }
    
    public LinearLayout getTicketCard()
    {
      return this.ticketCard;
    }
    
    public TextView getTicketClassDesc()
    {
      return this.ticketClassDesc;
    }
    
    public TextView getTicketClassName()
    {
      return this.ticketClassName;
    }
    
    public TextView getTicketClassPrice()
    {
      return this.ticketClassPrice;
    }
    
    public int getTicketId()
    {
      return this.ticketId;
    }
    
    public String getWarningExceedMaximumMessage()
    {
      return this.warningExceedMaximumMessage;
    }
    
    public TextView getWarningQtyTicket()
    {
      return this.warningQtyTicket;
    }
    
    public void setAddButton(ImageView paramImageView)
    {
      this.addButton = paramImageView;
    }
    
    public void setMinusButton(ImageView paramImageView)
    {
      this.minusButton = paramImageView;
    }
    
    public void setPrice(long paramLong)
    {
      this.price = paramLong;
    }
    
    public void setQtyTicket(TextView paramTextView)
    {
      this.qtyTicket = paramTextView;
    }
    
    public void setStock(int paramInt)
    {
      this.stock = paramInt;
    }
    
    public void setTicketCard(LinearLayout paramLinearLayout)
    {
      this.ticketCard = paramLinearLayout;
    }
    
    public void setTicketClassDesc(TextView paramTextView)
    {
      this.ticketClassDesc = paramTextView;
    }
    
    public void setTicketClassName(TextView paramTextView)
    {
      this.ticketClassName = paramTextView;
    }
    
    public void setTicketClassPrice(TextView paramTextView)
    {
      this.ticketClassPrice = paramTextView;
    }
    
    public void setTicketId(int paramInt)
    {
      this.ticketId = paramInt;
    }
    
    public void setWarningExceedMaximumMessage(String paramString)
    {
      this.warningExceedMaximumMessage = paramString;
    }
    
    public void setWarningQtyTicket(TextView paramTextView)
    {
      this.warningQtyTicket = paramTextView;
    }
    
    public void ticketPriceText(TextView paramTextView, int paramInt, String paramString)
    {
      int i;
      if (paramInt == 0)
      {
        i = GotixEventDetailFragment.this.getResources().getColor(R.color.gotix_red);
        paramTextView.setTextColor(i);
        if (paramInt != 0) {
          break label67;
        }
      }
      label67:
      for (paramString = GotixEventDetailFragment.this.getResources().getString(R.string.ticket_sold_out);; paramString = GotixUtils.getRupiahFormat(paramString))
      {
        paramTextView.setText(paramString);
        return;
        i = GotixEventDetailFragment.this.getResources().getColor(R.color.gotix_green);
        break;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/fragments/GotixEventDetailFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */