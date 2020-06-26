package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import dto.BoardDTO;

public class BoardDAO extends JdbcDAO {
	private static BoardDAO _dao;
	
	private BoardDAO() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_dao=new BoardDAO();
	}
	
	public static BoardDAO getDAO() {
		return _dao;
	}
	
	public List<BoardDTO> boardList_two(int pageNo) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			con=getConnection();
			String sql = "select t.* from (select * from bbs_board01 order by num desc) t where rownum between 1 and 10";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO board=new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setId(rs.getString("id"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setRegDate(rs.getString("reg_date"));
				board.setReadCount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setReStep(rs.getInt("re_step"));
				board.setReLevel(rs.getInt("re_Level"));
				board.setContent(rs.getString("content"));
				board.setIp(rs.getString("ip"));
				board.setStatus(rs.getInt("status"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			System.out.println("[占쏙옙占쏙옙]getBoardList() 筌롳옙占쏙옙占쏙옙占쏙옙 SQL 占썬끇占� = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return boardList;
	}
	
	
	
	//野껓옙占쏙옙占쏙옙占쏙옙�⑨옙 野껓옙占쏙옙占썬끉占쏙옙占쏙옙�몴占� 占쏙옙占싼됵옙占쏙옙 BOARD 占쏙옙占쎈�占쏙옙占� 占쏙옙占싸삼옙占� 野껓옙占쏙옙占썬끉占쏙옙占쏙옙 野껓옙占쏙옙疫뀐옙占쏙옙 揶쏉옙占쏙옙�몴占� 野껓옙占쏙옙占쏙옙占쏙옙 獄쏉옙占쏙옙占쏙옙占쏙옙 筌롳옙占쏙옙占쏙옙
	// => 野껓옙占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙 野껋럩占쏙옙 BOARD 占쏙옙占쎈�占쏙옙占� 占쏙옙占싸삼옙占� 占쏙옙筌ｏ옙 野껓옙占쏙옙疫뀐옙占쏙옙 揶쏉옙占쏙옙�몴占� 野껓옙占쏙옙占쏙옙占쏙옙 獄쏉옙占쏙옙占쏙옙占쏙옙 筌롳옙占쏙옙占쏙옙
	public int getBoardTotal(String search, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int rows=0;
		try {
			con=getConnection();
			
			//筌롳옙占쏙옙占쏙옙占쏙옙 占쏙옙占쎌눖占쏙옙怨쀯옙占� 占쏙옙占싼됵옙占� 揶쏉옙占쏙옙 占쎄퀡占쏙옙 占썬끇�뀲 SQL   
			//筌륅옙占쎈�占쏙옙 占쏙옙占싸쏙옙占쏙옙占� 占쏙옙占싼됵옙占쏙옙占썸에占� 占썬끉占쏙옙 - 占쏙옙占쏙옙 SQL
			if(keyword.equals("")) {//野껓옙占쏙옙 疫꿸퀡占쏙옙 沃섎챷占싼딉옙占�
				String sql="select count(*) from bbs_board01";
				pstmt=con.prepareStatement(sql);
			} else {//野껓옙占쏙옙 疫꿸퀡占쏙옙 占싼딉옙占�
				//�뚎됵옙�눖占쏙옙占� 占쏙옙占싸쏙옙占� Java 癰귨옙占쏙옙占쏙옙 InParameter嚥∽옙 占싼딉옙占� �겫占썲첎占쏙옙占�
				String sql="select count(*) from bbs_board01 where "+search+" like '%'||?||'%'";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
			}
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				rows=rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("[占쏙옙占쏙옙]getBoardTotal() 筌롳옙占쏙옙占쏙옙占쏙옙 SQL 占썬끇占� = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return rows;
	}
	
	//占쏙옙占쏙옙 占쏙옙甕곤옙占쎈챷占쏙옙 �넫占썹뙴占� 占쏙옙甕곤옙占쎈챶占쏙옙 占쏙옙占싼됵옙占쏙옙 BOARD 占쏙옙占쎈�占쏙옙占� 占쏙옙占싸삼옙占� 野껓옙占쏙옙疫뀐옙 筌뤴뫖占쏙옙占� 占쏙옙占쎈똻占� 占썩뫁占쏙옙嚥∽옙 野껓옙占쏙옙占쏙옙占쏙옙 獄쏉옙占쏙옙占쏙옙占쏙옙 筌롳옙占쏙옙占쏙옙
	// => 占쏙옙占쎈�占쏙옙占� 占쏙옙占싸삼옙占� 占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙占쎈똻占� 筌ｏ옙�뵳占�
	public List<BoardDTO> getBoardList(int startRow, int endRow, String search, String keyword) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardDTO> boardList=new ArrayList<BoardDTO>();
		try {
			con=getConnection();
		
			if(keyword.equals("")) {	
				String sql="select * from (select rownum rn, temp.* from (select * from bbs_board01 order by ref desc,re_step) temp) where rn between ? and ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
			} else {
				String sql="select * from (select rownum rn, temp.* from (select * from bbs_board01 where "
					+search+" like '%'||?||'%' and status!=9 order by ref desc,re_step) temp) where rn between ? and ?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO board=new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setId(rs.getString("id"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setRegDate(rs.getString("reg_date"));
				board.setReadCount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setReStep(rs.getInt("re_step"));
				board.setReLevel(rs.getInt("re_Level"));
				board.setContent(rs.getString("content"));
				board.setIp(rs.getString("ip"));
				board.setStatus(rs.getInt("status"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			System.out.println("[占쏙옙占쏙옙]getBoardList() 筌롳옙占쏙옙占쏙옙占쏙옙 SQL 占썬끇占� = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return boardList;
	}
	
	//BOARD_SEQ 占쏙옙占쎈챷占썬끉占쏙옙 占쏙옙占쏙옙 筌앾옙揶쏉옙揶쏉옙占쏙옙 野껓옙占쏙옙占쏙옙占쏙옙 獄쏉옙占쏙옙占쏙옙占쏙옙 筌롳옙占쏙옙占쏙옙
	public int getBoardNum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int num=0;
		try {
			con=getConnection();
			
			String sql="select sequence_bbs.nextval from dual";					
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("[占쏙옙占쏙옙]getBoardNum() 筌롳옙占쏙옙占쏙옙占쏙옙 SQL 占썬끇占� = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return num;
	}
	
	//野껓옙占쏙옙疫뀐옙 占쏙옙癰귣�占쏙옙 占쏙옙占싼됵옙占쏙옙 BOARD 占쏙옙占쎈�占쏙옙占� 占쏙옙占싸쏙옙占썸�⑨옙 占쏙옙占싸쏙옙占쏙옙占� 揶쏉옙占쏙옙�몴占� 獄쏉옙占쏙옙占쏙옙占쏙옙 筌롳옙占쏙옙占쏙옙
	public int addBoard(BoardDTO board) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="insert into bbs_board01 values(?,?,?,?,sysdate,0,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board.getNum());
			pstmt.setString(2, board.getId());
			pstmt.setString(3, board.getWriter());
			pstmt.setString(4, board.getSubject());
			pstmt.setInt(5, board.getRef());
			pstmt.setInt(6, board.getReStep());
			pstmt.setInt(7, board.getReLevel());
			pstmt.setString(8, board.getContent());
			pstmt.setString(9, board.getIp());
			pstmt.setInt(10, board.getStatus());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[占쏙옙占쏙옙]addBoard() 筌롳옙占쏙옙占쏙옙占쏙옙 SQL 占썬끇占� = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//�겫占쏙쭗�몿占� 占쏙옙癰귣�占쏙옙 占쏙옙占싼됵옙占쏙옙 BOARD 占쏙옙占쎈�占쏙옙占� 占쏙옙占싸삼옙占� 野껓옙占쏙옙疫뀐옙占쏙옙占쏙옙 揶쏉옙占쏙옙 域밸챶竊숋옙占� 占쏙옙占쏙옙揶쏉옙 
	//占쏙옙占쏙옙 野껓옙占쏙옙疫뀐옙占쏙옙 占쏙옙占쏙옙揶쏉옙占쏙옙 1 筌앾옙揶쏉옙占쏙옙占쏙옙嚥∽옙 癰귨옙野껋�占쏙옙�⑨옙 癰귨옙野껋�占쏙옙占쏙옙 獄쏉옙占쏙옙占쏙옙占쏙옙 筌롳옙占쏙옙占쏙옙
	// => 占쏙옙嚥∽옙占쏙옙 占쎈벀占쏙옙占� 疫꿸퀣�� 占쎈벀占썼퉪��占쏙옙 �솒�눘占쏙옙 野껓옙占쏙옙占쏙옙占쏙옙嚥∽옙 占썬끉占쏙옙
	public int modifyReSetp(int ref,int reStep) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update bbs_board01 set re_step=re_step+1 where ref=? and re_step>?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, reStep);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[占쏙옙占쏙옙]modifyReSetp() 筌롳옙占쏙옙占쏙옙占쏙옙 SQL 占썬끇占� = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//野껓옙占쏙옙疫뀐옙 甕곤옙占쎈챶占쏙옙 占쏙옙占싼됵옙占쏙옙 BOARD 占쏙옙占쎈�占쏙옙占� 占쏙옙占싸삼옙占� 野껓옙占쏙옙疫뀐옙占쏙옙 野껓옙占쏙옙占쏙옙占쏙옙 獄쏉옙占쏙옙占쏙옙占쏙옙 筌롳옙占쏙옙占쏙옙
	public BoardDTO getBoard(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BoardDTO board=null;
		try {
			con=getConnection();
			
			String sql="select * from bbs_board01 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				board=new BoardDTO();
				board.setNum(rs.getInt("num"));
				board.setId(rs.getString("id"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setRegDate(rs.getString("reg_date"));
				board.setReadCount(rs.getInt("readcount"));
				board.setRef(rs.getInt("ref"));
				board.setReStep(rs.getInt("re_step"));
				board.setReLevel(rs.getInt("re_Level"));
				board.setContent(rs.getString("content"));
				board.setIp(rs.getString("ip"));
				board.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			System.out.println("[占쏙옙占쏙옙]getBoard() 筌롳옙占쏙옙占쏙옙占쏙옙 SQL 占썬끇占� = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return board;
	}
	
	//野껓옙占쏙옙疫뀐옙 甕곤옙占쎈챶占쏙옙 占쏙옙占싼됵옙占쏙옙 BOARD 占쏙옙占쎈�占쏙옙占� 占쏙옙占싸삼옙占� 野껓옙占쏙옙疫뀐옙占쏙옙 鈺곌퀬占쏙옙占쏙옙揶쏉옙 1 筌앾옙揶쏉옙 占쏙옙占쏙옙嚥∽옙 癰귨옙野껋�占쏙옙�⑨옙 癰귨옙野껋�占쏙옙占쏙옙 揶쏉옙占쏙옙�몴占� 獄쏉옙占쏙옙占쏙옙占쏙옙 筌롳옙占쏙옙占쏙옙
	public int modifyReadCount(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update bbs_board01 set readcount=readcount+1 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[占쏙옙占쏙옙]modifyReadCount() 筌롳옙占쏙옙占쏙옙占쏙옙 SQL 占썬끇占� = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//野껓옙占쏙옙疫뀐옙 甕곤옙占쎈챶占쏙옙 占쏙옙占싼됵옙占쏙옙 BOARD 占쏙옙占쎈�占쏙옙占� 占쏙옙占싸삼옙占� 野껓옙占쏙옙疫뀐옙占쏙옙 占쏙옙占쏙옙 筌ｏ옙�뵳�뗰옙占썸�⑨옙 癰귨옙野껋�占쏙옙占쏙옙 揶쏉옙占쏙옙�몴占� 獄쏉옙占쏙옙占쏙옙占쏙옙 筌롳옙占쏙옙占쏙옙
	public int removeBoard(int num) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update bbs_board01 set status=9 where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[占쏙옙占쏙옙]removeBoard() 筌롳옙占쏙옙占쏙옙占쏙옙 SQL 占썬끇占� = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	//野껓옙占쏙옙疫뀐옙占쏙옙 占쏙옙占싼됵옙占쏙옙 BOARD 占쏙옙占쎈�占쏙옙占� 占쏙옙占싸삼옙占� 野껓옙占쏙옙疫뀐옙占쏙옙 癰귨옙野껋�占쏙옙�⑨옙 癰귨옙野껋�占쏙옙占쏙옙 揶쏉옙占쏙옙�몴占� 獄쏉옙占쏙옙占쏙옙占쏙옙 筌롳옙占쏙옙占쏙옙
	public int modifyBoard(BoardDTO board) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update bbs_board01 set subject=?,status=?,content=? where num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setInt(2, board.getStatus());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getNum());
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[占쏙옙占쏙옙]modifyBoard() 筌롳옙占쏙옙占쏙옙占쏙옙 SQL 占썬끇占� = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
}








