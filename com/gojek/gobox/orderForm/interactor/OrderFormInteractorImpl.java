package com.gojek.gobox.orderForm.interactor;

import com.gojek.gobox.model.CargoType;
import com.gojek.gobox.model.CustomerResponse;
import com.gojek.gobox.model.EstimationRequestBody;
import com.gojek.gobox.model.EstimationResponse;
import com.gojek.gobox.model.Location;
import com.gojek.gobox.model.VehicleTypeResponse;
import com.gojek.gobox.networking.NetworkService;
import com.gojek.gobox.orderForm.component.OnCalculateEstimationListener;
import java.util.ArrayList;
import java.util.Iterator;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.subjects.PublishSubject;

public class OrderFormInteractorImpl
  implements OrderFormInteractor, Observer<EstimationRequestBody>
{
  private final int DESTINATION_FIRST_INDEX = 1;
  private final int ORIGIN_INDEX = 0;
  private PublishSubject<Integer> additionalShipperSubject;
  private Observable<ArrayList<Location>> allLocationObservable;
  private PublishSubject<CargoType> cargoTypeSubject;
  private PublishSubject<ArrayList<Location>> destinationSubject;
  private Observable<EstimationRequestBody> estimationRequestBodyObservable;
  private int mAdditionalShipperNumber;
  private CargoType mCargoType;
  private ArrayList<Location> mDestinationLocationList;
  private EstimationResponse mEstimationResult;
  private EstimationRequestBody mLatestEstimationRequestBody;
  private Observable<NetworkService> mNetworkServiceObservable;
  private OnCalculateEstimationListener mOnCalculateEstimationListener;
  private Location mOriginLocation;
  private PublishSubject<Location> originSubject;
  
  public OrderFormInteractorImpl(Observable<NetworkService> paramObservable)
  {
    this.mNetworkServiceObservable = paramObservable;
    this.mDestinationLocationList = new ArrayList();
    this.mOriginLocation = new Location();
    this.originSubject = PublishSubject.create();
    this.destinationSubject = PublishSubject.create();
    this.cargoTypeSubject = PublishSubject.create();
    this.additionalShipperSubject = PublishSubject.create();
    this.allLocationObservable = Observable.combineLatest(this.originSubject, this.destinationSubject, OrderFormInteractorImpl..Lambda.1.lambdaFactory$());
    this.estimationRequestBodyObservable = Observable.combineLatest(this.allLocationObservable, this.cargoTypeSubject, this.additionalShipperSubject, OrderFormInteractorImpl..Lambda.2.lambdaFactory$(this));
    this.estimationRequestBodyObservable.subscribe(this);
  }
  
  private boolean isEmptyLocation(Location paramLocation)
  {
    return (paramLocation.getLat() != null) && (paramLocation.getLongitude() != null);
  }
  
  private ArrayList<Location> removeEmptyLocation(ArrayList<Location> paramArrayList)
  {
    paramArrayList = new ArrayList();
    Iterator localIterator = this.mDestinationLocationList.iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      Location localLocation = (Location)localIterator.next();
      if (!isEmptyLocation(localLocation)) {
        break label69;
      }
      localLocation.setOrder(i);
      paramArrayList.add(localLocation);
      i += 1;
    }
    label69:
    for (;;)
    {
      break;
      return paramArrayList;
    }
  }
  
  public void addAdditionalShipper(int paramInt)
  {
    this.mAdditionalShipperNumber = paramInt;
    this.additionalShipperSubject.onNext(Integer.valueOf(paramInt));
  }
  
  public void addDestinationLocationDetails(int paramInt, double paramDouble1, double paramDouble2, String paramString)
  {
    Location localLocation = (Location)this.mDestinationLocationList.get(paramInt);
    localLocation.setLatitude(paramDouble1);
    localLocation.setLongitude(paramDouble2);
    localLocation.setAddress(paramString);
    localLocation.setOrder(paramInt);
    this.destinationSubject.onNext(removeEmptyLocation(this.mDestinationLocationList));
  }
  
  public int addNewDestination()
  {
    this.mDestinationLocationList.add(this.mDestinationLocationList.size(), new Location());
    return this.mDestinationLocationList.size() - 1;
  }
  
  public void addOriginDetails(double paramDouble1, double paramDouble2, String paramString)
  {
    this.mOriginLocation.setLatitude(paramDouble1);
    this.mOriginLocation.setLongitude(paramDouble2);
    this.mOriginLocation.setAddress(paramString);
    this.originSubject.onNext(this.mOriginLocation);
  }
  
  public void deleteDestination(int paramInt)
  {
    this.mDestinationLocationList.remove(paramInt);
    ArrayList localArrayList = removeEmptyLocation(this.mDestinationLocationList);
    if (localArrayList.size() >= 1)
    {
      this.destinationSubject.onNext(localArrayList);
      return;
    }
    this.mOnCalculateEstimationListener.onNoDestinationPicked();
  }
  
  public void fetchAllVehicle(Subscriber<VehicleTypeResponse> paramSubscriber, long paramLong, double paramDouble1, double paramDouble2)
  {
    this.mNetworkServiceObservable.subscribe(OrderFormInteractorImpl..Lambda.3.lambdaFactory$(paramLong, paramDouble1, paramDouble2, paramSubscriber));
  }
  
  public void fetchEstimationPrice()
  {
    this.mNetworkServiceObservable.subscribe(OrderFormInteractorImpl..Lambda.5.lambdaFactory$(this));
    this.mOnCalculateEstimationListener.onCalculating();
  }
  
  public void fetchGojekCredit(Subscriber<CustomerResponse> paramSubscriber)
  {
    this.mNetworkServiceObservable.subscribe(OrderFormInteractorImpl..Lambda.4.lambdaFactory$(paramSubscriber));
  }
  
  public long getCargoId()
  {
    return this.mCargoType.getId();
  }
  
  public ArrayList<Location> getDestinationLocationData()
  {
    return removeEmptyLocation(this.mDestinationLocationList);
  }
  
  public EstimationResponse getEstimationData()
  {
    return this.mEstimationResult;
  }
  
  public Location getOriginLocationData()
  {
    return this.mOriginLocation;
  }
  
  public void onCompleted() {}
  
  public void onError(Throwable paramThrowable)
  {
    this.mOnCalculateEstimationListener.onCalculatingError(paramThrowable);
  }
  
  public void onNext(EstimationRequestBody paramEstimationRequestBody)
  {
    this.mLatestEstimationRequestBody = paramEstimationRequestBody;
    fetchEstimationPrice();
  }
  
  public void setOnCalculateEstimationListener(OnCalculateEstimationListener paramOnCalculateEstimationListener)
  {
    this.mOnCalculateEstimationListener = paramOnCalculateEstimationListener;
  }
  
  public void setSelectedCargoType(CargoType paramCargoType)
  {
    this.mCargoType = paramCargoType;
    this.cargoTypeSubject.onNext(paramCargoType);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gobox/orderForm/interactor/OrderFormInteractorImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */