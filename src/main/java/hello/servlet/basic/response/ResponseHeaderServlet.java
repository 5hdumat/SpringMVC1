package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-line]
        // 자바가 상수로 제공해주는 HTTP 응답코드를 활용하자.
        response.setStatus(HttpServletResponse.SC_OK);

        // [response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        // 1. [Header 편의 메서드]
        // 위에서 처럼 setHeader 메서드를 사용하지 않고 편리하게 사용 가능 하도록 도와준다.
        content(response);
        cookie(response);
        redirect(response);

        response.getWriter().println("안녕하세요");
    }


    private void content(HttpServletResponse response) {
        // response.setHeader("Content-Type", "text/plain;charset=utf-8"); 를 아래와 같이 편리하게 사용 가능
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        // Content-Length: 2 // 생략 가능
        // response.setContentLength(2); // (생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        // Set-Cookie: myCookie=good; Max-Age=600;
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600"); 를 아래와 같이 편리하게 사용 가능
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {


//        // 방법 1.
//        // 응답 코드를 Status Code 302로 만들고, 응답 헤더에 Location: /basic/hello-form.html를 설정하면 클라이언트가
//        // 해당 리소스로 이동된다. 하지만 너무 불편하다.
//        response.setStatus(HttpServletResponse.SC_FOUND); // 302
//        response.setHeader("Location", "/basic/hello-form.html");

        // 방법 2.
        // response.sendRedirect()를 사용하면 위와 동일하게 동작시킬 수 있다.
        response.sendRedirect("/basic/hello-form.html");
    }


}
