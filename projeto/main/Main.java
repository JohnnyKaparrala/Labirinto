package projeto.main;
import projeto.labirinto.Labirinto;

public class Main {
    public static void main(String[] args) {
        try {
            /*if( args.length == 0 ) {
                    System.out.println("TODO: exibir help");
                    return;
            }*/
            Labirinto lab = new Labirinto("Z:/01-POO/Labirinto/teste1.txt");/*args[0]*/
            lab.resolva();
            System.out.println(lab.toString());
        }
        catch(Exception erro) {
                erro.printStackTrace();
        }
    }
}
