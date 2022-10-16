package cn.wolfcode.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

@Getter
@Setter
public class LoggingModel {
	private Integer id;
	private String level;
	private String msg;
	
	@Override
	public String toString() {
		return new StringJoiner(", ", LoggingModel.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("level='" + level + "'")
				.add("msg='" + msg + "'")
				.toString();
	}
}
