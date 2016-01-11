package com.fasterxml.jackson.core.sym;

public final class Name1
  extends Name
{
  private static final Name1 EMPTY = new Name1("", 0, 0);
  private final int q;
  
  Name1(String paramString, int paramInt1, int paramInt2)
  {
    super(paramString, paramInt1);
    this.q = paramInt2;
  }
  
  public static Name1 getEmptyName()
  {
    return EMPTY;
  }
  
  public boolean equals(int paramInt)
  {
    return paramInt == this.q;
  }
  
  public boolean equals(int paramInt1, int paramInt2)
  {
    return (paramInt1 == this.q) && (paramInt2 == 0);
  }
  
  public boolean equals(int paramInt1, int paramInt2, int paramInt3)
  {
    return false;
  }
  
  public boolean equals(int[] paramArrayOfInt, int paramInt)
  {
    return (paramInt == 1) && (paramArrayOfInt[0] == this.q);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/sym/Name1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */