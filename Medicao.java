package dados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Medicao {

	private Integer id;
	private Date data;
	private Double medicaoAr;
	private Double medicaoLampada;
	private Double medicaoPc;
	//private List<Medicao> list = new ArrayList<>();
	private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");

	public Medicao() {

	}

	public Medicao(Integer id, Date data, Double medicaoAr, Double medicaoLampada, Double medicaoPc) {
		super();
		this.id = id;
		this.data = data;
		this.medicaoAr = medicaoAr;
		this.medicaoLampada = medicaoLampada;
		this.medicaoPc = medicaoPc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getMedicaoAr() {
		return medicaoAr;
	}

	public void setMedicaoAr(Double medicaoAr) {
		this.medicaoAr = medicaoAr;
	}

	public Double getMedicaoLampada() {
		return medicaoLampada;
	}

	public void setMedicaoLampada(Double medicaoLampada) {
		this.medicaoLampada = medicaoLampada;
	}

	public Double getMedicaoPc() {
		return medicaoPc;
	}

	public void setMedicaoPc(Double medicaoPc) {
		this.medicaoPc = medicaoPc;
	}

	public String toString() {
		return id + " - " + (data) + " - " + medicaoAr + " - " + medicaoLampada + " - " + medicaoPc;
	}
	
public List<Medicao> gerarLista(String path){
		
		FileReader fr = null;
		BufferedReader br = null;
		String result = "";
		List<Medicao> list = new ArrayList<>();
		Medicao rd = null;
		//SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);

			String line = br.readLine();
			
			//percorre o arquivo
			while (line != null) {
				int cont2 = 0;
				rd = new Medicao();
				
				//percorre a string da vez
				for (int i = 0; i < line.length(); i++) {

					if (line.substring(i, (i + 1)).equals(":")) {
						int cont = 1;
						int cont1 = i + 1;

						//percorre a string a partir do momento que achar o ':'
						while (cont == 1) {

							result += line.substring(cont1, cont1 + 1);
							cont1++;

							if (line.substring(cont1, cont1 + 1).equals("#")) {
								cont = 0;
								cont2++;
							}
						}
						
						//cria o objeto de acordo com a sequencia da string 
						switch (cont2) {
						case 1:
							rd.setId(Integer.parseInt(result));
							break;
						case 2:
							rd.setData(sf.parse(result));
							break;
						case 3:
							result = result.replaceAll(",", ".");
							rd.setMedicaoAr(Double.parseDouble(result));
							break;
						case 4:
							result = result.replaceAll(",", ".");
							rd.setMedicaoLampada(Double.parseDouble(result));
							break;
						case 5:
							result = result.replaceAll(",", ".");
							rd.setMedicaoPc(Double.parseDouble(result));
							break;
						default:
							break;
						}

					}
					result = "";
				}
				line = br.readLine();
				list.add(rd);
			}
		
		} catch (IOException e) {
			System.out.println("Error:" + e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
}
