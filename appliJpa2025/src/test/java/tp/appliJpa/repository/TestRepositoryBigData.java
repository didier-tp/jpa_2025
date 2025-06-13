package tp.appliJpa.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tp.appliJpa.entity.BigData;


@SpringBootTest   //à lancer avec Run as ... / JUnit Test
class TestRepositoryBigData {
	
	@Autowired //equivalent de @Inject	
	private RepositoryBigData repositoryBigData;
	
	public static String readFileAsString(String path) throws IOException {
	    byte[] encoded = Files.readAllBytes(Paths.get(path));
	    return new String(encoded, Charset.defaultCharset());
	}
	
	public static void writeStringInFile(String path,String data) throws IOException {
		Files.write(Paths.get(path), data.getBytes(Charset.defaultCharset()));
	}

	@Test
	void testInsertionEtRelecture() {
		try {
			String imageFilePath ="clementine.jpeg";//to read from filesystem and store in database
			String imageFilePath2 ="clementine2.jpeg";//to read from database and write in filesystem
			String jsonFilePath ="product.json";//to read from filesystem and store in database
			String jsonFilePath2 ="product2.json";//to read from database and write in filesystem
			File sourceImageFile = new File(imageFilePath);
			FileInputStream fis = new FileInputStream (sourceImageFile);
			byte[] sourceImage = fis.readAllBytes();
			int sourceImageSize = sourceImage.length;
			String jsonProductString = readFileAsString(jsonFilePath);
			BigData bigData = new BigData(null,sourceImage,jsonProductString);
			repositoryBigData.insertNew(bigData);
			fis.close();
			
			BigData bigDataRelu = repositoryBigData.findById(bigData.getId());
			byte[] destImage = bigDataRelu.getImage();
			int destImageSize = destImage.length;
			String jsonProduct2String = bigDataRelu.getJsonData();
			Assertions.assertEquals(destImageSize,sourceImageSize);
			Assertions.assertEquals(jsonProduct2String,jsonProductString);
			File destImageFile = new File(imageFilePath2);
			FileOutputStream fos = new FileOutputStream (destImageFile);
			fos.write(bigDataRelu.getImage(), 0, bigDataRelu.getImage().length);
			fos.close();
			writeStringInFile(jsonFilePath2,jsonProduct2String);
			//Refresh eclipse pour visualiser les fichiers clementine2.jpeg et product2.json générés
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
