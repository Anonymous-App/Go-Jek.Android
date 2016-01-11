package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public final class TextBuffer
{
  static final int MAX_SEGMENT_LEN = 262144;
  static final int MIN_SEGMENT_LEN = 1000;
  static final char[] NO_CHARS = new char[0];
  private final BufferRecycler _allocator;
  private char[] _currentSegment;
  private int _currentSize;
  private boolean _hasSegments = false;
  private char[] _inputBuffer;
  private int _inputLen;
  private int _inputStart;
  private char[] _resultArray;
  private String _resultString;
  private int _segmentSize;
  private ArrayList<char[]> _segments;
  
  public TextBuffer(BufferRecycler paramBufferRecycler)
  {
    this._allocator = paramBufferRecycler;
  }
  
  private char[] buf(int paramInt)
  {
    if (this._allocator != null) {
      return this._allocator.allocCharBuffer(2, paramInt);
    }
    return new char[Math.max(paramInt, 1000)];
  }
  
  private char[] carr(int paramInt)
  {
    return new char[paramInt];
  }
  
  private void clearSegments()
  {
    this._hasSegments = false;
    this._segments.clear();
    this._segmentSize = 0;
    this._currentSize = 0;
  }
  
  private void expand(int paramInt)
  {
    if (this._segments == null) {
      this._segments = new ArrayList();
    }
    char[] arrayOfChar = this._currentSegment;
    this._hasSegments = true;
    this._segments.add(arrayOfChar);
    this._segmentSize += arrayOfChar.length;
    this._currentSize = 0;
    paramInt = arrayOfChar.length;
    int i = paramInt + (paramInt >> 1);
    if (i < 1000) {
      paramInt = 1000;
    }
    for (;;)
    {
      this._currentSegment = carr(paramInt);
      return;
      paramInt = i;
      if (i > 262144) {
        paramInt = 262144;
      }
    }
  }
  
  private char[] resultArray()
  {
    if (this._resultString != null) {
      return this._resultString.toCharArray();
    }
    if (this._inputStart >= 0)
    {
      i = this._inputLen;
      if (i < 1) {
        return NO_CHARS;
      }
      j = this._inputStart;
      if (j == 0) {
        return Arrays.copyOf(this._inputBuffer, i);
      }
      return Arrays.copyOfRange(this._inputBuffer, j, j + i);
    }
    int j = size();
    if (j < 1) {
      return NO_CHARS;
    }
    int k = 0;
    int i = 0;
    char[] arrayOfChar1 = carr(j);
    if (this._segments != null)
    {
      j = 0;
      int m = this._segments.size();
      for (;;)
      {
        k = i;
        if (j >= m) {
          break;
        }
        char[] arrayOfChar2 = (char[])this._segments.get(j);
        k = arrayOfChar2.length;
        System.arraycopy(arrayOfChar2, 0, arrayOfChar1, i, k);
        i += k;
        j += 1;
      }
    }
    System.arraycopy(this._currentSegment, 0, arrayOfChar1, k, this._currentSize);
    return arrayOfChar1;
  }
  
  private void unshare(int paramInt)
  {
    int i = this._inputLen;
    this._inputLen = 0;
    char[] arrayOfChar = this._inputBuffer;
    this._inputBuffer = null;
    int j = this._inputStart;
    this._inputStart = -1;
    paramInt = i + paramInt;
    if ((this._currentSegment == null) || (paramInt > this._currentSegment.length)) {
      this._currentSegment = buf(paramInt);
    }
    if (i > 0) {
      System.arraycopy(arrayOfChar, j, this._currentSegment, 0, i);
    }
    this._segmentSize = 0;
    this._currentSize = i;
  }
  
  public void append(char paramChar)
  {
    if (this._inputStart >= 0) {
      unshare(16);
    }
    this._resultString = null;
    this._resultArray = null;
    char[] arrayOfChar2 = this._currentSegment;
    char[] arrayOfChar1 = arrayOfChar2;
    if (this._currentSize >= arrayOfChar2.length)
    {
      expand(1);
      arrayOfChar1 = this._currentSegment;
    }
    int i = this._currentSize;
    this._currentSize = (i + 1);
    arrayOfChar1[i] = paramChar;
  }
  
  public void append(String paramString, int paramInt1, int paramInt2)
  {
    if (this._inputStart >= 0) {
      unshare(paramInt2);
    }
    this._resultString = null;
    this._resultArray = null;
    char[] arrayOfChar = this._currentSegment;
    int k = arrayOfChar.length - this._currentSize;
    if (k >= paramInt2)
    {
      paramString.getChars(paramInt1, paramInt1 + paramInt2, arrayOfChar, this._currentSize);
      this._currentSize += paramInt2;
      return;
    }
    int j = paramInt1;
    int i = paramInt2;
    if (k > 0)
    {
      paramString.getChars(paramInt1, paramInt1 + k, arrayOfChar, this._currentSize);
      i = paramInt2 - k;
      j = paramInt1 + k;
    }
    do
    {
      expand(i);
      paramInt1 = Math.min(this._currentSegment.length, i);
      paramString.getChars(j, j + paramInt1, this._currentSegment, 0);
      this._currentSize += paramInt1;
      j += paramInt1;
      paramInt1 = i - paramInt1;
      i = paramInt1;
    } while (paramInt1 > 0);
  }
  
  public void append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if (this._inputStart >= 0) {
      unshare(paramInt2);
    }
    this._resultString = null;
    this._resultArray = null;
    char[] arrayOfChar = this._currentSegment;
    int k = arrayOfChar.length - this._currentSize;
    if (k >= paramInt2)
    {
      System.arraycopy(paramArrayOfChar, paramInt1, arrayOfChar, this._currentSize, paramInt2);
      this._currentSize += paramInt2;
      return;
    }
    int j = paramInt1;
    int i = paramInt2;
    if (k > 0)
    {
      System.arraycopy(paramArrayOfChar, paramInt1, arrayOfChar, this._currentSize, k);
      j = paramInt1 + k;
      i = paramInt2 - k;
    }
    do
    {
      expand(i);
      paramInt1 = Math.min(this._currentSegment.length, i);
      System.arraycopy(paramArrayOfChar, j, this._currentSegment, 0, paramInt1);
      this._currentSize += paramInt1;
      j += paramInt1;
      paramInt1 = i - paramInt1;
      i = paramInt1;
    } while (paramInt1 > 0);
  }
  
  public char[] contentsAsArray()
  {
    char[] arrayOfChar2 = this._resultArray;
    char[] arrayOfChar1 = arrayOfChar2;
    if (arrayOfChar2 == null)
    {
      arrayOfChar1 = resultArray();
      this._resultArray = arrayOfChar1;
    }
    return arrayOfChar1;
  }
  
  public BigDecimal contentsAsDecimal()
    throws NumberFormatException
  {
    if (this._resultArray != null) {
      return NumberInput.parseBigDecimal(this._resultArray);
    }
    if ((this._inputStart >= 0) && (this._inputBuffer != null)) {
      return NumberInput.parseBigDecimal(this._inputBuffer, this._inputStart, this._inputLen);
    }
    if ((this._segmentSize == 0) && (this._currentSegment != null)) {
      return NumberInput.parseBigDecimal(this._currentSegment, 0, this._currentSize);
    }
    return NumberInput.parseBigDecimal(contentsAsArray());
  }
  
  public double contentsAsDouble()
    throws NumberFormatException
  {
    return NumberInput.parseDouble(contentsAsString());
  }
  
  public String contentsAsString()
  {
    if (this._resultString == null)
    {
      if (this._resultArray == null) {
        break label34;
      }
      this._resultString = new String(this._resultArray);
    }
    for (;;)
    {
      return this._resultString;
      label34:
      if (this._inputStart >= 0)
      {
        if (this._inputLen < 1)
        {
          this._resultString = "";
          return "";
        }
        this._resultString = new String(this._inputBuffer, this._inputStart, this._inputLen);
      }
      else
      {
        int i = this._segmentSize;
        int j = this._currentSize;
        if (i == 0)
        {
          if (j == 0) {}
          for (localObject = "";; localObject = new String(this._currentSegment, 0, j))
          {
            this._resultString = ((String)localObject);
            break;
          }
        }
        Object localObject = new StringBuilder(i + j);
        if (this._segments != null)
        {
          i = 0;
          j = this._segments.size();
          while (i < j)
          {
            char[] arrayOfChar = (char[])this._segments.get(i);
            ((StringBuilder)localObject).append(arrayOfChar, 0, arrayOfChar.length);
            i += 1;
          }
        }
        ((StringBuilder)localObject).append(this._currentSegment, 0, this._currentSize);
        this._resultString = ((StringBuilder)localObject).toString();
      }
    }
  }
  
  public char[] emptyAndGetCurrentSegment()
  {
    this._inputStart = -1;
    this._currentSize = 0;
    this._inputLen = 0;
    this._inputBuffer = null;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    }
    char[] arrayOfChar2 = this._currentSegment;
    char[] arrayOfChar1 = arrayOfChar2;
    if (arrayOfChar2 == null)
    {
      arrayOfChar1 = buf(0);
      this._currentSegment = arrayOfChar1;
    }
    return arrayOfChar1;
  }
  
  public void ensureNotShared()
  {
    if (this._inputStart >= 0) {
      unshare(16);
    }
  }
  
  public char[] expandCurrentSegment()
  {
    char[] arrayOfChar = this._currentSegment;
    int k = arrayOfChar.length;
    int j = k + (k >> 1);
    int i = j;
    if (j > 262144) {
      i = k + (k >> 2);
    }
    arrayOfChar = Arrays.copyOf(arrayOfChar, i);
    this._currentSegment = arrayOfChar;
    return arrayOfChar;
  }
  
  public char[] expandCurrentSegment(int paramInt)
  {
    char[] arrayOfChar = this._currentSegment;
    if (arrayOfChar.length >= paramInt) {
      return arrayOfChar;
    }
    arrayOfChar = Arrays.copyOf(arrayOfChar, paramInt);
    this._currentSegment = arrayOfChar;
    return arrayOfChar;
  }
  
  public char[] finishCurrentSegment()
  {
    if (this._segments == null) {
      this._segments = new ArrayList();
    }
    this._hasSegments = true;
    this._segments.add(this._currentSegment);
    int i = this._currentSegment.length;
    this._segmentSize += i;
    this._currentSize = 0;
    int j = i + (i >> 1);
    if (j < 1000) {
      i = 1000;
    }
    for (;;)
    {
      char[] arrayOfChar = carr(i);
      this._currentSegment = arrayOfChar;
      return arrayOfChar;
      i = j;
      if (j > 262144) {
        i = 262144;
      }
    }
  }
  
  public char[] getCurrentSegment()
  {
    if (this._inputStart >= 0) {
      unshare(1);
    }
    for (;;)
    {
      return this._currentSegment;
      char[] arrayOfChar = this._currentSegment;
      if (arrayOfChar == null) {
        this._currentSegment = buf(0);
      } else if (this._currentSize >= arrayOfChar.length) {
        expand(1);
      }
    }
  }
  
  public int getCurrentSegmentSize()
  {
    return this._currentSize;
  }
  
  public char[] getTextBuffer()
  {
    if (this._inputStart >= 0) {
      return this._inputBuffer;
    }
    if (this._resultArray != null) {
      return this._resultArray;
    }
    if (this._resultString != null)
    {
      char[] arrayOfChar = this._resultString.toCharArray();
      this._resultArray = arrayOfChar;
      return arrayOfChar;
    }
    if (!this._hasSegments)
    {
      if (this._currentSegment == null) {
        return NO_CHARS;
      }
      return this._currentSegment;
    }
    return contentsAsArray();
  }
  
  public int getTextOffset()
  {
    if (this._inputStart >= 0) {
      return this._inputStart;
    }
    return 0;
  }
  
  public boolean hasTextAsCharacters()
  {
    if ((this._inputStart >= 0) || (this._resultArray != null)) {}
    while (this._resultString == null) {
      return true;
    }
    return false;
  }
  
  public void releaseBuffers()
  {
    if (this._allocator == null) {
      resetWithEmpty();
    }
    while (this._currentSegment == null) {
      return;
    }
    resetWithEmpty();
    char[] arrayOfChar = this._currentSegment;
    this._currentSegment = null;
    this._allocator.releaseCharBuffer(2, arrayOfChar);
  }
  
  public void resetWithCopy(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this._inputBuffer = null;
    this._inputStart = -1;
    this._inputLen = 0;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    }
    for (;;)
    {
      this._segmentSize = 0;
      this._currentSize = 0;
      append(paramArrayOfChar, paramInt1, paramInt2);
      return;
      if (this._currentSegment == null) {
        this._currentSegment = buf(paramInt2);
      }
    }
  }
  
  public void resetWithEmpty()
  {
    this._inputStart = -1;
    this._currentSize = 0;
    this._inputLen = 0;
    this._inputBuffer = null;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    }
  }
  
  public void resetWithShared(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    this._resultString = null;
    this._resultArray = null;
    this._inputBuffer = paramArrayOfChar;
    this._inputStart = paramInt1;
    this._inputLen = paramInt2;
    if (this._hasSegments) {
      clearSegments();
    }
  }
  
  public void resetWithString(String paramString)
  {
    this._inputBuffer = null;
    this._inputStart = -1;
    this._inputLen = 0;
    this._resultString = paramString;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    }
    this._currentSize = 0;
  }
  
  public String setCurrentAndReturn(int paramInt)
  {
    this._currentSize = paramInt;
    if (this._segmentSize > 0) {
      return contentsAsString();
    }
    paramInt = this._currentSize;
    if (paramInt == 0) {}
    for (String str = "";; str = new String(this._currentSegment, 0, paramInt))
    {
      this._resultString = str;
      return str;
    }
  }
  
  public void setCurrentLength(int paramInt)
  {
    this._currentSize = paramInt;
  }
  
  public int size()
  {
    if (this._inputStart >= 0) {
      return this._inputLen;
    }
    if (this._resultArray != null) {
      return this._resultArray.length;
    }
    if (this._resultString != null) {
      return this._resultString.length();
    }
    return this._segmentSize + this._currentSize;
  }
  
  public String toString()
  {
    return contentsAsString();
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/util/TextBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */