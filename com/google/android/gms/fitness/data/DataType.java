package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.jr;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DataType
  implements SafeParcelable
{
  public static final DataType AGGREGATE_ACTIVITY_SUMMARY;
  public static final DataType AGGREGATE_DISTANCE_DELTA;
  public static final DataType AGGREGATE_HEART_RATE_SUMMARY;
  public static final Set<DataType> AGGREGATE_INPUT_TYPES;
  public static final DataType AGGREGATE_LOCATION_BOUNDING_BOX;
  public static final DataType AGGREGATE_POWER_SUMMARY;
  public static final DataType AGGREGATE_SPEED_SUMMARY;
  public static final DataType AGGREGATE_STEP_COUNT_DELTA;
  public static final DataType AGGREGATE_WEIGHT_SUMMARY;
  public static final Parcelable.Creator<DataType> CREATOR = new h();
  public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.data_type/";
  public static final DataType ST;
  public static final DataType SU;
  private static final Map<DataType, List<DataType>> SV;
  public static final DataType[] SW;
  public static final String[] SX;
  public static final DataType TYPE_ACTIVITY_SAMPLE;
  public static final DataType TYPE_ACTIVITY_SEGMENT;
  public static final DataType TYPE_CALORIES_CONSUMED;
  public static final DataType TYPE_CALORIES_EXPENDED;
  public static final DataType TYPE_CYCLING_PEDALING_CADENCE;
  public static final DataType TYPE_CYCLING_PEDALING_CUMULATIVE;
  public static final DataType TYPE_CYCLING_WHEEL_REVOLUTION;
  public static final DataType TYPE_CYCLING_WHEEL_RPM;
  public static final DataType TYPE_DISTANCE_CUMULATIVE;
  public static final DataType TYPE_DISTANCE_DELTA;
  public static final DataType TYPE_HEART_RATE_BPM;
  public static final DataType TYPE_HEIGHT;
  public static final DataType TYPE_LOCATION_SAMPLE;
  public static final DataType TYPE_POWER_SAMPLE;
  public static final DataType TYPE_SPEED;
  public static final DataType TYPE_STEP_COUNT_CADENCE;
  public static final DataType TYPE_STEP_COUNT_CUMULATIVE;
  public static final DataType TYPE_STEP_COUNT_DELTA = new DataType("com.google.step_count.delta", new Field[] { Field.FIELD_STEPS });
  public static final DataType TYPE_WEIGHT;
  private final int BR;
  private final List<Field> SY;
  private final String mName;
  
  static
  {
    TYPE_STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", new Field[] { Field.FIELD_STEPS });
    TYPE_STEP_COUNT_CADENCE = new DataType("com.google.step_count.cadence", new Field[] { Field.FIELD_RPM });
    TYPE_ACTIVITY_SEGMENT = new DataType("com.google.activity.segment", new Field[] { Field.FIELD_ACTIVITY });
    TYPE_CALORIES_CONSUMED = new DataType("com.google.calories.consumed", new Field[] { Field.FIELD_CALORIES });
    TYPE_CALORIES_EXPENDED = new DataType("com.google.calories.expended", new Field[] { Field.FIELD_CALORIES });
    TYPE_POWER_SAMPLE = new DataType("com.google.power.sample", new Field[] { Field.FIELD_WATTS });
    TYPE_ACTIVITY_SAMPLE = new DataType("com.google.activity.sample", new Field[] { Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE });
    ST = new DataType("com.google.activity.edge", new Field[] { Field.FIELD_ACTIVITY, Field.Td });
    SU = new DataType("com.google.accelerometer", new Field[] { Field.Te, Field.Tf, Field.Tg });
    TYPE_HEART_RATE_BPM = new DataType("com.google.heart_rate.bpm", new Field[] { Field.FIELD_BPM });
    TYPE_LOCATION_SAMPLE = new DataType("com.google.location.sample", new Field[] { Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE });
    TYPE_DISTANCE_DELTA = new DataType("com.google.distance.delta", new Field[] { Field.FIELD_DISTANCE });
    TYPE_DISTANCE_CUMULATIVE = new DataType("com.google.distance.cumulative", new Field[] { Field.FIELD_DISTANCE });
    TYPE_SPEED = new DataType("com.google.speed", new Field[] { Field.FIELD_SPEED });
    TYPE_CYCLING_WHEEL_REVOLUTION = new DataType("com.google.cycling.wheel_revolution.cumulative", new Field[] { Field.FIELD_REVOLUTIONS });
    TYPE_CYCLING_WHEEL_RPM = new DataType("com.google.cycling.wheel_revolution.rpm", new Field[] { Field.FIELD_RPM });
    TYPE_CYCLING_PEDALING_CUMULATIVE = new DataType("com.google.cycling.pedaling.cumulative", new Field[] { Field.FIELD_REVOLUTIONS });
    TYPE_CYCLING_PEDALING_CADENCE = new DataType("com.google.cycling.pedaling.cadence", new Field[] { Field.FIELD_RPM });
    TYPE_HEIGHT = new DataType("com.google.height", new Field[] { Field.FIELD_HEIGHT });
    TYPE_WEIGHT = new DataType("com.google.weight", new Field[] { Field.FIELD_WEIGHT });
    AGGREGATE_INPUT_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new DataType[] { TYPE_STEP_COUNT_DELTA, TYPE_DISTANCE_DELTA, TYPE_ACTIVITY_SEGMENT, TYPE_SPEED, TYPE_HEART_RATE_BPM, TYPE_WEIGHT, TYPE_LOCATION_SAMPLE })));
    AGGREGATE_ACTIVITY_SUMMARY = new DataType("com.google.activity.summary", new Field[] { Field.FIELD_ACTIVITY, Field.FIELD_DURATION, Field.FIELD_NUM_SEGMENTS });
    AGGREGATE_STEP_COUNT_DELTA = TYPE_STEP_COUNT_DELTA;
    AGGREGATE_DISTANCE_DELTA = TYPE_DISTANCE_DELTA;
    AGGREGATE_HEART_RATE_SUMMARY = new DataType("com.google.heart_rate.summary", new Field[] { Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN });
    AGGREGATE_LOCATION_BOUNDING_BOX = new DataType("com.google.location.bounding_box", new Field[] { Field.FIELD_LOW_LATITUDE, Field.FIELD_LOW_LONGITUDE, Field.FIELD_HIGH_LATITUDE, Field.FIELD_HIGH_LONGITUDE });
    AGGREGATE_POWER_SUMMARY = new DataType("com.google.power.summary", new Field[] { Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN });
    AGGREGATE_SPEED_SUMMARY = new DataType("com.google.speed.summary", new Field[] { Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN });
    AGGREGATE_WEIGHT_SUMMARY = new DataType("com.google.weight.summary", new Field[] { Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN });
    SV = new HashMap() {};
    SW = new DataType[] { SU, ST, TYPE_ACTIVITY_SAMPLE, TYPE_ACTIVITY_SEGMENT, AGGREGATE_ACTIVITY_SUMMARY, TYPE_CALORIES_CONSUMED, TYPE_CALORIES_EXPENDED, TYPE_CYCLING_PEDALING_CADENCE, TYPE_CYCLING_PEDALING_CUMULATIVE, TYPE_CYCLING_WHEEL_REVOLUTION, TYPE_CYCLING_WHEEL_RPM, TYPE_DISTANCE_CUMULATIVE, TYPE_DISTANCE_DELTA, TYPE_HEART_RATE_BPM, AGGREGATE_HEART_RATE_SUMMARY, TYPE_HEIGHT, AGGREGATE_LOCATION_BOUNDING_BOX, TYPE_LOCATION_SAMPLE, TYPE_POWER_SAMPLE, AGGREGATE_POWER_SUMMARY, TYPE_SPEED, AGGREGATE_SPEED_SUMMARY, TYPE_STEP_COUNT_CADENCE, TYPE_STEP_COUNT_CUMULATIVE, TYPE_STEP_COUNT_DELTA, TYPE_WEIGHT, AGGREGATE_WEIGHT_SUMMARY };
    SX = new String[] { SU.getName(), ST.getName(), TYPE_ACTIVITY_SAMPLE.getName(), TYPE_ACTIVITY_SEGMENT.getName(), AGGREGATE_ACTIVITY_SUMMARY.getName(), TYPE_CALORIES_CONSUMED.getName(), TYPE_CALORIES_EXPENDED.getName(), TYPE_CYCLING_PEDALING_CADENCE.getName(), TYPE_CYCLING_PEDALING_CUMULATIVE.getName(), TYPE_CYCLING_WHEEL_REVOLUTION.getName(), TYPE_CYCLING_WHEEL_RPM.getName(), TYPE_DISTANCE_CUMULATIVE.getName(), TYPE_DISTANCE_DELTA.getName(), TYPE_HEART_RATE_BPM.getName(), AGGREGATE_HEART_RATE_SUMMARY.getName(), TYPE_HEIGHT.getName(), AGGREGATE_LOCATION_BOUNDING_BOX.getName(), TYPE_LOCATION_SAMPLE.getName(), TYPE_POWER_SAMPLE.getName(), AGGREGATE_POWER_SUMMARY.getName(), TYPE_SPEED.getName(), AGGREGATE_SPEED_SUMMARY.getName(), TYPE_STEP_COUNT_CADENCE.getName(), TYPE_STEP_COUNT_CUMULATIVE.getName(), TYPE_STEP_COUNT_DELTA.getName(), TYPE_WEIGHT.getName(), AGGREGATE_WEIGHT_SUMMARY.getName() };
  }
  
  DataType(int paramInt, String paramString, List<Field> paramList)
  {
    this.BR = paramInt;
    this.mName = paramString;
    this.SY = Collections.unmodifiableList(paramList);
  }
  
  public DataType(String paramString, Field... paramVarArgs)
  {
    this(1, paramString, jr.b(paramVarArgs));
  }
  
  private boolean a(DataType paramDataType)
  {
    return (this.mName.equals(paramDataType.mName)) && (this.SY.equals(paramDataType.SY));
  }
  
  public static List<DataType> getAggregatesForInput(DataType paramDataType)
  {
    paramDataType = (List)SV.get(paramDataType);
    if (paramDataType == null) {
      return Collections.emptyList();
    }
    return Collections.unmodifiableList(paramDataType);
  }
  
  public static String getMimeType(DataType paramDataType)
  {
    return "vnd.google.fitness.data_type/" + paramDataType.getName();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataType)) && (a((DataType)paramObject)));
  }
  
  public List<Field> getFields()
  {
    return this.SY;
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
  
  public String iQ()
  {
    if (this.mName.startsWith("com.google.")) {
      return this.mName.substring(11);
    }
    return this.mName;
  }
  
  public int indexOf(Field paramField)
  {
    if (this.SY.contains(paramField)) {
      return this.SY.indexOf(paramField);
    }
    throw new IllegalArgumentException(String.format("%s not a field of %s", new Object[] { paramField, this }));
  }
  
  public String toString()
  {
    return String.format("DataType{%s%s}", new Object[] { this.mName, this.SY });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    h.a(this, paramParcel, paramInt);
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/data/DataType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */