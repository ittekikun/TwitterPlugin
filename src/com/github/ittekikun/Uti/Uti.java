package com.github.ittekikun.Uti;

import java.util.Calendar;
import java.util.Date;

public class Uti
{
	public static String ArrayUnion(String[] par1, int par2)
    {
        StringBuilder sb = new StringBuilder();

        for (int a = par2; a < par1.length; ++a)
        {
            if (a > par2)
            {
            	sb.append(" ");
            }
            
            String s = par1[a];

            sb.append(s);
        }
        return sb.toString();
    }
	
	public static Date TimeGet()
	{
		Calendar calendar = Calendar.getInstance(); 
		Date Time = calendar.getTime();
		
        return Time;
    }
}
