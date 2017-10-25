package projeto.coordenada;

/**
 * Classe que armazena dois numeros inteiros maiores  ou igual a zero.
 * @author 17186, 17168, 17182
 */
public class Coordenada {
	protected int x;
	protected int y;
	
	/**
	 * Instancia a coordenada.
	 * @param xParametro Nao pode ser menor que zero.
	 * @param yParametro Nao pode ser menor que zero.
	 * @throws Exception se as coordenadas forem menor que zero.
	 */
	public Coordenada(int xParametro, int yParametro) throws Exception{
		if ( xParametro < 0 || yParametro <0)
			throw new Exception("Valor(es) invalidos.");
		
		this.x = xParametro;
		this.y = yParametro;
	}
        
        /**
         * Retorna o x da coordenadas.
         * @return X da coordenada.
         */
        public int getX(){            
            return (this.x);
        }
        
        /**
         * Metodo que atribui a X a coordenada.
         * @param xParametro Valor da coordenada X. Não pode ser menor que zero.
         * @throws Exception Se a coordenada for menor que zero.
         */
        public void setX(int xParametro) throws Exception{
            if ( xParametro < 0)
		throw new Exception("Valor invalidos.");
            this.x = xParametro;
        }
        
        /**
         * Retorna o y da coordenadas.
         * @return Y da coordenada.
         */
        public int getY(){            
            return (this.y);
        }
        
        /**
         * Metodo que atribui a Y a coordenada.
         * @param yParametro Valor da coordenada Y. Não pode ser menor que zero.
         * @throws Exception Se a coordenada for menor que zero.
         */
        public void setY(int yParametro) throws Exception{
            if ( yParametro < 0)
		throw new Exception("Valor invalidos.");
            this.y = yParametro;
        }
	
    /**
     * Retorna as coordenadas.
     * @return As coordenadas.
     */
	public String toString() {
		String ret = "(" + this.x + "," + this.y + ")";
		
		return ret;
	}
	
	/**
     * Retorna o hash code da pilha.
     * @return Hash code do objeto.
     */
	public int hashCode() {
		int ret  = 666;
		ret = ret * 7 + new Integer (this.x).hashCode();
		ret = ret * 7 + new Integer (this.y).hashCode();
		
		return ret;
	}
	
    /**
     * Verifica se a instancia de Coordenada eh igual a outro objeto.
     * @param obj O objeto a ser comparado.
     * @return true, se for igual. false, se for diferente.
     */
	public boolean equals(Object obj) {
		if ( this == obj)
			return true;
                
                if ( obj==null)
                    return false;
		
		if (!( obj instanceof Coordenada))
			return false;
		
		Coordenada coord = (Coordenada)obj;
		
		if ( coord.x != this.x)
			return false;
		
		if ( coord.y != this.y)
			return false;
		
		return true;
	}
	
    /**
     * Construtor de copia da Coordenada.
     * @param modelo Coordenada a ser clonada.
     * @throws Exception Se o modelo for nulo.
     */
    public Coordenada (Coordenada modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        this.x = modelo.x;
        this.y = modelo.y;
    }
        
    /**
     * Clona as coordenada.
     * @return O clone da coordenada.
     */
    public Object clone ()
    {
    	Coordenada ret=null;

        try
        {
            ret = new Coordenada (this);
        }
        catch (Exception erro)
        {}
        return ret;
    }
}
