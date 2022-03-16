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
import com.pcwk.cmn.WorkDiv02;
import com.pcwk.library.domain.Olympic;
import com.pcwk.library.domain.Olympic_P;

public class OlympicDao02 implements WorkDiv02<Olympic_P>, LoggerManager {

	public static List<Olympic_P> olympicList = new ArrayList<>();

	// 저장 파일 결로
	public final static String SAVE_FILE_PAHE_P = "C:\\DCOM_220127\\01_JAVA\\workspace\\Olympic_Mag\\src\\com\\pcwk\\cmn\\olympic_parti";

	public OlympicDao02() {
		int readStatus = readFile(SAVE_FILE_PAHE_P);

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

			for (Olympic_P olympic : olympicList) {
				String dal = ",";

				String outData = olympic.getName() + dal + olympic.getParti() + '\n';
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

	public int readFile(String filePath) {
		int flag = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

			String line = "";

			while ((line = br.readLine()) != null) {

				String[] inArray = line.split(",");

				Olympic_P olympic = new Olympic_P(inArray[0], inArray[1]);
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
	
	public int isOlympicExists(String dto) {
		int flag = 0;

		for (Olympic_P olympic : olympicList) {
			if (olympic.getName().equals(dto)) {
				flag = 1;
				break;
			}
		}

		return flag;
	}
	
	
	@Override
	public Olympic_P doParti(Olympic_P dto) {
		Olympic_P outData = null;

		for (Olympic_P olympic : olympicList) {
			if (olympic.getName().equals(dto.getName())) {
				outData = olympic;
				break;
			}
		}
		return outData;
	}

	
}
