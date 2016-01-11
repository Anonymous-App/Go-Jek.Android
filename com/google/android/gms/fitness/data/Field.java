package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class Field
  implements SafeParcelable
{
  public static final Parcelable.Creator<Field> CREATOR = new j();
  public static final Field FIELD_ACCURACY;
  public static final Field FIELD_ACTIVITY = br("activity");
  public static final Field FIELD_ALTITUDE;
  public static final Field FIELD_AVERAGE;
  public static final Field FIELD_BPM;
  public static final Field FIELD_CALORIES;
  public static final Field FIELD_CONFIDENCE = bs("confidence");
  public static final Field FIELD_DISTANCE;
  public static final Field FIELD_DURATION;
  public static final Field FIELD_HEIGHT;
  public static final Field FIELD_HIGH_LATITUDE;
  public static final Field FIELD_HIGH_LONGITUDE;
  public static final Field FIELD_LATITUDE;
  public static final Field FIELD_LONGITUDE;
  public static final Field FIELD_LOW_LATITUDE;
  public static final Field FIELD_LOW_LONGITUDE;
  public static final Field FIELD_MAX;
  public static final Field FIELD_MIN;
  public static final Field FIELD_NUM_SEGMENTS;
  public static final Field FIELD_REVOLUTIONS;
  public static final Field FIELD_RPM;
  public static final Field FIELD_SPEED;
  public static final Field FIELD_STEPS = br("steps");
  public static final Field FIELD_WATTS;
  public static final Field FIELD_WEIGHT;
  public static final int FORMAT_FLOAT = 2;
  public static final int FORMAT_INT32 = 1;
  public static final Field Td;
  public static final Field Te;
  public static final Field Tf;
  public static final Field Tg;
  private final int BR;
  private final int Th;
  private final String mName;
  
  static
  {
    FIELD_DURATION = br("duration");
    FIELD_BPM = bs("bpm");
    FIELD_LATITUDE = bs("latitude");
    FIELD_LONGITUDE = bs("longitude");
    FIELD_ACCURACY = bs("accuracy");
    FIELD_ALTITUDE = bs("altitude");
    FIELD_DISTANCE = bs("distance");
    FIELD_HEIGHT = bs("height");
    FIELD_WEIGHT = bs("weight");
    FIELD_SPEED = bs("speed");
    FIELD_RPM = bs("rpm");
    FIELD_REVOLUTIONS = br("revolutions");
    FIELD_CALORIES = bs("calories");
    FIELD_WATTS = bs("watts");
    FIELD_NUM_SEGMENTS = br("num_segments");
    FIELD_AVERAGE = bs("average");
    FIELD_MAX = bs("max");
    FIELD_MIN = bs("min");
    FIELD_LOW_LATITUDE = bs("low_latitude");
    FIELD_LOW_LONGITUDE = bs("low_longitude");
    FIELD_HIGH_LATITUDE = bs("high_latitude");
    FIELD_HIGH_LONGITUDE = bs("high_longitude");
    Td = br("edge_type");
    Te = bs("x");
    Tf = bs("y");
    Tg = bs("z");
  }
  
  Field(int paramInt1, String paramString, int paramInt2)
  {
    this.BR = paramInt1;
    this.mName = paramString;
    this.Th = paramInt2;
  }
  
  public Field(String paramString, int paramInt)
  {
    this(1, paramString, paramInt);
  }
  
  private boolean a(Field paramField)
  {
    return (this.mName.equals(paramField.mName)) && (this.Th == paramField.Th);
  }
  
  private static Field br(String paramString)
  {
    return new Field(paramString, 1);
  }
  
  private static Field bs(String paramString)
  {
    return new Field(paramString, 2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof Field)) && (a((Field)paramObject)));
  }
  
  public int getFormat()
  {
    return this.Th;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  int getVersionCode()
  {
    return this.BR;
  }
  
  public int hashCode()
  {
    return this.mName.hashCode();
  }
  
  public String toString()
  {
    String str2 = this.mName;
    if (this.Th == 1) {}
    for (String str1 = "i";; str1 = "f") {
      return String.format("%s(%s)", new Object[] { str2, str1 });
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    j.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/Field.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */