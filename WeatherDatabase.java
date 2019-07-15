package comp1406a2;

/** A very simple database storing weather data */ 
public class WeatherDatabase{
	//variable that keeps track of the number of stations in the database
	int size = 0;
	//the initial size of the array that keeps track of the stations
	int initialSize = 2;
	//array that keeps track of the stations
	WeatherStation[] stations = new WeatherStation[initialSize];
	
	/* ----------------------------------- */
	/* do NOT change this constructor      */
	/* ----------------------------------- */
	
	/** Creates an empty weather database  */
	public WeatherDatabase(){}
	
	/* ----------------------------------- */




	//
	// You need to complete these methods.
	//
	// Unless stated, you will need to change the return 
	// value of each of these methods as well as add the actual 
	// body of the methods.
	//



	/** Returns all weather stations in the database
	* in no particular order
	*
	* @return an array containing all weather stations that are in
	*         this database. Note that the size of the array should be equal to
	*         the result of <code>this.numberOfWeatherStations()</code>.
	*/
	public WeatherStation[] getWeatherStations(){
		WeatherStation[] currentStations = new WeatherStation[size];
		for (int i=0; i<size; i++){
			currentStations[i] = stations[i];
		}
		return currentStations;
	}

	/** Returns the number of weather stations in the database
	*
	* @return the number of weather stations in this database
	*/
	public int numberOfWeatherStations(){
		return size;
	}
	

	/** adds a weather station to this database
		* @param station is the weather station to add. It will always be non-null.
		* @return this weather station.
		*/
	public WeatherDatabase addWeatherStation(WeatherStation station){
		//create a new array everytime the the current array is at max capacity
		if (size >= initialSize){
			//the new size of the array is twice that of the current size
			initialSize *= 2; 
			//use a temporary array to store the value of the current array and place these values
			//in the new array
			WeatherStation[] tempArr = stations;
			stations = new WeatherStation[initialSize];
			for (int i=0; i<tempArr.length; i++){
				stations[i] = tempArr[i];
			}
		}
		//set the input station to the value at the appropriate index in the stations array
		stations[size] = station;
		//increase the size
		size++;
		return this;    // do NOT change the return statement
	}


	/** removes weather station with given id from this database
		* @param id is the ID number of the weather station to remove.
		* @return true if the specified weather station is successfully removed,
		*         returns false otherwise (i.e., if there was no weather station
		*         with the specified ID number in this database to begin with).
		*/
	public boolean removeWeatherStation(int id){
		//if the input id matches an id of a station in the array and that station is not at the last postion of the array
		//then remove that station and place the station that is at the last index of the array to this spot
		//make the last index of the array null
		for (int i=0; i<size; i++){
			if (stations[i].id == id && i != size-1){
				stations[i] = stations[size-1];
				stations[size-1] = null;
				size--;
				return true;
			}
		//if the input id matches an id of a station in the array and that station is  at the last postion of the array
		//then remove that station make the last index of the array null	
			else if(stations[i].id == id && i == size-1){
				stations[i] = null;
				size--;
				return true;
			}
		}
		return false;
	}


	/** returns the highest temperature ever recorded by any weather station
	  * in the data base.
	  */
	public Temperature getMaxTemperature(){
		//variable thaat stores the temperarute object with the current highest temperature
		Temperature mTemp = stations[0].highest.temperature;
		for (int i=1; i<size; i++){
			//check if the highest temperarutre recorded in each station is higher than the current highest
			if (stations[i].highest.temperature.setScale(Scale.KELVIN).getTemp()>mTemp.setScale(Scale.KELVIN).getTemp()){
				mTemp = stations[i].highest.temperature;
			}
		}
		return mTemp;
	}


	/** returns the highest temperature on a specified day
		* that is recorded in a report in the weather stations in this database.
		*
		* @param day is the day to find the max temperature on. Note that this
		*            might be the special max temperature day that a
		*            weather station records.
		* @return  the temperature object with the highest recorded Temperature
		*          in the data base on the specified day.
		*/

	public Temperature getMaxTemperature(int day){
		//variable thaat stores the temperarute object with the current highest temperature
		Temperature mTemp = stations[0].highest.temperature;
		for (int i=0; i<size; i++){
			//the boundary determines how many reports to check for each station
			//this is importatn as some stations don't hold 11 reports (with the 11th being the object with the highest ever temperature)
			int boundary = 0;
			if (stations[i].numReports == 10){
				boundary = 11;
			}
			else{
				boundary = stations[i].numReports;
			}
			for (int j=0; j<boundary; j++){
				//if the report was made on the same day then compare the temperature of that report with the current highest temperature
				//reassign the mTemp when it encounters a new highest temperature
				if (stations[i].reports[j].time.day==day){
					if (stations[i].reports[j].temperature.setScale(Scale.KELVIN).getTemp()>stations[0].highest.temperature.setScale(Scale.KELVIN).getTemp()){
						mTemp = stations[i].reports[j].temperature;
					}
				}
			}
		}
		return mTemp;
	}

	/** Computes the average temperature (over all weather stations) for the
	*  time period starting at startDay and ending at endDay (inclusive)
	*
	* @param startDay is the starting day
	* @param endDay is the ending endDay
	* @return the average temperature of all temperature reports for the time
	*         period startDay to endDay (inclusive) taken from all weather
	*         stations in this weather database.
	*/

	public double averageTemperature(int startDay, int endDay){
		//variable that keeps increasing by taking in the temperature values
		double average = 0.0;
		//variable that keeps track of the number of reports
		double numR = 0.0;
		for (int i=0; i<size; i++){
			//the boundary determines how many reports to check for each station
			int boundary = stations[i].numReports;
			//if the report was made on the same daywithin the start day and end day then add it to the average value
			for (int j=0; j<boundary; j++){
				if (stations[i].reports[j].time.day>=startDay && stations[i].reports[j].time.day<=endDay){
					average += stations[i].reports[j].temperature.setScale(Scale.CELSIUS).getTemp();
					numR++;
				}
			}
		}
		//get the average by diving the total by the number of reports
		average /= numR;
		return average;
	}
}
