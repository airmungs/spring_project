package com.shopping.www;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Repository;

//패스워드를  SHA3-256형태로 변환하는 메소드
//회원가입,로그인,1대1문의게시판,상품구매 등
@Repository("sha3pass")
public class sha3_pass {
    public String sha3Making(String mpass) {
        StringBuilder sb = new StringBuilder(); // return에 사용되는 객체
        try {
            MessageDigest md = MessageDigest.getInstance("SHA3-256");
            md.update(mpass.getBytes());
            for (byte bt : md.digest()) {
                sb.append(String.format("%02x", bt)); // 2자리 16진수로 변환
            }
        } catch (NoSuchAlgorithmException e) {
            sb.append("알고리즘 오류 발생으로 생성이 되지 않음");
        } catch (Exception e) {
            sb.append("인자값 오류 발생으로 생성이 되지 않음");
        }

        return sb.toString();
    }
}
