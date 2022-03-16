package com.pcwk.library.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

import com.pcwk.library.dao.OlympicDao;
import com.pcwk.library.dao.OlympicDao02;
import com.pcwk.library.domain.Olympic;
import com.pcwk.library.domain.Olympic_P;

public class OlympicMain {

	OlympicDao dao;
	OlympicDao02 dao02;

	public OlympicMain() {
		dao = new OlympicDao();
		dao02 = new OlympicDao02();
	}

	public void saveFile() {
		int writeCut = dao.saveFile("olympicTmp.csv");
		int writeCut02 = dao02.saveFile("olympic_pTmp.csv");
		System.out.println("writeCut: " + writeCut);
	}

	public void readFile() {
		for (Olympic b : dao.olympicList) {
			System.out.println(b);
		}
		for (Olympic_P d : dao02.olympicList) {
			System.out.println(d);
		}
	}

	public Olympic getInputData(Scanner sc) {
		Olympic olympic = null;

		String readInput = sc.nextLine().trim();
		String[] dataArr = readInput.split(",");

		int age = Integer.parseInt(dataArr[1]);

		olympic = new Olympic(dataArr[0], age, dataArr[2], dataArr[3]);

		return olympic;
	}

	public static void main(String[] args) {

		OlympicMain olympicMain = new OlympicMain();

		File olympics = new File(OlympicDao.SAVE_FILE_PAHE);
		File olympics_p = new File(OlympicDao02.SAVE_FILE_PAHE_P);

		if (olympics.exists() || olympics.isFile()) { 
			try {
				olympics.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (olympics_p.exists() || olympics_p.isFile()) { 
			try {
				olympics_p.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Olympic olympic = null;
		System.out.println("          <베이징 올림픽 대한민국 참가 명단>     ");
		System.out.println("   ----------------------------------------");
		System.out.println("   | 이 름\t나이\t성별\t참가종목\t");
		System.out.println("   ----------------------------------------");
		List<Olympic> list = olympicMain.dao.doRetrieve(olympic);

		for (Olympic o : list) {
			System.out.println("   | " + o.getName() + "\t" + o.getAge() + "\t" + o.getGender() + "\t" + o.getMainev());
			System.out.println("   ----------------------------------------");
		}
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		String inCommand = "";
		do {
			Olympic_P olympic_p = null;
			String readInput = "";
			
			System.out.print("\n   1. 출전 종목 및 순위 \n   2. 검색 \n   3. 프로그램 종료\n    → ");
			
			inCommand = sc.nextLine();
			inCommand = inCommand.trim();

			switch (inCommand.toUpperCase()) {

			case "1":

				System.out.print("   선수의 이름을 입력하세요 : ");
				readInput = sc.nextLine().trim();
				olympic_p = new Olympic_P();
				olympic_p.setName(readInput);

				if (olympicMain.dao02.isOlympicExists(readInput) != 1) {
					System.out.println("\n   <존재하지 않는 선수입니다.>\n");
					continue;

				}

				System.out.println("\n   ------------------------------------");
				System.out.println("   | 이 름\t출전종목\t");
				System.out.println("   ------------------------------------");

				Olympic_P outDate = olympicMain.dao02.doParti(olympic_p);

				if (null == outDate) {
					System.out.println("   <조회실패>");
				} else {
					System.out.println(outDate);
					System.out.println();
				}

				break;

			case "2":
				System.out.print("   선수의 이름을 입력하세요 : ");
				String olympic_name = sc.nextLine();

				if (olympicMain.dao.isOlympicExists(olympic_name) != 1) {
					System.out.println("\n   <존재하지 않는 선수입니다>\n");
					continue;

				}

				int sStatus = olympicMain.dao.doSearch(olympic_name);
				if (sStatus == 1) {
					System.out.println("   <검색 성공>\n");
				} else {
					System.out.println("   <검색 실패>\n");
				}
				break;

			}

		} while (!inCommand.equalsIgnoreCase("3"));
		System.out.println("  ============================");
		System.out.println("  =========프로그램 종료==========");
		System.out.println("  ============================");

	}

}
