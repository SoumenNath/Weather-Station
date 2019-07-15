package comp1406a2;

/**
 * A class to represent temperature (with a value and scale).
 *
 * COMP 1406
 * Winter 2019
 * Assignment 2
 */

public class Temperature{
	//variable that stores the curretn temperarute of the instance
	private double temp = 0;
	//variable that stores the curretn temperarute of the instance
	Scale currentScale;
	/** Initializes a temperature object with given value in Celsius
	 *  <p>
	 *  If the initial temperature is less than -273.15C then the temperature
	 *  object will be initialized with -273.15C.
   *
	 * @param temp is the initial temperature in Celsius.
	 */
  public Temperature(double temp){
	  //If the temperature is less than the absolute minimum value then make it equal to the absolute minimum value
	  //otherwise set the current temperartue to the input parameter
	  if (temp<-273.15){
			this.temp = -273.15;
		}
		else{
			this.temp = temp;
		}
		//set the current scale to celcius
		currentScale = Scale.CELSIUS;
  }


	/** Initializes a temperature object with given value using the specified scale
   * <p>
	 * If the temperature is lower than absolute zero, in the given scale,
	 * then set the temperature to absolute zero (in that scale).
   * <p>
 	 * Usage: new Temperature(12.3, Scale.KELVIN)
	 *
	 * @param temp is the initial temperature in the scale provided in
   *        the second argument.
	 * @param scale is the scale of initial temperature and must be a constant
	 *        defined in the Scale enum type.
	 */
  public Temperature(double temp, Scale scale){
		//set the current scale to the input parameter
		//if the temperature is less than the absolute minimum value for the scale then make it equal to the absolute minimum value
	  //otherwise set the current temperartue to the input parameter
		currentScale = scale;
		switch(currentScale){
			case CELSIUS:
				if (temp<-273.15){
					temp = -273.15;
				}
				break;
			case FAHRENHEIT:
				if (temp<-459.67){
					temp = -459.67;
				}
				break;
			case KELVIN:
				if (temp<0){
					temp = 0;
				}
				break;
		}
		this.temp = temp;

  }

	/** Initializes a new temperature object that is a copy of the
   *  temperature object parameter.
   *
   * That is, it makes a new object that is a copy of the input object.
   *
	 * @param temp is a non-null temperature object
	 */
	Temperature copyTemp;
  public Temperature(Temperature temp){
	  //use this() to call on another constructor to make a new object that is a copy of this input object
		this(temp.temp, temp.currentScale);
  }



	/** getter for the scale
	 * <p>
	 * The output of this getter method must always be the first letter of one
	 * of the names in the Scales enum class. It must be the upper case letter.
	 * <p>
	 * Example: t = new Temperature(12.3, Scale.KELVIN);
	 *          t.getScale() will then output 'K'
	 *
	 * @return the first letter (in upper case) of the string representation of the
	 *         current scale of this object.
	 */
  public char getScale(){
	  //a variable that holds the letter representation of the scale
		char Letter ='C';
		//set the char value of the variable to the approproate value given the cuurent scale
		switch(currentScale){
			case CELSIUS:
				Letter = 'C';
				break;
			case FAHRENHEIT:
				Letter = 'F';
				break;
			case KELVIN:
				Letter = 'K';
				break;
		}
    return Letter;
  }

	/** getter for the temperature
	 *
	 * @return the temperature of the object using the current scale
	 */
  public double getTemp(){
    return this.temp;
  }


  /** setter for scale
	 *
	 * @param scale is the new scale of the temperature and must be
	 *        a constant from the Scale enum type. The next time you
	 *        call getTemp(), the temperature will be output in this scale.
   * @return a reference to this object.
	 */
  public Temperature setScale(Scale scale){
		  //set the value of the current scale to the input scale if the two values are different
		  //perform the correct conversion of temperature if the two scales are different
		if (currentScale != scale){
			if (currentScale == Scale.CELSIUS && scale  == Scale.FAHRENHEIT){
				this.temp = (this.temp * 1.8) + 32;
			}
			else if (currentScale == Scale.CELSIUS && scale  == Scale.KELVIN){
				this.temp = this.temp + 273.15;
			}
			else if (currentScale == Scale.FAHRENHEIT && scale  == Scale.CELSIUS){
				this.temp = (this.temp - 32) * (5.0/9.0);
			}
			else if (currentScale == Scale.FAHRENHEIT && scale  == Scale.KELVIN){
				this.temp = (this.temp + 459.67) * (5.0/9.0);
			}
			else if (currentScale == Scale.KELVIN && scale  == Scale.CELSIUS){
				this.temp = this.temp - 273.15;
			}
			else if (currentScale == Scale.KELVIN  && scale  == Scale.FAHRENHEIT){
				this.temp = (this.temp * 1.8) - 459.67;
			}
			currentScale = scale;
		}
    return this;  // do NOT change this return statement.
	}


	/** setter for temperature
	 *
	 * @param temp is the new temperature (in the object's current scale)
   * @return a reference to this object.
	 */
  public Temperature setTemp(double temp){
		this.temp = temp;
    return this;  // do NOT change this return statement.
	}

	/** setter for temperature
	 *
	 * @param temp is the new temperature
	 * @param scale is the scale of the new temperature. It must be
	 *        a constant from the Scale enum type.
   * @return a reference to this object.
   */
  public Temperature setTemp(double temp, Scale scale){
	  //set the value of the current scale to the input scale
		currentScale = scale; 
		//if the temperature is less than the absolute minimum value for the scale then make it equal to the absolute minimum value
	  //otherwise set the current temperartue to the input parameter
		switch(currentScale){
			case CELSIUS:
				if (temp<-273.15){
					this.temp = -273.15;
				}
				else{
					this.temp = temp;
				}
				break;
			case FAHRENHEIT:
				if (temp<-459.67){
					this.temp = -459.67;
				}
				else{
					this.temp = temp;
				}
				break;
			case KELVIN:
				if (temp<0){
					this.temp = 0;
				}
				else{
					this.temp = temp;
				}
				break;
		}
    return this;  // do NOT change this return statement.
	}

	/** setter for temperature
	 *
	 * @param temp is the new temperature.
	 * @param scale is a string representing one of the three scales.
   * @return a reference to this object.
   */
  public Temperature setTemp(double temp, String scale){
		//change the case of the input string to lowercase
		scale = scale.toLowerCase();
		//determine the new value of the current scale by using the first character of the input string
		//set the current scale to appropirate scale
		//if the temperature is less than the absolute minimum value for the scale then make it equal to the absolute minimum value
	  //otherwise set the current temperartue to the input parameter
		if (scale.charAt(0)=='c'){
			currentScale = Scale.CELSIUS;
				if (temp<-273.15){
					this.temp = -273.15;
				}
				else{
					this.temp = temp;
				}
		}
		else if(scale.charAt(0)== 'f'){
			currentScale = Scale.FAHRENHEIT;
				if (temp<-459.67){
					this.temp = -459.67;
				}
				else{
					this.temp = temp;
				}
		}
		else if(scale.charAt(0)== 'k'){
			currentScale = Scale.KELVIN;
			if (temp<0){
				this.temp = 0;
			}
			else{
				this.temp = temp;
			}
		}
		return this;  // do NOT change this return statement.
  }

	/* ------------------------------------------------- */
	/* ------------------------------------------------- */
  /* do not change anything below this                 */
  /* ------------------------------------------------- */
	/* ------------------------------------------------- */

  /** String representation of a temperature object    */
	@Override
  public String toString(){
    return "" + this.getTemp() + this.getScale();
  }

}
