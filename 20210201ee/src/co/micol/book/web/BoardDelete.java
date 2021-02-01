package co.micol.book.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.book.common.Command;
import co.micol.book.dao.BookDao;
import co.micol.book.vo.BookVo;

public class BoardDelete implements Command {

	@Override
	public String exec(HttpServletRequest requset, HttpServletResponse response) {
		// TODO 삭제
		BookDao dao = new BookDao();
		BookVo vo = new BookVo();
		vo.setbCode(requset.getParameter("bCode"));
		
		int n = dao.delete(vo);
		
		return "bookForm.do";
	}

}
