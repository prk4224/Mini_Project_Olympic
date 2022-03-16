package com.pcwk.cmn;

import java.util.List;

import com.pcwk.library.domain.Olympic_P;


public interface WorkDiv02<T> {
	
	
	/**
	 * 참가 선수들 명단을 조회
	 * @param dto
	 * @return
	 */

	public T doParti(T dto);
	

}
