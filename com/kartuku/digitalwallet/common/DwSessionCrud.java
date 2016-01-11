package com.kartuku.digitalwallet.common;

import com.kartuku.digitalwallet.common.entity.DwSession;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DwSessionCrud
{
  private Logger ˊ = LoggerFactory.getLogger(DwSessionCrud.class);
  
  public List<DwSession> find(String paramString)
  {
    throw new NullPointerException();
  }
  
  public DwSession findOne(String paramString)
  {
    throw new NullPointerException();
  }
  
  @Transactional
  public DwSession save(DwSession paramDwSession)
  {
    new ArrayList();
    try
    {
      paramDwSession.getServerName();
      throw new NullPointerException();
    }
    catch (Exception paramDwSession)
    {
      this.ˊ.error("Failed to save session, Cause : ");
      paramDwSession.getStackTrace();
    }
    return null;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/DwSessionCrud.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */