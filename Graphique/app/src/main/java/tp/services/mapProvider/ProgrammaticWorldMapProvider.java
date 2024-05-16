
package tp.services.mapProvider;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import tp.SwingProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class ProgrammaticWorldMapProvider implements IWorldMapProvider {
    private static String map;

    private static String getInstance() throws IOException {
        if(map.isEmpty()) {
            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
            byte[] hardwareAddress = ni.getHardwareAddress();
            long macAsLong = 0;
            for (byte address : hardwareAddress) {
                macAsLong = (macAsLong << 8) | (address & 0xff);
            }
            macAsLong = macAsLong % 32600;
            // Commande à exécuter
            URL resource = SwingProject.class.getClassLoader().getResource("generate.exe");
            assert resource != null;
            String decode = URLDecoder.decode(resource.getPath(), StandardCharsets.UTF_8);
            var proc = Runtime.getRuntime().exec(decode + " " + Integer.valueOf((int) macAsLong).toString());
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));


            map = stdInput.lines().collect(Collectors.joining("\n"));
            var error = stdError.lines().collect(Collectors.joining());
            if (!error.isEmpty()) System.err.println(error);
        }
        return map;
    }

    public ProgrammaticWorldMapProvider() {

    }

    public String getMap() {
        try {
            return getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

