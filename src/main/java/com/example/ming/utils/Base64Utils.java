package com.example.ming.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * @Author: liubo
 * @Date: 2019/7/9.
 */
public class Base64Utils {
    public static void main(String[] args) {
        String dd ="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L1BhcmVudCA0IDAgUi9NZWRpYUJveFswIDAgNTk1IDg0MV0+PgplbmRvYmoKMTAgMCBvYmoKPDwvRGVzY2VudCAtMTIwL0NhcEhlaWdodCA4ODAvU3RlbVYgOTMvVHlwZS9Gb250RGVzY3JpcHRvci9GbGFncyA2L1N0eWxlPDwvUGFub3NlKAEFAgIEAAAAAAAAACk+Pi9Gb250QkJveCBbLTI1IC0yNTQgMTAwMCA4ODBdL0ZvbnROYW1lL1NUU29uZy1MaWdodC9JdGFsaWNBbmdsZSAwL0FzY2VudCA4ODA+PgplbmRvYmoKMTEgMCBvYmoKPDwvRFcgMTAwMC9TdWJ0eXBlL0NJREZvbnRUeXBlMC9DSURTeXN0ZW1JbmZvPDwvU3VwcGxlbWVudCA0L1JlZ2lzdHJ5KEFkb2JlKS9PcmRlcmluZyhHQjEpPj4vVHlwZS9Gb250L0Jhc2VGb250L1NUU29uZy1MaWdodC9Gb250RGVzY3JpcHRvciAxMCAwIFIvVyBbMVsyMDddMTRbMzc1IDIzOF0xNyAyNiA0NjIgNTRbNzUzXV0+PgplbmRvYmoKMSAwIG9iago8PC9TdWJ0eXBlL1R5cGUwL1R5cGUvRm9udC9CYXNlRm9udC9TVFNvbmctTGlnaHQtVW5pR0ItVUNTMi1IL0VuY29kaW5nL1VuaUdCLVVDUzItSC9EZXNjZW5kYW50Rm9udHNbMTEgMCBSXT4+CmVuZG9iagoyIDAgb2JqCjw8L1N1YnR5cGUvVHlwZTEvVHlwZS9Gb250L0Jhc2VGb250L0hlbHZldGljYS9FbmNvZGluZy9XaW5BbnNpRW5jb2Rpbmc+PgplbmRvYmoKNCAwIG9iago8PC9LaWRzWzUgMCBSIDcgMCBSIDkgMCBSXS9UeXBlL1BhZ2VzL0NvdW50IDMvSVRYVCgyLjEuNyk+PgplbmRvYmoKMTIgMCBvYmoKPDwvVHlwZS9DYXRhbG9nL1BhZ2VzIDQgMCBSPj4KZW5kb2JqCjEzIDAgb2JqCjw8L01vZERhdGUoRDoyMDE5MDcwOTE3NDc1MCswOCcwMCcpL0NyZWF0aW9uRGF0ZShEOjIwMTkwNzA5MTc0NzUwKzA4JzAwJykvUHJvZHVjZXIoaVRleHQgMi4xLjcgYnkgMVQzWFQpPj4KZW5kb2JqCnhyZWYKMCAxNAowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDg1NDEgMDAwMDAgbiAKMDAwMDAwODY2NiAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwMDg3NTQgMDAwMDAgbiAKMDAwMDAwMjY2MSAwMDAwMCBuIAowMDAwMDAyODI3IDAwMDAwIG4gCjAwMDAwMDU4MDYgMDAwMDAgbiAKMDAwMDAwNTk2MyAwMDAwMCBuIAowMDAwMDA3OTc5IDAwMDAwIG4gCjAwMDAwMDgxNDUgMDAwMDAgbiAKMDAwMDAwODMzNyAwMDAwMCBuIAowMDAwMDA4ODI5IDAwMDAwIG4gCjAwMDAwMDg4NzUgMDAwMDAgbiAKdHJhaWxlcgo8PC9JbmZvIDEzIDAgUi9JRCBbPDUxZWM5MjA4MTQ2NTE4MTk3MDI2NTYyZTUxMjYzODliPjw3NDgzZWU3MTU5ZWE4MjBiZTMwNWM4NmM1MDM4ZjEzOD5dL1Jvb3QgMTIgMCBSL1NpemUgMTQ+PgpzdGFydHhyZWYKODk5OAolJUVPRgo=";
        String ss ="E:\\tupian\\jj.pdf";
      //  base64StringToPdf(dd,ss);
    }
    /**
     * Description: 将base64编码内容转换为Pdf
     * @param  ，文件的存储路径（含文件名）
     * @Author fuyuwei
     * Create Date: 2015年7月30日 上午9:40:23
     */
    public static void base64StringToPdf(String base64Content,String filePath) throws  Exception{
        BASE64Decoder decoder = new BASE64Decoder();
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            byte[] bytes = decoder.decodeBuffer(base64Content);//base64编码内容转换为字节数组
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if(!path.exists()){
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while(length != -1){
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(bos!=null){
                bos.close();
            }
            if(fos!=null){
                fos.close();
            }
            if(bis!=null){
                bis.close();
            }
          // closeStream(bis, fos, bos);
        }
    }





    /**
     * Description: 将pdf文件转换为Base64编码
     * @param
     * @Author fuyuwei
     * Create Date: 2015年8月3日 下午9:52:30
     */
    public static String PDFToBase64(File file) {
        BASE64Encoder encoder = new BASE64Encoder();
        FileInputStream fin = null;
        BufferedInputStream bin = null;
        ByteArrayOutputStream baos = null;
        BufferedOutputStream bout = null;
        try {
            fin = new FileInputStream(file);
            bin = new BufferedInputStream(fin);
            baos = new ByteArrayOutputStream();
            bout = new BufferedOutputStream(baos);
            byte[] buffer = new byte[1024];
            int len = bin.read(buffer);
            while (len != -1) {
                bout.write(buffer, 0, len);
                len = bin.read(buffer);
            }
            //刷新此输出流并强制写出所有缓冲的输出字节
            bout.flush();
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fin.close();
                bin.close();
                bout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
