package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.SerializableString;
import java.io.Serializable;
import java.util.Arrays;

public abstract class CharacterEscapes
  implements Serializable
{
  public static final int ESCAPE_CUSTOM = -2;
  public static final int ESCAPE_NONE = 0;
  public static final int ESCAPE_STANDARD = -1;
  
  public static int[] standardAsciiEscapesForJSON()
  {
    int[] arrayOfInt = CharTypes.get7BitOutputEscapes();
    return Arrays.copyOf(arrayOfInt, arrayOfInt.length);
  }
  
  public abstract int[] getEscapeCodesForAscii();
  
  public abstract SerializableString getEscapeSequence(int paramInt);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/io/CharacterEscapes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */