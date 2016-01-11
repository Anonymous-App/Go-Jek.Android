package com.fasterxml.jackson.core.sym;

public final class Name2
  extends Name
{
  private final int q1;
  private final int q2;
  
  Name2(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramString, paramInt1);
    this.q1 = paramInt2;
    this.q2 = paramInt3;
  }
  
  public boolean equals(int paramInt)
  {
    return false;
  }
  
  public boolean equals(int paramInt1, int paramInt2)
  {
    return (paramInt1 == this.q1) && (paramInt2 == this.q2);
  }
  
  public boolean equals(int paramInt1, int paramInt2, int paramInt3)
  {
    return false;
  }
  
  public boolean equals(int[] paramArrayOfInt, int paramInt)
  {
    return (paramInt == 2) && (paramArrayOfInt[0] == this.q1) && (paramArrayOfInt[1] == this.q2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/sym/Name2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */