package com.analytique.transformer.theater;

import com.analytique.entity.movie.BookingData;
import com.analytique.exception.AnalytiqueException;
import com.analytique.util.DateTimeUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

    public static final String OCCUPIED = "occupied";
    public static final String CAPACITY = "capacity";
    public static final char NO_SEAT = '0';
    public static char BOOKED = '3';
    public static char AVAILABLE = '1';
    public static final char BLANK_SPACE = '0';


    /*public static void main(String[] args) {
        String line= "Royal:A:0000000005:4:N:0|PLATINUM:B:0000000001:3:N:0|GOLD:C:0000000002:2:N:0|SILVER:D:0000000003:1:N:0" +
                "" +
                "||1:B:A201:A202:A303:A304:A205:A206:A000:A000:A000:A000:A000:A000:A000:A000:A315:A316:A317:A318:A319:A320|1:A:B201:B202:B203:B204:B205:B206:B207:B208:B209:B210:B211:B212:B213:B314:B315:B316:B317:B318:B219:B220|2:B:B301:B302:B303:B304:B305:B306:B307:B208:B209:B210:B211:B212:B213:B214:B215:B216:B217:B318:B319:B320|3:C:B301:B302:B303:B304:B305:B306:B307:B208:B209:B210:B211:B312:B313:B314:B315:B316:B317:B318:B319:B320|1:D:C201:C202:C203:C204:C205:C206:C207:C208:C209:C210:C211:C212:C213:C214:C215:C316:C317:C318:C219:C220|2:E:C301:C302:C303:C304:C305:C306:C307:C208:C209:C210:C211:C212:C213:C214:C215:C316:C317:C318:C319:C320|3:F:C301:C302:C303:C304:C305:C306:C307:C308:C309:C310:C311:C312:C313:C314:C315:C316:C317:C318:C319:C320|4:G:C301:C302:C303:C304:C305:C306:C307:C308:C309:C310:C311:C312:C313:C314:C315:C316:C317:C318:C319:C320|5:H:C301:C302:C303:C304:C305:C306:C307:C308:C309:C310:C311:C312:C313:C314:C315:C316:C317:C318:C319:C320|1:I:D301:D302:D303:D304:D305:D306:D307:D308:D309:D210:D211:D212:D313:D314:D315:D316:D317:D318:D319:D320|";
        String[] split = line.split("\\|\\|");
        Map<String,String> seatClassMap=getSeatClass(split[0]);

        for (String seatClass :seatClassMap.keySet()){
            BookingData bookingData = new BookingData();

            String seatMapForClass=getOccupancyAndCapacityForSeatClass(seatClassMap.get(seatClass),split[1]);
        }


    }*/

    private static String getOccupancyAndCapacityForSeatClass(String classLetter, String seatMap) {
        for (String str : seatMap.split("\\|")) {
            Pattern pattern = Pattern.compile(classLetter+"\\d+");
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                return str;
            }

        }
        return null;
    }

    private static Map<String, String> getSeatClass(String seatClassStr) {
        Map<String, String> seatClass = new HashMap<>();
        try {
            String[] splitStr = seatClassStr.split("\\|");
            for (String str : splitStr) {
                seatClass.put(str.split(":")[0], str.split(":")[1]);
            }
        }catch (Exception e){
            throw new AnalytiqueException("Failed while getting seatClass from " + e + seatClassStr);
        }
        return seatClass;
    }

    void calculateOccupancy(String seatMap) {
        HashMap<String, Integer> occupancyAndCapacity = new HashMap<>();

        if (seatMap != null) {
            String[] seatCodes = seatMap.split(":");
            Integer capacity = 0;
            Integer occupied = 0;
            if (!seatCodes[0].equals("|1")) {
                BOOKED = '2';
                AVAILABLE = '1';
            }
            for (int i = 2; i < seatCodes.length; i++) {
                char c = seatCodes[i].charAt(1);
                if (c == BOOKED) {
                    occupied++;
                    capacity++;
                } else if (c == AVAILABLE) {
                    capacity++;
                } else if (c == NO_SEAT) {

                } else {

                }
            }

            occupancyAndCapacity.put(OCCUPIED, occupied);
            occupancyAndCapacity.put(CAPACITY, capacity);

        }

    }
}
