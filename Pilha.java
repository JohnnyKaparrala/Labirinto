package Coordenada;
import java.lang.reflect.Method;

/**
 * Vetor FILO dinamico.
 * @author 17186, 17168, 17172.
 * @param <X> - Tipo da classe.
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
     * Instancia o objeto com tamanho e a taxa de crescimento passados.
     * @param tc Taxa de crescimento do vetor. Este valor eh o percentual de crescimento da lista. Por exemplo, um valor 20 ira incrementar a lista em 20%. Nao pode ser negativo ou zero.
     * @param tam - Valor inicial do tamanho do vetor de armazenamento da pilha. Caso a quantidade de itens na pilha ultrapasse esse valor inicial, a pilha sera aumentada em tc%. Nao pode ser menor ou igual a zero.
     * @throws Exception Se o tamanho for menor que 0.
     */
    public Pilha ( int tam, float tc) throws Exception{
        this.iniciacao ( tam, tc);
    }

    /**
     * Instancia o objeto com tamanho passado. A taxaCrescimento se inicia com 10.
     * @param tam Valor inicial do tamanho do vetor de armazenamento da pilha. Caso a quantidade de itens na pilha ultrapasse esse valor inicial, a pilha sera aumentada em 10%. Nao pode ser menor ou igual a zero.
     * @throws Exception Se o tamanho for menor que 0.
     */
    public Pilha ( int tam) throws Exception{
        this.iniciacao ( tam);
    }

    /**
     * Instancia o objeto. A taxaCrescimento e o tamanho se iniciam com 10.
     * @throws Exception Se o tamanho for menor que 0.
     */
    public Pilha () throws Exception{
        this.iniciacao ();
    }

    protected void cresca (){
        Object[] vet = new Object[ (int) Math.ceil((this.vetor.length * (1.0+taxaCrescimento/100)))];

        for(int i = 0; i < this.vetor.length; i++) {
            vet[i] = this.vetor[i];
        }
        this.vetor = vet;
    }

    /**
     * Acrescenta ao vetor o objeto de tipo X ao final da pilha. A pilha sera aumentada de tamanho, caso necessario para adicionar um novo elemento.
     * @param x Objeto a ser acrescentado na pilha. Este valor nao pode ser nulo.
     * @throws Exception Se o objeto passado for nulo.
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
     * Retorna o elemento mais recente da pilha. Este metodo nao retira o elemento da pilha.
     * @return O ultimo elemento do vetor.
     * @throws Exception Sera lancada uma excecao se a pilha estiver vazia.
     */
    public X getElement () throws Exception{
        if ( this.vazia())
                throw new Exception("pilha vazia");

        return (X)this.vetor[this.top];
    }

    /**
     * Remove do vetor o objeto da ultima posicao.
     * @throws Exception Se nao tiver o que remover.
     * @return O objeto removido.
     */
    public X desempilhe () throws Exception{
        if ( this.vazia())
                throw new Exception("Nao ha o que remover.");

        X ret = (X) this.vetor[this.top];
        this.vetor[this.top] = null;
        this.top--;
        
        return ret;
    }

    protected boolean vazia (){
        return this.top ==-1;
    }

    /**
     * Retorna o vetor e suas posicoes usadas.
     * @return O vetor e suas posicoes usadas.
     */
    public String toString (){
        String ret="{";

        for ( int i=0; i<=this.top; i++)
            ret += this.vetor[i] + ( i != this.top?",":"");

        ret += "}";
        return ret;
    }

    /**
     * Retorna o hash code da pilha.
     * @return Hash code do objeto.
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
     * Verifica se a instancia de Fila é igual a outro objeto.
     * @param obj O objeto a ser comparado.
     * @return true, se for igual. false, se for diferente.
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