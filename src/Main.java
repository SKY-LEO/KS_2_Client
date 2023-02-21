import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            byte[] sendingDataBuffer = new byte[1024];
            byte[] receivingDataBuffer = new byte[1024];

            while (true) {
                if (reader.ready()) {
                    String clientCommand = reader.readLine();
                    sendingDataBuffer = clientCommand.getBytes();
                    DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length,
                            address, 6000);
                    socket.send(sendingPacket);
                    if (clientCommand.equals("-1")) {
                        System.out.println("Выход");
                        break;
                    }
                    DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer,
                            receivingDataBuffer.length);
                    socket.receive(receivingPacket);
                    String input_string = new String(receivingPacket.getData(), receivingPacket.getOffset(),
                            receivingPacket.getLength());
                    System.out.println("Получены данные:\n" + input_string);
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}