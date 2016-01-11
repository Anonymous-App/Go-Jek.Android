package com.kartuku.digitalwallet.common.dao;

import com.kartuku.digitalwallet.common.entity.NotificationReceiver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public abstract interface DwNotificationReceiverDao
  extends JpaRepository<NotificationReceiver, Long>, JpaSpecificationExecutor<NotificationReceiver>
{}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/kartuku/digitalwallet/common/dao/DwNotificationReceiverDao.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */