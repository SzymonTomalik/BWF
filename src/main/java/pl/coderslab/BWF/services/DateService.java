package pl.coderslab.BWF.services;import org.joda.time.DateTime;import org.joda.time.format.DateTimeFormat;import org.joda.time.format.DateTimeFormatter;import org.springframework.stereotype.Service;@Servicepublic class DateService {    public String getDateNow() {        DateTime d=new DateTime();        DateTimeFormatter fmt= DateTimeFormat.forPattern("yyyy-MM-dd, HH:mm");        return d.toString(fmt);    }}