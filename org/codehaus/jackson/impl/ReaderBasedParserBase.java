package org.codehaus.jackson.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.io.IOContext;

@Deprecated
public abstract class ReaderBasedParserBase
  extends JsonParserBase
{
  protected char[] _inputBuffer;
  protected Reader _reader;
  
  protected ReaderBasedParserBase(IOContext paramIOContext, int paramInt, Reader paramReader)
  {
    super(paramIOContext, paramInt);
    this._reader = paramReader;
    this._inputBuffer = paramIOContext.allocTokenBuffer();
  }
  
  protected void _closeInput()
    throws IOException
  {
    if (this._reader != null)
    {
      if ((this._ioContext.isResourceManaged()) || (isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE))) {
        this._reader.close();
      }
      this._reader = null;
    }
  }
  
  protected final boolean _matchToken(String paramString, int paramInt)
    throws IOException, JsonParseException
  {
    int j = paramString.length();
    int i;
    do
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        _reportInvalidEOFInValue();
      }
      if (this._inputBuffer[this._inputPtr] != paramString.charAt(paramInt)) {
        _reportInvalidToken(paramString.substring(0, paramInt), "'null', 'true', 'false' or NaN");
      }
      this._inputPtr += 1;
      i = paramInt + 1;
      paramInt = i;
    } while (i < j);
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {}
    while (!Character.isJavaIdentifierPart(this._inputBuffer[this._inputPtr])) {
      return true;
    }
    this._inputPtr += 1;
    _reportInvalidToken(paramString.substring(0, i), "'null', 'true', 'false' or NaN");
    return true;
  }
  
  protected void _releaseBuffers()
    throws IOException
  {
    super._releaseBuffers();
    char[] arrayOfChar = this._inputBuffer;
    if (arrayOfChar != null)
    {
      this._inputBuffer = null;
      this._ioContext.releaseTokenBuffer(arrayOfChar);
    }
  }
  
  protected void _reportInvalidToken(String paramString1, String paramString2)
    throws IOException, JsonParseException
  {
    paramString1 = new StringBuilder(paramString1);
    for (;;)
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {}
      char c;
      do
      {
        _reportError("Unrecognized token '" + paramString1.toString() + "': was expecting ");
        return;
        c = this._inputBuffer[this._inputPtr];
      } while (!Character.isJavaIdentifierPart(c));
      this._inputPtr += 1;
      paramString1.append(c);
    }
  }
  
  public Object getInputSource()
  {
    return this._reader;
  }
  
  protected char getNextChar(String paramString)
    throws IOException, JsonParseException
  {
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(paramString);
    }
    paramString = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    return paramString[i];
  }
  
  protected final boolean loadMore()
    throws IOException
  {
    boolean bool2 = false;
    this._currInputProcessed += this._inputEnd;
    this._currInputRowStart -= this._inputEnd;
    boolean bool1 = bool2;
    int i;
    if (this._reader != null)
    {
      i = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
      if (i <= 0) {
        break label74;
      }
      this._inputPtr = 0;
      this._inputEnd = i;
      bool1 = true;
    }
    label74:
    do
    {
      return bool1;
      _closeInput();
      bool1 = bool2;
    } while (i != 0);
    throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd);
  }
  
  public int releaseBuffered(Writer paramWriter)
    throws IOException
  {
    int i = this._inputEnd - this._inputPtr;
    if (i < 1) {
      return 0;
    }
    int j = this._inputPtr;
    paramWriter.write(this._inputBuffer, j, i);
    return i;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/org/codehaus/jackson/impl/ReaderBasedParserBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */