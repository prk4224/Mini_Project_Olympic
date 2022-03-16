package com.pcwk.library.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.pcwk.cmn.DTO;
import com.pcwk.cmn.LoggerManager;
import com.pcwk.cmn.WorkDiv;
import com.pcwk.library.domain.Olympic;

public class OlympicDao implements WorkDiv<Olympic>, LoggerManager {

	// 저장 파일에서 읽어 들인 Data : CRUD, 마지막에 파일 기록
	public static List<Olympic> olympicList = new ArrayList<>();

	// 저장 파일 결로
	public final static String SAVE_FILE_PAHE = "C:\\DCOM_220127\\01_JAVA\\workspace\\Olympic_Mag\\src\\com\\pcwk\\cmn\\olympic";

	public OlympicDao() {
		int readStatus = readFile(SAVE_FILE_PAHE);

//		if (0 == readStatus) {
//			LOG.debug("파일 읽기 실패");
//		} else {
//			LOG.debug("파일 읽기 성공");
//		}
		System.out.println();
	}
	
	

	
	public int saveFile(String filePath) {
		
		
		int writeCut = 0;

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

			for (Olympic olympic : olympicList) {
				String dal = ",";

				
				String outData = olympic.getName() + dal + olympic.getAge()+ dal + olympic.getGender() + dal + olympic.getMainev() + '\n';
				bw.write(outData);

				writeCut++;

			}

		} catch (IOException e) {
			LOG.debug("=========================");
			LOG.debug("=IOException=" + e.getMessage());
			LOG.debug("=========================");
		}

		return writeCut;
	}
	
	/**
	 * books.csv를 읽어서 bookList에 할당
	 * 
	 * @param filePath
	 * @return 0(실패)/1(성공)
	 */
	public int readFile(String filePath) {
		int flag = 0;


		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String line = "";

			while ((line = br.readLine()) != null) {

				String[] inArray = line.split(",");

				int age = Integer.parseInt(inArray[1]);
				
				Olympic olympic = new Olympic(inArray[0], age, inArray[2], inArray[3]);
				olympicList.add(olympic);
			}
			
			if (olympicList.size() > 0) {
				flag++;
			}

		} catch (IOException e) {
			LOG.debug("======================");
			LOG.debug("=IOException=" + e.getMessage());
			LOG.debug("======================");
		}

		return flag;
	}


	@Override
	public List<Olympic> doRetrieve(DTO dto) {
		
		return olympicList;
	}
	
	public int isOlympicExists(String dto) {
		int flag = 0;

		for (Olympic olympic : olympicList) {
			if (olympic.getName().equals(dto)) {
				flag = 1;
				break;
			}
		}

		return flag;
	}


	@Override
	public int doSearch (String dto) {
		
		int flag = 0;
		
	
		String clientId = "2pOgNlU72LFw_uyOyj63"; // 클라이언트 아이디
		String ClientSecret = "yNKgp1resk";
		
		if(dto == null) {
			System.exit(0);
		}
		String text = dto; // 검색어
		BufferedReader br = null;
		
		try {
			text = URLEncoder.encode(text, "UTF-8");
			
			
			String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + text;
			
			
			URL url = new URL(apiURL); // URL
			
			// clientId, CliemntSecret 적용을 위한 HttpURLConnection
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET"); // GET방식으로 접근
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", ClientSecret);
			
			int responseCode = con.getResponseCode();
			//System.out.println("responseCode: " + responseCode);
			
			if(200 == responseCode) { // 성공
				
				br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
				// byte 스트림을 char 스트림으로 연결 -> BufferedReader 생성
				
				
				//가지고 온 데이터 출력
				String line = "";
				while((line = br.readLine()) != null) {
					System.out.println(line);
				}
				
				flag =1;
				
			}else {
				System.out.println("클라이언트 아이디, 클라이언트 시크릿을 확인하세요.");
			}
			
		} catch (IOException e) {
			System.out.println("======================");
			System.out.println("=IOException=" + e.getMessage());
			System.out.println("======================");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return flag;
	}
}



