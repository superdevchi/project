package com.example.ecommerce.AWS;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Repository
public class AWSIMAGEUPLOAD {

    public String getThumbnail;
    public String getVendoricon;

    public String getFileImageName;

    public String URL;


    public String BucketName = "9socialmedia";

    AWSCredentials credentials = new BasicAWSCredentials("AKIARAQEMD63IEOP7S4R","WaV1iLHwLCqyAsKrjCElyZ1cSVYjF8wteU0LMcg8");



    AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.EU_NORTH_1).build();


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Error converting multipartFile to file");
        }
        return convertedFile;
    }

    public void uploadFileImage(MultipartFile file){
        File FileObj = convertMultiPartFileToFile(file);
        String FileName = System.currentTimeMillis() + "" + file.getOriginalFilename();
        getFileImageName = FileName;
        s3client.putObject( new PutObjectRequest(BucketName,FileName,FileObj).withCannedAcl(CannedAccessControlList.PublicRead));

        URL = String.valueOf(s3client.getUrl(BucketName, FileName));

        System.out.println("uploaded check mazon" + "url" + URL);


    }
    public void uploadMultipleFileImage(MultipartFile thumbnail, MultipartFile vendoricon){
        File Filethumbnail = convertMultiPartFileToFile(thumbnail);
        File Filevendoricon = convertMultiPartFileToFile(vendoricon);
        String thumbnailFileName = System.currentTimeMillis() + "" + thumbnail.getOriginalFilename();
        String vendoriconFileName = System.currentTimeMillis() + "" + vendoricon.getOriginalFilename();
        getThumbnail = thumbnailFileName;
        getVendoricon = vendoriconFileName;
        s3client.putObject( new PutObjectRequest(BucketName,thumbnailFileName,Filethumbnail).withCannedAcl(CannedAccessControlList.PublicRead));
        s3client.putObject( new PutObjectRequest(BucketName,vendoriconFileName,Filevendoricon).withCannedAcl(CannedAccessControlList.PublicRead));

        URL = String.valueOf(s3client.getUrl(BucketName, thumbnailFileName));

        System.out.println("uploaded check mazon" + "url" + URL);


    }

    public String GETURLIMAGE(){
        return String.valueOf(s3client.getUrl(BucketName, getFileImageName));
    }

    public String GETTHUMBNAILIMAGE(){
        return String.valueOf(s3client.getUrl(BucketName, getThumbnail));
    }
    public String GETVENDORICONIMAGE(){
        return String.valueOf(s3client.getUrl(BucketName, getVendoricon));
    }

}
