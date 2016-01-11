package com.google.gson;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class JsonArray
  extends JsonElement
  implements Iterable<JsonElement>
{
  private final List<JsonElement> elements = new ArrayList();
  
  public void add(JsonElement paramJsonElement)
  {
    Object localObject = paramJsonElement;
    if (paramJsonElement == null) {
      localObject = JsonNull.INSTANCE;
    }
    this.elements.add(localObject);
  }
  
  public void addAll(JsonArray paramJsonArray)
  {
    this.elements.addAll(paramJsonArray.elements);
  }
  
  public boolean contains(JsonElement paramJsonElement)
  {
    return this.elements.contains(paramJsonElement);
  }
  
  JsonArray deepCopy()
  {
    JsonArray localJsonArray = new JsonArray();
    Iterator localIterator = this.elements.iterator();
    while (localIterator.hasNext()) {
      localJsonArray.add(((JsonElement)localIterator.next()).deepCopy());
    }
    return localJsonArray;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof JsonArray)) && (((JsonArray)paramObject).elements.equals(this.elements)));
  }
  
  public JsonElement get(int paramInt)
  {
    return (JsonElement)this.elements.get(paramInt);
  }
  
  public BigDecimal getAsBigDecimal()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsBigDecimal();
    }
    throw new IllegalStateException();
  }
  
  public BigInteger getAsBigInteger()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsBigInteger();
    }
    throw new IllegalStateException();
  }
  
  public boolean getAsBoolean()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsBoolean();
    }
    throw new IllegalStateException();
  }
  
  public byte getAsByte()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsByte();
    }
    throw new IllegalStateException();
  }
  
  public char getAsCharacter()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsCharacter();
    }
    throw new IllegalStateException();
  }
  
  public double getAsDouble()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsDouble();
    }
    throw new IllegalStateException();
  }
  
  public float getAsFloat()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsFloat();
    }
    throw new IllegalStateException();
  }
  
  public int getAsInt()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsInt();
    }
    throw new IllegalStateException();
  }
  
  public long getAsLong()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsLong();
    }
    throw new IllegalStateException();
  }
  
  public Number getAsNumber()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsNumber();
    }
    throw new IllegalStateException();
  }
  
  public short getAsShort()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsShort();
    }
    throw new IllegalStateException();
  }
  
  public String getAsString()
  {
    if (this.elements.size() == 1) {
      return ((JsonElement)this.elements.get(0)).getAsString();
    }
    throw new IllegalStateException();
  }
  
  public int hashCode()
  {
    return this.elements.hashCode();
  }
  
  public Iterator<JsonElement> iterator()
  {
    return this.elements.iterator();
  }
  
  public JsonElement remove(int paramInt)
  {
    return (JsonElement)this.elements.remove(paramInt);
  }
  
  public boolean remove(JsonElement paramJsonElement)
  {
    return this.elements.remove(paramJsonElement);
  }
  
  public JsonElement set(int paramInt, JsonElement paramJsonElement)
  {
    return (JsonElement)this.elements.set(paramInt, paramJsonElement);
  }
  
  public int size()
  {
    return this.elements.size();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/gson/JsonArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */