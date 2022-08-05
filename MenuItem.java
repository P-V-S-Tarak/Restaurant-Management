public class MenuItem {
    protected String name;
    protected String description;
    protected double price;
    protected int id;
    public String category;
    

    

    public String getDescription()
    {
        return this.description;
    }  

    public String getName()
    {
        return this.name;
    }

    public double getPrice()
    {
        return this.price;
    }

    public void setPrice(double price)
    {
        this.price = price; 
    }

    @Override
    public String toString() {
       String s = Integer.toString(id)+ " "+name  + " " + Double.toString(price);
       return s; 
    } 
    
    
    public MenuItem(String name, String description,double price,int id, String category)
    {
        this.id = id;
        this.description = description;
        this.price = price;
        this.name = name;
        this.category = category;
        

    }

    
    

}
