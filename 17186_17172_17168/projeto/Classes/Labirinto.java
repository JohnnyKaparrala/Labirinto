package projeto.Classes;
import projeto.Classes.Fila;
import projeto.Classes.Pilha;
import projeto.Classes.Coordenada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe que resolve labirintos.
 * @author 17186, 17168, 17172
 */
public class Labirinto {
    
    Character[][] labirinto;
    Pilha<Coordenada> caminho;
    Pilha<Fila<Coordenada>> possibilidades;
    Coordenada atual;
    Fila<Coordenada> fila;
    
    /**
     * Instancia o labirinto de acordo com o parametro String que representa o nome do arquivo.
     * @param nomeArquivo Nome do arquivo a ser lido.
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
    	
    	// procura na primeira linha
    	for ( int i = 1; i <= this.labirinto[0].length-2; i++) { // comeca com um e vai ate o dois para nao procurar duas vezes nas vertices.
            if ( this.labirinto[0][i] == 'E') {
                    entradas++;
            }
            if ( this.labirinto[0][i] == 'S') {
                    saidas++;
            }
    	}

    	// procura na ultima linha
	for ( int i2 = 1; i2 <= this.labirinto[0].length-2; i2++) {	// comeca com um e vai ate o dois para nao procurar duas vezes nas vertices.
    		if ( this.labirinto[this.labirinto.length-1][i2] == 'E') {
    			entradas++;
    		}
    		if ( this.labirinto[this.labirinto.length-1][i2] == 'S') {
				saidas++;
    		}
    	}
		
    	// procura na primeira coluna
	for ( int i3 = 0; i3 <= this.labirinto.length-1; i3++) {
    		if ( this.labirinto[i3][0] == 'E') {
    			entradas++;
    		}
    		if ( this.labirinto[i3][0] == 'S') {
				saidas++;
    		}
    	}
		
    	// procura na ultima coluna
	for ( int i4 = 0; i4 <= this.labirinto.length-1; i4++) {
    		if ( this.labirinto[i4][this.labirinto[0].length-1] == 'E') {
    			entradas++;
    		}
    		if ( this.labirinto[i4][this.labirinto[0].length-1] == 'S') {
				saidas++;
    		}
    	}
    	
    	if ( entradas == 1 && saidas ==1)
    		temUmDeCada=true;
    	
    	return temUmDeCada;
    }
    
    protected void atribuiEntrada() throws Exception{
    	// procura na primeira linha
    	for ( int i = 1; i <= this.labirinto[0].length-2; i++) { // comeca com um e vai ate o dois para nao procurar duas vezes nas vertices.
            if ( this.labirinto[0][i] == 'E') {
                    this.atual = new Coordenada(0, i);
            }
    	}

    	// procura na ultima linha
    	for ( int i2 = 1; i2 <= this.labirinto[0].length-2; i2++) {	// comeca com um e vai ate o dois para nao procurar duas vezes nas vertices.
    		if ( this.labirinto[this.labirinto.length-1][i2] == 'E') {
    			this.atual = new Coordenada(this.labirinto.length-1, i2);
    		}
    	}
		
		// procura na primeira coluna
		for ( int i3 = 0; i3 <= this.labirinto.length-1; i3++) {
    		if ( this.labirinto[i3][0] == 'E') {
    			this.atual = new Coordenada(i3, 0);
    		}
    	}
		
    	// procura na ultima coluna
		for ( int i4 = 0; i4 <= this.labirinto.length-1; i4++) {
    		if ( this.labirinto[i4][this.labirinto[0].length-1] == 'E') {
    			this.atual = new Coordenada(i4, this.labirinto[0].length-1);
    		}
    	}
    }
    
    /**
     * Resolve o labirinto.
     * @throws Exception Se o labirinto tiver o numero de entradas e de saidas diferentes de um ou se a saida for inalcancavel.
     */
    public void resolva() throws Exception{
    	if (! this.temEntradaESaida())
    		throw new Exception("Labirinto invalido. Ele deve conter exatamente uma entrada e uma saida, posicionadas em suas bordas.");
        String modo = "Progressivo";
        
        this.atribuiEntrada();
	
        while( this.labirinto[this.atual.getX()][this.atual.getY()] != 'S'){

            this.fila =new Fila<Coordenada>(3);
            if(modo=="Progressivo")
                this.enfileirePossibilidades();
            
            if(this.fila.vazia())
                 modo = "Regressivo";
            
            
            while ( modo == "Regressivo"){
            	if ( this.caminho.vazia())
                    throw new Exception("Saida do labirinto nao pode ser alcancada.");
                this.atual = this.caminho.getElement(); 
                this.caminho.desempilhe();
                this.labirinto[this.atual.getX()][this.atual.getY()] = ' ';

                this.fila = this.possibilidades.getElement(); 
                this.possibilidades.desempilhe();
                if(!(this.fila.vazia()))
                    modo = "Progressivo";
            }
            
            while ( modo == "Progressivo"){
                
                this.atual = this.fila.getElement(); 
                this.fila.desenfileire();

                if ( this.labirinto[this.atual.getX()][this.atual.getY()] != 'S'){
                    this.labirinto[this.atual.getX()][this.atual.getY()] = '*';
                    this.caminho.empilhe(this.atual);
                    this.possibilidades.empilhe(this.fila);
         
                    this.fila =new Fila<Coordenada>(3);
                    this.enfileirePossibilidades();
                }
            
                if(this.fila.vazia())
                    modo = "Regressivo";
            }
        }
    }
    
    protected void enfileirePossibilidades() throws Exception{
        int coordX;
        int coordY;
        
        //acima
        coordY = this.atual.getY()+1;
        coordX = this.atual.getX();
        
        if ( coordY >= 0 && coordY <= this.labirinto[coordX].length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordX, coordY));
        }
        
        //a direita
        coordY = this.atual.getY();
        coordX = this.atual.getX()+1;
        
        if ( coordX >= 0 && coordX <= this.labirinto.length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordX, coordY));
        }
        
        //abaixo
        coordY = this.atual.getY()-1;
        coordX = this.atual.getX();
        
        if ( coordY >= 0 && coordY <= this.labirinto[coordX].length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordX, coordY));
        }
        
        //a esquerda
        coordY = this.atual.getY();
        coordX = this.atual.getX()-1;
        
        if ( coordX >= 0 && coordX <= this.labirinto.length-1){
            if ( this.labirinto[coordX][coordY] == ' '  || this.labirinto[coordX][coordY] == 'S')
                this.fila.enfileire(new Coordenada(coordX, coordY));
        }
    }
    
    /**
     * Verifica se a instancia de Coordenada eh igual a outro objeto.
     * @param obj O objeto a ser comparado.
     * @return true, se for igual. false, se for diferente.
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
     * Retorna o hash code do labirinto.
     * @return Hash code do objeto.
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
     * Representacao do labirinto e as coordenadas usadas para chegar a saida.
     * @return O labirinto e o caminho ate sua saida.
     */
    public String toString() {
    	StringBuilder ret = new StringBuilder();
    	
    	for ( int i = 0; i <= this.labirinto.length-1; i++) {
       		for ( int j = 0; j <= this.labirinto[i].length-1; j++) {
    			ret.append(this.labirinto[i][j]);
    		}
    		ret.append('\n');
    	}
        
        ret.append(this.caminho.toString());//a Plha<Coordenada> inverso estava mostrando o caminho invertido (da saida para a entrada),
    	return ret.toString();              //entao, para exibir na ordem certa, nao precisou cria-la.
    } 
}
