package dados;

import java.sql.Connection;
import java.sql.Date;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DaoMedicao {
	private Connection con;
	public Connection gerarConexao() {
		 con = null;
		try {
			 con = DriverManager.getConnection("jdbc:postgresql://tdevcgen.macieiras.com.br:5432/unicap_bd_sin", "aluno_bd_sin", "aluno_bd_sin");
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public void inserirDados(List<Medicao> list) {
		con = gerarConexao();
		
		try{
			String sql = "INSERT INTO public.medicao(id_sala,data,ar_condicionado,lampada,pc) "
					+ "VALUES(?,?,?,?,?)";
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			
			for(Medicao lista : list) {
				
				prepareStatement.setInt( 1, lista.getId());
				prepareStatement.setDate(2, new java.sql.Date(lista.getData().getTime()));
				prepareStatement.setDouble(3, lista.getMedicaoAr());
				prepareStatement.setDouble(4, lista.getMedicaoLampada());
				prepareStatement.setDouble(5, lista.getMedicaoPc());
				prepareStatement.execute();
			}
			
				
				prepareStatement.close();
				con.close();
				
		}catch (SQLException e) {
			System.out.println("Connection failure.");
            e.printStackTrace();
		}
		
		
	}
}
