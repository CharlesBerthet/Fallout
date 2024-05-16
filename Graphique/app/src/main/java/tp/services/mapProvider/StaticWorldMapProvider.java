package tp.services.mapProvider;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tp.SwingProject;

import java.io.*;
import java.net.*;
import java.util.stream.Collectors;

@Primary
@Component
public class StaticWorldMapProvider implements IWorldMapProvider {
    private static String map;

    public StaticWorldMapProvider() throws FileNotFoundException, UnknownHostException, SocketException, UnsupportedEncodingException {
        if(map== null || map.isEmpty()){
        InetAddress localHost = InetAddress.getLocalHost();
        NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
        byte[] hardwareAddress = ni.getHardwareAddress();
        long macAsLong = 0;
        for (int i = 0; i < hardwareAddress.length; i++) {
            macAsLong = (macAsLong << 8) | (hardwareAddress[i] & 0xff);
        }
        macAsLong = macAsLong % 100;
        URL resource = SwingProject.class.getClassLoader().getResource("map_"+(int)macAsLong+".txt");
        String decode = URLDecoder.decode(resource.getPath(), "UTF-8");
        BufferedReader br = new BufferedReader(new FileReader(decode));
        this.map = br.lines().collect(Collectors.joining("\n"));}
    }

    @Override
    public String getMap() {
        return map;
    }
}
