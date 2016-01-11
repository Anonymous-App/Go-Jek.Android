package com.kartuku.digitalwallet.common.dao;

import com.kartuku.digitalwallet.common.entity.TransactionHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface TransactionHistoryDao
  extends JpaRepository<TransactionHistory, Long>, JpaSpecificationExecutor<TransactionHistory>
{
  @Query
  public abstract List<TransactionHistory> findByProductType(String paramString);
  
  @Query("SELECT t FROM txn_history t WHERE t.txnReference = :txnRef OR t.authTxnReference = :txnRef")
  public abstract List<TransactionHistory> findByTxnRef(@Param("txnRef") String paramString);
  
  @Query
  public abstract List<TransactionHistory> findByTxnReference(String paramString);
  
  @Query
  public abstract List<TransactionHistory> findByTxnType(String paramString);
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dao/TransactionHistoryDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */