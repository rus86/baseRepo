package com.ruscorporation.controller;

import java.util.Locale;

import org.joda.time.DateTime;

public class JodaTimeTest {

	public static void main(String[] args) {
		System.out.println("Start Joda Time");
		DateTime dateTime = new DateTime();
		DateTime.Property dayOfYear = dateTime.dayOfWeek();
		
		System.out.println("Day of month=" + dayOfYear.getAsText(Locale.FRENCH));

	}

}
