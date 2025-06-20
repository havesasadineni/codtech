package fileaaa;


import java.io.*;

public class FileHandler {

    public static void writeFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            System.out.println("✅ File written successfully.");
        } catch (IOException e) {
            System.err.println("❌ Write Error: " + e.getMessage());
        }
    }

    public static void readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            System.out.println("📄 File content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(">> " + line);
            }
        } catch (IOException e) {
            System.err.println("❌ Read Error: " + e.getMessage());
        }
    }

    public static void modifyFile(String filePath, String oldWord, String newWord) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line.replaceAll(oldWord, newWord)).append("\n");
            }
        } catch (IOException e) {
            System.err.println("❌ Modify Read Error: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content.toString());
            System.out.println("🔁 File modified successfully.");
        } catch (IOException e) {
            System.err.println("❌ Modify Write Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String path = "space_mission_log.txt";

        String content = """
        MISSION LOG - STARDATE 4523.6

        COMMANDER: LUNA VOSS
        MISSION: EXPLORATION OF SECTOR 7X

        CREW STATUS: STABLE
        SUPPLIES: SUFFICIENT

        NOTE: ENCOUNTERED UNIDENTIFIED SIGNAL.
        ACTION REQUIRED: DEPLOY PROBE.

        BASE: NOVA STATION ALPHA

        DELIVERABLE: FULL ANALYSIS REPORT
        """;

        writeFile(path, content);
        readFile(path);

        // Apply modifications
        modifyFile(path, "STARDATE 4523.6", "STARDATE 4620.9");
        modifyFile(path, "DEPLOY PROBE", "RETURN TO BASE");
        readFile(path);
    }
}

