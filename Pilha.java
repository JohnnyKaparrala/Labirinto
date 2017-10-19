import java.lang.reflect.Method;

/**
 * classe Pilha que serve pra empilhar.
 * @author 17186, 17168, 17172.
 * @param <X> Tipo da classe.
 */
public class Pilha <X> implements Cloneable
{
    protected int top;
    protected Object[] vetor;
    private float taxaCrescimento;

    protected void iniciacao ( int tam, float tc) throws Exception{
        if ( tam < 0)
                throw new Exception("Tamanho invalido");
        if ( tc <= 0)
            throw new Exception("Taxa de crescimento invalida");
        
        this.vetor = new Object[tam];

        this.taxaCrescimento = tc;
        this.top = -1;
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
    public Pilha ( int tam, float tc) throws Exception{
        this.iniciacao ( tam, tc);
    }

    /**
     * Chama iniciacao(tam). A taxaCrescimento se inicia com 10.
     * @param tam Tamanho do vetor.
     * @throws Exception Se o tamanho for menor que 0.
     */
    public Pilha ( int tam) throws Exception{
        this.iniciacao ( tam);
    }

    /**
     * Chama iniciacao(). A taxaCrescimento se inicia com 10.
     * @throws Exception Se o tamanho for menor que 0.
     */
    public Pilha () throws Exception{
        this.iniciacao ();
    }

    protected void cresca (){
        Object[] vet = new Object[ Math.round((float)(this.top * taxaCrescimento/100))];

        for(int i = 0; i <= this.top; i++)
            vet[i] = this.vetor[i];

        this.vetor = vet;
    }

    /**
     * Acrescenta ao vetor a variavel de tipo X.
     * @param x Variavel.
     * @throws Exception Se o valor passado for nulo.
     */
    public void empilhe (X x) throws Exception{
        if ( x == null)
            throw new Exception("valor nulo");

        if ( top == this.vetor.length -1)
            this.cresca();

        this.top++;

        if ( x instanceof Cloneable)
            this.vetor[this.top] = this.meuCloneDeX(x);
        else
            this.vetor[this.top] = x;
    }

    /**
     * Retorna o elemento na posicao do topo do vetor.
     * @return O elemento na posicao do topo do vetor
     * @throws Exception Se nao tiver o que retornar.
     */
    public X getElement () throws Exception{
        if ( this.vazia())
                throw new Exception("pilha vazia");

        return (X)this.vetor[this.top];
    }

    /**
     * Remove do vetor a variavel que esta no topo dele.
     * @throws Exception Se nao tiver o que remover.
     */
    public void desempilhe () throws Exception{
        if ( this.vazia())
                throw new Exception("Nao ha o que remover.");

        this.vetor[this.top] = null;
        this.top--;
    }

    protected boolean vazia (){
        return top ==-1;
    }

    /**
     * Exibe o vetor e seus elementos em forma de String.
     * @return Os elementos do vetor.
     */
    public String toString (){
        String ret="{";

        for ( int i=0; i<=this.top; i++)
            ret += this.vetor[i] + ( i != this.top?",":"");

        ret += "}";
        return ret;
    }

    /**
     * 
     * @return HashCode do vetor.
     */
    public int hashCode ()
    {
        int ret=666;

        ret = ret*7 + new Integer (this.top).hashCode();

        for (int i=0; i<=this.top; i++)
            ret = ret*7 + this.vetor[i].hashCode();

        return ret;
    }

    /**
     * Verifica se a Pilha eh igual a outro objeto.
     * @param obj O objeto a ser comparado.
     * @return true, se for igual. false se for diferente.
     */
    public boolean equals (Object obj){
        if ( this == obj)
                return true;

        if (!(obj instanceof Pilha))
                return false;

        Pilha pilha = (Pilha)obj;

        if ( this.top != pilha.top)
                return false;

        for ( int i = 0; i <= this.top; i++)
                if ( this.vetor[i] != pilha.vetor[i])
                        return false;

        return true;
    }

    /**
     * Construtor de copia da Pilha.
     * @param modelo Pilha a ser clonada.
     * @throws Exception Se o modelo for nulo.
     */
    public Pilha (Pilha modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        this.vetor = new Object [modelo.vetor.length];
        for (int i=0; i<=modelo.top; i++)
            if (this.vetor[i] instanceof Cloneable)
                this.vetor[i] = this.meuCloneDeX((X)modelo.vetor[i]);
            else
                this.vetor[i] = modelo.vetor[i];

        this.top = modelo.top;
    }
        
    /**
     * Clona a pilha.
     * @return O clone da Pilha.
     */
    public Object clone ()
    {
        Pilha ret=null;

        try
        {
            ret = new Pilha (this);
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