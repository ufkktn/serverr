import java.io.*;
import java.net.*;

public class ServerDeneme {

    public static void main(String[] args) {
        System.out.println("Server Program");
        int byteRead = 0;
        try {
            ServerSocket sunucuSoketi = new ServerSocket(7525);
            if (!sunucuSoketi.isBound())
                System.out.println("Sunucu Soketi Sınırlandırılmadı...");
            else
                System.out.println("Sunucu Soketi Bağlantı Noktasına Bağlı : " + sunucuSoketi.getLocalPort());

            Socket clientSocket = sunucuSoketi.accept();
            if (!clientSocket.isConnected())
                System.out.println("İstemci Soketi Bağlı Değil...");
            else
                System.out.println("İstemci Soketi Bağlandı : " + clientSocket.getInetAddress());

            while (true) {
                InputStream in = clientSocket.getInputStream();

                OutputStream os = new FileOutputStream("C:\\Users\\ufukk\\Desktop/text.txt");
                byte[] byteArray = new byte[100];

                while ((byteRead = in .read(byteArray, 0, byteArray.length)) != -1) {
                    os.write(byteArray, 0, byteRead);
                    System.out.println("No. Alınan Bayt Sayısı : " + byteRead);
                }
                synchronized(os) {
                    os.wait(100);
                }
                os.close();
                sunucuSoketi.close();
                //System.out.println("File Received...");
            }

        } catch (Exception e) {
            System.out.println("Server Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }

}