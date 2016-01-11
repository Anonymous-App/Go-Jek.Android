package com.kartuku.digitalwallet.common.service;

import com.kartuku.digitalwallet.common.entity.TransactionHistory;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionHistoryService
{
  public List<TransactionHistory> findByProductType(String paramString)
  {
    throw new NullPointerException();
  }
  
  public List<TransactionHistory> findByTxnReference(String paramString)
  {
    throw new NullPointerException();
  }
  
  public List<TransactionHistory> findByTxnType(String paramString)
  {
    throw new NullPointerException();
  }
  
  @Transactional
  public TransactionHistory saveOrUpdate(TransactionHistory paramTransactionHistory)
  {
    try
    {
      throw new NullPointerException();
    }
    catch (Exception paramTransactionHistory)
    {
      paramTransactionHistory.getStackTrace();
    }
    return null;
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/service/TransactionHistoryService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */