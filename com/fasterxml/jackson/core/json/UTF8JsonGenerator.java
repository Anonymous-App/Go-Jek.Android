package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class UTF8JsonGenerator
  extends JsonGeneratorImpl
{
  private static final byte BYTE_0 = 48;
  private static final byte BYTE_BACKSLASH = 92;
  private static final byte BYTE_COLON = 58;
  private static final byte BYTE_COMMA = 44;
  private static final byte BYTE_LBRACKET = 91;
  private static final byte BYTE_LCURLY = 123;
  private static final byte BYTE_QUOTE = 34;
  private static final byte BYTE_RBRACKET = 93;
  private static final byte BYTE_RCURLY = 125;
  private static final byte BYTE_u = 117;
  private static final byte[] FALSE_BYTES = { 102, 97, 108, 115, 101 };
  static final byte[] HEX_CHARS = ;
  private static final int MAX_BYTES_TO_BUFFER = 512;
  private static final byte[] NULL_BYTES = { 110, 117, 108, 108 };
  private static final byte[] TRUE_BYTES = { 116, 114, 117, 101 };
  protected boolean _bufferRecyclable;
  protected boolean _cfgUnqNames;
  protected char[] _charBuffer;
  protected final int _charBufferLength;
  protected byte[] _entityBuffer;
  protected byte[] _outputBuffer;
  protected final int _outputEnd;
  protected final int _outputMaxContiguous;
  protected final OutputStream _outputStream;
  protected int _outputTail = 0;
  
  public UTF8JsonGenerator(IOContext paramIOContext, int paramInt, ObjectCodec paramObjectCodec, OutputStream paramOutputStream)
  {
    super(paramIOContext, paramInt, paramObjectCodec);
    this._outputStream = paramOutputStream;
    this._bufferRecyclable = true;
    this._outputBuffer = paramIOContext.allocWriteEncodingBuffer();
    this._outputEnd = this._outputBuffer.length;
    this._outputMaxContiguous = (this._outputEnd >> 3);
    this._charBuffer = paramIOContext.allocConcatBuffer();
    this._charBufferLength = this._charBuffer.length;
    if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII)) {
      setHighestNonEscapedChar(127);
    }
    if (!JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(paramInt)) {}
    for (;;)
    {
      this._cfgUnqNames = bool;
      return;
      bool = false;
    }
  }
  
  public UTF8JsonGenerator(IOContext paramIOContext, int paramInt1, ObjectCodec paramObjectCodec, OutputStream paramOutputStream, byte[] paramArrayOfByte, int paramInt2, boolean paramBoolean)
  {
    super(paramIOContext, paramInt1, paramObjectCodec);
    this._outputStream = paramOutputStream;
    this._bufferRecyclable = paramBoolean;
    this._outputTail = paramInt2;
    this._outputBuffer = paramArrayOfByte;
    this._outputEnd = this._outputBuffer.length;
    this._outputMaxContiguous = (this._outputEnd >> 3);
    this._charBuffer = paramIOContext.allocConcatBuffer();
    this._charBufferLength = this._charBuffer.length;
    paramBoolean = bool;
    if (!JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(paramInt1)) {
      paramBoolean = true;
    }
    this._cfgUnqNames = paramBoolean;
  }
  
  private final int _handleLongCustomEscape(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, byte[] paramArrayOfByte2, int paramInt3)
    throws IOException, JsonGenerationException
  {
    int j = paramArrayOfByte2.length;
    int i = paramInt1;
    if (paramInt1 + j > paramInt2)
    {
      this._outputTail = paramInt1;
      _flushBuffer();
      paramInt1 = this._outputTail;
      if (j > paramArrayOfByte1.length)
      {
        this._outputStream.write(paramArrayOfByte2, 0, j);
        return paramInt1;
      }
      System.arraycopy(paramArrayOfByte2, 0, paramArrayOfByte1, paramInt1, j);
      i = paramInt1 + j;
    }
    if (paramInt3 * 6 + i > paramInt2)
    {
      _flushBuffer();
      return this._outputTail;
    }
    return i;
  }
  
  private final int _outputMultiByteChar(int paramInt1, int paramInt2)
    throws IOException
  {
    byte[] arrayOfByte = this._outputBuffer;
    if ((paramInt1 >= 55296) && (paramInt1 <= 57343))
    {
      i = paramInt2 + 1;
      arrayOfByte[paramInt2] = 92;
      paramInt2 = i + 1;
      arrayOfByte[i] = 117;
      i = paramInt2 + 1;
      arrayOfByte[paramInt2] = HEX_CHARS[(paramInt1 >> 12 & 0xF)];
      paramInt2 = i + 1;
      arrayOfByte[i] = HEX_CHARS[(paramInt1 >> 8 & 0xF)];
      i = paramInt2 + 1;
      arrayOfByte[paramInt2] = HEX_CHARS[(paramInt1 >> 4 & 0xF)];
      arrayOfByte[i] = HEX_CHARS[(paramInt1 & 0xF)];
      return i + 1;
    }
    int i = paramInt2 + 1;
    arrayOfByte[paramInt2] = ((byte)(paramInt1 >> 12 | 0xE0));
    paramInt2 = i + 1;
    arrayOfByte[i] = ((byte)(paramInt1 >> 6 & 0x3F | 0x80));
    arrayOfByte[paramInt2] = ((byte)(paramInt1 & 0x3F | 0x80));
    return paramInt2 + 1;
  }
  
  private final int _outputRawMultiByteChar(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
    throws IOException
  {
    if ((paramInt1 >= 55296) && (paramInt1 <= 57343))
    {
      if ((paramInt2 >= paramInt3) || (paramArrayOfChar == null)) {
        _reportError("Split surrogate on writeRaw() input (last character)");
      }
      _outputSurrogates(paramInt1, paramArrayOfChar[paramInt2]);
      return paramInt2 + 1;
    }
    paramArrayOfChar = this._outputBuffer;
    paramInt3 = this._outputTail;
    this._outputTail = (paramInt3 + 1);
    paramArrayOfChar[paramInt3] = ((byte)(paramInt1 >> 12 | 0xE0));
    paramInt3 = this._outputTail;
    this._outputTail = (paramInt3 + 1);
    paramArrayOfChar[paramInt3] = ((byte)(paramInt1 >> 6 & 0x3F | 0x80));
    paramInt3 = this._outputTail;
    this._outputTail = (paramInt3 + 1);
    paramArrayOfChar[paramInt3] = ((byte)(paramInt1 & 0x3F | 0x80));
    return paramInt2;
  }
  
  private final int _readMore(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    int j = 0;
    int i = paramInt1;
    paramInt1 = j;
    while (i < paramInt2)
    {
      paramArrayOfByte[paramInt1] = paramArrayOfByte[i];
      paramInt1 += 1;
      i += 1;
    }
    paramInt3 = Math.min(paramInt3, paramArrayOfByte.length);
    paramInt2 = paramInt3 - paramInt1;
    if (paramInt2 == 0) {}
    for (;;)
    {
      return paramInt1;
      paramInt2 = paramInputStream.read(paramArrayOfByte, paramInt1, paramInt2);
      if (paramInt2 < 0) {
        return paramInt1;
      }
      paramInt2 = paramInt1 + paramInt2;
      paramInt1 = paramInt2;
      if (paramInt2 < 3) {
        break;
      }
      paramInt1 = paramInt2;
    }
  }
  
  private final void _writeBytes(byte[] paramArrayOfByte)
    throws IOException
  {
    int i = paramArrayOfByte.length;
    if (this._outputTail + i > this._outputEnd)
    {
      _flushBuffer();
      if (i > 512)
      {
        this._outputStream.write(paramArrayOfByte, 0, i);
        return;
      }
    }
    System.arraycopy(paramArrayOfByte, 0, this._outputBuffer, this._outputTail, i);
    this._outputTail += i;
  }
  
  private final void _writeBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + paramInt2 > this._outputEnd)
    {
      _flushBuffer();
      if (paramInt2 > 512)
      {
        this._outputStream.write(paramArrayOfByte, paramInt1, paramInt2);
        return;
      }
    }
    System.arraycopy(paramArrayOfByte, paramInt1, this._outputBuffer, this._outputTail, paramInt2);
    this._outputTail += paramInt2;
  }
  
  private final int _writeCustomEscape(byte[] paramArrayOfByte, int paramInt1, SerializableString paramSerializableString, int paramInt2)
    throws IOException, JsonGenerationException
  {
    paramSerializableString = paramSerializableString.asUnquotedUTF8();
    int i = paramSerializableString.length;
    if (i > 6) {
      return _handleLongCustomEscape(paramArrayOfByte, paramInt1, this._outputEnd, paramSerializableString, paramInt2);
    }
    System.arraycopy(paramSerializableString, 0, paramArrayOfByte, paramInt1, i);
    return paramInt1 + i;
  }
  
  private final void _writeCustomStringSegment2(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + (paramInt2 - paramInt1) * 6 > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int k;
    CharacterEscapes localCharacterEscapes;
    if (this._maximumNonEscapedChar <= 0)
    {
      k = 65535;
      localCharacterEscapes = this._characterEscapes;
    }
    for (;;)
    {
      if (paramInt1 < paramInt2)
      {
        int j = paramInt1 + 1;
        int m = paramString.charAt(paramInt1);
        SerializableString localSerializableString;
        if (m <= 127)
        {
          if (arrayOfInt[m] == 0)
          {
            arrayOfByte[i] = ((byte)m);
            i += 1;
            paramInt1 = j;
            continue;
            k = this._maximumNonEscapedChar;
            break;
          }
          paramInt1 = arrayOfInt[m];
          if (paramInt1 > 0)
          {
            m = i + 1;
            arrayOfByte[i] = 92;
            i = m + 1;
            arrayOfByte[m] = ((byte)paramInt1);
            paramInt1 = j;
            continue;
          }
          if (paramInt1 == -2)
          {
            localSerializableString = localCharacterEscapes.getEscapeSequence(m);
            if (localSerializableString == null) {
              _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(m) + ", although was supposed to have one");
            }
            i = _writeCustomEscape(arrayOfByte, i, localSerializableString, paramInt2 - j);
            paramInt1 = j;
            continue;
          }
          i = _writeGenericEscape(m, i);
          paramInt1 = j;
          continue;
        }
        if (m > k)
        {
          i = _writeGenericEscape(m, i);
          paramInt1 = j;
        }
        else
        {
          localSerializableString = localCharacterEscapes.getEscapeSequence(m);
          if (localSerializableString != null)
          {
            i = _writeCustomEscape(arrayOfByte, i, localSerializableString, paramInt2 - j);
            paramInt1 = j;
          }
          else
          {
            if (m <= 2047)
            {
              paramInt1 = i + 1;
              arrayOfByte[i] = ((byte)(m >> 6 | 0xC0));
              arrayOfByte[paramInt1] = ((byte)(m & 0x3F | 0x80));
              paramInt1 += 1;
            }
            for (;;)
            {
              i = paramInt1;
              paramInt1 = j;
              break;
              paramInt1 = _outputMultiByteChar(m, i);
            }
          }
        }
      }
    }
    this._outputTail = i;
  }
  
  private final void _writeCustomStringSegment2(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + (paramInt2 - paramInt1) * 6 > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int k;
    CharacterEscapes localCharacterEscapes;
    if (this._maximumNonEscapedChar <= 0)
    {
      k = 65535;
      localCharacterEscapes = this._characterEscapes;
    }
    for (;;)
    {
      if (paramInt1 < paramInt2)
      {
        int j = paramInt1 + 1;
        int m = paramArrayOfChar[paramInt1];
        SerializableString localSerializableString;
        if (m <= 127)
        {
          if (arrayOfInt[m] == 0)
          {
            arrayOfByte[i] = ((byte)m);
            i += 1;
            paramInt1 = j;
            continue;
            k = this._maximumNonEscapedChar;
            break;
          }
          paramInt1 = arrayOfInt[m];
          if (paramInt1 > 0)
          {
            m = i + 1;
            arrayOfByte[i] = 92;
            i = m + 1;
            arrayOfByte[m] = ((byte)paramInt1);
            paramInt1 = j;
            continue;
          }
          if (paramInt1 == -2)
          {
            localSerializableString = localCharacterEscapes.getEscapeSequence(m);
            if (localSerializableString == null) {
              _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(m) + ", although was supposed to have one");
            }
            i = _writeCustomEscape(arrayOfByte, i, localSerializableString, paramInt2 - j);
            paramInt1 = j;
            continue;
          }
          i = _writeGenericEscape(m, i);
          paramInt1 = j;
          continue;
        }
        if (m > k)
        {
          i = _writeGenericEscape(m, i);
          paramInt1 = j;
        }
        else
        {
          localSerializableString = localCharacterEscapes.getEscapeSequence(m);
          if (localSerializableString != null)
          {
            i = _writeCustomEscape(arrayOfByte, i, localSerializableString, paramInt2 - j);
            paramInt1 = j;
          }
          else
          {
            if (m <= 2047)
            {
              paramInt1 = i + 1;
              arrayOfByte[i] = ((byte)(m >> 6 | 0xC0));
              arrayOfByte[paramInt1] = ((byte)(m & 0x3F | 0x80));
              paramInt1 += 1;
            }
            for (;;)
            {
              i = paramInt1;
              paramInt1 = j;
              break;
              paramInt1 = _outputMultiByteChar(m, i);
            }
          }
        }
      }
    }
    this._outputTail = i;
  }
  
  private int _writeGenericEscape(int paramInt1, int paramInt2)
    throws IOException
  {
    byte[] arrayOfByte = this._outputBuffer;
    int i = paramInt2 + 1;
    arrayOfByte[paramInt2] = 92;
    paramInt2 = i + 1;
    arrayOfByte[i] = 117;
    if (paramInt1 > 255)
    {
      i = paramInt1 >> 8 & 0xFF;
      int j = paramInt2 + 1;
      arrayOfByte[paramInt2] = HEX_CHARS[(i >> 4)];
      paramInt2 = j + 1;
      arrayOfByte[j] = HEX_CHARS[(i & 0xF)];
      paramInt1 &= 0xFF;
    }
    for (;;)
    {
      i = paramInt2 + 1;
      arrayOfByte[paramInt2] = HEX_CHARS[(paramInt1 >> 4)];
      arrayOfByte[i] = HEX_CHARS[(paramInt1 & 0xF)];
      return i + 1;
      i = paramInt2 + 1;
      arrayOfByte[paramInt2] = 48;
      paramInt2 = i + 1;
      arrayOfByte[i] = 48;
    }
  }
  
  private final void _writeNull()
    throws IOException
  {
    if (this._outputTail + 4 >= this._outputEnd) {
      _flushBuffer();
    }
    System.arraycopy(NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
    this._outputTail += 4;
  }
  
  private final void _writeQuotedInt(int paramInt)
    throws IOException
  {
    if (this._outputTail + 13 >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
    this._outputTail = NumberOutput.outputInt(paramInt, this._outputBuffer, this._outputTail);
    arrayOfByte = this._outputBuffer;
    paramInt = this._outputTail;
    this._outputTail = (paramInt + 1);
    arrayOfByte[paramInt] = 34;
  }
  
  private final void _writeQuotedLong(long paramLong)
    throws IOException
  {
    if (this._outputTail + 23 >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
    this._outputTail = NumberOutput.outputLong(paramLong, this._outputBuffer, this._outputTail);
    arrayOfByte = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
  }
  
  private final void _writeQuotedRaw(String paramString)
    throws IOException
  {
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
    writeRaw(paramString);
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    paramString = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = (i + 1);
    paramString[i] = 34;
  }
  
  private final void _writeQuotedShort(short paramShort)
    throws IOException
  {
    if (this._outputTail + 8 >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
    this._outputTail = NumberOutput.outputInt(paramShort, this._outputBuffer, this._outputTail);
    arrayOfByte = this._outputBuffer;
    paramShort = this._outputTail;
    this._outputTail = (paramShort + 1);
    arrayOfByte[paramShort] = 34;
  }
  
  private final void _writeSegmentedRaw(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int j = this._outputEnd;
    byte[] arrayOfByte = this._outputBuffer;
    for (;;)
    {
      int i;
      if (paramInt1 < paramInt2) {
        do
        {
          i = paramArrayOfChar[paramInt1];
          if (i >= 128)
          {
            if (this._outputTail + 3 >= this._outputEnd) {
              _flushBuffer();
            }
            i = paramInt1 + 1;
            paramInt1 = paramArrayOfChar[paramInt1];
            if (paramInt1 >= 2048) {
              break label175;
            }
            k = this._outputTail;
            this._outputTail = (k + 1);
            arrayOfByte[k] = ((byte)(paramInt1 >> 6 | 0xC0));
            k = this._outputTail;
            this._outputTail = (k + 1);
            arrayOfByte[k] = ((byte)(paramInt1 & 0x3F | 0x80));
            paramInt1 = i;
            break;
          }
          if (this._outputTail >= j) {
            _flushBuffer();
          }
          int k = this._outputTail;
          this._outputTail = (k + 1);
          arrayOfByte[k] = ((byte)i);
          i = paramInt1 + 1;
          paramInt1 = i;
        } while (i < paramInt2);
      }
      return;
      label175:
      paramInt1 = _outputRawMultiByteChar(paramInt1, paramArrayOfChar, i, paramInt2);
    }
  }
  
  private final void _writeStringSegment(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    int j = paramInt2 + paramInt1;
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    paramInt2 = paramInt1;
    paramInt1 = i;
    for (;;)
    {
      if (paramInt2 < j)
      {
        i = paramString.charAt(paramInt2);
        if ((i <= 127) && (arrayOfInt[i] == 0)) {}
      }
      else
      {
        this._outputTail = paramInt1;
        if (paramInt2 < j)
        {
          if (this._characterEscapes == null) {
            break;
          }
          _writeCustomStringSegment2(paramString, paramInt2, j);
        }
        return;
      }
      arrayOfByte[paramInt1] = ((byte)i);
      paramInt2 += 1;
      paramInt1 += 1;
    }
    if (this._maximumNonEscapedChar == 0)
    {
      _writeStringSegment2(paramString, paramInt2, j);
      return;
    }
    _writeStringSegmentASCII2(paramString, paramInt2, j);
  }
  
  private final void _writeStringSegment(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    int j = paramInt2 + paramInt1;
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    paramInt2 = paramInt1;
    paramInt1 = i;
    for (;;)
    {
      if (paramInt2 < j)
      {
        i = paramArrayOfChar[paramInt2];
        if ((i <= 127) && (arrayOfInt[i] == 0)) {}
      }
      else
      {
        this._outputTail = paramInt1;
        if (paramInt2 < j)
        {
          if (this._characterEscapes == null) {
            break;
          }
          _writeCustomStringSegment2(paramArrayOfChar, paramInt2, j);
        }
        return;
      }
      arrayOfByte[paramInt1] = ((byte)i);
      paramInt2 += 1;
      paramInt1 += 1;
    }
    if (this._maximumNonEscapedChar == 0)
    {
      _writeStringSegment2(paramArrayOfChar, paramInt2, j);
      return;
    }
    _writeStringSegmentASCII2(paramArrayOfChar, paramInt2, j);
  }
  
  private final void _writeStringSegment2(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + (paramInt2 - paramInt1) * 6 > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int j = paramInt1;
    paramInt1 = i;
    i = j;
    while (i < paramInt2)
    {
      j = i + 1;
      int k = paramString.charAt(i);
      if (k <= 127)
      {
        if (arrayOfInt[k] == 0)
        {
          arrayOfByte[paramInt1] = ((byte)k);
          paramInt1 += 1;
          i = j;
        }
        else
        {
          i = arrayOfInt[k];
          if (i > 0)
          {
            k = paramInt1 + 1;
            arrayOfByte[paramInt1] = 92;
            paramInt1 = k + 1;
            arrayOfByte[k] = ((byte)i);
            i = j;
          }
          else
          {
            paramInt1 = _writeGenericEscape(k, paramInt1);
            i = j;
          }
        }
      }
      else
      {
        if (k <= 2047)
        {
          i = paramInt1 + 1;
          arrayOfByte[paramInt1] = ((byte)(k >> 6 | 0xC0));
          arrayOfByte[i] = ((byte)(k & 0x3F | 0x80));
        }
        for (paramInt1 = i + 1;; paramInt1 = _outputMultiByteChar(k, paramInt1))
        {
          i = j;
          break;
        }
      }
    }
    this._outputTail = paramInt1;
  }
  
  private final void _writeStringSegment2(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + (paramInt2 - paramInt1) * 6 > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int j = paramInt1;
    paramInt1 = i;
    i = j;
    while (i < paramInt2)
    {
      j = i + 1;
      int k = paramArrayOfChar[i];
      if (k <= 127)
      {
        if (arrayOfInt[k] == 0)
        {
          arrayOfByte[paramInt1] = ((byte)k);
          paramInt1 += 1;
          i = j;
        }
        else
        {
          i = arrayOfInt[k];
          if (i > 0)
          {
            k = paramInt1 + 1;
            arrayOfByte[paramInt1] = 92;
            paramInt1 = k + 1;
            arrayOfByte[k] = ((byte)i);
            i = j;
          }
          else
          {
            paramInt1 = _writeGenericEscape(k, paramInt1);
            i = j;
          }
        }
      }
      else
      {
        if (k <= 2047)
        {
          i = paramInt1 + 1;
          arrayOfByte[paramInt1] = ((byte)(k >> 6 | 0xC0));
          arrayOfByte[i] = ((byte)(k & 0x3F | 0x80));
        }
        for (paramInt1 = i + 1;; paramInt1 = _outputMultiByteChar(k, paramInt1))
        {
          i = j;
          break;
        }
      }
    }
    this._outputTail = paramInt1;
  }
  
  private final void _writeStringSegmentASCII2(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + (paramInt2 - paramInt1) * 6 > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int k = this._maximumNonEscapedChar;
    while (paramInt1 < paramInt2)
    {
      int j = paramInt1 + 1;
      int m = paramString.charAt(paramInt1);
      if (m <= 127)
      {
        if (arrayOfInt[m] == 0)
        {
          arrayOfByte[i] = ((byte)m);
          i += 1;
          paramInt1 = j;
        }
        else
        {
          paramInt1 = arrayOfInt[m];
          if (paramInt1 > 0)
          {
            m = i + 1;
            arrayOfByte[i] = 92;
            i = m + 1;
            arrayOfByte[m] = ((byte)paramInt1);
            paramInt1 = j;
          }
          else
          {
            i = _writeGenericEscape(m, i);
            paramInt1 = j;
          }
        }
      }
      else if (m > k)
      {
        i = _writeGenericEscape(m, i);
        paramInt1 = j;
      }
      else
      {
        if (m <= 2047)
        {
          paramInt1 = i + 1;
          arrayOfByte[i] = ((byte)(m >> 6 | 0xC0));
          arrayOfByte[paramInt1] = ((byte)(m & 0x3F | 0x80));
          paramInt1 += 1;
        }
        for (;;)
        {
          i = paramInt1;
          paramInt1 = j;
          break;
          paramInt1 = _outputMultiByteChar(m, i);
        }
      }
    }
    this._outputTail = i;
  }
  
  private final void _writeStringSegmentASCII2(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    if (this._outputTail + (paramInt2 - paramInt1) * 6 > this._outputEnd) {
      _flushBuffer();
    }
    int i = this._outputTail;
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    int k = this._maximumNonEscapedChar;
    while (paramInt1 < paramInt2)
    {
      int j = paramInt1 + 1;
      int m = paramArrayOfChar[paramInt1];
      if (m <= 127)
      {
        if (arrayOfInt[m] == 0)
        {
          arrayOfByte[i] = ((byte)m);
          i += 1;
          paramInt1 = j;
        }
        else
        {
          paramInt1 = arrayOfInt[m];
          if (paramInt1 > 0)
          {
            m = i + 1;
            arrayOfByte[i] = 92;
            i = m + 1;
            arrayOfByte[m] = ((byte)paramInt1);
            paramInt1 = j;
          }
          else
          {
            i = _writeGenericEscape(m, i);
            paramInt1 = j;
          }
        }
      }
      else if (m > k)
      {
        i = _writeGenericEscape(m, i);
        paramInt1 = j;
      }
      else
      {
        if (m <= 2047)
        {
          paramInt1 = i + 1;
          arrayOfByte[i] = ((byte)(m >> 6 | 0xC0));
          arrayOfByte[paramInt1] = ((byte)(m & 0x3F | 0x80));
          paramInt1 += 1;
        }
        for (;;)
        {
          i = paramInt1;
          paramInt1 = j;
          break;
          paramInt1 = _outputMultiByteChar(m, i);
        }
      }
    }
    this._outputTail = i;
  }
  
  private final void _writeStringSegments(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    int i;
    do
    {
      i = Math.min(this._outputMaxContiguous, paramInt2);
      if (this._outputTail + i > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(paramString, paramInt1, i);
      paramInt1 += i;
      i = paramInt2 - i;
      paramInt2 = i;
    } while (i > 0);
  }
  
  private final void _writeStringSegments(String paramString, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = 34;
    }
    int i = paramString.length();
    int j = 0;
    while (i > 0)
    {
      int k = Math.min(this._outputMaxContiguous, i);
      if (this._outputTail + k > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(paramString, j, k);
      j += k;
      i -= k;
    }
    if (paramBoolean)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      paramString = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = (i + 1);
      paramString[i] = 34;
    }
  }
  
  private final void _writeStringSegments(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    int i;
    do
    {
      i = Math.min(this._outputMaxContiguous, paramInt2);
      if (this._outputTail + i > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(paramArrayOfChar, paramInt1, i);
      paramInt1 += i;
      i = paramInt2 - i;
      paramInt2 = i;
    } while (i > 0);
  }
  
  private final void _writeUTF8Segment(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int[] arrayOfInt = this._outputEscapes;
    int i = paramInt1;
    while (i < paramInt1 + paramInt2)
    {
      int j = paramArrayOfByte[i];
      if ((j >= 0) && (arrayOfInt[j] != 0))
      {
        _writeUTF8Segment2(paramArrayOfByte, paramInt1, paramInt2);
        return;
      }
      i += 1;
    }
    if (this._outputTail + paramInt2 > this._outputEnd) {
      _flushBuffer();
    }
    System.arraycopy(paramArrayOfByte, paramInt1, this._outputBuffer, this._outputTail, paramInt2);
    this._outputTail += paramInt2;
  }
  
  private final void _writeUTF8Segment2(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int k = this._outputTail;
    int j = k;
    if (paramInt2 * 6 + k > this._outputEnd)
    {
      _flushBuffer();
      j = this._outputTail;
    }
    byte[] arrayOfByte = this._outputBuffer;
    int[] arrayOfInt = this._outputEscapes;
    k = paramInt1;
    while (k < paramInt2 + paramInt1)
    {
      int m = k + 1;
      int i = paramArrayOfByte[k];
      if ((i < 0) || (arrayOfInt[i] == 0))
      {
        arrayOfByte[j] = i;
        j += 1;
        k = m;
      }
      else
      {
        k = arrayOfInt[i];
        int n;
        if (k > 0)
        {
          n = j + 1;
          arrayOfByte[j] = 92;
          arrayOfByte[n] = ((byte)k);
        }
        for (j = n + 1;; j = _writeGenericEscape(i, j))
        {
          k = m;
          break;
        }
      }
    }
    this._outputTail = j;
  }
  
  private final void _writeUTF8Segments(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int i;
    do
    {
      i = Math.min(this._outputMaxContiguous, paramInt2);
      _writeUTF8Segment(paramArrayOfByte, paramInt1, i);
      paramInt1 += i;
      i = paramInt2 - i;
      paramInt2 = i;
    } while (i > 0);
  }
  
  private final void _writeUnq(SerializableString paramSerializableString)
    throws IOException
  {
    int i = paramSerializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
    if (i < 0)
    {
      _writeBytes(paramSerializableString.asQuotedUTF8());
      return;
    }
    this._outputTail += i;
  }
  
  protected final void _flushBuffer()
    throws IOException
  {
    int i = this._outputTail;
    if (i > 0)
    {
      this._outputTail = 0;
      this._outputStream.write(this._outputBuffer, 0, i);
    }
  }
  
  protected final void _outputSurrogates(int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt1 = _decodeSurrogate(paramInt1, paramInt2);
    if (this._outputTail + 4 > this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    paramInt2 = this._outputTail;
    this._outputTail = (paramInt2 + 1);
    arrayOfByte[paramInt2] = ((byte)(paramInt1 >> 18 | 0xF0));
    paramInt2 = this._outputTail;
    this._outputTail = (paramInt2 + 1);
    arrayOfByte[paramInt2] = ((byte)(paramInt1 >> 12 & 0x3F | 0x80));
    paramInt2 = this._outputTail;
    this._outputTail = (paramInt2 + 1);
    arrayOfByte[paramInt2] = ((byte)(paramInt1 >> 6 & 0x3F | 0x80));
    paramInt2 = this._outputTail;
    this._outputTail = (paramInt2 + 1);
    arrayOfByte[paramInt2] = ((byte)(paramInt1 & 0x3F | 0x80));
  }
  
  protected void _releaseBuffers()
  {
    Object localObject = this._outputBuffer;
    if ((localObject != null) && (this._bufferRecyclable))
    {
      this._outputBuffer = null;
      this._ioContext.releaseWriteEncodingBuffer((byte[])localObject);
    }
    localObject = this._charBuffer;
    if (localObject != null)
    {
      this._charBuffer = null;
      this._ioContext.releaseConcatBuffer((char[])localObject);
    }
  }
  
  protected final void _verifyPrettyValueWrite(String paramString, int paramInt)
    throws IOException
  {
    switch (paramInt)
    {
    default: 
      _throwInternal();
    }
    do
    {
      return;
      this._cfgPrettyPrinter.writeArrayValueSeparator(this);
      return;
      this._cfgPrettyPrinter.writeObjectFieldValueSeparator(this);
      return;
      this._cfgPrettyPrinter.writeRootValueSeparator(this);
      return;
      if (this._writeContext.inArray())
      {
        this._cfgPrettyPrinter.beforeArrayValues(this);
        return;
      }
    } while (!this._writeContext.inObject());
    this._cfgPrettyPrinter.beforeObjectEntries(this);
  }
  
  protected final void _verifyValueWrite(String paramString)
    throws IOException
  {
    int j = this._writeContext.writeValue();
    if (j == 5) {
      _reportError("Can not " + paramString + ", expecting field name");
    }
    if (this._cfgPrettyPrinter == null)
    {
      switch (j)
      {
      }
      do
      {
        do
        {
          return;
          for (int i = 44;; i = 58)
          {
            if (this._outputTail >= this._outputEnd) {
              _flushBuffer();
            }
            this._outputBuffer[this._outputTail] = i;
            this._outputTail += 1;
            return;
          }
        } while (this._rootValueSeparator == null);
        paramString = this._rootValueSeparator.asUnquotedUTF8();
      } while (paramString.length <= 0);
      _writeBytes(paramString);
      return;
    }
    _verifyPrettyValueWrite(paramString, j);
  }
  
  protected final int _writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, byte[] paramArrayOfByte)
    throws IOException, JsonGenerationException
  {
    int j = 0;
    int i2 = 0;
    int i1 = -3;
    int i = 0;
    int i4 = this._outputEnd - 6;
    int k = paramBase64Variant.getMaxLineLength() >> 2;
    for (;;)
    {
      int i3 = j;
      int n = i2;
      int m = i1;
      if (j > i1)
      {
        n = _readMore(paramInputStream, paramArrayOfByte, j, i2, paramArrayOfByte.length);
        i3 = 0;
        if (n < 3)
        {
          j = i;
          if (n < 0)
          {
            if (this._outputTail > i4) {
              _flushBuffer();
            }
            i1 = 0 + 1;
            m = paramArrayOfByte[0] << 16;
            k = 1;
            j = m;
            if (i1 < n)
            {
              j = m | (paramArrayOfByte[i1] & 0xFF) << 8;
              k = 2;
            }
            i += k;
            this._outputTail = paramBase64Variant.encodeBase64Partial(j, k, this._outputBuffer, this._outputTail);
            j = i;
          }
          return j;
        }
        m = n - 3;
      }
      if (this._outputTail > i4) {
        _flushBuffer();
      }
      i2 = i3 + 1;
      j = paramArrayOfByte[i3];
      i1 = i2 + 1;
      i2 = paramArrayOfByte[i2];
      i3 = paramArrayOfByte[i1];
      i += 3;
      this._outputTail = paramBase64Variant.encodeBase64Chunk((j << 8 | i2 & 0xFF) << 8 | i3 & 0xFF, this._outputBuffer, this._outputTail);
      j = k - 1;
      k = j;
      if (j <= 0)
      {
        byte[] arrayOfByte = this._outputBuffer;
        j = this._outputTail;
        this._outputTail = (j + 1);
        arrayOfByte[j] = 92;
        arrayOfByte = this._outputBuffer;
        j = this._outputTail;
        this._outputTail = (j + 1);
        arrayOfByte[j] = 110;
        k = paramBase64Variant.getMaxLineLength() >> 2;
      }
      j = i1 + 1;
      i2 = n;
      i1 = m;
    }
  }
  
  protected final int _writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt)
    throws IOException, JsonGenerationException
  {
    int m = 0;
    int j = 0;
    int i1 = -3;
    int i3 = this._outputEnd - 6;
    int k = paramBase64Variant.getMaxLineLength() >> 2;
    int i = paramInt;
    paramInt = m;
    int n = paramInt;
    m = j;
    int i2;
    if (i > 2)
    {
      i2 = paramInt;
      n = j;
      m = i1;
      if (paramInt <= i1) {
        break label222;
      }
      n = _readMore(paramInputStream, paramArrayOfByte, paramInt, j, i);
      i2 = 0;
      paramInt = 0;
      if (n < 3)
      {
        m = n;
        n = paramInt;
      }
    }
    else
    {
      paramInt = i;
      if (i > 0)
      {
        k = _readMore(paramInputStream, paramArrayOfByte, n, m, i);
        paramInt = i;
        if (k > 0)
        {
          if (this._outputTail > i3) {
            _flushBuffer();
          }
          paramInt = 0 + 1;
          j = paramArrayOfByte[0] << 16;
          if (paramInt >= k) {
            break label411;
          }
          j |= (paramArrayOfByte[paramInt] & 0xFF) << 8;
        }
      }
    }
    label222:
    label411:
    for (paramInt = 2;; paramInt = 1)
    {
      this._outputTail = paramBase64Variant.encodeBase64Partial(j, paramInt, this._outputBuffer, this._outputTail);
      paramInt = i - paramInt;
      return paramInt;
      m = n - 3;
      if (this._outputTail > i3) {
        _flushBuffer();
      }
      i1 = i2 + 1;
      paramInt = paramArrayOfByte[i2];
      j = i1 + 1;
      i1 = paramArrayOfByte[i1];
      int i4 = paramArrayOfByte[j];
      i2 = i - 3;
      this._outputTail = paramBase64Variant.encodeBase64Chunk((paramInt << 8 | i1 & 0xFF) << 8 | i4 & 0xFF, this._outputBuffer, this._outputTail);
      paramInt = k - 1;
      i = paramInt;
      if (paramInt <= 0)
      {
        byte[] arrayOfByte = this._outputBuffer;
        paramInt = this._outputTail;
        this._outputTail = (paramInt + 1);
        arrayOfByte[paramInt] = 92;
        arrayOfByte = this._outputBuffer;
        paramInt = this._outputTail;
        this._outputTail = (paramInt + 1);
        arrayOfByte[paramInt] = 110;
        i = paramBase64Variant.getMaxLineLength() >> 2;
      }
      paramInt = j + 1;
      j = n;
      k = i;
      i1 = m;
      i = i2;
      break;
    }
  }
  
  protected final void _writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int k = this._outputEnd - 6;
    int j = paramBase64Variant.getMaxLineLength() >> 2;
    int i = paramInt1;
    paramInt1 = j;
    while (i <= paramInt2 - 3)
    {
      if (this._outputTail > k) {
        _flushBuffer();
      }
      int m = i + 1;
      i = paramArrayOfByte[i];
      j = m + 1;
      this._outputTail = paramBase64Variant.encodeBase64Chunk((i << 8 | paramArrayOfByte[m] & 0xFF) << 8 | paramArrayOfByte[j] & 0xFF, this._outputBuffer, this._outputTail);
      i = paramInt1 - 1;
      paramInt1 = i;
      if (i <= 0)
      {
        byte[] arrayOfByte = this._outputBuffer;
        paramInt1 = this._outputTail;
        this._outputTail = (paramInt1 + 1);
        arrayOfByte[paramInt1] = 92;
        arrayOfByte = this._outputBuffer;
        paramInt1 = this._outputTail;
        this._outputTail = (paramInt1 + 1);
        arrayOfByte[paramInt1] = 110;
        paramInt1 = paramBase64Variant.getMaxLineLength() >> 2;
      }
      i = j + 1;
    }
    j = paramInt2 - i;
    if (j > 0)
    {
      if (this._outputTail > k) {
        _flushBuffer();
      }
      k = i + 1;
      paramInt2 = paramArrayOfByte[i] << 16;
      paramInt1 = paramInt2;
      if (j == 2) {
        paramInt1 = paramInt2 | (paramArrayOfByte[k] & 0xFF) << 8;
      }
      this._outputTail = paramBase64Variant.encodeBase64Partial(paramInt1, j, this._outputBuffer, this._outputTail);
      return;
    }
  }
  
  protected final void _writePPFieldName(SerializableString paramSerializableString)
    throws IOException
  {
    int i = 1;
    int j = this._writeContext.writeFieldName(paramSerializableString.getValue());
    if (j == 4) {
      _reportError("Can not write a field name, expecting a value");
    }
    if (j == 1)
    {
      this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
      if (this._cfgUnqNames) {
        break label158;
      }
    }
    for (;;)
    {
      if (i != 0)
      {
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        byte[] arrayOfByte = this._outputBuffer;
        j = this._outputTail;
        this._outputTail = (j + 1);
        arrayOfByte[j] = 34;
      }
      _writeBytes(paramSerializableString.asQuotedUTF8());
      if (i != 0)
      {
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        paramSerializableString = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = (i + 1);
        paramSerializableString[i] = 34;
      }
      return;
      this._cfgPrettyPrinter.beforeObjectEntries(this);
      break;
      label158:
      i = 0;
    }
  }
  
  protected final void _writePPFieldName(String paramString)
    throws IOException
  {
    int i = this._writeContext.writeFieldName(paramString);
    if (i == 4) {
      _reportError("Can not write a field name, expecting a value");
    }
    if (i == 1) {
      this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
    }
    while (this._cfgUnqNames)
    {
      _writeStringSegments(paramString, false);
      return;
      this._cfgPrettyPrinter.beforeObjectEntries(this);
    }
    i = paramString.length();
    if (i > this._charBufferLength)
    {
      _writeStringSegments(paramString, true);
      return;
    }
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfByte[j] = 34;
    paramString.getChars(0, i, this._charBuffer, 0);
    if (i <= this._outputMaxContiguous)
    {
      if (this._outputTail + i > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(this._charBuffer, 0, i);
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      paramString = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = (i + 1);
      paramString[i] = 34;
      return;
      _writeStringSegments(this._charBuffer, 0, i);
    }
  }
  
  public void close()
    throws IOException
  {
    super.close();
    if ((this._outputBuffer != null) && (isEnabled(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT))) {
      for (;;)
      {
        JsonWriteContext localJsonWriteContext = getOutputContext();
        if (localJsonWriteContext.inArray())
        {
          writeEndArray();
        }
        else
        {
          if (!localJsonWriteContext.inObject()) {
            break;
          }
          writeEndObject();
        }
      }
    }
    _flushBuffer();
    this._outputTail = 0;
    if (this._outputStream != null)
    {
      if ((!this._ioContext.isResourceManaged()) && (!isEnabled(JsonGenerator.Feature.AUTO_CLOSE_TARGET))) {
        break label102;
      }
      this._outputStream.close();
    }
    for (;;)
    {
      _releaseBuffers();
      return;
      label102:
      if (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM)) {
        this._outputStream.flush();
      }
    }
  }
  
  public void flush()
    throws IOException
  {
    _flushBuffer();
    if ((this._outputStream != null) && (isEnabled(JsonGenerator.Feature.FLUSH_PASSED_TO_STREAM))) {
      this._outputStream.flush();
    }
  }
  
  public int getOutputBuffered()
  {
    return this._outputTail;
  }
  
  public Object getOutputTarget()
  {
    return this._outputStream;
  }
  
  public int writeBinary(Base64Variant paramBase64Variant, InputStream paramInputStream, int paramInt)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a binary value");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
    arrayOfByte = this._ioContext.allocBase64Buffer();
    if (paramInt < 0) {}
    for (;;)
    {
      try
      {
        paramInt = _writeBinary(paramBase64Variant, paramInputStream, arrayOfByte);
        this._ioContext.releaseBase64Buffer(arrayOfByte);
        if (this._outputTail >= this._outputEnd) {
          _flushBuffer();
        }
        paramBase64Variant = this._outputBuffer;
        i = this._outputTail;
        this._outputTail = (i + 1);
        paramBase64Variant[i] = 34;
        return paramInt;
      }
      finally
      {
        this._ioContext.releaseBase64Buffer(arrayOfByte);
      }
      i = _writeBinary(paramBase64Variant, paramInputStream, arrayOfByte, paramInt);
      if (i > 0) {
        _reportError("Too few bytes available: missing " + i + " bytes (out of " + paramInt + ")");
      }
    }
  }
  
  public void writeBinary(Base64Variant paramBase64Variant, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a binary value");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
    _writeBinary(paramBase64Variant, paramArrayOfByte, paramInt1, paramInt1 + paramInt2);
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    paramBase64Variant = this._outputBuffer;
    paramInt1 = this._outputTail;
    this._outputTail = (paramInt1 + 1);
    paramBase64Variant[paramInt1] = 34;
  }
  
  public void writeBoolean(boolean paramBoolean)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a boolean value");
    if (this._outputTail + 5 >= this._outputEnd) {
      _flushBuffer();
    }
    if (paramBoolean) {}
    for (byte[] arrayOfByte = TRUE_BYTES;; arrayOfByte = FALSE_BYTES)
    {
      int i = arrayOfByte.length;
      System.arraycopy(arrayOfByte, 0, this._outputBuffer, this._outputTail, i);
      this._outputTail += i;
      return;
    }
  }
  
  public final void writeEndArray()
    throws IOException
  {
    if (!this._writeContext.inArray()) {
      _reportError("Current context not an ARRAY but " + this._writeContext.getTypeDesc());
    }
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
    }
    for (;;)
    {
      this._writeContext = this._writeContext.getParent();
      return;
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = 93;
    }
  }
  
  public final void writeEndObject()
    throws IOException
  {
    if (!this._writeContext.inObject()) {
      _reportError("Current context not an object but " + this._writeContext.getTypeDesc());
    }
    if (this._cfgPrettyPrinter != null) {
      this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
    }
    for (;;)
    {
      this._writeContext = this._writeContext.getParent();
      return;
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      byte[] arrayOfByte = this._outputBuffer;
      int i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = 125;
    }
  }
  
  public void writeFieldName(SerializableString paramSerializableString)
    throws IOException
  {
    if (this._cfgPrettyPrinter != null)
    {
      _writePPFieldName(paramSerializableString);
      return;
    }
    int i = this._writeContext.writeFieldName(paramSerializableString.getValue());
    if (i == 4) {
      _reportError("Can not write a field name, expecting a value");
    }
    if (i == 1)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      arrayOfByte = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = 44;
    }
    if (this._cfgUnqNames)
    {
      _writeUnq(paramSerializableString);
      return;
    }
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
    i = paramSerializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
    if (i < 0) {
      _writeBytes(paramSerializableString.asQuotedUTF8());
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      paramSerializableString = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = (i + 1);
      paramSerializableString[i] = 34;
      return;
      this._outputTail += i;
    }
  }
  
  public void writeFieldName(String paramString)
    throws IOException
  {
    if (this._cfgPrettyPrinter != null)
    {
      _writePPFieldName(paramString);
      return;
    }
    int i = this._writeContext.writeFieldName(paramString);
    if (i == 4) {
      _reportError("Can not write a field name, expecting a value");
    }
    if (i == 1)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      arrayOfByte = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = 44;
    }
    if (this._cfgUnqNames)
    {
      _writeStringSegments(paramString, false);
      return;
    }
    i = paramString.length();
    if (i > this._charBufferLength)
    {
      _writeStringSegments(paramString, true);
      return;
    }
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfByte[j] = 34;
    if (i <= this._outputMaxContiguous)
    {
      if (this._outputTail + i > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(paramString, 0, i);
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      paramString = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = (i + 1);
      paramString[i] = 34;
      return;
      _writeStringSegments(paramString, 0, i);
    }
  }
  
  public void writeNull()
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a null");
    _writeNull();
  }
  
  public void writeNumber(double paramDouble)
    throws IOException, JsonGenerationException
  {
    if ((this._cfgNumbersAsStrings) || (((Double.isNaN(paramDouble)) || (Double.isInfinite(paramDouble))) && (isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))))
    {
      writeString(String.valueOf(paramDouble));
      return;
    }
    _verifyValueWrite("write a number");
    writeRaw(String.valueOf(paramDouble));
  }
  
  public void writeNumber(float paramFloat)
    throws IOException, JsonGenerationException
  {
    if ((this._cfgNumbersAsStrings) || (((Float.isNaN(paramFloat)) || (Float.isInfinite(paramFloat))) && (isEnabled(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS))))
    {
      writeString(String.valueOf(paramFloat));
      return;
    }
    _verifyValueWrite("write a number");
    writeRaw(String.valueOf(paramFloat));
  }
  
  public void writeNumber(int paramInt)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (this._outputTail + 11 >= this._outputEnd) {
      _flushBuffer();
    }
    if (this._cfgNumbersAsStrings)
    {
      _writeQuotedInt(paramInt);
      return;
    }
    this._outputTail = NumberOutput.outputInt(paramInt, this._outputBuffer, this._outputTail);
  }
  
  public void writeNumber(long paramLong)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (this._cfgNumbersAsStrings)
    {
      _writeQuotedLong(paramLong);
      return;
    }
    if (this._outputTail + 21 >= this._outputEnd) {
      _flushBuffer();
    }
    this._outputTail = NumberOutput.outputLong(paramLong, this._outputBuffer, this._outputTail);
  }
  
  public void writeNumber(String paramString)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (this._cfgNumbersAsStrings)
    {
      _writeQuotedRaw(paramString);
      return;
    }
    writeRaw(paramString);
  }
  
  public void writeNumber(BigDecimal paramBigDecimal)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (paramBigDecimal == null)
    {
      _writeNull();
      return;
    }
    if (this._cfgNumbersAsStrings)
    {
      if (isEnabled(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)) {}
      for (paramBigDecimal = paramBigDecimal.toPlainString();; paramBigDecimal = paramBigDecimal.toString())
      {
        _writeQuotedRaw(paramBigDecimal);
        return;
      }
    }
    if (isEnabled(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN))
    {
      writeRaw(paramBigDecimal.toPlainString());
      return;
    }
    writeRaw(paramBigDecimal.toString());
  }
  
  public void writeNumber(BigInteger paramBigInteger)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (paramBigInteger == null)
    {
      _writeNull();
      return;
    }
    if (this._cfgNumbersAsStrings)
    {
      _writeQuotedRaw(paramBigInteger.toString());
      return;
    }
    writeRaw(paramBigInteger.toString());
  }
  
  public void writeNumber(short paramShort)
    throws IOException, JsonGenerationException
  {
    _verifyValueWrite("write a number");
    if (this._outputTail + 6 >= this._outputEnd) {
      _flushBuffer();
    }
    if (this._cfgNumbersAsStrings)
    {
      _writeQuotedShort(paramShort);
      return;
    }
    this._outputTail = NumberOutput.outputInt(paramShort, this._outputBuffer, this._outputTail);
  }
  
  public void writeRaw(char paramChar)
    throws IOException, JsonGenerationException
  {
    if (this._outputTail + 3 >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i;
    if (paramChar <= '')
    {
      i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = ((byte)paramChar);
      return;
    }
    if (paramChar < 'ࠀ')
    {
      i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = ((byte)(paramChar >> '\006' | 0xC0));
      i = this._outputTail;
      this._outputTail = (i + 1);
      arrayOfByte[i] = ((byte)(paramChar & 0x3F | 0x80));
      return;
    }
    _outputRawMultiByteChar(paramChar, null, 0, 0);
  }
  
  public void writeRaw(SerializableString paramSerializableString)
    throws IOException, JsonGenerationException
  {
    paramSerializableString = paramSerializableString.asUnquotedUTF8();
    if (paramSerializableString.length > 0) {
      _writeBytes(paramSerializableString);
    }
  }
  
  public void writeRaw(String paramString)
    throws IOException, JsonGenerationException
  {
    int j = 0;
    int i = paramString.length();
    if (i > 0)
    {
      char[] arrayOfChar = this._charBuffer;
      int k = arrayOfChar.length;
      if (i < k) {
        k = i;
      }
      for (;;)
      {
        paramString.getChars(j, j + k, arrayOfChar, 0);
        writeRaw(arrayOfChar, 0, k);
        j += k;
        i -= k;
        break;
      }
    }
  }
  
  public void writeRaw(String paramString, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    if (paramInt2 > 0)
    {
      char[] arrayOfChar = this._charBuffer;
      int i = arrayOfChar.length;
      if (paramInt2 < i) {
        i = paramInt2;
      }
      for (;;)
      {
        paramString.getChars(paramInt1, paramInt1 + i, arrayOfChar, 0);
        writeRaw(arrayOfChar, 0, i);
        paramInt1 += i;
        paramInt2 -= i;
        break;
      }
    }
  }
  
  public final void writeRaw(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException, JsonGenerationException
  {
    int i = paramInt2 + paramInt2 + paramInt2;
    if (this._outputTail + i > this._outputEnd) {
      if (this._outputEnd < i) {
        _writeSegmentedRaw(paramArrayOfChar, paramInt1, paramInt2);
      }
    }
    for (;;)
    {
      return;
      _flushBuffer();
      i = paramInt2 + paramInt1;
      while (paramInt1 < i)
      {
        do
        {
          paramInt2 = paramArrayOfChar[paramInt1];
          if (paramInt2 > 127)
          {
            paramInt2 = paramInt1 + 1;
            paramInt1 = paramArrayOfChar[paramInt1];
            if (paramInt1 >= 2048) {
              break label191;
            }
            arrayOfByte = this._outputBuffer;
            j = this._outputTail;
            this._outputTail = (j + 1);
            arrayOfByte[j] = ((byte)(paramInt1 >> 6 | 0xC0));
            arrayOfByte = this._outputBuffer;
            j = this._outputTail;
            this._outputTail = (j + 1);
            arrayOfByte[j] = ((byte)(paramInt1 & 0x3F | 0x80));
            paramInt1 = paramInt2;
            break;
          }
          byte[] arrayOfByte = this._outputBuffer;
          int j = this._outputTail;
          this._outputTail = (j + 1);
          arrayOfByte[j] = ((byte)paramInt2);
          paramInt2 = paramInt1 + 1;
          paramInt1 = paramInt2;
        } while (paramInt2 < i);
        return;
        label191:
        paramInt1 = _outputRawMultiByteChar(paramInt1, paramArrayOfChar, paramInt2, i);
      }
    }
  }
  
  public void writeRawUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
    _writeBytes(paramArrayOfByte, paramInt1, paramInt2);
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    paramArrayOfByte = this._outputBuffer;
    paramInt1 = this._outputTail;
    this._outputTail = (paramInt1 + 1);
    paramArrayOfByte[paramInt1] = 34;
  }
  
  public void writeRawValue(SerializableString paramSerializableString)
    throws IOException
  {
    _verifyValueWrite("write a raw (unencoded) value");
    paramSerializableString = paramSerializableString.asUnquotedUTF8();
    if (paramSerializableString.length > 0) {
      _writeBytes(paramSerializableString);
    }
  }
  
  public final void writeStartArray()
    throws IOException
  {
    _verifyValueWrite("start an array");
    this._writeContext = this._writeContext.createChildArrayContext();
    if (this._cfgPrettyPrinter != null)
    {
      this._cfgPrettyPrinter.writeStartArray(this);
      return;
    }
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 91;
  }
  
  public final void writeStartObject()
    throws IOException
  {
    _verifyValueWrite("start an object");
    this._writeContext = this._writeContext.createChildObjectContext();
    if (this._cfgPrettyPrinter != null)
    {
      this._cfgPrettyPrinter.writeStartObject(this);
      return;
    }
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 123;
  }
  
  public final void writeString(SerializableString paramSerializableString)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
    i = paramSerializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
    if (i < 0) {
      _writeBytes(paramSerializableString.asQuotedUTF8());
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      paramSerializableString = this._outputBuffer;
      i = this._outputTail;
      this._outputTail = (i + 1);
      paramSerializableString[i] = 34;
      return;
      this._outputTail += i;
    }
  }
  
  public void writeString(String paramString)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (paramString == null)
    {
      _writeNull();
      return;
    }
    int i = paramString.length();
    if (i > this._outputMaxContiguous)
    {
      _writeStringSegments(paramString, true);
      return;
    }
    if (this._outputTail + i >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int j = this._outputTail;
    this._outputTail = (j + 1);
    arrayOfByte[j] = 34;
    _writeStringSegment(paramString, 0, i);
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    paramString = this._outputBuffer;
    i = this._outputTail;
    this._outputTail = (i + 1);
    paramString[i] = 34;
  }
  
  public void writeString(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
    if (paramInt2 <= this._outputMaxContiguous)
    {
      if (this._outputTail + paramInt2 > this._outputEnd) {
        _flushBuffer();
      }
      _writeStringSegment(paramArrayOfChar, paramInt1, paramInt2);
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      paramArrayOfChar = this._outputBuffer;
      paramInt1 = this._outputTail;
      this._outputTail = (paramInt1 + 1);
      paramArrayOfChar[paramInt1] = '"';
      return;
      _writeStringSegments(paramArrayOfChar, paramInt1, paramInt2);
    }
  }
  
  public void writeUTF8String(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    _verifyValueWrite("write a string");
    if (this._outputTail >= this._outputEnd) {
      _flushBuffer();
    }
    byte[] arrayOfByte = this._outputBuffer;
    int i = this._outputTail;
    this._outputTail = (i + 1);
    arrayOfByte[i] = 34;
    if (paramInt2 <= this._outputMaxContiguous) {
      _writeUTF8Segment(paramArrayOfByte, paramInt1, paramInt2);
    }
    for (;;)
    {
      if (this._outputTail >= this._outputEnd) {
        _flushBuffer();
      }
      paramArrayOfByte = this._outputBuffer;
      paramInt1 = this._outputTail;
      this._outputTail = (paramInt1 + 1);
      paramArrayOfByte[paramInt1] = 34;
      return;
      _writeUTF8Segments(paramArrayOfByte, paramInt1, paramInt2);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/json/UTF8JsonGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */