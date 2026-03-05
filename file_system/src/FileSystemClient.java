import java.util.List;

public class FileSystemClient {

    public static void main(String[] args) {

        FileSystem fs = new FileSystem("mayank","sudo");

        // Create directories
        fs.mkdir("/home");
        fs.mkdir("/home/mayank");
        fs.mkdir("/home/mayank/docs");

        // Create file
        fs.createFile("/home/mayank/docs/file1.txt");

        // Write data
        fs.writeFile("/home/mayank/docs/file1.txt", "Hello World".getBytes());

        // Append data
        fs.appendFile("/home/mayank/docs/file1.txt", "\nWelcome to FileSystem".getBytes());

        // Read file
        Byte[] data = fs.readFile("/home/mayank/docs/file1.txt" , 0 , 1);
        if (data != null) {
            System.out.println("File Content:");
            StringBuilder sb = new StringBuilder();
            for (Byte datum : data) {
                sb.append(datum);
            }
            System.out.println(sb.toString());
        }

        // List directory
        System.out.println("\nListing /home/mayank/docs:");
        List<String> files = fs.ls("/home/mayank/docs");

        if (files != null) {
            for (String file : files) {
                System.out.println(file);
            }
        }

        // Rename file
        fs.rename("/home/mayank/docs/file1.txt", "notes.txt");

        // Move file
        fs.move("/home/mayank/docs/notes.txt", "/home/mayank");

        // Change permission
//        fs.chmod("/home/mayank/notes.txt", "755");

        // Delete file
        fs.deleteFile("/home/mayank/notes.txt");

        // Remove directory
        fs.rmdir("/home/mayank/docs");

        System.out.println("\nFile system operations completed.");
    }
}