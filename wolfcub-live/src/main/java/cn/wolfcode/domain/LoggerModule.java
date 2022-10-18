package cn.wolfcode.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoggerModule {
	private Long id;
	private String level;
	private String msg;
	private Date time;
	private Long userId;
	public static LoggerModule of(String level,String msg,Long userId){
		return new LoggerModule(null,level,msg,new Date(),userId);
	}
	public static LoggerModule of(String level,String msg){
		return of(level, msg,null);
	}
}
