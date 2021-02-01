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

import co.micol.book.web.BookInsert;
import co.micol.book.web.BookUpdate;
import co.micol.book.web.BookUpdateForm;
import co.micol.book.web.BookDelete;
import co.micol.book.web.BookForm;
import co.micol.book.web.InsertForm;
import co.micol.member.web.JoinForm;
import co.micol.member.web.Login;
import co.micol.member.web.LoginForm;
import co.micol.member.web.Logout;
import co.micol.member.web.MainCommand;
import co.micol.member.web.MemberIdCheck;
import co.micol.member.web.MemberJoin;


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
		map.put("/insertForm.do", new InsertForm()); // 새 도서 폼
		map.put("/bookInsert.do", new BookInsert()); //등록
		map.put("/bookDelete.do", new BookDelete()); //삭제
		map.put("/bookUpdateFrom.do", new BookUpdateForm()); //책 수정 폼 호출
		map.put("/bookUpdate.do", new BookUpdate()); //책 수정
		map.put("/joinForm.do", new JoinForm()); //회원가입 폼 호출
		map.put("/memberjoin.do", new MemberJoin());//회원가입완료
		map.put("/idCheck.do", new MemberIdCheck());//아이디 체크
		map.put("/logout.do", new Logout());  //로그아웃

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
