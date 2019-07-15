package comp1406a2;
import java.util.Arrays;
/** Builds a sample database that you can use to help 
 with your own coding/debugging
 */
public class BuildDatabase{

	/** Build a small database of weather stations
	  * @param args is not used in this program
    */
    
  public static void main(String[] args){
    // sample database
    WeatherDatabase db = new WeatherDatabase();
    WeatherDatabase db2 = new WeatherDatabase();

    // some WeatherStations
    WeatherStation[] stations = new WeatherStation[3];
    stations[0] = new WeatherStation("YOW-Airport", 1);
    stations[1] = new WeatherStation("Downtown", 2);
    stations[2] = new WeatherStation("Fallowfield-Railway-Station", 3);
    int[] days = {1,2,2,2,4,7,11,20,30,32, 33};
    double[] temps = {10.0, 11, 12, 10, 9.2, 28.1, 10.5, 20, 23, 24, 28.1};

    // populate YOW with some weather reports
    System.out.println("Building YOW WeatherStation");
    for(int i=0; i<days.length; i+=1){
      System.out.println("Downtown max temp: "+stations[1].getReportWithMaxTemp());
      Temperature t = new Temperature(temps[i]);
      TimeStamp time = new TimeStamp(days[i],7, 15+i);
      WeatherReport wr = new WeatherReport(t, time, stations[0]);
      System.out.println( "  " + wr.toString() );
      stations[0].addReport(wr);
    }
     //System.out.println("Max temp at FallowField: "+db.stations[1].getReportWithMaxTemp());

    // populate Down town weather station
    days = new int[]{1,2,4,7};
    temps = new double[]{12.1, 12.3, 15.2, 16.0, 17.0};
    System.out.println("Building Downtown WeatherStation");
    for(int i=0; i<days.length; i+=1){
      System.out.println("Downtown max temp: "+stations[1].getReportWithMaxTemp());
      Temperature t = new Temperature(temps[i]);
      TimeStamp time = new TimeStamp(days[i],8, i);
      WeatherReport wr = new WeatherReport(t, time, stations[1]);
      System.out.println( "  " + wr.toString() );
      stations[1].addReport(wr);
    }
    System.out.println( "Highest: " +stations[1].highest);

    // populate Fallowfield
    days = new int[]{1,1,1,2,2,2,3,3,3,4,4,4,5,5,6,6,6,6,7,7,7,8,8,8};
    temps = new double[]{45, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,-1,-1,
                         10,10,10,10,20,20,20,25,25,25};
    System.out.println("Building Fallowfield WeatherStation");
    for(int i=0; i<days.length; i+=1){
      Temperature t = new Temperature(temps[i]);
      TimeStamp time = new TimeStamp(days[i],12, 30+i);
      WeatherReport wr = new WeatherReport(t, time, stations[2]);
      System.out.println( "  " + wr.toString() );
      stations[2].addReport(wr);
    }
    
    // add stations to database
    for(int i=0; i<stations.length; i+=1){
      db.addWeatherStation(stations[i]);
      System.out.println("adding " + stations[i].toString());
    }
    System.out.println("Number of stations in db: "+db.numberOfWeatherStations());
    for(int i=0; i<stations.length-1; i+=1){
      db2.addWeatherStation(stations[i]);
      System.out.println("adding " + stations[i].toString());
    }
    /*
    System.out.println("Number of stations in db2: "+db2.numberOfWeatherStations());
    System.out.println(Arrays.toString(db2.getWeatherStations()));
    System.out.println(Arrays.toString(db.getWeatherStations()));
    */
    db.removeWeatherStation(2);
    //System.out.println(Arrays.toString(db.getWeatherStations()));
    //System.out.println("Number of stations in db: "+db.numberOfWeatherStations());
    //System.out.println("Max temp ever recorded for db: "+db.getMaxTemperature());
    //System.out.println("Max temp ever recorded for db2: "+db2.getMaxTemperature());
   // System.out.println("Max temp at Yow: "+db.stations[0].getReportWithMaxTemp());
    //System.out.println("Max temp at FallowField: "+db.stations[1].getReportWithMaxTemp());
   // System.out.println("Max temp at Downtown: "+db2.stations[1].getReportWithMaxTemp());
    //System.out.println("FallowField reports: "+Arrays.toString(db.stations[1].getReports()));
    //System.out.println(Arrays.toString(db2.stations[0].getReports()));
    //System.out.println("Max temp in db: "+db.getMaxTemperature());
   	System.out.println("Max temp in db on Day 7: "+db.getMaxTemperature(7));
    //System.out.println("Max temp in db2 on Day 2: "+ db2.getMaxTemperature(2));
    //System.out.println("Average temp in db2 from day 1 to Day 2: "+ db2.averageTemperature(1,2));
    //db2.averageTemperature(1,2);
    Temperature t = new Temperature(50);
    t. setTemp(-500, "f");
    System.out.println(t);
  }



}
