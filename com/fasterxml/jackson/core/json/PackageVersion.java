package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.util.VersionUtil;

public final class PackageVersion
  implements Versioned
{
  public static final Version VERSION = VersionUtil.parseVersion("2.6.3", "com.fasterxml.jackson.core", "jackson-core");
  
  public Version version()
  {
    return VERSION;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/fasterxml/jackson/core/json/PackageVersion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */