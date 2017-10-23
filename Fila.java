package labirinto;
import java.lang.reflect.Method;

/**
 * Vetor FIFO dinamico.
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
     * Instancia o objeto com tamanho e a taxa de crescimento passados.
     * @param tc Taxa de crescimento do vetor. Este valor eh o percentual de crescimento da lista. Por exemplo, um valor 20 ira incrementar a lista em 20%. Nao pode ser negativo ou zero.
     * @param tam - Valor inicial do tamanho do vetor de armazenamento da fila. Caso a quantidade de itens na pilha ultrapasse esse valor inicial, a fila sera aumentada em tc%. Nao pode ser menor ou igual a zero.
     * @throws Exception Se o tamanho for menor que 0.
     */
    public Fila ( int tam, float tc) throws Exception{
        this.iniciacao( tam, tc );
    }

    /**
     * Instancia o objeto com tamanho passado. A taxaCrescimento se inicia com 10.
     * @param tam Valor inicial do tamanho do vetor de armazenamento da fila. Caso a quantidade de itens na fila ultrapasse esse valor inicial, a fila sera aumentada em 10%. Nao pode ser menor ou igual a zero.
     * @throws Exception Se o tamanho for menor que 0.
     */
    public Fila ( int tam) throws Exception{
        this.iniciacao( tam );
    }


    /**
     * Instancia o objeto. A taxaCrescimento e o tamanho se iniciam com 10.
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
     * Acrescenta ao vetor o objeto de tipo X ao final da fila. A fila sera aumentada de tamanho, caso necessario para adicionar um novo elemento.
     * @param x Objeto a ser acrescentado na fila. Este valor nao pode ser nulo.
     * @throws Exception Se o objeto passado for nulo.
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
    
    /**
     * Retorna o elemento mais antigo da fila. Este metodo nao retira o elemento da fila.
     * @return O primeiro elemento do vetor.
     * @throws Exception Sera lancada uma excecao se a fila estiver vazia.
     */
    public X getElemento() throws Exception{
    	if ( this.vazia())
    		throw new Exception ("Fila nula.");
    	
        if ( this.vetor[this.inicio] instanceof Cloneable)
            return this.meuCloneDeX((X)this.vetor[this.inicio]);
        else
            return (X)this.vetor[this.inicio];
    }
    
    /**
     * Remove do vetor o objeto da primeira posicao.
     * @throws Exception Se nao tiver o que remover.
     * @return O objeto removido.
     */
    public X desenfileire() throws Exception{
    	if ( this.vazia())
    		throw new Exception ("Nao ha o que remover.");
    	
        X ret;
        
    	if ( this.vetor[this.inicio] instanceof Cloneable)
            ret = this.meuCloneDeX((X)this.vetor[this.inicio]);
        else
            ret = (X)this.vetor[this.inicio];
        
    	this.vetor[inicio] = null;
    	
    	if ( this.inicio == this.vetor.length-1)
     		this.inicio = 0;
    	else
    		this.inicio++;

    	this.qtd--;
    	
    	return ret;
    }
    
    /**
     * Retorna o vetor e suas posicoes usadas.
     * @return O vetor e suas posicoes usadas.
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
     * Retorna o hash code da fila.
     * @return Hash code do objeto.
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
     * Retorna o hash code da fila.
     * @return Hash code do objeto.
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
     * @return O clone da fila.
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