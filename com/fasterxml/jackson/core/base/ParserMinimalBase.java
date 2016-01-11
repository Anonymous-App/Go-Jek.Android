package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;

public abstract class ParserMinimalBase
  extends JsonParser
{
  protected static final int INT_BACKSLASH = 92;
  protected static final int INT_COLON = 58;
  protected static final int INT_COMMA = 44;
  protected static final int INT_CR = 13;
  protected static final int INT_E = 69;
  protected static final int INT_HASH = 35;
  protected static final int INT_LBRACKET = 91;
  protected static final int INT_LCURLY = 123;
  protected static final int INT_LF = 10;
  protected static final int INT_PERIOD = 46;
  protected static final int INT_QUOTE = 34;
  protected static final int INT_RBRACKET = 93;
  protected static final int INT_RCURLY = 125;
  protected static final int INT_SLASH = 47;
  protected static final int INT_SPACE = 32;
  protected static final int INT_TAB = 9;
  protected static final int INT_e = 101;
  protected JsonToken _currToken;
  protected JsonToken _lastClearedToken;
  
  protected ParserMinimalBase() {}
  
  protected ParserMinimalBase(int paramInt)
  {
    super(paramInt);
  }
  
  protected static String _ascii(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, "US-ASCII");
      return paramArrayOfByte;
    }
    catch (IOException paramArrayOfByte)
    {
      throw new RuntimeException(paramArrayOfByte);
    }
  }
  
  protected static byte[] _asciiBytes(String paramString)
  {
    byte[] arrayOfByte = new byte[paramString.length()];
    int i = 0;
    int j = paramString.length();
    while (i < j)
    {
      arrayOfByte[i] = ((byte)paramString.charAt(i));
      i += 1;
    }
    return arrayOfByte;
  }
  
  protected static final String _getCharDesc(int paramInt)
  {
    char c = (char)paramInt;
    if (Character.isISOControl(c)) {
      return "(CTRL-CHAR, code " + paramInt + ")";
    }
    if (paramInt > 255) {
      return "'" + c + "' (code " + paramInt + " / 0x" + Integer.toHexString(paramInt) + ")";
    }
    return "'" + c + "' (code " + paramInt + ")";
  }
  
  protected final JsonParseException _constructError(String paramString, Throwable paramThrowable)
  {
    return new JsonParseException(paramString, getCurrentLocation(), paramThrowable);
  }
  
  protected void _decodeBase64(String paramString, ByteArrayBuilder paramByteArrayBuilder, Base64Variant paramBase64Variant)
    throws IOException
  {
    try
    {
      paramBase64Variant.decode(paramString, paramByteArrayBuilder);
      return;
    }
    catch (IllegalArgumentException paramString)
    {
      _reportError(paramString.getMessage());
    }
  }
  
  protected abstract void _handleEOF()
    throws JsonParseException;
  
  protected char _handleUnrecognizedCharacterEscape(char paramChar)
    throws JsonProcessingException
  {
    if (isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {}
    while ((paramChar == '\'') && (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))) {
      return paramChar;
    }
    _reportError("Unrecognized character escape " + _getCharDesc(paramChar));
    return paramChar;
  }
  
  protected boolean _hasTextualNull(String paramString)
  {
    return "null".equals(paramString);
  }
  
  protected final void _reportError(String paramString)
    throws JsonParseException
  {
    throw _constructError(paramString);
  }
  
  protected void _reportInvalidEOF()
    throws JsonParseException
  {
    _reportInvalidEOF(" in " + this._currToken);
  }
  
  protected void _reportInvalidEOF(String paramString)
    throws JsonParseException
  {
    _reportError("Unexpected end-of-input" + paramString);
  }
  
  protected void _reportInvalidEOFInValue()
    throws JsonParseException
  {
    _reportInvalidEOF(" in a value");
  }
  
  protected void _reportMissingRootWS(int paramInt)
    throws JsonParseException
  {
    _reportUnexpectedChar(paramInt, "Expected space separating root-level values");
  }
  
  protected void _reportUnexpectedChar(int paramInt, String paramString)
    throws JsonParseException
  {
    if (paramInt < 0) {
      _reportInvalidEOF();
    }
    String str2 = "Unexpected character (" + _getCharDesc(paramInt) + ")";
    String str1 = str2;
    if (paramString != null) {
      str1 = str2 + ": " + paramString;
    }
    _reportError(str1);
  }
  
  protected final void _throwInternal() {}
  
  protected void _throwInvalidSpace(int paramInt)
    throws JsonParseException
  {
    paramInt = (char)paramInt;
    _reportError("Illegal character (" + _getCharDesc(paramInt) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
  }
  
  protected void _throwUnquotedSpace(int paramInt, String paramString)
    throws JsonParseException
  {
    if ((!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS)) || (paramInt > 32))
    {
      paramInt = (char)paramInt;
      _reportError("Illegal unquoted character (" + _getCharDesc(paramInt) + "): has to be escaped using backslash to be included in " + paramString);
    }
  }
  
  protected final void _wrapError(String paramString, Throwable paramThrowable)
    throws JsonParseException
  {
    throw _constructError(paramString, paramThrowable);
  }
  
  public void clearCurrentToken()
  {
    if (this._currToken != null)
    {
      this._lastClearedToken = this._currToken;
      this._currToken = null;
    }
  }
  
  public abstract void close()
    throws IOException;
  
  public abstract byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException;
  
  public abstract String getCurrentName()
    throws IOException;
  
  public JsonToken getCurrentToken()
  {
    return this._currToken;
  }
  
  public int getCurrentTokenId()
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == null) {
      return 0;
    }
    return localJsonToken.id();
  }
  
  public JsonToken getLastClearedToken()
  {
    return this._lastClearedToken;
  }
  
  public abstract JsonStreamContext getParsingContext();
  
  public abstract String getText()
    throws IOException;
  
  public abstract char[] getTextCharacters()
    throws IOException;
  
  public abstract int getTextLength()
    throws IOException;
  
  public abstract int getTextOffset()
    throws IOException;
  
  public boolean getValueAsBoolean(boolean paramBoolean)
    throws IOException
  {
    boolean bool2 = true;
    Object localObject = this._currToken;
    boolean bool1;
    if (localObject != null)
    {
      bool1 = bool2;
      switch (((JsonToken)localObject).id())
      {
      }
    }
    do
    {
      bool1 = paramBoolean;
      do
      {
        do
        {
          return bool1;
          localObject = getText().trim();
          bool1 = bool2;
        } while ("true".equals(localObject));
        if ("false".equals(localObject)) {
          return false;
        }
        if (!_hasTextualNull((String)localObject)) {
          break;
        }
        return false;
        bool1 = bool2;
      } while (getIntValue() != 0);
      return false;
      return false;
      localObject = getEmbeddedObject();
    } while (!(localObject instanceof Boolean));
    return ((Boolean)localObject).booleanValue();
  }
  
  public double getValueAsDouble(double paramDouble)
    throws IOException
  {
    Object localObject = this._currToken;
    if (localObject != null) {
      switch (((JsonToken)localObject).id())
      {
      }
    }
    do
    {
      return paramDouble;
      localObject = getText();
      if (_hasTextualNull((String)localObject)) {
        return 0.0D;
      }
      return NumberInput.parseAsDouble((String)localObject, paramDouble);
      return getDoubleValue();
      return 1.0D;
      return 0.0D;
      localObject = getEmbeddedObject();
    } while (!(localObject instanceof Number));
    return ((Number)localObject).doubleValue();
  }
  
  public int getValueAsInt()
    throws IOException
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      return getIntValue();
    }
    if (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
      return getIntValue();
    }
    return getValueAsInt(0);
  }
  
  public int getValueAsInt(int paramInt)
    throws IOException
  {
    Object localObject = this._currToken;
    int i;
    if (localObject == JsonToken.VALUE_NUMBER_INT) {
      i = getIntValue();
    }
    do
    {
      do
      {
        return i;
        if (localObject == JsonToken.VALUE_NUMBER_FLOAT) {
          return getIntValue();
        }
        i = paramInt;
      } while (localObject == null);
      switch (((JsonToken)localObject).id())
      {
      case 7: 
      case 8: 
      default: 
        return paramInt;
      case 6: 
        localObject = getText();
        if (_hasTextualNull((String)localObject)) {
          return 0;
        }
        return NumberInput.parseAsInt((String)localObject, paramInt);
      case 9: 
        return 1;
      case 10: 
        return 0;
      case 11: 
        return 0;
      }
      localObject = getEmbeddedObject();
      i = paramInt;
    } while (!(localObject instanceof Number));
    return ((Number)localObject).intValue();
  }
  
  public long getValueAsLong()
    throws IOException
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
      return getLongValue();
    }
    if (localJsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
      return getLongValue();
    }
    return getValueAsLong(0L);
  }
  
  public long getValueAsLong(long paramLong)
    throws IOException
  {
    Object localObject = this._currToken;
    long l;
    if (localObject == JsonToken.VALUE_NUMBER_INT) {
      l = getLongValue();
    }
    do
    {
      do
      {
        return l;
        if (localObject == JsonToken.VALUE_NUMBER_FLOAT) {
          return getLongValue();
        }
        l = paramLong;
      } while (localObject == null);
      switch (((JsonToken)localObject).id())
      {
      case 7: 
      case 8: 
      default: 
        return paramLong;
      case 6: 
        localObject = getText();
        if (_hasTextualNull((String)localObject)) {
          return 0L;
        }
        return NumberInput.parseAsLong((String)localObject, paramLong);
      case 9: 
        return 1L;
      case 10: 
      case 11: 
        return 0L;
      }
      localObject = getEmbeddedObject();
      l = paramLong;
    } while (!(localObject instanceof Number));
    return ((Number)localObject).longValue();
  }
  
  public String getValueAsString()
    throws IOException
  {
    if (this._currToken == JsonToken.VALUE_STRING) {
      return getText();
    }
    if (this._currToken == JsonToken.FIELD_NAME) {
      return getCurrentName();
    }
    return getValueAsString(null);
  }
  
  public String getValueAsString(String paramString)
    throws IOException
  {
    String str;
    if (this._currToken == JsonToken.VALUE_STRING) {
      str = getText();
    }
    do
    {
      do
      {
        do
        {
          return str;
          if (this._currToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
          }
          str = paramString;
        } while (this._currToken == null);
        str = paramString;
      } while (this._currToken == JsonToken.VALUE_NULL);
      str = paramString;
    } while (!this._currToken.isScalarValue());
    return getText();
  }
  
  public boolean hasCurrentToken()
  {
    return this._currToken != null;
  }
  
  public abstract boolean hasTextCharacters();
  
  public boolean hasToken(JsonToken paramJsonToken)
  {
    return this._currToken == paramJsonToken;
  }
  
  public boolean hasTokenId(int paramInt)
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == null) {
      if (paramInt != 0) {}
    }
    while (localJsonToken.id() == paramInt)
    {
      return true;
      return false;
    }
    return false;
  }
  
  public abstract boolean isClosed();
  
  public boolean isExpectedStartArrayToken()
  {
    return this._currToken == JsonToken.START_ARRAY;
  }
  
  public boolean isExpectedStartObjectToken()
  {
    return this._currToken == JsonToken.START_OBJECT;
  }
  
  public abstract JsonToken nextToken()
    throws IOException;
  
  public JsonToken nextValue()
    throws IOException
  {
    JsonToken localJsonToken2 = nextToken();
    JsonToken localJsonToken1 = localJsonToken2;
    if (localJsonToken2 == JsonToken.FIELD_NAME) {
      localJsonToken1 = nextToken();
    }
    return localJsonToken1;
  }
  
  public abstract void overrideCurrentName(String paramString);
  
  public JsonParser skipChildren()
    throws IOException
  {
    if ((this._currToken != JsonToken.START_OBJECT) && (this._currToken != JsonToken.START_ARRAY)) {
      return this;
    }
    int i = 1;
    int j;
    do
    {
      JsonToken localJsonToken;
      do
      {
        for (;;)
        {
          localJsonToken = nextToken();
          if (localJsonToken == null)
          {
            _handleEOF();
            return this;
          }
          if (!localJsonToken.isStructStart()) {
            break;
          }
          i += 1;
        }
      } while (!localJsonToken.isStructEnd());
      j = i - 1;
      i = j;
    } while (j != 0);
    return this;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/base/ParserMinimalBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */