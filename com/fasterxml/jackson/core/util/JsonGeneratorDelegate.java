package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonGeneratorDelegate
  extends JsonGenerator
{
  protected JsonGenerator delegate;
  protected boolean delegateCopyMethods;
  
  public JsonGeneratorDelegate(JsonGenerator paramJsonGenerator)
  {
    this(paramJsonGenerator, true);
  }
  
  public JsonGeneratorDelegate(JsonGenerator paramJsonGenerator, boolean paramBoolean)
  {
    this.delegate = paramJsonGenerator;
    this.delegateCopyMethods = paramBoolean;
  }
  
  public boolean canOmitFields()
  {
    return this.delegate.canOmitFields();
  }
  
  public boolean canUseSchema(FormatSchema paramFormatSchema)
  {
    return this.delegate.canUseSchema(paramFormatSchema);
  }
  
  public boolean canWriteBinaryNatively()
  {
    return this.delegate.canWriteBinaryNatively();
  }
  
  public boolean canWriteObjectId()
  {
    return this.delegate.canWriteObjectId();
  }
  
  public boolean canWriteTypeId()
  {
    return this.delegate.canWriteTypeId();
  }
  
  public void close()
    throws IOException
  {
    this.delegate.close();
  }
  
  public void copyCurrentEvent(JsonParser paramJsonParser)
    throws IOException
  {
    if (this.delegateCopyMethods)
    {
      this.delegate.copyCurrentEvent(paramJsonParser);
      return;
    }
    super.copyCurrentEvent(paramJsonParser);
  }
  
  public void copyCurrentStructure(JsonParser paramJsonParser)
    throws IOException
  {
    if (this.delegateCopyMethods)
    {
      this.delegate.copyCurrentStructure(paramJsonParser);
      return;
    }
    super.copyCurrentStructure(paramJsonParser);
  }
  
  public JsonGenerator disable(JsonGenerator.Feature paramFeature)
  {
    this.delegate.disable(paramFeature);
    return this;
  }
  
  public JsonGenerator enable(JsonGenerator.Feature paramFeature)
  {
    this.delegate.enable(paramFeature);
    return this;
  }
  
  public void flush()
    throws IOException
  {
    this.delegate.flush();
  }
  
  public CharacterEscapes getCharacterEscapes()
  {
    return this.delegate.getCharacterEscapes();
  }
  
  public ObjectCodec getCodec()
  {
    return this.delegate.getCodec();
  }
  
  public Object getCurrentValue()
  {
    return this.delegate.getCurrentValue();
  }
  
  public JsonGenerator getDelegate()
  {
    return this.delegate;
  }
  
  public int getFeatureMask()
  {
    return this.delegate.getFeatureMask();
  }
  
  public int getHighestEscapedChar()
  {
    return this.delegate.getHighestEscapedChar();
  }
  
  public int getOutputBuffered()
  {
    return this.delegate.getOutputBuffered();
  }
  
  public JsonStreamContext getOutputContext()
  {
    return this.delegate.getOutputContext();
  }
  
  public Object getOutputTarget()
  {
    return this.delegate.getOutputTarget();
  }
  
  public PrettyPrinter getPrettyPrinter()
  {
    return this.delegate.getPrettyPrinter();
  }
  
  public FormatSchema getSchema()
  {
    return this.delegate.getSchema();
  }
  
  public boolean isClosed()
  {
    return this.delegate.isClosed();
  }
  
  public boolean isEnabled(JsonGenerator.Feature paramFeature)
  {
    return this.delegate.isEnabled(paramFeature);
  }
  
  public JsonGenerator setCharacterEscapes(CharacterEscapes paramCharacterEscapes)
  {
    this.delegate.setCharacterEscapes(paramCharacterEscapes);
    return this;
  }
  
  public JsonGenerator setCodec(ObjectCodec paramObjectCodec)
  {
    this.delegate.setCodec(paramObjectCodec);
    return this;
  }
  
  public void setCurrentValue(Object paramObject)
  {
    this.delegate.setCurrentValue(paramObject);
  }
  
  public JsonGenerator setFeatureMask(int paramInt)
  {
    this.delegate.setFeatureMask(paramInt);
    return this;
  }
  
  public JsonGenerator setHighestNonEscapedChar(int paramInt)
  {
    this.delegate.setHighestNonEscapedChar(paramInt);
    return this;
  }
  
  public JsonGenerator setPrettyPrinter(PrettyPrinter paramPrettyPrinter)
  {
    this.delegate.setPrettyPrinter(paramPrettyPrinter);
    return this;
  }
  
  public JsonGenerator setRootValueSeparator(SerializableString paramSerializableString)
  {
    this.delegate.setRootValueSeparator(paramSerializableString);
    return this;
  }
  
  public void setSchema(FormatSchema paramFormatSchema)
  {
    this.delegate.setSchema(paramFormatSchema);
  }
  
  public JsonGenerator useDefaultPrettyPrinter()
  {
    this.delegate.useDefaultPrettyPrinter();
    return this;
  }
  
  public Version version()
  {
    return this.delegate.version();
  }
  
  public int writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, int paramInt)
    throws IOException
  {
    return this.delegate.writeBinary(paramBase64Variant, paramInputStream, paramInt);
  }
  
  public void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.delegate.writeBinary(paramBase64Variant, paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public void writeBoolean(boolean paramBoolean)
    throws IOException
  {
    this.delegate.writeBoolean(paramBoolean);
  }
  
  public void writeEndArray()
    throws IOException
  {
    this.delegate.writeEndArray();
  }
  
  public void writeEndObject()
    throws IOException
  {
    this.delegate.writeEndObject();
  }
  
  public void writeFieldName(SerializableString paramSerializableString)
    throws IOException
  {
    this.delegate.writeFieldName(paramSerializableString);
  }
  
  public void writeFieldName(String paramString)
    throws IOException
  {
    this.delegate.writeFieldName(paramString);
  }
  
  public void writeNull()
    throws IOException
  {
    this.delegate.writeNull();
  }
  
  public void writeNumber(double paramDouble)
    throws IOException
  {
    this.delegate.writeNumber(paramDouble);
  }
  
  public void writeNumber(float paramFloat)
    throws IOException
  {
    this.delegate.writeNumber(paramFloat);
  }
  
  public void writeNumber(int paramInt)
    throws IOException
  {
    this.delegate.writeNumber(paramInt);
  }
  
  public void writeNumber(long paramLong)
    throws IOException
  {
    this.delegate.writeNumber(paramLong);
  }
  
  public void writeNumber(String paramString)
    throws IOException, UnsupportedOperationException
  {
    this.delegate.writeNumber(paramString);
  }
  
  public void writeNumber(BigDecimal paramBigDecimal)
    throws IOException
  {
    this.delegate.writeNumber(paramBigDecimal);
  }
  
  public void writeNumber(BigInteger paramBigInteger)
    throws IOException
  {
    this.delegate.writeNumber(paramBigInteger);
  }
  
  public void writeNumber(short paramShort)
    throws IOException
  {
    this.delegate.writeNumber(paramShort);
  }
  
  public void writeObject(Object paramObject)
    throws IOException, JsonProcessingException
  {
    if (this.delegateCopyMethods)
    {
      this.delegate.writeObject(paramObject);
      return;
    }
    if (paramObject == null)
    {
      writeNull();
      return;
    }
    if (getCodec() != null)
    {
      getCodec().writeValue(this, paramObject);
      return;
    }
    _writeSimpleObject(paramObject);
  }
  
  public void writeObjectId(Object paramObject)
    throws IOException
  {
    this.delegate.writeObjectId(paramObject);
  }
  
  public void writeObjectRef(Object paramObject)
    throws IOException
  {
    this.delegate.writeObjectRef(paramObject);
  }
  
  public void writeOmittedField(String paramString)
    throws IOException
  {
    this.delegate.writeOmittedField(paramString);
  }
  
  public void writeRaw(char paramChar)
    throws IOException
  {
    this.delegate.writeRaw(paramChar);
  }
  
  public void writeRaw(SerializableString paramSerializableString)
    throws IOException
  {
    this.delegate.writeRaw(paramSerializableString);
  }
  
  public void writeRaw(String paramString)
    throws IOException
  {
    this.delegate.writeRaw(paramString);
  }
  
  public void writeRaw(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    this.delegate.writeRaw(paramString, paramInt1, paramInt2);
  }
  
  public void writeRaw(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    this.delegate.writeRaw(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeRawUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.delegate.writeRawUTF8String(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  public void writeRawValue(String paramString)
    throws IOException
  {
    this.delegate.writeRawValue(paramString);
  }
  
  public void writeRawValue(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    this.delegate.writeRawValue(paramString, paramInt1, paramInt2);
  }
  
  public void writeRawValue(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    this.delegate.writeRawValue(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeStartArray()
    throws IOException
  {
    this.delegate.writeStartArray();
  }
  
  public void writeStartArray(int paramInt)
    throws IOException
  {
    this.delegate.writeStartArray(paramInt);
  }
  
  public void writeStartObject()
    throws IOException
  {
    this.delegate.writeStartObject();
  }
  
  public void writeString(SerializableString paramSerializableString)
    throws IOException
  {
    this.delegate.writeString(paramSerializableString);
  }
  
  public void writeString(String paramString)
    throws IOException
  {
    this.delegate.writeString(paramString);
  }
  
  public void writeString(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    this.delegate.writeString(paramArrayOfChar, paramInt1, paramInt2);
  }
  
  public void writeTree(TreeNode paramTreeNode)
    throws IOException
  {
    if (this.delegateCopyMethods)
    {
      this.delegate.writeTree(paramTreeNode);
      return;
    }
    if (paramTreeNode == null)
    {
      writeNull();
      return;
    }
    if (getCodec() == null) {
      throw new IllegalStateException("No ObjectCodec defined");
    }
    getCodec().writeValue(this, paramTreeNode);
  }
  
  public void writeTypeId(Object paramObject)
    throws IOException
  {
    this.delegate.writeTypeId(paramObject);
  }
  
  public void writeUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.delegate.writeUTF8String(paramArrayOfByte, paramInt1, paramInt2);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/util/JsonGeneratorDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */