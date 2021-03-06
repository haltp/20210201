package co.micol.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.book.common.Command;
import co.micol.book.dao.BookDao;
import co.micol.book.vo.BookVo;

public class BookUpdateForm implements Command {

	@Override
	public String exec(HttpServletRequest requset, HttpServletResponse response) {
		// 책 수정 폼 호출
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();
		vo.setbCode(requset.getParameter("bCode"));
		vo.setbName(requset.getParameter("bName"));
//		vo.setQuanti(Integer.parseInt(requset.getParameter("quanti")));
//		vo.setbCount(Integer.parseInt(requset.getParameter("bCount")));
		
		vo = dao.select(vo);
		requset.setAttribute("vo", vo);
		return "book/bookUpdateForm";
	}

}
