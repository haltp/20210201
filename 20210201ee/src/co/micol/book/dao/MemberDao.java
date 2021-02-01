package co.micol.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.micol.book.common.DAO;
import co.micol.book.vo.MemberVo;

public class MemberDao extends DAO {
	private PreparedStatement psmt;
	private ResultSet rs;

	// 회원 전체 조회
	public ArrayList<MemberVo> selectMemList() {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		String sql = "SELECT * FROM MEM";
		MemberVo vo;
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setmId(rs.getString("MEMBERID"));
				vo.setmName(rs.getString("MEMBERNAME"));
				vo.setmTel(rs.getString("MEMBERTEL"));
				vo.setmAdd(rs.getString("MEMBERADDRESS"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	// 회원 등록 - 사용자가 할 수 있게
	public int insert(MemberVo vo) {
		String sql = "INSERT INTO MEM(MEMBERID, MEMBERNAME, MEMBERPASSWORD, MEMBERTEL, MEMBERADDRESS) VALUES (?, ?, ?, ?, ?)";
		int n = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getmId());
			psmt.setString(2, vo.getmName());
			psmt.setString(3, vo.getmPass());
			psmt.setString(4, vo.getmTel());
			psmt.setString(5, vo.getmAdd());
			n = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	// 회원 수정 - 관리자
	public int update(MemberVo vo) {
		String sql = "UPDATE MEM SET MEMBERPASSWORD = ?, MEMBERTEL=?, MEMBERADDRESS = ? WHERE MEMBERID = ?";
		int n = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getmPass());
			psmt.setString(2, vo.getmTel());
			psmt.setString(3, vo.getmAdd());
			psmt.setString(4, vo.getmId());
			n = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	// 회원 삭제 - 관리자
	public int delete(MemberVo vo) {
		String sql = "DELETE FROM MEM WHERE MEMBERID =?";
		int n = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getmId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	// 로그인
	public MemberVo login(MemberVo vo) {
		String sql = "SELECT * FROM MEM WHERE MEMBERID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getmId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setmName(rs.getString("MEMBERNAME"));
				vo.setmAu(rs.getString("MEMBERAU"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	// 아이디 중복 체크
	public boolean isIdCheck(String id) { // id중복체크를 위한 메소드
		boolean bool = true;
		String sql = "SELECT MEMBERID FROM MEMR WHERE MEMBERID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				bool = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bool;
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
