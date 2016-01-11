package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class ReaderBasedJsonParser
  extends ParserBase
{
  protected static final int[] _icLatin1 = ;
  protected boolean _bufferRecyclable;
  protected final int _hashSeed;
  protected char[] _inputBuffer;
  protected ObjectCodec _objectCodec;
  protected Reader _reader;
  protected final CharsToNameCanonicalizer _symbols;
  protected boolean _tokenIncomplete = false;
  
  public ReaderBasedJsonParser(IOContext paramIOContext, int paramInt, Reader paramReader, ObjectCodec paramObjectCodec, CharsToNameCanonicalizer paramCharsToNameCanonicalizer)
  {
    super(paramIOContext, paramInt);
    this._reader = paramReader;
    this._inputBuffer = paramIOContext.allocTokenBuffer();
    this._inputPtr = 0;
    this._inputEnd = 0;
    this._objectCodec = paramObjectCodec;
    this._symbols = paramCharsToNameCanonicalizer;
    this._hashSeed = paramCharsToNameCanonicalizer.hashSeed();
    this._bufferRecyclable = true;
  }
  
  public ReaderBasedJsonParser(IOContext paramIOContext, int paramInt1, Reader paramReader, ObjectCodec paramObjectCodec, CharsToNameCanonicalizer paramCharsToNameCanonicalizer, char[] paramArrayOfChar, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    super(paramIOContext, paramInt1);
    this._reader = paramReader;
    this._inputBuffer = paramArrayOfChar;
    this._inputPtr = paramInt2;
    this._inputEnd = paramInt3;
    this._objectCodec = paramObjectCodec;
    this._symbols = paramCharsToNameCanonicalizer;
    this._hashSeed = paramCharsToNameCanonicalizer.hashSeed();
    this._bufferRecyclable = paramBoolean;
  }
  
  private String _handleOddName2(int paramInt1, int paramInt2, int[] paramArrayOfInt)
    throws IOException
  {
    this._textBuffer.resetWithShared(this._inputBuffer, paramInt1, this._inputPtr - paramInt1);
    char[] arrayOfChar = this._textBuffer.getCurrentSegment();
    paramInt1 = this._textBuffer.getCurrentSegmentSize();
    int k = paramArrayOfInt.length;
    for (;;)
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {}
      int j;
      for (;;)
      {
        this._textBuffer.setCurrentLength(paramInt1);
        paramArrayOfInt = this._textBuffer;
        arrayOfChar = paramArrayOfInt.getTextBuffer();
        paramInt1 = paramArrayOfInt.getTextOffset();
        j = paramArrayOfInt.size();
        return this._symbols.findSymbol(arrayOfChar, paramInt1, j, paramInt2);
        int i = this._inputBuffer[this._inputPtr];
        if (i <= k)
        {
          if (paramArrayOfInt[i] != 0) {}
        }
        else {
          while (Character.isJavaIdentifierPart(i))
          {
            this._inputPtr += 1;
            paramInt2 = paramInt2 * 33 + i;
            j = paramInt1 + 1;
            arrayOfChar[paramInt1] = i;
            if (j < arrayOfChar.length) {
              break label188;
            }
            arrayOfChar = this._textBuffer.finishCurrentSegment();
            paramInt1 = 0;
            break;
          }
        }
      }
      label188:
      paramInt1 = j;
    }
  }
  
  private final void _matchFalse()
    throws IOException
  {
    int i = this._inputPtr;
    if (i + 4 < this._inputEnd)
    {
      char[] arrayOfChar = this._inputBuffer;
      if (arrayOfChar[i] == 'a')
      {
        i += 1;
        if (arrayOfChar[i] == 'l')
        {
          i += 1;
          if (arrayOfChar[i] == 's')
          {
            i += 1;
            if (arrayOfChar[i] == 'e')
            {
              i += 1;
              int j = arrayOfChar[i];
              if ((j < 48) || (j == 93) || (j == 125))
              {
                this._inputPtr = i;
                return;
              }
            }
          }
        }
      }
    }
    _matchToken("false", 1);
  }
  
  private final void _matchNull()
    throws IOException
  {
    int i = this._inputPtr;
    if (i + 3 < this._inputEnd)
    {
      char[] arrayOfChar = this._inputBuffer;
      if (arrayOfChar[i] == 'u')
      {
        i += 1;
        if (arrayOfChar[i] == 'l')
        {
          i += 1;
          if (arrayOfChar[i] == 'l')
          {
            i += 1;
            int j = arrayOfChar[i];
            if ((j < 48) || (j == 93) || (j == 125))
            {
              this._inputPtr = i;
              return;
            }
          }
        }
      }
    }
    _matchToken("null", 1);
  }
  
  private final void _matchTrue()
    throws IOException
  {
    int i = this._inputPtr;
    if (i + 3 < this._inputEnd)
    {
      char[] arrayOfChar = this._inputBuffer;
      if (arrayOfChar[i] == 'r')
      {
        i += 1;
        if (arrayOfChar[i] == 'u')
        {
          i += 1;
          if (arrayOfChar[i] == 'e')
          {
            i += 1;
            int j = arrayOfChar[i];
            if ((j < 48) || (j == 93) || (j == 125))
            {
              this._inputPtr = i;
              return;
            }
          }
        }
      }
    }
    _matchToken("true", 1);
  }
  
  private final JsonToken _nextAfterName()
  {
    this._nameCopied = false;
    JsonToken localJsonToken = this._nextToken;
    this._nextToken = null;
    if (localJsonToken == JsonToken.START_ARRAY) {
      this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
    }
    for (;;)
    {
      this._currToken = localJsonToken;
      return localJsonToken;
      if (localJsonToken == JsonToken.START_OBJECT) {
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
      }
    }
  }
  
  private final JsonToken _nextTokenNotInObject(int paramInt)
    throws IOException
  {
    if (paramInt == 34)
    {
      this._tokenIncomplete = true;
      localJsonToken = JsonToken.VALUE_STRING;
      this._currToken = localJsonToken;
      return localJsonToken;
    }
    switch (paramInt)
    {
    default: 
      localJsonToken = _handleOddValue(paramInt);
      this._currToken = localJsonToken;
      return localJsonToken;
    case 91: 
      this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
      localJsonToken = JsonToken.START_ARRAY;
      this._currToken = localJsonToken;
      return localJsonToken;
    case 123: 
      this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
      localJsonToken = JsonToken.START_OBJECT;
      this._currToken = localJsonToken;
      return localJsonToken;
    case 116: 
      _matchToken("true", 1);
      localJsonToken = JsonToken.VALUE_TRUE;
      this._currToken = localJsonToken;
      return localJsonToken;
    case 102: 
      _matchToken("false", 1);
      localJsonToken = JsonToken.VALUE_FALSE;
      this._currToken = localJsonToken;
      return localJsonToken;
    case 110: 
      _matchToken("null", 1);
      localJsonToken = JsonToken.VALUE_NULL;
      this._currToken = localJsonToken;
      return localJsonToken;
    case 45: 
      localJsonToken = _parseNegNumber();
      this._currToken = localJsonToken;
      return localJsonToken;
    }
    JsonToken localJsonToken = _parsePosNumber(paramInt);
    this._currToken = localJsonToken;
    return localJsonToken;
  }
  
  private final JsonToken _parseFloat(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, int paramInt4)
    throws IOException
  {
    int i2 = this._inputEnd;
    int k = 0;
    int m = 0;
    int i = paramInt1;
    int j = paramInt3;
    if (paramInt1 == 46)
    {
      i = paramInt3;
      paramInt1 = m;
    }
    int i1;
    int n;
    for (;;)
    {
      if (i >= i2) {
        return _parseNumber2(paramBoolean, paramInt2);
      }
      arrayOfChar = this._inputBuffer;
      paramInt3 = i + 1;
      m = arrayOfChar[i];
      if ((m < 48) || (m > 57))
      {
        k = paramInt1;
        i = m;
        j = paramInt3;
        if (paramInt1 == 0)
        {
          reportUnexpectedNumberChar(m, "Decimal point not followed by a digit");
          j = paramInt3;
          i = m;
          k = paramInt1;
        }
        paramInt1 = j;
        j = 0;
        i1 = 0;
        if (i != 101)
        {
          n = paramInt1;
          m = i;
          if (i != 69) {
            break label320;
          }
        }
        if (paramInt1 < i2) {
          break;
        }
        this._inputPtr = paramInt2;
        return _parseNumber2(paramBoolean, paramInt2);
      }
      paramInt1 += 1;
      i = paramInt3;
    }
    char[] arrayOfChar = this._inputBuffer;
    i = paramInt1 + 1;
    paramInt3 = arrayOfChar[paramInt1];
    if ((paramInt3 == 45) || (paramInt3 == 43))
    {
      if (i >= i2)
      {
        this._inputPtr = paramInt2;
        return _parseNumber2(paramBoolean, paramInt2);
      }
      arrayOfChar = this._inputBuffer;
      paramInt1 = i + 1;
      paramInt3 = arrayOfChar[i];
      i = i1;
    }
    for (;;)
    {
      if ((paramInt3 <= 57) && (paramInt3 >= 48))
      {
        i += 1;
        if (paramInt1 >= i2)
        {
          this._inputPtr = paramInt2;
          return _parseNumber2(paramBoolean, paramInt2);
        }
        paramInt3 = this._inputBuffer[paramInt1];
        paramInt1 += 1;
      }
      else
      {
        j = i;
        n = paramInt1;
        m = paramInt3;
        if (i == 0)
        {
          reportUnexpectedNumberChar(paramInt3, "Exponent indicator not followed by a digit");
          m = paramInt3;
          n = paramInt1;
          j = i;
        }
        label320:
        paramInt1 = n - 1;
        this._inputPtr = paramInt1;
        if (this._parsingContext.inRoot()) {
          _verifyRootSpace(m);
        }
        this._textBuffer.resetWithShared(this._inputBuffer, paramInt2, paramInt1 - paramInt2);
        return resetFloat(paramBoolean, paramInt4, k, j);
        paramInt1 = i;
        i = i1;
      }
    }
  }
  
  private String _parseName2(int paramInt1, int paramInt2, int paramInt3)
    throws IOException
  {
    this._textBuffer.resetWithShared(this._inputBuffer, paramInt1, this._inputPtr - paramInt1);
    Object localObject = this._textBuffer.getCurrentSegment();
    paramInt1 = this._textBuffer.getCurrentSegmentSize();
    for (;;)
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        _reportInvalidEOF(": was expecting closing '" + (char)paramInt3 + "' for name");
      }
      char[] arrayOfChar = this._inputBuffer;
      int k = this._inputPtr;
      this._inputPtr = (k + 1);
      int j = arrayOfChar[k];
      int i = j;
      if (j <= 92)
      {
        if (j != 92) {
          break label174;
        }
        i = _decodeEscaped();
      }
      for (;;)
      {
        paramInt2 = paramInt2 * 33 + i;
        k = paramInt1 + 1;
        localObject[paramInt1] = i;
        if (k < localObject.length) {
          break label262;
        }
        localObject = this._textBuffer.finishCurrentSegment();
        paramInt1 = 0;
        break;
        label174:
        i = j;
        if (j <= paramInt3)
        {
          if (j == paramInt3)
          {
            this._textBuffer.setCurrentLength(paramInt1);
            localObject = this._textBuffer;
            arrayOfChar = ((TextBuffer)localObject).getTextBuffer();
            paramInt1 = ((TextBuffer)localObject).getTextOffset();
            paramInt3 = ((TextBuffer)localObject).size();
            return this._symbols.findSymbol(arrayOfChar, paramInt1, paramInt3, paramInt2);
          }
          i = j;
          if (j < 32)
          {
            _throwUnquotedSpace(j, "name");
            i = j;
          }
        }
      }
      label262:
      paramInt1 = k;
    }
  }
  
  private final JsonToken _parseNumber2(boolean paramBoolean, int paramInt)
    throws IOException
  {
    int k = paramInt;
    if (paramBoolean) {
      k = paramInt + 1;
    }
    this._inputPtr = k;
    Object localObject2 = this._textBuffer.emptyAndGetCurrentSegment();
    paramInt = 0;
    if (paramBoolean)
    {
      localObject2[0] = 45;
      paramInt = 0 + 1;
    }
    int m = 0;
    Object localObject1;
    int i;
    int j;
    int n;
    label104:
    int i3;
    if (this._inputPtr < this._inputEnd)
    {
      localObject1 = this._inputBuffer;
      k = this._inputPtr;
      this._inputPtr = (k + 1);
      i = localObject1[k];
      j = i;
      if (i == 48) {
        j = _verifyNoLeadingZeroes();
      }
      n = 0;
      i = j;
      if ((i < 48) || (i > 57)) {
        break label898;
      }
      m += 1;
      localObject1 = localObject2;
      k = paramInt;
      if (paramInt >= localObject2.length)
      {
        localObject1 = this._textBuffer.finishCurrentSegment();
        k = 0;
      }
      paramInt = k + 1;
      localObject1[k] = i;
      if ((this._inputPtr < this._inputEnd) || (loadMore())) {
        break label209;
      }
      i = 0;
      k = 1;
      i3 = m;
    }
    for (;;)
    {
      if (i3 == 0)
      {
        return _handleInvalidNumberStart(i, paramBoolean);
        i = getNextChar("No digit following minus sign");
        break;
        label209:
        localObject2 = this._inputBuffer;
        k = this._inputPtr;
        this._inputPtr = (k + 1);
        i = localObject2[k];
        localObject2 = localObject1;
        break label104;
      }
      int i4 = 0;
      n = 0;
      int i1;
      if (i == 46)
      {
        m = paramInt + 1;
        localObject1[paramInt] = i;
        if ((this._inputPtr >= this._inputEnd) && (!loadMore()))
        {
          i1 = 1;
          label285:
          j = i;
          k = i1;
          i4 = n;
          localObject2 = localObject1;
          paramInt = m;
          if (n == 0)
          {
            reportUnexpectedNumberChar(i, "Decimal point not followed by a digit");
            paramInt = m;
            localObject2 = localObject1;
            i4 = n;
            k = i1;
            j = i;
          }
        }
      }
      for (;;)
      {
        int i5 = 0;
        n = 0;
        int i7;
        int i2;
        int i6;
        if (j != 101)
        {
          i7 = j;
          i2 = k;
          i6 = paramInt;
          if (j != 69) {}
        }
        else
        {
          localObject1 = localObject2;
          m = paramInt;
          if (paramInt >= localObject2.length)
          {
            localObject1 = this._textBuffer.finishCurrentSegment();
            m = 0;
          }
          paramInt = m + 1;
          localObject1[m] = j;
          if (this._inputPtr >= this._inputEnd) {
            break label819;
          }
          localObject2 = this._inputBuffer;
          m = this._inputPtr;
          this._inputPtr = (m + 1);
          i = localObject2[m];
          label439:
          if ((i != 45) && (i != 43)) {
            break label881;
          }
          if (paramInt < localObject1.length) {
            break label878;
          }
          localObject1 = this._textBuffer.finishCurrentSegment();
          paramInt = 0;
          label469:
          localObject1[paramInt] = i;
          if (this._inputPtr >= this._inputEnd) {
            break label830;
          }
          localObject2 = this._inputBuffer;
          m = this._inputPtr;
          this._inputPtr = (m + 1);
          i = localObject2[m];
          label511:
          paramInt += 1;
          i2 = n;
        }
        for (;;)
        {
          i1 = k;
          n = i2;
          m = paramInt;
          if (i <= 57)
          {
            i1 = k;
            n = i2;
            m = paramInt;
            if (i >= 48)
            {
              n = i2 + 1;
              localObject2 = localObject1;
              m = paramInt;
              if (paramInt >= localObject1.length)
              {
                localObject2 = this._textBuffer.finishCurrentSegment();
                m = 0;
              }
              paramInt = m + 1;
              localObject2[m] = i;
              if ((this._inputPtr < this._inputEnd) || (loadMore())) {
                break label841;
              }
              i1 = 1;
              m = paramInt;
            }
          }
          i7 = i;
          i2 = i1;
          i5 = n;
          i6 = m;
          if (n == 0)
          {
            reportUnexpectedNumberChar(i, "Exponent indicator not followed by a digit");
            i6 = m;
            i5 = n;
            i2 = i1;
            i7 = i;
          }
          if (i2 == 0)
          {
            this._inputPtr -= 1;
            if (this._parsingContext.inRoot()) {
              _verifyRootSpace(i7);
            }
          }
          this._textBuffer.setCurrentLength(i6);
          return reset(paramBoolean, i3, i4, i5);
          localObject2 = this._inputBuffer;
          paramInt = this._inputPtr;
          this._inputPtr = (paramInt + 1);
          j = localObject2[paramInt];
          i = j;
          i1 = k;
          if (j < 48) {
            break label285;
          }
          i = j;
          i1 = k;
          if (j > 57) {
            break label285;
          }
          n += 1;
          localObject2 = localObject1;
          paramInt = m;
          if (m >= localObject1.length)
          {
            localObject2 = this._textBuffer.finishCurrentSegment();
            paramInt = 0;
          }
          localObject2[paramInt] = j;
          m = paramInt + 1;
          i = j;
          localObject1 = localObject2;
          break;
          label819:
          i = getNextChar("expected a digit for number exponent");
          break label439;
          label830:
          i = getNextChar("expected a digit for number exponent");
          break label511;
          label841:
          localObject1 = this._inputBuffer;
          m = this._inputPtr;
          this._inputPtr = (m + 1);
          i = localObject1[m];
          i2 = n;
          localObject1 = localObject2;
          continue;
          label878:
          break label469;
          label881:
          i2 = n;
        }
        j = i;
        localObject2 = localObject1;
      }
      label898:
      k = n;
      i3 = m;
      localObject1 = localObject2;
    }
  }
  
  private final int _skipAfterComma2()
    throws IOException
  {
    while ((this._inputPtr < this._inputEnd) || (loadMore()))
    {
      char[] arrayOfChar = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      i = arrayOfChar[i];
      if (i > 32)
      {
        if (i == 47) {
          _skipComment();
        } else if ((i != 35) || (!_skipYAMLComment())) {
          return i;
        }
      }
      else if (i < 32) {
        if (i == 10)
        {
          this._currInputRow += 1;
          this._currInputRowStart = this._inputPtr;
        }
        else if (i == 13)
        {
          _skipCR();
        }
        else if (i != 9)
        {
          _throwInvalidSpace(i);
        }
      }
    }
    throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
  }
  
  private void _skipCComment()
    throws IOException
  {
    for (;;)
    {
      int i;
      if ((this._inputPtr < this._inputEnd) || (loadMore()))
      {
        char[] arrayOfChar = this._inputBuffer;
        i = this._inputPtr;
        this._inputPtr = (i + 1);
        i = arrayOfChar[i];
        if (i > 42) {
          continue;
        }
        if (i != 42) {
          break label102;
        }
        if ((this._inputPtr < this._inputEnd) || (loadMore())) {}
      }
      else
      {
        _reportInvalidEOF(" in a comment");
        return;
      }
      if (this._inputBuffer[this._inputPtr] == '/')
      {
        this._inputPtr += 1;
        return;
        label102:
        if (i < 32) {
          if (i == 10)
          {
            this._currInputRow += 1;
            this._currInputRowStart = this._inputPtr;
          }
          else if (i == 13)
          {
            _skipCR();
          }
          else if (i != 9)
          {
            _throwInvalidSpace(i);
          }
        }
      }
    }
  }
  
  private final int _skipColon()
    throws IOException
  {
    if (this._inputPtr + 4 >= this._inputEnd) {
      return _skipColon2(false);
    }
    int j = this._inputBuffer[this._inputPtr];
    char[] arrayOfChar;
    int i;
    if (j == 58)
    {
      arrayOfChar = this._inputBuffer;
      i = this._inputPtr + 1;
      this._inputPtr = i;
      i = arrayOfChar[i];
      if (i > 32)
      {
        if ((i == 47) || (i == 35)) {
          return _skipColon2(true);
        }
        this._inputPtr += 1;
        return i;
      }
      if ((i == 32) || (i == 9))
      {
        arrayOfChar = this._inputBuffer;
        i = this._inputPtr + 1;
        this._inputPtr = i;
        i = arrayOfChar[i];
        if (i > 32)
        {
          if ((i == 47) || (i == 35)) {
            return _skipColon2(true);
          }
          this._inputPtr += 1;
          return i;
        }
      }
      return _skipColon2(true);
    }
    if (j != 32)
    {
      i = j;
      if (j != 9) {}
    }
    else
    {
      arrayOfChar = this._inputBuffer;
      i = this._inputPtr + 1;
      this._inputPtr = i;
      i = arrayOfChar[i];
    }
    if (i == 58)
    {
      arrayOfChar = this._inputBuffer;
      i = this._inputPtr + 1;
      this._inputPtr = i;
      i = arrayOfChar[i];
      if (i > 32)
      {
        if ((i == 47) || (i == 35)) {
          return _skipColon2(true);
        }
        this._inputPtr += 1;
        return i;
      }
      if ((i == 32) || (i == 9))
      {
        arrayOfChar = this._inputBuffer;
        i = this._inputPtr + 1;
        this._inputPtr = i;
        i = arrayOfChar[i];
        if (i > 32)
        {
          if ((i == 47) || (i == 35)) {
            return _skipColon2(true);
          }
          this._inputPtr += 1;
          return i;
        }
      }
      return _skipColon2(true);
    }
    return _skipColon2(false);
  }
  
  private final int _skipColon2(boolean paramBoolean)
    throws IOException
  {
    for (;;)
    {
      if (this._inputPtr >= this._inputEnd) {
        loadMoreGuaranteed();
      }
      char[] arrayOfChar = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      i = arrayOfChar[i];
      if (i > 32)
      {
        if (i == 47)
        {
          _skipComment();
        }
        else if ((i != 35) || (!_skipYAMLComment()))
        {
          if (paramBoolean) {
            return i;
          }
          if (i != 58)
          {
            if (i < 32) {
              _throwInvalidSpace(i);
            }
            _reportUnexpectedChar(i, "was expecting a colon to separate field name and value");
          }
          paramBoolean = true;
        }
      }
      else if (i < 32) {
        if (i == 10)
        {
          this._currInputRow += 1;
          this._currInputRowStart = this._inputPtr;
        }
        else if (i == 13)
        {
          _skipCR();
        }
        else if (i != 9)
        {
          _throwInvalidSpace(i);
        }
      }
    }
  }
  
  private final int _skipComma(int paramInt)
    throws IOException
  {
    if (paramInt != 44) {
      _reportUnexpectedChar(paramInt, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
    }
    while (this._inputPtr < this._inputEnd)
    {
      char[] arrayOfChar = this._inputBuffer;
      paramInt = this._inputPtr;
      this._inputPtr = (paramInt + 1);
      paramInt = arrayOfChar[paramInt];
      if (paramInt > 32)
      {
        if ((paramInt == 47) || (paramInt == 35))
        {
          this._inputPtr -= 1;
          return _skipAfterComma2();
        }
        return paramInt;
      }
      if (paramInt < 32) {
        if (paramInt == 10)
        {
          this._currInputRow += 1;
          this._currInputRowStart = this._inputPtr;
        }
        else if (paramInt == 13)
        {
          _skipCR();
        }
        else if (paramInt != 9)
        {
          _throwInvalidSpace(paramInt);
        }
      }
    }
    return _skipAfterComma2();
  }
  
  private void _skipComment()
    throws IOException
  {
    if (!isEnabled(JsonParser.Feature.ALLOW_COMMENTS)) {
      _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
    }
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(" in a comment");
    }
    char[] arrayOfChar = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    i = arrayOfChar[i];
    if (i == 47)
    {
      _skipLine();
      return;
    }
    if (i == 42)
    {
      _skipCComment();
      return;
    }
    _reportUnexpectedChar(i, "was expecting either '*' or '/' for a comment");
  }
  
  private void _skipLine()
    throws IOException
  {
    for (;;)
    {
      int i;
      if ((this._inputPtr < this._inputEnd) || (loadMore()))
      {
        char[] arrayOfChar = this._inputBuffer;
        i = this._inputPtr;
        this._inputPtr = (i + 1);
        i = arrayOfChar[i];
        if (i >= 32) {
          continue;
        }
        if (i == 10)
        {
          this._currInputRow += 1;
          this._currInputRowStart = this._inputPtr;
        }
      }
      else
      {
        return;
      }
      if (i == 13)
      {
        _skipCR();
        return;
      }
      if (i != 9) {
        _throwInvalidSpace(i);
      }
    }
  }
  
  private final int _skipWSOrEnd()
    throws IOException
  {
    int i;
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      i = _eofAsNextChar();
    }
    char[] arrayOfChar;
    int j;
    do
    {
      return i;
      arrayOfChar = this._inputBuffer;
      i = this._inputPtr;
      this._inputPtr = (i + 1);
      j = arrayOfChar[i];
      if (j <= 32) {
        break label81;
      }
      if (j == 47) {
        break;
      }
      i = j;
    } while (j != 35);
    this._inputPtr -= 1;
    return _skipWSOrEnd2();
    label81:
    if (j != 32)
    {
      if (j != 10) {
        break label178;
      }
      this._currInputRow += 1;
      this._currInputRowStart = this._inputPtr;
    }
    for (;;)
    {
      if (this._inputPtr >= this._inputEnd) {
        break label265;
      }
      arrayOfChar = this._inputBuffer;
      i = this._inputPtr;
      this._inputPtr = (i + 1);
      j = arrayOfChar[i];
      if (j > 32)
      {
        if (j != 47)
        {
          i = j;
          if (j != 35) {
            break;
          }
        }
        this._inputPtr -= 1;
        return _skipWSOrEnd2();
        label178:
        if (j == 13)
        {
          _skipCR();
          continue;
        }
        if (j == 9) {
          continue;
        }
        _throwInvalidSpace(j);
        continue;
      }
      if (j != 32) {
        if (j == 10)
        {
          this._currInputRow += 1;
          this._currInputRowStart = this._inputPtr;
        }
        else if (j == 13)
        {
          _skipCR();
        }
        else if (j != 9)
        {
          _throwInvalidSpace(j);
        }
      }
    }
    label265:
    return _skipWSOrEnd2();
  }
  
  private int _skipWSOrEnd2()
    throws IOException
  {
    for (;;)
    {
      int i;
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        i = _eofAsNextChar();
      }
      int j;
      do
      {
        do
        {
          return i;
          char[] arrayOfChar = this._inputBuffer;
          i = this._inputPtr;
          this._inputPtr = (i + 1);
          j = arrayOfChar[i];
          if (j <= 32) {
            break label85;
          }
          if (j == 47)
          {
            _skipComment();
            break;
          }
          i = j;
        } while (j != 35);
        i = j;
      } while (!_skipYAMLComment());
      continue;
      label85:
      if (j != 32) {
        if (j == 10)
        {
          this._currInputRow += 1;
          this._currInputRowStart = this._inputPtr;
        }
        else if (j == 13)
        {
          _skipCR();
        }
        else if (j != 9)
        {
          _throwInvalidSpace(j);
        }
      }
    }
  }
  
  private boolean _skipYAMLComment()
    throws IOException
  {
    if (!isEnabled(JsonParser.Feature.ALLOW_YAML_COMMENTS)) {
      return false;
    }
    _skipLine();
    return true;
  }
  
  private char _verifyNLZ2()
    throws IOException
  {
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {}
    char c2;
    do
    {
      for (char c1 = '0'; (this._inputPtr >= this._inputEnd) && (!loadMore()); c1 = c2) {
        do
        {
          return c1;
          c2 = this._inputBuffer[this._inputPtr];
          if ((c2 < '0') || (c2 > '9')) {
            return '0';
          }
          if (!isEnabled(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
          }
          this._inputPtr += 1;
          c1 = c2;
        } while (c2 != '0');
      }
      c2 = this._inputBuffer[this._inputPtr];
      if ((c2 < '0') || (c2 > '9')) {
        return '0';
      }
      this._inputPtr += 1;
      c1 = c2;
    } while (c2 == '0');
    return c2;
  }
  
  private final char _verifyNoLeadingZeroes()
    throws IOException
  {
    if (this._inputPtr < this._inputEnd)
    {
      int i = this._inputBuffer[this._inputPtr];
      if ((i < 48) || (i > 57)) {
        return '0';
      }
    }
    return _verifyNLZ2();
  }
  
  private final void _verifyRootSpace(int paramInt)
    throws IOException
  {
    this._inputPtr += 1;
    switch (paramInt)
    {
    default: 
      _reportMissingRootWS(paramInt);
    case 9: 
    case 32: 
      return;
    case 13: 
      _skipCR();
      return;
    }
    this._currInputRow += 1;
    this._currInputRowStart = this._inputPtr;
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
  
  protected byte[] _decodeBase64(Base64Variant paramBase64Variant)
    throws IOException
  {
    ByteArrayBuilder localByteArrayBuilder = _getByteArrayBuilder();
    for (;;)
    {
      if (this._inputPtr >= this._inputEnd) {
        loadMoreGuaranteed();
      }
      char[] arrayOfChar = this._inputBuffer;
      int i = this._inputPtr;
      this._inputPtr = (i + 1);
      char c = arrayOfChar[i];
      if (c > ' ')
      {
        int j = paramBase64Variant.decodeBase64Char(c);
        i = j;
        if (j < 0)
        {
          if (c == '"') {
            return localByteArrayBuilder.toByteArray();
          }
          i = _decodeBase64Escape(paramBase64Variant, c, 0);
          if (i < 0) {}
        }
        else
        {
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfChar = this._inputBuffer;
          j = this._inputPtr;
          this._inputPtr = (j + 1);
          c = arrayOfChar[j];
          int k = paramBase64Variant.decodeBase64Char(c);
          j = k;
          if (k < 0) {
            j = _decodeBase64Escape(paramBase64Variant, c, 1);
          }
          int m = i << 6 | j;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfChar = this._inputBuffer;
          i = this._inputPtr;
          this._inputPtr = (i + 1);
          c = arrayOfChar[i];
          j = paramBase64Variant.decodeBase64Char(c);
          k = j;
          if (j < 0)
          {
            i = j;
            if (j != -2)
            {
              if ((c == '"') && (!paramBase64Variant.usesPadding()))
              {
                localByteArrayBuilder.append(m >> 4);
                return localByteArrayBuilder.toByteArray();
              }
              i = _decodeBase64Escape(paramBase64Variant, c, 2);
            }
            k = i;
            if (i == -2)
            {
              if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
              }
              arrayOfChar = this._inputBuffer;
              i = this._inputPtr;
              this._inputPtr = (i + 1);
              c = arrayOfChar[i];
              if (!paramBase64Variant.usesPaddingChar(c)) {
                throw reportInvalidBase64Char(paramBase64Variant, c, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'");
              }
              localByteArrayBuilder.append(m >> 4);
              continue;
            }
          }
          m = m << 6 | k;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfChar = this._inputBuffer;
          i = this._inputPtr;
          this._inputPtr = (i + 1);
          c = arrayOfChar[i];
          j = paramBase64Variant.decodeBase64Char(c);
          k = j;
          if (j < 0)
          {
            i = j;
            if (j != -2)
            {
              if ((c == '"') && (!paramBase64Variant.usesPadding()))
              {
                localByteArrayBuilder.appendTwoBytes(m >> 2);
                return localByteArrayBuilder.toByteArray();
              }
              i = _decodeBase64Escape(paramBase64Variant, c, 3);
            }
            k = i;
            if (i == -2)
            {
              localByteArrayBuilder.appendTwoBytes(m >> 2);
              continue;
            }
          }
          localByteArrayBuilder.appendThreeBytes(m << 6 | k);
        }
      }
    }
  }
  
  protected char _decodeEscaped()
    throws IOException
  {
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(" in character escape sequence");
    }
    char[] arrayOfChar = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    char c2 = arrayOfChar[i];
    char c1 = c2;
    switch (c2)
    {
    default: 
      c1 = _handleUnrecognizedCharacterEscape(c2);
    case '"': 
    case '/': 
    case '\\': 
      return c1;
    case 'b': 
      return '\b';
    case 't': 
      return '\t';
    case 'n': 
      return '\n';
    case 'f': 
      return '\f';
    case 'r': 
      return '\r';
    }
    int j = 0;
    i = 0;
    while (i < 4)
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        _reportInvalidEOF(" in character escape sequence");
      }
      arrayOfChar = this._inputBuffer;
      int k = this._inputPtr;
      this._inputPtr = (k + 1);
      k = arrayOfChar[k];
      int m = CharTypes.charToHex(k);
      if (m < 0) {
        _reportUnexpectedChar(k, "expected a hex-digit for character escape sequence");
      }
      j = j << 4 | m;
      i += 1;
    }
    return (char)j;
  }
  
  protected final void _finishString()
    throws IOException
  {
    int i = this._inputPtr;
    int k = this._inputEnd;
    int j = i;
    if (i < k)
    {
      int[] arrayOfInt = _icLatin1;
      int m = arrayOfInt.length;
      do
      {
        int n = this._inputBuffer[i];
        if ((n < m) && (arrayOfInt[n] != 0))
        {
          j = i;
          if (n != 34) {
            break;
          }
          this._textBuffer.resetWithShared(this._inputBuffer, this._inputPtr, i - this._inputPtr);
          this._inputPtr = (i + 1);
          return;
        }
        j = i + 1;
        i = j;
      } while (j < k);
    }
    this._textBuffer.resetWithCopy(this._inputBuffer, this._inputPtr, j - this._inputPtr);
    this._inputPtr = j;
    _finishString2();
  }
  
  protected void _finishString2()
    throws IOException
  {
    Object localObject1 = this._textBuffer.getCurrentSegment();
    int k = this._textBuffer.getCurrentSegmentSize();
    int[] arrayOfInt = _icLatin1;
    int n = arrayOfInt.length;
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(": was expecting closing quote for a string value");
    }
    Object localObject2 = this._inputBuffer;
    int m = this._inputPtr;
    this._inputPtr = (m + 1);
    int j = localObject2[m];
    int i = j;
    if (j < n)
    {
      i = j;
      if (arrayOfInt[j] != 0)
      {
        if (j == 34)
        {
          this._textBuffer.setCurrentLength(k);
          return;
        }
        if (j != 92) {
          break label165;
        }
        i = _decodeEscaped();
      }
    }
    for (;;)
    {
      localObject2 = localObject1;
      m = k;
      if (k >= localObject1.length)
      {
        localObject2 = this._textBuffer.finishCurrentSegment();
        m = 0;
      }
      localObject2[m] = i;
      k = m + 1;
      localObject1 = localObject2;
      break;
      label165:
      i = j;
      if (j < 32)
      {
        _throwUnquotedSpace(j, "string value");
        i = j;
      }
    }
  }
  
  protected final String _getText2(JsonToken paramJsonToken)
  {
    if (paramJsonToken == null) {
      return null;
    }
    switch (paramJsonToken.id())
    {
    default: 
      return paramJsonToken.asString();
    case 5: 
      return this._parsingContext.getCurrentName();
    }
    return this._textBuffer.contentsAsString();
  }
  
  protected JsonToken _handleApos()
    throws IOException
  {
    Object localObject1 = this._textBuffer.emptyAndGetCurrentSegment();
    int k = this._textBuffer.getCurrentSegmentSize();
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(": was expecting closing quote for a string value");
    }
    Object localObject2 = this._inputBuffer;
    int m = this._inputPtr;
    this._inputPtr = (m + 1);
    int j = localObject2[m];
    int i = j;
    if (j <= 92)
    {
      if (j != 92) {
        break label131;
      }
      i = _decodeEscaped();
    }
    for (;;)
    {
      localObject2 = localObject1;
      m = k;
      if (k >= localObject1.length)
      {
        localObject2 = this._textBuffer.finishCurrentSegment();
        m = 0;
      }
      localObject2[m] = i;
      k = m + 1;
      localObject1 = localObject2;
      break;
      label131:
      i = j;
      if (j <= 39)
      {
        if (j == 39)
        {
          this._textBuffer.setCurrentLength(k);
          return JsonToken.VALUE_STRING;
        }
        i = j;
        if (j < 32)
        {
          _throwUnquotedSpace(j, "string value");
          i = j;
        }
      }
    }
  }
  
  protected JsonToken _handleInvalidNumberStart(int paramInt, boolean paramBoolean)
    throws IOException
  {
    double d = Double.NEGATIVE_INFINITY;
    int i = paramInt;
    Object localObject;
    if (paramInt == 73)
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        _reportInvalidEOFInValue();
      }
      localObject = this._inputBuffer;
      paramInt = this._inputPtr;
      this._inputPtr = (paramInt + 1);
      paramInt = localObject[paramInt];
      if (paramInt != 78) {
        break label162;
      }
      if (paramBoolean)
      {
        localObject = "-INF";
        _matchToken((String)localObject, 3);
        if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
          break label117;
        }
        if (!paramBoolean) {
          break label110;
        }
      }
      for (;;)
      {
        return resetAsNaN((String)localObject, d);
        localObject = "+INF";
        break;
        label110:
        d = Double.POSITIVE_INFINITY;
      }
      label117:
      _reportError("Non-standard token '" + (String)localObject + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
      i = paramInt;
    }
    for (;;)
    {
      reportUnexpectedNumberChar(i, "expected digit (0-9) to follow minus sign, for valid numeric value");
      return null;
      label162:
      i = paramInt;
      if (paramInt == 110)
      {
        if (paramBoolean)
        {
          localObject = "-Infinity";
          _matchToken((String)localObject, 3);
          if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            break label224;
          }
          if (!paramBoolean) {
            break label217;
          }
        }
        for (;;)
        {
          return resetAsNaN((String)localObject, d);
          localObject = "+Infinity";
          break;
          label217:
          d = Double.POSITIVE_INFINITY;
        }
        label224:
        _reportError("Non-standard token '" + (String)localObject + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        i = paramInt;
      }
    }
  }
  
  protected String _handleOddName(int paramInt)
    throws IOException
  {
    if ((paramInt == 39) && (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))) {
      return _parseAposName();
    }
    if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
      _reportUnexpectedChar(paramInt, "was expecting double-quote to start field name");
    }
    int[] arrayOfInt = CharTypes.getInputCodeLatin1JsNames();
    int m = arrayOfInt.length;
    boolean bool;
    int k;
    int n;
    int j;
    if (paramInt < m) {
      if (arrayOfInt[paramInt] == 0)
      {
        bool = true;
        if (!bool) {
          _reportUnexpectedChar(paramInt, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        k = this._inputPtr;
        i = this._hashSeed;
        n = this._inputEnd;
        j = i;
        paramInt = k;
        if (k >= n) {
          break label239;
        }
        paramInt = k;
      }
    }
    label212:
    do
    {
      j = this._inputBuffer[paramInt];
      if (j < m)
      {
        if (arrayOfInt[j] == 0) {
          break label212;
        }
        j = this._inputPtr - 1;
        this._inputPtr = paramInt;
        return this._symbols.findSymbol(this._inputBuffer, j, paramInt - j, i);
        bool = false;
        break;
        bool = Character.isJavaIdentifierPart((char)paramInt);
        break;
      }
      if (!Character.isJavaIdentifierPart((char)j))
      {
        j = this._inputPtr - 1;
        this._inputPtr = paramInt;
        return this._symbols.findSymbol(this._inputBuffer, j, paramInt - j, i);
      }
      j = i * 33 + j;
      k = paramInt + 1;
      i = j;
      paramInt = k;
    } while (k < n);
    paramInt = k;
    label239:
    int i = this._inputPtr;
    this._inputPtr = paramInt;
    return _handleOddName2(i - 1, j, arrayOfInt);
  }
  
  protected JsonToken _handleOddValue(int paramInt)
    throws IOException
  {
    switch (paramInt)
    {
    default: 
    case 39: 
    case 78: 
    case 73: 
      for (;;)
      {
        if (Character.isJavaIdentifierStart(paramInt)) {
          _reportInvalidToken("" + (char)paramInt, "('true', 'false' or 'null')");
        }
        _reportUnexpectedChar(paramInt, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
        if (isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES))
        {
          return _handleApos();
          _matchToken("NaN", 1);
          if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            return resetAsNaN("NaN", NaN.0D);
          }
          _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
          continue;
          _matchToken("Infinity", 1);
          if (isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            return resetAsNaN("Infinity", Double.POSITIVE_INFINITY);
          }
          _reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
        }
      }
    }
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOFInValue();
    }
    char[] arrayOfChar = this._inputBuffer;
    paramInt = this._inputPtr;
    this._inputPtr = (paramInt + 1);
    return _handleInvalidNumberStart(arrayOfChar[paramInt], false);
  }
  
  protected final void _matchToken(String paramString, int paramInt)
    throws IOException
  {
    int j = paramString.length();
    int i;
    do
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
        _reportInvalidToken(paramString.substring(0, paramInt));
      }
      if (this._inputBuffer[this._inputPtr] != paramString.charAt(paramInt)) {
        _reportInvalidToken(paramString.substring(0, paramInt));
      }
      this._inputPtr += 1;
      i = paramInt + 1;
      paramInt = i;
    } while (i < j);
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {}
    char c;
    do
    {
      return;
      c = this._inputBuffer[this._inputPtr];
    } while ((c < '0') || (c == ']') || (c == '}') || (!Character.isJavaIdentifierPart(c)));
    _reportInvalidToken(paramString.substring(0, i));
  }
  
  protected String _parseAposName()
    throws IOException
  {
    int m = this._inputPtr;
    int k = this._hashSeed;
    int n = this._inputEnd;
    int i = k;
    int j = m;
    if (m < n)
    {
      int[] arrayOfInt = _icLatin1;
      int i1 = arrayOfInt.length;
      j = m;
      i = k;
      k = this._inputBuffer[j];
      if (k == 39)
      {
        k = this._inputPtr;
        this._inputPtr = (j + 1);
        return this._symbols.findSymbol(this._inputBuffer, k, j - k, i);
      }
      if ((k >= i1) || (arrayOfInt[k] == 0)) {
        break label118;
      }
    }
    for (;;)
    {
      k = this._inputPtr;
      this._inputPtr = j;
      return _parseName2(k, i, 39);
      label118:
      m = i * 33 + k;
      k = j + 1;
      i = m;
      j = k;
      if (k < n) {
        break;
      }
      i = m;
      j = k;
    }
  }
  
  protected final String _parseName()
    throws IOException
  {
    int i = this._inputPtr;
    int j = this._hashSeed;
    int[] arrayOfInt = _icLatin1;
    while (i < this._inputEnd)
    {
      k = this._inputBuffer[i];
      if ((k < arrayOfInt.length) && (arrayOfInt[k] != 0))
      {
        if (k != 34) {
          break;
        }
        k = this._inputPtr;
        this._inputPtr = (i + 1);
        return this._symbols.findSymbol(this._inputBuffer, k, i - k, j);
      }
      j = j * 33 + k;
      i += 1;
    }
    int k = this._inputPtr;
    this._inputPtr = i;
    return _parseName2(k, j, 34);
  }
  
  protected final JsonToken _parseNegNumber()
    throws IOException
  {
    int i = this._inputPtr;
    int m = i - 1;
    int n = this._inputEnd;
    if (i >= n) {
      return _parseNumber2(true, m);
    }
    char[] arrayOfChar = this._inputBuffer;
    int j = i + 1;
    i = arrayOfChar[i];
    if ((i > 57) || (i < 48))
    {
      this._inputPtr = j;
      return _handleInvalidNumberStart(i, true);
    }
    if (i == 48) {
      return _parseNumber2(true, m);
    }
    i = 1;
    int k;
    for (;;)
    {
      if (j >= n) {
        return _parseNumber2(true, m);
      }
      arrayOfChar = this._inputBuffer;
      k = j + 1;
      j = arrayOfChar[j];
      if ((j < 48) || (j > 57))
      {
        if ((j != 46) && (j != 101) && (j != 69)) {
          break;
        }
        this._inputPtr = k;
        return _parseFloat(j, m, k, true, i);
      }
      i += 1;
      j = k;
    }
    k -= 1;
    this._inputPtr = k;
    if (this._parsingContext.inRoot()) {
      _verifyRootSpace(j);
    }
    this._textBuffer.resetWithShared(this._inputBuffer, m, k - m);
    return resetInt(true, i);
  }
  
  protected final JsonToken _parsePosNumber(int paramInt)
    throws IOException
  {
    int i = this._inputPtr;
    int k = i - 1;
    int m = this._inputEnd;
    if (paramInt == 48) {
      return _parseNumber2(false, k);
    }
    paramInt = 1;
    int j;
    for (;;)
    {
      if (i >= m)
      {
        this._inputPtr = k;
        return _parseNumber2(false, k);
      }
      char[] arrayOfChar = this._inputBuffer;
      j = i + 1;
      i = arrayOfChar[i];
      if ((i < 48) || (i > 57))
      {
        if ((i != 46) && (i != 101) && (i != 69)) {
          break;
        }
        this._inputPtr = j;
        return _parseFloat(i, k, j, false, paramInt);
      }
      paramInt += 1;
      i = j;
    }
    j -= 1;
    this._inputPtr = j;
    if (this._parsingContext.inRoot()) {
      _verifyRootSpace(i);
    }
    this._textBuffer.resetWithShared(this._inputBuffer, k, j - k);
    return resetInt(false, paramInt);
  }
  
  protected int _readBinary(Base64Variant paramBase64Variant, OutputStream paramOutputStream, byte[] paramArrayOfByte)
    throws IOException
  {
    int i = 0;
    int i1 = paramArrayOfByte.length;
    int k = 0;
    for (;;)
    {
      if (this._inputPtr >= this._inputEnd) {
        loadMoreGuaranteed();
      }
      char[] arrayOfChar = this._inputBuffer;
      int j = this._inputPtr;
      this._inputPtr = (j + 1);
      char c = arrayOfChar[j];
      if (c > ' ')
      {
        j = paramBase64Variant.decodeBase64Char(c);
        int n = j;
        if (j < 0) {
          if (c == '"') {
            j = k;
          }
        }
        int m;
        int i2;
        for (;;)
        {
          this._tokenIncomplete = false;
          k = j;
          if (i > 0)
          {
            k = j + i;
            paramOutputStream.write(paramArrayOfByte, 0, i);
          }
          return k;
          n = _decodeBase64Escape(paramBase64Variant, c, 0);
          if (n < 0) {
            break;
          }
          j = k;
          m = i;
          if (i > i1 - 3)
          {
            j = k + i;
            paramOutputStream.write(paramArrayOfByte, 0, i);
            m = 0;
          }
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfChar = this._inputBuffer;
          i = this._inputPtr;
          this._inputPtr = (i + 1);
          c = arrayOfChar[i];
          k = paramBase64Variant.decodeBase64Char(c);
          i = k;
          if (k < 0) {
            i = _decodeBase64Escape(paramBase64Variant, c, 1);
          }
          i2 = n << 6 | i;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfChar = this._inputBuffer;
          i = this._inputPtr;
          this._inputPtr = (i + 1);
          c = arrayOfChar[i];
          k = paramBase64Variant.decodeBase64Char(c);
          n = k;
          if (k < 0)
          {
            i = k;
            if (k != -2)
            {
              if ((c == '"') && (!paramBase64Variant.usesPadding()))
              {
                paramArrayOfByte[m] = ((byte)(i2 >> 4));
                i = m + 1;
                continue;
              }
              i = _decodeBase64Escape(paramBase64Variant, c, 2);
            }
            n = i;
            if (i == -2)
            {
              if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
              }
              arrayOfChar = this._inputBuffer;
              i = this._inputPtr;
              this._inputPtr = (i + 1);
              c = arrayOfChar[i];
              if (!paramBase64Variant.usesPaddingChar(c)) {
                throw reportInvalidBase64Char(paramBase64Variant, c, 3, "expected padding character '" + paramBase64Variant.getPaddingChar() + "'");
              }
              paramArrayOfByte[m] = ((byte)(i2 >> 4));
              i = m + 1;
              k = j;
              break;
            }
          }
          i2 = i2 << 6 | n;
          if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
          }
          arrayOfChar = this._inputBuffer;
          i = this._inputPtr;
          this._inputPtr = (i + 1);
          c = arrayOfChar[i];
          k = paramBase64Variant.decodeBase64Char(c);
          n = k;
          if (k >= 0) {
            break label677;
          }
          i = k;
          if (k == -2) {
            break label624;
          }
          if ((c != '"') || (paramBase64Variant.usesPadding())) {
            break label614;
          }
          k = i2 >> 2;
          n = m + 1;
          paramArrayOfByte[m] = ((byte)(k >> 8));
          i = n + 1;
          paramArrayOfByte[n] = ((byte)k);
        }
        label614:
        i = _decodeBase64Escape(paramBase64Variant, c, 3);
        label624:
        n = i;
        if (i == -2)
        {
          k = i2 >> 2;
          n = m + 1;
          paramArrayOfByte[m] = ((byte)(k >> 8));
          i = n + 1;
          paramArrayOfByte[n] = ((byte)k);
          k = j;
        }
        else
        {
          label677:
          i = i2 << 6 | n;
          k = m + 1;
          paramArrayOfByte[m] = ((byte)(i >> 16));
          m = k + 1;
          paramArrayOfByte[k] = ((byte)(i >> 8));
          paramArrayOfByte[m] = ((byte)i);
          i = m + 1;
          k = j;
        }
      }
    }
  }
  
  protected void _releaseBuffers()
    throws IOException
  {
    super._releaseBuffers();
    this._symbols.release();
    if (this._bufferRecyclable)
    {
      char[] arrayOfChar = this._inputBuffer;
      if (arrayOfChar != null)
      {
        this._inputBuffer = null;
        this._ioContext.releaseTokenBuffer(arrayOfChar);
      }
    }
  }
  
  protected void _reportInvalidToken(String paramString)
    throws IOException
  {
    _reportInvalidToken(paramString, "'null', 'true', 'false' or NaN");
  }
  
  protected void _reportInvalidToken(String paramString1, String paramString2)
    throws IOException
  {
    paramString1 = new StringBuilder(paramString1);
    for (;;)
    {
      if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {}
      char c;
      do
      {
        _reportError("Unrecognized token '" + paramString1.toString() + "': was expecting " + paramString2);
        return;
        c = this._inputBuffer[this._inputPtr];
      } while (!Character.isJavaIdentifierPart(c));
      this._inputPtr += 1;
      paramString1.append(c);
    }
  }
  
  protected final void _skipCR()
    throws IOException
  {
    if (((this._inputPtr < this._inputEnd) || (loadMore())) && (this._inputBuffer[this._inputPtr] == '\n')) {
      this._inputPtr += 1;
    }
    this._currInputRow += 1;
    this._currInputRowStart = this._inputPtr;
  }
  
  protected final void _skipString()
    throws IOException
  {
    this._tokenIncomplete = false;
    int i = this._inputPtr;
    int j = this._inputEnd;
    char[] arrayOfChar = this._inputBuffer;
    for (;;)
    {
      int k = j;
      int m = i;
      if (i >= j)
      {
        this._inputPtr = i;
        if (!loadMore()) {
          _reportInvalidEOF(": was expecting closing quote for a string value");
        }
        m = this._inputPtr;
        k = this._inputEnd;
      }
      i = m + 1;
      j = arrayOfChar[m];
      if (j <= 92)
      {
        if (j == 92)
        {
          this._inputPtr = i;
          _decodeEscaped();
          i = this._inputPtr;
          j = this._inputEnd;
          continue;
        }
        if (j <= 34)
        {
          if (j == 34)
          {
            this._inputPtr = i;
            return;
          }
          if (j < 32)
          {
            this._inputPtr = i;
            _throwUnquotedSpace(j, "string value");
          }
        }
      }
      j = k;
    }
  }
  
  public byte[] getBinaryValue(Base64Variant paramBase64Variant)
    throws IOException
  {
    if ((this._currToken != JsonToken.VALUE_STRING) && ((this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT) || (this._binaryValue == null))) {
      _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
    }
    if (this._tokenIncomplete) {}
    for (;;)
    {
      try
      {
        this._binaryValue = _decodeBase64(paramBase64Variant);
        this._tokenIncomplete = false;
        return this._binaryValue;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw _constructError("Failed to decode VALUE_STRING as base64 (" + paramBase64Variant + "): " + localIllegalArgumentException.getMessage());
      }
      if (this._binaryValue == null)
      {
        ByteArrayBuilder localByteArrayBuilder = _getByteArrayBuilder();
        _decodeBase64(getText(), localByteArrayBuilder, paramBase64Variant);
        this._binaryValue = localByteArrayBuilder.toByteArray();
      }
    }
  }
  
  public ObjectCodec getCodec()
  {
    return this._objectCodec;
  }
  
  public Object getInputSource()
  {
    return this._reader;
  }
  
  protected char getNextChar(String paramString)
    throws IOException
  {
    if ((this._inputPtr >= this._inputEnd) && (!loadMore())) {
      _reportInvalidEOF(paramString);
    }
    paramString = this._inputBuffer;
    int i = this._inputPtr;
    this._inputPtr = (i + 1);
    return paramString[i];
  }
  
  public final String getText()
    throws IOException
  {
    JsonToken localJsonToken = this._currToken;
    if (localJsonToken == JsonToken.VALUE_STRING)
    {
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        _finishString();
      }
      return this._textBuffer.contentsAsString();
    }
    return _getText2(localJsonToken);
  }
  
  public final char[] getTextCharacters()
    throws IOException
  {
    if (this._currToken != null)
    {
      switch (this._currToken.id())
      {
      default: 
        return this._currToken.asCharArray();
      case 5: 
        String str;
        int i;
        if (!this._nameCopied)
        {
          str = this._parsingContext.getCurrentName();
          i = str.length();
          if (this._nameCopyBuffer != null) {
            break label112;
          }
          this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(i);
        }
        for (;;)
        {
          str.getChars(0, i, this._nameCopyBuffer, 0);
          this._nameCopied = true;
          return this._nameCopyBuffer;
          if (this._nameCopyBuffer.length < i) {
            this._nameCopyBuffer = new char[i];
          }
        }
      case 6: 
        label112:
        if (this._tokenIncomplete)
        {
          this._tokenIncomplete = false;
          _finishString();
        }
        break;
      }
      return this._textBuffer.getTextBuffer();
    }
    return null;
  }
  
  public final int getTextLength()
    throws IOException
  {
    int i = 0;
    if (this._currToken != null) {}
    switch (this._currToken.id())
    {
    default: 
      i = this._currToken.asCharArray().length;
      return i;
    case 5: 
      return this._parsingContext.getCurrentName().length();
    case 6: 
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        _finishString();
      }
      break;
    }
    return this._textBuffer.size();
  }
  
  public final int getTextOffset()
    throws IOException
  {
    if (this._currToken != null) {}
    switch (this._currToken.id())
    {
    case 5: 
    default: 
      return 0;
    case 6: 
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        _finishString();
      }
      break;
    }
    return this._textBuffer.getTextOffset();
  }
  
  public final String getValueAsString()
    throws IOException
  {
    if (this._currToken == JsonToken.VALUE_STRING)
    {
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        _finishString();
      }
      return this._textBuffer.contentsAsString();
    }
    if (this._currToken == JsonToken.FIELD_NAME) {
      return getCurrentName();
    }
    return super.getValueAsString(null);
  }
  
  public final String getValueAsString(String paramString)
    throws IOException
  {
    if (this._currToken == JsonToken.VALUE_STRING)
    {
      if (this._tokenIncomplete)
      {
        this._tokenIncomplete = false;
        _finishString();
      }
      return this._textBuffer.contentsAsString();
    }
    if (this._currToken == JsonToken.FIELD_NAME) {
      return getCurrentName();
    }
    return super.getValueAsString(paramString);
  }
  
  protected boolean loadMore()
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
  
  public final Boolean nextBooleanValue()
    throws IOException
  {
    Object localObject2 = null;
    JsonToken localJsonToken;
    Object localObject1;
    if (this._currToken == JsonToken.FIELD_NAME)
    {
      this._nameCopied = false;
      localJsonToken = this._nextToken;
      this._nextToken = null;
      this._currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_TRUE) {
        localObject1 = Boolean.TRUE;
      }
    }
    int i;
    do
    {
      do
      {
        do
        {
          return (Boolean)localObject1;
          if (localJsonToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
          }
          if (localJsonToken == JsonToken.START_ARRAY)
          {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            return null;
          }
          localObject1 = localObject2;
        } while (localJsonToken != JsonToken.START_OBJECT);
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        return null;
        localJsonToken = nextToken();
        localObject1 = localObject2;
      } while (localJsonToken == null);
      i = localJsonToken.id();
      if (i == 9) {
        return Boolean.TRUE;
      }
      localObject1 = localObject2;
    } while (i != 10);
    return Boolean.FALSE;
  }
  
  public String nextFieldName()
    throws IOException
  {
    this._numTypesValid = 0;
    if (this._currToken == JsonToken.FIELD_NAME)
    {
      _nextAfterName();
      return null;
    }
    if (this._tokenIncomplete) {
      _skipString();
    }
    int j = _skipWSOrEnd();
    if (j < 0)
    {
      close();
      this._currToken = null;
      return null;
    }
    this._tokenInputTotal = (this._currInputProcessed + this._inputPtr - 1L);
    this._tokenInputRow = this._currInputRow;
    this._tokenInputCol = (this._inputPtr - this._currInputRowStart - 1);
    this._binaryValue = null;
    if (j == 93)
    {
      if (!this._parsingContext.inArray()) {
        _reportMismatchedEndMarker(j, '}');
      }
      this._parsingContext = this._parsingContext.getParent();
      this._currToken = JsonToken.END_ARRAY;
      return null;
    }
    if (j == 125)
    {
      if (!this._parsingContext.inObject()) {
        _reportMismatchedEndMarker(j, ']');
      }
      this._parsingContext = this._parsingContext.getParent();
      this._currToken = JsonToken.END_OBJECT;
      return null;
    }
    int i = j;
    if (this._parsingContext.expectComma()) {
      i = _skipComma(j);
    }
    if (!this._parsingContext.inObject())
    {
      _nextTokenNotInObject(i);
      return null;
    }
    if (i == 34) {}
    for (String str = _parseName();; str = _handleOddName(i))
    {
      this._parsingContext.setCurrentName(str);
      this._currToken = JsonToken.FIELD_NAME;
      i = _skipColon();
      if (i != 34) {
        break;
      }
      this._tokenIncomplete = true;
      this._nextToken = JsonToken.VALUE_STRING;
      return str;
    }
    JsonToken localJsonToken;
    switch (i)
    {
    default: 
      localJsonToken = _handleOddValue(i);
    }
    for (;;)
    {
      this._nextToken = localJsonToken;
      return str;
      localJsonToken = _parseNegNumber();
      continue;
      localJsonToken = _parsePosNumber(i);
      continue;
      _matchFalse();
      localJsonToken = JsonToken.VALUE_FALSE;
      continue;
      _matchNull();
      localJsonToken = JsonToken.VALUE_NULL;
      continue;
      _matchTrue();
      localJsonToken = JsonToken.VALUE_TRUE;
      continue;
      localJsonToken = JsonToken.START_ARRAY;
      continue;
      localJsonToken = JsonToken.START_OBJECT;
    }
  }
  
  public final int nextIntValue(int paramInt)
    throws IOException
  {
    JsonToken localJsonToken;
    int i;
    if (this._currToken == JsonToken.FIELD_NAME)
    {
      this._nameCopied = false;
      localJsonToken = this._nextToken;
      this._nextToken = null;
      this._currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
        i = getIntValue();
      }
    }
    do
    {
      do
      {
        return i;
        if (localJsonToken == JsonToken.START_ARRAY)
        {
          this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
          return paramInt;
        }
        i = paramInt;
      } while (localJsonToken != JsonToken.START_OBJECT);
      this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
      return paramInt;
      i = paramInt;
    } while (nextToken() != JsonToken.VALUE_NUMBER_INT);
    return getIntValue();
  }
  
  public final long nextLongValue(long paramLong)
    throws IOException
  {
    JsonToken localJsonToken;
    long l;
    if (this._currToken == JsonToken.FIELD_NAME)
    {
      this._nameCopied = false;
      localJsonToken = this._nextToken;
      this._nextToken = null;
      this._currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_NUMBER_INT) {
        l = getLongValue();
      }
    }
    do
    {
      do
      {
        return l;
        if (localJsonToken == JsonToken.START_ARRAY)
        {
          this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
          return paramLong;
        }
        l = paramLong;
      } while (localJsonToken != JsonToken.START_OBJECT);
      this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
      return paramLong;
      l = paramLong;
    } while (nextToken() != JsonToken.VALUE_NUMBER_INT);
    return getLongValue();
  }
  
  public final String nextTextValue()
    throws IOException
  {
    String str = null;
    if (this._currToken == JsonToken.FIELD_NAME)
    {
      this._nameCopied = false;
      localJsonToken = this._nextToken;
      this._nextToken = null;
      this._currToken = localJsonToken;
      if (localJsonToken == JsonToken.VALUE_STRING)
      {
        if (this._tokenIncomplete)
        {
          this._tokenIncomplete = false;
          _finishString();
        }
        str = this._textBuffer.contentsAsString();
      }
    }
    while (nextToken() != JsonToken.VALUE_STRING)
    {
      JsonToken localJsonToken;
      do
      {
        return str;
        if (localJsonToken == JsonToken.START_ARRAY)
        {
          this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
          return null;
        }
      } while (localJsonToken != JsonToken.START_OBJECT);
      this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
      return null;
    }
    return getText();
  }
  
  public final JsonToken nextToken()
    throws IOException
  {
    this._numTypesValid = 0;
    if (this._currToken == JsonToken.FIELD_NAME) {
      return _nextAfterName();
    }
    if (this._tokenIncomplete) {
      _skipString();
    }
    int j = _skipWSOrEnd();
    if (j < 0)
    {
      close();
      this._currToken = null;
      return null;
    }
    this._tokenInputTotal = (this._currInputProcessed + this._inputPtr - 1L);
    this._tokenInputRow = this._currInputRow;
    this._tokenInputCol = (this._inputPtr - this._currInputRowStart - 1);
    this._binaryValue = null;
    Object localObject;
    if (j == 93)
    {
      if (!this._parsingContext.inArray()) {
        _reportMismatchedEndMarker(j, '}');
      }
      this._parsingContext = this._parsingContext.getParent();
      localObject = JsonToken.END_ARRAY;
      this._currToken = ((JsonToken)localObject);
      return (JsonToken)localObject;
    }
    if (j == 125)
    {
      if (!this._parsingContext.inObject()) {
        _reportMismatchedEndMarker(j, ']');
      }
      this._parsingContext = this._parsingContext.getParent();
      localObject = JsonToken.END_OBJECT;
      this._currToken = ((JsonToken)localObject);
      return (JsonToken)localObject;
    }
    int i = j;
    if (this._parsingContext.expectComma()) {
      i = _skipComma(j);
    }
    boolean bool = this._parsingContext.inObject();
    j = i;
    if (bool)
    {
      if (i == 34)
      {
        localObject = _parseName();
        this._parsingContext.setCurrentName((String)localObject);
        this._currToken = JsonToken.FIELD_NAME;
        j = _skipColon();
      }
    }
    else {
      switch (j)
      {
      default: 
        localObject = _handleOddValue(j);
      }
    }
    for (;;)
    {
      if (!bool) {
        break label590;
      }
      this._nextToken = ((JsonToken)localObject);
      return this._currToken;
      localObject = _handleOddName(i);
      break;
      this._tokenIncomplete = true;
      localObject = JsonToken.VALUE_STRING;
      continue;
      if (!bool) {
        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
      }
      localObject = JsonToken.START_ARRAY;
      continue;
      if (!bool) {
        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
      }
      localObject = JsonToken.START_OBJECT;
      continue;
      _reportUnexpectedChar(j, "expected a value");
      _matchTrue();
      localObject = JsonToken.VALUE_TRUE;
      continue;
      _matchFalse();
      localObject = JsonToken.VALUE_FALSE;
      continue;
      _matchNull();
      localObject = JsonToken.VALUE_NULL;
      continue;
      localObject = _parseNegNumber();
      continue;
      localObject = _parsePosNumber(j);
    }
    label590:
    this._currToken = ((JsonToken)localObject);
    return (JsonToken)localObject;
  }
  
  public int readBinaryValue(Base64Variant paramBase64Variant, OutputStream paramOutputStream)
    throws IOException
  {
    if ((!this._tokenIncomplete) || (this._currToken != JsonToken.VALUE_STRING))
    {
      paramBase64Variant = getBinaryValue(paramBase64Variant);
      paramOutputStream.write(paramBase64Variant);
      return paramBase64Variant.length;
    }
    byte[] arrayOfByte = this._ioContext.allocBase64Buffer();
    try
    {
      int i = _readBinary(paramBase64Variant, paramOutputStream, arrayOfByte);
      return i;
    }
    finally
    {
      this._ioContext.releaseBase64Buffer(arrayOfByte);
    }
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
  
  public void setCodec(ObjectCodec paramObjectCodec)
  {
    this._objectCodec = paramObjectCodec;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/json/ReaderBasedJsonParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */