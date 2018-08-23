package principal;

import conexao.Conexao;
import dao.LivroDao;
import dominio.Livro;

public class Testador {

	public static void main(String[] args) {
		
		Livro l = new Livro("Getting real","37 signals","37 signals",4,25.87);
		
		Conexao c = new Conexao();
		LivroDao ldao = new LivroDao(c.abreConexao());
		ldao.save(l);
		
		System.out.println(ldao.getOne(3));

		c.fechaConexao();
		Livro autualizado = new Livro("valley of genius","dont know ","dontknow either",3,56.78);
		autualizado.getId();
		ldao.update(autualizado);
		
		System.out.println("-*-*-**--*-*-**-*-*-*-*-*");
		
		System.out.println(ldao.getOne(3));
		c.fechaConexao();	
		ldao.delete(autualizado);	
		
		System.out.println("-*-*-**--*-*-**-*-*-*-*-*");
		
		System.out.println(ldao.list());		
		c.fechaConexao();
		
		System.out.println("-*-*-**--*-*-**-*-*-*-*-*");
		
		System.out.println(ldao.getOne(3));
		System.out.println(ldao.list());
		ldao.delete(5);
		}
}
