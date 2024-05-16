package tp.services.mapProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

public interface IWorldMapProvider {
    public String getMap() ;
}
