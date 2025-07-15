package com.climbjava.demo.domain;

//import controller.attach.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("attach")
public class Attach {
	private String uuid; //unjversal unique id
	private String path;
	private boolean image; 
	private String origin;
	private Long bno;
	private Long rno;
	private int odr;
	private long size;
	
	public Attach(String uuid, String path, boolean image, String origin, Long bno, int odr, long size) {
		super();
		this.uuid = uuid;
		this.path = path;
		this.image = image;
		this.origin = origin;
		this.bno = bno;
		this.odr = odr;
		this.size = size;
	}
	
//	public File toFile() {
//
//		return new File(UploadFile.UPLOAD_PATH + "/" + path, uuid);
//	}
	public Attach toThumb() {
		return Attach.builder().bno(bno).image(image).uuid("t_" + uuid).path(path).origin(origin).odr(odr).size(size).build();
	}
	
}
