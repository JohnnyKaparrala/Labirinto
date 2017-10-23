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
    Coordenada atual;
    Fila<Coordenada> fila;
    
    /**
     * 
     * @param nomeArquivo 
     */
    public Labirinto (String nomeArquivo){
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
            
            this.caminho = new Pilha<Coordenada>(linhas * colunas);
            this.possibilidades = new Pilha<Fila<Coordenada>>(linhas * colunas);
        }
        catch (Exception e){
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
    
    protected boolean temEntradaESaida() throws Exception {
    	int saidas = 0;
    	int entradas = 0;
    	boolean temUmDeCada = false;
    	
    	for ( int i = 0; i <= this.labirinto.length-1; i++)
    		for ( int j = 0; j <= this.labirinto[i].length-1; j++) {
    			if ( (i == 0 || i == this.labirinto.length-1) || (j == this.labirinto[i].length-1 || j == 0)) {
	    			if ( this.labirinto[i][j] == 'E') {
	    				entradas++;
	    			}    				
	    			if ( this.labirinto[i][j] == 'S') {
	    				saidas++;
	    			}
    			}
    		}
    	
    	if ( entradas == 1 && saidas ==1)
    		temUmDeCada=true;
    	
    	return temUmDeCada;
    }
    
    protected void atribuiEntrada() throws Exception{
        for ( int i = 0; i <= this.labirinto.length-1; i++)
    		for ( int j = 0; j <= this.labirinto[i].length-1; j++) {
    			if ( (i == 0 || i == this.labirinto.length-1) || (j == this.labirinto[i].length-1 || j == 0)) {
	    			if ( this.labirinto[i][j] == 'E') {
	    				this.atual = new Coordenada(i, j);
	    			}
    			}
    		}
    }
    
    /**
     * 
     * @throws Exception 
     */
    public void resolva() throws Exception{
    	if (! this.temEntradaESaida())
    		throw new Exception("Labirinto invalido.");
        this.atribuiEntrada();
	this.fila =new Fila<Coordenada>(3);
    	
    	this.enfileirePossibilidades();
        
    }
    
    protected void enfileirePossibilidades() throws Exception{
        int coordX;
        int coordY;
        
        //acima
        coordY = this.atual.getY()-1;
        coordX = this.atual.getX();
        
        if ( coordY >= 0 && coordY <= this.labirinto[coordX].length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordY, coordX));
        }
        
        //a direita
        coordY = this.atual.getY();
        coordX = this.atual.getX()+1;
        
        if ( coordY >= 0 && coordY <= this.labirinto[coordX].length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordY, coordX));
        }
        
        //abaixo
        coordY = this.atual.getY()+1;
        coordX = this.atual.getX();
        
        if ( coordY >= 0 && coordY <= this.labirinto[coordX].length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordY, coordX));
        }
        
        //a esquerda
        coordY = this.atual.getY();
        coordX = this.atual.getX()-1;
        
        if ( coordY >= 0 && coordY <= this.labirinto[coordX].length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordY, coordX));
        }
    }
    
    /**
     * 
     * @param obj
     * @return 
     */
    public boolean equals(Object obj) {
    	if ( this == obj)
			return true;
		
		if (!( obj instanceof Coordenada))
			return false;
		
		Coordenada coord = (Coordenada)obj;
		
		//for de verificacao
		
		return true;
    }
    
    /**
     * 
     * @return 
     */
    public int hashCode() {
    	int ret  = 666;
    	
    	for ( int i = 0; i <= this.labirinto.length-1; i++)
    		for ( int j = 0; j <= this.labirinto[i].length-1; j++)
    			ret = ret * 7 + this.labirinto[i][j].hashCode();
    	
    	return ret;
    }
    
    /**
     * 
     * @return 
     */
    public String toString() {
    	StringBuilder ret = new StringBuilder();
    	
    	for ( int i = 0; i <= this.labirinto.length-1; i++) {
       		for ( int j = 0; j <= this.labirinto[i].length-1; j++) {
    			ret.append(this.labirinto[i][j]);
    		}
    		ret.append('\n');
    	}    	
    	return ret.toString();
    } 
}
