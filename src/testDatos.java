
public class testDatos {

    public static void main(String[] args) throws Exception {
        TestDireccion fc = new TestDireccion();

        try {
            
            System.out.println(fc.obtieneDirecion("-4.00934408333333", "-79.1861944166667"));
            System.out.println(fc.obtieneDirecion("-4.0093389", "-79.1861953166667"));
        } catch (Exception e) {
            
        }
    }

}
