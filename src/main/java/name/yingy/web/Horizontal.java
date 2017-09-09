package name.yingy.web;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Horizontal {
    public static void main(String[] args) {
        String guid = UUID.randomUUID().toString().replace("-","");
        System.out.println("guid:"+guid);
        System.out.println("返回的表id:"+splitTable(16,guid));
    }
    public static String splitTable(int n,String guid){
        //n：分表的个数；guid是生成的32位UUID
        String result = "";
        try {
            String newid = getMd5(guid);
            System.out.println(newid);
            int ll = n/16+1;//确定位数
            int nn = Integer.parseInt(newid.substring(newid.length()-ll,newid.length()),16);//16进制转十进制
            result = String.valueOf((nn%n));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String getMd5(String Str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        md5.update(Str.getBytes());
        byte[] byteArray = md5.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for(int i=0;i<byteArray.length;i++){
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }
}
