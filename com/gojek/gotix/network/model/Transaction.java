package com.gojek.gotix.network.model;

import java.util.List;

public class Transaction
{
  private String credit_card_surcharge;
  private int event_id;
  private long order_date;
  private int order_id;
  private List<PaymentComponent> payment_component;
  private List<PaymentProvider> payment_provider;
  private List<TransactionTicket> tickets;
  private long timeout_period;
  private String total_price;
  private String total_price_credit_card;
  
  public Transaction() {}
  
  public Transaction(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, long paramLong1, long paramLong2, List<TransactionTicket> paramList, List<PaymentComponent> paramList1, List<PaymentProvider> paramList2)
  {
    this.order_id = paramInt1;
    this.event_id = paramInt2;
    this.credit_card_surcharge = paramString1;
    this.total_price_credit_card = paramString2;
    this.total_price = paramString3;
    this.order_date = paramLong1;
    this.timeout_period = paramLong2;
    this.tickets = paramList;
    this.payment_component = paramList1;
    this.payment_provider = paramList2;
  }
  
  public String getCredit_card_surcharge()
  {
    return this.credit_card_surcharge;
  }
  
  public int getEvent_id()
  {
    return this.event_id;
  }
  
  public long getOrder_date()
  {
    return this.order_date;
  }
  
  public int getOrder_id()
  {
    return this.order_id;
  }
  
  public List<PaymentComponent> getPayment_component()
  {
    return this.payment_component;
  }
  
  public List<PaymentProvider> getPayment_provider()
  {
    return this.payment_provider;
  }
  
  public List<TransactionTicket> getTickets()
  {
    return this.tickets;
  }
  
  public long getTimeout_period()
  {
    return this.timeout_period;
  }
  
  public String getTotal_price()
  {
    return this.total_price;
  }
  
  public String getTotal_price_credit_card()
  {
    return this.total_price_credit_card;
  }
  
  public void setCredit_card_surcharge(String paramString)
  {
    this.credit_card_surcharge = paramString;
  }
  
  public void setEvent_id(int paramInt)
  {
    this.event_id = paramInt;
  }
  
  public void setOrder_date(long paramLong)
  {
    this.order_date = paramLong;
  }
  
  public void setOrder_id(int paramInt)
  {
    this.order_id = paramInt;
  }
  
  public void setPayment_component(List<PaymentComponent> paramList)
  {
    this.payment_component = paramList;
  }
  
  public void setPayment_provider(List<PaymentProvider> paramList)
  {
    this.payment_provider = paramList;
  }
  
  public void setTickets(List<TransactionTicket> paramList)
  {
    this.tickets = paramList;
  }
  
  public void setTimeout_period(long paramLong)
  {
    this.timeout_period = paramLong;
  }
  
  public void setTotal_price(String paramString)
  {
    this.total_price = paramString;
  }
  
  public void setTotal_price_credit_card(String paramString)
  {
    this.total_price_credit_card = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/network/model/Transaction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */