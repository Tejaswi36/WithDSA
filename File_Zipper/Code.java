import java.io.*;
import java.util.*;

public class HuffmanZipper {

    public static void compress(String inputFile, String outputFile) throws IOException {
        // 1. Character Frequency Analysis
        Map<Character, Integer> frequencies = getCharacterFrequencies(inputFile);

        // 2. Huffman Tree Construction
        Node root = buildHuffmanTree(frequencies);

        // 3. Code Assignment
        Map<Character, String> codeMap = getCodeMap(root);

        // 4. Encoding
        encodeFile(inputFile, outputFile, codeMap);
    }

    public static void decompress(String inputFile, String outputFile) throws IOException {
        // (Optional) Implement decompression using the Huffman tree structure stored in the compressed file
    }

    private static Map<Character, Integer> getCharacterFrequencies(String filename) throws IOException {
        Map<Character, Integer> frequencies = new HashMap<>();
        try (FileReader reader = new FileReader(filename)) {
            int ch;
            while ((ch = reader.read()) != -1) {
                char c = (char) ch;
                frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
            }
        }
        return frequencies;
    }

    private static Node buildHuffmanTree(Map<Character, Integer> frequencies) {
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.frequency - n2.frequency);
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            queue.offer(new Node(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            Node parent = new Node(null, left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            queue.offer(parent);
        }

        return queue.poll();
    }

    private static Map<Character, String> getCodeMap(Node root) {
        Map<Character, String> codeMap = new HashMap<>();
        encodeHelper(root, "", codeMap);
        return codeMap;
    }

    private static void encodeHelper(Node node, String code, Map<Character, String> codeMap) {
        if (node == null) {
            return;
        }

        if (node.character != null) {
            codeMap.put(node.character, code);
            return;
        }

        encodeHelper(node.left, code + "0", codeMap);
        encodeHelper(node.right, code + "1", codeMap);
    }

    private static void encodeFile(String inputFile, String outputFile, Map<Character, String> codeMap) throws IOException {
        try (FileReader reader = new FileReader(inputFile);
             FileWriter writer = new FileWriter(outputFile)) {
            int ch;
            StringBuilder bitString = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                char c = (char) ch;
                String code = codeMap.get(c);
                if (code == null) {
                    // Handle unsupported characters (optional)
                    throw new RuntimeException("Unsupported character encountered: " + c);
                }
                bitString.append(code);
                // Write bits to output file efficiently (e.g., buffer and convert to bytes)
            }

            // Write buffered bits to output file
            writer.write(bitString.toString());
        }
    }

    private static class Node {
        Character character;
        int frequency;
        Node left, right;

        public Node(Character character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }
    }

    public static void main(String[] args) throws IOException {
        String inputFile = "C:\Users\tejaswi\Desktop\DSA Projects\File Zipper\file-sample_100kB.doc";
        String outputFile = inputFile + ".huffman";
        compress(inputFile, outputFile);
        System.out.println("File compressed to: " + outputFile);
    }
}
