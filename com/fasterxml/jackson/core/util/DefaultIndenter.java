package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;

public class DefaultIndenter
  extends DefaultPrettyPrinter.NopIndenter
{
  private static final int INDENT_LEVELS = 16;
  public static final DefaultIndenter SYSTEM_LINEFEED_INSTANCE;
  public static final String SYS_LF;
  private static final long serialVersionUID = 1L;
  private final int charsPerLevel;
  private final String eol;
  private final char[] indents;
  
  static
  {
    try
    {
      String str1 = System.getProperty("line.separator");
      SYS_LF = str1;
      SYSTEM_LINEFEED_INSTANCE = new DefaultIndenter("  ", SYS_LF);
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        String str2 = "\n";
      }
    }
  }
  
  public DefaultIndenter()
  {
    this("  ", SYS_LF);
  }
  
  public DefaultIndenter(String paramString1, String paramString2)
  {
    this.charsPerLevel = paramString1.length();
    this.indents = new char[paramString1.length() * 16];
    int j = 0;
    int i = 0;
    while (i < 16)
    {
      paramString1.getChars(0, paramString1.length(), this.indents, j);
      j += paramString1.length();
      i += 1;
    }
    this.eol = paramString2;
  }
  
  public String getEol()
  {
    return this.eol;
  }
  
  public String getIndent()
  {
    return new String(this.indents, 0, this.charsPerLevel);
  }
  
  public boolean isInline()
  {
    return false;
  }
  
  public DefaultIndenter withIndent(String paramString)
  {
    if (paramString.equals(getIndent())) {
      return this;
    }
    return new DefaultIndenter(paramString, this.eol);
  }
  
  public DefaultIndenter withLinefeed(String paramString)
  {
    if (paramString.equals(this.eol)) {
      return this;
    }
    return new DefaultIndenter(getIndent(), paramString);
  }
  
  public void writeIndentation(JsonGenerator paramJsonGenerator, int paramInt)
    throws IOException
  {
    paramJsonGenerator.writeRaw(this.eol);
    if (paramInt > 0)
    {
      paramInt *= this.charsPerLevel;
      while (paramInt > this.indents.length)
      {
        paramJsonGenerator.writeRaw(this.indents, 0, this.indents.length);
        paramInt -= this.indents.length;
      }
      paramJsonGenerator.writeRaw(this.indents, 0, paramInt);
    }
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/util/DefaultIndenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */