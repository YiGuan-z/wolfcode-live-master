package cn.wolfcode.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.StringJoiner;

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
	public static LoggerModule of(String level,StringBuilder msg){
		return of(level, msg.toString(),null);
	}
	
	@Override
	public String toString() {
		return new StringJoiner(", ", LoggerModule.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("level='" + level + "'")
				.add("msg='" + msg + "'")
				.add("time=" + time)
				.add("userId=" + userId)
				.toString();
	}
}
