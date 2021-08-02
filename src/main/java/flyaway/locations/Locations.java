package flyaway.locations;



public class Locations {
    protected int id;
    protected String name; 
 
    public Locations() {
    }
 
    public Locations(int id) {
        this.id = id;
    }
 
    public Locations(int id, String name) {
        this(name);
        this.id = id;
    }
     
    public Locations(String name) {
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