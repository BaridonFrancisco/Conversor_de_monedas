public class pruebasOtraCosas {
    public static void main(String[] args) {
        Model model=new ImpleModel("Hola");
        ImpleModel a=(ImpleModel)model;
        System.out.println(a);


    }

}
class Model{
    String otraCOsa;
}
class ImpleModel extends Model{
    private String  name;

    public ImpleModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Model returnThis(Model clas){
        return clas;
    }

    @Override
    public String toString() {
        return "hi";
    }
}
