
/**
 * The ClockDisplay class implements a digital clock display for a
 * US-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 12:00 AM (midnight) to 11:59 AM (one minute before noon)
 * and 12:00 PM (noon) to 11:59 PM(one minute before midnight).
 * 
 * The numberDisplay stores values from 0-11. when the Hours number display is zero, the
 * clock Display should show 12 but not change the internal value of the hours numberDisplay.
 * 
 * It is the job of the Clock display to keep track of AM to PM and PM to AM transitions.
 * When the clock is initialized with the zero parameter constructor
 * the time shoudl read as 12:00 AM
 * 
 * When you use the other constructor, it needs to include a parameter for 
 * the meridian to indicate if the time is AM or PM
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes modified by Brennen Lui
 * @version 2026.02.25
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    private String meridian;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        meridian = "AM";
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute, String meridian)
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        setTime(hour, minute, meridian);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            if (hours.getValue() == 11){
                if (meridian.equals("AM")){
                    meridian = "PM";
                }
                else{
                    meridian = "AM";
                }
            }
            hours.increment();
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute, String meridian)
    {
        hours.setValue(hour % 12);
        minutes.setValue(minute);
        this.meridian = meridian;
        updateDisplay(); 
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay()
    {
        int displayHour = hours.getValue();
        if (displayHour == 0){
            displayHour = 12;    
        }
        
        String hourString;
        if (displayHour < 10){
            hourString = "0" + displayHour;
        }
        else{
            hourString = "" + displayHour;
        }
        displayString = hourString + ":" + 
                        minutes.getDisplayValue() + " " + meridian;
    }
}
