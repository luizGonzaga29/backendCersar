package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RecuperarDados {

	
	public static void main(String[] args)  {

		String path = "C:\\Users\\cabra\\OneDrive\\Área de Trabalho\\Empresa_A.txt";
		
		List<Medicao> list = new ArrayList<>();
		Medicao m = new Medicao();
		list = m.gerarLista(path);
		
		DaoMedicao dm = new DaoMedicao();
		
		dm.inserirDados(list);
		
		//for (Medicao list1 : list) {
			//System.out.println(list1);
		//}
		//DaoMedicao dm = new DaoMedicao();
		/*try{
			String sql = "INSERT INTO public.medicao(id_sala,data,ar_condicionado,lampada,pc) "
					+ "VALUES(?,?,?,?,?)";
			//Statement statement = dm.gerarConexao().createStatement();
			//ResultSet resultSet = statement.executeQuery("sql");
			//Connection con = dm.gerarConexao();
			PreparedStatement prepareStatement = con.prepareStatement(sql);
				prepareStatement.setInt( 1, 1);
				prepareStatement.setDate(2, null);
				prepareStatement.setDouble(3, 8.9);
				prepareStatement.setDouble(4, 20.5);
				prepareStatement.setDouble(5, 11.5);
				
				prepareStatement.execute();
				prepareStatement.close();
				con.close();
			
			//while (resultSet.next()) {
              //  System.out.println(resultSet.getString("id_sala") + resultSet.getString("data"));
            //}
			
		}catch (SQLException e) {
			System.out.println("Connection failure.");
            e.printStackTrace();
		}*/
		
		
	}

}
