package co.micol.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.book.common.Command;
import co.micol.book.dao.BookDao;
import co.micol.book.vo.BookVo;

public class BookUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 글 수정 저장버튼 누를때
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();
		vo.setbCode(request.getParameter("bCode"));
		vo.setbName(request.getParameter("bName"));
		vo.setbCount(Integer.parseInt(request.getParameter("bCount")));
		
		int n = dao.update(vo);
		
		
		return "bookForm.do";
	}

}
