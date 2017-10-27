package projeto;
import projeto.Classes.Labirinto;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Por favor, digite o nome e o diretorio do arquivo:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String arquivo = new String(br.readLine());
            
            File f = new File ( arquivo);
            if (! f.exists()){
                throw new Exception ("O arquivo nao pode ser encontrado."); 
            }
            Labirinto lab = new Labirinto(arquivo);
            lab.resolva();
            System.out.println(lab.toString());
        }
        catch(Exception erro) {
        	erro.printStackTrace();
        }
    }
}
