/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirinto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * 
 */
public class Labirinto {
    
    Character[][] labirinto;
    Pilha<Coordenada> caminho;
    Pilha<Fila<Coordenada>> possibilidades;
    
    private void lerArquivo (String nomeArquivo){
        BufferedReader leitor = null;
        FileReader fr = null;
        try {
        
            fr = new FileReader(nomeArquivo);
            leitor = new BufferedReader(fr);

        
            int linhas = Integer.parseInt(leitor.readLine());
            int colunas = Integer.parseInt(leitor.readLine());
            this.labirinto = new Character[linhas][colunas];
            String linha;

            int i = 0;
            while ((linha = leitor.readLine()) != null){

                int i2 = 0;
                for (Character chr : linha.toCharArray()){
                    labirinto[i][i2] = chr;
                    i2++;
                }
                i++;
            }
        
            try{
                this.caminho = new Pilha<Coordenada>(linhas * colunas);
            }
            catch (Exception e){
                //CADE A EXCESSÃO?
            }
            
            try{
                this.possibilidades = new Pilha<Fila<Coordenada>>(linhas * colunas);
            }
            catch (Exception e){
                //CADE A EXCESSÃO?
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try {

                if (leitor != null)
                    leitor.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {
                    ex.printStackTrace();
            }
        }
    }
}
