package com.fasterxml.jackson.databind;

import java.io.Serializable;

public class PropertyMetadata
  implements Serializable
{
  public static final PropertyMetadata STD_OPTIONAL = new PropertyMetadata(Boolean.FALSE, null, null, null);
  public static final PropertyMetadata STD_REQUIRED = new PropertyMetadata(Boolean.TRUE, null, null, null);
  public static final PropertyMetadata STD_REQUIRED_OR_OPTIONAL = new PropertyMetadata(null, null, null, null);
  private static final long serialVersionUID = -1L;
  protected final String _defaultValue;
  protected final String _description;
  protected final Integer _index;
  protected final Boolean _required;
  
  @Deprecated
  protected PropertyMetadata(Boolean paramBoolean, String paramString)
  {
    this(paramBoolean, paramString, null, null);
  }
  
  protected PropertyMetadata(Boolean paramBoolean, String paramString1, Integer paramInteger, String paramString2)
  {
    this._required = paramBoolean;
    this._description = paramString1;
    this._index = paramInteger;
    if (paramString2 != null)
    {
      paramBoolean = paramString2;
      if (!paramString2.isEmpty()) {}
    }
    else
    {
      paramBoolean = null;
    }
    this._defaultValue = paramBoolean;
  }
  
  @Deprecated
  public static PropertyMetadata construct(boolean paramBoolean, String paramString)
  {
    return construct(paramBoolean, paramString, null, null);
  }
  
  public static PropertyMetadata construct(boolean paramBoolean, String paramString1, Integer paramInteger, String paramString2)
  {
    if ((paramString1 != null) || (paramInteger != null) || (paramString2 != null)) {
      return new PropertyMetadata(Boolean.valueOf(paramBoolean), paramString1, paramInteger, paramString2);
    }
    if (paramBoolean) {
      return STD_REQUIRED;
    }
    return STD_OPTIONAL;
  }
  
  public String getDefaultValue()
  {
    return this._defaultValue;
  }
  
  public String getDescription()
  {
    return this._description;
  }
  
  public Integer getIndex()
  {
    return this._index;
  }
  
  public Boolean getRequired()
  {
    return this._required;
  }
  
  public boolean hasDefaultValue()
  {
    return this._defaultValue != null;
  }
  
  @Deprecated
  public boolean hasDefuaultValue()
  {
    return hasDefaultValue();
  }
  
  public boolean hasIndex()
  {
    return this._index != null;
  }
  
  public boolean isRequired()
  {
    return (this._required != null) && (this._required.booleanValue());
  }
  
  protected Object readResolve()
  {
    if ((this._description == null) && (this._index == null) && (this._defaultValue == null))
    {
      if (this._required == null) {
        return STD_REQUIRED_OR_OPTIONAL;
      }
      if (this._required.booleanValue()) {
        return STD_REQUIRED;
      }
      return STD_OPTIONAL;
    }
    return this;
  }
  
  public PropertyMetadata withDefaultValue(String paramString)
  {
    String str;
    if ((paramString == null) || (paramString.isEmpty()))
    {
      if (this._defaultValue == null) {
        return this;
      }
      str = null;
    }
    do
    {
      return new PropertyMetadata(this._required, this._description, this._index, str);
      str = paramString;
    } while (!this._defaultValue.equals(paramString));
    return this;
  }
  
  public PropertyMetadata withDescription(String paramString)
  {
    return new PropertyMetadata(this._required, paramString, this._index, this._defaultValue);
  }
  
  public PropertyMetadata withIndex(Integer paramInteger)
  {
    return new PropertyMetadata(this._required, this._description, paramInteger, this._defaultValue);
  }
  
  public PropertyMetadata withRequired(Boolean paramBoolean)
  {
    if (paramBoolean == null)
    {
      if (this._required != null) {}
    }
    else {
      while ((this._required != null) && (this._required.booleanValue() == paramBoolean.booleanValue())) {
        return this;
      }
    }
    return new PropertyMetadata(paramBoolean, this._description, this._index, this._defaultValue);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/databind/PropertyMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */