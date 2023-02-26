package com.nextappoficial.springboot.app.invoice.sistem.Auth;

public class JwtConfig {
    public static final String KEY_SECRET_TOKEN = "alguna.clave.secreta.123456789";

    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAsK12vjGRLvSsqdIAFCitRMf6M4PzQii/PhrrIo6hcvWj8rM2\n" +
            "6GKnJoCCqDtTjp8RMDxaSnswCbLLGGN2CWfsDc79+0Sqyo1+jPAOUGr3R+5moCoB\n" +
            "uuKPSQrRqTZy+u+6DTNSoj+PNeSnogVidtR9BDvqZxztp/D4kZHURhzH2MzS9c7X\n" +
            "M4rQYhRCFuufy4258mlLGQPrOm+Y9mDLZGPMQpsLy76WI1zwLTdA/B+CqcOvBanp\n" +
            "2JrgTmIWCJxSdjthvyEp2fkcwfc88tZYdQi2Am6PHqlbeaZmDov31fMx+Yf4CxNJ\n" +
            "ULuqtm/AoLWcHW97VaXx5ZxZOBFNMMJsL6OpywIDAQABAoIBAB4jCK06sUQsMkOp\n" +
            "cZ/+rEeeQPyGlnkDkFOcrd0wLTP52KRuk4AiS9bSdvcRX5hlY74xnUk1KCWPXPtl\n" +
            "A95o3qggVcyFkwiICD0sOZTYGiQn81OAttjVCpHwCYFuq0BIohjcV00bwGb2AO4v\n" +
            "MvOIHWcafUpncdaC0TOrh2UKRcCiLw1cvxg7knPWu65Gc9Wz3UXOiu+o2qWsBm2o\n" +
            "oZq9CSz6D9nfY5NAzJ/DUKM+23aBwkXpx2PjaoxLHVG54HigPqm/C92f1yoj+Ux2\n" +
            "0U3L7TSRUYIPiTbeJS0IxAjJ8vJzZNzypoG02fFp8IDD5JciAxCI3/xqLHwQeMYr\n" +
            "Y10otiECgYEA2Fm/q43YolbIh674zXNy3k/MevI9lQyKbGPAfUFYp+JgnkYJO64T\n" +
            "oiFMSV9k13+7zlIwqdwkS5025+Z96yQ2q0XlXBJ/xcqggLp0CT0PP+zvWdpbGQq6\n" +
            "oaUcOq5YhvX/F/sNIvBCZnD7e7XgvkdOt1Bz/FGfsli58im/r+e1DnsCgYEA0Q5t\n" +
            "R4RiT8Q7jH2/EKD62B26RLssDUDdLf/zJtT2ZYa9oFbNtXLIGv1JPNlnLnzg0LOQ\n" +
            "ZR1l4G3lXeIzPPd0gR3Yli9yc51WnFVTujd3Iwl2Xe2+DSiQTaXfAkEjXVm5BuSv\n" +
            "cJZ8nj1ZqUWwoWcMfp7bJ/OdLwfl9USEPwFImPECgYByq58bnqMj6DuBLwxQWNCZ\n" +
            "ZFJNF/fr+CmmKXjs6qXC1gm/2f8q0qk1VdDJUvZwAhPY11dTsIrMSRfA8jnrV9Zi\n" +
            "ETnwAx4WB7/qeujlvKdIipySU1nVfApPguPjdmUVo20BmN7tlcqb4WZpu4zTTZg7\n" +
            "MTlIkzrBnKVx5Q7kfTtJ4QKBgQC+6/oJMnHGf2QS7lQsD5iG/r86PeYBRJe6huG9\n" +
            "OQi9t8w+PogniegOHftgwvtkjU+Tyim47pw31qV+lDJPH38a2/MNVc+knrCWnaaV\n" +
            "jkoTiXS8W38R7VW8VBvSp0Jw4pwEG53NqOGuckEMQkUJYl8a1GE5n1aRuRjeYfCU\n" +
            "yctPQQKBgBfQXH4i2ZE4QGoABXkhnwgmwG1WQEE9ywEwqK781sijN61w1ymCCd9P\n" +
            "ltvvk2Bi0Bemhkq/KBReeC7u5VD8MdlrZcHSpEjnP0mbBUJmF/at4x42ox7nmC+r\n" +
            "PEk5z/PwiD9FDhEsXtysTAvV3bAGI30W7onDq8KgfklHvryTHFsL\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsK12vjGRLvSsqdIAFCit\n" +
            "RMf6M4PzQii/PhrrIo6hcvWj8rM26GKnJoCCqDtTjp8RMDxaSnswCbLLGGN2CWfs\n" +
            "Dc79+0Sqyo1+jPAOUGr3R+5moCoBuuKPSQrRqTZy+u+6DTNSoj+PNeSnogVidtR9\n" +
            "BDvqZxztp/D4kZHURhzH2MzS9c7XM4rQYhRCFuufy4258mlLGQPrOm+Y9mDLZGPM\n" +
            "QpsLy76WI1zwLTdA/B+CqcOvBanp2JrgTmIWCJxSdjthvyEp2fkcwfc88tZYdQi2\n" +
            "Am6PHqlbeaZmDov31fMx+Yf4CxNJULuqtm/AoLWcHW97VaXx5ZxZOBFNMMJsL6Op\n" +
            "ywIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}