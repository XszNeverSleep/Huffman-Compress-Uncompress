package project;

import java.io.IOException;
import java.util.*;

import static project.Util.getBinaryString;

public class HuffmanTree {
    private Map<Byte, String> huffmanCodes = new HashMap<>();

    private StringBuilder stringBuilder = new StringBuilder();

    private StringBuilder linear = new StringBuilder();

    private Node root;

    /*
    *
    *
    * */

    public void create(byte[] bytes) {
        Map<Byte, Integer> counts = new HashMap<>();
        for(byte b : bytes) {
            counts.merge(b, 1, Integer::sum);
        }
        Queue<Node> queue = new PriorityQueue<>();
        for(Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }
        while (queue.size() > 1) {
            Node leftNode = queue.poll();
            Node rightNode = queue.poll();
            assert rightNode != null;
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            queue.add(parent);
        }
        this.root = queue.poll();

    }
    public void preOrder() {
        if(root == null)
            System.out.println("Huffman tree is empty!");
        else
            root.preOrder();
    }

    public void getHuffmanCodes() {
        if(root == null)
            return;
        getHuffmanCodes(root.left, "0", stringBuilder);
        getHuffmanCodes(root.right, "1", stringBuilder);
    }

    public void getHuffmanCodes(Node node, String path, StringBuilder stringBuilder) {
        StringBuilder curBuilder = new StringBuilder(stringBuilder);
        curBuilder.append(path);
        if(node != null) {
            if(node.data == null) {
                getHuffmanCodes(node.left, "0", curBuilder);
                getHuffmanCodes(node.right, "1", curBuilder);
            } else {
                huffmanCodes.put(node.data, curBuilder.toString());
            }
        }
    }
    public void delinearization(BitHandling bh) throws IOException {
        this.root = _delinearization(bh);
    }
    public Node _delinearization(BitHandling bh) throws IOException {
        Node node;
        if(bh.readByte()== 1) {
            node = new Node(null);
            node.left = _delinearization(bh);
            node.right = _delinearization(bh);
        }
        else {
            node = new Node(Integer.valueOf(bh.readByte()).byteValue());
        }
        return node;
    }

    public String decode(String compressedData) throws NullPointerException {
        try {
            StringBuilder code = new StringBuilder();
            Node node = root;
            for(int i = 0; i < compressedData.length(); i++) {
                node = compressedData.charAt(i) == '0' ? node.left : node.right;
                if(node.data != null) {
                    code.append(getBinaryString(node.data));
                    node = root;
                }
            }
            return code.toString();
        }catch (NullPointerException e){
            throw new NullPointerException("HUFFMANTREE-ERROR");
        }

    }

    public String linearization() {
        preOrder();
        return linear.toString();
    }

    public String enCode(byte[] bytes) {
        stringBuilder = new StringBuilder();
        for(byte val : bytes) {
            stringBuilder.append(huffmanCodes.get(val));
        }
        return stringBuilder.toString();
    }


    private class Node implements Comparable<Node>{

        Byte data;
        int weight;
        Node left;
        Node right;

        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        public Node(Byte data) {
            this.data = data;
        }

        public void preOrder() {
            System.out.println(this);
            if(this.data == null) {
                linear.append("00000001");
            }
            else {
                linear.append("00000000");
                linear.append(getBinaryString(this.data));
            }

            if(this.left != null)
                this.left.preOrder();
            if(this.right != null)
                this.right.preOrder();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", weight=" + weight +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
