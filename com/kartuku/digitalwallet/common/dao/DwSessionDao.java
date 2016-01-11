package com.kartuku.digitalwallet.common.dao;

import com.kartuku.digitalwallet.common.entity.DwSession;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface DwSessionDao
  extends JpaRepository<DwSession, String>, JpaSpecificationExecutor<DwSession>
{
  public abstract List<DwSession> findByServerName(String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dao/DwSessionDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */