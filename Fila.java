import java.lang.reflect.Method;

/**
 * classe Pilha que serve pra empilhar.
 * @author 17186, 17168, 17172.
 * @param <X> Tipo da classe.
 */
public class Fila <X> implements Cloneable
{
    protected int inicio;
    protected int fim;
    protected int qtd;
    protected Object[] vetor;
    private float taxaCrescimento;

    protected void iniciacao ( int tam, float tc) throws Exception{
        if ( tam < 0)
                throw new Exception("Tamanho invalido");
        if ( tc <= 0)
            throw new Exception("Taxa de crescimento invalida");
        
        this.vetor = new Object[tam];

        this.taxaCrescimento = tc;
        this.inicio = 0;
        this.fim = -1;
        this.qtd = 0;
    }

    protected void iniciacao (int tam) throws Exception{
        this.iniciacao ( tam,10);
    }

    protected void iniciacao () throws Exception{
        this.iniciacao ( 10,10);
    }

    /**
     * Construtor do objeto.
     * @param tc Taxa de crescimento do vetor.
     * @param tam Tamanho do vetor.
     * @throws Exception Se o tamanho for menor que 0.
     */
    public Fila ( int tam, float tc) throws Exception{
        this.iniciacao( tam, tc );
    }

    /**
     * Chama iniciacao(tam). A taxaCrescimento se inicia com 10.
     * @param tam Tamanho do vetor.
     * @throws Exception Se o tamanho for menor que 0.
     */
    public Fila ( int tam) throws Exception{
        this.iniciacao( tam );
    }

    /**
     * Chama iniciacao(). A taxaCrescimento se inicia com 10.
     * @throws Exception Se o tamanho for menor que 0.
     */
    public Fila () throws Exception{
        this.iniciacao ();
    }

    protected void cresca (){
    	Object[] vet = new Object[ (int) Math.ceil((this.vetor.length * (1.0+taxaCrescimento/100)))];
    	
    	int j =0;

    	if ( this.fim >= this.inicio)
    		for ( int i = this.inicio; i <= this.fim; i++) {
    			vet[j] = this.vetor[i];
    			j++;
    		}
    	else {
    		for ( int i = this.inicio; i <= this.vetor.length-1; i++)
    			vet[j] = this.vetor[i];
				j++;
    	
    		for ( int i =0; i <= this.fim ; i++)
    			vet[j] = this.vetor[i];
				j++;
    	}
    	
        this.vetor = vet;
        this.inicio = 0;
        this.fim = j-1;
    }
    
    
    /**
     * Acrescenta o objeto no vetor.
     * @param x Objeto a ser acrescentado.
     * @throws Exception
     */
    public void enfileire( X x) throws Exception{
    	if ( x == null)
    		throw new Exception("Parametro invalido.");
    	
    	if ( this.vetor.length == this.qtd)
    		this.cresca();
    	
    	if ( this.fim == this.vetor.length-1)
    		this.fim = 0;
    	else
    		this.fim++;	
    	
    	if ( x instanceof Cloneable)
    		this.vetor[this.fim] = (X)this.meuCloneDeX(x);
    	else
    		this.vetor[this.fim] = x;
    	
    	this.qtd++;
    }
    
    /**
     * Verifica se ha espaco no vetor.
     * @return true, se estiver vazia.
     */
    public boolean vazia() {
    	return this.qtd == 0;
    }
    
    public X getElemento() throws Exception{
    	if ( this.vazia())
    		throw new Exception ("Fila nula.");
    	
    	return (X)this.vetor[this.inicio];
    }
    
    /**
     * Retira o elemento que se encontra em vetor[inicio].
     * @return Objeto que se encontra em vetor[inicio].
     * @throws Exception Se a fila estiver vazia.
     */
    public X desenfileire() throws Exception{
    	if ( this.vazia())
    		throw new Exception ("Nao ha o que remover.");
    	
    	X ret = (X)this.vetor[inicio];
    	this.vetor[inicio] = null;
    	
    	if ( this.inicio == this.vetor.length-1)
     		this.inicio = 0;
    	else
    		this.inicio++;

    	this.qtd--;
    	
    	return ret;
    }
    
    /**
     * Verifica se a instancia de Fila é igual a outro objeto.
     */
    public boolean equals (Object obj){
        if ( this == obj)
                return true;

        if (!(obj instanceof Fila))
                return false;

        Fila pilha = (Fila)obj;

        if ( this.inicio != pilha.inicio)
                return false;
        
        if ( this.fim != pilha.fim)
            return false;
        
        if ( this.qtd != pilha.qtd)
        	return false;
        
        for ( int i = 0; i <= this.vetor.length-1; i++) {
        	if ( this.vetor[i] != pilha.vetor[i]);
        }

        return true;
    }
    
    /**
     * Retorna o vetor e suas posicoes usadas.
     */
    public String toString(){
    	String ret = "{";
    	
    	if ( this.fim >= this.inicio)
    		for ( int i = this.inicio; i <= this.fim; i++)
    			ret += vetor[i]+ (i == this.fim?"":",");
    	else {
    		for ( int i = this.inicio; i <= this.vetor.length-1; i++)
    			ret += vetor[i]+ ",";
    	
    		for ( int i =0; i <= this.fim ; i++)
    			ret += vetor[i]+ (i == this.fim?"":",");
    	}	
    		
    	ret += "}";
    	
    	return ret;
    }
    
    /**
     * Retorna o hashCode da fila.
     */
    public int hashCode() {
    	int ret = 666;
    	
    	ret = ret * 7 + new Integer(this.qtd).hashCode();
    	ret = ret * 7 + new Integer(this.fim).hashCode();
    	ret = ret * 7 + new Integer(this.inicio).hashCode();
    	
    	for ( int i = 0; i <= this.vetor.length-1; i++)
    		ret = ret * 7  + new Integer(( this.vetor[i]).hashCode());
    	
    	return ret;
    }
    
    protected Fila (Fila modelo) throws Exception{
    	if (modelo==null)
            throw new Exception ("Modelo ausente");

        this.vetor = new Object [modelo.vetor.length];
        
        for (int i = 0; i <= modelo.vetor.length-1; i++)
            if (this.vetor[i] instanceof Cloneable)
                this.vetor[i] = this.meuCloneDeX((X)modelo.vetor[i]);
            else
                this.vetor[i] = modelo.vetor[i];

        this.qtd = modelo.qtd;
        this.fim = modelo.fim;
        this.inicio = modelo.inicio;
    }
    
    /**
     * Clona a Fila.
     */
    public Object clone ()
    {
        Fila ret=null;

        try
        {
            ret = new Fila (this);
        }
        catch (Exception erro)
        {}
        return ret;
    }

    protected X meuCloneDeX (X x){
    	X ret = null;

    	try{
    		Class<?> classe = x.getClass();
    		Class[] tipoDoParametroFormal = null;
    		Method metodo = classe.getMethod("clone",tipoDoParametroFormal);
    		Object[] parametroReal = null; 
    		ret = (X)metodo.invoke(x, parametroReal);
    	}
    	catch ( Exception erro)
    	{}

    	return ret;
    }
}