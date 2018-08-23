package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import conexao.Conexao;
import dominio.Livro;

public class LivroDao implements IDao<Livro> {

	private Connection connection;

	public LivroDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void save(Livro t) {

		String SQL = "insert into livro (titulo, autor, editora, avaliacao, preco) values (?,?,?,?,?)";

		try {
			PreparedStatement pstmt = this.connection.prepareStatement(SQL);
			pstmt.setString(1, t.getTitulo());
			pstmt.setString(2, t.getAutor());
			pstmt.setString(3, t.getEditora());
			pstmt.setInt(4, t.getAvaliacao());
			pstmt.setDouble(5, t.getPreco());
			pstmt.execute();
			System.out.println("livro inserido com sucesso");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Livro getOne(int id) {
		Livro p = null;

		try {
			Statement sta = connection.createStatement();
			ResultSet elements = sta.executeQuery("select * from livro where id = " + id);
			while (elements.next()) {
				p = new Livro();
				p.setId(elements.getInt("id"));
				p.setAutor(elements.getString("autor"));
				p.setTitulo(elements.getString("titulo"));
				p.setEditora(elements.getString("editora"));
				p.setAvaliacao(elements.getInt("avaliacao"));
				p.setPreco(elements.getDouble("preco"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}

		return p;
	}

	@Override
	public List<Livro> list() {
		Livro p =  null;
		
		ArrayList<Livro> livros = new ArrayList<>();
		try {
			Statement stmt = connection.createStatement();
			
			ResultSet elements = stmt.executeQuery("select * from livro");
			
			while(elements.next()) {
				p = new Livro();
				p.setId(elements.getInt("id"));
				p.setTitulo(elements.getString("titulo"));
				p.setAutor(elements.getString("autor"));
				p.setEditora(elements.getString("editora"));
				p.setAvaliacao(elements.getInt("avaliacao"));
				p.setPreco(elements.getDouble("preco"));
				livros.add(p);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return livros;
	}

	@Override
	public void update(Livro t) {
		String sql = "update livro set titulo = ?, autor = ?,  editora = ? ,  avaliacao = ?,  preco = ? where id = ?";
		try { 
			PreparedStatement pstmt = this.connection.prepareStatement(sql);
			
			pstmt.setString(1, t.getTitulo());
			pstmt.setString(2, t.getAutor());
			pstmt.setString(3, t.getEditora());
			pstmt.setInt(4, t.getAvaliacao());
			pstmt.setDouble(5, t.getPreco());
			pstmt.setInt(6, t.getId());
			pstmt.execute();
			
			System.out.println("livros alterado");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql ="delete from Livro where id=?";
		try { 
			PreparedStatement pstmt = this.connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.execute();			
			System.out.println("livro deletado");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void delete(Livro id) {
		// TODO Auto-generated method stub
		
	}

}