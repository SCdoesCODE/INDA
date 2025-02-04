/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(12);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(12);
        //this means that you send 12 to rolloverlimit
        minutes = new NumberDisplay(60);
        //this means that you send 60 to rolloverlimit
        setTime(hour, minute);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.incrementMin();
        System.out.println("timetick minutes before if" + minutes);
        if(minutes.getValue() == 0) {  // it just rolled over!
            System.out.println("min in if" + minutes);
            hours.incrementHour();
            System.out.println("hours in if: " + hours);
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        System.out.println(hour + " : " + minute);
        
        if (minute > 59)
        {   
            //minute = 60k + a
            hour = hour + minute/60;
            minute = minute%60;
            
        }
        
        System.out.println(hour + " : " + minute);
        
        if (hour > 12)
        {
            hour = hour%12;
            if(hour == 0)
            {
                hour = 12;
            }
        }
        
        System.out.println(hour + " : " + minute);
        
        hours.setValue(hour);
        minutes.setValue(minute);
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
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue();
                        
        System.out.println("UPDATEDISPLAY : " + hours) ;
        System.out.println("UPDATEDISPLAY : " + minutes);
    }
    
    
}
