package com.climbjava.demo.util;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

@Slf4j
public class S3Util {
	
	private static final Properties props = PropsLoaderUtil.getProperties("secret/aws_s3_properties");
	
	private static final S3Client s3 = S3Client.builder()
			.region(Region.of(props.getProperty("region-name")))
			.credentialsProvider(
					StaticCredentialsProvider.create(
							AwsBasicCredentials.create(
									props.getProperty("access-key"), 
									props.getProperty("secret-key"))
							)
					
					)
					.build();
	
	public static void main(String[] args) throws InvalidRequestException, InvalidWriteOffsetException, TooManyPartsException, EncryptionTypeMismatchException, S3Exception, AwsServiceException, SdkClientException, FileNotFoundException {
		System.out.println(s3);
		
		PutObjectRequest por = PutObjectRequest.builder()
				.bucket(props.getProperty("bucket-name"))
				.key("test.jpg") //AWS 상의 위치
				.contentType("image/jpg")
				.build();
		File file = new File("C:\\Users\\tj\\workspaces_kch\\학습자료\\image 업로드 테스트 샘플\\새똥이.jpg"); 
		s3.putObject(por, RequestBody.fromInputStream(new FileInputStream(file), file.length())); // 파일에 보여질 이름
		
	}
	
	
	public static void upload(Part part, String key) {
		try {
			uploadInternal(part.getInputStream(), key, part.getSize(), part.getContentType());
		} catch(IOException e) {
			throw new RuntimeException("S3 업로드 중 오류", e);
		}
		
	}
	
	public static void upload(File file, String key) {
		try {
			uploadInternal(new FileInputStream(file), key, file.length(), Files.probeContentType(file.toPath()));
		} catch(IOException e) {
			throw new RuntimeException("S3 업로드 중 오류", e);
		}
		
	}
	
	public static void uploadInternal(InputStream is, String key, long size, String contentType) {
		log.info("{} {} {}", key, size, contentType);
		PutObjectRequest putReq = PutObjectRequest.builder()
				.bucket(props.getProperty("bucket-name"))
				.key(key) //AWS 상의 위치
				.contentType(contentType != null ? contentType : "application/octet-stream")
				.build();
		
		try(is){
			s3.putObject(putReq, RequestBody.fromInputStream(is, size));
		} catch(IOException e) {
			throw new RuntimeException("S3 업로드 중 오류", e);
		}
		
	}



	
}
