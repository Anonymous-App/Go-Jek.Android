package com.gojek.gotix.presenter;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.gojek.gotix.Event;
import com.gojek.gotix.Image;
import com.gojek.gotix.Location;
import com.gojek.gotix.R.string;
import com.gojek.gotix.Schedule;
import com.gojek.gotix.Ticket;
import com.gojek.gotix.fragments.GotixEventDetailFragment;
import com.gojek.gotix.helper.GotixData;
import com.gojek.gotix.network.GotixNetworkManager;
import com.gojek.gotix.network.model.Attendee;
import com.gojek.gotix.network.model.TicketItem;
import com.gojek.gotix.network.model.TicketPurchased;
import com.gojek.gotix.tools.GotixUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import lib.gojek.base.helper.BasePreferences;
import lib.gojek.base.networks.GojekNetworkManager;
import retrofit.client.Response;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GotixEventDetailPresenter
  extends GotixBasePresenter<GotixEventDetailFragment>
{
  private static final String EMPTY_STRING = "";
  private static final long FREE_EVENT_PRICE = 0L;
  private static final int LINE_COUNT = 1;
  private static final long LOWEST_PRICE_FROM_EVENT = -1L;
  private static final int MIN_LINE = 10;
  private static final int MIN_LINE_PRICE = 1;
  private static final String MISSMATCH_STRING = "N/A";
  private static final String TAG = GotixEventDetailPresenter.class.getSimpleName();
  private static final String ZERO_STRING = "0";
  private int MAX_TICKET = 5;
  private int availableTicket;
  private int checkSoldOut = 0;
  private Event event;
  private Observable<Event> eventObservable;
  private GojekNetworkManager gojekNetworkManager;
  private GotixNetworkManager gotixNetworkManager;
  private List<Image> imageList;
  private int quatityTicket;
  private int ticketId;
  private List<TicketItem> ticketList;
  private TicketPurchased ticketPurchased;
  private List<Long> tickets;
  private long totalPrice;
  private Map<Integer, Integer> totalTicket;
  
  private Long addTicketsPriceIntoList(List<Schedule> paramList)
  {
    this.checkSoldOut = 0;
    if (!paramList.isEmpty())
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Object localObject = (Schedule)paramList.next();
        if ((((Schedule)localObject).getTickets() != null) && (!((Schedule)localObject).getTickets().isEmpty()))
        {
          localObject = ((Schedule)localObject).getTickets().iterator();
          while (((Iterator)localObject).hasNext())
          {
            Ticket localTicket = (Ticket)((Iterator)localObject).next();
            this.tickets.add(localTicket.getPrice());
            if (localTicket.getStock().intValue() == 0) {
              this.checkSoldOut += 1;
            }
          }
        }
      }
      return getLowestPriceEventDetail(this.tickets);
    }
    return Long.valueOf(-1L);
  }
  
  private void clearTicketList()
  {
    if (this.ticketList != null) {
      this.ticketList.clear();
    }
  }
  
  private Observable<Integer> countLineTextView(final TextView paramTextView)
  {
    Observable.create(new Observable.OnSubscribe()
    {
      public void call(Subscriber<? super Integer> paramAnonymousSubscriber)
      {
        paramTextView.post(new GotixEventDetailPresenter.1.1(this, paramAnonymousSubscriber));
      }
    });
  }
  
  private Attendee createAttendee()
  {
    String[] arrayOfString = GotixUtils.splitName(BasePreferences.getCustomerName());
    return new Attendee(arrayOfString[0], arrayOfString[1], BasePreferences.getCustomerEmail(), BasePreferences.getCustomerPhone());
  }
  
  private TicketItem createTicketItem(int paramInt1, int paramInt2)
  {
    return new TicketItem(paramInt1, paramInt2);
  }
  
  private void doWhenLocationDataLoaded(GotixEventDetailFragment paramGotixEventDetailFragment, List<Location> paramList)
  {
    final StringBuilder localStringBuilder = new StringBuilder();
    if (isViewExists())
    {
      paramGotixEventDetailFragment.setListenerForOpenMap(paramList);
      Observable.from(paramList).subscribe(new Subscriber()
      {
        public void onCompleted()
        {
          localStringBuilder.setLength(localStringBuilder.length() - 1);
          ((GotixEventDetailFragment)GotixEventDetailPresenter.this.getView()).addDynamicLocation(localStringBuilder.toString());
        }
        
        public void onError(Throwable paramAnonymousThrowable) {}
        
        public void onNext(Location paramAnonymousLocation)
        {
          localStringBuilder.append(String.format(GotixEventDetailPresenter.this.getContext().getString(R.string.location_format), new Object[] { paramAnonymousLocation.getVenue_name(), paramAnonymousLocation.getProvince() }));
        }
      });
    }
  }
  
  private void getLocation(int paramInt)
  {
    this.eventObservable.flatMap(GotixEventDetailPresenter..Lambda.26.lambdaFactory$()).filter(GotixEventDetailPresenter..Lambda.27.lambdaFactory$(paramInt)).subscribe(GotixEventDetailPresenter..Lambda.28.lambdaFactory$(this));
  }
  
  private int getLocationId(List<Schedule> paramList)
  {
    int i = -1;
    int j = getScheduleTakeMeThere(paramList);
    if (getScheduleTakeMeThere(paramList) != -1) {
      i = ((Schedule)paramList.get(j)).getLocation_id().intValue();
    }
    return i;
  }
  
  private Long getLowestPriceEventDetail(List<Long> paramList)
  {
    if (!paramList.isEmpty()) {
      return (Long)Stream.of(paramList).min(GotixEventDetailPresenter..Lambda.18.lambdaFactory$()).get();
    }
    return Long.valueOf(0L);
  }
  
  private void getMainDataFromEvent()
  {
    this.eventObservable.compose(deliverLatestCache()).subscribe(GotixEventDetailPresenter..Lambda.6.lambdaFactory$(this));
  }
  
  private int getScheduleTakeMeThere(List<Schedule> paramList)
  {
    int i = 0;
    while (i < paramList.size())
    {
      if (GotixUtils.getEnabledBtnTakeMeThere(((Schedule)paramList.get(i)).getStart_time_at().longValue(), ((Schedule)paramList.get(i)).getFinish_time_at().longValue())) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  private void getTicketEvent(Event paramEvent)
  {
    this.tickets.clear();
    Observable.just(paramEvent.getSchedules()).map(GotixEventDetailPresenter..Lambda.9.lambdaFactory$(this)).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixEventDetailPresenter..Lambda.10.lambdaFactory$(this), GotixEventDetailPresenter..Lambda.11.lambdaFactory$());
  }
  
  private TicketPurchased getTicketPurchased(int paramInt1, int paramInt2, List<TicketItem> paramList)
  {
    return new TicketPurchased(paramInt1, paramInt2, paramList, createAttendee());
  }
  
  private boolean isTicketAvailable(int paramInt1, int paramInt2)
  {
    return (paramInt1 < paramInt2) && (paramInt1 < this.MAX_TICKET) && (this.availableTicket > 0);
  }
  
  private void postDataTransaction(TicketPurchased paramTicketPurchased)
  {
    this.gotixNetworkManager.postOrderOfEvent(paramTicketPurchased).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixEventDetailPresenter..Lambda.40.lambdaFactory$(this, paramTicketPurchased), GotixEventDetailPresenter..Lambda.41.lambdaFactory$(this));
  }
  
  private void setLowestPriceHasValue(Long paramLong)
  {
    String str = this.event.getLowest_available_price();
    if (-1L != paramLong.longValue())
    {
      ((GotixEventDetailFragment)getView()).setLowestPriceTicket(paramLong);
      return;
    }
    if ((str == null) || (str.equals("N/A")) || (str.equals("")))
    {
      ((GotixEventDetailFragment)getView()).setLowestPriceTicket(Long.valueOf(Long.parseLong("0")));
      return;
    }
    ((GotixEventDetailFragment)getView()).setLowestPriceTicket(Long.valueOf(Long.parseLong(str)));
  }
  
  private String sortedEventDate(List<Schedule> paramList)
  {
    if (!paramList.isEmpty())
    {
      ArrayList localArrayList1 = new ArrayList(paramList.size());
      ArrayList localArrayList2 = new ArrayList(paramList.size());
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Schedule localSchedule = (Schedule)paramList.next();
        localArrayList1.add(localSchedule.getStart_time_at());
        localArrayList2.add(localSchedule.getFinish_time_at());
      }
      Collections.sort(localArrayList1);
      Collections.sort(localArrayList2);
      return String.format(getContext().getString(R.string.date_time_format), new Object[] { GotixUtils.getDateFromTimestamp(((Long)localArrayList1.get(0)).longValue()), GotixUtils.getDateFromTimestamp(((Long)localArrayList2.get(localArrayList2.size() - 1)).longValue()) });
    }
    return "";
  }
  
  public void addToListWhenOrderTicket()
  {
    if (this.totalTicket.containsKey(Integer.valueOf(this.ticketId)))
    {
      this.quatityTicket = ((Integer)this.totalTicket.get(Integer.valueOf(this.ticketId))).intValue();
      if (this.quatityTicket > 0) {
        this.ticketList.add(createTicketItem(this.ticketId, this.quatityTicket));
      }
    }
  }
  
  public void countLineDesc(TextView paramTextView, String paramString)
  {
    countLineTextView(paramTextView).map(GotixEventDetailPresenter..Lambda.15.lambdaFactory$()).distinctUntilChanged().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixEventDetailPresenter..Lambda.16.lambdaFactory$(this, paramString), GotixEventDetailPresenter..Lambda.17.lambdaFactory$());
  }
  
  public void countLinePrice(TextView paramTextView, Long paramLong)
  {
    countLineTextView(paramTextView).map(GotixEventDetailPresenter..Lambda.12.lambdaFactory$()).distinctUntilChanged().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(GotixEventDetailPresenter..Lambda.13.lambdaFactory$(this, paramLong), GotixEventDetailPresenter..Lambda.14.lambdaFactory$());
  }
  
  public void displayWarningMessageTicket(int paramInt1, int paramInt2, int paramInt3)
  {
    if (isViewExists())
    {
      if (isTicketAvailable(paramInt1, paramInt2)) {
        ((GotixEventDetailFragment)getView()).setWarningQtyText(0, paramInt3);
      }
    }
    else {
      return;
    }
    ((GotixEventDetailFragment)getView()).setWarningQtyText(paramInt1, paramInt3);
  }
  
  public void doWhenCompleteOrder(int paramInt)
  {
    if (this.ticketList.size() > 0)
    {
      this.ticketPurchased = getTicketPurchased(paramInt, BasePreferences.getCustomerId(), this.ticketList);
      postDataTransaction(this.ticketPurchased);
    }
  }
  
  public void filterImageCover(List<Image> paramList, final GotixEventDetailFragment paramGotixEventDetailFragment)
  {
    this.imageList.clear();
    Observable.from(paramList).filter(GotixEventDetailPresenter..Lambda.34.lambdaFactory$(this)).subscribe(new Subscriber()
    {
      public void onCompleted()
      {
        if (GotixEventDetailPresenter.this.isViewExists())
        {
          if (GotixEventDetailPresenter.this.imageList.size() <= 1) {
            paramGotixEventDetailFragment.showSingleImagePager(((Image)GotixEventDetailPresenter.this.imageList.get(0)).getPath());
          }
        }
        else {
          return;
        }
        paramGotixEventDetailFragment.showMultipleImagePager(GotixEventDetailPresenter.this.imageList);
      }
      
      public void onError(Throwable paramAnonymousThrowable) {}
      
      public void onNext(Image paramAnonymousImage)
      {
        GotixEventDetailPresenter.this.imageList.add(paramAnonymousImage);
      }
    });
  }
  
  public void findImageFloorPlan()
  {
    this.eventObservable.flatMap(GotixEventDetailPresenter..Lambda.36.lambdaFactory$()).filter(GotixEventDetailPresenter..Lambda.37.lambdaFactory$(this)).map(GotixEventDetailPresenter..Lambda.38.lambdaFactory$()).compose(deliverLatestCache()).subscribe(GotixEventDetailPresenter..Lambda.39.lambdaFactory$());
  }
  
  public int getAvailableTicket()
  {
    return this.availableTicket;
  }
  
  public void getEnableTakeMeThere()
  {
    this.eventObservable.map(GotixEventDetailPresenter..Lambda.29.lambdaFactory$()).map(GotixEventDetailPresenter..Lambda.30.lambdaFactory$(this)).subscribe(GotixEventDetailPresenter..Lambda.31.lambdaFactory$(this));
  }
  
  public void getEventDateFromEvent()
  {
    this.eventObservable.map(GotixEventDetailPresenter..Lambda.19.lambdaFactory$()).map(GotixEventDetailPresenter..Lambda.20.lambdaFactory$(this)).flatMap(GotixEventDetailPresenter..Lambda.21.lambdaFactory$()).compose(deliverLatestCache()).subscribe(GotixEventDetailPresenter..Lambda.22.lambdaFactory$());
  }
  
  public void getEventDetail(int paramInt)
  {
    getEventDetailFromCache(paramInt);
    getEventDetailFromNetwork(paramInt);
  }
  
  public void getEventDetailFromCache(int paramInt)
  {
    Observable.just(GotixData.getEventData(paramInt)).compose(deliverLatestCache()).subscribe(GotixEventDetailPresenter..Lambda.1.lambdaFactory$(this, paramInt), GotixEventDetailPresenter..Lambda.4.lambdaFactory$(this, paramInt));
  }
  
  public void getEventDetailFromNetwork(int paramInt)
  {
    this.eventObservable = this.gotixNetworkManager.getEventDetail(paramInt).cache().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).map(GotixEventDetailPresenter..Lambda.5.lambdaFactory$(this));
    getEventDateFromEvent();
    getLocationFromEvent();
    getMainDataFromEvent();
    findImageFloorPlan();
  }
  
  public void getImageCover(List<Image> paramList, GotixEventDetailFragment paramGotixEventDetailFragment)
  {
    isImageExists().subscribe(GotixEventDetailPresenter..Lambda.32.lambdaFactory$(this, paramList, paramGotixEventDetailFragment), GotixEventDetailPresenter..Lambda.33.lambdaFactory$());
  }
  
  public void getLocationFromEvent()
  {
    this.eventObservable.map(GotixEventDetailPresenter..Lambda.7.lambdaFactory$()).compose(deliverLatestCache()).subscribe(GotixEventDetailPresenter..Lambda.8.lambdaFactory$(this));
  }
  
  public void getLocationTakeMeThere()
  {
    this.eventObservable.map(GotixEventDetailPresenter..Lambda.23.lambdaFactory$()).map(GotixEventDetailPresenter..Lambda.24.lambdaFactory$(this)).subscribe(GotixEventDetailPresenter..Lambda.25.lambdaFactory$(this));
  }
  
  public int getMaxTicket()
  {
    return this.MAX_TICKET;
  }
  
  public boolean getTimeScheduleToday(List<Schedule> paramList)
  {
    boolean bool = false;
    if (!paramList.isEmpty())
    {
      ArrayList localArrayList1 = new ArrayList(paramList.size());
      ArrayList localArrayList2 = new ArrayList(paramList.size());
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Schedule localSchedule = (Schedule)paramList.next();
        localArrayList1.add(localSchedule.getStart_time_at());
        localArrayList2.add(localSchedule.getFinish_time_at());
      }
      Collections.sort(localArrayList1);
      Collections.sort(localArrayList2);
      bool = GotixUtils.getEnabledBtnTakeMeThere(((Long)localArrayList1.get(0)).longValue(), ((Long)localArrayList2.get(localArrayList2.size() - 1)).longValue());
    }
    return bool;
  }
  
  public Map getTotalTicket()
  {
    return this.totalTicket;
  }
  
  public boolean isAddButtonEnable(int paramInt1, int paramInt2)
  {
    return (this.availableTicket > 0) && (paramInt1 < paramInt2);
  }
  
  public Observable<Boolean> isImageExists()
  {
    return this.eventObservable.map(GotixEventDetailPresenter..Lambda.35.lambdaFactory$());
  }
  
  public boolean isMinusButtonEnable(int paramInt)
  {
    return (this.availableTicket <= this.MAX_TICKET) && (paramInt > 0);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.gotixNetworkManager = new GotixNetworkManager(getContext(), this);
    this.gojekNetworkManager = new GojekNetworkManager(getContext(), this);
    this.totalTicket = new HashMap();
    this.ticketList = new ArrayList();
    this.tickets = new ArrayList();
    this.imageList = new ArrayList();
  }
  
  public void onFailedToProcessRequest(Response paramResponse)
  {
    if (isViewExists())
    {
      if (paramResponse.getStatus() != 409) {
        break label28;
      }
      ((GotixEventDetailFragment)getView()).onOutOfStock();
    }
    label28:
    while (paramResponse.getStatus() != 422) {
      return;
    }
    ((GotixEventDetailFragment)getView()).failedOrder();
  }
  
  public void onNetworkProblem()
  {
    if (isViewExists()) {
      ((GotixEventDetailFragment)getView()).showDialogNetworkProblem();
    }
  }
  
  public void onNoInternetConnection()
  {
    if (isViewExists()) {
      ((GotixEventDetailFragment)getView()).showDialogWhenNoInternetConnection();
    }
  }
  
  public void onRequestEnd() {}
  
  public void onRequesting() {}
  
  public void postTicketPurchase(final int paramInt)
  {
    this.eventObservable.flatMap(GotixEventDetailPresenter..Lambda.42.lambdaFactory$()).flatMap(GotixEventDetailPresenter..Lambda.43.lambdaFactory$()).subscribe(new Subscriber()
    {
      public void onCompleted()
      {
        GotixEventDetailPresenter.this.doWhenCompleteOrder(paramInt);
      }
      
      public void onError(Throwable paramAnonymousThrowable)
      {
        if (GotixEventDetailPresenter.this.isViewExists())
        {
          ((GotixEventDetailFragment)GotixEventDetailPresenter.this.getView()).showDialogNetworkProblem();
          ((GotixEventDetailFragment)GotixEventDetailPresenter.this.getView()).hideProgressBarWhenPostOrder();
        }
      }
      
      public void onNext(Ticket paramAnonymousTicket)
      {
        GotixEventDetailPresenter.access$102(GotixEventDetailPresenter.this, paramAnonymousTicket.getTicket_id().intValue());
        GotixEventDetailPresenter.this.addToListWhenOrderTicket();
      }
    });
  }
  
  public void resetAvailableTicket()
  {
    this.availableTicket = this.MAX_TICKET;
  }
  
  public void resetTotalPrice()
  {
    this.totalPrice = 0L;
  }
  
  public void setTotalTicket(int paramInt1, int paramInt2, long paramLong, String paramString, int paramInt3, int paramInt4)
  {
    this.totalTicket.put(Integer.valueOf(paramInt1), Integer.valueOf(paramInt2));
    if (paramString.equals("add")) {
      if ((this.availableTicket > 0) && (paramInt2 <= paramInt3))
      {
        this.availableTicket -= paramInt4;
        this.totalPrice += paramInt4 * paramLong;
      }
    }
    for (;;)
    {
      if (isViewExists())
      {
        ((GotixEventDetailFragment)getView()).setTicketQtySummary(this.MAX_TICKET - this.availableTicket);
        ((GotixEventDetailFragment)getView()).setTicketPrice(this.totalPrice + "");
      }
      return;
      ((GotixEventDetailFragment)getView()).disableAddButton(paramInt1);
      continue;
      if (this.availableTicket <= this.MAX_TICKET)
      {
        this.availableTicket += paramInt4;
        this.totalPrice -= paramInt4 * paramLong;
      }
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/presenter/GotixEventDetailPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */