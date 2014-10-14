package edu.chalmers.pickuapp.app.model;


/**
 * Date-class used to represent time
 */
public class Date {

	//It is generally considered stupid by many devs to have private access level for basic data classes.
	public int year;
	public int month;
	public int day;
	public int hour;
	public int minute;
	public int second;

    public Date(){

    }

	public Date(int year, int month, int day, int hour, int minute, int second) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public Date(Date date) {
		year = date.year;
		month = date.month;
		day = date.day;
		hour = date.hour;
		minute = date.minute;
		second = date.second;
	}

    @Override
    public boolean equals(Object rhs){
        if(rhs == null){
            return false;
        } else if (rhs == this){
            return true;
        } else if(!this.getClass().equals(rhs.getClass())){
            return false;
        }
        Date tmp = (Date)rhs;

        return year == tmp.year && month == tmp.month && day == tmp.day && hour == tmp.hour && minute == tmp.minute &&
                second == tmp.second;
    }
}