package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.kd;
import com.google.android.gms.internal.kf;
import com.google.android.gms.internal.kh;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class e
{
  private static Map<String, MetadataField<?>> PK = new HashMap();
  
  static
  {
    b(kd.PM);
    b(kd.Qm);
    b(kd.Qd);
    b(kd.Qk);
    b(kd.Qn);
    b(kd.PX);
    b(kd.PY);
    b(kd.PV);
    b(kd.Qa);
    b(kd.Qi);
    b(kd.PN);
    b(kd.Qf);
    b(kd.PP);
    b(kd.PW);
    b(kd.PQ);
    b(kd.PR);
    b(kd.PS);
    b(kd.Qc);
    b(kd.PZ);
    b(kd.Qe);
    b(kd.Qg);
    b(kd.Qh);
    b(kd.Qj);
    b(kd.Qo);
    b(kd.Qp);
    b(kd.PU);
    b(kd.PT);
    b(kd.Ql);
    b(kd.Qb);
    b(kd.PO);
    b(kd.Qq);
    b(kd.Qr);
    b(kd.Qs);
    b(kf.Qt);
    b(kf.Qv);
    b(kf.Qw);
    b(kf.Qx);
    b(kf.Qu);
    b(kh.Qz);
    b(kh.QA);
  }
  
  private static void b(MetadataField<?> paramMetadataField)
  {
    if (PK.containsKey(paramMetadataField.getName())) {
      throw new IllegalArgumentException("Duplicate field name registered: " + paramMetadataField.getName());
    }
    PK.put(paramMetadataField.getName(), paramMetadataField);
  }
  
  public static MetadataField<?> bj(String paramString)
  {
    return (MetadataField)PK.get(paramString);
  }
  
  public static Collection<MetadataField<?>> in()
  {
    return Collections.unmodifiableCollection(PK.values());
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/drive/metadata/internal/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */