package com.google.android.gms.fitness;

import android.util.SparseArray;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.data.Value;

public class FitnessActivities
{
  public static final String AEROBICS = "aerobics";
  public static final String BADMINTON = "badminton";
  public static final String BASEBALL = "baseball";
  public static final String BASKETBALL = "basketball";
  public static final String BIATHLON = "biathlon";
  public static final String BIKING = "biking";
  public static final String BIKING_HAND = "biking.hand";
  public static final String BIKING_MOUNTAIN = "biking.mountain";
  public static final String BIKING_ROAD = "biking.road";
  public static final String BIKING_SPINNING = "biking.spinning";
  public static final String BIKING_STATIONARY = "biking.stationary";
  public static final String BIKING_UTILITY = "biking.utility";
  public static final String BOXING = "boxing";
  public static final String CALISTHENICS = "calisthenics";
  public static final String CIRCUIT_TRAINING = "circuit_training";
  public static final String CRICKET = "cricket";
  public static final String CURLING = "curling";
  public static final String DANCING = "dancing";
  public static final String DIVING = "diving";
  public static final String ELLIPTICAL = "elliptical";
  public static final String ERGOMETER = "ergometer";
  public static final String EXTRA_STATUS = "actionStatus";
  public static final String FENCING = "fencing";
  public static final String FOOTBALL_AMERICAN = "football.american";
  public static final String FOOTBALL_AUSTRALIAN = "football.australian";
  public static final String FOOTBALL_SOCCER = "football.soccer";
  public static final String FRISBEE_DISC = "frisbee_disc";
  public static final String GARDENING = "gardening";
  public static final String GOLF = "golf";
  public static final String GYMNASTICS = "gymnastics";
  public static final String HANDBALL = "handball";
  public static final String HIKING = "hiking";
  public static final String HOCKEY = "hockey";
  public static final String HORSEBACK_RIDING = "horseback_riding";
  public static final String HOUSEWORK = "housework";
  public static final String ICE_SKATING = "ice_skating";
  public static final String IN_VEHICLE = "in_vehicle";
  public static final String JUMP_ROPE = "jump_rope";
  public static final String KAYAKING = "kayaking";
  public static final String KETTLEBELL_TRAINING = "kettlebell_training";
  public static final String KICKBOXING = "kickboxing";
  public static final String KICK_SCOOTER = "kick_scooter";
  public static final String KITESURFING = "kitesurfing";
  public static final String MARTIAL_ARTS = "martial_arts";
  public static final String MEDITATION = "meditation";
  public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.activity/";
  public static final String MIXED_MARTIAL_ARTS = "martial_arts.mixed";
  public static final String ON_FOOT = "on_foot";
  public static final String OTHER = "other";
  public static final String P90X = "p90x";
  public static final String PARAGLIDING = "paragliding";
  public static final String PILATES = "pilates";
  public static final String POLO = "polo";
  public static final String RACQUETBALL = "racquetball";
  public static final String ROCK_CLIMBING = "rock_climbing";
  public static final String ROWING = "rowing";
  public static final String ROWING_MACHINE = "rowing.machine";
  public static final String RUGBY = "rugby";
  public static final String RUNNING = "running";
  public static final String RUNNING_JOGGING = "running.jogging";
  public static final String RUNNING_SAND = "running.sand";
  public static final String RUNNING_TREADMILL = "running.treadmill";
  public static final String SAILING = "sailing";
  public static final String SCUBA_DIVING = "scuba_diving";
  public static final String SKATEBOARDING = "skateboarding";
  public static final String SKATING = "skating";
  public static final String SKATING_CROSS = "skating.cross";
  public static final String SKATING_INDOOR = "skating.indoor";
  public static final String SKATING_INLINE = "skating.inline";
  public static final String SKIING = "skiing";
  public static final String SKIING_BACK_COUNTRY = "skiing.back_country";
  public static final String SKIING_CROSS_COUNTRY = "skiing.cross_country";
  public static final String SKIING_DOWNHILL = "skiing.downhill";
  public static final String SKIING_KITE = "skiing.kite";
  public static final String SKIING_ROLLER = "skiing.roller";
  public static final String SLEDDING = "sledding";
  public static final String SLEEP = "sleep";
  public static final String SNOWBOARDING = "snowboarding";
  public static final String SNOWMOBILE = "snowmobile";
  public static final String SNOWSHOEING = "snowshoeing";
  public static final String SQUASH = "squash";
  public static final String STAIR_CLIMBING = "stair_climbing";
  public static final String STAIR_CLIMBING_MACHINE = "stair_climbing.machine";
  public static final String STANDUP_PADDLEBOARDING = "standup_paddleboarding";
  public static final String STATUS_ACTIVE = "ActiveActionStatus";
  public static final String STATUS_COMPLETED = "CompletedActionStatus";
  public static final String STILL = "still";
  public static final String STRENGTH_TRAINING = "strength_training";
  public static final String SURFING = "surfing";
  public static final String SWIMMING = "swimming";
  public static final String SWIMMING_OPEN_WATER = "swimming.open_water";
  public static final String SWIMMING_POOL = "swimming.pool";
  private static final SparseArray<String> So = new SparseArray(109);
  public static final String TABLE_TENNIS = "table_tennis";
  public static final String TEAM_SPORTS = "team_sports";
  public static final String TENNIS = "tennis";
  public static final String TILTING = "tilting";
  public static final String TREADMILL = "treadmill";
  public static final String UNKNOWN = "unknown";
  public static final String VOLLEYBALL = "volleyball";
  public static final String VOLLEYBALL_BEACH = "volleyball.beach";
  public static final String VOLLEYBALL_INDOOR = "volleyball.indoor";
  public static final String WAKEBOARDING = "wakeboarding";
  public static final String WALKING = "walking";
  public static final String WALKING_FITNESS = "walking.fitness";
  public static final String WALKING_NORDIC = "walking.nordic";
  public static final String WALKING_TREADMILL = "walking.treadmill";
  public static final String WATER_POLO = "water_polo";
  public static final String WEIGHTLIFTING = "weightlifting";
  public static final String WHEELCHAIR = "wheelchair";
  public static final String WINDSURFING = "windsurfing";
  public static final String YOGA = "yoga";
  public static final String ZUMBA = "zumba";
  
