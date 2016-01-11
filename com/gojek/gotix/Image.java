package com.gojek.gotix;

public class Image
{
  private long event_id;
  private Long id;
  private Integer image_id;
  private Integer order;
  private String path;
  private String type;
  
  public Image() {}
  
  public Image(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public Image(Long paramLong, Integer paramInteger1, Integer paramInteger2, String paramString1, String paramString2, long paramLong1)
  {
    this.id = paramLong;
    this.image_id = paramInteger1;
    this.order = paramInteger2;
    this.path = paramString1;
    this.type = paramString2;
    this.event_id = paramLong1;
  }
  
  public long getEvent_id()
  {
    return this.event_id;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public Integer getImage_id()
  {
    return this.image_id;
  }
  
  public Integer getOrder()
  {
    return this.order;
  }
  
  public String getPath()
  {
    return this.path;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setEvent_id(long paramLong)
  {
    this.event_id = paramLong;
  }
  
  public void setId(Long paramLong)
  {
    this.id = paramLong;
  }
  
  public void setImage_id(Integer paramInteger)
  {
    this.image_id = paramInteger;
  }
  
  public void setOrder(Integer paramInteger)
  {
    this.order = paramInteger;
  }
  
  public void setPath(String paramString)
  {
    this.path = paramString;
  }
  
  public void setType(String paramString)
  {
    this.type = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/gojek/gotix/Image.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */