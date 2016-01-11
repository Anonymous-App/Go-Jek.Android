package com.kartuku.digitalwallet.common;

public enum ComparisonOperator
{
  private String ˊ;
  private String ˋ;
  
  static
  {
    LESS_THAN = new ComparisonOperator("LESS_THAN", 2, "<", "Less Than");
    LESS_THAN_OR_EQUAL_TO = new ComparisonOperator("LESS_THAN_OR_EQUAL_TO", 3, "<=", "Less Than or Equal To");
  }
  
  private ComparisonOperator(String paramString1, String paramString2)
  {
    this.ˊ = paramString1;
    this.ˋ = paramString2;
  }
  
  public final String getCode()
  {
    return this.ˊ;
  }
  
  public final String getName()
  {
    return this.ˋ;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/ComparisonOperator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */