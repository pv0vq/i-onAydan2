package com.example.smple1231.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> { //제네릭 타입, 통신이성공 200인지 통신 실패 400 인지 사용하기위해
	int status;
	T data;

}
