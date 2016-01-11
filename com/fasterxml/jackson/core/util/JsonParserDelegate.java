package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonParserDelegate
  extends JsonParser
{
  protected JsonParser delegate;
  
  public JsonParserDelegate(JsonParser paramJsonParser)
  {
    this.delegate = paramJsonParser;
  }
  
  public boolean canReadObjectId()
  {
    return this.delegate.canReadObjectId();
  }
  
  public boolean canReadTypeId()
  {
    return this.delegate.canReadTypeId();
  }
  
  public boolean canUseSchema(FormatSchema paramFormatSchema)
  {
    return this.delegate.canUseSchema(paramFormatSchema);
  }
  
  public void clearCurrentToken()
  {
    this.delegate.clearCurrentToken();
  }
  
  public void close()
    throws IOException
  {
    this.delegate.close();
  }
  
  public JsonParser disable(JsonParser.Feature paramFeature)
  {
    this.delegate.disable(paramFeature);
    return this;
  }
  
  public JsonParser enable(JsonParser.Feature paramFeature)
  {
    this.delegate.enable(paramFeature);
    return this;
  }
  
  public BigInteger getBigIntegerValue()
    throws IOException
  {
    return this.delegate.getBigIntegerValue();
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException
  {
    return this.delegate.getBinaryValue(paramBase64Variant);
  }
  
  public boolean getBooleanValue()
    throws IOException
  {
    return this.delegate.getBooleanValue();
  }
  
  public byte getByteValue()
    throws IOException
  {
    return this.delegate.getByteValue();
  }
  
  public ObjectCodec getCodec()
  {
    return this.delegate.getCodec();
  }
  
  public JsonLocation getCurrentLocation()
  {
    return this.delegate.getCurrentLocation();
  }
  
  public String getCurrentName()
    throws IOException
  {
    return this.delegate.getCurrentName();
  }
  
  public JsonToken getCurrentToken()
  {
    return this.delegate.getCurrentToken();
  }
  
  public int getCurrentTokenId()
  {
    return this.delegate.getCurrentTokenId();
  }
  
  public Object getCurrentValue()
  {
    return this.delegate.getCurrentValue();
  }
  
  public BigDecimal getDecimalValue()
    throws IOException
  {
    return this.delegate.getDecimalValue();
  }
  
  public double getDoubleValue()
    throws IOException
  {
    return this.delegate.getDoubleValue();
  }
  
  public Object getEmbeddedObject()
    throws IOException
  {
    return this.delegate.getEmbeddedObject();
  }
  
  public int getFeatureMask()
  {
    return this.delegate.getFeatureMask();
  }
  
  public float getFloatValue()
    throws IOException
  {
    return this.delegate.getFloatValue();
  }
  
  public Object getInputSource()
  {
    return this.delegate.getInputSource();
  }
  
  public int getIntValue()
    throws IOException
  {
    return this.delegate.getIntValue();
  }
  
  public JsonToken getLastClearedToken()
  {
    return this.delegate.getLastClearedToken();
  }
  
  public long getLongValue()
    throws IOException
  {
    return this.delegate.getLongValue();
  }
  
  public JsonParser.NumberType getNumberType()
    throws IOException
  {
    return this.delegate.getNumberType();
  }
  
  public Number getNumberValue()
    throws IOException
  {
    return this.delegate.getNumberValue();
  }
  
  public Object getObjectId()
    throws IOException
  {
    return this.delegate.getObjectId();
  }
  
  public JsonStreamContext getParsingContext()
  {
    return this.delegate.getParsingContext();
  }
  
  public FormatSchema getSchema()
  {
    return this.delegate.getSchema();
  }
  
  public short getShortValue()
    throws IOException
  {
    return this.delegate.getShortValue();
  }
  
  public String getText()
    throws IOException
  {
    return this.delegate.getText();
  }
  
  public char[] getTextCharacters()
    throws IOException
  {
    return this.delegate.getTextCharacters();
  }
  
  public int getTextLength()
    throws IOException
  {
    return this.delegate.getTextLength();
  }
  
  public int getTextOffset()
    throws IOException
  {
    return this.delegate.getTextOffset();
  }
  
  public JsonLocation getTokenLocation()
  {
    return this.delegate.getTokenLocation();
  }
  
  public Object getTypeId()
    throws IOException
  {
    return this.delegate.getTypeId();
  }
  
  public boolean getValueAsBoolean()
    throws IOException
  {
    return this.delegate.getValueAsBoolean();
  }
  
  public boolean getValueAsBoolean(boolean paramBoolean)
    throws IOException
  {
    return this.delegate.getValueAsBoolean(paramBoolean);
  }
  
  public double getValueAsDouble()
    throws IOException
  {
    return this.delegate.getValueAsDouble();
  }
  
  public double getValueAsDouble(double paramDouble)
    throws IOException
  {
    return this.delegate.getValueAsDouble(paramDouble);
  }
  
  public int getValueAsInt()
    throws IOException
  {
    return this.delegate.getValueAsInt();
  }
  
  public int getValueAsInt(int paramInt)
    throws IOException
  {
    return this.delegate.getValueAsInt(paramInt);
  }
  
  public long getValueAsLong()
    throws IOException
  {
    return this.delegate.getValueAsLong();
  }
  
  public long getValueAsLong(long paramLong)
    throws IOException
  {
    return this.delegate.getValueAsLong(paramLong);
  }
  
  public String getValueAsString()
    throws IOException
  {
    return this.delegate.getValueAsString();
  }
  
  public String getValueAsString(String paramString)
    throws IOException
  {
    return this.delegate.getValueAsString(paramString);
  }
  
  public boolean hasCurrentToken()
  {
    return this.delegate.hasCurrentToken();
  }
  
  public boolean hasTextCharacters()
  {
    return this.delegate.hasTextCharacters();
  }
  
  public boolean hasToken(JsonToken paramJsonToken)
  {
    return this.delegate.hasToken(paramJsonToken);
  }
  
  public boolean hasTokenId(int paramInt)
  {
    return this.delegate.hasTokenId(paramInt);
  }
  
  public boolean isClosed()
  {
    return this.delegate.isClosed();
  }
  
  public boolean isEnabled(JsonParser.Feature paramFeature)
  {
    return this.delegate.isEnabled(paramFeature);
  }
  
  public boolean isExpectedStartArrayToken()
  {
    return this.delegate.isExpectedStartArrayToken();
  }
  
  public boolean isExpectedStartObjectToken()
  {
    return this.delegate.isExpectedStartObjectToken();
  }
  
  public JsonToken nextToken()
    throws IOException
  {
    return this.delegate.nextToken();
  }
  
  public JsonToken nextValue()
    throws IOException
  {
    return this.delegate.nextValue();
  }
  
  public void overrideCurrentName(String paramString)
  {
    this.delegate.overrideCurrentName(paramString);
  }
  
  public int readBinaryValue(Base64Variant paramBase64Variant, OutputStream paramOutputStream)
    throws IOException
  {
    return this.delegate.readBinaryValue(paramBase64Variant, paramOutputStream);
  }
  
  public boolean requiresCustomCodec()
  {
    return this.delegate.requiresCustomCodec();
  }
  
  public void setCodec(ObjectCodec paramObjectCodec)
  {
    this.delegate.setCodec(paramObjectCodec);
  }
  
  public void setCurrentValue(Object paramObject)
  {
    this.delegate.setCurrentValue(paramObject);
  }
  
  public JsonParser setFeatureMask(int paramInt)
  {
    this.delegate.setFeatureMask(paramInt);
    return this;
  }
  
  public void setSchema(FormatSchema paramFormatSchema)
  {
    this.delegate.setSchema(paramFormatSchema);
  }
  
  public JsonParser skipChildren()
    throws IOException
  {
    this.delegate.skipChildren();
    return this;
  }
  
  public Version version()
  {
    return this.delegate.version();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/util/JsonParserDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */