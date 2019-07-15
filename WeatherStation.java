package comp1406a2;

/** A weather station keeps a collection of weather reports.
  * The actual weather station it models will make temperature
  * observations which provides the data for the reports.
  *
  * A weather station only needs to remember the last 10
  * reports added to it (in the order that they are added),
  * in addition to the report with the highest temperature that
  * has ever been and recorded/added to this weather station.
  * That is, it only needs to store 11 weather reports in total.
  *
  * Notes: The highest ever temperature record might be one of the
  *        last 10 added reports but it also might not.
  *        You must store the last 10 reports in an array.
  *        The higherst ever temperature report does not need to be
  *        sotred in this array (unless it was one of the last 10).
  */
public class WeatherStation{

/* --------------------------------------------------------------------------/
/* --------------------------------------------------------------------------/
/* ----------------------------------------------------- */
/*                                                       */
/* BEGIN  --  do NOT change anything until the end of    */
/*            this block of attributes, constructor and  */
/*            and methods                                */
/*                                                       */
/* ----------------------------------------------------- */

  /** The name of this weather station */
  protected final String name;

  /** The id number of this weather station. Should be unique. */
  protected final int    id;

  /** Initializes this weather station's name and id  */
  public WeatherStation(String nameOfStation, int idOfStation){
    this.name = nameOfStation;
    this.id   = idOfStation;
  }

  /** Getter for this weather station's name
   * @return the name of this weather station
   */
  public String getName(){ return this.name;  }

  /** Getter for this weather station's id number
   * @return the ID of this weather station
   */
  public int    getID(){ return this.id; }

  /** Creates a weather report
   * @param temperature is a valid temperature object correspinding to
   *                    an obervation at this weather station.
   * @param time is the time that the temoerature was recorded.
   * @return a weather report for this weather station recording
   *         the temperature and time of when the obervation was made.
   */
  public WeatherReport makeReport(Temperature temperature, TimeStamp time){
    return new WeatherReport(temperature, time, this);
  }

	/** A nice String representation of a weather station object */
  @Override
  public String toString(){
    return this.name + " [id:" + this.id + "]";
  }
/* ----------------------------------------------------- */
/*                                                       */
/* END - complete the methods below these comments       */
/*                                                       */
/* ----------------------------------------------------- */
/* --------------------------------------------------------------------------/*
/* --------------------------------------------------------------------------/*
  /** an array that stores 11 reports, the last index storing the report with the highest temperature that the station has observed. */
  WeatherReport[] reports = new WeatherReport[11];
  /** value that keeps track of the number of reports in the station*/
  int numReports = 0;
  /** variable that keeps track of the the report with the highest temperature that the station has observed  */
  WeatherReport highest;

  /** Adds a report to the station.
   *
   * @param report is a WeatherReport to be added to this WeatherStation
   * @return this WeatherStation. (Do NOT alter the return statement of this
   *         method.)
   *
   */
  public WeatherStation addReport(WeatherReport report){
    //if the number of reports is already 10 then push each report back by one index, to get rid of the 
    //oldest one and place the new report at index 9
    if (numReports==10){
      for (int i=1; i<10; i++){
        reports[i-1] = reports[i];
      }
      reports[9] = report;
    }
    //if the station has less than 10 reports, set the value of the appropriate index of the reports array to the input report
    else{
      reports[numReports] = report;
      numReports++;
    }
    //variable that stores the temperature of the current report
    double newHigh = report.temperature.setScale(Scale.KELVIN).getTemp();
    //set the value of the highest variable to the report if that report is the first one
    if (highest==null){
      highest = report;
    }
    //otherwise compare the temperature of the new report to the previous highest value
    //if the temperaute of the new report is higher than the previous highest temperature then
    //set the value of highest to the new report.
    else{
      double oldHigh = highest.temperature.setScale(Scale.KELVIN).getTemp();
			if (newHigh >= oldHigh){
        highest = report;
      } 
     }
     //set the last index of the array to the highest report
     //this is useful for WeatherDatabase class to get the max temperature
     reports[10] = highest;
    return this;  // do NOT alter the return statement
  }


    /** Returns the most recently added report.
      *
      *
      * @return the most recently added WeatherReport to this weatherstation.
      *         If no reports have ever been added then returns null.
      */
    public WeatherReport getMostRecentReport(){
      if (numReports!=0){
        return reports[numReports-1];
      }
      return null;
    }


  /** Returns the last 10 weather getReports added to this weather station.
	 * <p>
	 * If there haven't been 10 reports added then return as many as has been added.
   *
   * @return an array of the last 10 added WeatherReports (in the order
   *         that they were added). The most recently added report is the
   *         first element in the array.
   *         If there has been less than 10 reports added to this
   *         weatherstation, then returns as many reports as there is.
   *         The returned array must be the same size as the number of
   *         reports returned.
   */
  public WeatherReport[] getReports(){
    WeatherReport[] gReports = new WeatherReport[numReports];
    for (int i=0; i<numReports; i++){
      gReports[i] = reports[numReports-1-i];
    }
    return gReports;
  }


  /** Returns a weather report with highest recorded temperature
   * of any report ever added to this WeatherStation.  If there have been
   * multiple reports with the same highest temperature, the most recently
   * added report is returned,.
   *
   * @return a WeatherReport that has the highest recorded temperature
   *         of any report ever added to this WeatherStation. Returns
   *         null if no report has ever been added to this weatherstation.
   */
  public WeatherReport getReportWithMaxTemp(){
    return highest;
  }
}
