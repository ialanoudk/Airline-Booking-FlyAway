    package flyaway.airlines;



public class AireLine {
    protected int id;
    protected String name; 
 
    public AireLine() {
    }
 
    public AireLine(int id) {
        this.id = id;
    }
 
    public AireLine(int id, String name) {
        this(name);
        this.id = id;
    }
     
    public AireLine(String name) {
        this.name = name;
    }
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getname() {
        return name;
    }
 
    public void setname(String name) {
        this.name = name;
    }
 
}
    
