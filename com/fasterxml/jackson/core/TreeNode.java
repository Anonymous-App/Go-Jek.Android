package com.fasterxml.jackson.core;

import java.util.Iterator;

public abstract interface TreeNode
{
  public abstract JsonToken asToken();
  
  public abstract TreeNode at(JsonPointer paramJsonPointer);
  
  public abstract TreeNode at(String paramString)
    throws IllegalArgumentException;
  
  public abstract Iterator<String> fieldNames();
  
  public abstract TreeNode get(int paramInt);
  
  public abstract TreeNode get(String paramString);
  
  public abstract boolean isArray();
  
  public abstract boolean isContainerNode();
  
  public abstract boolean isMissingNode();
  
  public abstract boolean isObject();
  
  public abstract boolean isValueNode();
  
  public abstract JsonParser.NumberType numberType();
  
  public abstract TreeNode path(int paramInt);
  
  public abstract TreeNode path(String paramString);
  
  public abstract int size();
  
  public abstract JsonParser traverse();
  
  public abstract JsonParser traverse(ObjectCodec paramObjectCodec);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/TreeNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */