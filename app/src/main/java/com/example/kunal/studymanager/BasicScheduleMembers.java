package com.example.kunal.studymanager;

/**
 * Created by kunal on 23/10/16.
 */
public class BasicScheduleMembers {


    private final long TotalDays;
    private String stdate,eddate;
    private final int totalSubject,NoOfSubPerDays;



    public BasicScheduleMembers(String stdate,String eddate,long TotalDays,int totalSubject,int NoOfSubPerDays) {
        this.stdate = stdate;
        this.eddate = eddate;
        this.TotalDays=TotalDays;
        this.totalSubject = totalSubject;
        this.NoOfSubPerDays = NoOfSubPerDays;

    }

    public String getstdate()       {  return this.stdate;          }

    public String geteddate()       {  return this.eddate;          }

    public long getTotalDays()    {  return  this.TotalDays;   }

    public int gettotalSubject()    {  return  this.totalSubject;   }

    public int getNoOfSubPerDays()  {  return  this.NoOfSubPerDays; }

}
