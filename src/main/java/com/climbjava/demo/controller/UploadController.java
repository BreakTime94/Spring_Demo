package com.climbjava.demo.controller;

import com.climbjava.demo.domain.Attach;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@Slf4j
public class UploadController {
  public static final String UPLOAD_PATH = "d:/upload/files";

  @GetMapping("upload")
  public String uploadForm() {
    return "uploadForm";
  }

  @PostMapping("upload")
  @ResponseBody
  public ResponseEntity<?> upload(List<MultipartFile> f1) throws IOException { // f1은 JS에서 formdata로 받을 때 줬던 이름값
    f1.forEach(f -> log.info(f.getOriginalFilename()));

    List<Attach> attachs = new ArrayList<Attach>();

    int odr = 0;
    for(MultipartFile part : f1) {
      if(part.getSize() == 0) {
        continue;
      }

      //이미지 파일 종류 image/png, image/jpeg, image/gif, image/webp, image/bmp
      Long fileSize = part.getSize();
      String origin = part.getOriginalFilename();
      String contentType = part.getContentType();

      log.info("origin : {}", origin);
      //ext (확장자 추출)
      int idx = origin.lastIndexOf(".");
      String ext = "";
      if(idx >= 0) {
        //확장자가 존재할 경우
        ext = origin.substring(idx);
      }
      UUID uuid = UUID.randomUUID();
      String fileName = uuid + ext;

      boolean image = contentType.startsWith("image");
      String path = genPath();
      String realPath = UPLOAD_PATH + "/" + path + "/";

      File file = new File(realPath);

      if(!file.exists()) {
        file.mkdirs();
      }

      part.transferTo(new File(realPath + fileName));
      log.info("filename : {}", "upload/" + path + "/" +fileName);

      if(image) {
        //이미지인 경우 추가처리 > 썸네일 생성
        try {
          Thumbnails.of(new File(realPath + fileName)).size(150, 150).toFile(realPath + "t_" + fileName);
        }
        catch (Exception e) {
          image = false;
        }
        //ImageIo로도 사용 가능하다
      }

      log.info("{} :: {} :: {} :: {}",  fileSize, origin, contentType, ext);
      attachs.add(Attach.builder()
              .uuid(fileName)
              .origin(origin)
              .image(image)
              .path(path)
              .odr(odr++)
              .size(fileSize)
              .build());
    }

    return ResponseEntity.ok().body(attachs);
  }

  private String genPath() {
    return new SimpleDateFormat("yyyy/MM/dd").format(new Date().getTime());
  }
}
