package com.df.converter;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Loser
 * @date 2021年11月10日 11:06
 */
//添加日期转换器
@Component
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String str) {
        String[] patterns = {"yyyy-MM-dd","yyyy-MM-dd hh:mm:ss","yyyy/MM/dd","yyyy:MM:dd:HH:ss",
                "MM-dd-yyyy","dd-MM-yyyy"};
        try {
            Date date = DateUtils.parseDate(str, patterns);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
