package com.kartuku.digitalwallet.common;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class ServicePageable
{
  public static String SORT_ASCENDING = "asc";
  public static String SORT_DESCENDING = "desc";
  @Min(message="The minimum value of page should be a positive number and more than 0.", value=0L)
  private int ˊ;
  @Max(message="The maximum value of limit should be less than or equal 100.", value=100L)
  private int ˋ;
  private String ˎ;
  private String ˏ;
  
  public String getDir()
  {
    return this.ˏ;
  }
  
  public int getLimit()
  {
    return this.ˋ;
  }
  
  public int getPage()
  {
    return this.ˊ;
  }
  
  public Pageable getPageable()
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (this.ˎ != null)
    {
      localObject1 = localObject2;
      if (!this.ˎ.isEmpty())
      {
        if (this.ˏ == null) {
          this.ˏ = SORT_ASCENDING;
        }
        if (!this.ˏ.equalsIgnoreCase(SORT_ASCENDING)) {
          break label96;
        }
        localObject1 = new Sort(new Sort.Order[] { new Sort.Order(Sort.Direction.ASC, this.ˎ) });
      }
    }
    for (;;)
    {
      return new PageRequest(this.ˊ, this.ˋ, (Sort)localObject1);
      label96:
      localObject1 = localObject2;
      if (this.ˏ.equalsIgnoreCase(SORT_DESCENDING)) {
        localObject1 = new Sort(new Sort.Order[] { new Sort.Order(Sort.Direction.DESC, this.ˎ) });
      }
    }
  }
  
  public String getSort()
  {
    return this.ˎ;
  }
  
  public void setDefaultSort(String paramString1, String paramString2)
  {
    if ((this.ˎ == null) || (this.ˎ.isEmpty()))
    {
      this.ˎ = paramString1;
      this.ˏ = paramString2;
    }
  }
  
  public void setDir(String paramString)
  {
    this.ˏ = paramString;
  }
  
  public void setLimit(int paramInt)
  {
    this.ˋ = paramInt;
  }
  
  public void setPage(int paramInt)
  {
    this.ˊ = paramInt;
  }
  
  public void setSort(String paramString)
  {
    this.ˎ = paramString;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/ServicePageable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */