package com.kartuku.digitalwallet.common;

import java.util.List;

public class PaginationInfo
{
  public List data;
  public Integer limit;
  public Integer page;
  public Integer total;
  
  public PaginationInfo(List paramList, Integer paramInteger1, Integer paramInteger2, Integer paramInteger3)
  {
    this.page = paramInteger1;
    this.limit = paramInteger2;
    this.total = paramInteger3;
    this.data = paramList;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/PaginationInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */