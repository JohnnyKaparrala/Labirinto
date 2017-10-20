import java.lang.reflect.*;

/**
*Classe que armazenas vetors de instancias da classe X, onde sera contido, valores e metodos para a mesma.
*@param <X>  parametro que determina o tipo da classe.
*@author 17172, 17168, 17186.
*/
public class Fila<X> implements Cloneable
{	
    protected int qtd;
    protected int ultimo;
    protected int inicio;
    protected float taxaDeCrescimento;
    protected Object[] vetor;


    protected void iniciacao (int tam, float tc)
{
    this.vetor             = new Object [tam];
    this.ultimo            = -1;
    this.taxaDeCrescimento = tc;
    this.inicio            = 0;
    this.qtd               = 0;   
}

    protected void iniciacao (int tam)
    {
        this.iniciacao (tam,10);
    }

    protected void iniciacao ()
    {
        this.iniciacao(10);
    } 

    /**
	*Contrutor que cria uma vetor de acordo com o tamannho e a taxa de Crescimento passada.
	*@param tam define o tamanho que  vetor vetor tera.
	*@param tc define a taxa de crescimento do vetor.
    */
    public Fila(int tam, float tc) throws Exception
    {
    	if (tam < 0)
    		throw new Exception("Tamanho invalido");

    	if(tc < 0)
    		throw new Exception("Taxa de crescimento invalida");

    	this.iniciacao(tam,tc);		 

    }

    /**
	*Contrutor que cria uma vetor de acordo com o tamannho passada e coloca taxa de crescimento como 10.
	*@param tam define o tamanho que  vetor vetor tera.
    */
	public Fila(int tam) throws Exception
	{
            if(tam < 0)
                    throw new Exception("Tamanho invalido");

            this.iniciacao(tam);
	}

	/**
	*Contrutor que cria uma vetor  o tamannho 10  e coloca taxa de crescimento como 10.
    */
	public Fila()
	{
		this.iniciacao();
	}

    /**
    *Retorna o elemento que esta no inicio da vetor.
    *@return retorna um elemento da vetor da classe X.
    */
    public X getElemento() throws Exception 
    {
        if(this.vazia())
            throw new Exception("O vetor esta vazio");

        if (this.vetor[inicio] instanceof Cloneable)
            return this.meuCloneDeX ((X)this.vetor[inicio]);
        else
            return (X)this.vetor[inicio];

    }

    /**Descarta o primeiro elemento da vetor.
    */
    public void desenfileire()throws Exception
    {
        if(this.vazia())
            throw new Exception("O vetor esta vazio");
        
        this.vetor[inicio]=null;    
        this.inicio++;
        this.qtd--;

           
    }

    /**
    *Inclui um elemnto no final da vetor, chama o metodo Cresca caso necessario.
    *@param x indica o elemnto que devera ser incluido.
    */
    public void enfileire(X x)
    {

            if(this.qtd==this.vetor.length)	
                    this.cresca();
            else
            if(this.ultimo==this.vetor.length-1)
                    this.ultimo = -1;

            this.ultimo++;
            this.qtd++;

            if (x instanceof Cloneable)
        this.vetor[this.ultimo] = this.meuCloneDeX(x);
    else
        this.vetor[this.ultimo] = x;

    }


	protected void cresca()
	{
	float multiplicador = this.taxaDeCrescimento/100+1;
        int   tamNovo       = (int)Math.ceil(this.vetor.length*multiplicador);

        Object[] novo = new Object [tamNovo];
        
        for (int i=0; i<qtd; i++)
            novo[i] = vetor[(i + inicio) % vetor.length];
       

        /**if(this.inicio>0){

        	for(int i=this.inicio; i<this.vetor.length; i++)
        		novo[i-this.inicio]= this.vetor[i];

     
        	for(int i =this.qtd - this.inicio; i<this.qtd;i++)
        		novo[i] = this.vetor[i-(this.qtd - this.inicio)];

        }else
        	for (int i=0; i<=this.vetor.length-1; i++)
            	novo[i] = this.vetor[i]*/;

                
        this.inicio = 0;
        this.ultimo = qtd-1;

        this.vetor = novo;

	}

    /**
    *Verifica se o vetor esta ou nao vazia.
    *@return retorna um boolean que indica se a vetor esta vazia ou nao
    */
	public boolean vazia()
	{
		return this.qtd == 0;

	}


    private X meuCloneDeX (X x)
    {
        X ret = null;

        try
        {
            Class<?> classe = x.getClass();            
            Class<?>[] tipoDoParametroFormal = null; 
            Method metodo = classe.getMethod ("clone", tipoDoParametroFormal);
            Object[] parametroReal = null;        
            ret = ((X)metodo.invoke (x, parametroReal));
        }
        catch (Exception erro)
        {}

        return ret;
    }


   public String toString ()
    {
        String ret="{";

       

//        if(this.inicio>0){
//            if(this.inicio>this.ultimo){
//                for(int i=this.inicio; i<this.vetor.length; i++)
//                    ret += this.vetor[i]+(i!=this.ultimo?",":"");
//
//                for(int i =0; i<this.inicio;i++)
//                    ret += this.vetor[i]+(i!=this.ultimo?",":"");
//            }
//            else
//                for(int i=this.inicio; i<=this.ultimo; i++)
//                    ret += this.vetor[i]+(i!=this.ultimo?",":""); 
//        
//        
//        }else
//            for (int i=0; i<=this.ultimo; i++)
//            	ret += this.vetor[i]+(i!=this.ultimo?",":"");

        for (int i=0; i<qtd; i++)
            ret += vetor[(i + inicio) % vetor.length];

        ret += "}";
        return ret;
    }

    public int hashCode ()
    {
        int ret=666; 

        ret = ret*7 + new Integer (this.ultimo).hashCode();
        ret = ret*7 + new Integer (this.inicio).hashCode();     
        ret = ret*7 + new Integer (this.qtd).hashCode();
        ret = ret*7 + new Float (this.taxaDeCrescimento).hashCode();
        
        if(this.inicio>0){
        	for(int i=this.inicio; i<this.vetor.length-1; i++)
        		ret = ret*7 + this.vetor[i].hashCode();

        	for(int i =this.qtd - this.inicio; i<this.qtd-1;i++)
        		ret = ret*7+ this.vetor[i-(this.qtd - this.inicio)].hashCode();

        }else
        	for (int i=0; i<=this.vetor.length-1; i++)
            	ret += this.vetor[i].hashCode();

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null) 
            return false;

   
        if (!(obj instanceof Fila)) 
            return false;

        
        Fila f = (Fila)obj;

        if (this.inicio!=f.inicio)
            return false;

        if (this.ultimo!=f.ultimo)
            return false;

        if (this.qtd!=f.qtd)
            return false;

        if (this.taxaDeCrescimento!=f.taxaDeCrescimento)
            return false;


        if(this.inicio>0){	
	       	for(int i=this.inicio; i<this.vetor.length-1; i++)
	        	if(!(this.vetor[i].equals(f.vetor[i])))
	        		return false;		
	
	        for(int i =this.qtd - this.inicio; i<this.qtd-1;i++)
	        	if(!(this.vetor[i-(this.qtd - this.inicio)].equals(f.vetor[i-(this.qtd - this.inicio)])))
	       			return false;
        }else
        	for (int i=0; i<=this.vetor.length-1; i++)
            	if(this.vetor[i].equals(f.vetor[i]))
           			return false; 


        return true;
    }

    public Fila (Fila modelo) throws Exception
    {
        if (modelo==null)
            throw new Exception ("Modelo ausente");

        this.vetor = new Object [modelo.vetor.length];
        

        if(this.inicio>0){
        	
	        for(int i=this.inicio; i<this.vetor.length-1; i++)
	        	if(this.vetor[i] instanceof Cloneable)
	        		this.vetor[i] = this.meuCloneDeX((X)modelo.vetor[i]);
	        	else
	        		this.vetor[i] = modelo.vetor[i];

	        	
	        for(int i =this.qtd - this.inicio; i<this.qtd-1;i++)
	        	if(this.vetor[i-(this.qtd - this.inicio)] instanceof Cloneable)
	        		this.vetor[i-(this.qtd - this.inicio)] = this.meuCloneDeX((X)modelo.vetor[i-(this.qtd - this.inicio)]);
	        	else
	        		this.vetor[i-(this.qtd - this.inicio)] = modelo.vetor[i-(this.qtd - this.inicio)];

        }else
        	for (int i=0; i<=this.vetor.length-1; i++)
           		if(this.vetor[i] instanceof Cloneable)
           			this.vetor[i] = this.meuCloneDeX((X)modelo.vetor[i]);
           		else
           			this.vetor[i] = modelo.vetor[i];	    
    

        this.inicio            = modelo.inicio;
        this.ultimo            = modelo.ultimo;
        this.qtd               = modelo.qtd;
        this.taxaDeCrescimento = modelo.taxaDeCrescimento;

    }

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
    
}