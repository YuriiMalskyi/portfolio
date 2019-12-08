package com.malskyi.project.domain.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ErrorMessage {

	private String message;
	
	@JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	private LocalDateTime time;
	
	public ErrorMessage(String message) {
		this.message = message;
		this.time = LocalDateTime.now();
	}
	
}
