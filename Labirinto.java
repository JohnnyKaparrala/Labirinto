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
        String modo = "Progressivo";
        
        this.atribuiEntrada();
	
        while( this.labirinto[this.atual.getX()][this.atual.getY()] != 'S'){

            this.fila =new Fila<Coordenada>(3);

            this.enfileirePossibilidades();
            
            if(this.fila.vazia())
                 modo = "Regressivo"; 
            
            while ( modo == "Regressivo"){
                
            }
            
            while ( modo == "Progressivo"){
                this.atual = this.fila.desenfileire();

                this.labirinto[this.atual.getX()][this.atual.getY()] = '*';
                this.caminho.empilhe(this.atual);
                this.possibilidades.empilhe(this.fila);
                
                
                this.fila =new Fila<Coordenada>(3);
                this.enfileirePossibilidades();////////
            
                if(this.fila.vazia())
                    modo = "Regressivo";
            }
        }
    }
    
    protected void enfileirePossibilidades() throws Exception{
        int coordX;
        int coordY;
        
        //acima
        coordY = this.atual.getY()-1;
        coordX = this.atual.getX();
        
        if ( coordY >= 0 && coordY <= this.labirinto[coordX].length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordX, coordY));
        }
        
        //a direita
        coordY = this.atual.getY();
        coordX = this.atual.getX()+1;
        
        if ( coordY >= 0 && coordY <= this.labirinto[coordX].length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordX, coordY));
        }
        
        //abaixo
        coordY = this.atual.getY()+1;
        coordX = this.atual.getX();
        
        if ( coordY >= 0 && coordY <= this.labirinto[coordX].length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordX, coordY));
        }
        
        //a esquerda
        coordY = this.atual.getY();
        coordX = this.atual.getX()-1;
        
        if ( coordY >= 0 && coordY <= this.labirinto[coordX].length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordX, coordY));
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
        
        if(obj == null)
            return false;
	if (!( obj instanceof Labirinto))
            return false;
		
	Labirinto lab = (Labirinto)obj;
		
	if(!(this.labirinto.equals(lab.labirinto)))
             return false;
        if(!(this.caminho.equals(lab.caminho)))
            return false; 
        if(!(this.possibilidades.equals(lab.possibilidades)))
            return false;
        if(!(this.atual.equals(lab.atual)))
            return false;
        if(!(this.fila.equals(lab.fila)))
            return false;
		
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

        
        ret = ret*7 + this.caminho.hashCode();
        ret = ret*7 + this.possibilidades.hashCode();
        if(this.atual!=null)
            ret = ret*7 +  this.atual.hashCode();
        if(this.fila!=null)
            ret = ret*7 + this.fila.hashCode();
        
           	
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
