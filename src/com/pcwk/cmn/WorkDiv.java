package com.pcwk.cmn;

import java.util.List;


public interface WorkDiv<T> {
	
	
	/**
	 * 참가 선수들 명단을 조회
	 * @param dto
	 * @return
	 */
	public List<T> doRetrieve(DTO dto);
	
//	/**
//	 * 특정 선수의 참가 종목을 조회
//	 * @param dto
//	 * @return 1(성공)/0(실패)
//	 */
//	public int doParti(T dto);
//	
//	/**
//	 * 특정 선수의 메달을 조회
//	 * @param dto
//	 * @return 1(성공)/0(실패)
//	 */
//	public int doMedal(T dto);
//	
	
	/**
	 * 특정 선수의 이슈를 검색
	 * @param dto
	 * @return 1(성공)/0(실패)
	 */
	public int doSearch(String search);
	

}
