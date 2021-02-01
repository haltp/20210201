package co.micol.book.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.book.web.BookForm;
import co.micol.book.web.JoinForm;
import co.micol.book.web.Login;
import co.micol.book.web.LoginForm;
import co.micol.book.web.MainCommand;
import co.micol.book.web.MemberIdCheck;
import co.micol.book.web.MemberJoin;


@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Command> map = new HashMap<String, Command>();   
    
    public FrontController() {
        super();
        
    }

	
	public void init(ServletConfig config) throws ServletException {
		map.put("/main.do", new MainCommand());
		map.put("/loginForm.do", new LoginForm()); //로그인
		map.put("/login.do", new Login()); //로그인처리
		map.put("/bookForm.do", new BookForm()); //도서 폼 호출
		map.put("/joinForm.do", new JoinForm()); //회원가입 폼 호출
		map.put("/memberjoin.do", new MemberJoin());//회원가입완료
		map.put("/idCheck.do", new MemberIdCheck());//아이디 체크
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String contextPath = request.getContextPath();
		String uri = request.getRequestURI();
		String path = uri.substring(contextPath.length());
		
		Command command = map.get(path);
		String viewPage = command.exec(request, response);
		
		if(!viewPage.endsWith(".do")) viewPage = "/WEB-INF/jsp/" + viewPage + ".jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
