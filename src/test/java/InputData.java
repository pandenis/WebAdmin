public class InputData {

    private static String testURL = "https://stage.cu-bx.com/LoginPage/login";
    private static String filePath = "C:\\Temp\\Sel\\InputData";
    private static String fileName = "Input.xlsx";


    public String getTestURL() {
        return testURL;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setTestURL(String testURL) {
        InputData.testURL = "https://stage.cu-bx.com/LoginPage/login";;
    }

    public void setFilePath(String filePath) {
        InputData.filePath = "C:\\Temp\\Sel\\InputData";;
    }

    public void setFileName(String fileName) {
        InputData.fileName = "Input.xlsx";;
    }
}
