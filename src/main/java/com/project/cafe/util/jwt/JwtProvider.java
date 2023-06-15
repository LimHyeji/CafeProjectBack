package com.project.cafe.util.jwt;

<<<<<<< HEAD
import com.project.cafe.member.model.vo.MemberVO;
=======
import com.project.cafe.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;//build.gradle에 jsonwebtoken을 추가해주어야 함
import java.util.Date;
>>>>>>> 0b627800e17c2d5348cd44f81c3b508a3f0cbc52

@Component
public class JwtProvider {

    //각각 application.properties에 선언된 값 가져오기
    @Value("${jwt.secretKey")
    private String secretKey;
    @Value("${jwt.expired}")
    private long expired;

    //토큰 생성하는 메소드
    public String createToken(MemberVO memberVO) throws Exception{
        return Jwts.builder()
                .setHeaderParam("typ","JWT")//헤더에 타입을 JWT로 지정하고
                .setSubject("memberToken")//이름은 memberToken이고
                .setExpiration(new Date(System.currentTimeMillis()+expired))//만료기한은 900000이고
                .claim("memberId",memberVO.getMemberId())//저장되는 데이터는 memberId와
                .claim("memberName",memberVO.getMemberName())//memberName이고
                .signWith(SignatureAlgorithm.HS256,secretKey.getBytes())//JWS 서명을 위한 KEY 지정하고
                .compact();//압축
    }

    //api로 토큰을 받았을 때 유효성 검증해 memberVO 반환하는 메소드
    public MemberVO parseInfo(String accessToken) throws JWTException {
        Jws<Claims> claimsJws=null;

        try{
            MemberVO memberVO= new MemberVO();
            claimsJws=Jwts.parserBuilder()//JwtParseBuilder 인스턴스 생성하고
                    .setSigningKey(secretKey.getBytes())//JWS 서명 검증을 위해 KEY 지정하고
                    .build()//쓰레드에 안전한 JwtParser 리턴을 위해 build() 메소드 호출하고
                    .parseClaimsJws(accessToken);//원본 JWT 반환을 위한 메소드 호출
            //-> 이 과정에서 JWT 검증 실패 시 각각의 예외가 발생함

            memberVO.setMemberId(claimsJws.getBody().get("memberId",String.class));
            memberVO.setMemberName(claimsJws.getBody().get("memberName",String.class));
            return memberVO;
        } catch(SignatureException|MalformedJwtException|ExpiredJwtException|UnsupportedJwtException|IllegalArgumentException e){
            //JWT 서명이 실패했거나(변조가 발생했거나), JWT의 구조가 잘못되었거나, 만료시간이 지나거나, JWT의 형식이 다르거나, 올바르지않은 경우일 때
            throw new JwtException("다시 로그인하세요");
        }catch(Exception e){
            throw new JwtException("다시 로그인하세요");
        }
    }

    //api로 토큰을 받았을 때 유효성 검증만을 수행하는 메소드
    public boolean isValidToken(String accessToken) throws JWTException {
        try{
            Jwts.parserBuilder()//JwtParseBuilder 인스턴스 생성하고
            .setSigningKey(secretKey.getBytes())//JWS 서명 검증을 위해 KEY 지정하고
            .build()//쓰레드에 안전한 JwtParser 리턴을 위해 build() 메소드 호출하고
             .parseClaimsJws(accessToken);//원본 JWT 반환을 위한 메소드 호출
            //-> 이 과정에서 JWT 검증 실패 시 각각의 예외가 발생함;

            return true;
        }catch(SignatureException e){
            System.out.println("Invalid JWT Signature");
        }catch(MalformedJwtException e){
            System.out.println("Invalid JWT Token");
        }catch(ExpiredJwtException e){
            System.out.println("Expired JWT token");
        }catch(UnsupportedJwtException e){
            System.out.println("Unsupported JWT token");
        }catch(IllegalArgumentException e){
            System.out.println("JWT claims string is empty");
        } catch (Exception e) {
            throw new JwtException("다시 로그인하세요");
        }
        return false;
    }
}