  static
  {
    So.put(9, "aerobics");
    So.put(10, "badminton");
    So.put(11, "baseball");
    So.put(12, "basketball");
    So.put(13, "biathlon");
    So.put(1, "biking");
    So.put(14, "biking.hand");
    So.put(15, "biking.mountain");
    So.put(16, "biking.road");
    So.put(17, "biking.spinning");
    So.put(18, "biking.stationary");
    So.put(19, "biking.utility");
    So.put(20, "boxing");
    So.put(21, "calisthenics");
    So.put(22, "circuit_training");
    So.put(23, "cricket");
    So.put(106, "curling");
    So.put(24, "dancing");
    So.put(102, "diving");
    So.put(25, "elliptical");
    So.put(103, "ergometer");
    So.put(6, "exiting_vehicle");
    So.put(26, "fencing");
    So.put(27, "football.american");
    So.put(28, "football.australian");
    So.put(29, "football.soccer");
    So.put(30, "frisbee_disc");
    So.put(31, "gardening");
    So.put(32, "golf");
    So.put(33, "gymnastics");
    So.put(34, "handball");
    So.put(35, "hiking");
    So.put(36, "hockey");
    So.put(37, "horseback_riding");
    So.put(38, "housework");
    So.put(104, "ice_skating");
    So.put(0, "in_vehicle");
    So.put(39, "jump_rope");
    So.put(40, "kayaking");
    So.put(41, "kettlebell_training");
    So.put(107, "kick_scooter");
    So.put(42, "kickboxing");
    So.put(43, "kitesurfing");
    So.put(44, "martial_arts");
    So.put(45, "meditation");
    So.put(46, "martial_arts.mixed");
    So.put(2, "on_foot");
    So.put(108, "other");
    So.put(47, "p90x");
    So.put(48, "paragliding");
    So.put(49, "pilates");
    So.put(50, "polo");
    So.put(51, "racquetball");
    So.put(52, "rock_climbing");
    So.put(53, "rowing");
    So.put(54, "rowing.machine");
    So.put(55, "rugby");
    So.put(8, "running");
    So.put(56, "running.jogging");
    So.put(57, "running.sand");
    So.put(58, "running.treadmill");
    So.put(59, "sailing");
    So.put(60, "scuba_diving");
    So.put(61, "skateboarding");
    So.put(62, "skating");
    So.put(63, "skating.cross");
    So.put(105, "skating.indoor");
    So.put(64, "skating.inline");
    So.put(65, "skiing");
    So.put(66, "skiing.back_country");
    So.put(67, "skiing.cross_country");
    So.put(68, "skiing.downhill");
    So.put(69, "skiing.kite");
    So.put(70, "skiing.roller");
    So.put(71, "sledding");
    So.put(72, "sleep");
    So.put(73, "snowboarding");
    So.put(74, "snowmobile");
    So.put(75, "snowshoeing");
    So.put(76, "squash");
    So.put(77, "stair_climbing");
    So.put(78, "stair_climbing.machine");
    So.put(79, "standup_paddleboarding");
    So.put(3, "still");
    So.put(80, "strength_training");
    So.put(81, "surfing");
    So.put(82, "swimming");
    So.put(83, "swimming.pool");
    So.put(84, "swimming.open_water");
    So.put(85, "table_tennis");
    So.put(86, "team_sports");
    So.put(87, "tennis");
    So.put(5, "tilting");
    So.put(88, "treadmill");
    So.put(4, "unknown");
    So.put(89, "volleyball");
    So.put(90, "volleyball.beach");
    So.put(91, "volleyball.indoor");
    So.put(92, "wakeboarding");
    So.put(7, "walking");
    So.put(93, "walking.fitness");
    So.put(94, "walking.nordic");
    So.put(95, "walking.treadmill");
    So.put(96, "water_polo");
    So.put(97, "weightlifting");
    So.put(98, "wheelchair");
    So.put(99, "windsurfing");
    So.put(100, "yoga");
    So.put(101, "zumba");
  }
  
  public static int bp(String paramString)
  {
    int i = So.indexOfValue(paramString);
    if (i < 0) {}
    do
    {
      return 4;
      i = So.keyAt(i);
    } while (i > 108);
    return i;
  }
  
  public static String getMimeType(String paramString)
  {
    return "vnd.google.fitness.activity/" + paramString;
  }
  
  public static String getName(int paramInt)
  {
    String str = (String)So.get(paramInt);
    if (str == null) {
      throw new IllegalArgumentException("Unknown activity " + paramInt);
    }
    return str;
  }
  
  public static String getValue(DataPoint paramDataPoint)
  {
    return getName(paramDataPoint.getValue(Field.FIELD_ACTIVITY).asInt());
  }
  
  public static void setValue(DataPoint paramDataPoint, String paramString)
  {
    paramDataPoint.getValue(Field.FIELD_ACTIVITY).setInt(bp(paramString));
  }
}


/* Location:              /Users/michael/Downloads/dex2jar-2.0/GO_JEK.jar!/com/google/android/gms/fitness/FitnessActivities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */