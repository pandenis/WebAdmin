public class OSValidator {

    public String getOperatingSystem() {
        String os = System.getProperty("os.name");
        System.out.println(os);
        return os;
    }

}
